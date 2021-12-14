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
		// �α��� ���� Ȯ��
		// �Ŀ� �������� �� �ּ� Ǯ ���� -> �α��� �� ������ �ٽ� ���� ���� �������� ���ư�!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form ��û���� redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String gameId = "1"; // �ӽ�
		String userId = "0";	// �ӽ�

		try {
			Date now = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(now);
			java.sql.Date date = java.sql.Date.valueOf(formattedDate);
			
			Reservation reservation = new Reservation(0, date, Integer.parseInt(gameId), Integer.parseInt(userId));
			
			int result = reservationDAO.create(reservation);	// ���� ���� �߰�
			System.out.println("���� ���� �߰� �Ϸ�");
		} catch (Exception e) {
			return "redirect:/";
		}

		return "redirect:/game"; // ���� ���� �������� �̵�
	}
}