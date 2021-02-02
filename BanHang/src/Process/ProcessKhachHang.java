package Process;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.KhachHang_DAO;
import Object.KhachHang;
import Object.PhieuChoThueDia;

public class ProcessKhachHang {
	public static ArrayList<KhachHang> getAll() {
		ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
		try {
			arr = KhachHang_DAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public static String getQuerySelect(String hoTen) {
		String query = "SELECT * FROM KHACHHANG WHERE isActive = 1 AND HoTen Like '%" + hoTen + "%'";
		return query;
	}

	public static ArrayList<KhachHang> executeQueryKH(String query) throws ClassNotFoundException, SQLException {
		ArrayList<KhachHang> arr = KhachHang_DAO.executeQueryselectKhachHang(query);
		return arr;
	}

	public static boolean insertKhachHang(String maKH, String hoTen, LocalDate ngaySinh, int Sdt, boolean laKHQuen,
			double tienDu, String diaChi, ArrayList<PhieuChoThueDia> listPhieuChoThueDia)
			throws ClassNotFoundException, SQLException {
		KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, Sdt, laKHQuen, tienDu, diaChi, listPhieuChoThueDia);
		return KhachHang_DAO.insertKhachHang(kh);

	}
	
	public static void deleteKhachHang(KhachHang kh) throws ClassNotFoundException, SQLException {
		KhachHang_DAO.softDeleteKhachHang(kh);
	}
	
	public static boolean updateKhachHang(String maKH, String hoTen, LocalDate ngaySinh, int Sdt, boolean laKHQuen,
			double tienDu, String diaChi, ArrayList<PhieuChoThueDia> listPhieuChoThueDia)
			throws ClassNotFoundException, SQLException {
		KhachHang kh = new KhachHang(maKH, hoTen, ngaySinh, Sdt, laKHQuen, tienDu, diaChi, listPhieuChoThueDia);
		return KhachHang_DAO.updateKhachHang(kh);
	}

	public static String getTenKhachThue(String maPhieuThue) throws ClassNotFoundException, SQLException {
		
		return KhachHang_DAO.getTenKhachThue(maPhieuThue);
	}
}
