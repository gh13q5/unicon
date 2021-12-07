package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.dao.GameDAO;
public class SearchController implements Controller{
	private GameDAO gameDAO = new GameDAO();
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 	String keyWord = '%'+req.getParameter("category")+'%';
		 	List<Game> searchGameList = gameDAO.searchGameList(keyWord);
		 	List<Game> endsearchGameList = gameDAO.endsearchGameList(keyWord);
		 	req.setAttribute("searchGameList", searchGameList);
		 	req.setAttribute("endsearchGameList", endsearchGameList);
	        return "/searchResult.jsp";
	    }
}