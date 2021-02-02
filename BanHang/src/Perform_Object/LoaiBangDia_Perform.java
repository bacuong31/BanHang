package Perform_Object;

public class LoaiBangDia_Perform {
	private String MaLoaiBangDia;
	private String TenLoaiBangDia;
	private String TenLoaiNoiDung;
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

	public String getTenLoaiNoiDung() {
		return TenLoaiNoiDung;
	}

	public void setTenLoaiNoiDung(String tenLoaiNoiDung) {
		TenLoaiNoiDung = tenLoaiNoiDung;
	}

	public double getGiaTri() {
		return GiaTri;
	}

	public void setGiaTri(double giaTri) {
		GiaTri = giaTri;
	}

	public LoaiBangDia_Perform(String maLoaiBangDia, String tenLoaiBangDia, String tenLoaiNoiDung, double giaTri) {
		super();
		MaLoaiBangDia = maLoaiBangDia;
		TenLoaiBangDia = tenLoaiBangDia;
		TenLoaiNoiDung = tenLoaiNoiDung;
		GiaTri = giaTri;
	}

	public LoaiBangDia_Perform() {
	};
}