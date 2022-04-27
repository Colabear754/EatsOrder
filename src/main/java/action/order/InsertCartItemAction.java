package action.order;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class InsertCartItemAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 품목을 추가하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		String[] selectedOptions = request.getParameterValues("options");
		int[] options = {};
		
		if (selectedOptions != null) {
			options = Arrays.stream(selectedOptions).mapToInt(Integer::parseInt).toArray();
		}
		
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		OrderDAO orderProcess = new OrderDAO();
		
		int result = orderProcess.insertCartItem(orderer, menu_id, options, quantity);
		
		request.setAttribute("result", result);
		
		return "/order/insertCartItem.jsp";
	}
}
