package action.notice_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import notice.*;
import java.sql.Timestamp;
//사진첨부에 필요 cos.jar
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.util.*;
import java.io.*;

public class NoticeWriteProAction implements CommandAction {

	static String filename;
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//한글처리
		//사진 첨부
  		
	 	String saveFolder= "C:/Users/liam/git/webapp/filestorage";
		String encType="UTF-8";
		
		int maxSize=10*1024*1024;
		
		try{
			MultipartRequest multi=new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			
			Enumeration files=multi.getFileNames();
			
			while(files.hasMoreElements()){
				String name=(String)files.nextElement();
				NoticeWriteProAction.filename=multi.getFilesystemName(name);
				
			}
			//writeFrom의 form에서 POST방식에서는 enctype이 "application/x-www-form-urlencoded"방식이 아닌 경우, getParameter()로 데이터를 불러올 수 없다.
			//MultipartRequest의 getParameter를 쓰면 request.getParamter와 똑같은 효과
			NoticeDTO article=new NoticeDTO();
			article.setNotice_number(Integer.parseInt(multi.getParameter("notice_number")));
			article.setWriter(multi.getParameter("writer"));
			article.setRegist_date(new Timestamp(System.currentTimeMillis()));
			article.setCategory(Integer.parseInt(multi.getParameter("category")));
			article.setContent(multi.getParameter("content"));
			article.setTitle(multi.getParameter("title"));
			article.setFilename(NoticeWriteProAction.filename);//첨부 사진이름
			 
			request.setAttribute("category", article.getCategory());
			NoticeDAO dbPro=new NoticeDAO();
			dbPro.insertArticle(article); 
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		} 
		
		return "/notice_admin/noticeWritePro.jsp";
	}
}
