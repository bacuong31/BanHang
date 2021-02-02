package Object;

import java.time.LocalDate;

public class HoaDon {
	private String MaHoaDon;
	private LocalDate NgayLapHoaDon;
	private double GiaTri;
	private String Mota;
	
	public HoaDon() {
		
	}
	public HoaDon(String maHoaDon, LocalDate ngayLapHoaDon, double giaTri, String mota) {
		super();
		MaHoaDon = maHoaDon;
		NgayLapHoaDon = ngayLapHoaDon;
		GiaTri = giaTri;
		Mota = mota;
	}
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

	
}
