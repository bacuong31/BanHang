package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Database.KhachHang_DAO;
import Object.KhachHang;
import Process.ProcessKhachHang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ChinhSuaKhachHangController implements Initializable{
	@FXML
	private Button btnLuu;

	@FXML
	private DatePicker dtPickerNgaySinh;

	@FXML
	private TextField textFieldHoVaTen;

	@FXML
	private TextField textFieldTienDu;

	@FXML
	private RadioButton rbLaKhachQuen_Yes;

	@FXML
	private ToggleGroup LaKhachQuen;

	@FXML
	private RadioButton rbLaKhachQuen_No;

	@FXML
	private TextField textFieldSDT;

	@FXML
	private TextField textFieldDiaChi;

	@FXML
	private TextField textFieldMaKH;
	
	@FXML
	private Button btnReset;
	
	private static KhachHang PreKhachHang;
	
	

	public KhachHang getPreKhachHang() {
		return PreKhachHang;
	}

	public static void setPreKhachHang(KhachHang preKhachHang) {
		PreKhachHang = preKhachHang;
	}

	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		if (event.getSource() == btnLuu) {
			ProcessKhachHang.updateKhachHang(textFieldMaKH.getText(), textFieldHoVaTen.getText(),
					dtPickerNgaySinh.getValue(), Integer.valueOf(textFieldSDT.getText()),
					rbLaKhachQuen_Yes.isSelected(), Double.parseDouble(textFieldTienDu.getText()),
					textFieldDiaChi.getText(), null);
			Stage stage = (Stage) btnLuu.getScene().getWindow();
			stage.close();
		} else if (event.getSource() == btnReset) {
			resetKhachHang(PreKhachHang);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	private void resetKhachHang(KhachHang preKhachHang) {
		dtPickerNgaySinh.setValue(preKhachHang.getNgaySinh());
		textFieldHoVaTen.setText(preKhachHang.getHoTen());
		textFieldTienDu.setText(String.valueOf(preKhachHang.getTienDu()));
		textFieldSDT.setText(String.valueOf(preKhachHang.getSdt()));
		textFieldDiaChi.setText(preKhachHang.getDiaChi());
		textFieldMaKH.setText(preKhachHang.getMaKH());
		if (preKhachHang.isLaKHQuen()) {
			rbLaKhachQuen_Yes.setSelected(true);
		} else if (!preKhachHang.isLaKHQuen()) {
			rbLaKhachQuen_No.setSelected(true);

		}
	}
}
