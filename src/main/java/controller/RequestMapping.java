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
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장 
    	
    	// main
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/main", new ViewMainController());
        mappings.put("/search", new SearchController());
        mappings.put("/gameRanking", new ForwardController("/main.jsp"));
        mappings.put("/category", new ViewCategoryController());
        mappings.put("/recommendation", new ViewRecommendationController());
        
        // info
        mappings.put("/register", new ForwardController("/chooseUserType.jsp"));
        mappings.put("/register_user", new UserRegisterController());
        mappings.put("/register_company", new CompanyRegisterController());
        mappings.put("/login", new LoginController());
        mappings.put("/logout", new LogoutController());
        mappings.put("/userGameList", new ForwardController("/mypage.jsp"));
        mappings.put("/updateRegister", new UpdateUserController());
        
        // point
        mappings.put("/pointShop", new ViewPointshopController());
        mappings.put("/pointShop", new BuyItemController());
        mappings.put("/addPoint", new AddPointController());
        
        // game
        mappings.put("/upload", new UploadGameController());
        mappings.put("/viewEdit", new ViewEditGameController());
        mappings.put("/edit", new EditGameController());
        mappings.put("/remove", new DeleteGameController());
        
        // reservation
        //mappings.put("/game", new ViewReservationController());
        mappings.put("/reservate", new ReservateController());
        mappings.put("/cancle", new CancleReservationController());
        mappings.put("/reward", new SendRewardController());
        mappings.put("/statistics", new StatisticUserController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
