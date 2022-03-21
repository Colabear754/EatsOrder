package action.coupon;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;

public class CouponListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		CouponDAO couponProcess = new CouponDAO();
		
		HashMap<String, ArrayList<Object>> couponData = couponProcess.getCoupons(email);
		
		request.setAttribute("couponData", couponData);
		
		return "/couponList.jsp";
	}
}
