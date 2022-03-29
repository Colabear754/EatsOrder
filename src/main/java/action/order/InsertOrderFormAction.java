package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class InsertOrderFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String orderer = (String) request.getSession().getAttribute("account");
		
		request.setAttribute("orderer", orderer);
		
		return "/orderForm.jsp";
	}
}
