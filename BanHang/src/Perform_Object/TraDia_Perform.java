package Perform_Object;

import javafx.scene.control.ChoiceBox;

public class TraDia_Perform {
	private String MaBangDia;
	private String TenBangDia;
	private ChoiceBox<String> TrangThai;
	
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
	public ChoiceBox<String> getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(ChoiceBox<String> trangThai) {
		TrangThai = trangThai;
	}
	
	public TraDia_Perform(String maBangDia, String tenBangDia, ChoiceBox<String> trangThai) {
		super();
		MaBangDia = maBangDia;
		TenBangDia = tenBangDia;
		TrangThai = trangThai;
	}
	public TraDia_Perform() {
		TrangThai = new ChoiceBox<String>();
		TrangThai.getItems().add("Nguyên vẹn");
		TrangThai.getItems().add("Hư hỏng nhẹ");
		TrangThai.getItems().add("Hư hỏng nặng");
		TrangThai.getItems().add("Mất hoàn toàn");
	}
	
	public void setItemTrangThai(String s) {
		TrangThai.setValue(s);
	}
	
}
