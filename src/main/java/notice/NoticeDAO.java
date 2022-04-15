package notice;

/*작성자: 허우림. 공지사항/이벤트 게시판 글목록보기, 글검색, 글추가, 글수정, 글삭제,페이징처리 메서드*/
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import connectionMgr.DBConnectionMgr;

public class NoticeDAO {

	private DBConnectionMgr pool = null;

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	public NoticeDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}
	
	//페이징 처리를 위한 메서드(총레코드수 구하기)
	public int getArticleCount() {
		int x = 0;
		try {
			con = pool.getConnection();
			sql = "select count(*) from notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getArticleCount()에러발생=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//검색어에 해당되는 총레코드수를 구하는 메서드(1.검색분야, 2.검색어)
	public int getArticleSearchCount(String search,String searchtext,int category) {
		int x=0;
		 NoticeDTO article=new NoticeDTO();
		try {
			con=pool.getConnection();
			
			if(search==null || search=="") {
				sql="select count(*) from notice where category="+category;
			}else {
				//제목+본문으로 검색
				if(search.equals("title_content")) {
					sql="select count(*) from notice where title like '%"+
							searchtext+"%' or content like '%"+searchtext+"%' and category="+category;
				}else {//제목이나 본문으로 검색
					sql="select count(*) from notice where "+search+" like '%"+
							searchtext+"%' and category="+category;
				}
			}

			sql="select count(*) from notice where category="+category;
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getArticleSearchCount()에러발생=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return x;
	} 
	
	//글목록보기에 대한 메서드(한 페이지당 보여줄 레코드 개수 설정) 1)레코드의 시작번호  2)불러올 레코드의 개수
	public List<NoticeDTO> getArticles(int start,int end){
		 List<NoticeDTO> articleList=null;
		 NoticeDTO article=new NoticeDTO();
		 
		 try {
			 con=pool.getConnection();
			 sql="select * from( select a.*, rownum as r from (select * from notice order by notice_number desc) a) where r between ? and ? where category=?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1, start);
			 pstmt.setInt(2, end); 
			pstmt.setInt(3, article.getCategory());
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
				 articleList=new ArrayList(end);
				 do {
					 article.setNotice_number(rs.getInt("notice_number"));
					 article.setWriter(rs.getString("writer"));
					 article.setRegist_date(rs.getTimestamp("regist_date"));
					 article.setTitle(rs.getString("title"));
					 article.setCategory(rs.getInt("category"));
					 article.setContent(rs.getString("content"));
					 article.setFilename(rs.getString("filename"));
					 articleList.add(article);
				 }while(rs.next());
			 }
		 }catch(Exception e) {
			System.out.println("getArticles() 에러유발=>"+e); 
		 }finally {
			 pool.freeConnection(con, pstmt, rs);
		 }
		 return articleList;
	}
	
	//검색어,카테고리에 따른 글목록보기 메서드
	public List<NoticeDTO> getNoticeArticles(int start,int end,String search,String searchtext, int category){
		 List<NoticeDTO> articleList=null;
		 
		 try {
			 con=pool.getConnection();
			 
			 if(search==null || search=="") {
					sql="select * from( select a.*, rownum as r from (select * from notice order by notice_number desc) a) where r between ? and ? and category="+category;
			 }else {
					if(search.equals("title_content")) {//제목+본문
						sql="select * from (select rownum r, notice.* from notice where title like '%"+
								searchtext+"%' or content like '%"+searchtext+"%' order by notice_number desc) where r >=? and r<=? and category="+category;
					}else {//제목,작성자->매개변수를 이용해서 하나의 sql통합
						sql="select * from (select rownum r, notice.* from notice where "+search+" like '%"+
								searchtext+"%' order by notice_number desc) where r>=? and r<=? and category="+category;
					}
			 }
			
			 pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1, start);
			 pstmt.setInt(2, end);
			 
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
				 //end 개수만큼 필드값 담을 객체생성
				 articleList=new ArrayList(end);
				 do {
					 NoticeDTO article=new NoticeDTO();
					 article.setNotice_number(rs.getInt("notice_number"));
					 article.setWriter(rs.getString("writer"));
					 article.setRegist_date(rs.getTimestamp("regist_date"));
					 article.setTitle(rs.getString("title"));
					 article.setCategory(rs.getInt("category"));
					 article.setContent(rs.getString("content"));
					 article.setFilename(rs.getString("filename"));
					 articleList.add(article);
				 }while(rs.next());
			 }
		 }catch(Exception e) {
			System.out.println("getNoticeArticles() 에러유발=>"+e); 
		 }finally {
			 pool.freeConnection(con, pstmt, rs);
		 }
		 return articleList;
	}
	
	//페이징 처리 계산 정리해주는 메서드
	public Hashtable pageList(String pageNum,int count) {
		//페이징 처리결과를 저장할 Hashtable 객체를 선언
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
		
		int pageSize=10;//numPerPage=>페이지당 보여주는 게시물수(=레코드수) 
	    int blockSize=3;//pagePerBlock=>블럭당 보여주는 페이지수 
	      
	    //게시판을 처음 실행시키면 가장 최신글을 보여주기 위해 무조건 1페이지부터 출력 
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int number = 0;
		number=count-(currentPage-1)*pageSize;
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		//시작페이지
		int startPage=0;
		
		if(currentPage%blockSize!=0){
			  startPage=currentPage/blockSize*blockSize+1;
		}else{//10%10=0(10,20,30,40,,,)
		startPage=((currentPage/blockSize)-1)*blockSize+1;
		}
		//종료페이지
		int endPage=startPage+blockSize-1;
		
		//블럭별로 구분해서 링크 걸어서 출력(마지막 페이지 > 총페이지수)
		if(endPage > pageCount) endPage=pageCount;	
		//페이징 처리에 대한 계산결과를 Hashtable,Hashmap에 담아서 NoticeListAction에 전달
		pgList.put("pageSize",pageSize);
		pgList.put("blockSize",blockSize);
		pgList.put("currentPage",currentPage);
		pgList.put("startRow",startRow);
		pgList.put("endRow",endRow);
		pgList.put("count",count);
		pgList.put("number",number);
		pgList.put("startPage",startPage);
		pgList.put("endPage",endPage);
		pgList.put("pageCount",pageCount);
		 
		return pgList;
	} 
	
	//게시판 글쓰기 메서드
	public void insertArticle(NoticeDTO article) {
		int notice_number=article.getNotice_number();
		
		try {
			con=pool.getConnection();
			sql="insert into notice(notice_number,writer,regist_date,title,content,category,filename)values(notice_number_seq.nextval,?,sysdate,?,?,?,?)";
		
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4,article.getCategory());
			pstmt.setString(5,article.getFilename());
			int insert=pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);
		}catch(Exception e) {
			System.out.println("insertArticle()메서드 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	//글상세보기 메서드	
	public NoticeDTO getArticle(int notice_number) {
		NoticeDTO article=null;
		try {
			 con=pool.getConnection();
		
			 sql="select * from notice where notice_number=?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1, notice_number);
			 rs=pstmt.executeQuery();
			 
			 if(rs.next()) {
				    article=this.makeArticleFromResult();
			 }
		 }catch(Exception e) {
			System.out.println("getArticle() 에러유발=>"+e); 
		 }finally {
			 pool.freeConnection(con, pstmt, rs);
		 }
		 return article;
	}
	//접근지정자가 private가 되는 경우 외부에서 호출되면 안되고 내부에서만 호출되는 메서드 작성
	private NoticeDTO makeArticleFromResult() throws Exception {
		NoticeDTO article=new NoticeDTO();//new MemberDTO();필드별로 담기위해서
		article.setNotice_number(rs.getInt("notice_number"));
		article.setWriter(rs.getString("writer"));//부적합한 열입니다.->필드오타
		article.setRegist_date(rs.getTimestamp("regist_date"));//작성날짜
		article.setTitle(rs.getString("title"));//글제목
		article.setCategory(rs.getInt("category"));//카테고리
		article.setContent(rs.getString("content"));//글내용
		article.setFilename(rs.getString("filename"));//첨부 사진 이름
		
		return article;
	}
	
	//글수정할 데이터를 찾을 메서드
	public NoticeDTO updateGetArticle(int notice_number) {
		NoticeDTO article=null;
		 try {
			 con=pool.getConnection();
			 sql="select * from notice where notice_number=?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1, notice_number);
			 rs=pstmt.executeQuery();
			 if(rs.next()) {
					 article=makeArticleFromResult();
			 }
		 }catch(Exception e) {
			System.out.println("updateGetArticle() 에러유발=>"+e); 
		 }finally {
			 pool.freeConnection(con, pstmt, rs);
		 }
		 return article;
	}
	//if절 수정 필요(암호 안 쓰기 때문에)
	//글수정시켜주는 메서드 작성
	public int updateArticle(NoticeDTO article) {
		
		int x=-1;//게시물의 수정유무
		
		try {
			con=pool.getConnection();
			sql="select title, category, content, filename from notice where notice_number=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, article.getNotice_number());
			rs=pstmt.executeQuery();
		
			if(rs.next()) {
				
					sql="update notice set title=?, category=?, content=?, filename=? where notice_number=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, article.getTitle());
					pstmt.setInt(2, article.getCategory());
					pstmt.setString(3, article.getContent());
					pstmt.setString(4,article.getFilename());
					pstmt.setInt(5, article.getNotice_number());
					
					int update=pstmt.executeUpdate();
					System.out.println("게시판의 글수정 성공유무)=>"+update);
					x=1;//수정성공 성공
			}else {//암호가 존재하지 않은경우
				x=-1;
			}
		}catch(Exception e) {
			System.out.println("updateArticle()실행 오류=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	} 
	
	//게시물 삭제 메서드
	public int deleteArticle(int notice_number) {
		
		int x=-1;//게시물의 삭제유무
		
		try {
			con=pool.getConnection();
			sql="select * from notice where notice_number=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_number);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
					sql="delete from notice where notice_number=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, notice_number);
					int delete=pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무)=>"+delete);//1 성공, or 0 실패
					x=1;//삭제성공 
				}else {
					x=0;//삭제 실패
				}
		}catch(Exception e) {
			System.out.println("deleteArticle()실행 오류=>"+e);//Log객체
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	} 
	
}
