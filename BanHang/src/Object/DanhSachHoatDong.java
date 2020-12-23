package Object;

import java.util.ArrayList;

public class DanhSachHoatDong {
	private String MaHoatDong;
	private String TenHoatDong;
	private ArrayList<HoatDong> listReferenceHoatDong;
	
	
	public ArrayList<HoatDong> getListReferenceHoatDong() {
		return listReferenceHoatDong;
	}
	public void setListReferenceHoatDong(ArrayList<HoatDong> listReferenceHoatDong) {
		this.listReferenceHoatDong = listReferenceHoatDong;
	}
	public String getMaHoatDong() {
		return MaHoatDong;
	}
	public void setMaHoatDong(String maHoatDong) {
		MaHoatDong = maHoatDong;
	}
	public String getTenHoatDong() {
		return TenHoatDong;
	}
	public void setTenHoatDong(String tenHoatDong) {
		TenHoatDong = tenHoatDong;
	}
	public DanhSachHoatDong() {
		
	}
	public DanhSachHoatDong(String maHoatDong, String tenHoatDong, ArrayList<HoatDong> listReferenceHoatDong) {
		super();
		MaHoatDong = maHoatDong;
		TenHoatDong = tenHoatDong;
		this.listReferenceHoatDong = listReferenceHoatDong;
	}

	
}
