package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class CancelOrderAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문을 취소하는 액션클래스
		String order_number = request.getParameter("order_number");
		String orderer = (String) request.getSession().getAttribute("account");
		String resaon_cancellation = request.getParameter("resaon_cancellation");
		OrderDAO orderProcess = new OrderDAO();
		
		int result = orderProcess.cancelOrder(order_number, orderer, resaon_cancellation);
		
		request.setAttribute("result", result);
		
		return "/cancelOrder.jsp";
	}
}
