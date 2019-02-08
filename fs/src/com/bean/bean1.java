package com.bean;

public class bean1 {
	private int id;
	private int age;
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public bean1(int id, int age, String userName) {
		super();
		this.id = id;
		this.age = age;
		this.userName = userName;
	}
	public bean1() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
