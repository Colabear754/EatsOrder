package action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
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
		OrderDAO orderProcess = new OrderDAO();
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;
		
		ArrayList<OrderBasicInfoDTO> result = orderProcess.getOrderList(orderer, start, end);
		
		request.setAttribute("result", result);
		
		return "/member/orderList.jsp";
	}
}
