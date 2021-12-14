package controller.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.Genre;
import model.dao.GenreDAO;

public class ViewUploadGameController implements Controller {

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
		ArrayList<Genre> genreList = new ArrayList<>();

		try {
			genreList = (ArrayList<Genre>) genreDAO.findGenreList();
		} catch (Exception e) {
			return "redirect:/";
		}

		req.setAttribute("genreList", genreList);
		return "/uploadGame.jsp"; // �ϴ��� ���� �������� �̵�
	}
}