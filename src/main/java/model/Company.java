package model;

import java.util.Date;

/**
 * ����� ������ ���� �ʿ��� ������ Ŭ����. COMPANYINFO ���̺�� ������
 */
public class Company {
	private String companyId;
	private String password;
	private String email;
	private String name;
	private String phone_number;
	public Company() { }		// �⺻ ������
	
	public Company(String companyId, String password, String email, String name, String phone_number) {
		this.companyId = companyId;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone_number = phone_number;
	}//������

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
	
	/* ��й�ȣ �˻� */
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

