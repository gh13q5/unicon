package controller.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.Genre;
import model.dao.CompanyDAO;
import model.dao.GameDAO;
import model.dao.GenreDAO;

public class ViewEditGameController implements Controller {

	GameDAO gameDAO = new GameDAO();
	GenreDAO genreDAO = new GenreDAO();
	CompanyDAO companyDAO = new CompanyDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String companyId = null;
		req.setAttribute("isLogin", "false");

		// 로그인 여부 확인 
		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

			if (companyDAO.existingCompany(session_Id)) {
				Company company = companyDAO.findCompany(session_Id);
				companyId = String.valueOf(company.getCompanyId());
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}

		String gameId = req.getParameter("gameId");
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