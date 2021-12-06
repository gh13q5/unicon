package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ���� USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ����
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	/**
	 * ȸ�� ���� ���̺� ���ο� ȸ�� ����.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO COMPANY VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { company.getCompanyId(), company.getId(), company.getPassword(),
				company.getEmail(), company.getName(), company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * ������ ȸ�� ������ ����.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE COMPANY " + "SET id=?, password=?, email=?, name=?, phone_number=? "
				+ "WHERE company_id=?";
		Object[] param = new Object[] { company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * ȸ�� ID�� �ش��ϴ� ȸ�� ����
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM COMPANY WHERE company_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil�� delete���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * �־��� ȸ�� ID�� �ش��ϴ� ȸ�� ������ �����ͺ��̽����� ã�� Company ������ Ŭ������ �����Ͽ� ��ȯ.
	 */
	public Company findCompany(String companyId) throws SQLException {
		String sql = "SELECT id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE company_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { // �л� ���� �߰�
				Company company = new Company( // Company ��ü�� �����Ͽ� ȸ�� ������ ����
						companyId, rs.getString("id"), rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}
}
