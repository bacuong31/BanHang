package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Global.GlobalParameter;
import Helper.DateHelper;
import Object.YeuCauNhapDia;
import Perform_Object.SuaNhapDia_DanhSachBangDia_Perform;
import Perform_Object.YeuCauNhapDia_Perform;
import Process.ProcessYeuCauNhapDia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NhapDiaController implements Initializable {
	@FXML
	private Button btnChiTiet;

	@FXML
	private Button btnChinhSua;

	@FXML
	private Button btnXoa;

	@FXML
	private TextField textFieldMaPhieuThue;

	@FXML
	private Button btnTimKiem;

	  @FXML
	    private RadioButton rbChoXuLy;

	    @FXML
	    private ToggleGroup status;

	    @FXML
	    private RadioButton rbDaHuy;

	    @FXML
	    private RadioButton rbHoanThanh;

	
    @FXML
    private DatePicker dtNgayTao;
	@FXML
	private Button btnThemYeuCau;
	
	@FXML
	private TableView<YeuCauNhapDia_Perform> tbViewYeuCauNhapDia;

	@FXML
	private TableColumn<YeuCauNhapDia_Perform, String> columnMaYeuCau;

	@FXML
	private TableColumn<YeuCauNhapDia_Perform, LocalDate> columnNgayTao;

	@FXML
	private TableColumn<YeuCauNhapDia_Perform, String> columnDVCC;

	@FXML
	private TableColumn<YeuCauNhapDia_Perform, String> columnTinhTrang;

	private Stage stageThemYeuCau;
	
	private Stage stageChiTiet;
	
	private Stage stageChinhSua;
	
	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnThemYeuCau) {
			if (stageThemYeuCau == null) {
				loadThemYeuCau();
			} else if (stageThemYeuCau.isShowing()) {
				stageThemYeuCau.toFront();
			} else {
				loadThemYeuCau();
			}
		} else if (event.getSource() == btnChiTiet) {
			if (stageChiTiet == null) {
				loadChiTiet();
			} else if (stageChiTiet.isShowing()) {
				stageChiTiet.toFront();
			} else {
				loadChiTiet();
			}
		} else if (event.getSource() == btnChinhSua) {
			YeuCauNhapDia_Perform yc = tbViewYeuCauNhapDia.getSelectionModel().getSelectedItem();
			if (yc != null &&yc.getTrangThaiNhap().equals("Chờ xử lý")) {
				if (stageChinhSua == null) {
					loadChinhSua(yc);
				} else if (stageChinhSua.isShowing()) {
					stageChinhSua.toFront();
				} else {
					loadChinhSua(yc);
				}
			}
		} else if (event.getSource() == btnTimKiem) {
			
			tbViewYeuCauNhapDia.setItems(data(ProcessYeuCauNhapDia.executeQueryselect(ProcessYeuCauNhapDia.getQueryselect(DateHelper.format(dtNgayTao.getValue()), rbChoXuLy.isSelected(), rbDaHuy.isSelected(), rbHoanThanh.isSelected()))));
		}
	}
	
	private ObservableList<YeuCauNhapDia_Perform> data(ArrayList<YeuCauNhapDia_Perform> arr) {
		ObservableList<YeuCauNhapDia_Perform> data = FXCollections.observableArrayList();
		data.addAll(arr);
		return data;
	}

	private void loadThemYeuCau() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemYeuCauNhapDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField textFieldNhanVien = (TextField)fxmlLoader.getNamespace().get("textFieldNhanVien");
		TextField textFieldNgay = (TextField)fxmlLoader.getNamespace().get("textFieldNgay");
		ChoiceBox<String> choiceBoxNhaCungCap = (ChoiceBox<String>)fxmlLoader.getNamespace().get("choiceBoxNhaCungCap");
		choiceBoxNhaCungCap.getItems().addAll(ProcessYeuCauNhapDia.getAllTenNhaCungCap());
		textFieldNhanVien.setText(GlobalParameter.TenNhanVienDangNhap + " - " + GlobalParameter.MaNhanVienDangNhap);
		textFieldNgay.setText(DateHelper.format(LocalDate.now()));
		stageThemYeuCau = new Stage();
		stageThemYeuCau.setTitle("LapYeuCauNhapDia");
		stageThemYeuCau.setScene(new Scene(root));
		stageThemYeuCau.setResizable(false);
		stageThemYeuCau.showAndWait();
		tbViewYeuCauNhapDia.setItems(data());
		
	}
	
	private void loadChiTiet() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChiTietYeuCauNhapDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageChiTiet = new Stage();
		stageChiTiet.setScene(new Scene(root));
		stageChiTiet.setTitle("ChiTietPhieuNhap");
		stageChiTiet.setResizable(false);
		stageChiTiet.show();
	}

	private void loadChinhSua(YeuCauNhapDia_Perform yc) throws IOException, ClassNotFoundException, SQLException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChinhSuaYeuCauNhapDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaYeuCau = (TextField)fxmlLoader.getNamespace().get("tfMaYeuCau");
		TextField tfNgay = (TextField)fxmlLoader.getNamespace().get("tfNgay");
		ChoiceBox choiceBoxNhaCungCap = (ChoiceBox)fxmlLoader.getNamespace().get("choiceBoxNhaCungCap");
		RadioButton rButtonChoXuLy = (RadioButton)fxmlLoader.getNamespace().get("rButtonChoXuLy");
		RadioButton rButtonDaHuy = (RadioButton)fxmlLoader.getNamespace().get("rButtonDaHuy");
		RadioButton rButtonHoanThanh = (RadioButton)fxmlLoader.getNamespace().get("rButtonHoanThanh");
		TableView<SuaNhapDia_DanhSachBangDia_Perform> tbViewDanhSachDia = (TableView<SuaNhapDia_DanhSachBangDia_Perform>)fxmlLoader.getNamespace().get("tbViewDanhSachDia");
		TableColumn<SuaNhapDia_DanhSachBangDia_Perform, String> columnMaLoaiBangDia = (TableColumn<SuaNhapDia_DanhSachBangDia_Perform, String>) fxmlLoader
				.getNamespace().get("columnMaLoaiBangDia");

		TableColumn<SuaNhapDia_DanhSachBangDia_Perform, String> columnTenLoaiBangDia = (TableColumn<SuaNhapDia_DanhSachBangDia_Perform, String>) fxmlLoader
				.getNamespace().get("columnTenLoaiBangDia");

		TableColumn<SuaNhapDia_DanhSachBangDia_Perform, Integer> columnSoLuong = (TableColumn<SuaNhapDia_DanhSachBangDia_Perform, Integer>) fxmlLoader
				.getNamespace().get("columnSoLuong");
		
		columnMaLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("MaLoaiBangDia"));
		columnTenLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
		tbViewDanhSachDia.setItems(dataChinhSuaDia(yc.getMaYeuCau()));
		
		//ChinhSuaYeuCauNhapDiaController.setArr(get(yc.getMaYeuCau()));
		tfMaYeuCau.setText(yc.getMaYeuCau());
		tfNgay.setText(DateHelper.format(yc.getNgayYeuCau()));
		choiceBoxNhaCungCap.getItems().addAll(ProcessYeuCauNhapDia.getAllTenNhaCungCap());
		choiceBoxNhaCungCap.setValue(yc.getDonViCungCap());
		rButtonChoXuLy.setSelected(true);
		rButtonDaHuy.setSelected(false);
		rButtonHoanThanh.setSelected(false);
		stageChinhSua = new Stage();
		stageChinhSua.setScene(new Scene(root));
		stageChinhSua.setResizable(false);
		stageChinhSua.setTitle("ChinhSuaPhieuNhapDia");
		stageChinhSua.showAndWait();
		tbViewYeuCauNhapDia.setItems(data());
	}

	private ObservableList<SuaNhapDia_DanhSachBangDia_Perform> dataChinhSuaDia(String maYeuCau) throws ClassNotFoundException, SQLException {
		ObservableList<SuaNhapDia_DanhSachBangDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<SuaNhapDia_DanhSachBangDia_Perform> arr = get(maYeuCau);
		for (SuaNhapDia_DanhSachBangDia_Perform ds : arr) {
			data.add(ds);
		}
		return data;
	}

	private ArrayList<SuaNhapDia_DanhSachBangDia_Perform> get(String maYeuCau) throws ClassNotFoundException, SQLException{
		ArrayList<SuaNhapDia_DanhSachBangDia_Perform> arr = ProcessYeuCauNhapDia.getBangDia_ChinhSuaYeuCauNhapDia(maYeuCau);
		return arr;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaYeuCau.setCellValueFactory(new PropertyValueFactory<>("MaYeuCau"));
		columnNgayTao.setCellValueFactory(new PropertyValueFactory<>("NgayYeuCau"));
		columnDVCC.setCellValueFactory(new PropertyValueFactory<>("DonViCungCap"));
		columnTinhTrang.setCellValueFactory(new PropertyValueFactory<>("TrangThaiNhap"));
		try {
			tbViewYeuCauNhapDia.setItems(data());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ObservableList<YeuCauNhapDia_Perform> data() throws ClassNotFoundException, SQLException {
		ObservableList<YeuCauNhapDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<YeuCauNhapDia_Perform> arr = ProcessYeuCauNhapDia.getAllYCND();
		for(YeuCauNhapDia_Perform y : arr) {
			data.add(y);
		}
		return data;
	}
}
