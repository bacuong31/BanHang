package Object;

import java.sql.Date;

public class HoaDon {
	private String MaHoaDon;
	private Date NgayLapHoaDon;
	private double GiaTri;
	private String Mota;
	protected String MaLoaiHoaDon;
	public HoaDon() {
		
	}
	public HoaDon(String maHoaDon, Date ngayLapHoaDon, double giaTri, String mota) {
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
	public Date getNgayLapHoaDon() {
		return NgayLapHoaDon;
	}
	public void setNgayLapHoaDon(Date ngayLapHoaDon) {
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
