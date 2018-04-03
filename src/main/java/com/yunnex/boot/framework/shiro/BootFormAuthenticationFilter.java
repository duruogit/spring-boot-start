package com.yunnex.boot.framework.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunnex.boot.framework.entity.ResultEntity;
import com.yunnex.boot.framework.util.ResultEntityBuilder;

public class BootFormAuthenticationFilter extends FormAuthenticationFilter {
	
	private static final Logger LOG = LoggerFactory.getLogger(BootFormAuthenticationFilter.class);  
	
	/**
	 * 访问拒绝时
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		 if (isLoginRequest(request, response)) {  
             if (isLoginSubmission(request, response)) {  
                 if (LOG.isTraceEnabled()) {  
                	 LOG.trace("Login submission detected.  Attempting to execute login.");  
                 }  
                 return executeLogin(request, response);  
             } else {  
                 if (LOG.isTraceEnabled()) {  
                	 LOG.trace("Login page view.");  
                 }  
                 //allow  to access the login page
                 return true;  
             }  
         } else {  
             if (LOG.isTraceEnabled()) {  
            	 LOG.trace("Attempting to access a path which requires authentication.  Forwarding to the " +  
                         "Authentication url [" + getLoginUrl() + "]");  
             }  
//             if(isAjax(request)){  
//                 return true; 
//             }else{  
//                 this.saveRequestAndRedirectToLogin(request, response);  
//             }  
             this.saveRequestAndRedirectToLogin(request, response); 
             return false;  
         }  
	}
	
	
    /**
     * 当登录成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        //将user对象放入session，这里你可能用不到，可以删除
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("username",token.getPrincipal().toString());
//        UserDto userDto = userMapper.findUserDto(params);
//        //----------以上代码你可以删除-------------------
//        ((HttpServletRequest)request).getSession().setAttribute(Constant.CURRENT_USER,userDto);

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            ResultEntity<?> rsult = ResultEntityBuilder.build(null, "登录成功");
            out.println(rsult.toString());
            out.flush();
            out.close();
        }
        return false;
    }
    
    /**
     * 当登录失败
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {// 不是ajax请求
            setFailureAttribute(request, e);
            return true;
        }
        try {
        	ResultEntity<?> rsult = ResultEntityBuilder.buildError("");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
            	rsult.setMessage("密码错误");
            } else if ("UnknownAccountException".equals(message)) {
            	rsult.setMessage("账号不存在");
            } else if ("LockedAccountException".equals(message)) {
            	rsult.setMessage("账号被锁定");
            } else {
            	rsult.setMessage("未知错误");
            }
            
            out.println(rsult.toString());
            out.flush();
            out.close();
        } catch (IOException e1) {
            LOG.error("用户登陆失败!",e);
        }
        
        return false;
    }
	
	/**
	 * 判断是否是 ajax 请求
	 * @param request
	 * @return
	 */
	private static boolean isAjax(ServletRequest request){  
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");  
        if("XMLHttpRequest".equalsIgnoreCase(header)){  
            return Boolean.TRUE;  
        }  
        
        return Boolean.FALSE;  
    }  
}
