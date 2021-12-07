package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Game;
import java.util.Date;

/**
 * 등록된 게임 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스 GAME 테이블에 게임 정보를 추가, 수정, 삭제, 검색 수행
 */

public class GameDAO {
	private JDBCUtil jdbcUtil = null;

	public GameDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/**
	 * GAME 테이블에 새로운 게임 생성.
	 */
	public int create(Game game) throws SQLException {
		String sql = "INSERT INTO Game VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { game.getGame_id(), game.getTitle(), new java.sql.Date(game.getStart_date().getTime()), 
				new java.sql.Date(game.getEnd_date().getTime()),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations(), game.getCompany_id() };
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

	/**
	 * 기존의 게임 정보를 수정.
	 */
	public int update(Game game) throws SQLException {
		String sql = "UPDATE Game "
				+ "SET title=?, start_date=?, end_date=?, image_address=?, description=?, category=?, reward_image=?, reward_text=?, total_reservations=? "
				+ "WHERE game_id=?";
		Object[] param = new Object[] { game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
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

	/**
	 * game_id에 해당하는 게임을 삭제.
	 */
	public int remove(String gameId) throws SQLException {
		String sql = "DELETE FROM Game WHERE game_id=?";
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

	/**
	 * 주어진 game_id에 해당하는 게임을 데이터베이스에서 찾아 Game 도메인 클래스에 저장하여 반환.
	 */
	public Game findGame(String gameId) throws SQLException {
		String sql = "SELECT  title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE game_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				Game game = new Game( // User 객체를 생성하여 학생 정보를 저장
						Integer.parseInt(gameId), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				return game;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 전체 게임 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Game> findGameList() throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "ORDER BY game_id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Game> gameList = new ArrayList<Game>(); // Game들의 리스트 생성
			while (rs.next()) {
				Game game = new Game( // Game 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				gameList.add(game); // List에 Game 객체 저장
			}
			return gameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 카테고리에 해당하는 게임 정보를 검색하여 List에 저장 및 반환(예약가능)
	 */
	public List<Game> categoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Game> categorygameList = new ArrayList<Game>(); // Game들의 리스트 생성
			while (rs.next()) {
				Game game = new Game( // Game 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List에 Game 객체 저장
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
	
	/**
	 * 카테고리에 해당하는 게임 정보를 검색하여 List에 저장 및 반환(예약불가)
	 */
	public List<Game> endCategoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Game> categorygameList = new ArrayList<Game>(); // Game들의 리스트 생성
			while (rs.next()) {
				Game game = new Game( // Game 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List에 Game 객체 저장
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
	
	/**
	 * 검색 키워드 에 해당하는 게임 정보를 검색하여 List에 저장 및 반환(예약가능)
	 */
	public List<Game> searchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Game> searchGameList = new ArrayList<Game>(); // Game들의 리스트 생성
			while (rs.next()) {
				Game game = new Game( // Game 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				searchGameList.add(game); // List에 Game 객체 저장
			}
			return searchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
	
	/**
	 * 검색 키워드 에 해당하는 게임 정보를 검색하여 List에 저장 및 반환(예약가능)
	 */
	public List<Game> endsearchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Game> endsearchGameList = new ArrayList<Game>(); // Game들의 리스트 생성
			while (rs.next()) {
				Game game = new Game( // Game 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				endsearchGameList.add(game); // List에 Game 객체 저장
			}
			return endsearchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 주어진 game_id에 해당하는 게임이 존재하는지 검사
	 */
	public boolean existingGame(String gameId) throws SQLException {
		String sql = "SELECT count(*) FROM Game WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
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
