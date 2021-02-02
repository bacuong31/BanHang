package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Global.GlobalParameter;
import Helper.IDHelper;
import Perform_Object.AnotherThueDia_Perform;
import Perform_Object.BangDia_Perform;
import Perform_Object.ThueDia_Perform;
import Process.ProcessBangDia;
import Process.ProcessLoaiBangDia;
import Process.ProcessThamSo;
import Process.ProcessThueDia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ThueDia_KhoBangDiaController implements Initializable{
	@FXML
	private TableView<ThueDia_Perform> tbViewBangDia;

	@FXML
	private TableColumn<ThueDia_Perform, String> columnMaBangDia;

	@FXML
	private TableColumn<ThueDia_Perform, String> columnTenBangDia;

	@FXML
	private TableColumn<ThueDia_Perform, String> columnNoiDung;

	@FXML
	private TableColumn<ThueDia_Perform, Double> columnGiaTien;

	@FXML
	private TableColumn<ThueDia_Perform, CheckBox> columnThue;
	
	   @FXML
	    private TableColumn<ThueDia_Perform, String> columnTinhTrang;
	
	public static ObservableList<ThueDia_Perform> getDataThueDia() {
		return DataThueDia;
	}

	public static void setDataThueDia(ObservableList<ThueDia_Perform> dataThueDia) {
		DataThueDia = dataThueDia;
	}

	@FXML
	private Button btnLapPhieuThueDia;

	@FXML
	private Button btnTimKiem;

	@FXML
	private TextField tfTenBangDia;

	@FXML
	private ChoiceBox<String> choiceBoxNoiDung;

	@FXML
	private TextField tfTienMin;

	@FXML
	private TextField tfTienMax;
	
	private static Stage ThisStage;
	
	public static Stage getThisStage() {
		return ThisStage;
	}

	public static void setThisStage(Stage thisStage) {
		ThisStage = thisStage;
	}

	private static ObservableList<ThueDia_Perform> DataThueDia = FXCollections.observableArrayList();
	
	

	@FXML
	void keyPressed(KeyEvent event) {

	}

	private Stage stage;

	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnTimKiem) {
			String noiDung = "";
			if(choiceBoxNoiDung.getSelectionModel().getSelectedItem() != null) {
				noiDung = choiceBoxNoiDung.getSelectionModel().getSelectedItem();
			};
			tbViewBangDia.setItems(data(ProcessThueDia.executeQuerySelectKhoBangDia(ProcessThueDia.getQuerySelectKhoBangDia(tfTenBangDia.getText(), noiDung, tfTienMin.getText(), tfTienMax.getText()))));
		} else if (event.getSource() == btnLapPhieuThueDia) {
			ObservableList<ThueDia_Perform> tempData = FXCollections.observableArrayList();
			for (ThueDia_Perform temp : tbViewBangDia.getItems()) {
				if (temp.getThue().isSelected()) {
					tempData.add(temp);
				}
				DataThueDia = tempData;
			}
			if (stage == null) {
				loadLapPhieuThueDia();
			} else if (stage.isShowing()) {
				stage.toFront();
			} else {
				loadLapPhieuThueDia();
			}
		}
	}	
	private ObservableList<ThueDia_Perform> data(ArrayList<ThueDia_Perform> arr) {
		ObservableList<ThueDia_Perform> data = FXCollections.observableArrayList();
		data.addAll(arr);
		return data;
	}

	private void loadLapPhieuThueDia() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/LapPhieuThueDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = (Stage)btnLapPhieuThueDia.getScene().getWindow();
		setThisStage(stage);
		TextField tfMaPhieuThue = (TextField)fxmlLoader.getNamespace().get("tfMaPhieuThue");
		DatePicker dtNgayThue =  (DatePicker)fxmlLoader.getNamespace().get("dtNgayThue");
		TextField tfNhanVien = (TextField)fxmlLoader.getNamespace().get("tfNhanVien");
		TextField tfTongTien = (TextField)fxmlLoader.getNamespace().get("tfTongTien");
		TextField tfTienDatCoc = (TextField)fxmlLoader.getNamespace().get("tfTienDatCoc");		
		TableView<AnotherThueDia_Perform> tbViewBangDia = (TableView<AnotherThueDia_Perform>)fxmlLoader.getNamespace().get("tbViewBangDia");

		TableColumn<AnotherThueDia_Perform, String> columnMaBangDia = (TableColumn<AnotherThueDia_Perform, String>)fxmlLoader.getNamespace().get("columnMaBangDia");

		TableColumn<AnotherThueDia_Perform, String> columnTenBangDia = (TableColumn<AnotherThueDia_Perform, String>)fxmlLoader.getNamespace().get("columnTenBangDia");

	
		TableColumn<AnotherThueDia_Perform, String> columnNoiDung = (TableColumn<AnotherThueDia_Perform, String>)fxmlLoader.getNamespace().get("columnNoiDung");
		
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("NoiDung"));
		tbViewBangDia.setItems(getData(DataThueDia));
		tfMaPhieuThue.setText(IDHelper.newUUIDString());
		dtNgayThue.setValue(LocalDate.now());
		tfNhanVien.setText(GlobalParameter.TenNhanVienDangNhap + " - " + GlobalParameter.MaNhanVienDangNhap);
		double tongtienthue = ProcessThueDia.getTienThueTong(getData(DataThueDia));
		double tongtiencoc = tongtienthue*ProcessThamSo.getHeSoTienCoc()/ProcessThamSo.getHeSoThueDiaNguyenVen();
		tfTongTien.setText(String.valueOf(tongtienthue));
		tfTienDatCoc.setText(String.valueOf(tongtiencoc));
		stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.setTitle("LapPhieuThueDia");
		stage.show();
	}
	
	
	private ObservableList<AnotherThueDia_Perform> getData(ObservableList<ThueDia_Perform> dataThue) {
		ObservableList<AnotherThueDia_Perform> data = FXCollections.observableArrayList();
		for (ThueDia_Perform thuedia : dataThue) {
			AnotherThueDia_Perform temp = new AnotherThueDia_Perform();
			temp.setMaBangDia(thuedia.getMaBangDia());
			temp.setNoiDung(thuedia.getNoiDung());
			temp.setTenBangDia(thuedia.getTenBangDia());
			data.add(temp);
		}
		return data;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("NoiDung"));
		columnGiaTien.setCellValueFactory(new PropertyValueFactory<>("GiaTien"));
		columnThue.setCellValueFactory(new PropertyValueFactory<>("Thue"));
		columnTinhTrang.setCellValueFactory(new PropertyValueFactory<>("TinhTrang"));
		choiceBoxNoiDung.getItems().addAll(ProcessLoaiBangDia.getAllTenNoiDung());
		try {
			tbViewBangDia.setItems(data());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ObservableList<ThueDia_Perform> data() throws ClassNotFoundException, SQLException {
		ObservableList<ThueDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<ThueDia_Perform> arr = ProcessThueDia.getAll();
		data.addAll(arr);
		return data;
	}
}
