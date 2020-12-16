package Object;

import java.sql.Date;

public class KhachHang {
	private String MaKH;
	private String HoTen;
	private Date NgaySinh;
	private boolean LaKHQuen;
	private double TienDu;
	
	public KhachHang() {}
	
	public KhachHang(String makh, String hoten, Date ngaysinh, Boolean lakhQuen, double tiendu) {
		MaKH = makh;
		HoTen = hoten;
		NgaySinh = ngaysinh;
		LaKHQuen = lakhQuen;
		TienDu = tiendu;
	}
	
	public void DangKyKHQuen() {
		LaKHQuen = true;
	}
	
	public void ThemTienDu(double tienthem) {
		TienDu += tienthem;
	}

	public String getMaKH() {
		return MaKH;
	}

	public void setMaKH(String maKH) {
		MaKH = maKH;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public boolean isLaKHQuen() {
		return LaKHQuen;
	}

	public void setLaKHQuen(boolean laKHQuen) {
		LaKHQuen = laKHQuen;
	}

	public double getTienDu() {
		return TienDu;
	}

	public void setTienDu(double tienDu) {
		TienDu = tienDu;
	}
	
	
}
