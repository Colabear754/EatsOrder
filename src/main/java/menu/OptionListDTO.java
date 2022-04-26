package menu;

import java.util.ArrayList;

public class OptionListDTO {
	OptionGroupDTO optionGroup;
	ArrayList<OptionInfoDTO> optionList;
	
	public OptionListDTO(OptionGroupDTO optionGroup, ArrayList<OptionInfoDTO> optionList) {
		this.optionGroup = optionGroup;
		this.optionList = optionList;
	}

	public OptionGroupDTO getOptionGroup() {
		return optionGroup;
	}

	public void setOptionGroup(OptionGroupDTO optionGroup) {
		this.optionGroup = optionGroup;
	}

	public ArrayList<OptionInfoDTO> getOptionList() {
		return optionList;
	}

	public void setOptionList(ArrayList<OptionInfoDTO> optionList) {
		this.optionList = optionList;
	}
}