package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.*;
import order.*;
import restaurant.RestaurantDAO;
import restaurant.RestaurantDTO;

public class CartAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 추가된 품목 리스트를 조회하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		boolean isOrderForm = Boolean.parseBoolean(request.getParameter("isOrderForm"));	// 주문 화면인지 아닌지 확인하기 위한 변수
		String point = request.getParameter("using_point");
		OrderDAO orderProcess = new OrderDAO();
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		RestaurantDAO rstProcess = new RestaurantDAO();
		ArrayList<CartDTO> items = orderProcess.getCartItems(orderer);
		ArrayList<CartItemDTO> cartItems = new ArrayList<>();
		int using_point = point != null ? Integer.parseInt(point) : 0;
		int rst_id = 0;
		String rst_name = "";
		int delivery_tip = 0;
		int total_price = 0;
		
		if (!items.isEmpty()) {
			RestaurantDTO rst = rstProcess.getRestaurantOfMenu(items.get(0).getMenu_id());
			rst_id = rst.getRst_id();
			rst_name = rst.getRst_name();
			delivery_tip = orderProcess.getDelivery_tip(items.get(0).getMenu_id());
		}
		
		for (CartDTO item : items) {
			MenuDTO menu = menuProcess.getMenu(item.getMenu_id());
			ArrayList<OptionInfoDTO> selectedOptions = orderProcess.getSelectedOptions(item.getBundle_id());
			int option_price = 0;
			
			for (OptionInfoDTO option : selectedOptions) {
				option_price += option.getPrice();
			}
			
			int quantity = item.getQuantity();
			int price = (menu.getPrice() + option_price) * quantity;
			total_price += price;
			
			cartItems.add(new CartItemDTO(menu.getMenu_id(), menu.getMenu_name(), selectedOptions, quantity, price));
		}
		
		request.setAttribute("cartItems", cartItems);
		request.setAttribute("rst_id", rst_id);
		request.setAttribute("rst_name", rst_name);
		request.setAttribute("using_point", using_point);
		request.setAttribute("delivery_tip", delivery_tip);
		request.setAttribute("total_price", total_price);
		request.setAttribute("isOrderForm", isOrderForm);
		
		return "/order/cart.jsp";
	}
}
