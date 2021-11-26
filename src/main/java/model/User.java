package model;

import java.util.Date;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
public class User {
	private String userId;
	private String password;
	private String email;
	private String name;
	private String phone_number;
	private Date commId;
	private int gender;
	private int interests;
	private int point;

	public User() { }		// 기본 생성자
	
	public User(String userId, String password, String email, String name, String phone_number, Date commId, int gender,
			int interests, int point) {
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
		this.commId = commId;
		this.gender = gender;
		this.interests = interests;
		this.point = point;
	}//생성자

	//getters&setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getCommId() {
		return commId;
	}

	public void setCommId(Date commId) {
		this.commId = commId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getInterests() {
		return interests;
	}

	public void setInterests(int interests) {
		this.interests = interests;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", phone_number=" + phone_number + ", commId=" + commId + ", gender=" + gender + ", interests="
				+ interests + ", point=" + point + "]";
	}	
}
