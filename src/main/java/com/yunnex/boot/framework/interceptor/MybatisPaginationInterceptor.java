package com.yunnex.boot.framework.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.yunnex.boot.framework.page.Dialect;
import com.yunnex.boot.framework.page.MySql5Dialect;
import com.yunnex.boot.framework.page.MySql5PageHepler;
import com.yunnex.boot.framework.page.PageInfo;
import com.yunnex.boot.framework.page.ReflectionUtil;

/**
 * 分页拦截器,附加了返回行数限制功能 ，查询不带limit或者limit>{maxrow}时，自动设置limit {maxrow}
 * @author yuwenjun
 * @date 2017年12月5日 下午3:38:33
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisPaginationInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(MybatisPaginationInterceptor.class);

    Dialect dialect = new MySql5Dialect();
    
    @Value("200")
    private Integer rowlimit;

    @SuppressWarnings("unchecked")
	public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sqlId = mappedStatement.getId();
        String originalSql = boundSql.getSql().trim();
        RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];

        Object parameterObject = boundSql.getParameterObject();
        if (boundSql == null || boundSql.getSql() == null || "".equals(boundSql.getSql())) return null;
        // 分页参数--上下文传参
        PageInfo page = null;

        // map传参每次都将currentPage重置,先判读map再判断context
        if (parameterObject instanceof PageInfo) {
            page = (PageInfo) parameterObject;
        } else if (parameterObject instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) parameterObject;
            if (map.containsKey("page")) {
                page = (PageInfo) map.get("page");
            }
        } else if (null != parameterObject) {
            Field pageField = ReflectionUtil.getFieldByFieldName(parameterObject, "page");
            if (pageField != null) {
                page = (PageInfo) ReflectionUtil.getValueByFieldName(parameterObject, "page");
            }
        }
        
        

        // 后面用到了context的东东
        if (page != null && page.isPagination() == true) {
            if (page.getPageSize() > rowlimit) {
                logger.warn("[toolarge_pagesize] page size greater than " + rowlimit + ",#sqlid:{}#,#pagesize:{}#,#sql:{}#", sqlId,
                    page.getPageSize(), originalSql);
                page.setPageSize(rowlimit);
            }

            int totalRows = page.getTotalRows();
            // 得到总记录数
            if (totalRows == 0 && page.isNeedCount()) {
                StringBuffer countSql = new StringBuffer();
                countSql.append(MySql5PageHepler.getCountString(originalSql));
                Connection connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
                PreparedStatement countStmt = connection.prepareStatement(countSql.toString());
                BoundSql countBS =
                                new BoundSql(mappedStatement.getConfiguration(), countSql.toString(), boundSql.getParameterMappings(),
                                                parameterObject);
                Field metaParamsField = ReflectionUtil.getFieldByFieldName(boundSql, "metaParameters");
                if (metaParamsField != null) {
                    MetaObject mo = (MetaObject) ReflectionUtil.getValueByFieldName(boundSql, "metaParameters");
                    ReflectionUtil.setValueByFieldName(countBS, "metaParameters", mo);
                }
                setParameters(countStmt, mappedStatement, countBS, parameterObject);
                ResultSet rs = countStmt.executeQuery();
                if (rs.next()) {
                    totalRows = rs.getInt(1);
                }
                rs.close();
                countStmt.close();
                connection.close();
            }

            // 分页计算
            page.init(totalRows, page.getPageSize(), page.getCurrentPage());

            if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
                rowBounds = new RowBounds(page.getPageSize() * (page.getCurrentPage() - 1), page.getPageSize());

            }

            // 分页查询 本地化对象 修改数据库注意修改实现
            String pagesql = dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
            BoundSql newBoundSql =
                            new BoundSql(mappedStatement.getConfiguration(), pagesql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            Field metaParamsField = ReflectionUtil.getFieldByFieldName(boundSql, "metaParameters");
            if (metaParamsField != null) {
                MetaObject mo = (MetaObject) ReflectionUtil.getValueByFieldName(boundSql, "metaParameters");
                ReflectionUtil.setValueByFieldName(newBoundSql, "metaParameters", mo);
            }
            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

            invocation.getArgs()[0] = newMs;
        }

        return invocation.proceed();

    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    public void setProperties(Properties arg0) {

    }

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     * 
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement "
                                        + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.keyProperty(buildKeyProperty(ms.getKeyProperties()));
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.useCache(ms.isUseCache());
        builder.cache(ms.getCache());
        MappedStatement newMs = builder.build();
        return newMs;
    }

    private static String buildKeyProperty(String[] props) {
        if (null != props && props.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (String p : props) {
                sb.append(p).append(",");
            }

            return sb.substring(0, sb.length() - 1);
        }
        return null;
    }

}
