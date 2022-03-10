package restaurant;

public class DeliveryZoneDTO {
	private int zone_index;
	private int rst_id;
	private String sido;
	private String sigungu;
	private String bname;
	
	public DeliveryZoneDTO() {
		super();
	}
	
	public DeliveryZoneDTO(int zone_index, int rst_id, String sido, String sigungu, String bname) {
		this.zone_index = zone_index;
		this.rst_id = rst_id;
		this.sido = sido;
		this.sigungu = sigungu;
		this.bname = bname;
	}
	public int getZone_index() {
		return zone_index;
	}
	public void setZone_index(int zone_index) {
		this.zone_index = zone_index;
	}
	public int getRst_id() {
		return rst_id;
	}
	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
}
