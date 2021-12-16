package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

/**
 * �궗�슜�옄 愿�由щ�� �쐞�빐 �뜲�씠�꽣踰좎씠�뒪 �옉�뾽�쓣 �쟾�떞�븯�뒗 DAO �겢�옒�뒪 USERINFO �뀒�씠釉붿뿉 �궗�슜�옄 �젙蹂대�� 異붽�, �닔�젙, �궘�젣, 寃��깋 �닔�뻾
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 媛앹껜 �깮�꽦
	}

	/**
	 * �쉶�궗 愿�由� �뀒�씠釉붿뿉 �깉濡쒖슫 �쉶�궗 �깮�꽦.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO COMPANY VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { company.getCompanyId(), company.getId(), company.getPassword(),
				company.getEmail(), company.getName(), company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			int result = jdbcUtil.executeUpdate(); // insert 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 諛섑솚
		}
		return 0;
	}

	/**
	 * 湲곗〈�쓽 �쉶�궗 �젙蹂대�� �닔�젙.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE COMPANY " + "SET id=?, password=?, email=?, name=?, phone_number=? "
				+ "WHERE company_id=?";
		Object[] param = new Object[] {  company.getCompanyId(), company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			int result = jdbcUtil.executeUpdate(); // update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 諛섑솚
		}
		return 0;
	}

	/**
	 * �쉶�궗 ID�뿉 �빐�떦�븯�뒗 �쉶�궗 �궘�젣
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM COMPANY WHERE company_Id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			int result = jdbcUtil.executeUpdate(); // delete 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 諛섑솚
		}
		return 0;
	}

	/**
	 * 二쇱뼱吏� �쉶�궗 ID�뿉 �빐�떦�븯�뒗 �쉶�궗 �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 Company �룄硫붿씤 �겢�옒�뒪�뿉 ���옣�븯�뿬 諛섑솚.
	 */
	public Company findCompany(String id) throws SQLException {
		String sql = "SELECT company_Id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �떎�뻾
			if (rs.next()) { // �븰�깮 �젙蹂� 諛쒓껄
				Company company = new Company( // Company 媛앹껜瑜� �깮�꽦�븯�뿬 �쉶�궗 �젙蹂대�� ���옣
						rs.getInt("company_Id"), rs.getString("id"), rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 諛섑솚
		}
		return null;
	}
	/**
	 * 二쇱뼱吏� �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄媛� 議댁옱�븯�뒗吏� 寃��궗 
	 */
	public boolean existingCompany(int i) throws SQLException {
		String sql = "SELECT count(*) FROM COMPANY WHERE company_Id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {i});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return false;
	}
}
