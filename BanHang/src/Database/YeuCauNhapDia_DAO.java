package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.BangDiaHelper;
import Helper.DateHelper;
import Object.BangDia;
import Object.YeuCauNhapDia;
import Perform_Object.SuaNhapDia_DanhSachBangDia_Perform;
import Perform_Object.YeuCauNhapDia_Perform;
import javafx.util.Pair;



public class YeuCauNhapDia_DAO {
	public static boolean insertYeuCauNhapDia(YeuCauNhapDia yeucaunhapdia) throws SQLException, ClassNotFoundException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "INSERT INTO YEUCAUNHAPDIA VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, yeucaunhapdia.getMaYeuCau());
		pstmt.setString(2, DateHelper.format(yeucaunhapdia.getNgayYeuCau()));
		pstmt.setString(3, yeucaunhapdia.getDonViCungCap());
		pstmt.setString(4, yeucaunhapdia.getTrangThaiNhap());
		pstmt.setBoolean(5, true);
		res = pstmt.executeUpdate();
		if (res == 0) {
			return false;
		}
		return true;
	}
	
	public static int softDeletetYeuCauNhapDia(YeuCauNhapDia yeucaunhapdia) throws SQLException, ClassNotFoundException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		int res = 0;
		String sqlQuery = "UPDATE YEUCAUNHAPDIA SET isActive = 0 WHERE MaYeuCau = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, yeucaunhapdia.getMaYeuCau());
		res = pstmt.executeUpdate();
		return res;
	}
	
	public static boolean updateYeuCauNhapDia(YeuCauNhapDia yeucaunhapdia) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "UPDATE YEUCAUNHAPDIA SET NgayYeuCau = ?, DonViCungCap = ?, TrangThaiNhap = ? WHERE MaYeuCau = ?";
		int res = 0;
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, DateHelper.format(yeucaunhapdia.getNgayYeuCau()));
		pstmt.setString(2, yeucaunhapdia.getDonViCungCap());
		pstmt.setString(3, yeucaunhapdia.getTrangThaiNhap());
		pstmt.setString(4, yeucaunhapdia.getMaYeuCau());
		res = pstmt.executeUpdate();
		if (res == 0)
			return false;
		return true;
	}
	public static YeuCauNhapDia selectYeuCauNhapDia(String mayeucau) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM YEUCAUNHAPDIA WHERE MaYeuCau = ? AND isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, mayeucau);
		ResultSet rs = pstmt.executeQuery();
		YeuCauNhapDia temp = new YeuCauNhapDia();
		if(rs.next()) {
			temp.setMaYeuCau(rs.getString(1));
			temp.setNgayYeuCau(DateHelper.parse(rs.getString(2)));
			temp.setDonViCungCap(rs.getString(3));
			temp.setTrangThaiNhap(rs.getString(4));
		}
		return temp;
	}
	public static ResultSet executeQueryselectYeuCauNhapDia(String sqlQuery) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	public static ArrayList<YeuCauNhapDia> getAll() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT * FROM YEUCAUNHAPDIA WHERE isActive = 1";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<YeuCauNhapDia> arr = new ArrayList<YeuCauNhapDia>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			YeuCauNhapDia temp = new YeuCauNhapDia();
			temp.setMaYeuCau(rs.getString(1));
			temp.setNgayYeuCau(DateHelper.parse(rs.getString(2)));
			temp.setDonViCungCap(rs.getString(3));
			temp.setTrangThaiNhap(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<YeuCauNhapDia_Perform> getAllPerform() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaYeuCau, NgayYeuCau, TenDVCC, TenTrangThai FROM YEUCAUNHAPDIA, DONVICUNGCAP, TRANGTHAINHAPDIA WHERE YEUCAUNHAPDIA.isActive = 1 AND TRANGTHAINHAPDIA.MaTrangThai = YEUCAUNHAPDIA.TrangThaiNhap AND YEUCAUNHAPDIA.DonViCungCap = DONVICUNGCAP.MaDVCC";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ArrayList<YeuCauNhapDia_Perform> arr = new ArrayList<YeuCauNhapDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			YeuCauNhapDia_Perform temp = new YeuCauNhapDia_Perform();
			temp.setMaYeuCau(rs.getString(1));
			temp.setNgayYeuCau(DateHelper.parse(rs.getString(2)));
			temp.setDonViCungCap(rs.getString(3));
			temp.setTrangThaiNhap(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<SuaNhapDia_DanhSachBangDia_Perform> getAllSuaNhapDiaPerform(String maYeuCau) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT CHITIETYEUCAUNHAPDIA.MaLoaiBangDia, TenLoaiBangDia, SoLuong FROM CHITIETYEUCAUNHAPDIA, LOAIBANGDIA WHERE MaYeuCau = ? AND CHITIETYEUCAUNHAPDIA.MaLoaiBangDia = LOAIBANGDIA.MaLoaiBangDia";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maYeuCau);
		ArrayList<SuaNhapDia_DanhSachBangDia_Perform> arr = new ArrayList<SuaNhapDia_DanhSachBangDia_Perform>();
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			SuaNhapDia_DanhSachBangDia_Perform temp = new SuaNhapDia_DanhSachBangDia_Perform();
			temp.setMaLoaiBangDia(rs.getString(1));
			temp.setTenLoaiBangDia(rs.getString(2));
			temp.setSoLuong(rs.getInt(3));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<BangDiaHelper> getDiaNhap_SoLuong(String maYeuCau) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaLoaiBangDia, SoLuong FROM CHITIETYEUCAUNHAPDIA WHERE MaYeuCau = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, maYeuCau);
		ArrayList<BangDiaHelper> arr = new ArrayList<BangDiaHelper>();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BangDiaHelper temp = new BangDiaHelper();
			temp.setMaLoaiBangDia(rs.getString(1));
			temp.setSoLuong(rs.getInt(2));
			arr.add(temp);
		}
		return arr;
	}

	public static ArrayList<YeuCauNhapDia_Perform> executeQueryselectYeuCauNhapDiaPerform(String query) throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<YeuCauNhapDia_Perform> arr = new ArrayList<YeuCauNhapDia_Perform>();
		while (rs.next()) {
			YeuCauNhapDia_Perform temp = new YeuCauNhapDia_Perform();
			temp.setMaYeuCau(rs.getString(1));
			temp.setNgayYeuCau(DateHelper.parse(rs.getString(2)));
			temp.setDonViCungCap(rs.getString(3));
			temp.setTrangThaiNhap(rs.getString(4));
			arr.add(temp);
		}
		return arr;
	}

}
