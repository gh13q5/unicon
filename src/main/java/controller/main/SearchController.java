package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.User;
import model.dao.CompanyDAO;
import model.dao.GameDAO;
import model.dao.UserDAO;

public class SearchController implements Controller {
	private GameDAO gameDAO = new GameDAO();
	private CompanyDAO companyDAO = new CompanyDAO();
	private UserDAO userDAO = new UserDAO();

	// private static final Logger logger =
	// LoggerFactory.getLogger(SearchController.class);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setAttribute("isLogin", "false");
		req.setAttribute("userType", "false");
		req.setAttribute("userObj", "false");

		// 로그인 여부 확인
		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

			if (companyDAO.existingCompany(session_Id)) {
				Company company = companyDAO.findCompany(session_Id);
				req.setAttribute("userType", "company");
				req.setAttribute("userObj", company);
			} else {
				User user = userDAO.findUser(session_Id);
				req.setAttribute("userType", "false");
				req.setAttribute("userObj", user);
			}
		}

		String keyWord = req.getParameter("keyWord");
		// keyWord = new String(keyWord.getBytes("ISO8859_1"), "UTF-8");
		List<Game> searchGameList = gameDAO.searchGameList(keyWord);
		List<Game> endsearchGameList = gameDAO.endsearchGameList(keyWord);

		req.setAttribute("keyword", keyWord);
		req.setAttribute("searchGameList", searchGameList);
		// logger.debug(searchGameList.toString());
		req.setAttribute("endsearchGameList", endsearchGameList);
		return "/searchResult.jsp";
	}
}