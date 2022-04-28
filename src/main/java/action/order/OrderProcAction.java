package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
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
		CouponDAO couponProcess = new CouponDAO();
		int used_point;
		
		if (used_point_str.isBlank() || used_point_str == null) {
			used_point = 0;
		} else {
			used_point = Integer.parseInt(used_point_str);
		}

		String order_number = orderProcess.insertOrder(orderer, destination, coupon_id, used_point, payment_method,
				order_request, used_point);
		
		OrderHistoryDTO order = orderProcess.getOrderHistory(order_number);
		
		if (order != null) {
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
