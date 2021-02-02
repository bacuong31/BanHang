package Perform_Object;

public class BangDia_Perform {
	private String MaBangDia;
	private String TenLoaiBangDia;
	private String TenLoaiNoiDung;
	private Double GiaTien;
	public String getMaBangDia() {
		return MaBangDia;
	}
	public void setMaBangDia(String maBangDia) {
		MaBangDia = maBangDia;
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
	public Double getGiaTien() {
		return GiaTien;
	}
	public void setGiaTien(Double giaTien) {
		GiaTien = giaTien;
	}
	public BangDia_Perform(String maBangDia, String tenLoaiBangDia, String tenLoaiNoiDung, Double giaTien) {
		super();
		MaBangDia = maBangDia;
		TenLoaiBangDia = tenLoaiBangDia;
		TenLoaiNoiDung = tenLoaiNoiDung;
		GiaTien = giaTien;
	}
	
	public BangDia_Perform() {}
}
