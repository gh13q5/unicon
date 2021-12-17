package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Community;
import model.User;
import model.Company;
import model.dao.CommunityDAO;
import model.dao.UserDAO;
import model.dao.CompanyDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class CompanyManager {
	private static CompanyManager compMan = new CompanyManager();
	private CompanyDAO companyDAO;
	//private CommunityDAO commDAO;
	private UserAnalysis userAanlysis;

	private CompanyManager() {
		try {
			companyDAO = new CompanyDAO();
			//commDAO = new CommunityDAO();
			//userAanlysis = new UserAnalysis(companyDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static CompanyManager getInstance() {
		return compMan;
	}
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (companyDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return companyDAO.create(user);
	}

//	public int update(User user) throws SQLException, UserNotFoundException {
//		int oldCommId = findUser(user.getUserId()).getCommId();
//		if (user.getCommId() != oldCommId) { 	// �Ҽ� Ŀ��Ƽ�ϰ� �����
//			Community comm = commDAO.findCommunity(oldCommId);  // ���� �Ҽ� Ŀ�´�Ƽ
//			if (comm != null && user.getUserId().equals(comm.getChairId())) {
//				// ����ڰ� ���� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
//				comm.setChairId(null);
//				commDAO.updateChair(comm);
//			}
//		}
//		return userDAO.update(user);
//	}	

//	public int remove(String userId) throws SQLException, UserNotFoundException {
//		int commId = findUser(userId).getCommId();
//		Community comm = commDAO.findCommunity(commId);  // �Ҽ� Ŀ�´�Ƽ
//		if (comm != null && userId.equals(comm.getChairId())) {
//			// ����ڰ� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
//			comm.setChairId(null);
//			commDAO.updateChair(comm);
//		}
//		return userDAO.remove(userId);
//	}

	public Company findUser(String userId)
		throws SQLException, UserNotFoundException {
		Company company = companyDAO.findCompany(userId);
		
		if (company == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return company;
	}

	public List<User> findUserList() throws SQLException {
			return companyDAO.findUserList();
	}
	
	public List<User> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return companyDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		Company comm = findUser(userId);
		
//		UserDAO userDAO = new UserDAO();
//		User user = userDAO.findUser(userId);

		if (!comm.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	public List<User> makeFriends(String userId) throws Exception {
		return userAanlysis.recommendFriends(userId);
	}
	
	public Community createCommunity(Community comm) throws SQLException {
		return commDAO.create(comm);		
	}

	public int updateCommunity(Community comm) throws SQLException {
		return commDAO.update(comm);				
	}
	
	public Community findCommunity(int commId) throws SQLException {
		Community comm = commDAO.findCommunity(commId); 
		
		List<User> memberList = companyDAO.findUsersInCommunity(commId);
		comm.setMemberList(memberList);
		
		int numOfMembers = companyDAO.getNumberOfUsersInCommunity(commId);
		comm.setNumOfMembers(numOfMembers);
		return comm;
	}
	
	public List<Community> findCommunityList() throws SQLException {
		return commDAO.findCommunityList();
	}
	
	public List<User> findCommunityMembers(int commId) throws SQLException {
		return companyDAO.findUsersInCommunity(commId);
	}

	public CompanyDAO getCompanyDAO() {
		return this.companyDAO;
	}
}
