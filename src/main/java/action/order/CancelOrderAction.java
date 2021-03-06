package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;
import menu.MenuAndOptionDAO;
import menu.MenuDTO;
import menu.OptionInfoDTO;
import order.OrderDAO;
import order.OrderDetailDTO;

public class CancelOrderAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문을 취소하는 액션클래스
		String order_number = request.getParameter("order_number");
		String orderer = (String) request.getSession().getAttribute("account");
		String reason_cancellation = request.getParameter("reason_cancellation");
		OrderDAO orderProcess = new OrderDAO();
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<OrderDetailDTO> items = orderProcess.getOrderedItemList(order_number);
		int total_price = 0;
		
		for (OrderDetailDTO item : items) {
			MenuDTO menu = menuProcess.getMenu(item.getMenu_id());
			ArrayList<OptionInfoDTO> orderedOptions = orderProcess.getOrderedOptions(item.getBundle_id());
			int option_price = 0;
			
			for (OptionInfoDTO option : orderedOptions) {
				option_price += option.getPrice();
			}
			
			int quantity = item.getQuantity();
			int price = (menu.getPrice() + option_price) * quantity;
			total_price += price;
		}
		
		memberDAO.updatePoint(orderer, total_price / 100, 0);
		int result = orderProcess.cancelOrder(order_number, orderer, reason_cancellation);
		
		request.setAttribute("result", result);
		
		return "/component/result.jsp";
	}
}
