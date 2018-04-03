package com.yunnex.boot.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunnex.boot.framework.entity.CampaignBase;
import com.yunnex.boot.framework.service.MyBootServiceFacade;


@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private MyBootServiceFacade myBootServiceFacade;

	@RequestMapping("/age")
	@ResponseBody
	public int home() {
		int i = myBootServiceFacade.testMyBootServiceFacade();
		if( i == 0){
			i = 29;
		}
		return i;
	}

	@RequestMapping("/info")
	@ResponseBody
	public CampaignBase CampaignBase() {
		CampaignBase base = myBootServiceFacade.testCampaignBase();
		base.setChannel(2);
		return base;
	}
}
