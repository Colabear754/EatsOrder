package action.member;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
import coupon.CouponDetailDTO;

public class CouponListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 보유한 쿠폰 목록을 조회하는 액션클래스
		String email = (String) request.getSession().getAttribute("account");
		CouponDAO couponProcess = new CouponDAO();
		
		ArrayList<CouponDetailDTO> couponData = couponProcess.getCoupons(email);
		
		request.setAttribute("couponData", couponData);
		
		return "/member/couponList.jsp";
	}
}
