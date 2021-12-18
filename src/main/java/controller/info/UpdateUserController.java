package controller.info;

import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.dao.UserDAO;
import model.User;
import model.Interests;
import model.dao.InterestsDAO;
import model.service.UserManager;
import model.User;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);
    private UserDAO userDAO = new UserDAO();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	 //받은 날짜를 date로 변환하는 함수
        SimpleDateFormat userDate = new SimpleDateFormat("yyyy-MM-dd");
        
     // 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/main";		// login form 요청으로 redirect
        }
    	 
		HttpSession session = request.getSession();
		String userId = UserSessionUtils.getLoginUserId(session);
		User user = userDAO.findUser(userId);
		
		//GET request: form 요청
		if (request.getMethod().equals("GET")) {
			request.setAttribute("user", user);
			
			if (UserSessionUtils.isLoginUser(userId, session)){
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
								
				return "/myPage.jsp";   // 검색한 사용자 정보를 update form으로 전송     
			}    
			
//			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
//			request.setAttribute("updateFailed", true);
//			request.setAttribute("exception", 
//				new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
//			return "/myPage/myPage.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
		}
		
		
//		String value = request.getParameter("birthDay");
//		java.util.Date date = userDate.parse(value);
//		String formattedStartDate = userDate.format(date);
//		java.sql.Date Rdate = java.sql.Date.valueOf(formattedStartDate);

		// POST request (占쎌돳占쎌뜚占쎌젟癰귣떯占� parameter嚥∽옙 占쎌읈占쎈꽊占쎈쭡)
	
		User updateUser = new User( request.getParameter("id"),
				request.getParameter("password"),
				request.getParameter("email"), request.getParameter("name"), request.getParameter("phone_number"), Integer.parseInt(request.getParameter("gender")));
		
	
		try {
			userDAO.update(userId, updateUser);
		} catch(Exception e) { 
			e.printStackTrace(); 
		} 

		request.setAttribute("user", updateUser);
		return "/ViewmyPage.jsp?userId=" + userId;
	}
}