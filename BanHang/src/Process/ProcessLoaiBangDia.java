package Process;

import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.KhachHang_DAO;
import Database.LoaiBangDia_DAO;
import Database.LoaiNoiDung_DAO;
import Object.KhachHang;
import Object.LoaiBangDia;
import Perform_Object.LoaiBangDia_Perform;


public class ProcessLoaiBangDia {
	
	public static ArrayList<String> getAllTenNoiDung() {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			arr = ProcessLoaiNoiDung.getAllTenLoaiNoiDung();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	public static Path moveImageFile(String fromFile, String fileName) throws IOException {
		Path source = Paths.get(fromFile);
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path destination = Paths.get(root.toString(),"src", "Images","Poster", fileName + ".png");		
		return Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	}

	private static String getMaLoaiNoiDung(String tenLoaiNoiDung) throws ClassNotFoundException, SQLException {
		return ProcessLoaiNoiDung.getMaLoaiNoiDung(tenLoaiNoiDung);
	}

	public static boolean insertLoaiBangDia(String maLoaiBangDia, String tenLoaiBangDia, String tenNoiDung,
			double giaTri) throws ClassNotFoundException, SQLException {
		LoaiBangDia temp = new LoaiBangDia(maLoaiBangDia, tenLoaiBangDia, getMaLoaiNoiDung(tenNoiDung), giaTri);
		return LoaiBangDia_DAO.insertLoaiBangDia(temp);
	}

	public static boolean updateLoaiBangDia(String maLoaiBangDia, String tenLoaiBangDia, String tenNoiDung,
			double giaTri) throws ClassNotFoundException, SQLException {
		LoaiBangDia temp = new LoaiBangDia(maLoaiBangDia, tenLoaiBangDia, getMaLoaiNoiDung(tenNoiDung), giaTri);
		return LoaiBangDia_DAO.updateLoaiBangDia(temp);
	}
	
	public static void deleteLoaiBangDia(LoaiBangDia_Perform loaiBangDia) throws ClassNotFoundException, SQLException {
		LoaiBangDia_DAO.softdeleteLoaiBangDia_Perform(loaiBangDia);
	}
	public static ArrayList<LoaiBangDia_Perform> getAll() {
		ArrayList<LoaiBangDia_Perform> arr = new ArrayList<LoaiBangDia_Perform>();
		try {
			arr = LoaiBangDia_DAO.getAllLoaiBangDia_Perform();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	public static String getQuerySelectLoaiBangDia(String maLoaiBangDia, String tenLoaiBangDia, String noiDung, String giaTienMin, String giaTienMax) {
		String s = "SELECT MaLoaiBangDia, TenLoaiBangDia, TenLoaiNoiDung, GiaTri  FROM LOAIBANGDIA, LOAINOIDUNG"
				+ " WHERE LOAIBANGDIA.MaLoaiNoiDung = LOAINOIDUNG.MaLoaiNoiDung AND isActive = 1";
		if (!maLoaiBangDia.isBlank()) {
			s = s + " AND MaLoaiBangDia Like '%" + maLoaiBangDia + "%'";
		}
		if (!tenLoaiBangDia.isBlank()) {
			s = s + " AND TenLoaiBangDia Like '%" + tenLoaiBangDia + "%'";
		}
		if (!noiDung.isBlank()) {
			s = s + " AND TenLoaiNoiDung Like '%" + noiDung + "%'";
		}
		if (!giaTienMin.isBlank()) {
			s = s + " AND GiaTri >= " + Double.parseDouble(giaTienMin);
		}
		if (!giaTienMax.isBlank()) {
			s = s + " AND GiaTri <= " + Double.parseDouble(giaTienMax);
		}
		return s;
	}
	
	public static ArrayList<LoaiBangDia_Perform> executeQuerySelectLoaiBangDia(String query) throws ClassNotFoundException, SQLException {
		return LoaiBangDia_DAO.executeQueryselectLoaiBangDia(query);
	}
	
	public static Double getGiaTriTheoMa(String maLoaiBangDia) throws ClassNotFoundException, SQLException {
		return LoaiBangDia_DAO.getGiaTriTheoMa(maLoaiBangDia);
				
	}
}
