package menu;

public class OptionGroupDTO {
	private int group_id;
	private int rst_id;
	private String group_name;
	private int essential;	// 1:필수선택, 0:필수아님

	public OptionGroupDTO() {
		super();
	}

	public OptionGroupDTO(int group_id, int rst_id, String group_name, int essential) {
		this.group_id = group_id;
		this.rst_id = rst_id;
		this.group_name = group_name;
		this.essential = essential;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public int getEssential() {
		return essential;
	}

	public void setEssential(int essential) {
		this.essential = essential;
	}
}
