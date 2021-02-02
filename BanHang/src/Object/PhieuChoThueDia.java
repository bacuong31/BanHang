package Object;

import java.sql.Date;
import java.time.LocalDate;

public class PhieuChoThueDia {
	private String MaPhieuThue;
	private LocalDate NgayThue;
	private LocalDate NgayHenTra;
	private String KhachHangThue;
	private String NhanVienTiepNhan;
	private String HoaDonDatCoc;
	private String TinhTrang;
	
	
	public String getTinhTrang() {
		return TinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		TinhTrang = tinhTrang;
	}
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
	public String getHoaDonDatCoc() {
		return HoaDonDatCoc;
	}
	public void setHoaDonDatCoc(String hoaDonDatCoc) {
		HoaDonDatCoc = hoaDonDatCoc;
	}

	
	public PhieuChoThueDia(String maPhieuThue, LocalDate ngayThue, LocalDate ngayHenTra, String khachHangThue,
			String nhanVienTiepNhan, String hoaDonDatCoc, String tinhTrang) {
		super();
		MaPhieuThue = maPhieuThue;
		NgayThue = ngayThue;
		NgayHenTra = ngayHenTra;
		KhachHangThue = khachHangThue;
		NhanVienTiepNhan = nhanVienTiepNhan;
		HoaDonDatCoc = hoaDonDatCoc;
		TinhTrang = tinhTrang;
	}
	public PhieuChoThueDia() {
		
	}
}
