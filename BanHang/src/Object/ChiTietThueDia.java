package Object;

public class ChiTietThueDia {
	private String MaPhieuThue;
	private String MaBangDia;
	public String getMaPhieuThue() {
		return MaPhieuThue;
	}
	public void setMaPhieuThue(String maPhieuThue) {
		MaPhieuThue = maPhieuThue;
	}
	public String getMaBangDia() {
		return MaBangDia;
	}
	public void setMaBangDia(String maBangDia) {
		MaBangDia = maBangDia;
	}
	public ChiTietThueDia(String maPhieuThue, String maBangDia) {
		super();
		MaPhieuThue = maPhieuThue;
		MaBangDia = maBangDia;
	}
	public ChiTietThueDia() {}
}
