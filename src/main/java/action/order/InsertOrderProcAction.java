package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
import order.OrderDAO;

public class InsertOrderProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문을 추가하는 액션클래스
		String orderer = request.getParameter("orderer");
		String destination = request.getParameter("destination");
		String coupon_id = request.getParameter("coupon_id");
		int used_point = Integer.parseInt(request.getParameter("used_point"));
		String payment_method = request.getParameter("payment_method");
		String order_request = request.getParameter("order_request");
		OrderDAO orderProcess = new OrderDAO();
		CouponDAO couponProcess = new CouponDAO();

		int orderResult = orderProcess.insertOrder(orderer, destination, coupon_id, used_point, payment_method,
				order_request, used_point);
		
		if (orderResult > 0) {
			orderProcess.cleanCart(orderer);
			couponProcess.useCoupon(coupon_id, orderer);
		}
		
		return "insertOrderProc.jsp";
	}
}
