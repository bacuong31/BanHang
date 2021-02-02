package Controller;

import java.sql.SQLException;

import Object.DonViCungCap;
import Process.ProcessDVCC;
import Process.ProcessKhachHang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChinhSuaDVCCController {
	@FXML
	private TextField tfTenDVCC;

	@FXML
	private TextField tfSDT;

	@FXML
	private TextField tfDiaChi;

	@FXML
	private TextField tfMaDVCC;

	@FXML
	private Button btnLuu;
	
	@FXML
	
	private Button btnReset;
	
	private static DonViCungCap preDVCC;

	
	public static DonViCungCap getPreDVCC() {
		return preDVCC;
	}


	public static void setPreDVCC(DonViCungCap preDVCC) {
		ChinhSuaDVCCController.preDVCC = preDVCC;
	}


	@FXML
	void onClick(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnLuu) {
			ProcessDVCC.updateDVCC(tfMaDVCC.getText(),tfTenDVCC.getText(),Integer.valueOf(tfSDT.getText()),tfDiaChi.getText());
			Stage stage = (Stage) btnLuu.getScene().getWindow();
			stage.close();
		} else if (event.getSource() == btnReset) {
			resetDVCC(preDVCC);
		}
	}


	private void resetDVCC(DonViCungCap preDVCC) {
		tfMaDVCC.setText(preDVCC.getMaDVCC());
		tfTenDVCC.setText(preDVCC.getTenDVCC());
		tfSDT.setText(String.valueOf(preDVCC.getSDT()));
		tfDiaChi.setText(preDVCC.getDiaChi());
		
	}
}
