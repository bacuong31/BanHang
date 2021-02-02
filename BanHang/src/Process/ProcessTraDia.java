package Process;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.BangDia_DAO;
import Database.ChiTietTraDia_DAO;
import Database.PhieuChoThueDia_DAO;
import Database.PhieuTraDia_DAO;
import Helper.DateHelper;
import Object.ChiTietTraDia;
import Object.PhieuTraDia;
import Perform_Object.ChiTietTraDia_Perform;
import Perform_Object.PhieuTraDia_Perform;
import Perform_Object.TraDia_Perform;
import javafx.collections.ObservableList;

public class ProcessTraDia {

	public static double getTienThueAfter(ObservableList<TraDia_Perform> listDiaTra) throws ClassNotFoundException, SQLException {
		double heSoThueDiaMin = 0.1;
		double sum = 0.0;
		for (TraDia_Perform t : listDiaTra) {
			double heso = ProcessThamSo.getHeSoTheoTen("Hệ số tiền thuê đĩa " + t.getTrangThai().getSelectionModel().getSelectedItem().toLowerCase());
			//System.out.println("Hệ số tiền thuê đĩa " + t.getTrangThai().getSelectionModel().getSelectedItem().toLowerCase());
			sum = sum + PhieuChoThueDia_DAO.getTienThueAfter(t,heSoThueDiaMin,heso);
		}
		return sum;
	}

	public static double getTienThueBefore(ObservableList<TraDia_Perform> listDiaTra) throws ClassNotFoundException, SQLException {
		double sum = 0.0;
		for (TraDia_Perform t : listDiaTra) {
			double heso = ProcessThamSo.getHeSoTheoTen("Hệ số tiền thuê đĩa " + ProcessBangDia.getTrangThai(t.getMaBangDia()).toLowerCase());
			//System.out.println("Hệ số tiền thuê đĩa " + ProcessBangDia.getTrangThai(t.getMaBangDia()).toLowerCase());
			sum = sum + PhieuChoThueDia_DAO.getTienThueBefore(t,heso);
			
		}
		return sum;
	}

	public static void updateThongTinDia(ObservableList<TraDia_Perform> arr) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		for (TraDia_Perform res : arr) {
			if (res.getTrangThai().getSelectionModel().getSelectedItem().equals("Mất hoàn toàn")) {
				BangDia_DAO.softDeleteBangDiaTheoMa(res.getMaBangDia());
			} else {
				BangDia_DAO.updateTrangThaiDia(res.getMaBangDia(), ProcessTrangThaiDia
						.getMaTrangThaiTheoTen(res.getTrangThai().getSelectionModel().getSelectedItem()));
			}
		}
	}

	public static void insertPhieuTraDia(String maPhieuTra, String phieuThamChieu, LocalDate now, String maNhanVienDangNhap,
			double giatri, String maHoaDon) throws ClassNotFoundException, SQLException {
		PhieuTraDia p = null;
		if (giatri < 0) {
			p = new PhieuTraDia(maPhieuTra,phieuThamChieu,now,maNhanVienDangNhap,null,maHoaDon);
		} else if (giatri >= 0) {
			p = new PhieuTraDia(maPhieuTra,phieuThamChieu,now,maNhanVienDangNhap,maHoaDon,null);
		}
		PhieuTraDia_DAO.insertPhieuTraDia(p);
		
	}

	public static void insertChiTietTraDia(String maPhieuTra, ObservableList<TraDia_Perform> arr) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		for (TraDia_Perform t : arr) {
			
			ChiTietTraDia chitiet = new ChiTietTraDia(maPhieuTra,t.getMaBangDia(),ProcessTrangThaiDia.getMaTrangThaiTheoTen(t.getTrangThai().getSelectionModel().getSelectedItem()));
			ChiTietTraDia_DAO.insertChiTietTraDia(chitiet);
		}
	}

	public static ArrayList<PhieuTraDia_Perform> getAllPhieuTraDiaPerform() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return PhieuTraDia_DAO.getAllPhieuTraDiaPerform();
	}

	public static String getQuerySelect(String maPhieuTra,LocalDate ngayThanhToan, String phieuThamChieu, String tenNV) {
		String s = "SELECT * FROM PHIEUTRADIA, NHANVIEN WHERE PHIEUTRADIA.isActive = 1 AND PhieuTraDia.MaNhanVien = NhanVien.MaNV";
		if (!maPhieuTra.isBlank()) {
			s = s + " AND MaPhieuTra Like '%" + maPhieuTra + "%'";
		}
		if (ngayThanhToan != null) {
			s = s + " AND NgayTra = '" + DateHelper.format(ngayThanhToan) + "'";
		}
		if (!phieuThamChieu.isBlank()) {
			s = s + " AND PhieuThamChieu Like '%" + phieuThamChieu + "%'";
		}
		if (!tenNV.isBlank()) {
			s = s + " AND HoTen Like '%" + tenNV + "%'";
		}
		return s;
	}
	public static ArrayList<PhieuTraDia_Perform> executeQuerySelectTraDiaPerform(String query) throws ClassNotFoundException, SQLException {
		return PhieuTraDia_DAO.executeQueryselectTraDiaPerform(query);
	}

	public static void deletePhieuTraDia(PhieuTraDia_Perform temp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		PhieuTraDia_DAO.softDeletePhieuTraDia(temp.getMaPhieuTra());
	}

	public static ArrayList<ChiTietTraDia_Perform> getAllChiTietTraDiaPerform(String maPhieuTra) throws ClassNotFoundException, SQLException {
		return ChiTietTraDia_DAO.getAllChiTietTraDiaPerform(maPhieuTra);
		
	}
}
