package com.yunnex.boot.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunnex.boot.framework.entity.ResultEntity;
import com.yunnex.boot.framework.exception.BootFrameworkException;
import com.yunnex.boot.framework.util.ResultEntityBuilder;

/**
 * 主页信息
 * @author yuwenjun
 * @date 2017年12月5日 下午5:59:19
 */
@Controller
@RequestMapping("/boot")
public class AdminController {
	
	@RequestMapping("home")
	public String home(){
		return "home";
	}
	
	@GetMapping("login")
	public String redirectToLogin(){
		return "login";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/ajaxLogin",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity doLogin(HttpServletRequest req) {
        ResultEntity result = ResultEntityBuilder.build(null, "");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
        	result.setCode(ResultEntity.FAIL);
            result.setMessage("账号或密码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            List<String> roleIds = new ArrayList<String>();
            roleIds.add("1");
            subject.hasAllRoles(roleIds);
            result.setCode(ResultEntity.SUCCESS);
        } catch (AuthenticationException e) {
            result.setCode(ResultEntity.FAIL);
            result.setMessage("用户名或密码错误");
        }
        
        return result;
    }
	
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public ResultEntity<?> logout(){
		ResultEntity<?> result = ResultEntityBuilder.build(null, "注销成功");
		try {
			//退出
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			throw new BootFrameworkException(ResultEntity.FAIL, "退出系统失败!");
		}
		
		return result;
	}
	
	@RequestMapping(value="test",method =RequestMethod.POST, produces = {"text/xml;charset=UTF-8"} )
	@ResponseBody
	public ResultEntity<?> test(@PathVariable("shopIdOrSerial") String shopIdOrSerial, @RequestParam(required = false) String signature,
            @RequestParam(required = false) String timestamp, @RequestParam(required = false) String nonce,
            @RequestBody String xml){
		ResultEntity<?> result = ResultEntityBuilder.build(null, "注销成功");
		try {
			//退出
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			throw new BootFrameworkException(ResultEntity.FAIL, "退出系统失败!");
		}
		
		return result;
	}
	
	
	@RequestMapping(value="amazon/signin",method =RequestMethod.GET)
	@ResponseBody
	public ResultEntity<?> signAmazon(){
		ResultEntity<?> result = ResultEntityBuilder.build(null, "发送登录请求成功");
		String url = "https://www.amazon.com/ap/signin";
		try {
			String body = "";  
			Map<String,String> map = new HashMap<String,String>();
			map.put("email", "471949989@qq.com");
			map.put("password", "123ewq");
			map.put("appActionToken", "hl5rxvSP7ybGauPeFVt5j2FlHigq0j3D");
			map.put("appAction", "SIGNIN");
			map.put("openid.identity", "ape:aHR0cDovL3NwZWNzLm9wZW5pZC5uZXQvYXV0aC8yLjAvaWRlbnRpZmllcl9zZWxlY3Q");
	        map.put("ignoreAuthState", "ape:MQ==");
	        map.put("pageId", "ape:dXNmbGV4");
	        map.put("openid.return_to", "ape:aHR0cHM6Ly93d3cuYW1hem9uLmNvbS8/cmVmXz1uYXZfY3VzdHJlY19zaWduaW4=");
	        map.put("prevRID", "ape:QTBEMllXSldNU1lXMDZKTVYzUEM=");
	        map.put("openid.assoc_handle", "ape:dXNmbGV4");
	        map.put("openid.mode", "ape:Y2hlY2tpZF9zZXR1cA==");
	        map.put("openid.ns.pape", "ape:aHR0cDovL3NwZWNzLm9wZW5pZC5uZXQvZXh0ZW5zaW9ucy9wYXBlLzEuMA==");
	        map.put("openid.ns", "ape:aHR0cDovL3NwZWNzLm9wZW5pZC5uZXQvYXV0aC8yLjA=");
	        
	        map.put("openid.claimed_id", "ape:aHR0cDovL3NwZWNzLm9wZW5pZC5uZXQvYXV0aC8yLjAvaWRlbnRpZmllcl9zZWxlY3Q=");
	        //创建httpclient对象  
	        CloseableHttpClient client = HttpClients.createDefault();  
	        //创建post方式请求对象  
	        HttpPost httpPost = new HttpPost(url);  
	          
	        //装填参数  
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        if(map!=null){  
	            for (Entry<String, String> entry : map.entrySet()) {  
	                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
	            }  
	        }  
	        //设置参数到请求对象中  
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));  
	  
	        System.out.println("请求地址："+url);  
	        System.out.println("请求参数："+nvps.toString());  
	          
	        //设置header信息  
	        //指定报文头【Content-type】、【User-Agent】  
	        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
	        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");  
	          
	        //执行请求操作，并拿到结果（同步阻塞）  
	        CloseableHttpResponse response = client.execute(httpPost);  
	        //获取结果实体  
	        HttpEntity entity = response.getEntity();  
	        if (entity != null) {  
	            //按指定编码转换结果实体为String类型  
	            body = EntityUtils.toString(entity, "UTF-8");  
	            System.out.println(body);
	        }  
	        EntityUtils.consume(entity);  
	        //释放链接  
	        response.close();  
	        result.setMessage(body);
		} catch (Exception e) {
			throw new BootFrameworkException(ResultEntity.FAIL, "登录Amazon系统失败!");
		}
		
		return result;
	}
}
