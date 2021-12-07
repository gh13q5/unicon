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

import model.service.ExistingUserException;


public class UserRegisterController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UserRegisterController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

     //받은 날짜를 date로 변환하는 함수
       	SimpleDateFormat userDate = new SimpleDateFormat("yyyy-MM-dd");
       	
     // POST request (회원정보가 parameter로 전송됨)
       	User user = new User(
			request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("email"),
			request.getParameter("name"),
			request.getParameter("phone_number"),
			userDate.parse(request.getParameter("birthDay")),
			Integer.parseInt(request.getParameter("gender")),
			0);
		
        log.debug("Create User : {}", user);

		try {
			create(user);
	        return "/welcome.jsp";	// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/userRegisterForm.jsp";
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
