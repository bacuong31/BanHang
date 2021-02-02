package Perform_Object;



public class SuaNhapDia_DanhSachBangDia_Perform {
	private String MaLoaiBangDia;
	private String TenLoaiBangDia;
	private int SoLuong;
	public String getMaLoaiBangDia() {
		return MaLoaiBangDia;
	}
	public void setMaLoaiBangDia(String maLoaiBangDia) {
		MaLoaiBangDia = maLoaiBangDia;
	}
	public String getTenLoaiBangDia() {
		return TenLoaiBangDia;
	}
	public void setTenLoaiBangDia(String tenLoaiBangDia) {
		TenLoaiBangDia = tenLoaiBangDia;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public SuaNhapDia_DanhSachBangDia_Perform(String maLoaiBangDia, String tenLoaiBangDia, int soLuong) {
		super();
		MaLoaiBangDia = maLoaiBangDia;
		TenLoaiBangDia = tenLoaiBangDia;
		SoLuong = soLuong;
	}
	public SuaNhapDia_DanhSachBangDia_Perform() {}
}
