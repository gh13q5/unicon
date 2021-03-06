package controller.info;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.dao.UserDAO;
import model.User;
import model.Interests;
import model.dao.InterestsDAO;
import model.service.UserManager;
import model.User;

public class UpdateUserController implements Controller {
	private UserDAO userDAO = new UserDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/main";
		}

		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		User originUser = userDAO.findUser(userId);

		User updateUser = new User(originUser.getUserId(), request.getParameter("id"), request.getParameter("password"),
				request.getParameter("email"), request.getParameter("name"), request.getParameter("phone_number"),
				originUser.getBirthDay(), Integer.parseInt(request.getParameter("gender")), originUser.getPoint());
		try {
			userDAO.update(userId, updateUser);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/viewUpdateUser";
		}

		return "redirect:/mypage";
	}
}