package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.Genre;
import model.User;
import model.dao.CompanyDAO;
import model.dao.GameDAO;
import model.dao.GenreDAO;
import model.dao.UserDAO;

public class ViewCategoryController implements Controller{ 
	
	private GameDAO gameDAO = new GameDAO();
	private GenreDAO genreDAO = new GenreDAO();
	private CompanyDAO companyDAO = new CompanyDAO();
	private UserDAO userDAO = new UserDAO();
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 HttpSession session = req.getSession();
		 req.setAttribute("userType", "false");
		 
		 if (UserSessionUtils.hasLogined(req.getSession())) {
				// HttpSession session = req.getSession();
				String userID = UserSessionUtils.getLoginUserId(session);

				// company User
				if (companyDAO.existingCompany(userID)) {
					Company company = companyDAO.findCompany(userID);
					req.setAttribute("userType", "company");
					req.setAttribute("userObj", company);
				}
				else {
					User user = userDAO.findUser(userID); // 사용자 정보 검색
					// String nickName = user.getName();
					req.setAttribute("userObj", user);
					req.setAttribute("userType", "user");
				}
		 }
		 
		 String category = req.getParameter("category");
		 
		 Genre genre = genreDAO.findGenre(category);
		 
		 //String order = req.getParameter("order");
		 List<Game> categoryGameList = gameDAO.categoryGameList(category);
		 List<Game> endCategoryGameList = gameDAO.endCategoryGameList(category);
		 
		 req.setAttribute("genre", genre);
		 
		 req.setAttribute("categoryGameList", categoryGameList);
		 req.setAttribute("endGameList",endCategoryGameList);
		 
		 return "/category.jsp";
	    }
}