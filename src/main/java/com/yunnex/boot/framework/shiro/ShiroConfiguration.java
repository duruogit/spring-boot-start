package com.yunnex.boot.framework.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro权限配置类
 * @author yuwenjun
 * @date 2017年12月7日 下午4:35:17
 */
//@Configuration
public class ShiroConfiguration {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter() start...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        
        // 基于Form表单的身份验证过滤器 
//        Map<String,Filter> filterMap = new HashMap<String,Filter>();
//        filterMap.put("authc", formAuthenticate());
//        shiroFilterFactoryBean.setFilters(filterMap);
        
        // 拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接,所有静态资源不做拦截控制
        filterChainDefinitionMap.put("/assets/**", "anon");
        // 设置 ajax 登录 url请求不被拦截
        filterChainDefinitionMap.put("/boot/ajaxLogin", "anon");
        
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        
        //过滤链定义，从上向下顺序执行，一般将  /** = authc 放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //设置其他的所有请求资源都必须认证通过才能访问
        filterChainDefinitionMap.put("/**", "authc");
        
        // 配置不会被拦截的链接,所有静态资源不做拦截控制
        filterChainDefinitionMap.put("/assets/**", "anon");
        
        // 设置系统拦截后的登录链接
        shiroFilterFactoryBean.setLoginUrl("/boot/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/boot/home");
        
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/boot/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        return shiroFilterFactoryBean;
	}
	
	@Bean
    public ShiroRealm shiroRealm(){
		ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }
	
	/**
	 * 缓存管理器 使用Ehcache实现
	 * @return
	 */
	@Bean
	public EhCacheManager ehCacheManager(){
		EhCacheManager ehCacheManger = new EhCacheManager();
		ehCacheManger.setCacheManagerConfigFile("classpath:META-INF/ehcache.xml");
		return ehCacheManger;
	}

	/**
	 * 安全管理器
	 * @return
	 */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }
    
    /**
     * 基于Form表单的身份验证过滤器
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticate(){
//    	BootFormAuthenticationFilter formFilter = new BootFormAuthenticationFilter();
    	FormAuthenticationFilter formFilter = new FormAuthenticationFilter();
    	formFilter.setUsernameParam("username");
    	formFilter.setPasswordParam("password");
    	formFilter.setLoginUrl("/boot/login");
    	formFilter.setSuccessUrl("/boot/home");
    	
    	return formFilter;
    }
    
    
    
    class ShiroRealm extends AuthorizingRealm  {
        public String getName() {
            return "ShiroRealm";
        }

        public boolean supports(AuthenticationToken token) {
            return token instanceof UsernamePasswordToken;
        }
        
        /**
         * 认证完成进行授权
         */
		@Override
		protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
			LOGGER.info("....登录认证后进行权限认证....");
			Object user = SecurityUtils.getSubject().getPrincipal();
			
			List<String> permissions  = new ArrayList<String>();
			 // 查到权限数据，返回授权信息(要包括 上边的permissions)
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			
			if(user != null) {
				//用户名
				String userName = user.toString();
				//TODO 根据用户名查数据库或者配置文件查看 当前用户所拥有的角色和权限信息
				permissions.add(userName);
				// 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
				simpleAuthorizationInfo.addStringPermissions(permissions);
			}
			
			return simpleAuthorizationInfo;
		}

		/**
		 * 登录认证
		 */
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
			LOGGER.info("....登录操作进行登录认证...");
			String userName = (String) token.getPrincipal();
            String passWord = new String((char[]) token.getCredentials());
            if ("admin@qq.com".equals(userName) && "123ewq".equals(passWord)) {
            	LOGGER.debug("login in success, userName is: {}", userName);
            	Map<String,String> map = new HashMap<String,String>();
            	map.put("username", userName);
                return new SimpleAuthenticationInfo(map, passWord, getName());
            } else {
                // 5、身份验证失败
            	LOGGER.debug("login in success, userName is: {}", userName);
                throw new AuthenticationException("用户名或密码错误");
            }
		}
    }
    
}
