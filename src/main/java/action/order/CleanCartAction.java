package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class CleanCartAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니 비우기 액션클래스
		String account = (String) request.getSession().getAttribute("account");
		OrderDAO orderProcess = new OrderDAO();
		
		int result = orderProcess.cleanCart(account);
		
		request.setAttribute("result", result);
		
		return "/component/result.jsp";
	}
}
