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
import model.Game;
import model.Reservation;
import model.User;
import model.dao.GameDAO;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.*;

public class UploadGameController implements Controller {

	GameDAO gameDAO = new GameDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// 로그인 여부 확인
		// 후에 취합했을 때 주석 풀 예정 -> 로그인 안 했으면 다시 게임 예약 페이지로 돌아감!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form 요청으로 redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String companyId = "1"; // 임시

		String title = null;

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date start_date = null;
		java.sql.Date end_date = null;

		String image_path = null;
		String description = null;
		ArrayList<String> tagList = new ArrayList<>();
		String tag = null; // 임시!
		String reward_image_path = null;
		String reward_text = null;

		// 게임 이미지 파일 업로드
		// 전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크하고, 아니라면 파일 전송 처리 X
		boolean check = ServletFileUpload.isMultipartContent(req);
		if (check) {
			// 파일 전송 포함 상태
			ServletContext context = req.getServletContext();
			String path = context.getRealPath("/images/" + companyId);
			File dir = new File(path);

			System.out.println("파일 지정 완료 : " + path);

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
							System.out.println("태그 가져오기 완료");
						} else if (item.getFieldName().equals("reward_text"))
							reward_text = value;
					} else {
						// 파일 데이터라면?
						System.out.println("파일 데이터 가져오기 시작");
						if (item.getFieldName().equals("image01")) {
							// 후에 image02랑 reward01도 추가
							image_path = item.getName();

							// 파일이 넘어오지 않았으면 패스
							if (image_path == null || image_path.trim().length() == 0)
								continue;

							System.out.println("파일 경로 : " + image_path);

							File file = new File(dir, image_path);
							System.out.println("파일 경로 및 생성 완료");
							item.write(file);
							System.out.println("이미지 저장 완료");
						}
					}
				}
				System.out.println("for문 종료");

				tag = tagList.get(0);
				Game newGame = new Game(0, title, start_date, end_date, image_path, description, tag, "", reward_text,
						0, Integer.parseInt(companyId));
				gameDAO.create(newGame);
				System.out.println("새 게임 추가 완료");
			} catch (Exception e) {
				return "redirect:/viewUpload";
			}
		}

		return "redirect:/"; // 일단은 메인 페이지로 이동
	}
}