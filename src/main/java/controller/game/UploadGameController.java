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

		// �α��� ���� Ȯ��
		// �Ŀ� �������� �� �ּ� Ǯ ���� -> �α��� �� ������ �ٽ� ���� ���� �������� ���ư�!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form ��û���� redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String companyId = "1"; // �ӽ�

		try {
			// ��¥ ���˿� ���� ���� �� sql Date �������� ����
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date originStartDate = transFormat.parse(req.getParameter("start_date"));
			Date originEndDate = transFormat.parse(req.getParameter("end_date"));
			
			String formattedStartDate = transFormat.format(originStartDate);
			String formattedEndDate = transFormat.format(originEndDate);
			java.sql.Date start_date = java.sql.Date.valueOf(formattedStartDate);
			java.sql.Date end_date = java.sql.Date.valueOf(formattedEndDate);

			// test�� �±װ� 1�� ���´ٰ� �����ϰ� �ۼ����� -> ���߿� ����!
			String[] tagList = req.getParameterValues("tag[]");
			String tag = tagList[0];

			Game newGame = new Game(0, req.getParameter("title"), start_date, end_date, "",
					req.getParameter("description"), tag, "", req.getParameter("reward_text"), 0,
					Integer.parseInt(companyId));

			gameDAO.create(newGame);
			System.out.println("�� ���� �߰� �Ϸ�");
		} catch (Exception e) {
			return "redirect:/viewUpload";
		}

		return "redirect:/"; // �ϴ��� ���� �������� �̵�
	}
}