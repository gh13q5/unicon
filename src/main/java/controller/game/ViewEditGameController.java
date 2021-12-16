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

		// 로그인 여부 확인
		// 후에 취합했을 때 주석 풀 예정 -> 로그인 안 했으면 다시 게임 예약 페이지로 돌아감!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form 요청으로 redirect }
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

			// 태그 및 이미지 리스트 생성
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
		return "/editGame.jsp"; // 일단은 메인 페이지로 이동
	}
}