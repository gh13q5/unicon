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
import model.service.UserManager;
import model.service.UserNotFoundException;

public class ViewReservationController implements Controller {

	GameDAO gameDAO = new GameDAO();
	CompanyDAO companyDAO = new CompanyDAO();
	GenreDAO genreDAO = new GenreDAO();
	ReservationDAO reservationDAO = new ReservationDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 로그인 여부 확인
		// login 후 reservation 데이터 여부로 예약 버튼 바꿀 때 마저 작성할 예정...
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form 요청으로 redirect }
		 */

		String gameId = req.getParameter("gameId");
		String userId = "0"; // 임시

		Game game = null;
		Company company = null;

		ArrayList<Genre> genreList = new ArrayList<>();
		String[] imageList = null;
		String[] rewardImageList = null;

		try {
			game = gameDAO.findGame(gameId); // 게임 정보 검색
			company = companyDAO.findCompany(String.valueOf(game.getCompany_id()));

			// 게임의 장르 태그 리스트, 이미지 주소 리스트, 리워드 이미지 주소 리스트 생성
			System.out.println(game.getCategory());
			String[] tagList = game.getCategory().split(",");
			for (int i = 0; i < tagList.length; i++) {
				genreList.add(genreDAO.findGenre(tagList[i]));
				System.out.println(genreList.get(i));
			}

			imageList = game.getImage_address().split(",");
			rewardImageList = game.getReward_image().split(",");

			// 나중에 Login 구현되면 그 안으로 들어갈 예정
			boolean isReservate = reservationDAO.isReservate(gameId, userId); // DB에서 user가 게임 예약한 기록 확인
			if (isReservate)
				req.setAttribute("reservate", "true");
			else
				req.setAttribute("reservate", "false");
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