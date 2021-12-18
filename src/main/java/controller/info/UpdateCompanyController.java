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
import model.Company;
import model.Interests;
import model.dao.CompanyDAO;
import model.dao.InterestsDAO;
import model.service.UserManager;
import model.User;

public class UpdateCompanyController implements Controller {
	private CompanyDAO companyDAO = new CompanyDAO();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/main";
		}

		HttpSession session = request.getSession();
		String companyId = UserSessionUtils.getLoginUserId(session);
		Company originCompany = companyDAO.findCompany(companyId);

		Company updateCompany = new Company(originCompany.getCompanyId(), request.getParameter("id"),
				request.getParameter("password"), request.getParameter("email"), request.getParameter("name"),
				request.getParameter("phone_number"));
		try {
			companyDAO.update(updateCompany);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/viewUpdateUser";
		}

		return "redirect:/mypage";
	}
}