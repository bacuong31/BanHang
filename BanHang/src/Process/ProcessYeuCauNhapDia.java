package Process;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.ChiTietYeuCauNhapDia_DAO;
import Database.YeuCauNhapDia_DAO;
import Helper.BangDiaHelper;
import Helper.IDHelper;
import Object.ChiTietYeuCauNhapDia;
import Object.DonViCungCap;
import Object.YeuCauNhapDia;
import Perform_Object.LoaiBangDia_Perform;
import Perform_Object.NhapDia_DanhSachBangDia_Perform;
import Perform_Object.SuaNhapDia_DanhSachBangDia_Perform;
import Perform_Object.YeuCauNhapDia_Perform;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class ProcessYeuCauNhapDia {
	public static ArrayList<String> getAllTenNhaCungCap(){
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<DonViCungCap> arrDVCC = ProcessDVCC.getAll();
		for(DonViCungCap dvcc : arrDVCC) {
			arr.add(dvcc.getTenDVCC());
		}
		return arr;
	}
	public static ArrayList<YeuCauNhapDia_Perform> getAllYCND() throws ClassNotFoundException, SQLException{
		ArrayList<YeuCauNhapDia_Perform> arr = YeuCauNhapDia_DAO.getAllPerform();
		return arr;
	}
	public static ArrayList<NhapDia_DanhSachBangDia_Perform> getAllDanhSachBangDia() {
		ArrayList<LoaiBangDia_Perform> temp = ProcessLoaiBangDia.getAll();
		ArrayList<NhapDia_DanhSachBangDia_Perform> arr = new ArrayList<NhapDia_DanhSachBangDia_Perform>();
		for (LoaiBangDia_Perform loaibangdia : temp) {
			NhapDia_DanhSachBangDia_Perform n = new NhapDia_DanhSachBangDia_Perform();
			n.setMaLoaiBangDia(loaibangdia.getMaLoaiBangDia());
			n.setTenLoaiBangDia(loaibangdia.getTenLoaiBangDia());
			n.setSoLuong(new TextField(String.valueOf(1)));;
			arr.add(n);
		}
		return arr;
	}
	
	public static String getQueryselect(String ngayTao, boolean btnChoXuLy, boolean btnDaHuy, boolean btnHoanThanh) {
		String s = "SELECT MaYeuCau, NgayYeuCau, TenDVCC, TenTrangThai FROM YEUCAUNHAPDIA, DONVICUNGCAP, TRANGTHAINHAPDIA WHERE YEUCAUNHAPDIA.isActive = 1 AND TRANGTHAINHAPDIA.MaTrangThai = YEUCAUNHAPDIA.TrangThaiNhap AND YEUCAUNHAPDIA.DonViCungCap = DONVICUNGCAP.MaDVCC";
		if(!ngayTao.isBlank()) {
			s = s + " AND NgayYeuCau = '" + ngayTao + "'"; 
		}
		if(btnChoXuLy) {
			s = s + " AND TenTrangThai = 'Chờ xử lý'";
		}
		if(btnDaHuy) {

			s = s + " AND TenTrangThai = 'Đã hủy'";
		}
		if(btnHoanThanh) {
			s = s + " AND TenTrangThai = 'Hoàn thành'";
		}
		return s;
	}
	
	public static ArrayList<YeuCauNhapDia_Perform> executeQueryselect(String query) throws ClassNotFoundException, SQLException {
		return YeuCauNhapDia_DAO.executeQueryselectYeuCauNhapDiaPerform(query);
		
	}
	public static Double getTienNhapDia(ArrayList<NhapDia_DanhSachBangDia_Perform> arr) throws ClassNotFoundException, SQLException {
		Double sum = 0.0;
		for (NhapDia_DanhSachBangDia_Perform bangdia : arr) {
			Double temp = ProcessLoaiBangDia.getGiaTriTheoMa(bangdia.getMaLoaiBangDia()) * Integer.valueOf(bangdia.getSoLuong().getText());
			sum = sum + temp;
		}
		return sum;
	}
	
	public static void insertYeuCauNhapDia(LocalDate ngayYC, String tenDVCC, String trangThaiNhap, ArrayList<NhapDia_DanhSachBangDia_Perform> arr) throws ClassNotFoundException, SQLException 
	{
		String maYeuCau = IDHelper.newUUIDString();
		YeuCauNhapDia yc = new YeuCauNhapDia(maYeuCau,ngayYC,ProcessDVCC.getMaDVCCTheoTen(tenDVCC),ProcessTrangThaiNhapDia.getMaTrangThaiTheoTen(trangThaiNhap));
		YeuCauNhapDia_DAO.insertYeuCauNhapDia(yc);
		for (NhapDia_DanhSachBangDia_Perform n : arr) {
			ChiTietYeuCauNhapDia chitiet = new ChiTietYeuCauNhapDia(maYeuCau,n.getMaLoaiBangDia(),Integer.valueOf(n.getSoLuong().getText()));
			ChiTietYeuCauNhapDia_DAO.insertChiTietYeuCauNhapDia(chitiet);
		}
	}
	
	public static ArrayList<SuaNhapDia_DanhSachBangDia_Perform> getBangDia_ChinhSuaYeuCauNhapDia(String maYeuCau) throws ClassNotFoundException, SQLException {
		return YeuCauNhapDia_DAO.getAllSuaNhapDiaPerform(maYeuCau);
	}
	
	public static void updateYeuCauNhapDia(String maYeuCau, LocalDate ngayYC, String tenNhaCungCap, boolean isDaHuy, boolean isHoanThanh) throws ClassNotFoundException, SQLException {
		String trangthai = "";
		if (isDaHuy == false && isHoanThanh == false) {
			trangthai = "Chờ xử lý";
		} else if (isDaHuy == true) {
			trangthai = "Đã hủy";
		} else if (isHoanThanh == true) {
			trangthai = "Hoàn thành";
		}
		YeuCauNhapDia yc = new YeuCauNhapDia(maYeuCau,ngayYC,ProcessDVCC.getMaDVCCTheoTen(tenNhaCungCap),ProcessTrangThaiNhapDia.getMaTrangThaiTheoTen(trangthai));
		YeuCauNhapDia_DAO.updateYeuCauNhapDia(yc);
	}
	public static ArrayList<BangDiaHelper> getDiaNhap_SoLuong(String maYeuCau) throws ClassNotFoundException, SQLException {
		ArrayList<BangDiaHelper> arr = YeuCauNhapDia_DAO.getDiaNhap_SoLuong(maYeuCau);
		return arr;
	}
	
	
}
