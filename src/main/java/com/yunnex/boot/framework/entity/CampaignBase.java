package com.yunnex.boot.framework.entity;

import java.util.Date;

import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "campaign_base")
public class CampaignBase {

    /**
     * 主键列
     * 自动增长ID 
     */
	
    private Long id;

    /**
     * 
     * 对外业务ID 
     */
    private String campBaseBizId;

    /**
     * 
     * 商户ID 
     */
    private Long shopId;

    /**
     * 
     * 活动状态 1:创建中、2:未开始、3:进行中、4:下架中、5:下架、6:完成 
     */
    private Integer status;

    /**
     * 
     * 活动名称 
     */
    private String name;

    /**
     * 
     * 业务活动类型 1消费送 3直接送 
     */
    private Integer type;

    /**
     * 
     * 渠道(1-支付宝,2-云移信息) 
     */
    private Integer channel;

    /**
     * 
     * 活动开始时间 
     */
    @DateTimeFormat(pattern="DateTimeFormat.ISO.DATE_TIME")
    private Date startTime;

    /**
     * 
     * 活动结束时间 
     */
    @DateTimeFormat(pattern="DateTimeFormat.ISO.DATE_TIME")
    private Date endTime;

    /**
     * 
     * 是否已删除 
     */
    private Integer isDeleted;

    /**
     * 
     * 删除时间 
     */
    @DateTimeFormat(pattern="DateTimeFormat.ISO.DATE_TIME")
    private Date deleteTime;

    /**
     * 
     * 创建时间 
     */
    @DateTimeFormat(pattern="DateTimeFormat.ISO.DATE_TIME")
    private Date createTime;

    /**
     * 
     * 更新时间 
     */
    @DateTimeFormat(pattern="DateTimeFormat.ISO.DATE_TIME")
    private Date updateTime;


	
	public CampaignBase(){
	    // default constructor
	}
    
    public CampaignBase(Long id){
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setCampBaseBizId(String campBaseBizId) {
        this.campBaseBizId = campBaseBizId;
    }
    
    public String getCampBaseBizId() {
        return this.campBaseBizId;
    }
    
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    
    public Long getShopId() {
        return this.shopId;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
    
    public Date getDeleteTime() {
        return this.deleteTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;

    }
}
