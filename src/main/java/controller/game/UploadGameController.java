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

		// �α��� ���� Ȯ��
		// �Ŀ� �������� �� �ּ� Ǯ ���� -> �α��� �� ������ �ٽ� ���� ���� �������� ���ư�!
		/*
		 * if (!UserSessionUtils.hasLogined(req.getSession())) { return
		 * "redirect:/user/login/form"; // login form ��û���� redirect }
		 */

		// String gameId = req.getParameter("gameId");
		String companyId = "1"; // �ӽ�

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
							// �Ŀ� image02�� reward01�� �߰�
							String file_path = item.getName();

							// ������ �Ѿ���� �ʾ����� �н�
							if (file_path == null || file_path.trim().length() == 0)
								continue;

							System.out.println("���� ��� : " + file_path);

							File file = new File(dir, file_path);
							System.out.println("���� ��� �� ���� �Ϸ�");
							item.write(file);
							image_path_list.add(file_path);
							System.out.println("�̹��� ���� �Ϸ�");
						} else if (item.getFieldName().equals("reward-image01") || item.getFieldName().equals("reward-image02")
								|| item.getFieldName().equals("reward-image03") || item.getFieldName().equals("reward-image04")) {
							// �Ŀ� image02�� reward01�� �߰�
							String file_path = item.getName();

							// ������ �Ѿ���� �ʾ����� �н�
							if (file_path == null || file_path.trim().length() == 0)
								continue;

							System.out.println("���� ��� : " + file_path);

							File file = new File(dir, file_path);
							System.out.println("���� ��� �� ���� �Ϸ�");
							item.write(file);
							reward_image_list.add(file_path);
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
				System.out.println("�� ���� �߰� �Ϸ�");
			} catch (Exception e) {
				return "redirect:/viewUpload";
			}
		}

		return "redirect:/"; // �ϴ��� ���� �������� �̵�
	}
}