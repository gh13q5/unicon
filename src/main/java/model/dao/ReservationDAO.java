package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reservation;

import model.User;

import model.Game;

public class ReservationDAO {

	private JDBCUtil jdbcUtil = null;

	public ReservationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	// 예약 정보 추가
	public int create(Reservation reservation) throws SQLException {
		String sql = "INSERT INTO Reservation (reservation_date, game_id, user_id) VALUES (?, ?, ?)";
		Object[] param = new Object[] { reservation.getReservation_date(), reservation.getGame_id(),
				reservation.getUser_id() };
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

	// userId로 예약 정보 삭제
	public int removeByUserId(String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 delete문과 매개 변수 설정

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

	// gameId로 예약 정보 삭제
	public int removeByGameId(String gameId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil에 delete문과 매개 변수 설정

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

	// gameId와 userId로 예약 정보 삭제
	public int removeByUserIdAndGameId(String gameId, String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=? AND user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil에 delete문과 매개 변수 설정

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
	
	public int findReservateById(String gameId, String userId) throws SQLException {
		String sql = "SELECT reservation_id "
				+ "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			int find = 0;
			if (rs.next()) {						//  정보 발견
				find++;
			}
			return find;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return 0;
	}

	/**
	 * 해당 User가 예약한 게임인지 확인
	 */
	public boolean isReservate(String gameId, String userId) throws SQLException {
		String sql = "SELECT count(*) " + "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return false;
	}

}
