package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.*;
import order.*;

public class CartAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 추가된 품목 리스트를 조회하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		OrderDAO orderProcess = new OrderDAO();
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		ArrayList<CartDTO> items = orderProcess.getCartItems(orderer);
		ArrayList<CartItemDTO> cartItems = new ArrayList<>();
		int delivery_tip = 0;
		
		if (!items.isEmpty()) {
			delivery_tip = orderProcess.getDelivery_tip(items.get(0).getMenu_id());
		}
		int total_price = 0;
		
		for (CartDTO item : items) {
			MenuDTO menu = menuProcess.getMenu(item.getMenu_id());
			OptionInfoDTO option = menuProcess.getOption(item.getOption_id());
			int quantity = item.getQuantity();
			int price = (menu.getPrice() + option.getPrice()) * quantity;
			total_price += price;
			cartItems.add(new CartItemDTO(menu.getMenu_name(), option.getOption_name(), quantity, price));
		}
		
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("delivery_tip", delivery_tip);
		request.setAttribute("total_price", total_price);
		
		return "/order/cart.jsp";
	}
}
