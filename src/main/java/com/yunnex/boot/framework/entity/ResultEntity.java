package com.yunnex.boot.framework.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 前后端交互对象API,用于后端返回给前端进行页面渲染
 * <p>Description: <／p>
 * @author yuwenjun
 * @date 2017年12月7日 上午11:46:49
 */
public class ResultEntity<T> implements Serializable {
	
	private static final long serialVersionUID = -6270710110828627850L;
	
	/**
	 * 成功
	 */
	public static final Integer SUCCESS = 0;
	
	/**
	 * 失败
	 */
	public static final Integer FAIL = 1;
	
	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private T data;
	
    public static <T> ResultEntity<T> build() {
        return new ResultEntity<>();
    }
	
	public static <T> ResultEntity<T> newInstance(T obj) {
		ResultEntity<T> entity = new ResultEntity<>();
		entity.setData(obj);
        return entity;
    }
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
