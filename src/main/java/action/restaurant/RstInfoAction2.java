package action.restaurant;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;
import restaurant.RestaurantDAO;
import restaurant.RestaurantDTO;
import review.*;
import menu.*;

public class RstInfoAction2 implements CommandAction{
	
	private static final int PAGESIZE = 10; // 페이지 크기
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		RestaurantDAO rstProcess = new RestaurantDAO();
		RestaurantDTO restaurant = rstProcess.getRestaurant(rst_id);
		
		request.setAttribute("restaurant", restaurant);
		
		//리뷰리스트액션 추가
		// 리뷰와 리뷰 댓글 목록을 조회하는 페이지에 대한 액션클래스
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum"); // 현재 페이지 번호
		//int rst_id = Integer.parseInt(request.getParameter("rst_id")); // 매장ID
		boolean onlyPhotoReview = Boolean.parseBoolean(request.getParameter("onlyPhotoReview")); // 사진 리뷰만 조회
		ReviewDAO reviewProcess = new ReviewDAO();
		MemberDAO memberProcess = new MemberDAO();

		if (pageNum == null) { // 현재 페이지번호가 null이면 1으로 지정
			pageNum = "1";
		}

		// 정수변환 및 불러올 리뷰번호 시작값과 끝값 지정
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;

		ArrayList<ReviewDTO> reviewList = reviewProcess.getReviews(rst_id, onlyPhotoReview, start, end); // 리뷰 리스트
		ArrayList<ReplyDTO> replyList = reviewProcess.getReplies(rst_id); // 사장님 댓글 리스트
		ArrayList<String> writerNicknameList = new ArrayList<>(); // 작성자 닉네임 리스트
		ArrayList<Integer> likeCountList = new ArrayList<>(); // 리뷰 좋아요 개수 리스트

		for (ReviewDTO review : reviewList) { // 리뷰 리스트에 있는 각 리뷰에 해당하는 작성자 닉네임과 좋아요 개수를 별도의 ArrayList에 저장
			writerNicknameList.add(memberProcess.getNickname(reviewProcess.getReviewWriter(review.getReview_number())));
			likeCountList.add(reviewProcess.getLikeCount(review.getReview_number()));
		}

		int reviewCount = reviewList.size(); // 리뷰 개수
		int replyCount = replyList.size(); // 사장님 댓글 개수

		System.out.println(onlyPhotoReview ? "전체 리뷰 개수 : " : "사진 리뷰 개수 : " + reviewCount); // 사진 리뷰만 조회하는지 여부에 따라 조회한 리뷰 개수를 콘솔에 출력

		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("replyCount", replyCount);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("writerNicknameList", writerNicknameList);
		request.setAttribute("likeCountList", likeCountList);
		request.setAttribute("onlyPhotoReview", onlyPhotoReview);		
		
		//메뉴리스트액션추가
		// 메뉴 리스트를 조회하는 액션클래스
		request.setCharacterEncoding("utf-8");
		//int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		ArrayList<MenuCategoryDTO> categoryList = menuProcess.getMenuCategories(rst_id);
		ArrayList<MenuList> menuList = new ArrayList<>();
		
		//우림 추가---------
		//int category_id = Integer.parseInt(request.getParameter("category_id"));
		//ArrayList<MenuDTO> menuInfoList=menuProcess.getMenuList(rst_id, category_id);
		//----------------
		for (MenuCategoryDTO category : categoryList) {
			MenuList categoryMenuList = new MenuList(category, menuProcess.getMenuList(rst_id, category.getCategory_id()));
			menuList.add(categoryMenuList);
		}

		request.setAttribute("menuList", menuList);
		
		
		return "/rstInfo.jsp";
	}
	
		class MenuList {
			MenuCategoryDTO category;
			ArrayList<MenuDTO> menuList;
	
			public MenuList(MenuCategoryDTO category, ArrayList<MenuDTO> menuList) {
				this.category = category;
				this.menuList = menuList;
			}
	
			public MenuCategoryDTO getCategory() {
				return category;
			}
	
			public void setCategory(MenuCategoryDTO category) {
				this.category = category;
			}
	
			public ArrayList<MenuDTO> getMenuList() {
				return menuList;
			}
	
			public void setMenuList(ArrayList<MenuDTO> menuList) {
				this.menuList = menuList;
			}
		}
}
