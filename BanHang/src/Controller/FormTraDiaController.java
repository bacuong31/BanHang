package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Global.GlobalParameter;
import Helper.DateHelper;
import Helper.IDHelper;
import Perform_Object.PhieuThue_Perform;
import Perform_Object.TraDia_Perform;
import Process.ProcessKhachHang;
import Process.ProcessThueDia;
import Process.ProcessTraDia;
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

public class FormTraDiaController implements Initializable {
	@FXML
	private TextField tfPhieuThamChieu;

	@FXML
	private TextField tfMaPhieuTra;

	@FXML
	private TableView<TraDia_Perform> tbViewDiaTra;

	@FXML
	private TableColumn<TraDia_Perform, String> columnMaBangDia;

	@FXML
	private TableColumn<TraDia_Perform, String> columnTenBangDia;

	@FXML
	private TableColumn<TraDia_Perform, ChoiceBox<String>> columnTrangThai;

	@FXML
	private Button btnXacNhan;

	private Stage stageHoaDon;

	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		if (stageHoaDon == null) {
			loadHoaDon();
		} else if (stageHoaDon.isShowing()) {
			stageHoaDon.toFront();
		} else {
			loadHoaDon();
		}

	}

	private void loadHoaDon() throws ClassNotFoundException, SQLException, IOException {
		double sumBefore = 0.0;
		double sumAfter = 0.0;
		double tiencoc = 0.0;
		double tienphat = 0.0;
		ObservableList<TraDia_Perform> listDiaTra = tbViewDiaTra.getItems();
		sumAfter = ProcessTraDia.getTienThueAfter(listDiaTra);
		sumBefore = ProcessTraDia.getTienThueBefore(listDiaTra);
		tiencoc = ProcessThueDia.getTienDatCoc(tfPhieuThamChieu.getText());
		tienphat = ProcessThueDia.getTienPhat(LocalDate.now(),tfPhieuThamChieu.getText());
//    	System.out.println(sumBefore);
//    	System.out.println(sumAfter);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemHoaDon.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaHoaDon = (TextField) fxmlLoader.getNamespace().get("tfMaHoaDon");
		TextField tfNgayLap = (TextField) fxmlLoader.getNamespace().get("tfNgayLap");
		TextField tfGiaTri = (TextField) fxmlLoader.getNamespace().get("tfGiaTri");
		TextField tfMoTa = (TextField) fxmlLoader.getNamespace().get("tfMoTa");
		RadioButton rbHoaDonThu = (RadioButton) fxmlLoader.getNamespace().get("rbHoaDonThu");
		RadioButton rbHoaDonChi = (RadioButton) fxmlLoader.getNamespace().get("rbHoaDonChi");
		double giatri = Math.round(((sumAfter + tienphat - sumBefore - tiencoc) * 1000) / 1000);
		if (giatri < 0) {
			rbHoaDonThu.setSelected(false);
			rbHoaDonChi.setSelected(true);
			tfMoTa.setText("Khách hàng " + ProcessKhachHang.getTenKhachThue(tfPhieuThamChieu.getText())
					+ " nhận lại tiền thừa sau thanh toán");
		} else if (giatri >= 0) {
			rbHoaDonThu.setSelected(true);
			rbHoaDonChi.setSelected(false);
			tfMoTa.setText(
					"Khách hàng " + ProcessKhachHang.getTenKhachThue(tfPhieuThamChieu.getText()) + " bồi thường");
		}
		rbHoaDonThu.setDisable(true);
		rbHoaDonChi.setDisable(true);
		tfGiaTri.setText(String.valueOf(Math.abs(giatri)));
		tfMaHoaDon.setText(IDHelper.newUUIDString());
		tfNgayLap.setText(DateHelper.format(LocalDate.now()));
		stageHoaDon = new Stage();
		stageHoaDon.setScene(new Scene(root));
		stageHoaDon.setTitle("LapHoaDon");
		stageHoaDon.setResizable(false);
		stageHoaDon.showAndWait();
		Stage stage = (Stage) btnXacNhan.getScene().getWindow();

		FXMLLoader fxmlLoaderThueDia = new FXMLLoader(getClass().getResource("/FXML/ThueDia.fxml"));
		Parent rootThueDia = (Parent) fxmlLoaderThueDia.load();

		TableView<PhieuThue_Perform> tbViewPhieuChoThue = (TableView<PhieuThue_Perform>) fxmlLoaderThueDia
				.getNamespace().get("tbViewPhieuChoThue");
		TableColumn<PhieuThue_Perform, String> columnMaPhieuThue = (TableColumn<PhieuThue_Perform, String>) fxmlLoaderThueDia
				.getNamespace().get("columnMaPhieuThue");
		TableColumn<PhieuThue_Perform, LocalDate> columnNgayThue = (TableColumn<PhieuThue_Perform, LocalDate>) fxmlLoaderThueDia
				.getNamespace().get("columnNgayThue");
		TableColumn<PhieuThue_Perform, LocalDate> columnNgayHenTra = (TableColumn<PhieuThue_Perform, LocalDate>) fxmlLoaderThueDia
				.getNamespace().get("columnNgayHenTra");
		TableColumn<PhieuThue_Perform, String> columnMaKhachThue = (TableColumn<PhieuThue_Perform, String>) fxmlLoaderThueDia
				.getNamespace().get("columnMaKhachThue");
		TableColumn<PhieuThue_Perform, String> columnMaNhanVien = (TableColumn<PhieuThue_Perform, String>) fxmlLoaderThueDia
				.getNamespace().get("columnMaNhanVien");
		TableColumn<PhieuThue_Perform, Double> columnTienDatCoc = (TableColumn<PhieuThue_Perform, Double>) fxmlLoaderThueDia
				.getNamespace().get("columnTienDatCoc");
		TableColumn<PhieuThue_Perform, String> columnTinhTrang = (TableColumn<PhieuThue_Perform, String>) fxmlLoaderThueDia
				.getNamespace().get("columnTinhTrang");

		columnMaPhieuThue.setCellValueFactory(new PropertyValueFactory<>("MaPhieuThue"));
		columnNgayThue.setCellValueFactory(new PropertyValueFactory<>("NgayThue"));
		columnNgayHenTra.setCellValueFactory(new PropertyValueFactory<>("NgayHenTra"));
		columnMaKhachThue.setCellValueFactory(new PropertyValueFactory<>("KhachHangThue"));
		columnMaNhanVien.setCellValueFactory(new PropertyValueFactory<>("NhanVienTiepNhan"));
		columnTienDatCoc.setCellValueFactory(new PropertyValueFactory<>("HoaDonDatCoc"));
		columnTinhTrang.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));
		if (((ThemHoaDonController) fxmlLoader.getController()).isComplete()) {
			stage.close();
			ObservableList<TraDia_Perform> arr = tbViewDiaTra.getItems();
			ProcessTraDia.updateThongTinDia(arr);
			ProcessThueDia.updateTinhTrangPhieuThue(tfPhieuThamChieu.getText());
			ProcessTraDia.insertPhieuTraDia(tfMaPhieuTra.getText(),tfPhieuThamChieu.getText(),LocalDate.now(),GlobalParameter.MaNhanVienDangNhap,giatri,tfMaHoaDon.getText());
			ProcessTraDia.insertChiTietTraDia(tfMaPhieuTra.getText(),arr);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenBangDia"));
		columnTrangThai.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));

	}
}
