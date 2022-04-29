package action.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class OrderFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 주문 폼 액션클래스
		request.setCharacterEncoding("utf-8");
		String orderer = (String) request.getSession().getAttribute("account");
		String address = request.getParameter("address");
		String rst_id = request.getParameter("rst_id");
		MemberDAO memberProcess = new MemberDAO();
		
		String phone = memberProcess.getMember(orderer).getPhone();
		int point = memberProcess.getMember(orderer).getPoint();
		
		request.setAttribute("address", address);
		request.setAttribute("phone", phone);
		request.setAttribute("point", point);
		request.setAttribute("rst_id", rst_id);
		
		return "/order/orderForm.jsp";
	}
}
