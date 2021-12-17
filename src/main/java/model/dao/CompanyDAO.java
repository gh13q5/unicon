package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

/**
 * �뜝�럡�뀬�뜝�럩�뮔�뜝�럩�겱 占쎄슈�뜝�뜾逾놂옙�맮�삕�뜝占� �뜝�럩留꾢뜝�럥�돵 �뜝�럥�몥�뜝�럩逾졾뜝�럡�댉�뵓怨쀬쪠占쎈턄�뜝�럥裕� �뜝�럩�굚�뜝�럥�뵜�뜝�럩諭� �뜝�럩�쓧�뜝�럥堉뽩뜝�럥由��뜝�럥裕� DAO �뜝�럡源삣뜝�럩�굥�뜝�럥裕� USERINFO �뜝�럥占쎈���삕占쎈턄占쎈눀�겫�슜�뱺 �뜝�럡�뀬�뜝�럩�뮔�뜝�럩�겱 �뜝�럩�젧�솻洹ｏ옙�뜝�룞�삕 占쎈퉲�겫�룞�삕, �뜝�럥�빢�뜝�럩�젧, �뜝�럡�뀭�뜝�럩�젷, �뇦猿볦삕�뜝�럡�돰 �뜝�럥�빢�뜝�럥六�
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil �뤆�룇鍮섊뙼占� �뜝�럡臾멨뜝�럡�뎽
	}

	/**
	 * �뜝�럩�뤂�뜝�럡�뀬 占쎄슈�뜝�뜾逾녑뜝占� �뜝�럥占쎈���삕占쎈턄占쎈눀�겫�슜�뱺 �뜝�럡�돮�슖�돦裕뉛옙�뮧 �뜝�럩�뤂�뜝�럡�뀬 �뜝�럡臾멨뜝�럡�뎽.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO COMPANY "
				+"(id, password, email, name, phone_number ) "
				+"VALUES (?, ?, ?, ?, ?)";
		Object[] param = new Object[] { company.getId(), company.getPassword(),
				company.getEmail(), company.getName(), company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �뜝�럥�뱺 insert占쎈닱筌롫챶沅� 嶺뚮씞�걝�뚳옙 �솻洹⑥삕�뜝�럥�빢 �뜝�럡�맟�뜝�럩�젧

		try {
			int result = jdbcUtil.executeUpdate(); // insert 占쎈닱�뜝占� �뜝�럥堉꾢뜝�럥六�
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뛾�룇瑗뱄옙�꼶
		}
		return 0;
	}

	/**
	 * �뼨轅명�ｏ옙�뜉�삕占쎈꺄 �뜝�럩�뤂�뜝�럡�뀬 �뜝�럩�젧�솻洹ｏ옙�뜝�룞�삕 �뜝�럥�빢�뜝�럩�젧.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE COMPANY " + "SET id=?, password=?, email=?, name=?, phone_number=? "
				+ "WHERE company_id=?";
		Object[] param = new Object[] {  company.getCompanyId(), company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�뜝�럥�뱺 update占쎈닱筌롫챶沅� 嶺뚮씞�걝�뚳옙 �솻洹⑥삕�뜝�럥�빢 �뜝�럡�맟�뜝�럩�젧

		try {
			int result = jdbcUtil.executeUpdate(); // update 占쎈닱�뜝占� �뜝�럥堉꾢뜝�럥六�
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뛾�룇瑗뱄옙�꼶
		}
		return 0;
	}

	/**
	 * �뜝�럩�뤂�뜝�럡�뀬 ID�뜝�럥�뱺 �뜝�럥�돵�뜝�럥堉ｅ뜝�럥由��뜝�럥裕� �뜝�럩�뤂�뜝�럡�뀬 �뜝�럡�뀭�뜝�럩�젷
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM COMPANY WHERE company_Id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil�뜝�럥�뱺 delete占쎈닱筌롫챶沅� 嶺뚮씞�걝�뚳옙 �솻洹⑥삕�뜝�럥�빢 �뜝�럡�맟�뜝�럩�젧

		try {
			int result = jdbcUtil.executeUpdate(); // delete 占쎈닱�뜝占� �뜝�럥堉꾢뜝�럥六�
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뛾�룇瑗뱄옙�꼶
		}
		return 0;
	}

	/**
	 * �썒�슣�닔�젆源띿�ゅ뜝占� �뜝�럩�뤂�뜝�럡�뀬 ID�뜝�럥�뱺 �뜝�럥�돵�뜝�럥堉ｅ뜝�럥由��뜝�럥裕� �뜝�럩�뤂�뜝�럡�뀬 �뜝�럩�젧�솻洹ｏ옙�뜝�룞�삕 �뜝�럥�몥�뜝�럩逾졾뜝�럡�댉�뵓怨쀬쪠占쎈턄�뜝�럥裕욃뜝�럥�뱺�뜝�럡�맋 嶺뚢돦堉싮뇡占� Company �뜝�럥利꿰춯濡ル뾼占쎈데 �뜝�럡源삣뜝�럩�굥�뜝�럥裕욃뜝�럥�뱺 �뜝�룞�삕�뜝�럩�궋�뜝�럥由��뜝�럥�뿰 �뛾�룇瑗뱄옙�꼶.
	 */
	public Company findCompany(String id) throws SQLException {
		String sql = "SELECT company_Id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil�뜝�럥�뱺 query占쎈닱筌롫챶沅� 嶺뚮씞�걝�뚳옙 �솻洹⑥삕�뜝�럥�빢 �뜝�럡�맟�뜝�럩�젧

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�럥堉꾢뜝�럥六�
			if (rs.next()) { // �뜝�럥由겼뜝�럡臾� �뜝�럩�젧�솻洹⑥삕 �뛾�룇裕꾤뙼占�
				Company company = new Company( // Company �뤆�룇鍮섊뙼�뮁紐닷뜝占� �뜝�럡臾멨뜝�럡�뎽�뜝�럥由��뜝�럥�뿰 �뜝�럩�뤂�뜝�럡�뀬 �뜝�럩�젧�솻洹ｏ옙�뜝�룞�삕 �뜝�룞�삕�뜝�럩�궋
						rs.getInt("company_Id"),id, rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뛾�룇瑗뱄옙�꼶
		}
		return null;
	}
	
	public Company findCompanyByCompanyId(String companyId) throws SQLException {
		String sql = "SELECT id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE company_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil�뜝�럥�뱺 query占쎈닱筌롫챶沅� 嶺뚮씞�걝�뚳옙 �솻洹⑥삕�뜝�럥�빢 �뜝�럡�맟�뜝�럩�젧

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�럥堉꾢뜝�럥六�
			if (rs.next()) { // �뜝�럥由겼뜝�럡臾� �뜝�럩�젧�솻洹⑥삕 �뛾�룇裕꾤뙼占�
				Company company = new Company( // Company �뤆�룇鍮섊뙼�뮁紐닷뜝占� �뜝�럡臾멨뜝�럡�뎽�뜝�럥由��뜝�럥�뿰 �뜝�럩�뤂�뜝�럡�뀬 �뜝�럩�젧�솻洹ｏ옙�뜝�룞�삕 �뜝�룞�삕�뜝�럩�궋
						Integer.parseInt(companyId), rs.getString("id"), rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뛾�룇瑗뱄옙�꼶
		}
		return null;
	}
	
	/**
	 * �썒�슣�닔�젆源띿�ゅ뜝占� �뜝�럡�뀬�뜝�럩�뮔�뜝�럩�겱 ID�뜝�럥�뱺 �뜝�럥�돵�뜝�럥堉ｅ뜝�럥由��뜝�럥裕� �뜝�럡�뀬�뜝�럩�뮔�뜝�럩�겱�뤆�룊�삕 �댖怨뺣샍占쎌궨�뜝�럥由��뜝�럥裕됬춯�쉻�삕 �뇦猿볦삕�뜝�럡�뀬 
	 */
	public boolean existingCompany(String id) throws SQLException {
		String sql = "SELECT count(*) FROM COMPANY WHERE id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil�뜝�럥�뱺 query占쎈닱筌롫챶沅� 嶺뚮씞�걝�뚳옙 �솻洹⑥삕�뜝�럥�빢 �뜝�럡�맟�뜝�럩�젧

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �뜝�럥堉꾢뜝�럥六� 
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뛾�룇瑗뱄옙�꼶
		}
		return false;
	}
}
