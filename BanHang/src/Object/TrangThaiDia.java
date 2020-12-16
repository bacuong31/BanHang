package Object;

public class TrangThaiDia {
	private String MaTrangThai;
	private String TenTrangThai;
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
	public TrangThaiDia(String maTrangThai, String tenTrangThai) {
		super();
		MaTrangThai = maTrangThai;
		TenTrangThai = tenTrangThai;
	}
	
}
