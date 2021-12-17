package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.Game;
import model.dao.GameDAO;
public class SearchController implements Controller{
	private GameDAO gameDAO = new GameDAO();
	//private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		 	String keyWord = req.getParameter("keyWord");
		 	//keyWord = new String(keyWord.getBytes("ISO8859_1"), "UTF-8");
		 	List<Game> searchGameList = gameDAO.searchGameList(keyWord);
		 	List<Game> endsearchGameList = gameDAO.endsearchGameList(keyWord);
		 	req.setAttribute("searchGameList", searchGameList);
		 	//logger.debug(searchGameList.toString());
		 	req.setAttribute("endsearchGameList", endsearchGameList);
	        return "/searchResult.jsp";
	    }
}