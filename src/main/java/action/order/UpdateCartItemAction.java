package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class UpdateCartItemAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 등록된 메뉴 수량을 수정하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		OrderDAO orderProcess = new OrderDAO();
		
		orderProcess.updateCartItem(orderer, menu_id, quantity);
		
		return "/updateCartItem.jsp";
	}
}
