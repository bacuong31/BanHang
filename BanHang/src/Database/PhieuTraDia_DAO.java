package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DateHelper;
import Object.BangDia;
import Object.PhieuTraDia;
import Perform_Object.PhieuTraDia_Perform;



public class PhieuTraDia_DAO {
	public static boolean insertPhieuTraDia(PhieuTraDia phieutradia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO PHIEUTRADIA VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieutradia.getMaPhieuTra());
		pstmt.setString(2, phieutradia.getPhieuThamChieu());
		pstmt.setString(3, DateHelper.format(phieutradia.getNgayTra()));
		pstmt.setString(4, phieutradia.getMaNhanVien());
		pstmt.setString(5, phieutradia.getHoaDonBoiThuong());
		pstmt.setString(6, phieutradia.getHoaDonHoanTra());
		pstmt.setBoolean(7, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int softDeletePhieuTraDia(String maphieutra) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "UPDATE PHIEUTRADIA SET isActive = 0 WHERE MaPhieuTra = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieutra);
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updatePhieuTraDia(PhieuTraDia phieutradia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE PHIEUTRADIA SET PhieuThamChieu = ?, NgayTra = ?, MaNhanVien = ?, HoaDonBoiThuong = ?, HoaDonHoanTra = ? WHERE MaPhieuTra = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, phieutradia.getPhieuThamChieu());
		pstmt.setString(2, DateHelper.format(phieutradia.getNgayTra()));
		pstmt.setString(3, phieutradia.getMaNhanVien());
		pstmt.setString(4, phieutradia.getHoaDonBoiThuong());
		pstmt.setString(5, phieutradia.getHoaDonHoanTra());
		pstmt.setString(6, phieutradia.getMaPhieuTra());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static PhieuTraDia selectPhieuTraDia(String maphieutra) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUTRADIA WHERE MAPHIEUTRA = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maphieutra);
		ResultSet rs = pstmt.executeQuery();
		PhieuTraDia temp = new PhieuTraDia();
		if(rs.next()) {
			temp.setMaPhieuTra(rs.getString(1));
			temp.setPhieuThamChieu(rs.getString(2));
			temp.setNgayTra(DateHelper.parse(rs.getString(3)));
			temp.setMaNhanVien(rs.getString(4));
			temp.setHoaDonBoiThuong(rs.getString(5));
			temp.setHoaDonHoanTra(rs.getString(6));
		}
		return temp;
	}
	public static ResultSet executeQueryselectPhieuTraDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<PhieuTraDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUTRADIA WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuTraDia> arr = new ArrayList<PhieuTraDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuTraDia temp = new PhieuTraDia();
			temp.setMaPhieuTra(rs.getString(1));
			temp.setPhieuThamChieu(rs.getString(2));
			temp.setNgayTra(DateHelper.parse(rs.getString(3)));
			temp.setMaNhanVien(rs.getString(4));
			temp.setHoaDonBoiThuong(rs.getString(5));
			temp.setHoaDonHoanTra(rs.getString(6));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<PhieuTraDia_Perform> getAllPhieuTraDiaPerform() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM PHIEUTRADIA, NHANVIEN WHERE PHIEUTRADIA.isActive = 1 AND PhieuTraDia.MaNhanVien = NhanVien.MaNV ";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<PhieuTraDia_Perform> arr = new ArrayList<PhieuTraDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuTraDia_Perform temp = new PhieuTraDia_Perform();
			temp.setMaPhieuTra(rs.getString("MaPhieuTra"));
			temp.setNgayTra(DateHelper.parse(rs.getString("NgayTra")));
			temp.setNhanVienTiepNhan(rs.getString("HoTen"));
			temp.setPhieuThamChieu(rs.getString("PhieuThamChieu"));
			String HoaDonHoanTra = rs.getString("HoaDonHoanTra");
			if (!rs.wasNull()) {
				temp.setTienBoiThuong(0.0);
				String sql = "SELECT GiaTri FROM PHIEUTRADIA, HOADON WHERE PHIEUTRADIA.isActive = 1 AND PHIEUTRADIA.HoaDonHoanTra = HOADON.MaHoaDon AND PHIEUTRADIA.HoaDonHoanTra = ?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, HoaDonHoanTra);
				ResultSet r = p.executeQuery();
				if (r.next()) {
					temp.setTienHoanTra(r.getDouble(1));
				}
			} else {
				temp.setTienHoanTra(0.0);
				String HoaDonBoiThuong = rs.getString("HoaDonBoiThuong");
				String sql = "SELECT GiaTri FROM PHIEUTRADIA, HOADON WHERE PHIEUTRADIA.isActive = 1 AND PHIEUTRADIA.HoaDonBoiThuong = HOADON.MaHoaDon AND PHIEUTRADIA.HoaDonBoiThuong = ?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, HoaDonBoiThuong);
				ResultSet r = p.executeQuery();
				if (r.next()) {
					temp.setTienBoiThuong(r.getDouble(1));
				}
			}

			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<PhieuTraDia_Perform> executeQueryselectTraDiaPerform(String query) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ArrayList<PhieuTraDia_Perform> arr = new ArrayList<PhieuTraDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PhieuTraDia_Perform temp = new PhieuTraDia_Perform();
			temp.setMaPhieuTra(rs.getString("MaPhieuTra"));
			temp.setNgayTra(DateHelper.parse(rs.getString("NgayTra")));
			temp.setNhanVienTiepNhan(rs.getString("HoTen"));
			temp.setPhieuThamChieu(rs.getString("PhieuThamChieu"));
			String HoaDonHoanTra = rs.getString("HoaDonHoanTra");
			if (!rs.wasNull()) {
				temp.setTienBoiThuong(0.0);
				String sql = "SELECT GiaTri FROM PHIEUTRADIA, HOADON WHERE PHIEUTRADIA.isActive = 1 AND PHIEUTRADIA.HoaDonHoanTra = HOADON.MaHoaDon AND PHIEUTRADIA.HoaDonHoanTra = ?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, HoaDonHoanTra);
				ResultSet r = p.executeQuery();
				if (r.next()) {
					temp.setTienHoanTra(r.getDouble(1));
				}
			} else {
				temp.setTienHoanTra(0.0);
				String HoaDonBoiThuong = rs.getString("HoaDonBoiThuong");
				String sql = "SELECT GiaTri FROM PHIEUTRADIA, HOADON WHERE PHIEUTRADIA.isActive = 1 AND PHIEUTRADIA.HoaDonBoiThuong = HOADON.MaHoaDon AND PHIEUTRADIA.HoaDonBoiThuong = ?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, HoaDonBoiThuong);
				ResultSet r = p.executeQuery();
				if (r.next()) {
					temp.setTienBoiThuong(r.getDouble(1));
				}
			}

			arr.add(temp);
		}
		return arr;
	}

}
