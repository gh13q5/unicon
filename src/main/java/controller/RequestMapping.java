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

	// 媛� �슂泥� uri�뿉 ���븳 controller 媛앹껜瑜� ���옣�븷 HashMap �깮�꽦
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// 媛� uri�뿉 ���쓳�릺�뒗 controller 媛앹껜瑜� �깮�꽦 諛� ���옣

		// main
		mappings.put("/", new ForwardController("/index.jsp"));
		mappings.put("/main", new ViewMainController());
		mappings.put("/search", new SearchController());
		mappings.put("/gameRanking", new ForwardController("/main.jsp"));
		mappings.put("/category", new ViewCategoryController());
		mappings.put("/recommendation", new ViewRecommendationController());
		mappings.put("/mypage", new ViewMypageController());
		mappings.put("/companyMypage", new ViewCompanyMyPageController());

		// info
		mappings.put("/register", new ForwardController("/chooseUserType.jsp"));
		mappings.put("/userregister", new ForwardController("/userRegisterForm.jsp"));
		mappings.put("/companyregister", new ForwardController("/companyRegisterForm.jsp"));
		mappings.put("/welcome", new ForwardController("/welcome.jsp"));
		mappings.put("/register/user", new UserRegisterController());
		mappings.put("/register/company", new CompanyRegisterController());
		mappings.put("/login", new LoginController());
		mappings.put("/logout", new LogoutController());
		mappings.put("/userGameList", new ForwardController("/mypage.jsp"));
		mappings.put("/viewUpdateUser", new ViewUpdateUserController());
		mappings.put("/viewUpdateCompany", new ViewUpdateCompanyController());
		mappings.put("/updateRegister/user", new UpdateUserController());
		mappings.put("/updateRegister/company", new UpdateCompanyController());

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
		mappings.put("/reservationInfo", new ReservationInfoController());

		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String uri) {
		// 二쇱뼱吏� uri�뿉 ���쓳�릺�뒗 controller 媛앹껜瑜� 李얠븘 諛섑솚
		return mappings.get(uri);
	}
}
