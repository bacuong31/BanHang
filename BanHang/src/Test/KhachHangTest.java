package Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Object.KhachHang;

import Database.KhachHang_DAO;

public class KhachHangTest {
	
	public void insert() throws ClassNotFoundException, SQLException {
		/*
		 * String makh = "abc"; String hoten = "Ba Cuong";
		 * 
		 * @SuppressWarnings("deprecation") Date ngaysinh = new Date(2000, 3, 31);
		 * boolean lakhquen = false; double tiendu = 0; KhachHang kh = new
		 * KhachHang(makh,hoten,ngaysinh,lakhquen,tiendu);
		 */
		
		String makh1 = "zcs";
		String hoten1 = "Ba Cuonggg";
		@SuppressWarnings("deprecation")
		Date ngaysinh1 = new Date(2000, 3, 31);
		boolean lakhquen1 = false;
		double tiendu1 = 0;
		KhachHang kh1 = new KhachHang(makh1,hoten1,ngaysinh1,lakhquen1,tiendu1);
		//KhachHang_DAO.insertKhachHang(kh);
		KhachHang_DAO.insertKhachHang(kh1);
	}
	public void getAll() throws ClassNotFoundException, SQLException {
		ArrayList<KhachHang> arr = new ArrayList<KhachHang>();
		arr = KhachHang_DAO.getAll();
		for (int i = 0; i< arr.size(); i++) {
			System.out.println(arr.get(i).getMaKH());
			System.out.println(arr.get(i).getHoTen());
			System.out.println(arr.get(i).getNgaySinh());
			System.out.println(arr.get(i).isLaKHQuen());
			System.out.println(arr.get(i).getTienDu());
		}	
	}
	
	public void delete(String makh) {
		
	}
}
