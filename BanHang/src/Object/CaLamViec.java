package Object;

import java.sql.Time;

public class CaLamViec {
	private String MaCaLamViec;
	private Time ThoiGianBatDau;
	private Time ThoiGianKetThuc;
	public CaLamViec() {
		
	}
	public CaLamViec(String maCaLamViec, Time thoiGianBatDau, Time thoiGianKetThuc) {
		MaCaLamViec = maCaLamViec;
		ThoiGianBatDau = thoiGianBatDau;
		ThoiGianKetThuc = thoiGianKetThuc;
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
