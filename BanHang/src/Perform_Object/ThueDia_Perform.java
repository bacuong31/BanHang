package Perform_Object;

import javafx.scene.control.CheckBox;

public class ThueDia_Perform {
	private String MaBangDia;
	private String TenBangDia;
	private String NoiDung;
	private Double GiaTien;
	private String TinhTrang;
	private CheckBox Thue;
	
	
	public String getTinhTrang() {
		return TinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		TinhTrang = tinhTrang;
	}
	public String getMaBangDia() {
		return MaBangDia;
	}
	public void setMaBangDia(String maBangDia) {
		MaBangDia = maBangDia;
	}
	public String getTenBangDia() {
		return TenBangDia;
	}
	public void setTenBangDia(String tenBangDia) {
		TenBangDia = tenBangDia;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public Double getGiaTien() {
		return GiaTien;
	}
	public void setGiaTien(Double giaTien) {
		GiaTien = giaTien;
	}
	public CheckBox getThue() {
		return Thue;
	}
	public void setThue(CheckBox thue) {
		Thue = thue;
	}

	public ThueDia_Perform(String maBangDia, String tenBangDia, String noiDung, Double giaTien, String tinhTrang,
			CheckBox thue) {
		super();
		MaBangDia = maBangDia;
		TenBangDia = tenBangDia;
		NoiDung = noiDung;
		GiaTien = giaTien;
		TinhTrang = tinhTrang;
		Thue = thue;
	}
	public ThueDia_Perform() {};
}
