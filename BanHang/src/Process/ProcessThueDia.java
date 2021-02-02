package Process;

import java.time.temporal.ChronoUnit;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.BangDia_DAO;
import Database.ChiTietThueDia_DAO;
import Database.PhieuChoThueDia_DAO;
import Helper.DateHelper;
import Object.ChiTietThueDia;
import Object.PhieuChoThueDia;
import Perform_Object.AnotherThueDia_Perform;
import Perform_Object.PhieuThue_Perform;
import Perform_Object.ThueDia_Perform;
import Perform_Object.TraDia_Perform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProcessThueDia {

	public static ArrayList<ThueDia_Perform> getAll() throws ClassNotFoundException, SQLException {
		return BangDia_DAO.getAllBangDiaThueDiaPerform();
	}

	public static ArrayList<ThueDia_Perform> executeQuerySelectKhoBangDia(String query) throws ClassNotFoundException, SQLException{
		return BangDia_DAO.executeQueryselectBangDiaThueDia(query);
	}
	
	public static String getQuerySelectThueDia(String maPhieuThue, String ngayThue, String ngayHenTra) {
		String s = "SELECT MaPhieuThue, NgayThue, NgayHenTra, KHACHHANG.HoTen, NHANVIEN.HoTen, GiaTri FROM PHIEUCHOTHUEDIA, NHANVIEN, KHACHHANG, HOADON WHERE HOADON.MaHoaDon = PHIEUCHOTHUEDIA.HoaDonDatCoc "
				+ "AND NHANVIEN.MaNV = PHIEUCHOTHUEDIA.NhanVienTiepNhan AND KHACHHANG.MaKH = PHIEUCHOTHUEDIA.KhachHangThue";
		if (!maPhieuThue.isBlank()) {
			s = s + " AND MaPhieuThue = '" + maPhieuThue + "'";
		}
		if (!ngayThue.isBlank()) {
			s = s + " AND NgayThue = '" + ngayThue + "'"; 
		}
		if (!ngayHenTra.isBlank()) {
			s = s + " AND NgayHenTra = '" + ngayHenTra + "'";
		}
		return s;
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
	public static Double getTienThueTong(ObservableList<AnotherThueDia_Perform> arr) throws ClassNotFoundException, SQLException {
		Double sum = 0.0;
		Double heso = ProcessThamSo.getHeSoThueDiaNguyenVen();
		for (AnotherThueDia_Perform a : arr) {
			sum = sum + (BangDia_DAO.getGiaTienThue(a.getMaBangDia())*heso);
		}
		return sum;
	}

	public static void insertPhieuThue(String maPhieuThue, LocalDate ngayThue, LocalDate ngayTra, String maKhachThue,
			String maNhanVienDangNhap, String maHoaDon) {
		
		PhieuChoThueDia phieu = new PhieuChoThueDia(maPhieuThue, ngayThue, ngayTra, maKhachThue,maNhanVienDangNhap, maHoaDon, "Chưa trả đĩa");
		try {
			PhieuChoThueDia_DAO.insertPhieuChoThueDia(phieu);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<PhieuThue_Perform> getAllPhieuThuePerForm() throws ClassNotFoundException, SQLException {
		return PhieuChoThueDia_DAO.getAllPhieuThuePerForm();
		
	}

	public static void insertChiTietThueDia(String maPhieuThue, ObservableList<ThueDia_Perform> listDiaThue) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		for (ThueDia_Perform thuedia : listDiaThue) {
			ChiTietThueDia chitiet = new ChiTietThueDia(maPhieuThue,thuedia.getMaBangDia());
			ChiTietThueDia_DAO.insertChiTietThueDia(chitiet);
		}
	}

	public static void updateDiaThue(ObservableList<ThueDia_Perform> listDiaThue) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		for (ThueDia_Perform thuedia : listDiaThue) {
			BangDia_DAO.updateBangDiaDangChoThue(thuedia.getMaBangDia());
		}
	}

	public static ArrayList<TraDia_Perform> getDiaTraPerForm(String maPhieuThue) throws ClassNotFoundException, SQLException {
		return PhieuChoThueDia_DAO.getDiaTraPerForm(maPhieuThue);
		
	}

	public static double getTienDatCoc(String maPhieuThue) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return PhieuChoThueDia_DAO.getTienDatCoc(maPhieuThue);
	}

	public static int updateTinhTrangPhieuThue(String maPhieuThue) throws ClassNotFoundException, SQLException {
		return PhieuChoThueDia_DAO.updateTinhTrangPhieuThue(maPhieuThue);
		
	}

	public static double getTienPhat(LocalDate now, String maPhieuThue) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		double tienPhatTheoNgay = ProcessThamSo.getTienPhatTheoNgay();
		LocalDate ngayHenTra = DateHelper.parse(PhieuChoThueDia_DAO.getNgayTra(maPhieuThue));
		if (now.isBefore(ngayHenTra)) {
			return 0;
		}
		long daysBetween = ChronoUnit.DAYS.between(ngayHenTra, now);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo quá hạn");
		alert.setHeaderText("Thông báo:");
		alert.setContentText("Bạn trả đĩa chậm " + daysBetween + " ngày! Bạn phải nộp phạt " + daysBetween*tienPhatTheoNgay + "!");
		alert.showAndWait();
		return daysBetween*tienPhatTheoNgay;
	}

	

}
