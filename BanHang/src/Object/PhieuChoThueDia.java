package Object;

import java.sql.Date;

public class PhieuChoThueDia {
	private String MaPhieuThue;
	private Date NgayThue;
	private Date NgayHenTra;
	private String KhachHangThue;
	private String NhanVienTiepNhan;
	private String HoaDonDatCoc;
	
	public String getMaPhieuThue() {
		return MaPhieuThue;
	}
	public void setMaPhieuThue(String maPhieuThue) {
		MaPhieuThue = maPhieuThue;
	}
	public Date getNgayThue() {
		return NgayThue;
	}
	public void setNgayThue(Date ngayThue) {
		NgayThue = ngayThue;
	}
	public Date getNgayHenTra() {
		return NgayHenTra;
	}
	public void setNgayHenTra(Date ngayHenTra) {
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
	public String getHoaDonDatCoc() {
		return HoaDonDatCoc;
	}
	public void setHoaDonDatCoc(String hoaDonDatCoc) {
		HoaDonDatCoc = hoaDonDatCoc;
	}
	
	public PhieuChoThueDia(String maPhieuThue, Date ngayThue, Date ngayHenTra, String khachHangThue,
			String nhanVienTiepNhan, String hoaDonDatCoc) {
		super();
		MaPhieuThue = maPhieuThue;
		NgayThue = ngayThue;
		NgayHenTra = ngayHenTra;
		KhachHangThue = khachHangThue;
		NhanVienTiepNhan = nhanVienTiepNhan;
		HoaDonDatCoc = hoaDonDatCoc;
	}
	
	public PhieuChoThueDia() {
		
	}
}
