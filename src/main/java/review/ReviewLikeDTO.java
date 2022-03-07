package review;

public class ReviewLikeDTO {
	private int number;
	private String email;

	public ReviewLikeDTO() {
		super();
	}

	public ReviewLikeDTO(int number, String email) {
		this.number = number;
		this.email = email;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
