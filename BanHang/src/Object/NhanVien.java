package Object;

import java.time.LocalDate;


public class NhanVien {
	private String MaNV;
	private String HoTen;
	private LocalDate NgaySinh;
	private int Sdt;
	private String Username;
	private String Password;
	
	public int getSdt() {
		return Sdt;
	}
	public void setSdt(int sdt) {
		Sdt = sdt;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
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
	public LocalDate getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		NgaySinh = ngaySinh;
	}
	
	public NhanVien() {
		
	}
	public NhanVien(String maNV, String hoTen, LocalDate ngaySinh, int sdt, String username, String password) {
		super();
		MaNV = maNV;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
		Sdt = sdt;
		Username = username;
		Password = password;
	}



}
