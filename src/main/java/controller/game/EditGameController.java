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

public class EditGameController implements Controller {

	GameDAO gameDAO = new GameDAO();
	CompanyDAO companyDAO = new CompanyDAO();

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String companyId = null;
		req.setAttribute("isLogin", "false");

		// �α��� ���� Ȯ��
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

		String gameId = req.getParameter("gameId");
		Game originGame = gameDAO.findGame(gameId);

		String title = null;

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date start_date = null;
		java.sql.Date end_date = null;

		String[] image_path_list = originGame.getImage_address().split(",");
		String image_path = "";

		String description = null;

		ArrayList<String> tagList = new ArrayList<>();
		String tag = "";

		String[] reward_image_list = originGame.getReward_image().split(",");
		String reward_image_path = "";
		String reward_text = null;

		// ���� �̹��� ���� ���ε�
		// ���۵� �������� ���ڵ� Ÿ���� multipart ���� ���θ� üũ�ϰ�, �ƴ϶�� ���� ���� ó�� X
		boolean check = ServletFileUpload.isMultipartContent(req);
		if (check) {
			// ���� ���� ���� ����
			ServletContext context = req.getServletContext();
			String path = context.getRealPath("/images/" + companyId);
			File dir = new File(path);

			System.out.println("���� ���� �Ϸ� : " + dir.getPath());

			if (!dir.exists())
				dir.mkdir();

			System.out.println("���� ��� ���� �Ϸ�");
			try {
				// ���� �̹��� ���� ����
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024); // 10kb�� �޸𸮿� �����͸� �о� ���δ�.
				factory.setRepository(dir);

				System.out.println("factory ���� �Ϸ�");

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024); // ���ε� �ִ� �뷮�� 10MB
				upload.setHeaderEncoding("utf-8");

				System.out.println("upload ���� �Ϸ�");

				// upload ��ü�� ���۵Ǿ� �� ��� �����͸� Collection ��ü�� ��´�.
				List<FileItem> items = (List<FileItem>) upload.parseRequest(req);
				for (int i = 0; i < items.size(); i++) {
					FileItem item = (FileItem) items.get(i);
					String value = item.getString("utf-8");

					// �Ϲ� �� �����Ͷ��?
					if (item.isFormField()) {
						System.out.println("formField ������ �������� ����");
						if (item.getFieldName().equals("title"))
							title = value;
						else if (item.getFieldName().equals("start_date")) {
							Date originStartDate = transFormat.parse(value);
							String formattedStartDate = transFormat.format(originStartDate);
							start_date = java.sql.Date.valueOf(formattedStartDate);
							System.out.println("start_date �������� �Ϸ�");
						} else if (item.getFieldName().equals("end_date")) {
							Date originEndDate = transFormat.parse(value);
							String formattedEndDate = transFormat.format(originEndDate);
							end_date = java.sql.Date.valueOf(formattedEndDate);
						} else if (item.getFieldName().equals("description"))
							description = value;
						else if (item.getFieldName().equals("tag[]")) {
							// test�� �±װ� 1�� ���´ٰ� �����ϰ� �ۼ����� -> ���߿� ����!
							System.out.println("�±� �������� ����" + value);
							tagList.add(value);
						} else if (item.getFieldName().equals("reward_text"))
							reward_text = value;
					} else {
						// ���� �����Ͷ��?
						System.out.println("���� ������ �������� ����");
						if (item.getFieldName().equals("image01") || item.getFieldName().equals("image02")
								|| item.getFieldName().equals("image03") || item.getFieldName().equals("image04")) {
							String file_path = item.getName();

							// ������ �Ѿ���� �ʾ����� �н�
							if (file_path == null || file_path.trim().length() == 0)
								continue;

							System.out.println("���� ��� : " + file_path);

							File file = new File(dir, file_path);
							System.out.println("���� ��� �� ���� �Ϸ�");
							item.write(file);

							if (item.getFieldName().equals("image01"))
								image_path_list[0] = file_path;
							else if (item.getFieldName().equals("image02"))
								image_path_list[1] = file_path;
							else if (item.getFieldName().equals("image03"))
								image_path_list[2] = file_path;
							else if (item.getFieldName().equals("image04"))
								image_path_list[3] = file_path;

							System.out.println("�̹��� ���� �Ϸ�");
						} else if (item.getFieldName().equals("reward-image01")
								|| item.getFieldName().equals("reward-image02")
								|| item.getFieldName().equals("reward-image03")
								|| item.getFieldName().equals("reward-image04")) {
							// �Ŀ� image02�� reward01�� �߰�
							String file_path = item.getName();

							// ������ �Ѿ���� �ʾ����� �н�
							if (file_path == null || file_path.trim().length() == 0)
								continue;

							System.out.println("���� ��� : " + file_path);

							File file = new File(dir, file_path);
							System.out.println("���� ��� �� ���� �Ϸ�");
							item.write(file);

							if (item.getFieldName().equals("reward-image01"))
								reward_image_list[0] = file_path;
							else if (item.getFieldName().equals("reward-image02"))
								reward_image_list[0] = file_path;
							else if (item.getFieldName().equals("reward-image03"))
								reward_image_list[0] = file_path;
							else if (item.getFieldName().equals("reward-image04"))
								reward_image_list[0] = file_path;

							System.out.println("������ �̹��� ���� �Ϸ�");
						}
					}
				}
				System.out.println("for�� ����");

				// �±� �� �̹��� �ּ� ����Ʈ ���ڿ� �ϳ��� ����
				for (int i = 0; i < tagList.size(); i++) {
					if (i != 0)
						tag += ",";
					tag += tagList.get(i);
				}
				for (int i = 0; i < image_path_list.length; i++) {
					if (i != 0)
						image_path += ",";
					image_path += image_path_list[i];
				}
				for (int i = 0; i < reward_image_list.length; i++) {
					if (i != 0)
						reward_image_path += ",";
					reward_image_path += reward_image_list[i];
				}

				Game newGame = new Game(Integer.parseInt(gameId), title, start_date, end_date, image_path, description,
						tag, reward_image_path, reward_text, 0, Integer.parseInt(companyId));
				gameDAO.update(newGame);
				System.out.println("���� ���� �Ϸ�");
			} catch (Exception e) {
				return "redirect:/viewEdit?gameId=" + gameId;
			}
		}

		return "redirect:/"; // �ϴ��� ���� �������� �̵�
	}
}