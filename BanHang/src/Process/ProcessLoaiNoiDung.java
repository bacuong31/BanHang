package Process;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.LoaiNoiDung_DAO;

public class ProcessLoaiNoiDung {
	public static String getMaLoaiNoiDung (String tenLoaiNoiDung) throws ClassNotFoundException, SQLException {
		return LoaiNoiDung_DAO.getMaLoaiNoiDung(tenLoaiNoiDung);
	}
	public static ArrayList<String> getAllTenLoaiNoiDung() throws ClassNotFoundException, SQLException{
		return LoaiNoiDung_DAO.getAllTenLoaiNoiDung();
	}
}
