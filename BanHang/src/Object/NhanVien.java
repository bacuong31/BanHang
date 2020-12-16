package Object;

import java.sql.Date;

public class NhanVien {
	private String MaNV;
	private String HoTen;
	private Date NgaySinh;
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
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
	
	public NhanVien() {
		
	}
	public NhanVien(String maNV, String hoTen, Date ngaySinh) {
		super();
		MaNV = maNV;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
	}

}
