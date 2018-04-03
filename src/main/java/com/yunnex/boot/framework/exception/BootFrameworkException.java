package com.yunnex.boot.framework.exception;

public class BootFrameworkException extends RuntimeException {
	
	private static final long serialVersionUID = 4834540291506999376L;
	
	private Integer code;
	private String message;
	
	public BootFrameworkException(Integer code, String message){
		this.code = code;
		this.message = message;
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
	
	
}
