package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Perform_Object.ChiTietTraDia_Perform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChiTietTraDiaController implements Initializable{
	@FXML
	private TextField tfPhieuThamChieu;

	@FXML
	private TextField tfMaPhieuTra;

	@FXML
	private TextField tfThanhToan;

	@FXML
	private TableView<ChiTietTraDia_Perform> tbViewDiaTra;

	@FXML
	private TableColumn<ChiTietTraDia_Perform, String> columnMaBangDia;

	@FXML
	private TableColumn<ChiTietTraDia_Perform, String> columnTenBangDia;

	@FXML
	private TableColumn<ChiTietTraDia_Perform, String> columnTrangThai;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenBangDia"));
		columnTrangThai.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));
		//tbViewDiaTra.setItems(data());
	}

//	private ObservableList<ChiTietTraDia_Perform> data() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
