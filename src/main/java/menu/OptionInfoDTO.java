package menu;

public class OptionInfoDTO {
	private int option_id;
	private int group_id;
	private String option_name;
	private int price;
	private int enable;	// 0:품절, 1:선택가능

	public OptionInfoDTO() {
		super();
	}

	public OptionInfoDTO(int option_id, int group_id, String option_name, int price, int enable) {
		this.option_id = option_id;
		this.group_id = group_id;
		this.option_name = option_name;
		this.price = price;
		this.enable = enable;
	}

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}
}
