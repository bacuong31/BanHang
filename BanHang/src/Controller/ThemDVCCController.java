package Controller;

import java.sql.SQLException;

import Process.ProcessDVCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ThemDVCCController {
	@FXML
    private TextField tfTenDVCC;

    @FXML
    private TextField tfSDT;

    @FXML
    private TextField tfDiaChi;

    @FXML
    private TextField tfMaDVCC;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnThem;

    @FXML
    void onClick(ActionEvent event) throws NumberFormatException, ClassNotFoundException, SQLException {
    	ProcessDVCC.insertDVCC(tfMaDVCC.getText(),tfTenDVCC.getText(),Integer.valueOf(tfSDT.getText()),tfDiaChi.getText());
    	Stage stage = (Stage) btnThem.getScene().getWindow();
		stage.close();
    }
}
