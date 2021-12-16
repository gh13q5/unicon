package model.dao;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import java.util.Date;

/**
 * 占쎈쾻嚥≪빖留� 野껊슣�뿫 �꽴占썹뵳�됵옙占� 占쎌맄占쎈퉸 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞 占쎌삂占쎈씜占쎌뱽 占쎌읈占쎈뼖占쎈릭占쎈뮉 DAO 占쎄깻占쎌삋占쎈뮞 GAME 占쎈�믭옙�뵠�뇡遺용퓠 野껊슣�뿫 占쎌젟癰귣�占쏙옙 �빊遺쏙옙, 占쎈땾占쎌젟, 占쎄텣占쎌젫, 野껓옙占쎄퉳 占쎈땾占쎈뻬
 */

public class GameDAO {
	private JDBCUtil jdbcUtil = null;

	public GameDAO() { 
		jdbcUtil = new JDBCUtil(); // JDBCUtil �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
	}

	/**
	 * GAME 占쎈�믭옙�뵠�뇡遺용퓠 占쎄퉱嚥≪뮇�뒲 野껊슣�뿫 占쎄문占쎄쉐.
	 */
	public int create(Game game) throws SQLException {
		String sql = "INSERT INTO Game "
				+ "(title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations(), game.getCompany_id() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �뜝�룞�삕 insert�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			int result = jdbcUtil.executeUpdate(); // insert �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return 0;
	}

	/**
	 * 疫꿸퀣�덌옙�벥 野껊슣�뿫 占쎌젟癰귣�占쏙옙 占쎈땾占쎌젟.
	 */
	public int update(Game game) throws SQLException {
		String sql = "UPDATE Game "
				+ "SET title=?, start_date=?, end_date=?, image_address=?, description=?, category=?, reward_image=?, reward_text=? "
				+ "WHERE game_id=?";
		Object[] param = new Object[] { game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getGame_id() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�뜝�룞�삕 update�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			int result = jdbcUtil.executeUpdate(); // update �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return 0;
	}

	/**
	 * game_id�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕.
	 */
	public int remove(String gameId) throws SQLException {
		String sql = "DELETE FROM Game WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�뜝�룞�삕 delete�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			int result = jdbcUtil.executeUpdate(); // delete �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return 0;
	}

	/**
	 * �뜝�뙇�뼲�삕�뜝�룞�삕 game_id�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢釉앹삕�뜝�떛�룞�삕�뜝�룞�삕�뜝�룞�삕 李얍뜝�룞�삕 Game �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �겢�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�솚.
	 */
	public Game findGame(String gameId) throws SQLException {
		String sql = "SELECT  title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE game_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			if (rs.next()) { // �뜝�떩�궪�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�뙥怨ㅼ삕
				Game game = new Game( // User �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�떩�궪�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						Integer.parseInt(gameId), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				return game;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return null;
	}

	/**
	 * �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떙�궪�삕�뜝�떦�슱�삕 List�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�솚
	 */
	public List<Game> findGameList() throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "ORDER BY end_date DESC ";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			List<Game> gameList = new ArrayList<Game>(); // Game�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Game game = new Game( // Game �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				gameList.add(game); // List�뜝�룞�삕 Game �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}
			return gameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return null;
	}

	/**
	 * 移닷뜝�뙎怨ㅼ삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떙�궪�삕�뜝�떦�슱�삕 List�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�솚(�뜝�룞�삕�뜝�뛿媛��뜝�룞�삕)
	 */
	public List<Game> categoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			List<Game> categorygameList = new ArrayList<Game>(); // Game�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Game game = new Game( // Game �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List�뜝�룞�삕 Game �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return null;
	}
	
	/**
	 * 移닷뜝�뙎怨ㅼ삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떙�궪�삕�뜝�떦�슱�삕 List�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�솚(�뜝�룞�삕�뜝�룞�삕�윴�뜝占�)
	 */
	public List<Game> endCategoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			List<Game> categorygameList = new ArrayList<Game>(); // Game�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Game game = new Game( // Game �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List�뜝�룞�삕 Game �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return null;
	}
	
	/**
	 * �뜝�떙�궪�삕 �궎�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떙�궪�삕�뜝�떦�슱�삕 List�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�솚(�뜝�룞�삕�뜝�뛿媛��뜝�룞�삕)
	 */
	public List<Game> searchGameList(String keyWord) throws SQLException, UnsupportedEncodingException{
		keyWord = new String(keyWord.getBytes("ISO8859_1"), "UTF-8");
		//String k = "\'%"+keyWord+"%\'";
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { "%"+keyWord+"%" }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			List<Game> searchGameList = new ArrayList<Game>(); // Game�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Game game = new Game( // Game �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				searchGameList.add(game); // List�뜝�룞�삕 Game �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}
			return searchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return null;
	}
	
	/**
	 * �뜝�떙�궪�삕 �궎�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떙�궪�삕�뜝�떦�슱�삕 List�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�솚(�뜝�룞�삕�뜝�뛿媛��뜝�룞�삕)
	 */
	public List<Game> endsearchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			List<Game> endsearchGameList = new ArrayList<Game>(); // Game�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Game game = new Game( // Game �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				endsearchGameList.add(game); // List�뜝�룞�삕 Game �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}
			return endsearchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return null;
	}

	/**
	 * �뜝�뙇�뼲�삕�뜝�룞�삕 game_id�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕�뜝�룞�삕 �뜝�떙�궪�삕
	 */
	public boolean existingGame(String gameId) throws SQLException {
		String sql = "SELECT count(*) FROM Game WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�뜝�룞�삕 query�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query �뜝�룞�삕�뜝�룞�삕
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return false;
	}
	
	public int updateReservate(String gameId, int total_reservate) throws SQLException {
		String sql = "UPDATE Game "
				+ "SET total_reservations=? "
				+ "WHERE game_id=?";
		Object[] param = new Object[] { total_reservate, gameId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�뜝�룞�삕 update�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			int result = jdbcUtil.executeUpdate(); // update �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource �뜝�룞�삕�솚
		}
		return 0;
	}

}