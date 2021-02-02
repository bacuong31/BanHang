package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.KhachHang_DAO;
import Helper.DateHelper;
import Object.KhachHang;
import Process.ProcessKhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ThemKhachHangController implements Initializable{
	@FXML
	private Button btnThem;

	@FXML
	private DatePicker dtPickerNgaySinh;

	@FXML
	private TextField textFieldHoTen;

	@FXML
	private TextField textFieldTienDu;

	@FXML
	private RadioButton rButtonLaKhachQuen_Yes;

	@FXML
	private ToggleGroup LaKhachQuen;

	@FXML
	private RadioButton rButtonLaKhachQuen_No;

	@FXML
	private TextField textFieldSDT;

	@FXML
	private TextField textFieldDiaChi;

	@FXML
	private TextField textFieldMaKH;

	@FXML
	private Button btnReset;

	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		ProcessKhachHang.insertKhachHang(textFieldMaKH.getText(), textFieldHoTen.getText(), dtPickerNgaySinh.getValue(),
				Integer.valueOf(textFieldSDT.getText()), rButtonLaKhachQuen_Yes.isSelected(),
				Double.parseDouble(textFieldTienDu.getText()), textFieldDiaChi.getText(), null);
		Stage stage = (Stage) btnThem.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dtPickerNgaySinh.setValue(DateHelper.parse("01.01.2000"));
	}

}
