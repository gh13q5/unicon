package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reservation;

import model.User;

import model.Game;

public class ReservationDAO {

	private JDBCUtil jdbcUtil = null;

	public ReservationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	// ���� ���� �߰�
	public int create(Reservation reservation) throws SQLException {
		String sql = "INSERT INTO Reservation (reservation_date, game_id, user_id) VALUES (?, ?, ?)";
		Object[] param = new Object[] { reservation.getReservation_date(), reservation.getGame_id(),
				reservation.getUser_id() };
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

	// userId�� ���� ���� ����
	public int removeByUserId(String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� delete���� �Ű� ���� ����

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

	// gameId�� ���� ���� ����
	public int removeByGameId(String gameId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�� delete���� �Ű� ���� ����

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

	// gameId�� userId�� ���� ���� ����
	public int removeByUserIdAndGameId(String gameId, String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=? AND user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil�� delete���� �Ű� ���� ����

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
	 * �ش� User�� ������ �������� Ȯ��
	 */
	public boolean isReservate(String gameId, String userId) throws SQLException {
		String sql = "SELECT reservation_id " + "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { // �л� ���� �߰�
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return false;
	}

}
