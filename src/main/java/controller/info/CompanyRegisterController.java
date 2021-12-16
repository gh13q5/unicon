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
import model.service.PasswordMismatchException;


public class CompanyRegisterController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CompanyRegisterController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	
     // POST request (회원정보가 parameter로 전송됨)
       	Company company = new Company(
			0,
			request.getParameter("id"),
			request.getParameter("password"),
			request.getParameter("email"),
			request.getParameter("name"),
			request.getParameter("phone_number"));
       	
		
        log.debug("Create Company : {}", company);

		try {
			if(request.getParameter("password") != request.getParameter("passwordCheck") || request.getParameter("inputTermsYes") != "Y") {
				return "redirect:/companyRegisterForm.jsp";
			}
			create(company);
	        return "redirect:/welcome.jsp";	// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
           request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("company", company);
			return "redirect:/companyRegisterForm.jsp";
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
