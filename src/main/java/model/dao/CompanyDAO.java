package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

/**
 * 占쎈쐻占쎈윞占쎈�э옙�쐻占쎈윪占쎈츛占쎈쐻占쎈윪占쎄껑 �뜝�럡�뒋占쎈쐻占쎈쑟�얜냲�삕占쎈㎜占쎌굲占쎈쐻�뜝占� 占쎈쐻占쎈윪筌띻쐼�쐻占쎈윥占쎈뤅
 * 占쎈쐻占쎈윥占쎈ぅ占쎈쐻占쎈윪�얠±�쐻占쎈윞占쎈뙃占쎈탶�⑥�ъ첓�뜝�럥�꼧占쎈쐻占쎈윥獒뺧옙
 * 占쎈쐻占쎈윪占쎄탾占쎈쐻占쎈윥占쎈턀占쎈쐻占쎈윪獄�占� 占쎈쐻占쎈윪占쎌벁占쎈쐻占쎈윥�젆戮⑸쐻占쎈윥�뵳占쏙옙�쐻占쎈윥獒뺧옙 DAO
 * 占쎈쐻占쎈윞繹먯궍�쐻占쎈윪占쎄데占쎈쐻占쎈윥獒뺧옙 USERINFO 占쎈쐻占쎈윥�뜝�럥占쏙옙占쎌굲�뜝�럥�꼧�뜝�럥��占쎄껀占쎌뒠占쎈군
 * 占쎈쐻占쎈윞占쎈�э옙�쐻占쎈윪占쎈츛占쎈쐻占쎈윪占쎄껑 占쎈쐻占쎈윪占쎌젳占쎌녃域뱄퐦�삕占쎈쐻占쎈짗占쎌굲 �뜝�럥�돯占쎄껀占쎈짗占쎌굲,
 * 占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윪占쎌젳, 占쎈쐻占쎈윞占쎈��占쎈쐻占쎈윪占쎌졆, 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿
 * 占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윥筌묕옙
 */
public class CompanyDAO {
	private JDBCUtil jdbcUtil = null;

	public CompanyDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 占쎈쨬占쎈즵�뜮�꼯�쇊�뜝占� 占쎈쐻占쎈윞�눧硫⑤쐻占쎈윞占쎈렰
	}

	/**
	 * 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� �뜝�럡�뒋占쎈쐻占쎈쑟�얜뀘�쐻�뜝占�
	 * 占쎈쐻占쎈윥�뜝�럥占쏙옙占쎌굲�뜝�럥�꼧�뜝�럥��占쎄껀占쎌뒠占쎈군 占쎈쐻占쎈윞占쎈룼占쎌뒙占쎈룱獒뺣돍�삕占쎈��
	 * 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� 占쎈쐻占쎈윞�눧硫⑤쐻占쎈윞占쎈렰.
	 */
	public int create(Company company) throws SQLException {
		String sql = "INSERT INTO COMPANY " + "(id, password, email, name, phone_number ) " + "VALUES (?, ?, ?, ?, ?)";
		Object[] param = new Object[] { company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 占쎈쐻占쎈윥占쎈군 insert�뜝�럥�떛嶺뚮∥梨뜻쾮占� 癲ル슢�뵞占쎄콨占쎈슪�삕
													// 占쎌녃域밟뫁�굲占쎈쐻占쎈윥占쎈묄 占쎈쐻占쎈윞占쎈쭫占쎈쐻占쎈윪占쎌젳

		try {
			int result = jdbcUtil.executeUpdate(); // insert �뜝�럥�떛占쎈쐻�뜝占� 占쎈쐻占쎈윥�젆袁��쐻占쎈윥筌묕옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
		}
		return 0;
	}

	/**
	 * 占쎈섀饔낅챸占쏙퐦�삕占쎈쐣占쎌굲�뜝�럥爰� 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� 占쎈쐻占쎈윪占쎌젳占쎌녃域뱄퐦�삕占쎈쐻占쎈짗占쎌굲
	 * 占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윪占쎌젳.
	 */
	public int update(Company company) throws SQLException {
		String sql = "UPDATE COMPANY " + "SET id=?, password=?, email=?, name=?, phone_number=? "
				+ "WHERE company_id=?";
		Object[] param = new Object[] { company.getId(), company.getPassword(), company.getEmail(), company.getName(),
				company.getPhone_number(), company.getCompanyId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil占쎈쐻占쎈윥占쎈군 update�뜝�럥�떛嶺뚮∥梨뜻쾮占� 癲ル슢�뵞占쎄콨占쎈슪�삕
													// 占쎌녃域밟뫁�굲占쎈쐻占쎈윥占쎈묄 占쎈쐻占쎈윞占쎈쭫占쎈쐻占쎈윪占쎌젳

		try {
			int result = jdbcUtil.executeUpdate(); // update �뜝�럥�떛占쎈쐻�뜝占� 占쎈쐻占쎈윥�젆袁��쐻占쎈윥筌묕옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
		}
		return 0;
	}

	/**
	 * 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� ID占쎈쐻占쎈윥占쎈군 占쎈쐻占쎈윥占쎈뤅占쎈쐻占쎈윥�젆節낅쐻占쎈윥�뵳占쏙옙�쐻占쎈윥獒뺧옙
	 * 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� 占쎈쐻占쎈윞占쎈��占쎈쐻占쎈윪占쎌졆
	 */
	public int remove(String companyId) throws SQLException {
		String sql = "DELETE FROM COMPANY WHERE company_Id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil占쎈쐻占쎈윥占쎈군 delete�뜝�럥�떛嶺뚮∥梨뜻쾮占�
																		// 癲ル슢�뵞占쎄콨占쎈슪�삕 占쎌녃域밟뫁�굲占쎈쐻占쎈윥占쎈묄
																		// 占쎈쐻占쎈윞占쎈쭫占쎈쐻占쎈윪占쎌젳

		try {
			int result = jdbcUtil.executeUpdate(); // delete �뜝�럥�떛占쎈쐻�뜝占� 占쎈쐻占쎈윥�젆袁��쐻占쎈윥筌묕옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
		}
		return 0;
	}

	/**
	 * 占쎌뜏占쎌뒩占쎈땾占쎌젂繹먮씮占썬굝�쐻�뜝占� 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� ID占쎈쐻占쎈윥占쎈군
	 * 占쎈쐻占쎈윥占쎈뤅占쎈쐻占쎈윥�젆節낅쐻占쎈윥�뵳占쏙옙�쐻占쎈윥獒뺧옙 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈��
	 * 占쎈쐻占쎈윪占쎌젳占쎌녃域뱄퐦�삕占쎈쐻占쎈짗占쎌굲
	 * 占쎈쐻占쎈윥占쎈ぅ占쎈쐻占쎈윪�얠±�쐻占쎈윞占쎈뙃占쎈탶�⑥�ъ첓�뜝�럥�꼧占쎈쐻占쎈윥獒뺤쉩�쐻占쎈윥占쎈군占쎈쐻占쎈윞占쎈쭓
	 * 癲ル슓�룱�젆�떘�눀�뜝占� Company 占쎈쐻占쎈윥筌앷염異�嚥▲꺂毓쇔뜝�럥�뜲
	 * 占쎈쐻占쎈윞繹먯궍�쐻占쎈윪占쎄데占쎈쐻占쎈윥獒뺤쉩�쐻占쎈윥占쎈군 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈윪占쎄텑占쎈쐻占쎈윥�뵳占쏙옙�쐻占쎈윥占쎈염
	 * 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬.
	 */
	public Company findCompany(String id) throws SQLException {
		String sql = "SELECT company_Id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil占쎈쐻占쎈윥占쎈군 query�뜝�럥�떛嶺뚮∥梨뜻쾮占� 癲ル슢�뵞占쎄콨占쎈슪�삕
																// 占쎌녃域밟뫁�굲占쎈쐻占쎈윥占쎈묄 占쎈쐻占쎈윞占쎈쭫占쎈쐻占쎈윪占쎌젳

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈쐻占쎈윥�젆袁��쐻占쎈윥筌묕옙
			if (rs.next()) { // 占쎈쐻占쎈윥�뵳寃쇰쐻占쎈윞�눧占� 占쎈쐻占쎈윪占쎌젳占쎌녃域밟뫁�굲 占쎈쎗占쎈즵獒뺢쑈�쇊�뜝占�
				Company company = new Company( // Company 占쎈쨬占쎈즵�뜮�꼯�쇊占쎈츃筌뤿떣�쐻�뜝占� 占쎈쐻占쎈윞�눧硫⑤쐻占쎈윞占쎈렰占쎈쐻占쎈윥�뵳占쏙옙�쐻占쎈윥占쎈염
												// 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� 占쎈쐻占쎈윪占쎌젳占쎌녃域뱄퐦�삕占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈윪占쎄텑
						rs.getInt("company_Id"), id, rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
		}
		return null;
	}

	public Company findCompanyByCompanyId(String companyId) throws SQLException {
		String sql = "SELECT id, password, email, name, phone_number " + "FROM COMPANY " + "WHERE company_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { companyId }); // JDBCUtil占쎈쐻占쎈윥占쎈군 query�뜝�럥�떛嶺뚮∥梨뜻쾮占�
																		// 癲ル슢�뵞占쎄콨占쎈슪�삕 占쎌녃域밟뫁�굲占쎈쐻占쎈윥占쎈묄
																		// 占쎈쐻占쎈윞占쎈쭫占쎈쐻占쎈윪占쎌젳

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈쐻占쎈윥�젆袁��쐻占쎈윥筌묕옙
			if (rs.next()) { // 占쎈쐻占쎈윥�뵳寃쇰쐻占쎈윞�눧占� 占쎈쐻占쎈윪占쎌젳占쎌녃域밟뫁�굲 占쎈쎗占쎈즵獒뺢쑈�쇊�뜝占�
				Company company = new Company( // Company 占쎈쨬占쎈즵�뜮�꼯�쇊占쎈츃筌뤿떣�쐻�뜝占� 占쎈쐻占쎈윞�눧硫⑤쐻占쎈윞占쎈렰占쎈쐻占쎈윥�뵳占쏙옙�쐻占쎈윥占쎈염
												// 占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윞占쎈�� 占쎈쐻占쎈윪占쎌젳占쎌녃域뱄퐦�삕占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈윪占쎄텑
						Integer.parseInt(companyId), rs.getString("id"), rs.getString("password"),
						rs.getString("email"), rs.getString("name"), rs.getString("phone_number"));
				return company;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
		}
		return null;
	}

	/**
	 * 占쎌뜏占쎌뒩占쎈땾占쎌젂繹먮씮占썬굝�쐻�뜝占� 占쎈쐻占쎈윞占쎈�э옙�쐻占쎈윪占쎈츛占쎈쐻占쎈윪占쎄껑 ID占쎈쐻占쎈윥占쎈군
	 * 占쎈쐻占쎈윥占쎈뤅占쎈쐻占쎈윥�젆節낅쐻占쎈윥�뵳占쏙옙�쐻占쎈윥獒뺧옙 占쎈쐻占쎈윞占쎈�э옙�쐻占쎈윪占쎈츛占쎈쐻占쎈윪占쎄껑占쎈쨬占쎈즸占쎌굲
	 * 占쎈뙑�⑤베�깓�뜝�럩沅⑨옙�쐻占쎈윥�뵳占쏙옙�쐻占쎈윥獒뺣맟異�占쎌돸占쎌굲 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈��
	 */
	public boolean existingCompany(String id) throws SQLException {
		String sql = "SELECT count(*) FROM COMPANY WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil占쎈쐻占쎈윥占쎈군 query�뜝�럥�떛嶺뚮∥梨뜻쾮占� 癲ル슢�뵞占쎄콨占쎈슪�삕
																// 占쎌녃域밟뫁�굲占쎈쐻占쎈윥占쎈묄 占쎈쐻占쎈윞占쎈쭫占쎈쐻占쎈윪占쎌젳

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈쐻占쎈윥�젆袁��쐻占쎈윥筌묕옙
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쎈쎗占쎈즵�몭諭꾩삕占쎄섬
		}
		return false;
	}
}
