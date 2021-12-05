package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Genre;

/**
 * 등록된 게임 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스 Genre 테이블에 게임 정보를 추가, 수정, 삭제, 검색 수행
 */

public class GenreDAO {
	private JDBCUtil jdbcUtil = null;

	public GenreDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/**
	 * GENRE 테이블에 새로운 게임 생성.
	 */
	public int create(Genre genre) throws SQLException {
		String sql = "INSERT INTO Genre VALUES (?, ?)";
		Object[] param = new Object[] { genre.getGenre_id(), genre.getName() };
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
	 * 기존의 장르 정보를 수정.
	 */
	public int update(Genre genre) throws SQLException {
		String sql = "UPDATE Genre " + "SET name=? " + "WHERE genre_id=?";
		Object[] param = new Object[] { genre.getName(), genre.getGenre_id() };
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
	 * genre_id에 해당하는 장르 삭제.
	 */
	public int remove(String genreId) throws SQLException {
		String sql = "DELETE FROM Genre WHERE genre_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { genreId }); // JDBCUtil에 delete문과 매개 변수 설정

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
	 * 주어진 genre_id에 해당하는 장르를 데이터베이스에서 찾아 Genre 도메인 클래스에 저장하여 반환.
	 */
	public Genre findGenre(String genreId) throws SQLException {
		String sql = "SELECT name " + "FROM Genre " + "WHERE genre_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { genreId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				Genre genre = new Genre(Integer.parseInt(genreId), rs.getString("name"));
				return genre;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 전체 장르 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Genre> findGenreList() throws SQLException {
		String sql = "SELECT genre_id, name " + "FROM Genre " + "ORDER BY genre_id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Genre> genreList = new ArrayList<Genre>(); // Game들의 리스트 생성
			while (rs.next()) {
				Genre genre = new Genre( // Game 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("genre_id"), rs.getString("name"));
				genreList.add(genre); // List에 Game 객체 저장
			}
			return genreList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 주어진 genre_id에 해당하는 게임이 존재하는지 검사
	 */
	public boolean existingGenre(String genreId) throws SQLException {
		String sql = "SELECT count(*) FROM Genre WHERE genre_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { genreId }); // JDBCUtil에 query문과 매개 변수 설정

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
