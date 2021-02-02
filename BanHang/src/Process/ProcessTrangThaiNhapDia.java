package Process;

import java.sql.SQLException;

import Database.TrangThaiNhapDia_DAO;

public class ProcessTrangThaiNhapDia {
	public static String getMaTrangThaiTheoTen(String ten) throws ClassNotFoundException, SQLException {
		return TrangThaiNhapDia_DAO.getMaTheoTen(ten);
	}
}
