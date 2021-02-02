package Object;

public class TrangThaiDia {
	private String MaTrangThai;
	private String TenTrangThai;
	private double HeSoGiaTri;
	
	
	public double getHeSoGiaTri() {
		return HeSoGiaTri;
	}
	public void setHeSoGiaTri(double heSoGiaTri) {
		HeSoGiaTri = heSoGiaTri;
	}
	public String getMaTrangThai() {
		return MaTrangThai;
	}
	public void setMaTrangThai(String maTrangThai) {
		MaTrangThai = maTrangThai;
	}
	public String getTenTrangThai() {
		return TenTrangThai;
	}
	public void setTenTrangThai(String tenTrangThai) {
		TenTrangThai = tenTrangThai;
	}
	public TrangThaiDia() {
		
	}
	public TrangThaiDia(String maTrangThai, String tenTrangThai, double heSoGiaTri) {
		super();
		MaTrangThai = maTrangThai;
		TenTrangThai = tenTrangThai;
		HeSoGiaTri = heSoGiaTri;
	}

}
