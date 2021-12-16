package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.game.*;
import controller.info.*;
import controller.main.*;
import controller.point.*;
import controller.reservation.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ���� 
    	
    	// main
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/main", new ViewMainController());
        mappings.put("/search", new SearchController());
        mappings.put("/gameRanking", new ForwardController("/main.jsp"));
        mappings.put("/category", new ViewCategoryController());
        mappings.put("/recommendation", new ViewRecommendationController());
        
        // info
        mappings.put("/register", new ForwardController("/chooseUserType.jsp"));
        mappings.put("/register/user", new UserRegisterController());
        mappings.put("/register/company", new CompanyRegisterController());
        mappings.put("/login", new LoginController());
        mappings.put("/logout", new LogoutController());
        mappings.put("/userGameList", new ForwardController("/mypage.jsp"));
        mappings.put("/updateRegister/user", new UpdateUserController());
        mappings.put("/updateRegister/company", new UpdateUserController());
        
        // point
        mappings.put("/pointShop", new ViewPointshopController());
        mappings.put("/pointShop", new BuyItemController());
        mappings.put("/addPoint", new AddPointController());
        
        // game
        mappings.put("/viewUpload", new ViewUploadGameController());
        mappings.put("/upload", new UploadGameController());
        mappings.put("/viewEdit", new ViewEditGameController());
        mappings.put("/edit", new EditGameController());
        mappings.put("/remove", new DeleteGameController());
        
        // reservation
        mappings.put("/game", new ViewReservationController());
        mappings.put("/reservate", new ReservateController());
        mappings.put("/cancle", new CancleReservationController());
        mappings.put("/reward", new SendRewardController());
        mappings.put("/statistics", new StatisticUserController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
