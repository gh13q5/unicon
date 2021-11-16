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
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/main", new ForwardController("main.jsp"));
        mappings.put("/main/search", new SearchController());
        mappings.put("/main/gameRanking", new ForwardController("main.jsp"));
        mappings.put("/main/category", new ViewCategoryController());
        mappings.put("/main/recommendation", new ViewRecommendationController());
        
        // info
        mappings.put("/info/register", new ForwardController("chooseUserType.jsp"));
        mappings.put("/info/register/user", new RegisterController());
        mappings.put("/info/register/company", new RegisterController());
        mappings.put("/info/login", new LoginController());
        mappings.put("/info/logout", new LogoutController());
        mappings.put("/info/userGameList", new ForwardController("mypage.jsp"));
        mappings.put("/info/updateRegister/user", new UpdateInfoController());
        mappings.put("/info/updateRegister/company", new UpdateInfoController());
        
        // point
        mappings.put("/point/pointShop", new ViewPointshopController());
        mappings.put("/point/pointShop", new BuyItemController());
        mappings.put("/point/addPoint", new AddPointController());
        
        // game
        mappings.put("/game/upload", new UploadGameController());
        mappings.put("/game/edit", new UpdateGameController());
        mappings.put("/game/remove", new DeleteGameController());
        
        // reservation
        mappings.put("/reservation/game", new ForwardController("reservation.jsp"));
        mappings.put("/reservation/reservate", new ReservateController());
        mappings.put("/reservation/cancle", new CancleReservationController());
        mappings.put("/reservation/reward", new SendRewardController());
        mappings.put("/reservation/statistics", new StatisticUserController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
