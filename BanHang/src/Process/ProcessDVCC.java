package Process;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.DonViCungCap_DAO;
import Object.DonViCungCap;

public class ProcessDVCC {
	public static ArrayList<DonViCungCap> getAll(){
		ArrayList<DonViCungCap> arr = new ArrayList<DonViCungCap>();
		try {
			arr = DonViCungCap_DAO.getAll();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public static String getQuerySelectDVCC(String tenDVCC) {
		String s = "SELECT * FROM DONVICUNGCAP WHERE isActive = 1";
		if (!tenDVCC.isBlank()) {
			s = s + " AND TenDVCC Like '%" + tenDVCC + "%'";
		}
		return s;
	}
	
	public static ArrayList<DonViCungCap> executeQuerySelectDVCC(String query) throws ClassNotFoundException, SQLException {
		ArrayList<DonViCungCap> arr = DonViCungCap_DAO.executeQueryselectDonViCungCap(query);
		return arr;
	}
	public static boolean insertDVCC(String maDVCC, String tenDVCC, int Sdt, String diaChi) throws ClassNotFoundException, SQLException {
		DonViCungCap dvcc = new DonViCungCap(maDVCC,tenDVCC,Sdt,diaChi,null);
		return DonViCungCap_DAO.insertDonViCungCap(dvcc);
	}
	
	public static boolean updateDVCC(String maDVCC, String tenDVCC, int Sdt, String diaChi) throws ClassNotFoundException, SQLException {
		DonViCungCap dvcc = new DonViCungCap(maDVCC,tenDVCC,Sdt,diaChi,null);
		return DonViCungCap_DAO.updateDonViCungCap(dvcc);
	}
	
	public static void deleteDVCC(DonViCungCap dvcc) throws ClassNotFoundException, SQLException {
		DonViCungCap_DAO.softDeleteDonViCungCap(dvcc);
	}
	
	public static String getMaDVCCTheoTen(String tenDVCC) throws ClassNotFoundException, SQLException {
		return DonViCungCap_DAO.getMaDVCCTheoTen(tenDVCC);
	}
}
