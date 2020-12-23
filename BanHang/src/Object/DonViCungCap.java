package Object;

import java.util.ArrayList;

public class DonViCungCap {
	private String MaDVCC;
	private String TenDVCC;
	private String SDT;
	private String DiaChi;
	private ArrayList<YeuCauNhapDia> listYeuCauNhapDia;
	
	public ArrayList<YeuCauNhapDia> getListYeuCauNhapDia() {
		return listYeuCauNhapDia;
	}
	public void setListYeuCauNhapDia(ArrayList<YeuCauNhapDia> listYeuCauNhapDia) {
		this.listYeuCauNhapDia = listYeuCauNhapDia;
	}
	public String getMaDVCC() {
		return MaDVCC;
	}
	public void setMaDVCC(String maDVCC) {
		MaDVCC = maDVCC;
	}
	public String getTenDVCC() {
		return TenDVCC;
	}
	public void setTenDVCC(String tenDVCC) {
		TenDVCC = tenDVCC;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public DonViCungCap(String maDVCC, String tenDVCC, String sDT, String diaChi,
			ArrayList<YeuCauNhapDia> listYeuCauNhapDia) {
		super();
		MaDVCC = maDVCC;
		TenDVCC = tenDVCC;
		SDT = sDT;
		DiaChi = diaChi;
		this.listYeuCauNhapDia = listYeuCauNhapDia;
	}
	public DonViCungCap() {}
}
