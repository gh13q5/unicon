package controller.game;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;


public class ViewEditGameController implements Controller{
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 String companyId = request.getParameter("companyId");
		 request.setAttribute("companyId", companyId);
		 return "/editGame.jsp";
	    }
}
