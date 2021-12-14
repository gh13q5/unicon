package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import java.util.Date;

/**
 * �벑濡앸맂 寃뚯엫 愿�由щ�� �쐞�빐 �뜲�씠�꽣踰좎씠�뒪 �옉�뾽�쓣 �쟾�떞�븯�뒗 DAO �겢�옒�뒪 GAME �뀒�씠釉붿뿉 寃뚯엫 �젙蹂대�� 異붽�, �닔�젙, �궘�젣, 寃��깋 �닔�뻾
 */

public class GameDAO {
	private JDBCUtil jdbcUtil = null;

	public GameDAO() { 
		jdbcUtil = new JDBCUtil(); // JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}

	/**
	 * GAME �뀒�씠釉붿뿉 �깉濡쒖슫 寃뚯엫 �깮�꽦.
	 */
	public int create(Game game) throws SQLException {
		String sql = "INSERT INTO Game "
				+ "(title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations(), game.getCompany_id() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			int result = jdbcUtil.executeUpdate(); // insert 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return 0;
	}

	/**
	 * 湲곗〈�쓽 寃뚯엫 �젙蹂대�� �닔�젙.
	 */
	public int update(Game game) throws SQLException {
		String sql = "UPDATE Game "
				+ "SET title=?, start_date=?, end_date=?, image_address=?, description=?, category=?, reward_image=?, reward_text=?, total_reservations=? "
				+ "WHERE game_id=?";
		Object[] param = new Object[] { game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil占쏙옙 update占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			int result = jdbcUtil.executeUpdate(); // update 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return 0;
	}

	/**
	 * game_id占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙.
	 */
	public int remove(String gameId) throws SQLException {
		String sql = "DELETE FROM Game WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			int result = jdbcUtil.executeUpdate(); // delete 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return 0;
	}

	/**
	 * 占쌍억옙占쏙옙 game_id占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙占쏙옙 찾占쏙옙 Game 占쏙옙占쏙옙占쏙옙 클占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙환.
	 */
	public Game findGame(String gameId) throws SQLException {
		String sql = "SELECT  title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE game_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			if (rs.next()) { // 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
				Game game = new Game( // User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占싻삼옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						Integer.parseInt(gameId), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				return game;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}

	/**
	 * 占쏙옙체 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환
	 */
	public List<Game> findGameList() throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "ORDER BY game_id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<Game> gameList = new ArrayList<Game>(); // Game占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Game game = new Game( // Game 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				gameList.add(game); // List占쏙옙 Game 占쏙옙체 占쏙옙占쏙옙
			}
			return gameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}

	/**
	 * 카占쌓곤옙占쏙옙占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환(占쏙옙占썅가占쏙옙)
	 */
	public List<Game> categoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<Game> categorygameList = new ArrayList<Game>(); // Game占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Game game = new Game( // Game 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List占쏙옙 Game 占쏙옙체 占쏙옙占쏙옙
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}
	
	/**
	 * 카占쌓곤옙占쏙옙占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환(占쏙옙占쏙옙柰占�)
	 */
	public List<Game> endCategoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<Game> categorygameList = new ArrayList<Game>(); // Game占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Game game = new Game( // Game 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List占쏙옙 Game 占쏙옙체 占쏙옙占쏙옙
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}
	
	/**
	 * 占싯삼옙 키占쏙옙占쏙옙 占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환(占쏙옙占썅가占쏙옙)
	 */
	public List<Game> searchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<Game> searchGameList = new ArrayList<Game>(); // Game占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Game game = new Game( // Game 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				searchGameList.add(game); // List占쏙옙 Game 占쏙옙체 占쏙옙占쏙옙
			}
			return searchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}
	
	/**
	 * 占싯삼옙 키占쏙옙占쏙옙 占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환(占쏙옙占썅가占쏙옙)
	 */
	public List<Game> endsearchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<Game> endsearchGameList = new ArrayList<Game>(); // Game占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Game game = new Game( // Game 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				endsearchGameList.add(game); // List占쏙옙 Game 占쏙옙체 占쏙옙占쏙옙
			}
			return endsearchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}

	/**
	 * 占쌍억옙占쏙옙 game_id占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙占쏙옙 占싯삼옙
	 */
	public boolean existingGame(String gameId) throws SQLException {
		String sql = "SELECT count(*) FROM Game WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return false;
	}

}