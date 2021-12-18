package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 * 占쎄텢占쎌뒠占쎌쁽 �꽴占썹뵳�됵옙占� 占쎌맄占쎈퉸 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞 占쎌삂占쎈씜占쎌뱽 占쎌읈占쎈뼖占쎈릭占쎈뮉 DAO
 * 占쎄깻占쎌삋占쎈뮞 USERINFO 占쎈�믭옙�뵠�뇡遺용퓠 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 �빊遺쏙옙, 占쎈땾占쎌젟, 占쎄텣占쎌젫,
 * 野껓옙占쎄퉳 占쎈땾占쎈뻬
 */
public class UserDAO {
	private JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}

	/**
	 * 占쎄텢占쎌뒠占쎌쁽 �꽴占썹뵳占� 占쎈�믭옙�뵠�뇡遺용퓠 占쎄퉱嚥≪뮇�뒲 占쎄텢占쎌뒠占쎌쁽 占쎄문占쎄쉐.
	 */
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO COMMONUSER " + "(id, password, email, name, phone_number, birthday, gender, point) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getId(), user.getPassword(), user.getEmail(), user.getName(),
				user.getPhone_number(), user.getBirthDay(), user.getGender(), user.getPoint() };
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

	/**
	 * 疫꿸퀣�덌옙�벥 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 占쎈땾占쎌젟.
	 */
	public int update(String id, User user) throws SQLException {
		String sql = "UPDATE COMMONUSER "
				+ "SET id=?, password=?, email=?, name=?, phone_number=?, birthday=?, gender=? " + "WHERE id=?";
		Object[] param = new Object[] { user.getId(), user.getPassword(), user.getEmail(), user.getName(),
				user.getPhone_number(), user.getBirthDay(), user.getGender(), id };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			int result = jdbcUtil.executeUpdate(); // update �눧占� 占쎈뼄占쎈뻬
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

	/**
	 * 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽�몴占� 占쎄텣占쎌젫.
	 */
	public int remove(String id) throws SQLException {
		String sql = "DELETE FROM COMMONUSER WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

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

	/**
	 * 雅뚯눘堉깍쭪占� 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙
	 * 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎈퓠占쎄퐣 筌≪뼚釉� User 占쎈즲筌롫뗄�뵥 占쎄깻占쎌삋占쎈뮞占쎈퓠 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
	 */
	public User findUser(String id) throws SQLException {
		String sql = "SELECT user_id, password, email, name, phone_number, birthday, gender, point "
				+ "FROM COMMONUSER " + "WHERE id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			if (rs.next()) { // 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				User user = new User( // User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getInt("user_id"), id, rs.getString("password"), rs.getString("email"), rs.getString("name"),
						rs.getString("phone_number"), rs.getDate("birthday"), rs.getInt("gender"), rs.getInt("point"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return null;
	}

	public User findUserByUserId(String userId) throws SQLException {
		String sql = "SELECT id, password, email, name, phone_number, birthday, gender, point " + "FROM COMMONUSER "
				+ "WHERE user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			if (rs.next()) { // 占쎈린占쎄문 占쎌젟癰귨옙 獄쏆뮄猿�
				User user = new User( // User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						Integer.parseInt(userId), rs.getString("id"), rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"), rs.getDate("birthday"), rs.getInt("gender"),
						rs.getInt("point"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return null;
	}

//	/**
//	 * 占쎌읈筌ｏ옙 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈릭占쎈연 List占쎈퓠 占쏙옙占쎌삢 獄쏉옙 獄쏆꼹�넎
//	 */
	public List<User> findUserList(int userId) throws SQLException {
		String sql = " SELECT id, password, email, name, phone_number, birthday, gender, point " + " FROM COMMONUSER "
				+ " WHERE user_Id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			List<User> userList = new ArrayList<User>(); // User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				User user = new User( // User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						userId, rs.getString("id"), rs.getString("password"), rs.getString("email"),
						rs.getString("name"), rs.getString("phone_number"), rs.getDate("birthday"), rs.getInt("gender"),
						rs.getInt("point"));
				userList.add(user); // List占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
			}
			return userList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹�넎
		}
		return null;
	}

//	/**
//	 * 占쎌읈筌ｏ옙 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈립 占쎌뜎 占쎌겱占쎌삺 占쎈읂占쎌뵠筌욑옙占쏙옙 占쎈읂占쎌뵠筌욑옙占쎈뼣 �빊�뮆�젾占쎈막 占쎄텢占쎌뒠占쎌쁽 占쎈땾�몴占� 占쎌뵠占쎌뒠占쎈릭占쎈연
//	 * 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귣�彛뷂옙�뱽 List占쎈퓠 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
//	 */
//	public List<User> findUserList(int currentPage, int countPerPage) throws SQLException {
//		String sql = "SELECT userId, name, email, NVL(commId, 0), cName " 
//					+ "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
//					+ "ORDER BY userId";
//		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
//				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 揶쏉옙占쎈뮟
//				ResultSet.CONCUR_READ_ONLY);						
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();				// query 占쎈뼄占쎈뻬			
//			int start = ((currentPage-1) * countPerPage) + 1;	// �빊�뮆�젾占쎌뱽 占쎈뻻占쎌삂占쎈막 占쎈뻬 甕곕뜇�깈 �④쑴沅�
//			if ((start >= 0) && rs.absolute(start)) {			// �뚣끉苑뚨몴占� 占쎈뻻占쎌삂 占쎈뻬占쎌몵嚥∽옙 占쎌뵠占쎈짗
//				List<User> userList = new ArrayList<User>();	// User占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
//				do {
//					User user = new User(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
//						rs.getString("userId"),
//						null,
//						rs.getString("name"),
//						rs.getString("email"),
//						null,
//						rs.getInt("commId"),
//						rs.getString("cName"));
//					userList.add(user);							// �뵳�딅뮞占쎈뱜占쎈퓠 User 揶쏆빘猿� 占쏙옙占쎌삢
//				} while ((rs.next()) && (--countPerPage > 0));		
//				return userList;							
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 獄쏆꼹�넎
//		}
//		return null;
//	}

//	/**
//	 * 占쎈뱟占쎌젟 �뚣끇占썬끇�빍占쎈뼒占쎈퓠 占쎈꺗占쎈립 占쎄텢占쎌뒠占쎌쁽占쎈굶占쎌뱽 野껓옙占쎄퉳占쎈릭占쎈연 List占쎈퓠 占쏙옙占쎌삢 獄쏉옙 獄쏆꼹�넎
//	 */
//	public List<User> findUsersInCommunity(int communityId) throws SQLException {
//        String sql = "SELECT userId, name, email, phone FROM UserInfo "
//     				+ "WHERE commId = ?";                         
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
//			List<User> memList = new ArrayList<User>();	// member占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
//			while (rs.next()) {
//				User member = new User(			// User 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
//					rs.getString("userId"),
//					rs.getString("name"),
//					rs.getString("email"),
//					rs.getString("phone"));
//				memList.add(member);			// List占쎈퓠 Community 揶쏆빘猿� 占쏙옙占쎌삢
//			}		
//			return memList;					
//				
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 獄쏆꼹�넎
//		}
//		return null;
//	}
//	
//	/**
//	 * 占쎈뱟占쎌젟 �뚣끇占썬끇�빍占쎈뼒占쎈퓠 占쎈꺗占쎈립 占쎄텢占쎌뒠占쎌쁽占쎈굶占쎌벥 占쎈땾�몴占� count占쎈릭占쎈연 獄쏆꼹�넎
//	 */
//	public int getNumberOfUsersInCommunity(int communityId) {
//		String sql = "SELECT COUNT(userId) FROM UserInfo "
//     				+ "WHERE commId = ?";              
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
//			rs.next();										
//			return rs.getInt(1);			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 獄쏆꼹�넎
//		}
//		return 0;
//	}

	/**
	 * 雅뚯눘堉깍쭪占� 占쎄텢占쎌뒠占쎌쁽 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄텢占쎌뒠占쎌쁽揶쏉옙 鈺곕똻�삺占쎈릭占쎈뮉筌욑옙 野껓옙占쎄텢
	 */
	public boolean existingUser(String id) throws SQLException {
		String sql = "SELECT count(*) FROM COMMONUSER WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
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
