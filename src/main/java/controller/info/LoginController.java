package controller.info; 

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.PasswordMismatchException;
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
			
			//usermanager 안쓰고 로그인
			login(id, password);
	
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, id);
            
            return "redirect:/main.jsp";
            
		} catch (Exception e) { // 오류잡기

			request.setAttribute("오류", e);
            return "/main.jsp";			
		}	
    }
	public boolean login(String id, String password) throws SQLException, UserNotFoundException, PasswordMismatchException {
		
			if(UserDAO.findUser(id) != null) { //일반유저인지 알아보기
				
				User userId = UserDAO.findUser(id);
				
				if (!userId.matchPassword(password)) {
					throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
				}
				
			} else if(CompanyDAO.findCompany(id) != null) { //화사유저인지 알아보기
				
				Company companyId = CompanyDAO.findCompany(id);
				
				if (!companyId.matchPassword(password)) {
					throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
				}
				
			} else {
				throw new UserNotFoundException("입력하신 아이디는 존재하지 않습니다.");
			}
			return true;
		}
}
