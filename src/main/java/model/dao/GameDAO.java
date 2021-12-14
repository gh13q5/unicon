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
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	/**
	 * GAME 테이블에 새로운 게임 생성.
	 */
	public int create(Game game) throws SQLException {
		String sql = "INSERT INTO Game "
				+ "(title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { game.getGame_id(), game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations(), game.getCompany_id() };
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
	 * 기존의 게임 정보를 수정.
	 */
	public int update(Game game) throws SQLException {
		String sql = "UPDATE Game "
				+ "SET title=?, start_date=?, end_date=?, image_address=?, description=?, category=?, reward_image=?, reward_text=?, total_reservations=? "
				+ "WHERE game_id=?";
		Object[] param = new Object[] { game.getTitle(), game.getStart_date(), game.getEnd_date(),
				game.getImage_address(), game.getDescription(), game.getCategory(), game.getReward_image(),
				game.getReward_text(), game.getTotal_reservations() };
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
	 * game_id�� �ش��ϴ� ������ ����.
	 */
	public int remove(String gameId) throws SQLException {
		String sql = "DELETE FROM Game WHERE game_id=?";
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

	/**
	 * �־��� game_id�� �ش��ϴ� ������ �����ͺ��̽����� ã�� Game ������ Ŭ������ �����Ͽ� ��ȯ.
	 */
	public Game findGame(String gameId) throws SQLException {
		String sql = "SELECT  title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE game_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { // �л� ���� �߰�
				Game game = new Game( // User ��ü�� �����Ͽ� �л� ������ ����
						Integer.parseInt(gameId), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				return game;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * ��ü ���� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Game> findGameList() throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "ORDER BY game_id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Game> gameList = new ArrayList<Game>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Game game = new Game( // Game ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				gameList.add(game); // List�� Game ��ü ����
			}
			return gameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * ī�װ����� �ش��ϴ� ���� ������ �˻��Ͽ� List�� ���� �� ��ȯ(���డ��)
	 */
	public List<Game> categoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Game> categorygameList = new ArrayList<Game>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Game game = new Game( // Game ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List�� Game ��ü ����
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ī�װ����� �ش��ϴ� ���� ������ �˻��Ͽ� List�� ���� �� ��ȯ(����Ұ�)
	 */
	public List<Game> endCategoryGameList(String category) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE category=? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { category }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Game> categorygameList = new ArrayList<Game>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Game game = new Game( // Game ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				categorygameList.add(game); // List�� Game ��ü ����
			}
			return categorygameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �˻� Ű���� �� �ش��ϴ� ���� ������ �˻��Ͽ� List�� ���� �� ��ȯ(���డ��)
	 */
	public List<Game> searchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND start_date <= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) AND end_date >= (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Game> searchGameList = new ArrayList<Game>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Game game = new Game( // Game ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				searchGameList.add(game); // List�� Game ��ü ����
			}
			return searchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �˻� Ű���� �� �ش��ϴ� ���� ������ �˻��Ͽ� List�� ���� �� ��ȯ(���డ��)
	 */
	public List<Game> endsearchGameList(String keyWord) throws SQLException {
		String sql = "SELECT game_id, title, start_date, end_date, image_address, description, category, reward_image, reward_text, total_reservations, company_id "
				+ "FROM Game " + "WHERE title like ? AND end_date < (SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') FROM DUAL) " ;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyWord }); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Game> endsearchGameList = new ArrayList<Game>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Game game = new Game( // Game ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("game_id"), rs.getString("title"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("image_address"), rs.getString("description"),
						rs.getString("category"), rs.getString("reward_image"), rs.getString("reward_text"),
						rs.getInt("total_reservations"), rs.getInt("company_id"));
				endsearchGameList.add(game); // List�� Game ��ü ����
			}
			return endsearchGameList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * �־��� game_id�� �ش��ϴ� ������ �����ϴ��� �˻�
	 */
	public boolean existingGame(String gameId) throws SQLException {
		String sql = "SELECT count(*) FROM Game WHERE game_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { gameId }); // JDBCUtil�� query���� �Ű� ���� ����

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