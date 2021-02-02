package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Database.NhanVien_DAO;
import Helper.DateHelper;
import Object.NhanVien;
import Process.ProcessNhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ThemNhanVienController implements Initializable{
	@FXML
	private Button btnThem;

	@FXML
	private Button btnReset;

	@FXML
	private TextField tfMaNV;

	@FXML
	private TextField tfTaiKhoan;

	@FXML
	private TextField tfMatKhau;

	@FXML
	private TextField tfHoTen;

	@FXML
	private DatePicker dtNgaySinh;

	@FXML
	private TextField tfSDT;

	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {

		ProcessNhanVien.insertNhanVien(tfMaNV.getText(), tfHoTen.getText(), dtNgaySinh.getValue(),
				Integer.valueOf(tfSDT.getText()), tfTaiKhoan.getText(), tfMatKhau.getText());
		Stage stage = (Stage) btnThem.getScene().getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dtNgaySinh.setValue(DateHelper.parse("01.01.2000"));
	}
}
