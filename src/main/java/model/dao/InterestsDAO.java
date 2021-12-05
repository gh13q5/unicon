package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Interests;

/**
 * 등록된 게임 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스 Interests 테이블에 게임 정보를 추가, 수정, 삭제, 검색 수행
 */

public class InterestsDAO {
	private JDBCUtil jdbcUtil = null;

	public InterestsDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/**
	 * Interests 테이블에 새로운 게임 생성.
	 */
	public int create(Interests interests) throws SQLException {
		String sql = "INSERT INTO Interests VALUES (?, ?, ?)";
		Object[] param = new Object[] { interests.getId(), interests.getUser_id(), interests.getGenre_id() };
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
	 * 기존의 관심사 정보를 수정.
	 */
	public int update(Interests interests) throws SQLException {
		String sql = "UPDATE Interests "
				+ "SET user_id=?, genre_id=? "
				+ "WHERE id=?";
		Object[] param = new Object[] { interests.getUser_id(), interests.getGenre_id(), interests.getId() };
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
	 * interestsId에 해당하는 관심사 삭제.
	 */
	public int remove(String interestsId) throws SQLException {
		String sql = "DELETE FROM Interests WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { interestsId }); // JDBCUtil에 delete문과 매개 변수 설정

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
	 * 주어진 interestsId에 해당하는 관심사를 데이터베이스에서 찾아 Interests 도메인 클래스에 저장하여 반환.
	 */
	public Interests findInterests(String interestsId) throws SQLException {
		String sql = "SELECT user_id, genre_id "
				+ "FROM Interests " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { interestsId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { 
				Interests interests = new Interests(
						Integer.parseInt(interestsId), rs.getInt("user_id"), rs.getInt("genre_id"));
				return interests;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 전체 관심사 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Interests> findInterestsList() throws SQLException {
		String sql = "SELECT user_id, genre_id "
				+ "FROM Interests " + "ORDER BY id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Interests> interestsList = new ArrayList<Interests>(); // Game들의 리스트 생성
			while (rs.next()) {
				Interests interests = new Interests(
						rs.getInt("id"), rs.getInt("user_id"), rs.getInt("genre_id"));
				interestsList.add(interests);
			}
			return interestsList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 주어진  interestsId에 해당하는 관심사가 존재하는지 검사
	 */
	public boolean existingInterests(String interestsId) throws SQLException {
		String sql = "SELECT count(*) FROM Interests WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { interestsId }); // JDBCUtil에 query문과 매개 변수 설정

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
