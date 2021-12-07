package controller.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.User;
import model.service.UserManager;

public class DeleteGameController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("category");
		String CompanyId = request.getParameter("companyId");
    	log.debug("Delete Game : {}", deleteId);

    	UserManager manager = UserManager.getInstance();		
		HttpSession session = request.getSession();	
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	// 로그인한 사용자가 관리자이고 	
			 !deleteId.equals("admin"))							// 삭제 대상이 일반 사용자인 경우, 
			   || 												// 또는 
			(!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
			  UserSessionUtils.isLoginUser(CompanyId, session))) { // 로그인한 사용자가 게임사 사용자인 경우
				
			manager.remove(deleteId);				// 사용자 정보 삭제
			if (UserSessionUtils.isLoginUser("admin", session))	// 로그인한 사용자가 관리자 	
				return "redirect:/main";		// main으로 이동
			else 									
				return "redirect:/CompanyMypage";		// companyMypage로 redirect
		}
		
		/* 삭제가 불가능한 경우 */
		User user = manager.findUser(deleteId);	// 사용자 정보 검색
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "시스템 관리자 정보는 삭제할 수 없습니다."		
				   : "타인의 정보는 삭제할 수 없습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/CompanyMypage.jsp";		// 사용자 보기 화면으로 이동 (forwarding)	
	}
}
