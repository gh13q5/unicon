package controller.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Reservation;
import model.dao.ReservationDAO;

public class ReservateController implements Controller {

	ReservationDAO reservationDAO = new ReservationDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 로그인 여부 확인
		// 후에 취합했을 때 주석 풀 예정 -> 로그인 안 했으면 다시 게임 예약 페이지로 돌아감!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form 요청으로 redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String gameId = "1"; // 임시
		String userId = "0";	// 임시

		try {
			Date now = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(now);
			java.sql.Date date = java.sql.Date.valueOf(formattedDate);
			
			Reservation reservation = new Reservation(0, date, Integer.parseInt(gameId), Integer.parseInt(userId));
			
			int result = reservationDAO.create(reservation);	// 예약 정보 추가
			System.out.println("예약 정보 추가 완료");
		} catch (Exception e) {
			return "redirect:/";
		}

		return "redirect:/game"; // 게임 예약 페이지로 이동
	}
}