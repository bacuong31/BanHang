package Helper;

public class BangDiaHelper {
	private String MaLoaiBangDia;
	private int SoLuong;
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
	public BangDiaHelper(String maLoaiBangDia, int soLuong) {
		super();
		MaLoaiBangDia = maLoaiBangDia;
		SoLuong = soLuong;
	}
	public BangDiaHelper() {};
}
