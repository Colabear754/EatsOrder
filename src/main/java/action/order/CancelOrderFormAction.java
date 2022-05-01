package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.MenuDTO;
import menu.OptionInfoDTO;
import order.OrderDAO;
import order.OrderDetailDTO;

public class CancelOrderFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문 취소 폼
		String order_number = request.getParameter("order_number");
		OrderDAO orderProcess = new OrderDAO();
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		ArrayList<OrderDetailDTO> items = orderProcess.getOrderedItemList(order_number);
		String orderedItemString = "";
		
		for (OrderDetailDTO item : items) {
			MenuDTO menu = menuProcess.getMenu(item.getMenu_id());
			ArrayList<OptionInfoDTO> orderedOptions = orderProcess.getOrderedOptions(item.getBundle_id());
			
			orderedItemString += menu.getMenu_name();
			
			for (OptionInfoDTO option : orderedOptions) {
				if (orderedOptions.indexOf(option) == 0) {
					orderedItemString += "(" + option.getOption_name() + ", ";
				} else if (orderedOptions.indexOf(option) == orderedOptions.size() - 1) {
					orderedItemString += option.getOption_name() + "), ";
				} else {
					orderedItemString += option.getOption_name() + ", ";
				}
			}
			
			orderedItemString += ", ";
		}
		
		orderedItemString = orderedItemString.substring(0, orderedItemString.length() - 2);
		
		request.setAttribute("order_number", order_number);
		request.setAttribute("order_item_list", orderedItemString);
		
		return "/order/cancelOrderForm.jsp";
	}
}
