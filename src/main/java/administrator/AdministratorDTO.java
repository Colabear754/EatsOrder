package administrator;

public class AdministratorDTO {
	private String admin_id;
	private String password;
	private int authority; // 예시) 1:최고관리자, 2:공지담당, 3:이벤트담당, 4:FAQ담당 등등

	public AdministratorDTO() {
		super();
	}

	public AdministratorDTO(String admin_id, String password, int authority) {
		this.admin_id = admin_id;
		this.password = password;
		this.authority = authority;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
