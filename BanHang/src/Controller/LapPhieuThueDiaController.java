	package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Global.GlobalParameter;
import Helper.DateHelper;
import Helper.IDHelper;
import Object.KhachHang;
import Perform_Object.AnotherThueDia_Perform;
import Perform_Object.ThueDia_Perform;
import Process.ProcessThueDia;
import Process.ProcessYeuCauNhapDia;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LapPhieuThueDiaController implements Initializable{
	@FXML
	private TableColumn<AnotherThueDia_Perform, String> columnMaBangDia;

	@FXML
	private TableView<AnotherThueDia_Perform> tbViewBangDia;

	@FXML
	private TableColumn<AnotherThueDia_Perform, String> columnTenBangDia;

	@FXML
	private TableColumn<AnotherThueDia_Perform, String> columnNoiDung;

	@FXML
	private Button btnXacNhan;

	@FXML
	private TextField tfMaPhieuThue;

	@FXML
	private DatePicker dtNgayThue;

	@FXML
	private DatePicker dtNgayTra;

	private static String TenKhachHang = "";
	
	
	
	public String getTenKhachHang() {
		return TenKhachHang;
	}

	public static void setTenKhachHang(String tenKhachHang) {
		TenKhachHang = tenKhachHang;
	}

	@FXML
	private TextField tfNhanVien;

	@FXML
	private TextField tfTongTien;

	@FXML
	private TextField tfTienDatCoc;

	@FXML
	private TextField tfKhachThue;

	@FXML
	private Button btnLoadKhachHang;

	private Stage stageKhachHang;
	private Stage stageHoaDon;
	
	public KhachHang selectedKH = null; 

	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnXacNhan) {
			if (stageHoaDon == null) {
				loadHoaDon();
			} else if (stageHoaDon.isShowing()) {
				stageHoaDon.toFront();
			} else {
				loadHoaDon();
			}
		} else if (event.getSource() == btnLoadKhachHang) {
			if (stageKhachHang == null) {
				loadKhachHang();
			} else if (stageKhachHang.isShowing()) {
				stageKhachHang.toFront();
			} else {
				loadKhachHang();
			}
		}
	}

	private void loadHoaDon() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemHoaDon.fxml"));
		FXMLLoader fxmlLoaderThueDia = new FXMLLoader(getClass().getResource("/FXML/ThueDia_KhoBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaHoaDon = (TextField)fxmlLoader.getNamespace().get("tfMaHoaDon");
		TextField tfNgayLap = (TextField)fxmlLoader.getNamespace().get("tfNgayLap");
		TextField tfGiaTri = (TextField)fxmlLoader.getNamespace().get("tfGiaTri");
		TextField tfMoTa = (TextField)fxmlLoader.getNamespace().get("tfMoTa");
		RadioButton rbHoaDonThu = (RadioButton)fxmlLoader.getNamespace().get("rbHoaDonThu");
		RadioButton rbHoaDonChi = (RadioButton)fxmlLoader.getNamespace().get("rbHoaDonChi");
		tfGiaTri.setText(tfTienDatCoc.getText());
		rbHoaDonThu.setSelected(true);
		rbHoaDonChi.setSelected(false);
		rbHoaDonThu.setDisable(true);
		rbHoaDonChi.setDisable(true);
		tfMoTa.setText("Khách hàng " + tfKhachThue.getText()  + " cọc tiền thuê đĩa");
		tfMaHoaDon.setText(IDHelper.newUUIDString());
		tfNgayLap.setText(DateHelper.format(LocalDate.now()));
		stageHoaDon = new Stage();
		stageHoaDon.setScene(new Scene(root));
		stageHoaDon.setTitle("LapHoaDon");
		stageHoaDon.setResizable(false);
		Stage stage1 = ThueDia_KhoBangDiaController.getThisStage();
		Stage stage = (Stage)btnXacNhan.getScene().getWindow();
		stageHoaDon.showAndWait();
		String res = ((ThemHoaDonController)fxmlLoader.getController()).getMaHoaDon();
		if (((ThemHoaDonController)fxmlLoader.getController()).isComplete()) {
			
			stage.close();
			stage1.close();
			
			ProcessThueDia.insertPhieuThue(tfMaPhieuThue.getText(), dtNgayThue.getValue(),dtNgayTra.getValue(),selectedKH.getMaKH(),GlobalParameter.MaNhanVienDangNhap, res);
			ObservableList<ThueDia_Perform> listDiaThue = ((ThueDia_KhoBangDiaController)fxmlLoaderThueDia.getController()).getDataThueDia();
			ProcessThueDia.insertChiTietThueDia(tfMaPhieuThue.getText(),listDiaThue);
			ProcessThueDia.updateDiaThue(listDiaThue);
			//setArr(null);
		}
	}

	private void loadKhachHang() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/LapPhieuThue_LoadKhachHang.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageKhachHang = new Stage();
		stageKhachHang.setScene(new Scene(root));
		stageKhachHang.setTitle("DanhSachKhachHang");
		stageKhachHang.setResizable(false);
		stageKhachHang.showAndWait();
		selectedKH = ((LapPhieuThue_LoadKhachHangController)fxmlLoader.getController()).SelectedKhachHang;
		tfKhachThue.setText(TenKhachHang);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("NoiDung"));
	}
}
