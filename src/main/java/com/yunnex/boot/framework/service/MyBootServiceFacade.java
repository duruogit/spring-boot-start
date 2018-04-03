package com.yunnex.boot.framework.service;

import com.yunnex.boot.framework.entity.CampaignBase;
import com.yunnex.boot.framework.page.PageResult;

/**
 * 活动接口类
 * <p>Description: <／p>
 * @author yuwenjun
 * @date 2017年12月1日 下午4:11:47
 * <p>Copyright (c) 2017, www.yunnex.com All Rights Reserved.<／p>
 */
public interface MyBootServiceFacade {
	
	public int testMyBootServiceFacade();
	
	public CampaignBase testCampaignBase();
	
	public CampaignBase getCampaignBaseById(String id);
	
	PageResult<CampaignBase> selectALL();
	
}
