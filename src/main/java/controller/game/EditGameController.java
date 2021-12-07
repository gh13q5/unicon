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
	    		// GET request: ȸ������ ���� form ��û	
	    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
	    		String id = request.getParameter("id");
	    		UserDAO manager = new UserDAO();
	    		
	    		manager.findUser(id);	// �����Ϸ��� ����� ���� �˻�
				request.setAttribute("id", id);			

				HttpSession session = request.getSession();
				if (UserSessionUtils.isLoginUser(id, session) ||
					UserSessionUtils.isLoginUser("admin", session)) {
					// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
					
					return "/updateUserInfo.jsp";   // �˻��� ����� ������ update form���� ����     
				}    
				
				// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
				request.setAttribute("updateFailed", true);
				request.setAttribute("exception", 
						new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
				return "/user/view.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
		    }	
		 
		//���� ��¥�� date�� ��ȯ�ϴ� �Լ�
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
				
		        return "redirect:/main.jsp";	// ���� �� ����� ����Ʈ ȭ������ redirect

	        
	        
	    }
}