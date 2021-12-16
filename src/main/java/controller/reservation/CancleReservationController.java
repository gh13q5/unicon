package controller.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Game;
import model.Reservation;
import model.dao.GameDAO;
import model.dao.ReservationDAO;

public class CancleReservationController implements Controller {

	ReservationDAO reservationDAO = new ReservationDAO();
	GameDAO gameDAO = new GameDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// �α��� ���� Ȯ��
		// �Ŀ� �������� �� �ּ� Ǯ ���� -> �α��� �� ������ �ٽ� ���� ���� �������� ���ư�!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form ��û���� redirect }
		 */

		String gameId = req.getParameter("gameId");
		String userId = "0"; // �ӽ�
		
		Game game = gameDAO.findGame(gameId);

		try {
			// ���߿� Login �����Ǹ� �� ������ �� ����
			boolean isReservate = reservationDAO.isReservate(gameId, userId); // DB���� user�� ���� ������ ��� Ȯ��
			if (isReservate) {
				reservationDAO.removeByUserIdAndGameId(gameId, userId);
				
				int total_reservate = game.getTotal_reservations();
				gameDAO.updateReservate(gameId, total_reservate - 1);
			}
		} catch (Exception e) {
			return "redirect:/";
		}

		return "redirect:/game?gameId=" + gameId; // ���� ���� �������� �̵�
	}
}