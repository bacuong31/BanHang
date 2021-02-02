package Process;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.ThamSo_DAO;
import Object.ThamSo;
import Perform_Object.QuyDinh_Perform;

public class ProcessThamSo {
	public static Double getHeSoThueDiaNguyenVen() throws ClassNotFoundException, SQLException {
		return ThamSo_DAO.getHeSoThueDiaNguyenVen();
	}
	public static Double getHeSoTienCoc() throws ClassNotFoundException, SQLException {
		return ThamSo_DAO.getHeSoTienCoc();
	}
	public static double getHeSoTheoTen(String ten) throws ClassNotFoundException, SQLException {
		return ThamSo_DAO.getHeSoTheoTen(ten);
	}
	public static double getTienPhatTheoNgay() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return ThamSo_DAO.getTienPhat();
	}
	public static ArrayList<QuyDinh_Perform> getAll() throws ClassNotFoundException, SQLException {
		
		return ThamSo_DAO.getAll();
	}
	public static void updateThamSo(String maQuyDinh, String tenQuyDinh, String giaTri, String chuThich) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ThamSo temp = new ThamSo(maQuyDinh,tenQuyDinh,Double.valueOf(giaTri),chuThich);
		ThamSo_DAO.updateThamSo(temp);
	}
}
