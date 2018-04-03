package com.yunnex.boot.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yunnex.boot.framework.entity.CampaignBase;

public interface CampaignBaseMapper  { 
	
    CampaignBase get(@Param("id") String id);
    
    
    List<CampaignBase> list(Map<String,Object> map);
}
