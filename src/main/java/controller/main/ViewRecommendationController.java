package controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.dao.GameDAO;
public class ViewRecommendationController implements Controller{
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	        
		 
		 return "/main.jsp";
	    }
}