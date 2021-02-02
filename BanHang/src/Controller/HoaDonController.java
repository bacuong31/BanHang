package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.DateHelper;
import Helper.IDHelper;
import Object.KhachHang;
import Perform_Object.HoaDon_Perform;
import Process.ProcessHoaDon;
import Process.ProcessKhachHang;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class HoaDonController implements Initializable{
    @FXML
    private TableView<HoaDon_Perform> tableViewHoaDon;

    @FXML
    private TableColumn<HoaDon_Perform, String> columnMaHoaDon;

    @FXML
    private TableColumn<HoaDon_Perform, LocalDate> columnNgayLap;

    @FXML
    private TableColumn<HoaDon_Perform, Double> columnGiaTri;

    @FXML
    private TableColumn<HoaDon_Perform, String> columnThongTin;

    @FXML
    private TableColumn<HoaDon_Perform, String> columnLoaiHoaDon;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnLapHoaDon;

    @FXML
    private TextField tfMaHoaDon;

    @FXML
    private RadioButton rbHoaDonThu;

    @FXML
    private ToggleGroup HoaDon;

    @FXML
    private RadioButton rbHoaDonChi;

    @FXML
    private DatePicker dtNgayLap;
    
    private Stage stageLapHoaDon;

    @FXML
    private TextField tfGiaTriMin;

    @FXML
    private TextField tfGiaTriMax;

    @FXML
    void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	if (event.getSource() == btnLapHoaDon) {
    		if (stageLapHoaDon == null) {
    			loadLapHoaDon();
    		} else if (stageLapHoaDon.isShowing()) {
    			stageLapHoaDon.toFront();
    		} else {
    			loadLapHoaDon();
    		}
    	} else if (event.getSource() == btnXoa) {
    		HoaDon_Perform hd = tableViewHoaDon.getSelectionModel().getSelectedItem();
    		if (hd!=null) {
    			ProcessHoaDon.deleteHoaDon(hd);
    			tableViewHoaDon.setItems(data());
    		}
    	} else if (event.getSource() == btnTimKiem) {
    		System.out.println(ProcessHoaDon.getQuerySelectHoaDon(tfMaHoaDon.getText(),
					tfGiaTriMin.getText(), tfGiaTriMax.getText(), dtNgayLap.getValue(), rbHoaDonThu.isSelected(), rbHoaDonChi.isSelected()));
			tableViewHoaDon.setItems(
					data(ProcessHoaDon.executeQuerySelectDVCC(ProcessHoaDon.getQuerySelectHoaDon(tfMaHoaDon.getText(),
							tfGiaTriMin.getText(), tfGiaTriMax.getText(), dtNgayLap.getValue(), rbHoaDonThu.isSelected(), rbHoaDonChi.isSelected()))));
		}
    }
    


	private ObservableList<HoaDon_Perform> data(ArrayList<HoaDon_Perform> arr) {
		ObservableList<HoaDon_Perform> dataHD = FXCollections.observableArrayList();
		for (HoaDon_Perform hd : arr) {
			dataHD.add(hd);
		}
		return dataHD;
	}





	@FXML
    void onKeyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			tableViewHoaDon.setItems(
					data(ProcessHoaDon.executeQuerySelectDVCC(ProcessHoaDon.getQuerySelectHoaDon(tfMaHoaDon.getText(),
							tfGiaTriMin.getText(), tfGiaTriMax.getText(), dtNgayLap.getValue(), rbHoaDonThu.isSelected(), rbHoaDonChi.isSelected()))));
		
		}
    }
    private void loadLapHoaDon() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemHoaDon.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaHoaDon = (TextField)fxmlLoader.getNamespace().get("tfMaHoaDon");
		TextField tfNgayLap = (TextField)fxmlLoader.getNamespace().get("tfNgayLap");
		tfMaHoaDon.setText(IDHelper.newUUIDString());
		tfNgayLap.setText(DateHelper.format(LocalDate.now()));
		stageLapHoaDon = new Stage();
		stageLapHoaDon.setTitle("LapHoaDon");
		stageLapHoaDon.setScene(new Scene(root));
		stageLapHoaDon.setResizable(false);
		stageLapHoaDon.showAndWait();
		tableViewHoaDon.setItems(data());
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaHoaDon.setCellValueFactory(new PropertyValueFactory<>("MaHoaDon"));
		columnNgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayLapHoaDon"));
		columnGiaTri.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
		columnThongTin.setCellValueFactory(new PropertyValueFactory<>("Mota"));
		columnLoaiHoaDon.setCellValueFactory(new PropertyValueFactory<>("LoaiHoaDon"));
		tableViewHoaDon.setItems(data());
		
	}


	private ObservableList<HoaDon_Perform> data() {
		ObservableList<HoaDon_Perform> dataHD = FXCollections.observableArrayList();
		ArrayList<HoaDon_Perform> arrHD = ProcessHoaDon.getAll();
		for (HoaDon_Perform hd : arrHD) {
			dataHD.add(hd);
		}
		return dataHD;

	}
}
