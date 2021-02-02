package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class QuanLyController {
    @FXML
    private Button btnKhachHang;

    @FXML
    private Button btnNhanVien;

    @FXML
    private Button btnBangDia;
    
    @FXML
    private Button btnQuyDinh;
    
    @FXML
    private Button btnNhaCungCap;


    @FXML
    private Button btnHoaDon;
    
    private Stage stageKhachHang;
    
    private Stage stageNhanVien;

    private Stage stageBangDia;
    
    private Stage stageQuyDinh;
    
    private Stage stageHoaDon;
    
    private Stage stageDVCC;
    //Stage stage = (Stage) btnKhachHang.getScene().getWindow();
    @FXML
	void onClick(ActionEvent event) throws IOException {
		if (event.getSource() == btnKhachHang) {
			if (stageKhachHang == null) {
				loadKhachHang();
			} else if (stageKhachHang.isShowing()) {
				stageKhachHang.toFront();
			} else {
				loadKhachHang();
			}
		} else if (event.getSource() == btnNhanVien) {
			if (stageNhanVien == null) {
				loadNhanVien();
			} else if (stageNhanVien.isShowing()) {
				stageNhanVien.toFront();
			} else {
				loadNhanVien();
			}
		} else if (event.getSource() == btnBangDia) {
			if (stageBangDia == null) {
				loadBangDia();
			} else if (stageBangDia.isShowing()) {
				stageBangDia.toFront();
			} else {
				loadBangDia();
			}
		} else if (event.getSource() == btnHoaDon) {
			if (stageHoaDon == null) {
				loadHoaDon();
			} else if (stageHoaDon.isShowing()) {
				stageHoaDon.toFront();
			} else {
				loadHoaDon();
			}
		} else if (event.getSource() == btnQuyDinh) {
			if (stageQuyDinh == null) {
				loadQuyDinh();
			} else if (stageQuyDinh.isShowing()) {
				stageQuyDinh.toFront();
			} else {
				loadQuyDinh();
			}
		} else if (event.getSource() == btnNhaCungCap) {
			if (stageDVCC == null) {
				loadDVCC();
			} else if (stageDVCC.isShowing()) {
				stageDVCC.toFront();
			} else {
				loadDVCC();
			}
		}
	}
    
    private void loadKhachHang() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/KhachHang.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageKhachHang = new Stage();
		stageKhachHang.setTitle("QuanLyKhachHang");
		stageKhachHang.setScene(new Scene(root));
		stageKhachHang.setResizable(false);
		stageKhachHang.show();
    }
    
    private void loadNhanVien() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/NhanVien.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageNhanVien = new Stage();
		stageNhanVien.setTitle("QuanLyNhanVien");
		stageNhanVien.setScene(new Scene(root));
		stageNhanVien.setResizable(false);
		stageNhanVien.show();
    }
    
    private void loadBangDia() throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/LoaiBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageBangDia = new Stage();
		stageBangDia.setTitle("QuanLyLoaiBangDia");
		stageBangDia.setScene(new Scene(root));
		stageBangDia.setResizable(false);
		stageBangDia.show();
    }
    
    private void loadHoaDon() throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/HoaDon.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageHoaDon = new Stage();
		stageHoaDon.setTitle("HoaDon");
		stageHoaDon.setScene(new Scene(root));
		stageHoaDon.setResizable(false);
		stageHoaDon.show();
    }
    
    private void loadQuyDinh() throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/QuyDinh.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageQuyDinh = new Stage();
		stageQuyDinh.setTitle("QuyDinh");
		stageQuyDinh.setScene(new Scene(root));
		stageQuyDinh.setResizable(false);
		stageQuyDinh.show();
    }
    private void loadDVCC() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/NhaCungCap.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageDVCC = new Stage();
		stageDVCC.setTitle("NhaCungCap");
		stageDVCC.setScene(new Scene(root));
		stageDVCC.setResizable(false);
		stageDVCC.show();
    }
}
