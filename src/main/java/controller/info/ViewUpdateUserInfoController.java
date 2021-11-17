package controller.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import model.service.UserManager;
import model.service.UserNotFoundException;

public class ViewUpdateUserInfoController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/updateUserInfo.jsp"; // 사용자 보기 화면으로 이동
	}
}
