package Object;

public class DonViCungCap {
	private String MaDVCC;
	private String TenDVCC;
	private String SDT;
	private String DiaChi;
	public String getMaDVCC() {
		return MaDVCC;
	}
	public void setMaDVCC(String maDVCC) {
		MaDVCC = maDVCC;
	}
	public String getTenDVCC() {
		return TenDVCC;
	}
	public void setTenDVCC(String tenDVCC) {
		TenDVCC = tenDVCC;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public DonViCungCap(String maDVCC, String tenDVCC, String sDT, String diaChi) {
		super();
		MaDVCC = maDVCC;
		TenDVCC = tenDVCC;
		SDT = sDT;
		DiaChi = diaChi;
	}
	
	public DonViCungCap() {}
}
