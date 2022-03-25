package action.coupon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;

public class RegistUserCouponProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 사용자가 쿠폰을 등록하는 액션클래스
		String coupon_id = request.getParameter("coupon_id");
		String owner_id = (String) request.getSession().getAttribute("owner_id");
		int available_count = Integer.parseInt(request.getParameter("available_count"));
		CouponDAO couponProcess = new CouponDAO();
		
		int result = couponProcess.registUserCoupon(coupon_id, owner_id, available_count);
		
		request.setAttribute("result", result);
		
		return "/registUserCouponProc.jsp";
	}
}
