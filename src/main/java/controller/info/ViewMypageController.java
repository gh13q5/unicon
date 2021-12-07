package controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.dao.UserDAO;

public class ViewMypageController implements Controller {
	
	private UserDAO userDAO = new UserDAO();
	@Override
		public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
			int userId = Integer.parseInt(req.getParameter("userId"));
			List<User> findUserList = userDAO.findUserList(userId);
			req.setAttribute("findUserList", findUserList);
			return "/mypage.jsp";
	}
	

}
