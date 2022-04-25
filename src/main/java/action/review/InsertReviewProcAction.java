package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;
//사진첨부 때문에 추가 lib에 cos.jar 넣어야 import 가능
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.ServletContext;
import java.util.*;
import java.io.*;
import java.sql.Timestamp;

public class InsertReviewProcAction implements CommandAction {

	//이미지의 실제 파일이름을 테이블에 저장하기위해 statice 변수 선언
	static String filename;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		//추가) 작성자:허우림. 작성일:22-04-17------
		//사진 첨부
		//로드 파일의 실제경로
		
		// 파일 경로 상대경로로 수정함
		String saveFolder = this.getClass().getResource("/").getPath();
		saveFolder = saveFolder.replaceAll("%20", " ");
		saveFolder = saveFolder.replace("WEB-INF/classes/", "reviewPhoto");
		String encType = "UTF-8";
		int maxSize = 10 * 1024 * 1024;

		ArrayList<String> saveFiles = new ArrayList<>();

		try {
			MultipartRequest multi = new MultipartRequest(request, saveFolder, maxSize, encType,
					new DefaultFileRenamePolicy());

			Enumeration files = multi.getFileNames();

			//hasMoreElements()는 Enumeration에 요소가 있는지 없는지 체크. 하나가 있으면 그 다음 요소가 있는지 없는지 체크.
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				InsertReviewProcAction.filename = multi.getFilesystemName(name);
				saveFiles.add(multi.getFilesystemName(name));
			}

			String email = multi.getParameter("email");
			String order_number = multi.getParameter("order_number");
			String content = multi.getParameter("content");
			int rating = Integer.parseInt(multi.getParameter("rating"));
			ReviewDAO reviewProcess = new ReviewDAO();

			String[] photos = new String[5];
			int photoCount = 0; // 사진 개수

			//저장된 사진의 파일명을 photos 배열에 형변환 하여 담음
			for (int i = 0; i < saveFiles.size(); i++) {
				photos[i] = (String) saveFiles.get(i);
			}

			for (String photo : photos) { // 사진 개수 계산
				photoCount = photo != null ? photoCount + 1 : photoCount;
			}

			switch (photoCount) { // 사진 개수에 따라 호출하는 메소드가 달라짐
			case 0:
				reviewProcess.insertReivew(email, order_number, content, rating);
				break;
			case 1:
				reviewProcess.insertReivew(email, order_number, content, photos[0], rating);
				break;
			case 2:
				reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], rating);
				break;
			case 3:
				reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], photos[2], rating);
				break;
			case 4:
				reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], photos[2], photos[3], rating);
				break;
			case 5:
				reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], photos[2], photos[3],
						photos[4], rating);
				break;
			default:
				break;
			}

		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		//---------------------

		/*
		 * // 리뷰 작성 액션클래스 request.setCharacterEncoding("utf-8"); String email =
		 * request.getParameter("email"); String order_number =
		 * request.getParameter("order_number"); String content =
		 * request.getParameter("content"); int rating =
		 * Integer.parseInt(request.getParameter("rating")); ReviewDAO reviewProcess =
		 * new ReviewDAO(); String[] photos = new String[5]; int photoCount = 0; // 사진
		 * 개수
		 * 
		 * for (int i = 0; i < photos.length; i++) { photos[i] =
		 * request.getParameter("photo" + (i + 1)); }
		 * 
		 * for (String photo : photos) { // 사진 개수 계산 photoCount = photo != null ?
		 * photoCount + 1 : photoCount; }
		 * 
		 * switch (photoCount) { // 사진 개수에 따라 호출하는 메소드가 달라짐 case 0:
		 * reviewProcess.insertReivew(email, order_number, content, rating); break; case
		 * 1: reviewProcess.insertReivew(email, order_number, content, photos[0],
		 * rating); break; case 2: reviewProcess.insertReivew(email, order_number,
		 * content, photos[0], photos[1], rating); break; case 3:
		 * reviewProcess.insertReivew(email, order_number, content, photos[0],
		 * photos[1], photos[2], rating); break; case 4:
		 * reviewProcess.insertReivew(email, order_number, content, photos[0],
		 * photos[1], photos[2], photos[3], rating); break; case 5:
		 * reviewProcess.insertReivew(email, order_number, content, photos[0],
		 * photos[1], photos[2], photos[3], photos[4], rating); break; default: break; }
		 */
		return "/review/insertReviewProc.jsp";
	}
}
