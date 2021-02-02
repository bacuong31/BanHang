package Object;

import java.sql.Date;
import java.time.LocalDate;

public class YeuCauNhapDia {
	private String MaYeuCau;
	private LocalDate NgayYeuCau;
	private String DonViCungCap;
	private String TrangThaiNhap;
	public String getMaYeuCau() {
		return MaYeuCau;
	}
	public void setMaYeuCau(String maYeuCau) {
		MaYeuCau = maYeuCau;
	}
	public LocalDate getNgayYeuCau() {
		return NgayYeuCau;
	}
	public void setNgayYeuCau(LocalDate ngayYeuCau) {
		NgayYeuCau = ngayYeuCau;
	}
	public String getDonViCungCap() {
		return DonViCungCap;
	}
	public void setDonViCungCap(String donViCungCap) {
		DonViCungCap = donViCungCap;
	}
	public String getTrangThaiNhap() {
		return TrangThaiNhap;
	}
	public void setTrangThaiNhap(String trangThaiNhap) {
		TrangThaiNhap = trangThaiNhap;
	}
	public YeuCauNhapDia(String maYeuCau, LocalDate ngayYeuCau, String donViCungCap, String trangThaiNhap) {
		super();
		MaYeuCau = maYeuCau;
		NgayYeuCau = ngayYeuCau;
		DonViCungCap = donViCungCap;
		TrangThaiNhap = trangThaiNhap;
	}
	public YeuCauNhapDia() {}
}
