package controller.game;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class UploadGameController implements Controller{
	 @Override
	    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	        return "redirect:/main";
	    }
}