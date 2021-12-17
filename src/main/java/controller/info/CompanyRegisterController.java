package controller.info;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Company;
import model.User;
import model.dao.CompanyDAO;
import model.service.CompanyManager;
import model.service.ExistingUserException;
import model.service.PasswordMismatchException;
import model.service.UserManager;


public class CompanyRegisterController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CompanyRegisterController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	
    

		// POST request (占쎌돳占쎌뜚占쎌젟癰귣떯占� parameter嚥∽옙 占쎌읈占쎈꽊占쎈쭡)
	
		Company company = new Company(request.getParameter("id"), request.getParameter("password"), request.getParameter("email"),
				request.getParameter("name"), request.getParameter("phone_number"));
		

		log.debug("Create User : {}", company);

		try {
			CompanyManager compManager = CompanyManager.getInstance();
			compManager.create(company);
			return "redirect:/welcome";
		} catch (ExistingUserException e) { // 占쎌굙占쎌뇚 獄쏆뮇源� 占쎈뻻 占쎌돳占쎌뜚揶쏉옙占쎌뿯 form占쎌몵嚥∽옙 forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("company", company);
			return "redirect:/companyregister";
		}
	}
}
