package controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.User;
import model.Interests;
import model.Genre;
import model.Reservation;
import model.dao.UserDAO;
import model.dao.InterestsDAO;
import model.dao.CompanyDAO;
import model.dao.GameDAO;
import model.dao.GenreDAO;
import model.dao.ReservationDAO;

public class ViewCompanyMyPageController implements Controller {

	private GameDAO gameDAO = new GameDAO();
	private CompanyDAO companyDAO = new CompanyDAO();
	private UserDAO userDAO = new UserDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setAttribute("userType", "false");
		req.setAttribute("userObj", "false");

		String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

		Company company = companyDAO.findCompany(session_Id);
		int companyId = company.getCompanyId();
		req.setAttribute("userType", "company");
		req.setAttribute("userObj", company);

		List<Game> uploadGameList = gameDAO.findGameListByCompanyId(String.valueOf(companyId));

		req.setAttribute("company", company);
		req.setAttribute("uploadGameList", uploadGameList);

		return "/companyMypage.jsp";
	}

}
