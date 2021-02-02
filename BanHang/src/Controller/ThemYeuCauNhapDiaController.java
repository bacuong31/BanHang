package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Global.GlobalParameter;
import Helper.DateHelper;
import Helper.IDHelper;
import Perform_Object.NhapDia_DanhSachBangDia_Perform;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ThemYeuCauNhapDiaController implements Initializable {
	@FXML
	private TextField textFieldNhanVien;

	@FXML
	private TextField textFieldNgay;

	@FXML
	private ChoiceBox<String> choiceBoxNhaCungCap;

	@FXML
	private TableView<NhapDia_DanhSachBangDia_Perform> tbViewDanhSachDia;

	@FXML
	private TableColumn<NhapDia_DanhSachBangDia_Perform, String> columnMaLoaiBangDia;

	@FXML
	private TableColumn<NhapDia_DanhSachBangDia_Perform, String> columnTenLoaiBangDia;

	@FXML
	private TableColumn<NhapDia_DanhSachBangDia_Perform, TextField> columnSoLuong;

	private static ArrayList<NhapDia_DanhSachBangDia_Perform> Arr;
	
	
	public ArrayList<NhapDia_DanhSachBangDia_Perform> getArr() {
		return Arr;
	}

	public static void setArr(ArrayList<NhapDia_DanhSachBangDia_Perform> arr) {
		Arr = arr;
	}

	public static void updateArr(ArrayList<NhapDia_DanhSachBangDia_Perform> arr) {
		if (Arr == null) {
			Arr = arr;
		} else {
			Arr.addAll(arr);		
		}
	}
	public static void removeItemArr(NhapDia_DanhSachBangDia_Perform bangdia) {
		Arr.remove(bangdia);
	}
	@FXML
	private Button btnThemDia;

	@FXML
	private Button btnXacNhan;

	@FXML
	private Button btnXoaDia;

	private Stage stageThemDia;

	private Stage stageHoaDon;

	
	
	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnThemDia) {
			if (stageThemDia == null) {
				loadThemDia();
			} else if (stageThemDia.isShowing()) {
				stageThemDia.toFront();
			} else {
				loadThemDia();
			}
		} else if (event.getSource() == btnXacNhan) {
			if (stageHoaDon == null) {
				loadHoaDon();
			} else if (stageHoaDon.isShowing()) {
				stageHoaDon.toFront();
			} else {
				loadHoaDon();
			}
		} else if (event.getSource() == btnXoaDia) {
			NhapDia_DanhSachBangDia_Perform selected = tbViewDanhSachDia.getSelectionModel().getSelectedItem();
			if (selected!=null) {
				removeItemArr(selected);
				tbViewDanhSachDia.setItems(data());
				//tbViewDanhSachDia.getItems().remove(selected);
			}
		}
	}

	private void loadThemDia() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemYeuCauNhapDia_LoaiBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageThemDia = new Stage();
		stageThemDia.setScene(new Scene(root));
		stageThemDia.setTitle("LoaiBangDia");
		stageThemDia.setResizable(false);
		stageThemDia.showAndWait();
		tbViewDanhSachDia.setItems(data());
	}

	private void loadHoaDon() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemHoaDon.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaHoaDon = (TextField)fxmlLoader.getNamespace().get("tfMaHoaDon");
		TextField tfNgayLap = (TextField)fxmlLoader.getNamespace().get("tfNgayLap");
		TextField tfGiaTri = (TextField)fxmlLoader.getNamespace().get("tfGiaTri");
		TextField tfMoTa = (TextField)fxmlLoader.getNamespace().get("tfMoTa");
		RadioButton rbHoaDonThu = (RadioButton)fxmlLoader.getNamespace().get("rbHoaDonThu");
		RadioButton rbHoaDonChi = (RadioButton)fxmlLoader.getNamespace().get("rbHoaDonChi");
		tfGiaTri.setText(String.valueOf(ProcessYeuCauNhapDia.getTienNhapDia(Arr)));
		rbHoaDonThu.setSelected(false);
		rbHoaDonChi.setSelected(true);
		rbHoaDonThu.setDisable(true);
		rbHoaDonChi.setDisable(true);
		tfMoTa.setText("Nhân viên " + GlobalParameter.TenNhanVienDangNhap + " yêu cầu nhập đĩa");
		tfMaHoaDon.setText(IDHelper.newUUIDString());
		tfNgayLap.setText(DateHelper.format(LocalDate.now()));
		stageHoaDon = new Stage();
		stageHoaDon.setScene(new Scene(root));
		stageHoaDon.setTitle("LapHoaDon");
		stageHoaDon.setResizable(false);
		
		Stage stage = (Stage)btnXacNhan.getScene().getWindow();
		stageHoaDon.showAndWait();
		if (((ThemHoaDonController)fxmlLoader.getController()).isComplete()) {
			stage.close();
			ProcessYeuCauNhapDia.insertYeuCauNhapDia(DateHelper.parse(textFieldNgay.getText()), choiceBoxNhaCungCap.getSelectionModel().getSelectedItem(), "Chờ xử lý", Arr);
			setArr(null);
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("MaLoaiBangDia"));
		columnTenLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
		
	}

	private ObservableList<NhapDia_DanhSachBangDia_Perform> data() {
		// TODO Auto-generated method stub
		ObservableList<NhapDia_DanhSachBangDia_Perform> dataBangDia = FXCollections.observableArrayList();
		if (Arr!= null)
			dataBangDia.addAll(Arr);
		return dataBangDia;
	}
}
