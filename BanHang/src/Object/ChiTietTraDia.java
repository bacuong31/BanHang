package Object;

public class ChiTietTraDia {
	private String MaPhieuTra;
	private String MaBangDia;
	private String TrangThaiDia;
	public String getMaPhieuTra() {
		return MaPhieuTra;
	}
	public void setMaPhieuTra(String maPhieuTra) {
		MaPhieuTra = maPhieuTra;
	}
	public String getMaBangDia() {
		return MaBangDia;
	}
	public void setMaBangDia(String maBangDia) {
		MaBangDia = maBangDia;
	}
	public String getTrangThaiDia() {
		return TrangThaiDia;
	}
	public void setTrangThaiDia(String trangThaiDia) {
		TrangThaiDia = trangThaiDia;
	}
	public ChiTietTraDia(String maPhieuTra, String maBangDia, String trangThaiDia) {
		super();
		MaPhieuTra = maPhieuTra;
		MaBangDia = maBangDia;
		TrangThaiDia = trangThaiDia;
	}
	public ChiTietTraDia() {}
}
