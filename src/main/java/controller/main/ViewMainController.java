package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Game;
import model.Genre;
import model.User;
import model.dao.GameDAO;
import model.dao.GenreDAO;
import model.dao.UserDAO;
public class ViewMainController implements Controller{ 
	private GameDAO gameDAO = new GameDAO(); 
	private UserDAO userDAO = new UserDAO();
	private GenreDAO genreDAO = new GenreDAO();
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 HttpSession session = req.getSession();
//		String userID = UserSessionUtils.getLoginUserId(session); 
		 
		 if (UserSessionUtils.hasLogined(req.getSession())) {
			 	//HttpSession session = req.getSession();
			 String userID = UserSessionUtils.getLoginUserId(session);
				User user = userDAO.findUser(userID);	// 사용자 정보 검색	
				//String nickName = user.getName();
				req.setAttribute("userObj", user);
	        }
		 
		 List<Game> entireGameList = gameDAO.findGameList();
		 List<Genre> genreList = genreDAO.findGenreList();
		 
		 req.setAttribute("entireGameList", entireGameList);
		 req.setAttribute("genreList", genreList);
		 
	        return "/main.jsp";
	    }
}