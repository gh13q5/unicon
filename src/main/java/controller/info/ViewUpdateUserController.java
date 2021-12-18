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
import model.dao.GenreDAO;
import model.dao.ReservationDAO;

public class ViewUpdateUserController implements Controller {

	private UserDAO userDAO = new UserDAO();
	private InterestsDAO interestDAO = new InterestsDAO();
	private GenreDAO genreDAO = new GenreDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

		User user = userDAO.findUser(session_Id);
		int user_id = user.getUserId();

		req.setAttribute("userObj", user);
		req.setAttribute("userType", "user");

		List<Interests> findUserInterestsList = interestDAO.findUserInterestsList(user_id);
		List<Genre> findGenreList = genreDAO.findGenreList();

		req.setAttribute("user", user);
		req.setAttribute("interestsList", findUserInterestsList);
		req.setAttribute("genreList", findGenreList);

		return "/updateUserInfo.jsp";
	}

}
