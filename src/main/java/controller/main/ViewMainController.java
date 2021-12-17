package controller.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.Genre;
import model.Interests;
import model.User;
import model.dao.CompanyDAO;
import model.dao.GameDAO;
import model.dao.GenreDAO;
import model.dao.InterestsDAO;
import model.dao.UserDAO;

public class ViewMainController implements Controller {
	private GameDAO gameDAO = new GameDAO();
	private UserDAO userDAO = new UserDAO();
	private GenreDAO genreDAO = new GenreDAO();
	private InterestsDAO interestsDAO = new InterestsDAO();
	private CompanyDAO companyDAO = new CompanyDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
//		String userID = UserSessionUtils.getLoginUserId(session); 

		ArrayList<Game> recommendList = new ArrayList<>();

		req.setAttribute("userType", "false");

		if (UserSessionUtils.hasLogined(req.getSession())) {
			// HttpSession session = req.getSession();
			String userID = UserSessionUtils.getLoginUserId(session);

			// company User는 추천 게임 제공 X
			if (companyDAO.existingCompany(userID))
				req.setAttribute("userType", "company");
			else {
				User user = userDAO.findUser(userID); // 사용자 정보 검색
				// String nickName = user.getName();
				req.setAttribute("userObj", user);
				req.setAttribute("userType", "user");

				// 유저 관심사에 따른 게임 추천
				ArrayList<Game> originRecommendList = new ArrayList<>();
				List<Interests> interestsList = interestsDAO.findUserInterestsList(user.getUserId());

				for (int i = 0; i < interestsList.size(); i++) {
					String category = String.valueOf(interestsList.get(i).getGenre_id());
					List<Game> interestedGameList = gameDAO.categoryGameList(category);
					for (int j = 0; j < interestedGameList.size(); j++)
						originRecommendList.add(interestedGameList.get(j));
				}

				// Map에 저장하여 태그가 많이 중복되는 순으로 내림차순 정렬
				HashMap<Integer, Integer> recommendMap = new HashMap<>();

				for (int i = 0; i < originRecommendList.size(); i++) {
					int recommend_id = originRecommendList.get(i).getGame_id();
					Integer count = recommendMap.get(recommend_id);

					if (count == null)
						recommendMap.put(recommend_id, 1);
					else
						recommendMap.put(recommend_id, count + 1);
				}

				List<HashMap.Entry<Integer, Integer>> entryList = new LinkedList<>(recommendMap.entrySet());
				entryList.sort(new Comparator<HashMap.Entry<Integer, Integer>>() {
					public int compare(HashMap.Entry<Integer, Integer> o1, HashMap.Entry<Integer, Integer> o2) {
						return o2.getValue() - o1.getValue();
					}
				});

				for (HashMap.Entry<Integer, Integer> entry : entryList) {
					if (recommendList.size() == ((entryList.size() / 3) * 3))
						break;
					recommendList.add(gameDAO.findGame(String.valueOf(entry.getKey())));
					System.out.println(entry.getKey());
				}

				req.setAttribute("recommendList", recommendList);
			}
		}

		List<Game> entireGameList = gameDAO.findGameList();
		List<Genre> genreList = genreDAO.findGenreList();

		req.setAttribute("entireGameList", entireGameList);
		req.setAttribute("genreList", genreList);

		return "/main.jsp";
	}
}