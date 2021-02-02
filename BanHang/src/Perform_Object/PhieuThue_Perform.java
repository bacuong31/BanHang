package Perform_Object;

import java.time.LocalDate;

public class PhieuThue_Perform {

	private String MaPhieuThue;
	private LocalDate NgayThue;
	private LocalDate NgayHenTra;
	private String KhachHangThue;
	private String NhanVienTiepNhan;
	private Double HoaDonDatCoc;
	private String TrangThai;
	public String getMaPhieuThue() {
		return MaPhieuThue;
	}
	public void setMaPhieuThue(String maPhieuThue) {
		MaPhieuThue = maPhieuThue;
	}
	public LocalDate getNgayThue() {
		return NgayThue;
	}
	public void setNgayThue(LocalDate ngayThue) {
		NgayThue = ngayThue;
	}
	public LocalDate getNgayHenTra() {
		return NgayHenTra;
	}
	public void setNgayHenTra(LocalDate ngayHenTra) {
		NgayHenTra = ngayHenTra;
	}
	public String getKhachHangThue() {
		return KhachHangThue;
	}
	public void setKhachHangThue(String khachHangThue) {
		KhachHangThue = khachHangThue;
	}
	public String getNhanVienTiepNhan() {
		return NhanVienTiepNhan;
	}
	public void setNhanVienTiepNhan(String nhanVienTiepNhan) {
		NhanVienTiepNhan = nhanVienTiepNhan;
	}
	public Double getHoaDonDatCoc() {
		return HoaDonDatCoc;
	}
	public void setHoaDonDatCoc(Double hoaDonDatCoc) {
		HoaDonDatCoc = hoaDonDatCoc;
	}
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	public PhieuThue_Perform(String maPhieuThue, LocalDate ngayThue, LocalDate ngayHenTra, String khachHangThue,
			String nhanVienTiepNhan, Double hoaDonDatCoc, String trangThai) {
		super();
		MaPhieuThue = maPhieuThue;
		NgayThue = ngayThue;
		NgayHenTra = ngayHenTra;
		KhachHangThue = khachHangThue;
		NhanVienTiepNhan = nhanVienTiepNhan;
		HoaDonDatCoc = hoaDonDatCoc;
		TrangThai = trangThai;
	}
	public PhieuThue_Perform() {}
	
}
