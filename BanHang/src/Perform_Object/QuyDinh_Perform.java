package Perform_Object;

public class QuyDinh_Perform {
	String MaQuyDinh;
	String TenQuyDinh;
	Double GiaTri;
	String ChuThich;
	public String getMaQuyDinh() {
		return MaQuyDinh;
	}
	public void setMaQuyDinh(String maQuyDinh) {
		MaQuyDinh = maQuyDinh;
	}
	public String getTenQuyDinh() {
		return TenQuyDinh;
	}
	public void setTenQuyDinh(String tenQuyDinh) {
		TenQuyDinh = tenQuyDinh;
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
	public QuyDinh_Perform(String maQuyDinh, String tenQuyDinh, Double giaTri, String chuThich) {
		super();
		MaQuyDinh = maQuyDinh;
		TenQuyDinh = tenQuyDinh;
		GiaTri = giaTri;
		ChuThich = chuThich;
	}
	public QuyDinh_Perform() {}
	
}
