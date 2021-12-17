package controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Game;
import model.User;
import model.Interests;
import model.Genre;
import model.Reservation;
import model.dao.UserDAO;
import model.dao.InterestsDAO;
import model.dao.GenreDAO;
import model.dao.ReservationDAO;

public class ViewMypageController implements Controller {

	private UserDAO userDAO = new UserDAO();
	private InterestsDAO interestDAO = new InterestsDAO();
	private GenreDAO genreDAO = new GenreDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 로그인 여부 
//		if (!UserSessionUtils.hasLogined(req.getSession())) { // 로그인 안되어있는 있는 경우
//			return "redirect:/main";	
//		} 
		
		String session_Id = UserSessionUtils.getLoginUserId(req.getSession());
		User user = userDAO.findUser(session_Id);
		int user_id = user.getUserId();
		
		List<Interests> findUserInterestsList = interestDAO.findUserInterestsList(user_id);
		List<Genre> findGenreList = genreDAO.findGenreList();
		//List<Game>

//		HttpSession session = req.getSession();
//		String userID = UserSessionUtils.getLoginUserId(session);
		//int userId = Integer.parseInt(req.getParameter("userId"));
		//User findUserList = userDAO.findUser(userID);
		req.setAttribute("findUser", user);
		req.setAttribute("findUserInterestsList", findUserInterestsList);
		req.setAttribute("findGenreList", findGenreList);
		
		return "/mypage.jsp";
	}

}
