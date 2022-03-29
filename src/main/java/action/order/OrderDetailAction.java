package action.order;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class OrderDetailAction implements CommandAction {

	@SuppressWarnings("unchecked")
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문 상세정보를 조회하는 액션클래스
		request.setCharacterEncoding("utf-8");
		String order_number = request.getParameter("order_number");
		OrderDAO orderProcess = new OrderDAO();
		HashMap<String, Object> result = orderProcess.getOrderDetail(order_number);
		ArrayList<String> menuList = (ArrayList<String>) result.get("menuList");
		ArrayList<String> optionList = (ArrayList<String>) result.get("optionList");
		ArrayList<Integer> quantityList = (ArrayList<Integer>) result.get("quantityList");
		int price = (int) result.get("price");
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("optionList", optionList);
		request.setAttribute("quantityList", quantityList);
		request.setAttribute("price", price);
		
		return "/orderDetail.jsp";
	}
}
