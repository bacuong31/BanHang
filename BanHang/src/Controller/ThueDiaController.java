package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.IDHelper;
import Perform_Object.PhieuThue_Perform;
import Perform_Object.TraDia_Perform;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ThueDiaController implements Initializable {

	 @FXML
	    private TableView<PhieuThue_Perform> tbViewPhieuChoThue;

	    @FXML
	    private TableColumn<PhieuThue_Perform, String> columnMaPhieuThue;

	    @FXML
	    private TableColumn<PhieuThue_Perform, LocalDate> columnNgayThue;

	    @FXML
	    private TableColumn<PhieuThue_Perform, LocalDate> columnNgayHenTra;

	    @FXML
	    private TableColumn<PhieuThue_Perform, String> columnMaKhachThue;

	    @FXML
	    private TableColumn<PhieuThue_Perform, String> columnMaNhanVien;

	    @FXML
	    private TableColumn<PhieuThue_Perform, Double> columnTienDatCoc;

	    @FXML
	    private TableColumn<PhieuThue_Perform, String> columnTinhTrang;
    @FXML
    private Button btnChiTiet;

    @FXML
    private Button btnXoa;

    @FXML
    private TextField textFieldMaPhieuThue;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnThueDia;
    
    @FXML
    private Button btnTraDia;


    private Stage stageThueDia;
    
    private Stage stageChiTietThueDia;
    
    private Stage stageTraDia;
    @FXML
    void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnThueDia) {
			if (stageThueDia == null) {
				
				loadThueDia();
			} else if (stageThueDia.isShowing()) {
				stageThueDia.toFront();
			} else {
				loadThueDia();
			}

		} else if (event.getSource() == btnChiTiet) {
			if (stageChiTietThueDia == null) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChiTietThueDia.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				stageChiTietThueDia = new Stage();
				stageChiTietThueDia.setScene(new Scene(root));
				stageChiTietThueDia.setResizable(false);
				stageChiTietThueDia.setTitle("ChiTietThueDia");
				stageChiTietThueDia.show();
			} else if (stageChiTietThueDia.isShowing()) {
				stageChiTietThueDia.toFront();
			} else {
				stageChiTietThueDia.show();
			}

		} else if (event.getSource() == btnTraDia) {
			PhieuThue_Perform temp = tbViewPhieuChoThue.getSelectionModel().getSelectedItem();
			if (temp != null && temp.getTrangThai().equals("Chưa trả đĩa")) {
				if (stageTraDia == null) {
					loadTraDia(temp);
				} else if (stageTraDia.isShowing()) {
					stageTraDia.toFront();
				} else {
					loadTraDia(temp);
				}
			}
		}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		    columnMaPhieuThue.setCellValueFactory(new PropertyValueFactory<>("MaPhieuThue"));
		    columnNgayThue.setCellValueFactory(new PropertyValueFactory<>("NgayThue"));  
		    columnNgayHenTra.setCellValueFactory(new PropertyValueFactory<>("NgayHenTra"));	    
		    columnMaKhachThue.setCellValueFactory(new PropertyValueFactory<>("KhachHangThue"));
		    columnMaNhanVien.setCellValueFactory(new PropertyValueFactory<>("NhanVienTiepNhan"));
		    columnTienDatCoc.setCellValueFactory(new PropertyValueFactory<>("HoaDonDatCoc"));
		    columnTinhTrang.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));
		    try {
				tbViewPhieuChoThue.setItems(data());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	
	}
	private ObservableList<PhieuThue_Perform> data() throws ClassNotFoundException, SQLException {
		ObservableList<PhieuThue_Perform> data = FXCollections.observableArrayList();
		ArrayList<PhieuThue_Perform> arr = ProcessThueDia.getAllPhieuThuePerForm();
		data.addAll(arr);
		return data;
	}
	
	private void loadTraDia(PhieuThue_Perform phieuthue) throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FormTraDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaPhieuTra = (TextField)fxmlLoader.getNamespace().get("tfMaPhieuTra");
		TextField tfPhieuThamChieu = (TextField)fxmlLoader.getNamespace().get("tfPhieuThamChieu");
		TableView<TraDia_Perform> tbViewDiaTra = (TableView<TraDia_Perform>)fxmlLoader.getNamespace().get("tbViewDiaTra");
		TableColumn<TraDia_Perform, String> columnMaBangDia = (TableColumn<TraDia_Perform, String>)fxmlLoader.getNamespace().get("columnMaBangDia");
		TableColumn<TraDia_Perform, String> columnTenBangDia = (TableColumn<TraDia_Perform, String>)fxmlLoader.getNamespace().get("columnTenBangDia");
		TableColumn<TraDia_Perform, ChoiceBox<String>> columnTrangThai = (TableColumn<TraDia_Perform, ChoiceBox<String>>)fxmlLoader.getNamespace().get("columnTrangThai");
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenBangDia"));  
		columnTrangThai.setCellValueFactory(new PropertyValueFactory<>("TrangThai"));	    
		tbViewDiaTra.setItems(dataDiaTra(phieuthue.getMaPhieuThue()));
		tfMaPhieuTra.setText(IDHelper.newUUIDString());
		tfPhieuThamChieu.setText(phieuthue.getMaPhieuThue());
		stageTraDia = new Stage();
		stageTraDia.setScene(new Scene(root));
		stageTraDia.setResizable(false);
		stageTraDia.setTitle("TraDia");
		stageTraDia.showAndWait();
		tbViewPhieuChoThue.setItems(data());
	}

	private ObservableList<TraDia_Perform> dataDiaTra(String maPhieuThue) throws ClassNotFoundException, SQLException {
		ObservableList<TraDia_Perform> dataDiaTra = FXCollections.observableArrayList();
		ArrayList<TraDia_Perform> arr = ProcessThueDia.getDiaTraPerForm(maPhieuThue);
		dataDiaTra.addAll(arr);
		return dataDiaTra;
	}
	private void loadThueDia() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThueDia_KhoBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		stageThueDia = new Stage();
		stageThueDia.setScene(new Scene(root));
		stageThueDia.setResizable(false);
		stageThueDia.setTitle("ThueDia");
		stageThueDia.showAndWait();
		tbViewPhieuChoThue.setItems(data());
	}
}
