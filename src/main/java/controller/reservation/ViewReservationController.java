package controller.reservation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.Genre;
import model.Reservation;
import model.User;
import model.dao.CompanyDAO;
import model.dao.GameDAO;
import model.dao.GenreDAO;
import model.dao.ReservationDAO;
import model.dao.UserDAO;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class ViewReservationController implements Controller {

	UserDAO userDAO = new UserDAO();
	GameDAO gameDAO = new GameDAO();
	CompanyDAO companyDAO = new CompanyDAO();
	GenreDAO genreDAO = new GenreDAO();
	ReservationDAO reservationDAO = new ReservationDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String gameId = req.getParameter("gameId");
		String userId = null;
		req.setAttribute("isLogin", "false");
		req.setAttribute("reservate", "false");
		req.setAttribute("userType", "false");
		req.setAttribute("userObj", "false");

		// 로그인 여부 확인
		// 로그인 및 이미 예약한 게임인지 확인
		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

			// company User는 추천 게임 제공 X
			if (companyDAO.existingCompany(session_Id)) {
				Company company = companyDAO.findCompany(session_Id);
				req.setAttribute("userType", "company");
				req.setAttribute("userObj", company);
			} else {
				User user = userDAO.findUser(session_Id);
				userId = String.valueOf(user.getUserId());

				req.setAttribute("userType", "false");
				req.setAttribute("userObj", user);

				int isReservate = reservationDAO.findReservateById(gameId, userId); // DB에서 user가 게임 예약한 기록 확인
				if (isReservate == 0)
					req.setAttribute("reservate", "false");
				else
					req.setAttribute("reservate", "true");
			}
		}

		System.out.println("gameId : " + gameId + " userId : " + userId + " isLogin : " + req.getAttribute("isLogin")
				+ " reservate : " + req.getAttribute("reservate"));

		Game game = null;
		Company company = null;

		ArrayList<Genre> genreList = new ArrayList<>();
		String[] imageList = null;
		String[] rewardImageList = null;

		try {
			game = gameDAO.findGame(gameId); // 게임 정보 검색
			company = companyDAO.findCompanyByCompanyId(String.valueOf(game.getCompany_id()));

			// 게임의 장르 태그 리스트, 이미지 주소 리스트, 리워드 이미지 주소 리스트 생성
			String[] tagList = game.getCategory().split(",");
			for (int i = 0; i < tagList.length; i++) {
				genreList.add(genreDAO.findGenre(tagList[i]));
				System.out.println(genreList.get(i));
			}

			imageList = game.getImage_address().split(",");
			rewardImageList = game.getReward_image().split(",");
		} catch (Exception e) {
			return "redirect:/";
		}

		req.setAttribute("gameId", gameId);
		req.setAttribute("game", game);
		req.setAttribute("company", company);

		req.setAttribute("genreList", genreList);
		req.setAttribute("imageList", imageList);
		req.setAttribute("rewardImageList", rewardImageList);

		return "/reservation.jsp"; // 게임 예약 페이지로 이동
	}
}