package action.myReview;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import javax.servlet.ServletContext;
import java.util.*;
import java.io.*;

public class InsertMyReviewAction implements CommandAction{
	
	static String filename;
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String saveFolder= "c:/webtest/4.jsp/2.back-end/sou2/EatsOrder/src/main/webapp/reviewPhoto";
		String encType="UTF-8";
		int maxSize=10*1024*1024;
		
		ServletContext context=request.getSession().getServletContext();
		ArrayList saveFiles=new ArrayList();
		
		try{
			MultipartRequest multi=new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			
			Enumeration files=multi.getFileNames();
			
			//hasMoreElements()는 Enumeration에 요소가 있는지 없는지 체크. 하나가 있으면 그 다음 요소가 있는지 없는지 체크.
			while(files.hasMoreElements()){
				String name=(String)files.nextElement();
				InsertMyReviewAction.filename=multi.getFilesystemName(name);
				saveFiles.add(multi.getFilesystemName(name));
			}
			for(int i=0;i<saveFiles.size();i++) {
				saveFiles.get(i);
			}
			
			String email = multi.getParameter("email");
			String order_number = multi.getParameter("order_number");
			String content = multi.getParameter("content");
			int rating = Integer.parseInt(multi.getParameter("rating"));
			System.out.println("rating: "+rating);
			ReviewDAO reviewProcess = new ReviewDAO();
			
			request.setCharacterEncoding("UTF-8");
			
			request.setAttribute("email", email);
			request.setAttribute("order_number", order_number);
			request.setAttribute("content", content);
			request.setAttribute("rating", rating);
						
			String[] photos = new String[5];
				
			//저장된 사진의 파일명을 photos 배열에 형변환 하여 담음
			for (int i = saveFiles.size() - 1; i >= 0; i--) {
				photos[i] = (String) saveFiles.get(i);
				System.out.println("photos["+i+"]=>"+photos[i]);
			}

			reviewProcess.insertReivew(email, order_number, content, photos, rating);
			
		}catch(IOException ioe){
			System.out.println("insertMyReviewAction의 ioe예외처리: "+ioe);
		}catch(Exception ex){
			System.out.println("insertMyReviewAction의 ex: "+ex);
		}
	
		return "/myReview/insertMyReview.jsp";
	}
}
