package controller.game;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.info.DeleteUserController;
import controller.info.UserSessionUtils;
import model.dao.CompanyDAO;
import model.dao.GameDAO;

public class DeleteGameController implements Controller {
	
	GameDAO gameDAO = new GameDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String gameId = req.getParameter("gameId");
		try {
			gameDAO.remove(gameId);
		}catch(Exception e) {
			return "redirect:/companyMypage";
		}
		
		return "redirect:/companyMypage";
	}
}