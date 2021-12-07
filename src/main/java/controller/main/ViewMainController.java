package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.dao.GameDAO;
public class ViewMainController implements Controller{
	private GameDAO gameDAO = new GameDAO();
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 List<Game> entireGameList = gameDAO.findGameList();
		 req.setAttribute("entireGameList", entireGameList);
		 
	        return "/main.jsp";
	    }
}