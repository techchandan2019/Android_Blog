package com.neosoft.model;

public class UserInfoRequest {
	private String usn;
	private String pwd;
	private String roles;
	public UserInfoRequest(String usn, String pwd, String roles) {
		super();
		this.usn = usn;
		this.pwd = pwd;
		this.roles = roles;
	}
	public String getUsn() {
		return usn;
	}
	public void setUsn(String usn) {
		this.usn = usn;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserInfoRequest [usn=" + usn + ", pwd=" + pwd + ", roles=" + roles + "]";
	}
	
	
}
