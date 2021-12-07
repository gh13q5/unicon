package controller.game;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.info.DeleteUserController;
import controller.info.UserSessionUtils;
import model.dao.CompanyDAO;

public class DeleteGameController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("category");
		String CompanyId = request.getParameter("companyId");
		CompanyDAO manager = new CompanyDAO();
		
    	log.debug("Delete Game : {}", deleteId);
		
		HttpSession session = request.getSession();	
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !deleteId.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			  UserSessionUtils.isLoginUser(CompanyId, session))) { // �α����� ����ڰ� ���ӻ� ������� ���
				
			manager.remove(deleteId);				// ����� ���� ����
			if (UserSessionUtils.isLoginUser("admin", session))	// �α����� ����ڰ� ������ 	
				return "redirect:/main";		// main���� �̵�
			else 									
				return "redirect:/CompanyMypage";		// companyMypage�� redirect
		}
		
		/* ������ �Ұ����� ��� */
	    
		
		manager.findCompany(deleteId);	// ����� ���� �˻�
	    
		request.setAttribute("company", manager);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/CompanyMypage.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}