package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
import member.*;

public class UpdateMemberFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("account");
		MemberDAO memberProcess = new MemberDAO();
		CouponDAO couponProcess = new CouponDAO();
		
		MemberInfoDTO member = memberProcess.getMember(email);
		int couponCount = couponProcess.getCouponCount(email);
		
		request.setAttribute("member", member);
		request.setAttribute("couponCount", couponCount);
		
		return "/member/updateMemberForm.jsp";
	}
}
