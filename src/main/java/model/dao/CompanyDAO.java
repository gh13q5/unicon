package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Company;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ���� 
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;
	
	public CompanyDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {company.getCompanyId(), company.getPassword(), 
						company.getName(), company.getEmail(), company.getPhone(), 
						(company.getCommId()!=0) ? company.getCommId() : null };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	/**
	 * ������ ����� ������ ����.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE USERINFO "
					+ "SET password=?, name=?, email=?, phone=?, commId=? "
					+ "WHERE companyid=?";
		Object[] param = new Object[] {company.getPassword(), company.getName(), 
					company.getEmail(), company.getPhone(), 
					(company.getCommId()!=0) ? company.getCommId() : null, 
					company.getcompanyId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * ����� ID�� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM USERINFO WHERE companyid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {companyId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� Company ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public Company findCompany(String companyId) throws SQLException {
        String sql = "SELECT password, name, email, phone, commId, cName "
        			+ "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        			+ "WHERE companyid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {companyId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				Company company = new Company(		// Company ��ü�� �����Ͽ� �л� ������ ����
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Company> findCompanyList() throws SQLException {
        String sql = "SELECT companyId, name, email, NVL(commId,0) AS commId, cName " 
        		   + "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        		   + "ORDER BY companyId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Company> companyList = new ArrayList<Company>();	// Company���� ����Ʈ ����
			while (rs.next()) {
				Company company = new Company(			// Company ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("companyId"),
					null,
					rs.getString("name"),
					rs.getString("email"),
					null,
					rs.getInt("commId"),
					rs.getString("cName"));
				companyList.add(company);				// List�� Company ��ü ����
			}		
			return companyList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<Company> findCompanyList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT companyId, name, email, NVL(commId, 0), cName " 
					+ "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
					+ "ORDER BY companyId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Company> companyList = new ArrayList<Company>();	// Company���� ����Ʈ ����
				do {
					Company company = new Company(			// Company ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("companyId"),
						null,
						rs.getString("name"),
						rs.getString("email"),
						null,
						rs.getInt("commId"),
						rs.getString("cName"));
					companyList.add(company);							// ����Ʈ�� Company ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return companyList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * Ư�� Ŀ�´�Ƽ�� ���� ����ڵ��� �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Company> findCompanysInCommunity(int communityId) throws SQLException {
        String sql = "SELECT companyId, name, email, phone FROM CompanyInfo "
     				+ "WHERE commId = ?";                         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			List<Company> memList = new ArrayList<Company>();	// member���� ����Ʈ ����
			while (rs.next()) {
				Company member = new Company(			// Company ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("companyId"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"));
				memList.add(member);			// List�� Community ��ü ����
			}		
			return memList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * Ư�� Ŀ�´�Ƽ�� ���� ����ڵ��� ���� count�Ͽ� ��ȯ
	 */
	public int getNumberOfCompanysInCommunity(int communityId) {
		String sql = "SELECT COUNT(companyId) FROM CompanyInfo "
     				+ "WHERE commId = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingCompany(String companyId) throws SQLException {
		String sql = "SELECT count(*) FROM USERINFO WHERE companyid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {companyId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}

}
