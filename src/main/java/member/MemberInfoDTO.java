package member;

import java.sql.Date;

public class MemberInfoDTO {
	private String email;
	private String nickname;
	private String phone;
	private String membership;
	private int point;
	private Date join_date;
	private Date withdraw_date;
	private int receive_marketing;

	public MemberInfoDTO() {
		super();
	}
	
	public MemberInfoDTO(String email, String nickname, String phone, String membership, int point, Date join_date,
			Date withdraw_date, int receive_marketing) {
		this.email = email;
		this.nickname = nickname;
		this.phone = phone;
		this.membership = membership;
		this.point = point;
		this.join_date = join_date;
		this.withdraw_date = withdraw_date;
		this.receive_marketing = receive_marketing;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public Date getWithdraw_date() {
		return withdraw_date;
	}

	public void setWithdraw_date(Date withdraw_date) {
		this.withdraw_date = withdraw_date;
	}

	public int getReceive_marketing() {
		return receive_marketing;
	}

	public void setReceive_marketing(int receive_marketing) {
		this.receive_marketing = receive_marketing;
	}
}
