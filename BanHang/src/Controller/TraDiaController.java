package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Perform_Object.ChiTietTraDia_Perform;
import Perform_Object.PhieuTraDia_Perform;
import Perform_Object.TraDia_Perform;
import Process.ProcessThueDia;
import Process.ProcessTraDia;
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
import javafx.stage.Stage;

public class TraDiaController implements Initializable {
	@FXML
	private TableView<PhieuTraDia_Perform> tableViewTraDia;

	@FXML
	private TableColumn<PhieuTraDia_Perform, String> columnMaPhieuTra;

	@FXML
	private TableColumn<PhieuTraDia_Perform, String> columnPhieuThamChieu;

	@FXML
	private TableColumn<PhieuTraDia_Perform, LocalDate> columnNgayTra;

	@FXML
	private TableColumn<PhieuTraDia_Perform, String> columnNhanVienTiepNhan;

	@FXML
	private TableColumn<PhieuTraDia_Perform, Double> columnTienBoiThuong;

	@FXML
	private TableColumn<PhieuTraDia_Perform, Double> columnTienHoanTra;

	@FXML
	private Button btnXemChiTiet;

	@FXML
	private Button btnXoa;

	@FXML
	private TextField textFieldMaPhieuTraDia;

	@FXML
	private TextField textFieldMaPhieuThamChieu;

	@FXML
	private DatePicker dtPickerNgayThanhToan;

	@FXML
	private TextField textFieldTenNhanVien;

	@FXML
	private Button btnTimKiem;

	private Stage stageXemChiTiet;

	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnXemChiTiet) {
			PhieuTraDia_Perform temp = tableViewTraDia.getSelectionModel().getSelectedItem();
			if (temp != null) {
				if (stageXemChiTiet == null) {
					loadChiTiet(temp);
				} else if (stageXemChiTiet.isShowing()) {
					stageXemChiTiet.toFront();
				} else {
					loadChiTiet(temp);
				}
			}
		} else if (event.getSource() == btnTimKiem) {
			tableViewTraDia.setItems(data(ProcessTraDia.executeQuerySelectTraDiaPerform(
					ProcessTraDia.getQuerySelect(textFieldMaPhieuTraDia.getText(), dtPickerNgayThanhToan.getValue(),
							textFieldMaPhieuThamChieu.getText(), textFieldMaPhieuThamChieu.getText()))));
		} else if (event.getSource() == btnXoa) {
			PhieuTraDia_Perform temp = tableViewTraDia.getSelectionModel().getSelectedItem();
			if (temp != null) {
				ProcessTraDia.deletePhieuTraDia(temp);
				tableViewTraDia.setItems(data());
			}
		}
	}
	
	private ObservableList<PhieuTraDia_Perform> data(ArrayList<PhieuTraDia_Perform> arr) {
		ObservableList<PhieuTraDia_Perform> data = FXCollections.observableArrayList();
		data.addAll(arr);
		return data;
	}

	private void loadChiTiet(PhieuTraDia_Perform temp) throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChiTietTraDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TableView<ChiTietTraDia_Perform> tbViewDiaTra = (TableView<ChiTietTraDia_Perform>)fxmlLoader.getNamespace().get("tbViewDiaTra");
		TableColumn<ChiTietTraDia_Perform, String> columnMaBangDia = (TableColumn<ChiTietTraDia_Perform, String>)fxmlLoader.getNamespace().get("columnMaBangDia");
		TableColumn<ChiTietTraDia_Perform, String> columnTenBangDia = (TableColumn<ChiTietTraDia_Perform, String>)fxmlLoader.getNamespace().get("columnTenBangDia");;
		TableColumn<ChiTietTraDia_Perform, String> columnTrangThai =  (TableColumn<ChiTietTraDia_Perform, String>)fxmlLoader.getNamespace().get("columnTrangThai");;
		TextField tfMaPhieuTra = (TextField)fxmlLoader.getNamespace().get("tfMaPhieuTra");
		TextField tfThanhToan = (TextField)fxmlLoader.getNamespace().get("tfThanhToan");
		TextField tfPhieuThamChieu = (TextField)fxmlLoader.getNamespace().get("tfPhieuThamChieu");
		tfMaPhieuTra.setText(temp.getMaPhieuTra());
		tfPhieuThamChieu.setText(temp.getPhieuThamChieu());
		if (temp.getTienBoiThuong() > 0) {
			tfThanhToan.setText("Bồi thường " + temp.getTienBoiThuong());
		} else if (temp.getTienHoanTra() > 0) {
			tfThanhToan.setText("Hoàn trả " + temp.getTienHoanTra());
		}
		tbViewDiaTra.setItems(data(temp.getMaPhieuTra()));
		
		stageXemChiTiet = new Stage();
		stageXemChiTiet.setScene(new Scene(root));
		stageXemChiTiet.setResizable(false);
		stageXemChiTiet.setTitle("ChiTietTraDia");
		stageXemChiTiet.show();
	}

	private ObservableList<ChiTietTraDia_Perform> data(String maPhieuTra) throws ClassNotFoundException, SQLException {
		ObservableList<ChiTietTraDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<ChiTietTraDia_Perform> arr = ProcessTraDia.getAllChiTietTraDiaPerform(maPhieuTra);
		data.addAll(arr);
		return data;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaPhieuTra.setCellValueFactory(new PropertyValueFactory<>("MaPhieuTra"));
		columnPhieuThamChieu.setCellValueFactory(new PropertyValueFactory<>("PhieuThamChieu"));
		columnNgayTra.setCellValueFactory(new PropertyValueFactory<>("NgayTra"));
		columnNhanVienTiepNhan.setCellValueFactory(new PropertyValueFactory<>("NhanVienTiepNhan"));
		columnTienBoiThuong.setCellValueFactory(new PropertyValueFactory<>("TienBoiThuong"));
		columnTienHoanTra.setCellValueFactory(new PropertyValueFactory<>("TienHoanTra"));
		try {
			tableViewTraDia.setItems(data());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ObservableList<PhieuTraDia_Perform> data() throws ClassNotFoundException, SQLException {
		ObservableList<PhieuTraDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<PhieuTraDia_Perform> arr = ProcessTraDia.getAllPhieuTraDiaPerform();
		data.addAll(arr);
		return data;
	}

}
