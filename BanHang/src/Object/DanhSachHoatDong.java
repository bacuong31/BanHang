package Object;

public class DanhSachHoatDong {
	private String MaHoatDong;
	private String TenHoatDong;
	public String getMaHoatDong() {
		return MaHoatDong;
	}
	public void setMaHoatDong(String maHoatDong) {
		MaHoatDong = maHoatDong;
	}
	public String getTenHoatDong() {
		return TenHoatDong;
	}
	public void setTenHoatDong(String tenHoatDong) {
		TenHoatDong = tenHoatDong;
	}
	public DanhSachHoatDong() {
		
	}
	public DanhSachHoatDong(String maHoatDong, String tenHoatDong) {
		super();
		MaHoatDong = maHoatDong;
		TenHoatDong = tenHoatDong;
	}
	
}
