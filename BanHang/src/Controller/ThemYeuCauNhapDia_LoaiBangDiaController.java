package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Perform_Object.LoaiBangDia_Perform;
import Perform_Object.NhapDia_DanhSachBangDia_Perform;
import Process.ProcessLoaiBangDia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ThemYeuCauNhapDia_LoaiBangDiaController implements Initializable{
	@FXML
	private TableView<LoaiBangDia_Perform> tableViewLoaiBangDia;

	@FXML
	private TableColumn<LoaiBangDia_Perform, String> columnMaLoaiBangDia;

	@FXML
	private TableColumn<LoaiBangDia_Perform, String> columnTenLoaiBangDia;

	@FXML
	private TableColumn<LoaiBangDia_Perform, String> columnNoiDung;

	@FXML
	private TableColumn<LoaiBangDia_Perform, Double> columnGiaTri;

	@FXML
	private Button btnThem;

	@FXML
	private Button btnTimKiem;

	@FXML
	private TextField tfMaLoaiBangDia;

	@FXML
	private TextField tfTenLoaiBangDia;

	@FXML
	private TextField tfNoiDung;

	@FXML
	private TextField tfTienMin;

	@FXML
	private TextField tfTienMax;

	ObservableList<LoaiBangDia_Perform> selectedItems;

    @FXML
    void onMousePressed(MouseEvent event) {
    	selectedItems = tableViewLoaiBangDia.getSelectionModel().getSelectedItems();
    	
    }
	
	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		if (event.getSource() == btnTimKiem) {
			tableViewLoaiBangDia.setItems(data(ProcessLoaiBangDia.executeQuerySelectLoaiBangDia(
					ProcessLoaiBangDia.getQuerySelectLoaiBangDia(tfMaLoaiBangDia.getText(), tfTenLoaiBangDia.getText(),
							tfNoiDung.getText(), tfTienMin.getText(), tfTienMax.getText()))));
		} else if (event.getSource() == btnThem) {
			if (selectedItems != null) {
				ArrayList<NhapDia_DanhSachBangDia_Perform> arr = new ArrayList<NhapDia_DanhSachBangDia_Perform>();
				for (LoaiBangDia_Perform l : selectedItems) {
					NhapDia_DanhSachBangDia_Perform temp = new NhapDia_DanhSachBangDia_Perform(l.getMaLoaiBangDia(),
							l.getTenLoaiBangDia(), new TextField(String.valueOf("1")));
					arr.add(temp);
				}
				ThemYeuCauNhapDiaController.updateArr(arr);
				Stage stage = (Stage) btnThem.getScene().getWindow();
				stage.close();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("MaLoaiBangDia"));
		columnTenLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("TenLoaiNoiDung"));
		columnGiaTri.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
		tableViewLoaiBangDia.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewLoaiBangDia.setItems(data());
	}

	private ObservableList<LoaiBangDia_Perform> data() {
		ObservableList<LoaiBangDia_Perform> dataLoaiBangDia = FXCollections.observableArrayList();
		ArrayList<LoaiBangDia_Perform> arrLoaiBangDia = ProcessLoaiBangDia.getAll();
		for (LoaiBangDia_Perform temp : arrLoaiBangDia) {
			dataLoaiBangDia.add(temp);
		}
		return dataLoaiBangDia;
	}
	private ObservableList<LoaiBangDia_Perform> data(ArrayList<LoaiBangDia_Perform> arr) {
		ObservableList<LoaiBangDia_Perform> dataLoaiBangDia = FXCollections.observableArrayList();
		for (LoaiBangDia_Perform temp : arr) {
			dataLoaiBangDia.add(temp);
		}
		return dataLoaiBangDia;
	}
}
