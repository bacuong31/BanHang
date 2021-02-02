package Perform_Object;

public class ChiTietTraDia_Perform {
	private String MaBangDia;
	private String TenBangDia;
	private String TrangThai;
	public String getMaBangDia() {
		return MaBangDia;
	}
	public void setMaBangDia(String maBangDia) {
		MaBangDia = maBangDia;
	}
	public String getTenBangDia() {
		return TenBangDia;
	}
	public void setTenBangDia(String tenBangDia) {
		TenBangDia = tenBangDia;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public ChiTietTraDia_Perform(String maBangDia, String tenBangDia, String trangThai) {
		super();
		MaBangDia = maBangDia;
		TenBangDia = tenBangDia;
		TrangThai = trangThai;
	}
	public ChiTietTraDia_Perform() {}
}
