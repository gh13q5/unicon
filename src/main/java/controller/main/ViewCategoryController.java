package controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ViewCategoryController implements Controller{
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	        return "url ¾²±â";
	    }
}