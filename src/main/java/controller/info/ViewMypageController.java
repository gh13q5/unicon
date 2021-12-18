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

public class ViewMypageController implements Controller {

	private UserDAO userDAO = new UserDAO();
	private InterestsDAO interestDAO = new InterestsDAO();
	private GenreDAO genreDAO = new GenreDAO();
	private CompanyDAO companyDAO = new CompanyDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setAttribute("userType", "false");
		req.setAttribute("userObj", "false");
		
		String session_Id = UserSessionUtils.getLoginUserId(req.getSession());
		
		// Company User인 경우, company Mypage로 redirect
		if(companyDAO.existingCompany(session_Id)) {
			Company company = companyDAO.findCompany(session_Id);
			return "redirect:/companyMypage";
		}
		
			User user = userDAO.findUser(session_Id);
			int user_id = user.getUserId();
			
			req.setAttribute("userObj", user);
			req.setAttribute("userType", "user");
			
			List<Interests> findUserInterestsList = interestDAO.findUserInterestsList(user_id);
			List<Genre> findGenreList = genreDAO.findGenreList();
			List<Game> findReservationListByUserId = reservationDAO.findReservationListByUserId(user_id);
		
			req.setAttribute("findUser", user);
			req.setAttribute("findUserInterestsList", findUserInterestsList);
			req.setAttribute("findGenreList", findGenreList);
			req.setAttribute("findrv", findReservationListByUserId);

		return "/mypage.jsp";
	}

}
