package Object;

public class ChiTietPhieuNhap {
	private String MaPhieuNhap;
	private String MaLoaiBangDia;
	private int SoLuong;
	public String getMaPhieuNhap() {
		return MaPhieuNhap;
	}
	public void setMaPhieuNhap(String maPhieuNhap) {
		MaPhieuNhap = maPhieuNhap;
	}
	public String getMaLoaiBangDia() {
		return MaLoaiBangDia;
	}
	public void setMaLoaiBangDia(String maLoaiBangDia) {
		MaLoaiBangDia = maLoaiBangDia;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public ChiTietPhieuNhap(String maPhieuNhap, String maLoaiBangDia, int soLuong) {
		super();
		MaPhieuNhap = maPhieuNhap;
		MaLoaiBangDia = maLoaiBangDia;
		SoLuong = soLuong;
	}
	public ChiTietPhieuNhap() {}
}
