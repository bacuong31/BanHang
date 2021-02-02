package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.DateHelper;
import Perform_Object.BangDia_Perform;
import Perform_Object.NhapDia_DanhSachBangDia_Perform;
import Perform_Object.SuaNhapDia_DanhSachBangDia_Perform;
import Process.ProcessBangDia;
import Process.ProcessYeuCauNhapDia;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ChinhSuaYeuCauNhapDiaController implements Initializable {
	@FXML
	private static TableView<SuaNhapDia_DanhSachBangDia_Perform> tbViewDanhSachDia;
	
	public static ArrayList<SuaNhapDia_DanhSachBangDia_Perform> Arr;
	
	

	public static ArrayList<SuaNhapDia_DanhSachBangDia_Perform> getArr() {
		return Arr;
	}

	public static void setArr(ArrayList<SuaNhapDia_DanhSachBangDia_Perform> arr) {
		
		Arr = arr;
	}

	@FXML
    private TableColumn<SuaNhapDia_DanhSachBangDia_Perform, String> columnMaLoaiBangDia;

    @FXML
    private TableColumn<SuaNhapDia_DanhSachBangDia_Perform, String> columnTenLoaiBangDia;

    @FXML
    private TableColumn<SuaNhapDia_DanhSachBangDia_Perform, Integer> columnSoLuong;

	@FXML
	private TextField tfMaYeuCau;

	@FXML
	private TextField tfNgay;

	@FXML
	private ChoiceBox<String> choiceBoxNhaCungCap;

	@FXML
	private RadioButton rButtonChoXuLy;

	@FXML
	private ToggleGroup TinhTrang;

	@FXML
	private RadioButton rButtonDaHuy;

	@FXML
	private RadioButton rButtonHoanThanh;

	@FXML
	private Button btnThemDia;

	@FXML
	private Button btnLuu;

	@FXML
	private Button btnXoaDia;

	private Stage stageThemDia;
	
	private static String maYeuCau;

	public String getMaYeuCau() {
		return maYeuCau;
	}

	public void setMaYeuCau(String maYeuCau) {
		this.maYeuCau = maYeuCau;
	}

	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
//		if (event.getSource() == btnThemDia) {
//			if (stageThemDia == null) {
//				FXMLLoader fxmlLoader = new FXMLLoader(
//						getClass().getResource("/FXML/ThemYeuCauNhapDia_LoaiBangDia.fxml"));
//				Parent root = (Parent) fxmlLoader.load();
//				stageThemDia = new Stage();
//				stageThemDia.setScene(new Scene(root));
//				stageThemDia.setTitle("LoaiBangDia");
//				stageThemDia.setResizable(false);
//				stageThemDia.show();
//			} else if (stageThemDia.isShowing()) {
//				stageThemDia.toFront();
//			} else {
//				stageThemDia.show();
//			}
//
//		}
		
		if (event.getSource() == btnLuu) {
			if (rButtonHoanThanh.isSelected()) {
				ProcessBangDia.insertBangDia(tfMaYeuCau.getText());
				loadDataBangDia();
			}
			ProcessYeuCauNhapDia.updateYeuCauNhapDia(tfMaYeuCau.getText(), DateHelper.parse(tfNgay.getText()),
					choiceBoxNhaCungCap.getSelectionModel().getSelectedItem(), rButtonDaHuy.isSelected(),
					rButtonHoanThanh.isSelected());
			
			Stage stage = (Stage)btnLuu.getScene().getWindow();
			stage.close();
		}
	}

	private void loadDataBangDia() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/KhoBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		
	    TableView<BangDia_Perform> tbViewBangDia = (TableView<BangDia_Perform>)fxmlLoader.getNamespace().get("tbViewBangDia");

	   
	    TableColumn<BangDia_Perform, String> columnMaBangDia = (TableColumn<BangDia_Perform, String>)fxmlLoader.getNamespace().get("columnMaBangDia");

	   
	    TableColumn<BangDia_Perform, String> columnTenBangDia = (TableColumn<BangDia_Perform, String>)fxmlLoader.getNamespace().get("columnTenBangDia");

	    TableColumn<BangDia_Perform, String> columnNoiDung = (TableColumn<BangDia_Perform, String>)fxmlLoader.getNamespace().get("columnNoiDung");

	    TableColumn<BangDia_Perform, String> columnGiaTien = (TableColumn<BangDia_Perform, String>)fxmlLoader.getNamespace().get("columnGiaTien");
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("TenLoaiNoiDung"));
		columnGiaTien.setCellValueFactory(new PropertyValueFactory<>("GiaTien"));
		tbViewBangDia.setItems(dataBangDia());
	}
	private ObservableList<BangDia_Perform> dataBangDia() throws ClassNotFoundException, SQLException {
		ObservableList<BangDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<BangDia_Perform> arr = ProcessBangDia.getAll();
		data.addAll(arr);
		return data;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		// TODO Auto-generated method stub
		columnMaLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("MaLoaiBangDia"));
		columnTenLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
//		try {
//			tbViewDanhSachDia.setItems(data());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void updateData() throws ClassNotFoundException, SQLException {
		tbViewDanhSachDia.setItems(data());
	}

	private ObservableList<SuaNhapDia_DanhSachBangDia_Perform> data() throws ClassNotFoundException, SQLException {
		ObservableList<SuaNhapDia_DanhSachBangDia_Perform> data = FXCollections.observableArrayList();
		if (Arr != null) {
			for (SuaNhapDia_DanhSachBangDia_Perform s : Arr) {
				data.add(s);
			}
		}
		return data;
	}

	private static ObservableList<SuaNhapDia_DanhSachBangDia_Perform> data(String maYeuCau) throws ClassNotFoundException, SQLException {
		ObservableList<SuaNhapDia_DanhSachBangDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<SuaNhapDia_DanhSachBangDia_Perform> arr = ProcessYeuCauNhapDia.getBangDia_ChinhSuaYeuCauNhapDia(maYeuCau);
		for (SuaNhapDia_DanhSachBangDia_Perform s : arr) {
			data.add(s);
		}
		return data;
	}
}
