package controller.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Game;
import model.Reservation;
import model.User;
import model.dao.GameDAO;
import model.dao.ReservationDAO;
import model.dao.UserDAO;

public class ReservateController implements Controller {

	ReservationDAO reservationDAO = new ReservationDAO();
	GameDAO gameDAO = new GameDAO();
	UserDAO userDAO = new UserDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// �α��� ���� Ȯ��
		// �α��� �� ������ ���� �������� ���ư�! 
		String gameId = req.getParameter("gameId");
		String userId = null;

		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());
			User user = userDAO.findUser(session_Id);
			userId = String.valueOf(user.getUserId());
			
		} else {
			return "redirect:/";
		}

		Game game = gameDAO.findGame(gameId);

		try {
			Date now = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(now);
			java.sql.Date date = java.sql.Date.valueOf(formattedDate);

			Reservation reservation = new Reservation(0, date, Integer.parseInt(gameId), Integer.parseInt(userId));
			reservationDAO.create(reservation); // ���� ���� �߰�

			int total_reservate = game.getTotal_reservations();
			gameDAO.updateReservate(gameId, total_reservate + 1);

			System.out.println("���� ���� �߰� �Ϸ�");
		} catch (Exception e) {
			return "redirect:/";
		}
		
		req.setAttribute("reservate", "true");

		return "redirect:/game?gameId=" + gameId; // ���� ���� �������� �̵�
	}
}