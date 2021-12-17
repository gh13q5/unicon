package model;

import java.util.Date;

/**
 * �궗�슜�옄 愿�由щ�� �쐞�빐 �븘�슂�븳 �룄硫붿씤 �겢�옒�뒪. COMPANYINFO �뀒�씠釉붽낵 ���쓳�맖
 */
public class Company {
	private int companyId; //踰덉㎏
	private String id;
	private String password;
	private String email;
	private String name;
	private String phone_number;
	
	public Company(String id, String password, String email, String name, String phone_number) {
		
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
	}//�깮�꽦�옄
	
	public Company(int companyId, String id, String password, String email, String name, String phone_number) {
		this.companyId = companyId;
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
	}//�깮�꽦�옄

	//getters&setters
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public boolean isSameCompany(int companyid) {
        return this.companyId == companyid;
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
		return "Company [companyId=" + companyId + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", phone_number=" + phone_number + "]";
	}
}

