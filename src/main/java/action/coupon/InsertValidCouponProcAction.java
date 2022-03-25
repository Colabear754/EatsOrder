package action.coupon;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;

public class InsertValidCouponProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 관리자가 유효쿠폰을 추가하는 액션 클래스
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		String password = request.getParameter("password");
		String coupon_id = request.getParameter("coupon_id");
		String coupon_name = request.getParameter("coupon_name");
		String coupon_info = request.getParameter("coupon_info");
		int available_price = Integer.parseInt(request.getParameter("available_price"));
		int discount_amount = Integer.parseInt(request.getParameter("discount_amount"));
		Date expiration_date = Date.valueOf(request.getParameter("expiration_date"));
		CouponDAO couponProcess = new CouponDAO();

		int result = couponProcess.insertValidCoupon(coupon_id, coupon_name, coupon_info, available_price, discount_amount,
				expiration_date, admin_id, password);
		
		request.setAttribute("result", result);
		
		return "/insertValidCouponProc.jsp";
	}
}
