package com.aurospaces.neighbourhood.bean;

public class NewClassBean {

	protected String name;
	protected String email;
	protected String phno;
	protected String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "NewClassBean [name=" + name + ", email=" + email + ", phno=" + phno + ", state=" + state + "]";
	}
	 
}
