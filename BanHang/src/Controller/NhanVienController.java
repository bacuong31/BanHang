package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.NhanVien_DAO;
import Helper.IDHelper;

import Object.NhanVien;
import Process.ProcessNhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NhanVienController implements Initializable {

    @FXML
    private TableView<NhanVien> tbViewNV;
    
	@FXML
	private TableColumn<NhanVien, String> columnMaNV;

	@FXML
	private TableColumn<NhanVien, String> columnHoTen;

	@FXML
	private TableColumn<NhanVien, LocalDate> columnNgaySinh;
	
    @FXML
    private TableColumn<NhanVien, Integer> columnSDT;

	@FXML
	private TableColumn<NhanVien, String> columnTaiKhoan;

	@FXML
	private TableColumn<NhanVien, String> columnMatKhau;

	@FXML
	private Button btnChinhSua;

	@FXML
	private Button btnXoa;

	@FXML
	private Button btnDangKiNhanVienMoi;

	@FXML
	private TextField tfMaNV;

	@FXML
	private TextField tfHoTen;

	@FXML
	private Button btnTimKiem;

	private Stage stageDangKiNV;

	private Stage stageChinhSua;

	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnDangKiNhanVienMoi) {
			if (stageDangKiNV == null) {
				loadDangKiNhanVienMoi();
			} else if (stageDangKiNV.isShowing()) {
				stageDangKiNV.toFront();
			} else {
				loadDangKiNhanVienMoi();
			}
		} else if (event.getSource() == btnChinhSua) {
			NhanVien nv = tbViewNV.getSelectionModel().getSelectedItem();
			if (nv != null) {
				if (stageChinhSua == null) {
					loadChinhSuaNhanVien(nv);
				} else if (stageChinhSua.isShowing()) {
					stageChinhSua.toFront();
				} else {
					loadChinhSuaNhanVien(nv);
				}
			}
		} else if (event.getSource() == btnXoa) {
			NhanVien nv = tbViewNV.getSelectionModel().getSelectedItem();
			if (nv != null) {
				ProcessNhanVien.deleteNhanVien(nv);
				tbViewNV.setItems(data());
			}
		} else if (event.getSource() == btnTimKiem) {
			tbViewNV.setItems(data(ProcessNhanVien.executeQuerySelectNV(ProcessNhanVien.getQuerySelectNV(tfMaNV.getText(), tfHoTen.getText()))));
		}
		
	}
	
	@FXML
	void onKeyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			tbViewNV.setItems(data(ProcessNhanVien.executeQuerySelectNV(ProcessNhanVien.getQuerySelectNV(tfMaNV.getText(), tfHoTen.getText()))));
		}
	}
	private void loadChinhSuaNhanVien(NhanVien nv) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChinhSuaNhanVien.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaNV = (TextField)fxmlLoader.getNamespace().get("tfMaNV");
		TextField tfTaiKhoan = (TextField)fxmlLoader.getNamespace().get("tfTaiKhoan");
		TextField tfMatKhau = (TextField)fxmlLoader.getNamespace().get("tfMatKhau");
		TextField tfHoTen = (TextField)fxmlLoader.getNamespace().get("tfHoTen");
		TextField tfSDT = (TextField)fxmlLoader.getNamespace().get("tfSDT");
		DatePicker dtNgaySinh = (DatePicker)fxmlLoader.getNamespace().get("dtNgaySinh");
		tfMaNV.setText(nv.getMaNV());
		tfTaiKhoan.setText(nv.getUsername());
		tfMatKhau.setText(nv.getPassword());
		tfHoTen.setText(nv.getHoTen());
		tfSDT.setText(String.valueOf(nv.getSdt()));
		dtNgaySinh.setValue(nv.getNgaySinh());
		ChinhSuaNhanVienController.setPreNhanVien(nv);
		stageChinhSua = new Stage();
		stageChinhSua.setScene(new Scene(root));
		stageChinhSua.setTitle("ChinhSuaThongTinNhanVien");
		stageChinhSua.setResizable(false);
		stageChinhSua.showAndWait();
		tbViewNV.setItems(data());
	}
	private void loadDangKiNhanVienMoi() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemNhanVien.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaNV = (TextField)fxmlLoader.getNamespace().get("tfMaNV");
		tfMaNV.setText(IDHelper.newUUIDString());
		stageDangKiNV = new Stage();
		stageDangKiNV.setScene(new Scene(root));
		stageDangKiNV.setTitle("DangKyNhanVienMoi");
		stageDangKiNV.setResizable(false);
		stageDangKiNV.showAndWait();;
		tbViewNV.setItems(data());
	}
	
	private ObservableList<NhanVien> data(ArrayList<NhanVien> arrNV){
		ObservableList<NhanVien> dataNV = FXCollections.observableArrayList();
		for (NhanVien nv : arrNV) {
			dataNV.add(nv);
		}
		return dataNV;
	}
	
	private ObservableList<NhanVien> data(){
		ObservableList<NhanVien> dataNV = FXCollections.observableArrayList();
		ArrayList<NhanVien> arrNV = ProcessNhanVien.getAll();
		for (NhanVien nv : arrNV) {
			dataNV.add(nv);
		}
		return dataNV;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaNV.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
		columnHoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		columnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
		columnSDT.setCellValueFactory(new PropertyValueFactory<>("Sdt"));
		columnTaiKhoan.setCellValueFactory(new PropertyValueFactory<>("Username"));
		columnMatKhau.setCellValueFactory(new PropertyValueFactory<>("Password"));
		tbViewNV.setItems(data());

	}
}
