package controller.info;

import java.sql.Date;
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
import model.service.UserManager;

public class UserRegisterController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UserRegisterController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 獄쏆룇占� 占쎄텊筌욎뮆占쏙옙 date嚥∽옙 癰귨옙占쎌넎占쎈릭占쎈뮉 占쎈맙占쎈땾
		SimpleDateFormat userDate = new SimpleDateFormat("yyyy-MM-dd");
		String value = request.getParameter("birthDay");
		java.util.Date date = userDate.parse(value);
		String formattedStartDate = userDate.format(date);
		java.sql.Date Rdate = java.sql.Date.valueOf(formattedStartDate);

		// POST request (占쎌돳占쎌뜚占쎌젟癰귣떯占� parameter嚥∽옙 占쎌읈占쎈꽊占쎈쭡)
	
		User user = new User( request.getParameter("id"),
				request.getParameter("password"),
				request.getParameter("email"), request.getParameter("name"), request.getParameter("phone_number"),
				Rdate, Integer.parseInt(request.getParameter("gender")), 0);
		

		log.debug("Create User : {}", user);

		try {
			UserManager userManager = UserManager.getInstance();
			userManager.create(user);
			return "redirect:/welcome";
		} catch (ExistingUserException e) { // 占쎌굙占쎌뇚 獄쏆뮇源� 占쎈뻻 占쎌돳占쎌뜚揶쏉옙占쎌뿯 form占쎌몵嚥∽옙 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "redirect:/userregister";
		}
	}
}
