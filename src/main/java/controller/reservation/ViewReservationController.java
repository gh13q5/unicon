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

		// �α��� ���� Ȯ��
		// �α��� �� �̹� ������ �������� Ȯ��
		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());
			User user = userDAO.findUser(session_Id);
			userId = String.valueOf(user.getUserId());
			
			int isReservate = reservationDAO.findReservateById(gameId, userId); // DB���� user�� ���� ������ ��� Ȯ��
			if (isReservate == 0)
				req.setAttribute("reservate", "false");
			else
				req.setAttribute("reservate", "true");
		}

		System.out.println("gameId : " + gameId + " userId : " + userId + " isLogin : " + req.getAttribute("isLogin")
				+ " reservate : " + req.getAttribute("reservate"));

		Game game = null;
		Company company = null;

		ArrayList<Genre> genreList = new ArrayList<>();
		String[] imageList = null;
		String[] rewardImageList = null;

		try {
			game = gameDAO.findGame(gameId); // ���� ���� �˻�
			company = companyDAO.findCompanyByCompanyId(String.valueOf(game.getCompany_id()));

			// ������ �帣 �±� ����Ʈ, �̹��� �ּ� ����Ʈ, ������ �̹��� �ּ� ����Ʈ ����
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

		return "/reservation.jsp"; // ���� ���� �������� �̵�
	}
}