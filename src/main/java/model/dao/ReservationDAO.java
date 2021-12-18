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
		jdbcUtil = new JDBCUtil(); // JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}

	// 占쎌굙占쎈튋 占쎌젟癰귨옙 �빊遺쏙옙
	public int create(Reservation reservation) throws SQLException {
		String sql = "INSERT INTO Reservation (reservation_date, game_id, user_id) VALUES (?, ?, ?)";
		Object[] param = new Object[] { reservation.getReservation_date(), reservation.getGame_id(),
				reservation.getUser_id() };
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

	// userId嚥∽옙 占쎌굙占쎈튋 占쎌젟癰귨옙 占쎄텣占쎌젫
	public int removeByUserId(String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

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

	// gameId嚥∽옙 占쎌굙占쎈튋 占쎌젟癰귨옙 占쎄텣占쎌젫
	public int removeByGameId(String gameId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

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

	// gameId占쏙옙 userId嚥∽옙 占쎌굙占쎈튋 占쎌젟癰귨옙 占쎄텣占쎌젫
	public int removeByUserIdAndGameId(String gameId, String userId) throws SQLException {
		String sql = "DELETE FROM Reservation WHERE game_id=? AND user_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾
																			// 占쎄퐬占쎌젟

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

	public int findReservateById(String gameId, String userId) throws SQLException {
		String sql = "SELECT reservation_id " + "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil�뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
																			// query�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
																			// �뜝�럥�맶�뜝�럥六울옙�벀�걠占쎄뎡
																			// �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
																			// �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			int find = 0;
			if (rs.next()) { // 占쎌젟癰귨옙 獄쏆뮄猿�
				find++;
			}
			return find;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럩�꼶
		}
		return 0;
	}

	// 雅뚯눘堉깍쭪占� userId占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎌굙占쎈튋 占쎌젟癰귨옙 List�몴占�
	// 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎈퓠占쎄퐣 筌≪뼚釉� 占쎈즲筌롫뗄�뵥 占쎄깻占쎌삋占쎈뮞占쎈퓠 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎
	public List<Game> findReservationListByUserId(int userId) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM GAME G " + "WHERE G.game_id IN " + "(SELECT rv.game_id " + "FROM Reservation rv "
				+ "WHERE rv.user_id=? ) ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			List<Game> reservationList = new ArrayList<Game>();
			while (rs.next()) {
				Game game = new Game( // Reservation 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쏙옙占쎌삢
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getString("image_address"), rs.getString("description"), rs.getString("category"),
						rs.getString("reward_image"), rs.getString("reward_text"), rs.getInt("total_reservations"),
						rs.getInt("company_id"));
				reservationList.add(game);
			}
			return reservationList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return null;
	}

	public List<Reservation> findReservationListByGameId(int gameId) throws SQLException {
		String sql = "SELECT reservation_id, reservation_date, user_id " + "FROM Reservation " + "WHERE game_id=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			List<Reservation> reservationList = new ArrayList<Reservation>();
			while (rs.next()) {
				Reservation reservation = new Reservation( // Reservation 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쏙옙占쎌삢
						rs.getInt("reservation_id"), rs.getDate("reservation_date"), gameId, rs.getInt("user_id"));
				reservationList.add(reservation);
			}
			return reservationList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return null;
	}

	/**
	 * 占쎈퉸占쎈뼣 User揶쏉옙 占쎌굙占쎈튋占쎈립 野껊슣�뿫占쎌뵥筌욑옙 占쎌넇占쎌뵥
	 */
	public boolean isReservate(String gameId, String userId) throws SQLException {
		String sql = "SELECT count(*) " + "FROM Reservation " + "WHERE game_id=? AND user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId, userId }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾
																			// 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return false;
	}

}
