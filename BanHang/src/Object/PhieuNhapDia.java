package Object;

public class PhieuNhapDia {
	private String MaPhieuNhap;
	private String YeuCauThucHien;
	private String HoaDonThanhToan;
	public String getMaPhieuNhap() {
		return MaPhieuNhap;
	}
	public void setMaPhieuNhap(String maPhieuNhap) {
		MaPhieuNhap = maPhieuNhap;
	}
	public String getYeuCauThucHien() {
		return YeuCauThucHien;
	}
	public void setYeuCauThucHien(String yeuCauThucHien) {
		YeuCauThucHien = yeuCauThucHien;
	}
	public String getHoaDonThanhToan() {
		return HoaDonThanhToan;
	}
	public void setHoaDonThanhToan(String hoaDonThanhToan) {
		HoaDonThanhToan = hoaDonThanhToan;
	}
	public PhieuNhapDia(String maPhieuNhap, String yeuCauThucHien, String hoaDonThanhToan) {
		super();
		MaPhieuNhap = maPhieuNhap;
		YeuCauThucHien = yeuCauThucHien;
		HoaDonThanhToan = hoaDonThanhToan;
	}
	public PhieuNhapDia() {
		
	}
}
