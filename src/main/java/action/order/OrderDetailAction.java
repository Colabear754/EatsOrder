package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;
import order.OrderDetailInfoDTO;

public class OrderDetailAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문 상세정보를 조회하는 액션클래스
		request.setCharacterEncoding("utf-8");
		String order_number = request.getParameter("order_number");
		OrderDAO orderProcess = new OrderDAO();
//		OrderDetailInfoDTO result = orderProcess.getOrderDetailInfo(order_number);
//		ArrayList<String> menuList = result.getMenuList();
//		ArrayList<String> optionList = result.getOptionList();
//		ArrayList<Integer> quantityList = result.getQuantityList();
//		int price = result.getPrice();
//		
//		request.setAttribute("menuList", menuList);
//		request.setAttribute("optionList", optionList);
//		request.setAttribute("quantityList", quantityList);
//		request.setAttribute("price", price);
		
		return "/orderDetail.jsp";
	}
}
