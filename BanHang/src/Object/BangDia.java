package Object;

public class BangDia {
	private String MaBangDia;
	private String MaLoaiBangDia;
	private String MaTrangThai;
	private boolean DangChoThue;
	
	public String getMaTrangThai() {
		return MaTrangThai;
	}

	public void setMaTrangThai(String maTrangThai) {
		MaTrangThai = maTrangThai;
	}

	public boolean isDangChoThue() {
		return DangChoThue;
	}

	public void setDangChoThue(boolean dangChoThue) {
		DangChoThue = dangChoThue;
	}

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
		return MaTrangThai;
	}

	public void setTrangThai(String trangThai) {
		MaTrangThai = trangThai;
	}


	public BangDia(String maBangDia, String maLoaiBangDia, String maTrangThai, boolean dangChoThue) {
		super();
		MaBangDia = maBangDia;
		MaLoaiBangDia = maLoaiBangDia;
		MaTrangThai = maTrangThai;
		DangChoThue = dangChoThue;
	}

	public BangDia() {}
}
