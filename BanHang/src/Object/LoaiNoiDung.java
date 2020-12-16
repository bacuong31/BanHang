package Object;

public class LoaiNoiDung {
	private String MaLoaiNoiDung;
	private String TenLoaiNoiDung;
	public LoaiNoiDung() {
		
	}
	public String getMaLoaiNoiDung() {
		return MaLoaiNoiDung;
	}
	public void setMaLoaiNoiDung(String maLoaiNoiDung) {
		MaLoaiNoiDung = maLoaiNoiDung;
	}
	public String getTenLoaiNoiDung() {
		return TenLoaiNoiDung;
	}
	public void setTenLoaiNoiDung(String tenLoaiNoiDung) {
		TenLoaiNoiDung = tenLoaiNoiDung;
	}
	public LoaiNoiDung(String maLoaiNoiDung, String tenLoaiNoiDung) {
		super();
		MaLoaiNoiDung = maLoaiNoiDung;
		TenLoaiNoiDung = tenLoaiNoiDung;
	}
}
