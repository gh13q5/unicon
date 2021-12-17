package controller.info; 

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.PasswordMismatchException;
import model.service.UserManager;
import model.service.UserNotFoundException;

import model.Company;
import model.User;
import model.dao.CompanyDAO;
import model.dao.UserDAO;

public class LoginController implements Controller {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			try {
				// 모델에 로그인 처리를 위임
				UserManager manager = UserManager.getInstance();
				manager.login(id, password);
		
				UserDAO userDAO = new UserDAO();
				User user = userDAO.findUser(id);
				
				// 세션에 사용자 이이디 저장
				HttpSession session = request.getSession();
	            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, String.valueOf(user.getId()));
	            session.setAttribute("userObj", user);
	            return "redirect:/main";
			} catch (Exception e) {
				/* UserNotFoundException이나 PasswordMismatchException 발생 시
				 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
				 */
	            request.setAttribute("loginFailed", true);
				request.setAttribute("exception", e);
	            return "/main.jsp";			
			}	
	    }
	}
