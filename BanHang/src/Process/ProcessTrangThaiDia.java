package Process;

import java.sql.SQLException;

import Database.TrangThaiDia_DAO;

public class ProcessTrangThaiDia {
	public static String getMaTrangThaiTheoTen(String ten) throws ClassNotFoundException, SQLException {
		return TrangThaiDia_DAO.getMaTrangThaiTheoTen(ten);
	}
}
