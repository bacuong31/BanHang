package Perform_Object;

import javafx.scene.control.TextField;

public class NhapDia_DanhSachBangDia_Perform {
	private String MaLoaiBangDia;
	private String TenLoaiBangDia;
	private TextField SoLuong;
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

	public TextField getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(TextField soLuong) {
		SoLuong = soLuong;
	}

	public NhapDia_DanhSachBangDia_Perform(String maLoaiBangDia, String tenLoaiBangDia, TextField soLuong) {
		super();
		MaLoaiBangDia = maLoaiBangDia;
		TenLoaiBangDia = tenLoaiBangDia;
		SoLuong = soLuong;
	}
	public NhapDia_DanhSachBangDia_Perform() {}
}
