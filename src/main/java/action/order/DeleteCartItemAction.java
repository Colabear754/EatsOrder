package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import order.OrderDAO;

public class DeleteCartItemAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 장바구니에 등록된 메뉴를 삭제하는 액션클래스
		String orderer = (String) request.getSession().getAttribute("account");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		OrderDAO orderProcess = new OrderDAO();
		
		int result = orderProcess.deleteCartItem(orderer, menu_id);
		
		request.setAttribute("result", result);
		
		return "/component/result.jsp";
	}
}
