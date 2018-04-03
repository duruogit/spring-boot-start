package com.yunnex.boot.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yunnex.boot.framework.entity.CampaignBase;
import com.yunnex.boot.framework.service.MyBootServiceFacade;
import com.yunnex.boot.framework.service.facade.BootFeignFacade;

@Controller
@EnableAutoConfiguration
//添加的注解,springboot不需要传统的springmvc配置文件,springboot默认按照包顺序注入，所以在创建controller时service还没有注入
@ComponentScan(basePackages={"com.yunnex.boot.framework.service"})
public class MySpringBootController {

	@Autowired
	private MyBootServiceFacade myBootServiceFacade;
	
	@Autowired
	private BootFeignFacade bootFeginFacade;

	@RequestMapping("/num")
	@ResponseBody
	public int home() {
		int i = myBootServiceFacade.testMyBootServiceFacade();
		return i;
	}

	@RequestMapping("/get")
	@ResponseBody
	public CampaignBase CampaignBase() {
		return myBootServiceFacade.testCampaignBase();
	}
	
	@RequestMapping("/feign")
	@ResponseBody
	public String Feign() {
		return bootFeginFacade.getMessage();
	}
	
}
