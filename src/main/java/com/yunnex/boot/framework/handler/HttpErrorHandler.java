package com.yunnex.boot.framework.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统错误页面转跳控制处理类
 * @author yuwenjun
 * @date 2017年12月7日 下午3:41:56
 */
@Controller
public class HttpErrorHandler implements ErrorController {
	
	/**
	 * 默认的错误处理页面
	 */
	private final static String ERROR_PATH = "/error";
	
	/**
     * Supports the HTML Error View
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorHtml(HttpServletRequest request, HttpServletResponse response) {
    	String returnPage = "";
    	switch (response.getStatus()) {
			case HttpStatus.SC_NOT_FOUND :
				returnPage = String.valueOf(HttpStatus.SC_NOT_FOUND);
				break;
	
			default:
				returnPage = String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
    	
        return returnPage;
    }

    /**
     * Supports other formats like JSON, XML
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request, HttpServletResponse response) {
    	String returnPage = "";
    	switch (response.getStatus()) {
			case HttpStatus.SC_NOT_FOUND :
				returnPage = String.valueOf(HttpStatus.SC_NOT_FOUND);
				break;
	
			default:
				returnPage = String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
    	
        return returnPage;
    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
