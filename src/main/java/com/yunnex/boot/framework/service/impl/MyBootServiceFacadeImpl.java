package com.yunnex.boot.framework.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunnex.boot.framework.dao.CampaignBaseMapper;
import com.yunnex.boot.framework.entity.CampaignBase;
import com.yunnex.boot.framework.page.PageInfo;
import com.yunnex.boot.framework.page.PageResult;
import com.yunnex.boot.framework.service.MyBootServiceFacade;

/**
 * 接口服务层实现
 * <p>Description: <／p>
 * @author yuwenjun
 * @date 2017年12月1日 下午4:12:21
 * <p>Copyright (c) 2017, www.yunnex.com All Rights Reserved.<／p>
 */
@Service
public class MyBootServiceFacadeImpl implements MyBootServiceFacade {
	
	@Autowired
	private CampaignBaseMapper campaignBaseMapper;

	@Override
	public int testMyBootServiceFacade() {
		return 0;
	}

	@Override
	public CampaignBase testCampaignBase() {
		CampaignBase c = new CampaignBase();
		c.setCampBaseBizId("1234567");
		c.setName("测试活动");
		return c;
	}

	@Override
	public CampaignBase getCampaignBaseById(String id) {
		return campaignBaseMapper.get(id);
	}

	@Override
	public PageResult<CampaignBase> selectALL() {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPagination(true);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", pageInfo);
		map.put("campBaseBizId", "451a54e51ba34359b2114c3787192821");
		return PageResult.wrap(pageInfo, campaignBaseMapper.list(map));
	}

}
