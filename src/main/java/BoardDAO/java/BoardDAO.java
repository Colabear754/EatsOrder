package BoardDAO.java;

//DBConnectionMgr(DB접속,관리),BoardDTO(매개변수,반환형,데이터를 담는 역할)

import java.sql.*;
import java.util.*;

public class BoardDAO {//MemberDAO

	private DBConnectionMgr pool=null;//1.연결객체선언
	//공통
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;//select
	private String sql="";//실행시킬 SQL구문 저장
	
	//2.생성자를 통해서 연결=->의존관계
	public BoardDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		}catch(Exception e) {
		   System.out.println("DB접속 오류=>"+e);	
		}
	}
	
	//3.메서드 작성(페이징 처리를 위한 메서드 작성)=>총레코드수(=총게시물수=총회원수)
	//select count(*) from board;  select count(*) from member
	public int getArticleCount() {//getmemberCount()->MemberDAO에서 작성
		int x=0;
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				x=rs.getInt(1);//변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X
			}
		}catch(Exception e) {
			System.out.println("getArticleCount()에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//2.글목록보기에 대한 메서드구현->레코드 한개이상->한 페이지당 10개씩 끊어서 보여준다.
	//1)레코드의 시작번호    2)불러올 레코드의 갯수(ex 10,20,30)
	//public List<MemberDTO> getMemberList(int start,int end){
	public List<BoardDTO> getArticles(int start,int end){
		 List<BoardDTO> articleList=null;//ArrayList articleList=null;//(0)
		 
		 try {
			 con=pool.getConnection();
			 /*
			  * 그룹번호가 가장 최신의 글을 중심으로 정렬하되,만약에 level이 같은 경우에는
			  * step값으로 오름차순을 통해서 몇번째 레코드번호를 기준해서 몇개까지 정렬할것인가
			  * 를 지정해주는 SQL구문
			  */
			 sql="select * from board order by ref desc,re_step limit ?,?";
			 pstmt=con.prepareStatement(sql);
			 pstmt.setInt(1, start-1);//mysql은 레코드순번이 내부적으로 0부터 시작
			 pstmt.setInt(2, end);//불러와서 담을 갯수(ex 10)
			 rs=pstmt.executeQuery();
			 if(rs.next()) {//보여주는 결과가 있다면
				 articleList=new ArrayList(end);//10=>end갯수만큼 데이터를 담을 공간생성
				 do {
					 BoardDTO article=new BoardDTO();//new MemberDTO();필드별로 담기위해서
					 article.setNum(rs.getInt("num"));
					 article.setWriter(rs.getString("writer"));//부적합한 열입니다.->필드오타
					 article.setEmail(rs.getString("email"));
					 article.setSubject(rs.getString("subject"));//글제목
					 article.setPasswd(rs.getString("passwd"));
					 article.setReg_date(rs.getTimestamp("reg_date"));//작성날짜
					 article.setReadcount(rs.getInt("readcount"));//조회수 default->0
					 article.setRef(rs.getInt("ref"));//그룹번호->신규글과 답변글을 묶어주는 역할
					 article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
					 article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이) depth
					 article.setContent(rs.getString("content"));//글내용
					 article.setIp(rs.getString("ip"));//ip->request.getRemoteAddr()
					 //추가
					 articleList.add(article);//생략하면 데이터가 저장X->for문 에러유발(NullPointerException)
				 }while(rs.next());
			 }
		 }catch(Exception e) {
			System.out.println("getArticles() 에러유발=>"+e); 
		 }finally {
			 pool.freeConnection(con, pstmt, rs);
		 }
		 return articleList;//NullPointerException 조심
	}
	
}
