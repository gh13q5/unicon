package controller.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.Genre;
import model.dao.GameDAO;
import model.dao.GenreDAO;

public class ViewEditGameController implements Controller {

	GameDAO gameDAO = new GameDAO();
	GenreDAO genreDAO = new GenreDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// �α��� ���� Ȯ��
		// �Ŀ� �������� �� �ּ� Ǯ ���� -> �α��� �� ������ �ٽ� ���� ���� �������� ���ư�!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form ��û���� redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String gameId = "39";
		Game game = null;

		ArrayList<Genre> genreList = new ArrayList<>();
		
		String[] tagList = null;
		String[] imageList = null;
		String[] rewardImageList = null;

		try {
			game = gameDAO.findGame(gameId);
			genreList = (ArrayList<Genre>) genreDAO.findGenreList();

			// �±� �� �̹��� ����Ʈ ����
			tagList = game.getCategory().split(",");
			imageList = game.getImage_address().split(",");
			rewardImageList = game.getReward_image().split(",");

		} catch (Exception e) {
			return "redirect:/";
		}

		req.setAttribute("game", game);
		req.setAttribute("genreList", genreList);
		
		req.setAttribute("tagList", tagList);
		req.setAttribute("imageList", imageList);
		req.setAttribute("rewardImageList", rewardImageList);
		return "/editGame.jsp"; // �ϴ��� ���� �������� �̵�
	}
}