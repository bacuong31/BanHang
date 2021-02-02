package Process;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


import Database.HoaDon_DAO;
import Helper.DateHelper;

import Object.HoaDon;
import Object.HoaDonChi;
import Object.HoaDonThu;
import Perform_Object.HoaDon_Perform;

public class ProcessHoaDon {
	public static ArrayList<HoaDon_Perform> getAll(){
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		try {
			arr = HoaDon_DAO.getAllHoaDon_Perform();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public static String getQuerySelectHoaDon(String maHoaDon, String giaTriMin, String giaTriMax, LocalDate ngayLap, boolean isHoaDonThu, boolean isHoaDonChi) {
		String s = "SELECT * FROM HOADON WHERE isActive = 1";
		if (!maHoaDon.isBlank()) {
			s = s + " AND MaHoaDon Like '%" + maHoaDon + "%'";
		}
		if (!giaTriMin.isBlank()) {
			s = s + " AND GiaTri >= " + Double.valueOf(giaTriMin);
		}
		if (!giaTriMax.isBlank()) {
			s = s + " AND GiaTri <= " + Double.valueOf(giaTriMax);
		}
		if (ngayLap != null) {
			s = s + " AND NgayLapHoaDon = '" + DateHelper.format(ngayLap) + "'";
		}
		if (isHoaDonThu == true && isHoaDonChi == false) {
			s = s + " AND MaLoaiHoaDon = 1";
		} else if (isHoaDonThu == false && isHoaDonChi == true) {
			s = s + " AND MaLoaiHoaDon = 0";
		}
		
		return s;
	}
	
	public static ArrayList<HoaDon_Perform> executeQuerySelectDVCC(String query) throws ClassNotFoundException, SQLException {
		ArrayList<HoaDon_Perform> arr = HoaDon_DAO.executeQueryselectHoaDon(query);
		return arr;
	}
	public static boolean insertHoaDon(String maHoaDon, LocalDate ngayLap, double giaTri, String moTa, boolean isHoaDonThu) throws ClassNotFoundException, SQLException {
		HoaDon hoadon;
		if (isHoaDonThu) {
			hoadon = new HoaDonThu(maHoaDon,ngayLap,giaTri,moTa);
		} else {
			hoadon = new HoaDonChi(maHoaDon,ngayLap,giaTri,moTa);
		}
		return HoaDon_DAO.insertHoaDon(hoadon);
	}
	
	public static void deleteHoaDon(HoaDon_Perform hoaDon) throws ClassNotFoundException, SQLException {
		HoaDon_DAO.softDeleteHoaDon(hoaDon);
	}

	public static ArrayList<HoaDon_Perform> getAllHoaDonChi() {
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		try {
			arr = HoaDon_DAO.getAllHoaDonChi_Perform();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public static String getQueryHoaDonThu(String tungay, String denngay) {
		String s = "SELECT * FROM HOADON WHERE isActive = 1 AND MaLoaiHoaDon = 1";
		if (tungay != null) {
			if (!tungay.isBlank()) {
				s = s + " AND NgayLapHoaDon >= '" + tungay + "'";
			}
		}
		if (denngay != null) {
			if (!denngay.isBlank()) {
				s = s + " AND NgayLapHoaDon <= '" + denngay + "'";
			}
		}
		return s;
	}
	
	public static String getQueryHoaDonChi(String tungay, String denngay) {
		String s = "SELECT * FROM HOADON WHERE isActive = 1 AND MaLoaiHoaDon = 0";
		if (tungay != null) {
			if (!tungay.isBlank())
				s = s + " AND NgayLapHoaDon >= '" + tungay + "'";
		}
		if (denngay != null) {
			if (!denngay.isBlank())
				s = s + " AND NgayLapHoaDon <= '" + denngay + "'";
		}
		return s;
	}
	public static ArrayList<HoaDon_Perform> executeQueryHoaDonThu(String query) throws ClassNotFoundException, SQLException {
		return HoaDon_DAO.executeQueryselectHoaDonThu(query);
	}
	public static ArrayList<HoaDon_Perform> executeQueryHoaDonChi(String query) throws ClassNotFoundException, SQLException {
		return HoaDon_DAO.executeQueryselectHoaDonChi(query);
	}
	public static ArrayList<HoaDon_Perform> getAllHoaDonThu() {
		ArrayList<HoaDon_Perform> arr = new ArrayList<HoaDon_Perform>();
		try {
			arr = HoaDon_DAO.getAllHoaDonThu_Perform();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
