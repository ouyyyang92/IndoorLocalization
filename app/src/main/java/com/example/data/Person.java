package com.example.data;

import java.util.Date;

/**
 * ����
 *
 * @author 76557
 */
public class Person {
	private int id;
	private String phone;//传
	private String password;
	private String name;//传
	private String address;//传
	private String email;//传
	private int gender;//传
	private String headImg;
	private Date creatDate;
	private Date bornDate;//传
	private int age;//传
	private String hobby;//传
	private String friends;
	private int headImg2;//传

	public Person() {
		super();
	}

	public Person(String name, int headImg2) {
		this.name = name;
		this.headImg2 = headImg2;
	}

	public Person(String phone, String password, String name, Date creatDate) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.creatDate = creatDate;
	}

	public Person(String phone, String name, String address, String email, int gender, Date bornDate, int age, String hobby, int headImg2) {
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.bornDate = bornDate;
		this.age = age;
		this.hobby = hobby;
		this.headImg2 = headImg2;
	}

	public Person(String phone, String password, String name, String address, String email, int gender, String headImg,
				  Date creatDate, Date bornDate, int age, String hobby, String friends) {
		super();
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.headImg = headImg;
		this.creatDate = creatDate;
		this.bornDate = bornDate;
		this.age = age;
		this.hobby = hobby;
		this.friends = friends;
	}

	public Person(int id, String phone, String password, String name, String address, String email, int gender,
				  String headImg, Date creatDate, Date bornDate, int age, String hobby, String friends, int headImg2) {
		super();
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.headImg = headImg;
		this.creatDate = creatDate;
		this.bornDate = bornDate;
		this.age = age;
		this.hobby = hobby;
		this.friends = friends;
		this.headImg2 = headImg2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public int getHeadImg2() {
		return headImg2;
	}

	public void setHeadImg2(int headImg2) {
		this.headImg2 = headImg2;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", phone=" + phone + ", password=" + password + ", name=" + name + ", address="
				+ address + ", email=" + email + ", gender=" + gender + ", headImg=" + headImg + ", creatDate="
				+ creatDate + ", bornDate=" + bornDate + ", age=" + age + ", hobby=" + hobby + ", friends=" + friends
				+ ", headImg2=" + headImg2 + "]";
	}

}
