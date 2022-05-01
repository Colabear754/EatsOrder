package action.notice_admin;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.*;
import action.CommandAction;

//사진첨부 때문에 추가 
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.util.*;
import java.io.*;

public class NoticeUpdateProAction implements CommandAction{
	
	static String filename;
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		//사진 첨부
	 	String saveFolder= "C:/Users/liam/git/EatsOrder/src/main/webapp/filestorage";
		String encType="UTF-8";
		
		int maxSize=100*1024*1024;
		
		try{
			MultipartRequest multi=new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			Enumeration files=multi.getFileNames();
			
			while(files.hasMoreElements()){
				String name=(String)files.nextElement();
				NoticeUpdateProAction.filename=multi.getFilesystemName(name);
			}
			NoticeDTO article=new NoticeDTO();
			article.setNotice_number(Integer.parseInt(request.getParameter("notice_number")));		  
			article.setTitle(request.getParameter("title"));
			article.setCategory(Integer.parseInt(request.getParameter("category")));
			article.setContent(request.getParameter("content"));
			article.setFilename(NoticeUpdateProAction.filename);
			
			NoticeDAO dbPro=new NoticeDAO();
			int check=dbPro.updateArticle(article);
			String pageNum=multi.getParameter("pageNum");
			int category=Integer.parseInt(multi.getParameter("category"));
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", check);
			request.setAttribute("category", category);
		}catch(IOException ioe) {
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		} 
	
	return "/notice_admin/noticeUpdatePro.jsp"; 
	 
	}
}
