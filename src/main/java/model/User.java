package model;

import java.util.Date;

/**
 * �궗�슜�옄 愿�由щ�� �쐞�빐 �븘�슂�븳 �룄硫붿씤 �겢�옒�뒪. USERINFO �뀒�씠釉붽낵 ���쓳�맖
 */
public class User {
	private int userId;
	private String id;
	private String password;
	private String email;
	private String name;
	private String phone_number;
	private Date birthDay;
	private int gender;
	//private int interests;
	private int point;

	public User(String id, String password, String email, String name, String phone_number, Date birthDay, int gender,
			int point) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
		this.birthDay = birthDay;
		this.gender = gender;
		//this.interests = interests;
		this.point = point;
	}		// 湲곕낯 �깮�꽦�옄
	
	public User(int userId, String id, String password, String email, String name, String phone_number, Date birthDay, int gender,
			int point) {
		super();
		this.userId = userId;
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
		this.birthDay = birthDay;
		this.gender = gender;
		//this.interests = interests;
		this.point = point; 
	}//�깮�꽦�옄
	
	public User(String id, String password, String email, String name, String phone_number, int gender) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
	
		this.gender = gender;
		// TODO Auto-generated constructor stub
	}

	//getters&setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

//	public int getInterests() {
//		return interests;
//	}
//
//	public void setInterests(int interests) {
//		this.interests = interests;
//	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isSameUser(int userid) {
        return this.userId == userid;
    }
	
	/* 鍮꾨�踰덊샇 寃��궗 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ",id=" + id + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", phone_number=" + phone_number + ", birthDay=" + birthDay + ", gender=" + gender + ", point=" + point + "]";
	}	
}
