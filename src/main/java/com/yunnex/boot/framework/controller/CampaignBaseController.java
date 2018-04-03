package com.yunnex.boot.framework.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yunnex.boot.framework.entity.CampaignBase;
import com.yunnex.boot.framework.exception.BootFrameworkException;
import com.yunnex.boot.framework.page.PageResult;
import com.yunnex.boot.framework.service.EmailServiceFacade;
import com.yunnex.boot.framework.service.MyBootServiceFacade;
import com.yunnex.boot.framework.util.ResultEntityBuilder;


@RestController
@RequestMapping("campaign")
public class CampaignBaseController {
	
	private final Logger log = LoggerFactory.getLogger(CampaignBaseController.class);
	
	@Autowired
	private MyBootServiceFacade myBootServiceFacade;
	
	@Autowired
	private EmailServiceFacade emailServiceFacade;

	@RequestMapping("/num")
	@ResponseBody
	public int home() {
		int i = myBootServiceFacade.testMyBootServiceFacade();
		return i;
	}

	@RequestMapping("/get")
	@ResponseBody
	public CampaignBase campaignBase() {
		log.info("CampaignBaseController campaignBase info");
		CampaignBase base = myBootServiceFacade.getCampaignBaseById("2");
		return base;
	}
	
    @RequestMapping("/getall")
    @ResponseBody 
    public PageResult<CampaignBase> getall() {
        return myBootServiceFacade.selectALL();
    }
    
    
    @SuppressWarnings("unused")
	@RequestMapping("/sendMail")
    @ResponseBody 
    public String sendEmail(){
    	Map<String,String> map = new HashMap<String, String>();
    	try {
    		// rzg0168z2m199tw@marketplace.amazon.com   2308425278@qq.com  fengyi1988@outlook.com  yuwenjun@yunnex.com  stuartgreenson@outlook.com
    		emailServiceFacade.sendSimpleMail("rzg0168z2m199tw@marketplace.amazon.com", "测试邮件主题", "这是测试邮件内容信息!");
		} catch (Exception e) {
			throw new BootFrameworkException(222, e.getMessage()); 
		}
    	
    	return ResultEntityBuilder.build(null, "发送成功！").toString();
    }
    
}
