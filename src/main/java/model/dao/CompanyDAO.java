package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Company;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;
	
	public CompanyDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {company.getCompanyId(), company.getPassword(), 
						company.getName(), company.getEmail(), company.getPhone(), 
						(company.getCommId()!=0) ? company.getCommId() : null };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	/**
	 * 기존의 사용자 정보를 수정.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE USERINFO "
					+ "SET password=?, name=?, email=?, phone=?, commId=? "
					+ "WHERE companyid=?";
		Object[] param = new Object[] {company.getPassword(), company.getName(), 
					company.getEmail(), company.getPhone(), 
					(company.getCommId()!=0) ? company.getCommId() : null, 
					company.getcompanyId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM USERINFO WHERE companyid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {companyId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 Company 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Company findCompany(String companyId) throws SQLException {
        String sql = "SELECT password, name, email, phone, commId, cName "
        			+ "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        			+ "WHERE companyid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {companyId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Company company = new Company(		// Company 객체를 생성하여 학생 정보를 저장
					companyId,
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getInt("commId"),					
					rs.getString("cName"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Company> findCompanyList() throws SQLException {
        String sql = "SELECT companyId, name, email, NVL(commId,0) AS commId, cName " 
        		   + "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        		   + "ORDER BY companyId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Company> companyList = new ArrayList<Company>();	// Company들의 리스트 생성
			while (rs.next()) {
				Company company = new Company(			// Company 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("companyId"),
					null,
					rs.getString("name"),
					rs.getString("email"),
					null,
					rs.getInt("commId"),
					rs.getString("cName"));
				companyList.add(company);				// List에 Company 객체 저장
			}		
			return companyList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<Company> findCompanyList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT companyId, name, email, NVL(commId, 0), cName " 
					+ "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
					+ "ORDER BY companyId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Company> companyList = new ArrayList<Company>();	// Company들의 리스트 생성
				do {
					Company company = new Company(			// Company 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("companyId"),
						null,
						rs.getString("name"),
						rs.getString("email"),
						null,
						rs.getInt("commId"),
						rs.getString("cName"));
					companyList.add(company);							// 리스트에 Company 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return companyList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 특정 커뮤니티에 속한 사용자들을 검색하여 List에 저장 및 반환
	 */
	public List<Company> findCompanysInCommunity(int communityId) throws SQLException {
        String sql = "SELECT companyId, name, email, phone FROM CompanyInfo "
     				+ "WHERE commId = ?";                         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<Company> memList = new ArrayList<Company>();	// member들의 리스트 생성
			while (rs.next()) {
				Company member = new Company(			// Company 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("companyId"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"));
				memList.add(member);			// List에 Community 객체 저장
			}		
			return memList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 특정 커뮤니티에 속한 사용자들의 수를 count하여 반환
	 */
	public int getNumberOfCompanysInCommunity(int communityId) {
		String sql = "SELECT COUNT(companyId) FROM CompanyInfo "
     				+ "WHERE commId = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return 0;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingCompany(String companyId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE companyid=?";      
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
