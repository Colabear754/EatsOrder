package action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.CartDTO;
import order.OrderDAO;
import restaurant.RestaurantDAO;

public class CartButtonAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 헤더의 장바구니 버튼을 눌렀을 때의 동작에 관련된 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		OrderDAO orderProcess = new OrderDAO();
		RestaurantDAO rstProcess = new RestaurantDAO();
		ArrayList<CartDTO> items = orderProcess.getCartItems(orderer);
		int rst_id = 0;
		
		if (!items.isEmpty()) {
			rst_id = rstProcess.getRestaurantOfMenu(items.get(0).getMenu_id()).getRst_id();
		}
		
		request.setAttribute("result", rst_id);
		
		return "/component/result.jsp";
	}
}
