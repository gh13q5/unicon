package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Interests;

/**
 * ��ϵ� ���� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ���� Interests ���̺� ���� ������ �߰�, ����, ����, �˻� ����
 */

public class InterestsDAO {
	private JDBCUtil jdbcUtil = null;

	public InterestsDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	/**
	 * Interests ���̺� ���ο� ���� ����.
	 */
	public int create(Interests interests) throws SQLException {
		String sql = "INSERT INTO Interests VALUES (?, ?, ?)";
		Object[] param = new Object[] { interests.getId(), interests.getUser_id(), interests.getGenre_id() };
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
	 * ������ ���ɻ� ������ ����.
	 */
	public int update(Interests interests) throws SQLException {
		String sql = "UPDATE Interests "
				+ "SET user_id=?, genre_id=? "
				+ "WHERE id=?";
		Object[] param = new Object[] { interests.getUser_id(), interests.getGenre_id(), interests.getId() };
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
	 * interestsId�� �ش��ϴ� ���ɻ� ����.
	 */
	public int remove(String interestsId) throws SQLException {
		String sql = "DELETE FROM Interests WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { interestsId }); // JDBCUtil�� delete���� �Ű� ���� ����

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
	 * �־��� interestsId�� �ش��ϴ� ���ɻ縦 �����ͺ��̽����� ã�� Interests ������ Ŭ������ �����Ͽ� ��ȯ.
	 */
	public Interests findInterests(String interestsId) throws SQLException {
		String sql = "SELECT user_id, genre_id "
				+ "FROM Interests " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { interestsId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { 
				Interests interests = new Interests(
						Integer.parseInt(interestsId), rs.getInt("user_id"), rs.getInt("genre_id"));
				return interests;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * ��ü ���ɻ� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Interests> findInterestsList() throws SQLException {
		String sql = "SELECT user_id, genre_id "
				+ "FROM Interests " + "ORDER BY id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Interests> interestsList = new ArrayList<Interests>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Interests interests = new Interests(
						rs.getInt("id"), rs.getInt("user_id"), rs.getInt("genre_id"));
				interestsList.add(interests);
			}
			return interestsList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �ش� ������ ���ɻ� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Interests> findUserInterestsList(int id) throws SQLException {
		String sql = "SELECT id, genre_id "
				+ "FROM Interests " + "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Interests> findUserInterestsList = new ArrayList<Interests>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Interests interests = new Interests(
						rs.getInt("id"), id, rs.getInt("genre_id"));
				findUserInterestsList.add(interests);
			}
			return findUserInterestsList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}


	/**
	 * �־���  interestsId�� �ش��ϴ� ���ɻ簡 �����ϴ��� �˻�
	 */
	public boolean existingInterests(String interestsId) throws SQLException {
		String sql = "SELECT count(*) FROM Interests WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { interestsId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return false;
	}

}
