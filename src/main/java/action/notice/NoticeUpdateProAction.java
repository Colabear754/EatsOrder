package action.notice;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.*;

import action.CommandAction;
import action.UpdateProAction;
import hwl.notice.NoticeDAO;

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
	 	String saveFolder= "c:/webtest/4.jsp/2.back-end/sou2/EatsOrder/src/main/webapp/filestorage";
		String encType="UTF-8";
		
		int maxSize=100*1024*1024;
		
		try{
			MultipartRequest multi=new MultipartRequest(request,saveFolder,maxSize,encType,new DefaultFileRenamePolicy());
			Enumeration params=multi.getParameterNames();
			
			while(params.hasMoreElements()){
				String name=(String)params.nextElement();
				String value=multi.getParameter(name);
				System.out.println(name+"="+value+"<br/>");
				
			}
			Enumeration files=multi.getFileNames();
			
			while(files.hasMoreElements()){
				String name=(String)files.nextElement();
				
				NoticeUpdateProAction.filename=multi.getFilesystemName(name);
				
				String original=multi.getOriginalFileName(name);
				String type=multi.getContentType(name);
				File f=multi.getFile(name);
		
				System.out.println("파라미터 이름:"+name+"<br/>");
				System.out.println("실제 파일 이름:"+original+"<br/>");
				System.out.println("저장된 파일 이름:"+NoticeUpdateProAction.filename+"<br/>");
				System.out.println("파일 타입:"+type+"<br/>");
				
				if(f!=null){
					System.out.println("크기:"+f.length()+"바이트");
				}
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
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("check", check);
		}catch(IOException ioe) {
			System.out.println(ioe);
		}catch(Exception ex){
			System.out.println(ex);
		} 
	
	return "/notice/noticeUpdatePro.jsp"; 
	 
	}
}
