package com.demo.web.models;

import org.hibernate.validator.constraints.NotEmpty;

public class UserInfoModel {
	
	private Integer id;
	private String acc;
	@NotEmpty(message="{name.not.empty}")
	private String userName;
	@NotEmpty(message="{email.not.empty}")
	private String email;
	@NotEmpty(message="{password.not.empty}")
	private String password;
	@NotEmpty(message="{password.not.enpty}")
	private String confirmPassword;
	
	public UserInfoModel(Integer id, String acc, String userName, String email, String password) {
		super();
		this.id = id;
		this.acc = acc;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.confirmPassword = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
