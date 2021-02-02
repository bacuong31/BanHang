package Object;

public class ThamSo {
	private String MaThamSo;
	private String TenThamSo;
	private Double GiaTri;
	private String ChuThich;
	public String getMaThamSo() {
		return MaThamSo;
	}
	public void setMaThamSo(String maThamSo) {
		MaThamSo = maThamSo;
	}
	public String getTenThamSo() {
		return TenThamSo;
	}
	public void setTenThamSo(String tenThamSo) {
		TenThamSo = tenThamSo;
	}
	public Double getGiaTri() {
		return GiaTri;
	}
	public void setGiaTri(Double giaTri) {
		GiaTri = giaTri;
	}
	public String getChuThich() {
		return ChuThich;
	}
	public void setChuThich(String chuThich) {
		ChuThich = chuThich;
	}
	public ThamSo(String maThamSo, String tenThamSo, Double giaTri, String chuThich) {
		super();
		MaThamSo = maThamSo;
		TenThamSo = tenThamSo;
		GiaTri = giaTri;
		ChuThich = chuThich;
	}
	public ThamSo() {
		
	}
}
