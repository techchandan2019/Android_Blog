package com.neosoft.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_info04")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String usn;
	private String pwd;
	private String roles;
	
	@OneToMany(targetEntity = BlogMessage.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "info")
//	@JoinColumn(name = "blog_id",referencedColumnName = "id")
	@JsonIgnore
	private List<BlogMessage> blog;
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo(Integer id, String usn, String pwd, String roles, List<BlogMessage> blog) {
		super();
		this.id = id;
		this.usn = usn;
		this.pwd = pwd;
		this.roles = roles;
		this.blog = blog;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<BlogMessage> getBlog() {
		return blog;
	}

	public void setBlog(List<BlogMessage> blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", usn=" + usn + ", pwd=" + pwd + ", roles=" + roles + ", blog=" + blog + "]";
	}

	

}
