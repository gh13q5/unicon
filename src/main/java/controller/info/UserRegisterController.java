package controller.info;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.dao.UserDAO;
import model.Interests;
import model.dao.InterestsDAO;

import model.service.ExistingUserException;

public class UserRegisterController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UserRegisterController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 諛쏆� �궇吏쒕�� date濡� 蹂��솚�븯�뒗 �븿�닔
		SimpleDateFormat userDate = new SimpleDateFormat("yyyy-MM-dd");

		// POST request (�쉶�썝�젙蹂닿� parameter濡� �쟾�넚�맖)
		User user = new User(Integer.parseInt(request.getParameter("gender")), request.getParameter("Id"),
				request.getParameter("password"),
				request.getParameter("email"), request.getParameter("name"), request.getParameter("phone_number"),
				userDate.parse(request.getParameter("birthDay")), Integer.parseInt(request.getParameter("gender")), 0);
		

		log.debug("Create User : {}", user);

		try {
			if(request.getParameter("password") != request.getParameter("passwordCheck")) {
				return "redirect:/userRegisterForm.jsp";
			}
			create(user);
			return "redirect:/welcome.jsp"; // �꽦怨� �떆 �궗�슜�옄 由ъ뒪�듃 �솕硫댁쑝濡� redirect

		} catch (ExistingUserException e) { // �삁�쇅 諛쒖깮 �떆 �쉶�썝媛��엯 form�쑝濡� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "redirect:/userRegisterForm.jsp";
		}
	}

	public int create(User user) throws SQLException, ExistingUserException {

		UserDAO userManager = new UserDAO();

		if (userManager.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "is aleady exist.");
		}
		return userManager.create(user);
	}

}
