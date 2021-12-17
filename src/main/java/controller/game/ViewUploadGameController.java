package controller.game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.Genre;
import model.User;
import model.dao.CompanyDAO;
import model.dao.GenreDAO;

public class ViewUploadGameController implements Controller {

	GenreDAO genreDAO = new GenreDAO();
	CompanyDAO companyDAO = new CompanyDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String companyId = null;
		req.setAttribute("isLogin", "false");

		// 로그인 여부 확인 
		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

			if (companyDAO.existingCompany(session_Id)) {
				Company company = companyDAO.findCompany(session_Id);
				companyId = String.valueOf(company.getCompanyId());
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}

		ArrayList<Genre> genreList = new ArrayList<>();

		try {
			genreList = (ArrayList<Genre>) genreDAO.findGenreList();
		} catch (Exception e) {
			return "redirect:/";
		}

		req.setAttribute("genreList", genreList);
		return "/uploadGame.jsp"; // 일단은 메인 페이지로 이동
	}
}