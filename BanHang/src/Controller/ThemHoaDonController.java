package Controller;

import java.sql.SQLException;

import Helper.DateHelper;
import Process.ProcessHoaDon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ThemHoaDonController {
	@FXML
	private Button btnThem;

	@FXML
	private TextField tfNgayLap;

	@FXML
	private TextField tfMoTa;

	@FXML
	private TextField tfGiaTri;

	@FXML
	private TextField tfMaHoaDon;

	@FXML
	private RadioButton rbHoaDonThu;

	@FXML
	private ToggleGroup HoaDon;

	@FXML
	private RadioButton rbHoaDonChi;
	
	private boolean isComplete = false;
	
	public boolean isComplete() {
		return isComplete;
	}


	@FXML
	void onClick(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
		ProcessHoaDon.insertHoaDon(tfMaHoaDon.getText(), DateHelper.parse(tfNgayLap.getText()),
				Double.valueOf(tfGiaTri.getText()), tfMoTa.getText(), rbHoaDonThu.isSelected());
		Stage stage = (Stage) btnThem.getScene().getWindow();
		isComplete = true;
		stage.close();
	}
	
    public String getMaHoaDon() {
    	return tfMaHoaDon.getText();
    }
}
