package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Object.KhachHang;
import Process.ProcessKhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LapPhieuThue_LoadKhachHangController implements Initializable{
	@FXML
	private TableView<KhachHang> tableViewKhachHang;

	@FXML
	private TableColumn<KhachHang, String> tbColumnMaKH;

	@FXML
	private TableColumn<KhachHang, String> tbColumnHoVaTen;

	@FXML
	private TableColumn<KhachHang, LocalDate> tbColumnNgaySinh;

	@FXML
	private TableColumn<KhachHang, Integer> tbColumnSDT;

	@FXML
	private TableColumn<KhachHang, Boolean> tbColumnLaKhachQuen;

	@FXML
	private TableColumn<KhachHang, Double> tbColumnTienDu;

	@FXML
	private TableColumn<KhachHang, String> tbColumnDiaChi;

	@FXML
	private TextField textFieldHoVaTen;

	@FXML
	private Button btnTimKiem;

	@FXML
	private Button btnXacNhan;

	@FXML
	void keyPressed(KeyEvent event) {

	}
	
	public KhachHang SelectedKhachHang = null;

	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		if(event.getSource() == btnTimKiem) {
			tableViewKhachHang.setItems(data(ProcessKhachHang.executeQueryKH(ProcessKhachHang.getQuerySelect(textFieldHoVaTen.getText()))));
		} else if (event.getSource() == btnXacNhan) {
			SelectedKhachHang = tableViewKhachHang.getSelectionModel().getSelectedItem();
			//System.out.println(kh.getHoTen());
			if (SelectedKhachHang!=null) {
				LapPhieuThueDiaController.setTenKhachHang(SelectedKhachHang.getHoTen());
			}
			Stage stage = (Stage)btnXacNhan.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tbColumnMaKH.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
		tbColumnHoVaTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		tbColumnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
		tbColumnSDT.setCellValueFactory(new PropertyValueFactory<>("Sdt"));
		tbColumnLaKhachQuen.setCellValueFactory(new PropertyValueFactory<>("LaKHQuen"));
		tbColumnTienDu.setCellValueFactory(new PropertyValueFactory<>("TienDu"));
		tbColumnDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
    	tableViewKhachHang.setItems(data());
	}
	private ObservableList<KhachHang> data() {
		ObservableList<KhachHang> dataKH = FXCollections.observableArrayList();
		ArrayList<KhachHang> arrKH = ProcessKhachHang.getAll();
		for (KhachHang kh : arrKH) {
			dataKH.add(kh);
		}
		return dataKH;
	}
	private ObservableList<KhachHang> data(ArrayList<KhachHang> arrKH) {
		ObservableList<KhachHang> dataKH = FXCollections.observableArrayList();
		for (KhachHang kh : arrKH) {
			dataKH.add(kh);
		}
		return dataKH;
	}

}
