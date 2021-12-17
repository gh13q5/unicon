package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;

import model.User;

import model.Game;

public class ReservationDAO {

	private JDBCUtil jdbcUtil = null;

	public ReservationDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 媛앹껜 �깮�꽦 
	}

	// �삁�빟 �젙蹂� 異붽�
	public int create(Reservation reservation) throws SQLException {
		String sql = "INSERT INTO Reservation (reservation_date, game_id, user_id) VALUES (?, ?, ?)";
		Object[] param = new Object[] { reservation.getReservation_date(), reservation.getGame_id(),
				reservation.getUser_id() };
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

	// userId濡� �삁�빟 �젙蹂� �궘�젣
	public int removeByUserId(String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

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

	// gameId濡� �삁�빟 �젙蹂� �궘�젣
	public int removeByGameId(String gameId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

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

	// gameId�� userId濡� �삁�빟 �젙蹂� �궘�젣
	public int removeByUserIdAndGameId(String gameId, String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=? AND user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

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

	public int findReservateById(String gameId, String userId) throws SQLException {
		String sql = "SELECT reservation_id " + "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil占쎈쐻占쎈짗占쎌굲 query占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈뻿�ⓦ끉�굲
																			// 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �떎�뻾
			int find = 0;
			if (rs.next()) { // �젙蹂� 諛쒓껄
				find++;
			}
			return find;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쎈쐻占쎈짗占쎌굲占쎌넎
		}
		return 0;
	}

	// 二쇱뼱吏� userId�뿉 �빐�떦�븯�뒗 �삁�빟 �젙蹂� List瑜� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 �룄硫붿씤 �겢�옒�뒪�뿉 ���옣�븯�뿬 諛섑솚
	public List<Game> findReservationListByUserId(int userId) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id " + "FROM GAME G " + "WHERE G.game_id IN " + "(SELECT rv.game_id " + "FROM Reservation rv " + "WHERE rv.user_id=? ) ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �떎�뻾
			List<Game> reservationList = new ArrayList<Game>();
			while (rs.next()) {
				Game game = new Game( // Reservation 媛앹껜瑜� �깮�꽦�븯�뿬 ���옣
						rs.getInt("game_id"),rs.getString("title"),rs.getDate("start_date"),rs.getDate("end_date"),rs.getString("image_address"),rs.getString("description"),rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				reservationList.add(game);
			}
			return reservationList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 諛섑솚
		}
		return null;
	}

	/**
	 * �빐�떦 User媛� �삁�빟�븳 寃뚯엫�씤吏� �솗�씤
	 */
	public boolean isReservate(String gameId, String userId) throws SQLException {
		String sql = "SELECT count(*) " + "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 諛섑솚
		}
		return false;
	}

}
