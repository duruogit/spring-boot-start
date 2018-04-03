package com.yunnex.boot.framework.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunnex.boot.framework.entity.ResultEntity;
import com.yunnex.boot.framework.exception.BootFrameworkException;
import com.yunnex.boot.framework.util.ResultEntityBuilder;

/**
 * 统一异常拦截处理器,处理Controller中抛出的全局异常Exception, 系统自定义异常BootFrameworkException
 * 以及参数校验异常 MethodArgumentNotValidException,在controller中使用时必须显示抛出异常,此处才会被
 * 捕获并加以处理;记录错误操作日志
 * @author yuwenjun
 * @date 2017年12月7日 下午3:43:46
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	/**
	 * 全局异常捕获处理方法
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
    private <T> ResultEntity<T> exceptionHandler(Exception e) {
		ResultEntity<T> result = ResultEntityBuilder.buildError(999, e.getMessage());
        LOGGER.error("", e);
        return result;
    }
	
	/**
	 * 自定义异常捕获处理方法
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(BootFrameworkException.class)
    private <T> ResultEntity<T> runtimeExceptionHandler(Exception e) {
		ResultEntity<T> result = ResultEntityBuilder.buildError(999, e.getMessage());
        LOGGER.error("", e);
        if( e instanceof BootFrameworkException){
        	result.setCode(((BootFrameworkException) e).getCode());
        }
        return result;
    }
	
	/**
	 * 参数转换异常捕获处理方法
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
    private <T> ResultEntity<T> illegalParamsExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("---------> invalid request!", e);
        return ResultEntityBuilder.buildError(333, e.getMessage());
    }

}
