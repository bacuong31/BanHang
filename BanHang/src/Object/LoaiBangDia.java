package Object;

public class LoaiBangDia {
	private String MaLoaiBangDia;
	private String TenLoaiBangDia;
	private String MaLoaiNoiDung;
	private double GiaTri;
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
	public String getMaLoaiNoiDung() {
		return MaLoaiNoiDung;
	}
	public void setMaLoaiNoiDung(String maLoaiNoiDung) {
		MaLoaiNoiDung = maLoaiNoiDung;
	}
	public double getGiaTri() {
		return GiaTri;
	}
	public void setGiaTri(double giaTri) {
		GiaTri = giaTri;
	}
	public LoaiBangDia(String maLoaiBangDia, String tenLoaiBangDia, String maLoaiNoiDung, double giaTri) {
		super();
		MaLoaiBangDia = maLoaiBangDia;
		TenLoaiBangDia = tenLoaiBangDia;
		MaLoaiNoiDung = maLoaiNoiDung;
		GiaTri = giaTri;
	}
	public LoaiBangDia() {}
}
