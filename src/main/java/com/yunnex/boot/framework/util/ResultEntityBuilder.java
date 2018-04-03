package com.yunnex.boot.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunnex.boot.framework.entity.ResultEntity;

/**
 * {@link ResultEntity 类的生成工具类}
 * <p>Description: <／p>
 * @author yuwenjun
 * @date 2017年12月7日 上午11:51:42
 */
public class ResultEntityBuilder {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(ResultEntityBuilder.class);

	 /**
	  * 创建一个成功的返回对象
	  * @param data
	  * @param message
	  * @return
	  */
	 public static <T> ResultEntity<T> build(T data, String message) {
		 ResultEntity<T> result = ResultEntity.build();
         result.setCode(ResultEntity.SUCCESS);
         result.setData(data);
         result.setMessage(message);
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("build result:{}", result);
         }
         
         return result;
	 }
	 
	 /**
	  * 创建一个错误的返回对象
	  * @param data
	  * @param message
	  * @return
	  */
	 public static <T> ResultEntity<T> buildError(String message) {
		 ResultEntity<T> result = ResultEntity.build();
         result.setCode(ResultEntity.FAIL);
         result.setMessage(message);
         if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("build result:{}", result);
         }
         
         return result;
	 }
	 
	 /**
	  * 创建指定错误码的返回对象
	  * @param code
	  * @param message
	  * @return
	  */
	 public static <T> ResultEntity<T> buildError(Integer code, String message){
		 ResultEntity<T> result = buildError(message);
		 result.setCode(code);
		 
		 return result;
	 }
}
