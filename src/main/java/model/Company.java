package model;

import java.util.Date;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. COMPANYINFO 테이블과 대응됨
 */
public class Company {
	private String companyId;
	private String password;
	private String email;
	private String name;
	private String phone_number;
	public Company() { }		// 기본 생성자
	
	public Company(String companyId, String password, String email, String name, String phone_number) {
		this.companyId = companyId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
	}//생성자

	//getters&setters
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public boolean isSameCompany(String companyid) {
        return this.companyId.equals(companyid);
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
		return "Company [companyId=" + companyId + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", phone_number=" + phone_number + "]";
	}	
}

