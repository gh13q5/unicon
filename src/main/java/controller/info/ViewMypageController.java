package controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.User;
import model.dao.UserDAO;

public class ViewMypageController implements Controller {

	private UserDAO userDAO = new UserDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// �α��� ���� 
//		if (!UserSessionUtils.hasLogined(req.getSession())) { // �α��� �ȵǾ��ִ� �ִ� ���
//			return "redirect:/main";	
//		} 

		HttpSession session = req.getSession();
		String userID = UserSessionUtils.getLoginUserId(session);
		
		//int userId = Integer.parseInt(req.getParameter("userId"));
		User findUserList = userDAO.findUser(userID);
		req.setAttribute("findUser", findUserList);
		return "/mypage.jsp";
	}

}
