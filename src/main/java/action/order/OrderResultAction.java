package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.MenuDTO;
import menu.OptionInfoDTO;
import order.CartItemDTO;
import order.OrderDAO;
import order.OrderDetailDTO;
import order.OrderHistoryDTO;
import restaurant.RestaurantDAO;

public class OrderResultAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문결과 페이지 액션클래스
		String order_number = request.getParameter("order_number");
		String rst_id = request.getParameter("rst_id");
		OrderDAO orderProcess = new OrderDAO();
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		RestaurantDAO rstProcess = new RestaurantDAO();
		OrderHistoryDTO orderInfo = orderProcess.getOrderHistory(order_number);
		ArrayList<OrderDetailDTO> items = orderProcess.getOrderedItemList(order_number);
		ArrayList<CartItemDTO> orderedItemList = new ArrayList<>();
		int delivery_tip = orderProcess.getDelivery_tip(items.get(0).getMenu_id());
		int total_price = 0;
		String orderedItemString = "";
		String rst_name = rstProcess.getRestaurant(Integer.parseInt(rst_id)).getRst_name();
		
		for (OrderDetailDTO item : items) {
			MenuDTO menu = menuProcess.getMenu(item.getMenu_id());
			ArrayList<OptionInfoDTO> orderedOptions = orderProcess.getOrderedOptions(item.getBundle_id());
			int option_price = 0;
			
			orderedItemString += menu.getMenu_name();
			
			for (OptionInfoDTO option : orderedOptions) {
				option_price += option.getPrice();
				if (orderedOptions.indexOf(option) == 0) {
					orderedItemString += "(" + option.getOption_name() + ", ";
				} else if (orderedOptions.indexOf(option) == orderedOptions.size() - 1) {
					orderedItemString += option.getOption_name() + "), ";
				} else {
					orderedItemString += option.getOption_name() + ", ";
				}
			}
			
			orderedItemString += ", ";
			
			int quantity = item.getQuantity();
			int price = (menu.getPrice() + option_price) * quantity;
			total_price += price;
			
			orderedItemList.add(new CartItemDTO(menu.getMenu_name(), orderedOptions, quantity, price));
		}
		
		orderedItemString = orderedItemString.substring(0, orderedItemString.length() - 2);
		
		request.setAttribute("orderedItems", orderedItemString);
		request.setAttribute("rst_name", rst_name);
		request.setAttribute("orderInfo", orderInfo);
		request.setAttribute("delivery_tip", delivery_tip);
		request.setAttribute("total_price", total_price);
		
		return "/order/orderResult.jsp";
	}
}
