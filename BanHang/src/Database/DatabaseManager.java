	package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import Helper.DateHelper;
import Helper.IDHelper;
import Object.DonViCungCap;
import Object.KhachHang;
import Object.LoaiBangDia;
import Object.LoaiNoiDung;
import Object.NhanVien;
import Object.ThamSo;
import Object.TrangThaiDia;
import Object.TrangThaiNhapDia;
import Perform_Object.QuyDinh_Perform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DatabaseManager {

	private Connection conn = null;
	private static DatabaseManager instance = null;
	String url = "jdbc:sqlite:Database.db";

	public DatabaseManager() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(url);

	}

	boolean checkConnection() {
		if (conn != null)
			return true;
		return false;
	}

	public static DatabaseManager getInstance() throws SQLException, ClassNotFoundException {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

	public void initDatabase() throws SQLException, ClassNotFoundException {
		if (!checkConnection()) {
			System.out.println("Fail to connect database");
			return;
		}
		createTable();
		if (!checkData()) {
			insertData();
		}
	}

	private boolean checkData() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT COUNT(*) FROM LOAINOIDUNG";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		ResultSet rs = pstmt.executeQuery();

		if (rs.getInt(1) > 0) {
			return true;

		}
		return false;
	}

	public void createTable() throws SQLException {
		Statement stmt = conn.createStatement();
		String createTableKhachHang = "CREATE TABLE IF NOT EXISTS KHACHHANG (\n" + "MaKH CHAR(36),\n" + "HoTen TEXT,\n"
				+ "NgaySinh TEXT,\n" + "Sdt INTEGER,\n" + "LaKHQuen,\n" + "TienDu REAL,\n" + "DiaChi TEXT,\n"
				+ "isActive INTEGER,\n" + "PRIMARY KEY(MaKH)\n" + ");";

		String createTableNhanVien = "CREATE TABLE IF NOT EXISTS NHANVIEN (\n" + "MaNV CHAR(36),\n" + "HoTen TEXT,\n"
				+ "NgaySinh TEXT,\n" + "Sdt INTEGER,\n" + "Username TEXT,\n" + "Password TEXT,\n"
				+ "isActive INTEGER,\n" + "PRIMARY KEY (MaNV)\n" + ");";

		String createTableCaLamViec = "CREATE TABLE IF NOT EXISTS CALAMVIEC (\n" + "MaNV CHAR(36),\n"
				+ "ThoiGianBatDau TEXT,\n" + "ThoiGianKetThuc TEXT,\n" + "PRIMARY KEY (MaNV,ThoiGianBatDau),\n"
				+ "FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV)\n" + ");";

		String createTableHoatDong = "CREATE TABLE IF NOT EXISTS HOATDONG (\n" + "NhanVienThucHien CHAR(36),\n"
				+ "ThoiGianThucHien TEXT,\n" + "HoatDong TEXT,\n" + "PRIMARY KEY (NhanVienThucHien,ThoiGianThucHien),\n"
				+ "FOREIGN KEY (NhanVienThucHien) REFERENCES NHANVIEN(MaNV)\n" + ");";

		String createTableHoaDon = "CREATE TABLE IF NOT EXISTS HOADON (\n" + "MaHoaDon CHAR(36),\n"
				+ "NgayLapHoaDon TEXT,\n" + "GiaTri REAL,\n" + "MoTa TEXT,\n" + "MaLoaiHoaDon INTEGER,\n"
				+ "isActive INTEGER,\n" + "PRIMARY KEY (MaHoaDon)\n" + ");";

		String createTableTrangThaiDia = "CREATE TABLE IF NOT EXISTS TRANGTHAIDIA (\n" + "MaTrangThai CHAR(36),\n"
				+ "TenTrangThai TEXT,\n"
				+ "HeSoGiaTri REAL,\n" + "PRIMARY KEY (MaTrangThai)\n" + ");";

		String createTableLoaiNoiDung = "CREATE TABLE IF NOT EXISTS LOAINOIDUNG (\n" + "MaLoaiNoiDung CHAR(36),\n"
				+ "TenLoaiNoiDung TEXT,\n" + "PRIMARY KEY (MaLoaiNoiDung)\n" + ");";

		String createTableLoaiBangDia = "CREATE TABLE IF NOT EXISTS LOAIBANGDIA (\n" + "MaLoaiBangDia CHAR(36),\n"
				+ "TenLoaiBangDia TEXT,\n" + "MaLoaiNoiDung CHAR(36),\n" + "GiaTri REAL,\n"
						+ "isActive INTEGER,\n"
				+ "PRIMARY KEY (MaLoaiBangDia),\n"
				+ "FOREIGN KEY (MaLoaiNoiDung) REFERENCES LOAINOIDUNG(MaLoaiNoiDung)\n" + ");";

		String createTableBangDia = "CREATE TABLE IF NOT EXISTS BANGDIA (\n" + "MaBangDia CHAR(36),\n"
				+ "MaLoaiBangDia CHAR(36),\n" + "MaTrangThai CHAR(36),\n" + "DangChoThue INTEGER,\n"
						+ "isActive INTEGER,\n"
				+ "PRIMARY KEY (MaBangDia),\n" + "FOREIGN KEY (MaLoaiBangDia) REFERENCES LOAIBANGDIA(MaLoaiBangDia),\n"
				+ "FOREIGN KEY (MaTrangThai) REFERENCES TRANGTHAIDIA(MaTrangThai)\n" + ");";

		String createTablePhieuChoThueDia = "CREATE TABLE IF NOT EXISTS PHIEUCHOTHUEDIA (\n" + "MaPhieuThue CHAR(36),\n"
				+ "NgayThue TEXT,\n" + "NgayHenTra TEXT,\n" + "KhachHangThue CHAR(36),\n"
				+ "NhanVienTiepNhan CHAR(36),\n" + "HoaDonDatCoc CHAR(36),\n" + "TinhTrang TEXT,\n"
						+ "PRIMARY KEY (MaPhieuThue),\n"
				+ "FOREIGN KEY (KhachHangThue) REFERENCES KHACHHANG(MaKH),\n"
				+ "FOREIGN KEY (NhanVienTiepNhan) REFERENCES NHANVIEN(MaNV),\n"
				+ "FOREIGN KEY (HoaDonDatCoc) REFERENCES HOADON(MaHoaDon)\n" + ");";

		String createTableChiTietThueDia = "CREATE TABLE IF NOT EXISTS CHITIETTHUEDIA (\n" + "MaPhieuThue CHAR(36),\n"
				+ "MaBangDia CHAR(36),\n" + "PRIMARY KEY (MaPhieuThue,MaBangDia),\n"
				+ "FOREIGN KEY (MaPhieuThue) REFERENCES PHIEUCHOTHUEDIA(MaPhieuThue),\n"
				+ "FOREIGN KEY (MaBangDia) REFERENCES BANGDIA(MaBangDia)\n" + ");";

		String createTablePhieuTraDia = "CREATE TABLE IF NOT EXISTS PHIEUTRADIA (\n" + "MaPhieuTra CHAR(36),\n"
				+ "PhieuThamChieu CHAR(36),\n" + "NgayTra TEXT,\n" + "MaNhanVien CHAR(36),\n"
				+ "HoaDonBoiThuong CHAR(36),\n" + "HoaDonHoanTra CHAR(36),\n" + "isActive INTEGER,\n" + "PRIMARY KEY (MaPhieuTra),\n"
				+ "FOREIGN KEY (PhieuThamChieu) REFERENCES PHIEUCHOTHUEDIA(MaPhieuThue),\n"
				+ "FOREIGN KEY (MaNhanVien) REFERENCES NHANVIEN(MaNV),\n"
				+ "FOREIGN KEY (HoaDonBoiThuong) REFERENCES HOADON(MaHoaDon),\n"
				+ "FOREIGN KEY (HoaDonHoanTra) REFERENCES HOADON(MaHoaDon)\n" + ");";

		String createTableChiTietTraDia = "CREATE TABLE IF NOT EXISTS CHITIETTRADIA (\n" + "MaPhieuTra CHAR(36),\n"
				+ "MaBangDia CHAR(36),\n" + "TrangThaiDia CHAR(36),\n" + "PRIMARY KEY (MaPhieuTra,MaBangDia),\n"
				+ "FOREIGN KEY (MaPhieuTra) REFERENCES PHIEUTRADIA(MaPhieuTra),\n"
				+ "FOREIGN KEY (MaBangDia) REFERENCES BANGDIA(MaBangDia),\n"
				+ "FOREIGN KEY (TrangThaiDia) REFERENCES TRANGTHAIDIA(MaTrangThai)\n" + ");";

		String createTableTrangThaiNhapDia = "CREATE TABLE IF NOT EXISTS TRANGTHAINHAPDIA (\n"
				+ "MaTrangThai CHAR(36),\n" + "TenTrangThai TEXT,\n" + "PRIMARY KEY (MaTrangThai)\n" + ");";

		String createTableDonViCungCap = "CREATE TABLE IF NOT EXISTS DONVICUNGCAP (\n" + "MaDVCC CHAR(36),\n"
				+ "TenDVCC TEXT,\n" + "SDT INTEGER,\n" + "DiaChi TEXT,\n"
						+ "isActive INTEGER,\n" + "PRIMARY KEY (MaDVCC)\n" + ");";

		String createTableYeuCauNhapDia = "CREATE TABLE IF NOT EXISTS YEUCAUNHAPDIA (\n" + "MaYeuCau CHAR(36),\n"
				+ "NgayYeuCau TEXT,\n" + "DonViCungCap CHAR(36),\n" + "TrangThaiNhap CHAR(36),\n"
						+ "isActive INTEGER,\n"
				+ "PRIMARY KEY (MaYeuCau),\n" + "FOREIGN KEY (DonViCungCap) REFERENCES DONVICUNGCAP(MaDVCC),\n"
				+ "FOREIGN KEY (TrangThaiNhap) REFERENCES TRANGTHAINHAPDIA(MaTrangThai)\n" + ");";

		String createTableChiTietYeuCauNhapDia = "CREATE TABLE IF NOT EXISTS CHITIETYEUCAUNHAPDIA (\n"
				+ "MaYeuCau CHAR(36),\n" + "MaLoaiBangDia CHAR(36),\n" + "SoLuong INTEGER,\n"
				+ "PRIMARY KEY (MaYeuCau,MaLoaiBangDia),\n"
				+ "FOREIGN KEY (MaYeuCau) REFERENCES YEUCAUNHAPDIA(MaYeuCau),\n"
				+ "FOREIGN KEY (MaLoaiBangDia) REFERENCES LOAIBANGDIA(MaLoaiBangDia)\n" + ");";

		String createTablePhieuNhapDia = "CREATE TABLE IF NOT EXISTS PHIEUNHAPDIA (\n" + "MaPhieuNhap CHAR(36),\n"
				+ "YeuCauThucHien CHAR(36),\n" + "HoaDonThanhToan CHAR(36),\n" + "PRIMARY KEY (MaPhieuNhap),\n"
				+ "FOREIGN KEY (YeuCauThucHien) REFERENCES YEUCAUNHAPDIA(MaYeuCau),\n"
				+ "FOREIGN KEY (HoaDonThanhToan) REFERENCES HOADON(MaHoaDon)\n" + ");";

		String createTableChiTietPhieuNhap = "CREATE TABLE IF NOT EXISTS CHITIETPHIEUNHAP (\n"
				+ "MaPhieuNhap CHAR(36),\n" + "MaLoaiBangDia CHAR(36),\n" + "SoLuong INTEGER,\n"
				+ "PRIMARY KEY (MaPhieuNhap,MaLoaiBangDia),\n"
				+ "FOREIGN KEY (MaPhieuNhap) REFERENCES PHIEUNHAPDIA(MaPhieuNhap),\n"
				+ "FOREIGN KEY (MaLoaiBangDia) REFERENCES LOAIBANGDIA(MaLoaiBangDia)\n" + ");";

		String createTableThamSo = "CREATE TABLE IF NOT EXISTS THAMSO (\n" + "MaThamSo CHAR(36),\n"
				+ "TenThamSo TEXT,\n" + "GiaTri DOUBLE,\n" + "ChuThich TEXT,\n" + "PRIMARY KEY (MaThamSo)\n" + ");";
		stmt.execute(createTableKhachHang);
		stmt.execute(createTableNhanVien);
		stmt.execute(createTableCaLamViec);
		stmt.execute(createTableHoatDong);
		stmt.execute(createTableHoaDon);
		stmt.execute(createTableTrangThaiDia);
		stmt.execute(createTableLoaiNoiDung);
		stmt.execute(createTableLoaiBangDia);
		stmt.execute(createTableBangDia);
		stmt.execute(createTablePhieuChoThueDia);
		stmt.execute(createTableChiTietThueDia);
		stmt.execute(createTablePhieuTraDia);
		stmt.execute(createTableChiTietTraDia);
		stmt.execute(createTableTrangThaiNhapDia);
		stmt.execute(createTableDonViCungCap);
		stmt.execute(createTableYeuCauNhapDia);
		stmt.execute(createTableChiTietYeuCauNhapDia);
		stmt.execute(createTablePhieuNhapDia);
		stmt.execute(createTableChiTietPhieuNhap);
		stmt.execute(createTableThamSo);
	}

	public void insertData() throws SQLException, ClassNotFoundException {

		LoaiNoiDung nd1 = new LoaiNoiDung(IDHelper.newUUIDString(), "Hành động");
		LoaiNoiDung nd2 = new LoaiNoiDung(IDHelper.newUUIDString(), "Lãng mạn");
		LoaiNoiDung nd3 = new LoaiNoiDung(IDHelper.newUUIDString(), "Tâm lý");
		LoaiNoiDung nd4 = new LoaiNoiDung(IDHelper.newUUIDString(), "Viễn tưởng");
		LoaiNoiDung nd5 = new LoaiNoiDung(IDHelper.newUUIDString(), "Hài hước");
		LoaiNoiDung nd6 = new LoaiNoiDung(IDHelper.newUUIDString(), "Kinh dị");
		LoaiNoiDung nd7 = new LoaiNoiDung(IDHelper.newUUIDString(), "Hoạt hình");
		LoaiNoiDung nd8 = new LoaiNoiDung(IDHelper.newUUIDString(), "Tài liệu");
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd1);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd2);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd3);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd4);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd5);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd6);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd7);
		LoaiNoiDung_DAO.insertLoaiNoiDung(nd8);
		TrangThaiNhapDia tt1 = new TrangThaiNhapDia(IDHelper.newUUIDString(),"Chờ xử lý");
		TrangThaiNhapDia tt2 = new TrangThaiNhapDia(IDHelper.newUUIDString(),"Đã hủy");
		TrangThaiNhapDia tt3 = new TrangThaiNhapDia(IDHelper.newUUIDString(),"Hoàn thành");
		TrangThaiNhapDia_DAO.insertTrangThaiNhapDIa(tt1);
		TrangThaiNhapDia_DAO.insertTrangThaiNhapDIa(tt2);
		TrangThaiNhapDia_DAO.insertTrangThaiNhapDIa(tt3);
		DonViCungCap dvcc = new DonViCungCap(IDHelper.newUUIDString(),"Hồng Bàng",123476879,"70 Hàng Trống",null);
		DonViCungCap dvcc1 = new DonViCungCap(IDHelper.newUUIDString(),"Phan Chu Trinh",843472379,"80 Hai Bà Trưng",null);
		DonViCungCap dvcc2 = new DonViCungCap(IDHelper.newUUIDString(),"Phạm Ngọc Thạch",576478379,"6 Tuệ Tĩnh",null);
		DonViCungCap dvcc3 = new DonViCungCap(IDHelper.newUUIDString(),"Đại học công nghệ thông tin",234935201,"Đông Hòa Dĩ An Bình Dương",null);
		DonViCungCap dvcc4 = new DonViCungCap(IDHelper.newUUIDString(),"Fujinet",24852393,"Bình Thạnh, Hồ Chí Minh",null);
		DonViCungCap dvcc5 = new DonViCungCap(IDHelper.newUUIDString(),"Đại học Kinh Tế - Luật",285642964,"Dĩ An, Bình Dương",null);
		DonViCungCap_DAO.insertDonViCungCap(dvcc);
		DonViCungCap_DAO.insertDonViCungCap(dvcc1);
		DonViCungCap_DAO.insertDonViCungCap(dvcc2);
		DonViCungCap_DAO.insertDonViCungCap(dvcc3);
		DonViCungCap_DAO.insertDonViCungCap(dvcc4);
		DonViCungCap_DAO.insertDonViCungCap(dvcc5);
		TrangThaiDia ttd1 = new TrangThaiDia(IDHelper.newUUIDString(),"Nguyên vẹn", 1.0);
		TrangThaiDia ttd2 = new TrangThaiDia(IDHelper.newUUIDString(),"Hư hỏng nhẹ", 0.8);
		TrangThaiDia ttd3 = new TrangThaiDia(IDHelper.newUUIDString(),"Hư hỏng nặng", 0.5);
		TrangThaiDia ttd4 = new TrangThaiDia(IDHelper.newUUIDString(),"Mất hoàn toàn", 0.0);
		TrangThaiDia_DAO.insertTrangThaiDia(ttd1);
		TrangThaiDia_DAO.insertTrangThaiDia(ttd2);
		TrangThaiDia_DAO.insertTrangThaiDia(ttd3);
		TrangThaiDia_DAO.insertTrangThaiDia(ttd4);
		ThamSo qd1 = new ThamSo(IDHelper.newUUIDString(),"Hệ số tiền đặt cọc",0.9,"Sau khi nhân với giá trị đĩa sẽ ra tiền đặt cọc");
		ThamSo qd2 = new ThamSo(IDHelper.newUUIDString(),"Hệ số tiền thuê đĩa nguyên vẹn",0.1,"Sau khi nhân với giá trị đĩa sẽ ra tiền thuê đĩa");
		ThamSo qd3 = new ThamSo(IDHelper.newUUIDString(),"Hệ số tiền thuê đĩa hư hỏng nhẹ",0.3,"Sau khi nhân với giá trị đĩa sẽ ra tiền thuê đĩa");
		ThamSo qd4 = new ThamSo(IDHelper.newUUIDString(),"Hệ số tiền thuê đĩa hư hỏng nặng",0.8,"Sau khi nhân với giá trị đĩa sẽ ra tiền thuê đĩa");
		ThamSo qd8 = new ThamSo(IDHelper.newUUIDString(),"Hệ số tiền thuê đĩa mất hoàn toàn",1.1,"Sau khi nhân với giá trị đĩa sẽ ra tiền thuê đĩa");
		
		ThamSo qd5 = new ThamSo(IDHelper.newUUIDString(),"Số ngày thuê tối thiểu",1.0,"Số ngày thuê tối thiểu");
		ThamSo qd6 = new ThamSo(IDHelper.newUUIDString(),"Số ngày thuê tối đa",60.0,"Số ngày thuê tối đa");
		ThamSo qd7 = new ThamSo(IDHelper.newUUIDString(),"Tiền phạt mỗi ngày sau khi thuê quá hạn",20000.0,"Không");
		ThamSo_DAO.insertThamSo(qd1);
		ThamSo_DAO.insertThamSo(qd2);
		ThamSo_DAO.insertThamSo(qd3);
		ThamSo_DAO.insertThamSo(qd4);
		ThamSo_DAO.insertThamSo(qd8);
		ThamSo_DAO.insertThamSo(qd5);
		ThamSo_DAO.insertThamSo(qd6);
		ThamSo_DAO.insertThamSo(qd7);
		NhanVien nv = new NhanVien(IDHelper.newUUIDString(), "admin", LocalDate.now(), 1, "admin", "admin");
		NhanVien_DAO.insertNhanVien(nv);
		NhanVien_DAO.softDeleteNhanVien(nv);
		KhachHang kh1 = new KhachHang(IDHelper.newUUIDString(),"Trương Bá Cường", DateHelper.parse("01.01.2000"), 843274023, true, 50000.0, "60 Phố cổ", null);
		KhachHang kh2 = new KhachHang(IDHelper.newUUIDString(),"Ngọc Đăng", DateHelper.parse("01.01.2000"), 124245667, true, 100000.0, "70 Lý Thái Tổ", null);
		KhachHang kh3 = new KhachHang(IDHelper.newUUIDString(),"Hà Minh Hiệu", DateHelper.parse("01.01.2000"), 481724853, false, 0.0, "80 Hàng Bè", null);
		KhachHang_DAO.insertKhachHang(kh1);
		KhachHang_DAO.insertKhachHang(kh2);
		KhachHang_DAO.insertKhachHang(kh3);
	}
}
