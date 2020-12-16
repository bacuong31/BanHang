package Object;

public class ChiTietYeuCauNhapDia {
	private String MaYeuCau;
	private String MaLoaiBangDia;
	private int SoLuong;
	public ChiTietYeuCauNhapDia(String maYeuCau, String maLoaiBangDia, int soLuong) {
		super();
		MaYeuCau = maYeuCau;
		MaLoaiBangDia = maLoaiBangDia;
		SoLuong = soLuong;
	}
	public String getMaYeuCau() {
		return MaYeuCau;
	}
	public void setMaYeuCau(String maYeuCau) {
		MaYeuCau = maYeuCau;
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
	public ChiTietYeuCauNhapDia() {}
	
}
