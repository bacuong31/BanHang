package Object;

import java.sql.Date;

public class PhieuTraDia {
	private String MaPhieuTra;
	private String PhieuThamChieu;
	private Date NgayTra;
	private String MaNhanVien;
	private String HoaDonBoiThuong;
	private String HoaDonHoanTra;
	
	public PhieuTraDia(String maPhieuTra, String phieuThamChieu, Date ngayTra, String maNhanVien,
			String hoaDonBoiThuong, String hoaDonHoanTra) {
		super();
		MaPhieuTra = maPhieuTra;
		PhieuThamChieu = phieuThamChieu;
		NgayTra = ngayTra;
		MaNhanVien = maNhanVien;
		HoaDonBoiThuong = hoaDonBoiThuong;
		HoaDonHoanTra = hoaDonHoanTra;
	}
	
	public PhieuTraDia() {}
	
	public String getMaPhieuTra() {
		return MaPhieuTra;
	}
	public void setMaPhieuTra(String maPhieuTra) {
		MaPhieuTra = maPhieuTra;
	}
	public String getPhieuThamChieu() {
		return PhieuThamChieu;
	}
	public void setPhieuThamChieu(String phieuThamChieu) {
		PhieuThamChieu = phieuThamChieu;
	}
	public Date getNgayTra() {
		return NgayTra;
	}
	public void setNgayTra(Date ngayTra) {
		NgayTra = ngayTra;
	}
	public String getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	public String getHoaDonBoiThuong() {
		return HoaDonBoiThuong;
	}
	public void setHoaDonBoiThuong(String hoaDonBoiThuong) {
		HoaDonBoiThuong = hoaDonBoiThuong;
	}
	public String getHoaDonHoanTra() {
		return HoaDonHoanTra;
	}
	public void setHoaDonHoanTra(String hoaDonHoanTra) {
		HoaDonHoanTra = hoaDonHoanTra;
	}
	
	
}
