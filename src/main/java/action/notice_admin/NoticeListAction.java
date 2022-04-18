package action.notice_admin;

import java.util.Collections;
import java.util.Hashtable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import notice.*;

public class NoticeListAction implements CommandAction{
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		int category=Integer.parseInt(request.getParameter("category"));
		int count=0;
		
		List<NoticeDTO> articleList = null;

		NoticeDAO dbPro = new NoticeDAO();
		
		count = dbPro.getArticleSearchCount(search,searchtext,category);
		
		Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		
		if (count > 0) {
			articleList = dbPro.getNoticeArticles(pgList.get("startRow"),
																  pgList.get("endRow"),
																  search,searchtext,category);
		}else {
			articleList=Collections.EMPTY_LIST;//비어있는 List객체
		}

		request.setAttribute("search", search);
		request.setAttribute("searchtext", searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
		request.setAttribute("category",category);

		return "/notice_admin/noticeList.jsp";
	}
}
