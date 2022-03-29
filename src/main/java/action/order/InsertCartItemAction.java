package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class InsertCartItemAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 품목을 추가하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		String temp = request.getParameter("option_id");
		int option_id;
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		OrderDAO orderProcess = new OrderDAO();
		
		if (temp == null || temp.isBlank()) {
			orderProcess.insertCartItem(orderer, menu_id, quantity);
		} else {
			option_id = Integer.parseInt(temp);
			orderProcess.insertCartItem(orderer, menu_id, option_id, quantity);
		}
		
		return "/insertCartItem.jsp";
	}
}
