package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DateHelper;
import Object.BangDia;
import Object.PhieuChoThueDia;
import Perform_Object.PhieuThue_Perform;
import Perform_Object.TraDia_Perform;
import javafx.collections.ObservableList;


public class PhieuChoThueDia_DAO {
	public static boolean insertPhieuChoThueDia(PhieuChoThueDia phieuchothuedia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO PHIEUCHOTHUEDIA VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieuchothuedia.getMaPhieuThue());
		pstmt.setString(2, DateHelper.format(phieuchothuedia.getNgayThue()));
		pstmt.setString(3, DateHelper.format(phieuchothuedia.getNgayHenTra()));
		pstmt.setString(4, phieuchothuedia.getKhachHangThue());
		pstmt.setString(5, phieuchothuedia.getNhanVienTiepNhan());
		pstmt.setString(6, phieuchothuedia.getHoaDonDatCoc());
		pstmt.setString(7, phieuchothuedia.getTinhTrang());
		//pstmt.setBoolean(7, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int deletePhieuChoThueDia(PhieuChoThueDia phieuchothuedia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "DELETE FROM PHIEUCHOTHUEDIA WHERE MaPhieuThue = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieuchothuedia.getMaPhieuThue());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updatePhieuChoThueDia(PhieuChoThueDia phieuchothuedia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHIEUCHOTHUEDIA SET NgayThue = ?, NgayHenTra = ?, KhachHangThue = ?, NhanVienTiepNhan = ?, HoaDonDatCoc = ? WHERE MaPhieuThue = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, DateHelper.format(phieuchothuedia.getNgayThue()));
		pstmt.setString(2, DateHelper.format(phieuchothuedia.getNgayHenTra()));
		pstmt.setString(3, phieuchothuedia.getKhachHangThue());
		pstmt.setString(4, phieuchothuedia.getNhanVienTiepNhan());
		pstmt.setString(5, phieuchothuedia.getHoaDonDatCoc());
		pstmt.setString(6, phieuchothuedia.getMaPhieuThue());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static PhieuChoThueDia selectPhieuChoThueDia(String maphieuthue) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUCHOTHUEDIA WHERE MAPHIEUTHUE = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieuthue);
		ResultSet rs = pstmt.executeQuery();
		PhieuChoThueDia temp = new PhieuChoThueDia();
		if(rs.next()) {
			temp.setMaPhieuThue(rs.getString(1));
			temp.setNgayThue(DateHelper.parse(rs.getString(2)));
			temp.setNgayHenTra(DateHelper.parse(rs.getString(3)));
			temp.setKhachHangThue(rs.getString(4));
			temp.setNhanVienTiepNhan(rs.getString(5));
			temp.setHoaDonDatCoc(rs.getString(6));
		}
		return temp;
	}
	public static ResultSet executeQueryselectPhieuChoThueDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<PhieuChoThueDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUCHOTHUEDIA WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuChoThueDia> arr = new ArrayList<PhieuChoThueDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuChoThueDia temp = new PhieuChoThueDia();
			temp.setMaPhieuThue(rs.getString(1));
			temp.setNgayThue(DateHelper.parse(rs.getString(2)));
			temp.setNgayHenTra(DateHelper.parse(rs.getString(3)));
			temp.setKhachHangThue(rs.getString(4));
			temp.setNhanVienTiepNhan(rs.getString(5));
			temp.setHoaDonDatCoc(rs.getString(6));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<PhieuThue_Perform> getAllPhieuThuePerForm() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaPhieuThue, NgayThue, NgayHenTra, KHACHHANG.HoTen, NHANVIEN.HoTen, GiaTri, TinhTrang FROM PHIEUCHOTHUEDIA, NHANVIEN, KHACHHANG, HOADON WHERE HOADON.MaHoaDon = PHIEUCHOTHUEDIA.HoaDonDatCoc"
				+ " AND NHANVIEN.MaNV = PHIEUCHOTHUEDIA.NhanVienTiepNhan AND KHACHHANG.MaKH = PHIEUCHOTHUEDIA.KhachHangThue";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuThue_Perform> arr = new ArrayList<PhieuThue_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuThue_Perform temp = new PhieuThue_Perform();
			temp.setMaPhieuThue(rs.getString(1));
			
			temp.setNgayThue(DateHelper.parse(rs.getString(2)));
			temp.setNgayHenTra(DateHelper.parse(rs.getString(3)));
			temp.setKhachHangThue(rs.getString(4));
			temp.setNhanVienTiepNhan(rs.getString(5));
			temp.setHoaDonDatCoc(rs.getDouble(6));
			temp.setTrangThai(rs.getString(7));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<TraDia_Perform> getDiaTraPerForm(String maPhieuThue) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT BANGDIA.MaBangDia, TenLoaiBangDia, TenTrangThai FROM CHITIETTHUEDIA, BANGDIA, LOAIBANGDIA, TRANGTHAIDIA WHERE BANGDIA.MaLoaiBangDia = LoaiBangDia.MaLoaiBangDia AND BANGDIA.MaTrangThai = TRANGTHAIDIA.MaTrangThai AND BANGDIA.MaBangDia = CHITIETTHUEDIA.MaBangDia AND MaPhieuThue = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maPhieuThue);
		ArrayList<TraDia_Perform> arr = new ArrayList<TraDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			TraDia_Perform temp = new TraDia_Perform();
			temp.setMaBangDia(rs.getString(1));
			temp.setTenBangDia(rs.getString(2));
			temp.setItemTrangThai(rs.getString(3));
			arr.add(temp);
		}
		return arr;
	}

	public static double getTienThueAfter(TraDia_Perform t, double hesothuediamin, double heso) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT LOAIBANGDIA.GiaTri, HeSoGiaTri FROM BANGDIA, LOAIBANGDIA, TRANGTHAIDIA WHERE BANGDIA.MaTrangThai = TRANGTHAIDIA.MaTrangThai AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, t.getMaBangDia());
	
		ResultSet rs = pstmt.executeQuery();
		double sum = 0.0;
		if (rs.next()) {
			sum = rs.getDouble(1)*rs.getDouble(2)*(heso+hesothuediamin);
		}
		return sum;
	}

	public static double getTienThueBefore(TraDia_Perform t, double heso) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT LOAIBANGDIA.GiaTri, HeSoGiaTri FROM BANGDIA, LOAIBANGDIA, TRANGTHAIDIA WHERE BANGDIA.MaTrangThai = TRANGTHAIDIA.MaTrangThai AND BANGDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia AND MaBangDia = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, t.getMaBangDia());

		ResultSet rs = pstmt.executeQuery();
		double sum = 0.0;
		if (rs.next()) {
			sum = rs.getDouble(1)*rs.getDouble(2)*(heso);
		}
		return sum;
	}

	public static double getTienDatCoc(String maPhieuThue) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT GiaTri FROM PHIEUCHOTHUEDIA, HOADON WHERE PHIEUCHOTHUEDIA.HoaDonDatCoc = HOADON.MaHoaDon AND MaPhieuThue = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maPhieuThue);
		
		ResultSet rs = pstmt.executeQuery();
		double sum = 0.0;
		if (rs.next()) {
			sum = rs.getDouble(1);
		}
		return sum;
	}

	public static int updateTinhTrangPhieuThue(String maPhieuThue) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHIEUCHOTHUEDIA SET TinhTrang = 'Đã trả đĩa' WHERE MaPhieuThue = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maPhieuThue);
		res = pstmt.executeUpdate();
		return res;
	}

	public static String getNgayTra(String maPhieuThue) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT NgayHenTra FROM PHIEUCHOTHUEDIA WHERE MaPhieuThue = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maPhieuThue);
		String s = "";
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			s = rs.getString(1);
		}
		return s;
	}
}
