package Object;

public class PhanCongLamViec {
	private String MaNV;
	private String MaCaLamViec;
	private String ChuThich;
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getMaCaLamViec() {
		return MaCaLamViec;
	}
	public void setMaCaLamViec(String maCaLamViec) {
		MaCaLamViec = maCaLamViec;
	}
	public String getChuThich() {
		return ChuThich;
	}
	public void setChuThich(String chuThich) {
		ChuThich = chuThich;
	}
	public PhanCongLamViec(String maNV, String maCaLamViec, String chuThich) {
		super();
		MaNV = maNV;
		MaCaLamViec = maCaLamViec;
		ChuThich = chuThich;
	}
	public PhanCongLamViec() {
		
	}
}
