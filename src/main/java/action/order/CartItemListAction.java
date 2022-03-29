package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.CartDTO;
import order.OrderDAO;

public class CartItemListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 추가된 품목 리스트를 조회하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		OrderDAO orderProcess = new OrderDAO();
		ArrayList<CartDTO> cartItems = orderProcess.getCartItems(orderer);
		
		request.setAttribute("cartItems", cartItems);
		
		return "/cartItemList.jsp";
	}
}
