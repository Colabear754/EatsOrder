package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
import member.MemberDAO;
import member.MemberInfoDTO;
import order.OrderBasicInfoDTO;
import order.OrderDAO;

public class OrderListAction implements CommandAction {
	private static final int PAGESIZE = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문 목록을 조회하는 액션클래스
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		String orderer = (String) request.getSession().getAttribute("account");
		MemberDAO memberProcess = new MemberDAO();
		CouponDAO couponProcess = new CouponDAO();
		OrderDAO orderProcess = new OrderDAO();
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;
		
		ArrayList<OrderBasicInfoDTO> result = orderProcess.getOrderList(orderer, start, end);
		
		for (OrderBasicInfoDTO order : result) {
			long elapsed_time = (System.currentTimeMillis() - order.getPay_date().getTime()) / 1000 / 60;	// 주문으로부터 경과한 시간(분)
			
			order.setElapsed_time(elapsed_time);
		}

		MemberInfoDTO member = memberProcess.getMember(orderer);
		int couponCount = couponProcess.getCouponCount(orderer);

		request.setAttribute("member", member);
		request.setAttribute("couponCount", couponCount);
		request.setAttribute("result", result);
		
		return "/member/orderList.jsp";
	}
}
