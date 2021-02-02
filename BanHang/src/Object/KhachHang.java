package Object;


import java.time.LocalDate;
import java.util.ArrayList;

public class KhachHang {
	private String MaKH = "";
	private String HoTen;
	private LocalDate NgaySinh;
	private int Sdt;
	private boolean LaKHQuen;
	private double TienDu;
	private String DiaChi;
	private ArrayList<PhieuChoThueDia> listPhieuChoThueDia;
	
	
	public int getSdt() {
		return Sdt;
	}

	public void setSdt(int sdt) {
		Sdt = sdt;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public ArrayList<PhieuChoThueDia> getListPhieuChoThueDia() {
		return listPhieuChoThueDia;
	}

	public void setListPhieuChoThueDia(ArrayList<PhieuChoThueDia> listPhieuChoThueDia) {
		this.listPhieuChoThueDia = listPhieuChoThueDia;
	}

	public KhachHang() {}

	public KhachHang(String maKH, String hoTen, LocalDate ngaySinh, int sdt, boolean laKHQuen, double tienDu, String diaChi,
			ArrayList<PhieuChoThueDia> listPhieuChoThueDia) {
		super();
		MaKH = maKH;
		HoTen = hoTen;
		NgaySinh = ngaySinh;
		Sdt = sdt;
		LaKHQuen = laKHQuen;
		TienDu = tienDu;
		DiaChi = diaChi;
		this.listPhieuChoThueDia = listPhieuChoThueDia;
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

	public LocalDate getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
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
