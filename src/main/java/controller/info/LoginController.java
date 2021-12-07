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
			
			//usermanager �Ⱦ��� �α���
			login(id, password);
	
			// ���ǿ� ����� ���̵� ����
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, id);
            
            return "redirect:/main.jsp";
            
		} catch (Exception e) { // �������

			request.setAttribute("����", e);
            return "/main.jsp";			
		}	
    }
	public boolean login(String id, String password) throws SQLException, UserNotFoundException, PasswordMismatchException {
		
			UserDAO userManager = new UserDAO();
			CompanyDAO companyManager = new CompanyDAO();
			
			if(userManager.findUser(id) != null) { //�Ϲ��������� �˾ƺ���
				
				User userId = userManager.findUser(id);
				
				if (!userId.matchPassword(password)) {
					throw new PasswordMismatchException("password incorrect!");
				}
				
			} else if(companyManager.findCompany(id) != null) { //ȭ���������� �˾ƺ���
				
				Company companyId = companyManager.findCompany(id);
				
				if (!companyId.matchPassword(password)) {
					throw new PasswordMismatchException("password incorrect!");
				}
				
			} else {
				throw new UserNotFoundException("id unexist");
			}
			return true;
		}
}