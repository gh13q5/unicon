package controller.game;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.info.UserSessionUtils;
import model.Company;
import model.Game;
import model.Reservation;
import model.User;
import model.dao.CompanyDAO;
import model.dao.GameDAO;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.*;

public class UploadGameController implements Controller {

	CompanyDAO companyDAO = new CompanyDAO();
	GameDAO gameDAO = new GameDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String companyId = null;
		req.setAttribute("isLogin", "false");

		// 로그인 여부 확인 
		if (UserSessionUtils.hasLogined(req.getSession())) {
			req.setAttribute("isLogin", "true");
			String session_Id = UserSessionUtils.getLoginUserId(req.getSession());

			if (companyDAO.existingCompany(session_Id)) {
				Company company = companyDAO.findCompany(session_Id);
				companyId = String.valueOf(company.getCompanyId());
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}

		String title = null;

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date start_date = null;
		java.sql.Date end_date = null;

		ArrayList<String> image_path_list = new ArrayList<>();
		String image_path = "";

		String description = null;

		ArrayList<String> tagList = new ArrayList<>();
		String tag = "";

		ArrayList<String> reward_image_list = new ArrayList<>();
		String reward_image_path = "";
		String reward_text = null;

		// 게임 이미지 파일 업로드
		// 전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크하고, 아니라면 파일 전송 처리 X
		boolean check = ServletFileUpload.isMultipartContent(req);
		if (check) {
			// 파일 전송 포함 상태
			ServletContext context = req.getServletContext();
			String path = context.getRealPath("/images/" + companyId);
			File dir = new File(path);

			System.out.println("파일 지정 완료 : " + dir.getPath());

			if (!dir.exists())
				dir.mkdir();

			System.out.println("파일 경로 생성 완료");
			try {
				// 게임 이미지 파일 전송
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024); // 10kb씩 메모리에 데이터를 읽어 들인다.
				factory.setRepository(dir);

				System.out.println("factory 지정 완료");

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024); // 업로드 최대 용량은 10MB
				upload.setHeaderEncoding("utf-8");

				System.out.println("upload 지정 완료");

				// upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
				List<FileItem> items = (List<FileItem>) upload.parseRequest(req);
				for (int i = 0; i < items.size(); i++) {
					FileItem item = (FileItem) items.get(i);
					String value = item.getString("utf-8");

					// 일반 폼 데이터라면?
					if (item.isFormField()) {
						System.out.println("formField 데이터 가져오기 시작");
						if (item.getFieldName().equals("title"))
							title = value;
						else if (item.getFieldName().equals("start_date")) {
							Date originStartDate = transFormat.parse(value);
							String formattedStartDate = transFormat.format(originStartDate);
							start_date = java.sql.Date.valueOf(formattedStartDate);
							System.out.println("start_date 가져오기 완료");
						} else if (item.getFieldName().equals("end_date")) {
							Date originEndDate = transFormat.parse(value);
							String formattedEndDate = transFormat.format(originEndDate);
							end_date = java.sql.Date.valueOf(formattedEndDate);
						} else if (item.getFieldName().equals("description"))
							description = value;
						else if (item.getFieldName().equals("tag[]")) {
							// test라 태그가 1개 들어온다고 가정하고 작성했음 -> 나중에 수정!
							System.out.println("태그 가져오기 시작" + value);
							tagList.add(value);
						} else if (item.getFieldName().equals("reward_text"))
							reward_text = value;
					} else {
						// 파일 데이터라면?
						System.out.println("파일 데이터 가져오기 시작");
						if (item.getFieldName().equals("image01") || item.getFieldName().equals("image02")
								|| item.getFieldName().equals("image03") || item.getFieldName().equals("image04")) {
							// 후에 image02랑 reward01도 추가
							String file_path = item.getName();

							// 파일이 넘어오지 않았으면 패스
							if (file_path == null || file_path.trim().length() == 0)
								continue;

							System.out.println("파일 경로 : " + file_path);

							File file = new File(dir, file_path);
							System.out.println("파일 경로 및 생성 완료");
							item.write(file);
							image_path_list.add(file_path);
							System.out.println("이미지 저장 완료");
						} else if (item.getFieldName().equals("reward-image01")
								|| item.getFieldName().equals("reward-image02")
								|| item.getFieldName().equals("reward-image03")
								|| item.getFieldName().equals("reward-image04")) {
							// 후에 image02랑 reward01도 추가
							String file_path = item.getName();

							// 파일이 넘어오지 않았으면 패스
							if (file_path == null || file_path.trim().length() == 0)
								continue;

							System.out.println("파일 경로 : " + file_path);

							File file = new File(dir, file_path);
							System.out.println("파일 경로 및 생성 완료");
							item.write(file);
							reward_image_list.add(file_path);
							System.out.println("리워드 이미지 저장 완료");
						}
					}
				}
				System.out.println("for문 종료");

				// 태그 및 이미지 주소 리스트 문자열 하나로 압축
				for (int i = 0; i < tagList.size(); i++) {
					if (i != 0)
						tag += ",";
					tag += tagList.get(i);
				}
				for (int i = 0; i < image_path_list.size(); i++) {
					if (i != 0)
						image_path += ",";
					image_path += image_path_list.get(i);
				}
				for (int i = 0; i < reward_image_list.size(); i++) {
					if (i != 0)
						reward_image_path += ",";
					reward_image_path += reward_image_list.get(i);
				}

				Game newGame = new Game(0, title, start_date, end_date, image_path, description, tag, reward_image_path,
						reward_text, 0, Integer.parseInt(companyId));
				gameDAO.create(newGame);
				System.out.println("새 게임 추가 완료");
			} catch (Exception e) {
				return "redirect:/viewUpload";
			}
		}

		return "redirect:/"; // 일단은 메인 페이지로 이동
	}
}