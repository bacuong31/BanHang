package Object;

import java.sql.Time;
import java.util.ArrayList;

public class CaLamViec {
	private String MaCaLamViec;
	private Time ThoiGianBatDau;
	private Time ThoiGianKetThuc;
	private ArrayList<PhanCongLamViec> listPhanCong;
	
	
	public ArrayList<PhanCongLamViec> getListPhanCong() {
		return listPhanCong;
	}
	public void setListPhanCong(ArrayList<PhanCongLamViec> listPhanCong) {
		this.listPhanCong = listPhanCong;
	}
	public CaLamViec() {
		
	}
	
	public CaLamViec(String maCaLamViec, Time thoiGianBatDau, Time thoiGianKetThuc,
			ArrayList<PhanCongLamViec> listPhanCong) {
		super();
		MaCaLamViec = maCaLamViec;
		ThoiGianBatDau = thoiGianBatDau;
		ThoiGianKetThuc = thoiGianKetThuc;
		this.listPhanCong = listPhanCong;
	}
	public String getMaCaLamViec() {
		return MaCaLamViec;
	}
	public void setMaCaLamViec(String maCaLamViec) {
		MaCaLamViec = maCaLamViec;
	}
	public Time getThoiGianBatDau() {
		return ThoiGianBatDau;
	}
	public void setThoiGianBatDau(Time thoiGianBatDau) {
		ThoiGianBatDau = thoiGianBatDau;
	}
	public Time getThoiGianKetThuc() {
		return ThoiGianKetThuc;
	}
	public void setThoiGianKetThuc(Time thoiGianKetThuc) {
		ThoiGianKetThuc = thoiGianKetThuc;
	}
	
}
