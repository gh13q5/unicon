package controller.game;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.info.UserSessionUtils;

import model.Game;
import model.dao.GameDAO;
import model.dao.UserDAO;


public class EditGameController implements Controller{
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 if (request.getMethod().equals("GET")) {	
	    		// GET request: 회원정보 수정 form 요청	
	    		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
	    		String id = request.getParameter("id");
	    		UserDAO manager = new UserDAO();
	    		
	    		manager.findUser(id);	// 수정하려는 사용자 정보 검색
				request.setAttribute("id", id);			

				HttpSession session = request.getSession();
				if (UserSessionUtils.isLoginUser(id, session) ||
					UserSessionUtils.isLoginUser("admin", session)) {
					// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
					
					return "/updateUserInfo.jsp";   // 검색한 사용자 정보를 update form으로 전송     
				}    
				
				// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", 
						new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
				return "/user/view.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
		    }	
		 
		//받은 날짜를 date로 변환하는 함수
	     SimpleDateFormat gameDate = new SimpleDateFormat("yyyy-MM-dd");
		 
		 Game game = new Game(
				 Integer.parseInt(request.getParameter("game_id")),
				 request.getParameter("title"),
				 gameDate.parse(request.getParameter("start_date")),
				 gameDate.parse(request.getParameter("end_date")),
				 request.getParameter("image_address"),
				 request.getParameter("description"),
				 request.getParameter("category"),
				 request.getParameter("reward_image"),
				 request.getParameter("reward_text"),
				 Integer.parseInt(request.getParameter("total_reservations")),
				 Integer.parseInt(request.getParameter("company_id")));

				GameDAO manager = new GameDAO();
				manager.update(game);
				
		        return "redirect:/main.jsp";	// 성공 시 사용자 리스트 화면으로 redirect

	        
	        
	    }
}