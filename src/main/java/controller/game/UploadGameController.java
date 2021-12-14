package controller.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.Reservation;
import model.User;
import model.dao.GameDAO;

public class UploadGameController implements Controller {

	GameDAO gameDAO = new GameDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 로그인 여부 확인
		// 후에 취합했을 때 주석 풀 예정 -> 로그인 안 했으면 다시 게임 예약 페이지로 돌아감!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form 요청으로 redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String companyId = "1"; // 임시

		try {
			// 날짜 포맷에 따라 변경 후 sql Date 형식으로 변경
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date originStartDate = transFormat.parse(req.getParameter("start_date"));
			Date originEndDate = transFormat.parse(req.getParameter("end_date"));
			
			String formattedStartDate = transFormat.format(originStartDate);
			String formattedEndDate = transFormat.format(originEndDate);
			java.sql.Date start_date = java.sql.Date.valueOf(formattedStartDate);
			java.sql.Date end_date = java.sql.Date.valueOf(formattedEndDate);

			// test라 태그가 1개 들어온다고 가정하고 작성했음 -> 나중에 수정!
			String[] tagList = req.getParameterValues("tag[]");
			String tag = tagList[0];

			Game newGame = new Game(0, req.getParameter("title"), start_date, end_date, "",
					req.getParameter("description"), tag, "", req.getParameter("reward_text"), 0,
					Integer.parseInt(companyId));

			gameDAO.create(newGame);
			System.out.println("새 게임 추가 완료");
		} catch (Exception e) {
			return "redirect:/viewUpload";
		}

		return "redirect:/"; // 일단은 메인 페이지로 이동
	}
}