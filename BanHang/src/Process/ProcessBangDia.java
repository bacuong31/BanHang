package Process;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.BangDia_DAO;
import Helper.BangDiaHelper;
import Helper.IDHelper;
import Object.BangDia;
import Perform_Object.BangDia_Perform;




public class ProcessBangDia {
	public static void insertBangDia(String maYeuCau) throws ClassNotFoundException, SQLException {
		ArrayList<BangDiaHelper> arr = new ArrayList<BangDiaHelper>();
		arr = ProcessYeuCauNhapDia.getDiaNhap_SoLuong(maYeuCau);
		for (BangDiaHelper bd : arr) {
			for (int i = 0; i< bd.getSoLuong(); i++) {
				BangDia bangdia = new BangDia(IDHelper.newUUIDString(),bd.getMaLoaiBangDia(),ProcessTrangThaiDia.getMaTrangThaiTheoTen("Nguyên vẹn"),false);
				BangDia_DAO.insertBangDia(bangdia);
			}
		}
	}

	public static ArrayList<BangDia_Perform> getAll() throws ClassNotFoundException, SQLException {
		ArrayList<BangDia_Perform> arr = BangDia_DAO.getAllBangDiaPerform();
		return arr;	
	}
	public static String getMaLoaiBangDia(String maBangDia) throws ClassNotFoundException, SQLException {
		return BangDia_DAO.getMaLoaiBangDia(maBangDia);
	}
	
	public static ArrayList<BangDia_Perform> executeQuerySelectKhoBangDia(String query) throws ClassNotFoundException, SQLException{
		return BangDia_DAO.executeQueryselectBangDia(query);
	}
	
	
	public static String getQuerySelectKhoBangDia(String tenBangDia, String noiDung, String tienMin, String tienMax) {
		String s = "SELECT MaBangDia, TenLoaiBangDia, TenLoaiNoiDung, GiaTri FROM BANGDIA, LOAIBANGDIA, LOAINOIDUNG WHERE BANGDIA.isActive = 1 AND DangChoThue = 0 AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND LOAIBANGDIA.MaLoaiNoiDung = LOAINOIDUNG.MaLoaiNoiDung";
		if (!tenBangDia.isBlank()) {
			s = s + " AND TenLoaiBangDia Like '%" + tenBangDia + "%'";
		}
		if (!noiDung.isBlank()) {
			s = s + " AND TenLoaiNoiDung Like '%" + noiDung + "%'";
		}	
		if (!tienMin.isBlank()) {
			s = s + " AND GiaTri >= " + Double.valueOf(tienMin);
		}
		if (!tienMax.isBlank()) {
			s = s + " AND GiaTri <= " + Double.valueOf(tienMax);
		}
		s = s + " ORDER BY GiaTri";
		return s;
				
	}

	public static String getTrangThai(String maBangDia) throws ClassNotFoundException, SQLException {
		return BangDia_DAO.getTrangThai(maBangDia);
	}
}
