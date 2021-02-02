package Perform_Object;

import java.time.LocalDate;

public class HoaDon_Perform {
	private String MaHoaDon;
	private LocalDate NgayLapHoaDon;
	private double GiaTri;
	private String Mota;
	private String LoaiHoaDon;
	public String getMaHoaDon() {
		return MaHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		MaHoaDon = maHoaDon;
	}
	public LocalDate getNgayLapHoaDon() {
		return NgayLapHoaDon;
	}
	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		NgayLapHoaDon = ngayLapHoaDon;
	}
	public double getGiaTri() {
		return GiaTri;
	}
	public void setGiaTri(double giaTri) {
		GiaTri = giaTri;
	}
	public String getMota() {
		return Mota;
	}
	public void setMota(String mota) {
		Mota = mota;
	}
	public String getLoaiHoaDon() {
		return LoaiHoaDon;
	}
	public void setLoaiHoaDon(String loaiHoaDon) {
		LoaiHoaDon = loaiHoaDon;
	}
	public HoaDon_Perform(String maHoaDon, LocalDate ngayLapHoaDon, double giaTri, String mota, String loaiHoaDon) {
		super();
		MaHoaDon = maHoaDon;
		NgayLapHoaDon = ngayLapHoaDon;
		GiaTri = giaTri;
		Mota = mota;
		LoaiHoaDon = loaiHoaDon;
	}
	public HoaDon_Perform() {}
}
