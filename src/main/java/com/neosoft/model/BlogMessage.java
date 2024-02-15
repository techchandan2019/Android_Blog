package com.neosoft.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Blog_table01")
public class BlogMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer bid;
	private String blog;
	
	@ManyToOne(targetEntity = UserInfo.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	@JsonIgnore
	private UserInfo info;
	
	public BlogMessage() {
		// TODO Auto-generated constructor stub
	}

	public BlogMessage(Integer bid, String blog, UserInfo info) {
		super();
		this.bid = bid;
		this.blog = blog;
		this.info = info;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public UserInfo getInfo() {
		return info;
	}

	public void setInfo(UserInfo info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "BlogMessage [bid=" + bid + ", blog=" + blog + ", info=" + info + "]";
	}

	

}
