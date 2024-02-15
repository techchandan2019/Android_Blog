package com.neosoft.model;

public class BlogMessageRequest {
	
	private Integer userId;
	private String message;
	public BlogMessageRequest() {
		// TODO Auto-generated constructor stub
	}
	public BlogMessageRequest(Integer userId, String message) {
		super();
		this.userId = userId;
		this.message = message;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "BlogMessageRequest [userId=" + userId + ", message=" + message + "]";
	}

	
	
	

}
