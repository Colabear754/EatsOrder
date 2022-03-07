package menu;

public class OptionGroupDTO {
	private int group_id;
	private String group_name;
	private int essential;

	public OptionGroupDTO() {
		super();
	}

	public OptionGroupDTO(int group_id, String group_name, int essential) {
		this.group_id = group_id;
		this.group_name = group_name;
		this.essential = essential;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public int is_essential() {
		return essential;
	}

	public void set_essential(int essential) {
		this.essential = essential;
	}
}
