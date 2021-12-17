package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 * �궗�슜�옄 愿�由щ�� �쐞�빐 �뜲�씠�꽣踰좎씠�뒪 �옉�뾽�쓣 �쟾�떞�븯�뒗 DAO �겢�옒�뒪
 * USERINFO �뀒�씠釉붿뿉 �궗�슜�옄 �젙蹂대�� 異붽�, �닔�젙, �궘�젣, 寃��깋 �닔�뻾 
 */
public class UserDAO {
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 媛앹껜 �깮�꽦
	}
		
	/**
	 * �궗�슜�옄 愿�由� �뀒�씠釉붿뿉 �깉濡쒖슫 �궗�슜�옄 �깮�꽦.
	 */
	public int create(User user) throws SQLException {
		String sql = "INSERT INTO COMMONUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(),user.getId(), user.getPassword(), 
						user.getEmail(), user.getName(), user.getPhone_number(), user.getBirthDay(), user.getGender(), 
						user.getPoint()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;			
	}

	/**
	 * 湲곗〈�쓽 �궗�슜�옄 �젙蹂대�� �닔�젙.
	 */
	public int update(String id, User user) throws SQLException {
		String sql = "UPDATE COMMONUSER "
					+ "SET id=?, password=?, email=?, name=?, phone_number=?, birthday=?, gender=? "
					+ "WHERE id=?";
		Object[] param = new Object[] {user.getId(), user.getPassword(), user.getEmail(), 
					user.getName(), user.getPhone_number(), 
					user.getBirthDay(), user.getGender(), id };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}

	/**
	 * �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄瑜� �궘�젣.
	 */
	public int remove(String id) throws SQLException {
		String sql = "DELETE FROM COMMONUSER WHERE id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}

	/**
	 * 二쇱뼱吏� �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄 �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 User �룄硫붿씤 �겢�옒�뒪�뿉 
	 * ���옣�븯�뿬 諛섑솚.
	 */
	public User findUser(String id) throws SQLException {
        String sql = "SELECT user_id, password, email, name, phone_number, birthday, gender, point "
        			+ "FROM COMMONUSER "
        			+ "WHERE id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {						// �븰�깮 �젙蹂� 諛쒓껄
				User user = new User(		// User 媛앹껜瑜� �깮�꽦�븯�뿬 �븰�깮 �젙蹂대�� ���옣
						rs.getInt("user_id"),
						id,
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("name"),
					rs.getString("phone_number"),
					rs.getDate("birthday"),					
					rs.getInt("gender"),
					rs.getInt("point"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}

//	/**
//	 * �쟾泥� �궗�슜�옄 �젙蹂대�� 寃��깋�븯�뿬 List�뿉 ���옣 諛� 諛섑솚
//	 */
	public List<User> findUserList(int userId) throws SQLException {
        String sql = " SELECT user_Id, password, email, name, phone_number, birthday, gender, point " 
        		   + " FROM COMMONUSER " + " WHERE user_Id=? ";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�뿉 query臾� �꽕�젙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
			List<User> userList = new ArrayList<User>();	// User�뱾�쓽 由ъ뒪�듃 �깮�꽦
			while (rs.next()) {
				User user = new User(			// User 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
					rs.getInt("userId"),
					rs.getString("id"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("name"),
					rs.getString("phone_number"),
					rs.getDate("birthday"),
					rs.getInt("gender"),
					rs.getInt("point"));
				userList.add(user);				// List�뿉 User 媛앹껜 ���옣
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
//	/**
//	 * �쟾泥� �궗�슜�옄 �젙蹂대�� 寃��깋�븳 �썑 �쁽�옱 �럹�씠吏��� �럹�씠吏��떦 異쒕젰�븷 �궗�슜�옄 �닔瑜� �씠�슜�븯�뿬
//	 * �빐�떦�븯�뒗 �궗�슜�옄 �젙蹂대쭔�쓣 List�뿉 ���옣�븯�뿬 諛섑솚.
//	 */
//	public List<User> findUserList(int currentPage, int countPerPage) throws SQLException {
//		String sql = "SELECT userId, name, email, NVL(commId, 0), cName " 
//					+ "FROM USERINFO u LEFT OUTER JOIN Community c ON u.commId = c.cId "
//					+ "ORDER BY userId";
//		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�뿉 query臾� �꽕�젙
//				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 媛��뒫
//				ResultSet.CONCUR_READ_ONLY);						
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();				// query �떎�뻾			
//			int start = ((currentPage-1) * countPerPage) + 1;	// 異쒕젰�쓣 �떆�옉�븷 �뻾 踰덊샇 怨꾩궛
//			if ((start >= 0) && rs.absolute(start)) {			// 而ㅼ꽌瑜� �떆�옉 �뻾�쑝濡� �씠�룞
//				List<User> userList = new ArrayList<User>();	// User�뱾�쓽 由ъ뒪�듃 �깮�꽦
//				do {
//					User user = new User(			// User 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
//						rs.getString("userId"),
//						null,
//						rs.getString("name"),
//						rs.getString("email"),
//						null,
//						rs.getInt("commId"),
//						rs.getString("cName"));
//					userList.add(user);							// 由ъ뒪�듃�뿉 User 媛앹껜 ���옣
//				} while ((rs.next()) && (--countPerPage > 0));		
//				return userList;							
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 諛섑솚
//		}
//		return null;
//	}

//	/**
//	 * �듅�젙 而ㅻ�ㅻ땲�떚�뿉 �냽�븳 �궗�슜�옄�뱾�쓣 寃��깋�븯�뿬 List�뿉 ���옣 諛� 諛섑솚
//	 */
//	public List<User> findUsersInCommunity(int communityId) throws SQLException {
//        String sql = "SELECT userId, name, email, phone FROM UserInfo "
//     				+ "WHERE commId = ?";                         
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
//			List<User> memList = new ArrayList<User>();	// member�뱾�쓽 由ъ뒪�듃 �깮�꽦
//			while (rs.next()) {
//				User member = new User(			// User 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
//					rs.getString("userId"),
//					rs.getString("name"),
//					rs.getString("email"),
//					rs.getString("phone"));
//				memList.add(member);			// List�뿉 Community 媛앹껜 ���옣
//			}		
//			return memList;					
//				
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 諛섑솚
//		}
//		return null;
//	}
//	
//	/**
//	 * �듅�젙 而ㅻ�ㅻ땲�떚�뿉 �냽�븳 �궗�슜�옄�뱾�쓽 �닔瑜� count�븯�뿬 諛섑솚
//	 */
//	public int getNumberOfUsersInCommunity(int communityId) {
//		String sql = "SELECT COUNT(userId) FROM UserInfo "
//     				+ "WHERE commId = ?";              
//		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
//			rs.next();										
//			return rs.getInt(1);			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 諛섑솚
//		}
//		return 0;
//	}
	
	/**
	 * 二쇱뼱吏� �궗�슜�옄 ID�뿉 �빐�떦�븯�뒗 �궗�슜�옄媛� 議댁옱�븯�뒗吏� 寃��궗 
	 */
	public boolean existingUser(int i) throws SQLException {
		String sql = "SELECT count(*) FROM COMMONUSER WHERE user_Id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {i});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return false;
	}

}
