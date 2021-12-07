package controller.info;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Company;
import model.dao.CompanyDAO;
import model.service.ExistingUserException;


public class CompanyRegisterController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CompanyRegisterController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	
     // POST request (회원정보가 parameter로 전송됨)
       	Company company = new Company(
			request.getParameter("companyId"),
			request.getParameter("Id"),
			request.getParameter("password"),
			request.getParameter("email"),
			request.getParameter("name"),
			request.getParameter("phone_number"));
		
        log.debug("Create User : {}", company);

		try {
			create(company);
	        return "/welcome.jsp";	// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
           request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("company", company);
			return "/companyRegisterForm.jsp";
		}
    }
    public int create(Company company) throws SQLException, ExistingUserException {
    	
    	CompanyDAO companyManager = new CompanyDAO();
    	
		if (companyManager.existingCompany(company.getCompanyId()) == true) {
			throw new ExistingUserException(company.getCompanyId() + "is aleady exist.");
		}
		return companyManager.create(company);
	}
}