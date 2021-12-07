package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.dao.GameDAO;

public class ViewCategoryController implements Controller{ 
	
	private GameDAO gameDAO = new GameDAO();
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	     
		 String category = req.getParameter("category");
		 //String order = req.getParameter("order");
		 List<Game> categoryGameList = gameDAO.categoryGameList(category);
		 List<Game> endCategoryGameList = gameDAO.endCategoryGameList(category);
		 req.setAttribute("categoryGameList", categoryGameList);
		 req.setAttribute("endGameList",endCategoryGameList);
		 return "/category.jsp";
	    }
}