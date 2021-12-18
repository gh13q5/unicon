package controller.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class ReservationInfoController implements Controller {

	ReservationDAO reservationDAO = new ReservationDAO();
	GameDAO gameDAO = new GameDAO();
	UserDAO userDAO = new UserDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String gameId = req.getParameter("gameId");
		List<Reservation> reservationList = new ArrayList<>();
		List<User> userList = new ArrayList<>();

		try {
			reservationList = reservationDAO.findReservationListByGameId(Integer.parseInt(gameId));
			
			for(int i = 0; i < reservationList.size(); i++) {
				User user = userDAO.findUserByUserId(String.valueOf(reservationList.get(i).getUser_id()));
				userList.add(user);
			}
			System.out.println("예약자 목록 가져오기 완료");
		} catch (Exception e) {
			return "redirect:/companyMypage";
		}
		
		req.setAttribute("userList", userList);

		return "/reservationInfo.jsp"; // 게임 예약 페이지로 이동
	}
}