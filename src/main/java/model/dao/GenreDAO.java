package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Genre;

/**
 * ��ϵ� ���� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ���� Genre ���̺� ���� ������ �߰�, ����, ����, �˻� ����
 */

public class GenreDAO {
	private JDBCUtil jdbcUtil = null;

	public GenreDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	/**
	 * GENRE ���̺� ���ο� ���� ����.
	 */
	public int create(Genre genre) throws SQLException {
		String sql = "INSERT INTO Genre VALUES (?, ?)";
		Object[] param = new Object[] { genre.getGenre_id(), genre.getName() };
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
	 * ������ �帣 ������ ����.
	 */
	public int update(Genre genre) throws SQLException {
		String sql = "UPDATE Genre " + "SET name=? " + "WHERE genre_id=?";
		Object[] param = new Object[] { genre.getName(), genre.getGenre_id() };
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
	 * genre_id�� �ش��ϴ� �帣 ����.
	 */
	public int remove(String genreId) throws SQLException {
		String sql = "DELETE FROM Genre WHERE genre_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { genreId }); // JDBCUtil�� delete���� �Ű� ���� ����

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
	 * �־��� genre_id�� �ش��ϴ� �帣�� �����ͺ��̽����� ã�� Genre ������ Ŭ������ �����Ͽ� ��ȯ.
	 */
	public Genre findGenre(String genreId) throws SQLException {
		String sql = "SELECT name " + "FROM Genre " + "WHERE genre_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { genreId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) { // �л� ���� �߰�
				Genre genre = new Genre(Integer.parseInt(genreId), rs.getString("name"));
				return genre;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * ��ü �帣 ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Genre> findGenreList() throws SQLException {
		String sql = "SELECT genre_id, name " + "FROM Genre " + "ORDER BY genre_id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<Genre> genreList = new ArrayList<Genre>(); // Game���� ����Ʈ ����
			while (rs.next()) {
				Genre genre = new Genre( // Game ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("genre_id"), rs.getString("name"));
				genreList.add(genre); // List�� Game ��ü ����
			}
			return genreList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * �־��� genre_id�� �ش��ϴ� ������ �����ϴ��� �˻�
	 */
	public boolean existingGenre(String genreId) throws SQLException {
		String sql = "SELECT count(*) FROM Genre WHERE genre_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { genreId }); // JDBCUtil�� query���� �Ű� ���� ����

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
