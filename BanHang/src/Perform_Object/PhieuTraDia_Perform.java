package Perform_Object;

import java.time.LocalDate;

public class PhieuTraDia_Perform {
	private String MaPhieuTra;
	private String PhieuThamChieu;
	private LocalDate NgayTra;
	private String NhanVienTiepNhan;
	private Double TienBoiThuong;
	private Double TienHoanTra;
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
	public LocalDate getNgayTra() {
		return NgayTra;
	}
	public void setNgayTra(LocalDate ngayTra) {
		NgayTra = ngayTra;
	}
	public String getNhanVienTiepNhan() {
		return NhanVienTiepNhan;
	}
	public void setNhanVienTiepNhan(String nhanVienTiepNhan) {
		NhanVienTiepNhan = nhanVienTiepNhan;
	}
	public Double getTienBoiThuong() {
		return TienBoiThuong;
	}
	public void setTienBoiThuong(Double tienBoiThuong) {
		TienBoiThuong = tienBoiThuong;
	}
	public Double getTienHoanTra() {
		return TienHoanTra;
	}
	public void setTienHoanTra(Double tienHoanTra) {
		TienHoanTra = tienHoanTra;
	}
	/**
	 * @param maPhieuTra
	 * @param phieuThamChieu
	 * @param ngayTra
	 * @param nhanVienTiepNhan
	 * @param tienBoiThuong
	 * @param tienHoanTra
	 */
	public PhieuTraDia_Perform(String maPhieuTra, String phieuThamChieu, LocalDate ngayTra, String nhanVienTiepNhan,
			Double tienBoiThuong, Double tienHoanTra) {
		super();
		MaPhieuTra = maPhieuTra;
		PhieuThamChieu = phieuThamChieu;
		NgayTra = ngayTra;
		NhanVienTiepNhan = nhanVienTiepNhan;
		TienBoiThuong = tienBoiThuong;
		TienHoanTra = tienHoanTra;
	}
	public PhieuTraDia_Perform() {}
}
