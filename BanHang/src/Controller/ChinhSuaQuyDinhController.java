package Controller;

import java.sql.SQLException;

import Process.ProcessThamSo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChinhSuaQuyDinhController {
	@FXML
    private Button btnLuu;

    @FXML
    private TextField tfMaQuyDinh;

    @FXML
    private TextField tfChuThich;

    @FXML
    private TextField tfTenQuyDinh;

    @FXML
    private TextField tfGiaTri;

    @FXML
    void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {
    	ProcessThamSo.updateThamSo(tfMaQuyDinh.getText(),tfTenQuyDinh.getText(),tfGiaTri.getText(),tfChuThich.getText());
    	Stage stage = (Stage)btnLuu.getScene().getWindow();
    	stage.close();
    }
}
