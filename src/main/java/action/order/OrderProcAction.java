package action.order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
import member.MemberDAO;
import menu.MenuAndOptionDAO;
import menu.MenuDTO;
import menu.OptionInfoDTO;
import order.CartDTO;
import order.OrderDAO;
import order.OrderHistoryDTO;

public class OrderProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문을 추가하는 액션클래스
		request.setCharacterEncoding("utf-8");
		String orderer = request.getParameter("orderer");
		String rst_id = request.getParameter("rst_id");
		String destination = request.getParameter("destination");
		String coupon_id = request.getParameter("coupon_id");
		String used_point_str = request.getParameter("used_point");
		String payment_method = request.getParameter("payment_method");
		String order_request = request.getParameter("order_request");
		OrderDAO orderProcess = new OrderDAO();
		MemberDAO memberProcess = new MemberDAO();
		CouponDAO couponProcess = new CouponDAO();
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		int used_point;
		ArrayList<CartDTO> items = orderProcess.getCartItems(orderer);
		int total_price = 0;	// 포인트 적립을 위한 전체 가격(배달팁 제외)
		
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
		}
		
		if (used_point_str.isBlank() || used_point_str == null) {
			used_point = 0;
		} else {
			used_point = Integer.parseInt(used_point_str);
		}

		String order_number = orderProcess.insertOrder(orderer, destination, coupon_id, used_point, payment_method,
				order_request, 1);
		OrderHistoryDTO order = orderProcess.getOrderHistory(order_number);
		
		if (order != null) {
			memberProcess.updatePoint(orderer, used_point, total_price / 100);
			orderProcess.cleanCart(orderer);
			
			if (coupon_id == null || coupon_id.isBlank()) {
				couponProcess.useCoupon(coupon_id, orderer);
			}
		}
		
		request.setAttribute("order_number", order_number);
		request.setAttribute("rst_id", rst_id);
		
		return "/order/orderProc.jsp";
	}
}
