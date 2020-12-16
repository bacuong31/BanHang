package Object;

public class BangDia {
	private String MaBangDia;
	private String MaLoaiBangDia;
	private String TrangThai;
	
	public String getMaBangDia() {
		return MaBangDia;
	}

	public void setMaBangDia(String maBangDia) {
		MaBangDia = maBangDia;
	}

	public String getMaLoaiBangDia() {
		return MaLoaiBangDia;
	}

	public void setMaLoaiBangDia(String maLoaiBangDia) {
		MaLoaiBangDia = maLoaiBangDia;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public BangDia(String maBangDia, String maLoaiBangDia, String trangThai) {
		super();
		MaBangDia = maBangDia;
		MaLoaiBangDia = maLoaiBangDia;
		TrangThai = trangThai;
	}
	
	public BangDia() {}
}
