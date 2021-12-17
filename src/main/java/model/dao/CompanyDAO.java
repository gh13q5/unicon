package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

/**
 * 占쎄텢占쎌뒠占쎌쁽 �꽴占썹뵳�됵옙占� 占쎌맄占쎈퉸 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞 占쎌삂占쎈씜占쎌뱽 占쎌읈占쎈뼖占쎈릭占쎈뮉 DAO 占쎄깻占쎌삋占쎈뮞 USERINFO 占쎈�믭옙�뵠�뇡遺용퓠 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 �빊遺쏙옙, 占쎈땾占쎌젟, 占쎄텣占쎌젫, 野껓옙占쎄퉳 占쎈땾占쎈뻬
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}

	/**
	 * 占쎌돳占쎄텢 �꽴占썹뵳占� 占쎈�믭옙�뵠�뇡遺용퓠 占쎄퉱嚥≪뮇�뒲 占쎌돳占쎄텢 占쎄문占쎄쉐.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO COMPANY VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { company.getCompanyId(), company.getId(), company.getPassword(),
				company.getEmail(), company.getName(), company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			int result = jdbcUtil.executeUpdate(); // insert �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return 0;
	}

	/**
	 * 疫꿸퀣�덌옙�벥 占쎌돳占쎄텢 占쎌젟癰귣�占쏙옙 占쎈땾占쎌젟.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE COMPANY " + "SET id=?, password=?, email=?, name=?, phone_number=? "
				+ "WHERE company_id=?";
		Object[] param = new Object[] {  company.getCompanyId(), company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			int result = jdbcUtil.executeUpdate(); // update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return 0;
	}

	/**
	 * 占쎌돳占쎄텢 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎌돳占쎄텢 占쎄텣占쎌젫
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM COMPANY WHERE company_Id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			int result = jdbcUtil.executeUpdate(); // delete �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return 0;
	}

	/**
	 * 雅뚯눘堉깍쭪占� 占쎌돳占쎄텢 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎌돳占쎄텢 占쎌젟癰귣�占쏙옙 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎈퓠占쎄퐣 筌≪뼚釉� Company 占쎈즲筌롫뗄�뵥 占쎄깻占쎌삋占쎈뮞占쎈퓠 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
	 */
	public Company findCompany(String id) throws SQLException {
		String sql = "SELECT company_Id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			if (rs.next()) { // 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				Company company = new Company( // Company 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌돳占쎄텢 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getInt("company_Id"),id, rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return null;
	}
	/**
	 * 雅뚯눘堉깍쭪占� 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽揶쏉옙 鈺곕똻�삺占쎈릭占쎈뮉筌욑옙 野껓옙占쎄텢 
	 */
	public boolean existingCompany(int i) throws SQLException {
		String sql = "SELECT count(*) FROM COMPANY WHERE company_Id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {i});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return false;
	}
}
