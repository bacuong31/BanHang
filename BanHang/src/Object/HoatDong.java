package Object;


import java.sql.Timestamp;

public class HoatDong {
	private String NhanVienThucHien;
	private Timestamp ThoiGianThucHien;
	private String HoatDong;
	public String getNhanVienThucHien() {
		return NhanVienThucHien;
	}
	public void setNhanVienThucHien(String nhanVienThucHien) {
		NhanVienThucHien = nhanVienThucHien;
	}
	public Timestamp getThoiGianThucHien() {
		return ThoiGianThucHien;
	}
	public void setThoiGianThucHien(Timestamp thoiGianThucHien) {
		ThoiGianThucHien = thoiGianThucHien;
	}
	public String getHoatDong() {
		return HoatDong;
	}
	public void setHoatDong(String hoatDong) {
		HoatDong = hoatDong;
	}
	public HoatDong() {
		
	}
	public HoatDong(String nhanVienThucHien, Timestamp thoiGianThucHien, String hoatDong) {
		NhanVienThucHien = nhanVienThucHien;
		ThoiGianThucHien = thoiGianThucHien;
		HoatDong = hoatDong;
	}
	
}
