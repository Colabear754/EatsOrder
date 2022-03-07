package member;

import java.sql.Date;

public class WithdrawMemberDTO {
	private String email;
	private Date withdraw_date;
	private String reason_withdraw;

	public WithdrawMemberDTO() {
		super();
	}

	public WithdrawMemberDTO(String email, Date withdraw_date, String reason_withdraw) {
		this.email = email;
		this.withdraw_date = withdraw_date;
		this.reason_withdraw = reason_withdraw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getWithdraw_date() {
		return withdraw_date;
	}

	public void setWithdraw_date(Date withdraw_date) {
		this.withdraw_date = withdraw_date;
	}

	public String getReason_withdraw() {
		return reason_withdraw;
	}

	public void setReason_withdraw(String reason_withdraw) {
		this.reason_withdraw = reason_withdraw;
	}
}
