package controller.reservation;

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

public class CancleReservationController implements Controller {

	ReservationDAO reservationDAO = new ReservationDAO();
	GameDAO gameDAO = new GameDAO();
	UserDAO userDAO = new UserDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 로그인 여부 확인
		// 로그인 안 했으면 메인 페이지로 돌아감! 
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
			// 나중에 Login 구현되면 그 안으로 들어갈 예정
			boolean isReservate = reservationDAO.isReservate(gameId, userId); // DB에서 user가 게임 예약한 기록 확인
			if (isReservate) {
				reservationDAO.removeByUserIdAndGameId(gameId, userId);

				int total_reservate = game.getTotal_reservations();
				gameDAO.updateReservate(gameId, total_reservate - 1);
			}
		} catch (Exception e) {
			return "redirect:/";
		}

		req.setAttribute("reservate", "false");

		return "redirect:/game?gameId=" + gameId; // 게임 예약 페이지로 이동
	}
}