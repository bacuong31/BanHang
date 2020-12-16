package Object;

import java.sql.Date;

public class YeuCauNhapDia {
	private String MaYeuCau;
	private Date NgayYeuCau;
	private String DonViCungCap;
	private String TrangThaiNhap;
	public String getMaYeuCau() {
		return MaYeuCau;
	}
	public void setMaYeuCau(String maYeuCau) {
		MaYeuCau = maYeuCau;
	}
	public Date getNgayYeuCau() {
		return NgayYeuCau;
	}
	public void setNgayYeuCau(Date ngayYeuCau) {
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
	public YeuCauNhapDia(String maYeuCau, Date ngayYeuCau, String donViCungCap, String trangThaiNhap) {
		super();
		MaYeuCau = maYeuCau;
		NgayYeuCau = ngayYeuCau;
		DonViCungCap = donViCungCap;
		TrangThaiNhap = trangThaiNhap;
	}
	public YeuCauNhapDia() {}
}
