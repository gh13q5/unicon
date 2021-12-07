package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스 USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/**
	 * 회사 관리 테이블에 새로운 회사 생성.
	 */
	public static int create(Company company) throws SQLException {
		String sql = "INSERT INTO COMPANY VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { company.getCompanyId(), company.getId(), company.getPassword(),
				company.getEmail(), company.getName(), company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	/**
	 * 기존의 회사 정보를 수정.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE COMPANY " + "SET id=?, password=?, email=?, name=?, phone_number=? "
				+ "WHERE company_id=?";
		Object[] param = new Object[] { company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	/**
	 * 회사 ID에 해당하는 회사 삭제
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM COMPANY WHERE company_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil에 delete문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	/**
	 * 주어진 회사 ID에 해당하는 회사 정보를 데이터베이스에서 찾아 Company 도메인 클래스에 저장하여 반환.
	 */
	public static Company findCompany(String companyId) throws SQLException {
		String sql = "SELECT id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE company_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				Company company = new Company( // Company 객체를 생성하여 회사 정보를 저장
						companyId, rs.getString("id"), rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public static boolean existingCompany(String companyId) throws SQLException {
		String sql = "SELECT count(*) FROM COMPANYINFO WHERE companyid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {companyId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}

}
