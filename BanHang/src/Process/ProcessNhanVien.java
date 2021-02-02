package Process;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


import Database.NhanVien_DAO;

import Object.NhanVien;


public class ProcessNhanVien {
	public static ArrayList<NhanVien> executeQuerySelectNV(String query) throws ClassNotFoundException, SQLException {
		return NhanVien_DAO.executeQueryselectNhanVien(query);
	}
	
	public static String getQuerySelectNV(String maNV, String hoTen) {
		String s = "SELECT * FROM NHANVIEN WHERE isActive = 1";
		if (!maNV.isBlank()) {
			s = s + " AND MaNV Like '%" + maNV + "%'";
		}
		if (!hoTen.isBlank()) {
			s = s + " AND HoTen Like '%" + hoTen + "%'";
		}
		return s;
	}
	
	public static ArrayList<NhanVien> getAll() {
		ArrayList<NhanVien> arr = new ArrayList<NhanVien>();
		try {
			arr = NhanVien_DAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public static void deleteNhanVien(NhanVien nv) throws ClassNotFoundException, SQLException {
		NhanVien_DAO.softDeleteNhanVien(nv);
	}

	public static boolean updateNhanVien(String maNV, String hoTen, LocalDate ngaySinh, int Sdt, String taiKhoan,
			String matKhau) throws ClassNotFoundException, SQLException {
		NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, Sdt, taiKhoan, matKhau);
		return NhanVien_DAO.updateNhanVien(nv);
	}
	public static boolean insertNhanVien(String maNV, String hoTen, LocalDate ngaySinh, int Sdt, String taiKhoan,
			String matKhau) throws ClassNotFoundException, SQLException {
		NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, Sdt, taiKhoan, matKhau);
		return NhanVien_DAO.insertNhanVien(nv);
	}
}
