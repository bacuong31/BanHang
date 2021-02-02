package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.KhachHang_DAO;
import Helper.DateHelper;
import Helper.IDHelper;
import Object.KhachHang;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KhachHangController implements Initializable {
    @FXML
    private TextField textFieldHoVaTen;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnThemKhachHang;

    @FXML
    private Button btnChinhSua;

    @FXML
    private Button btnXoa;
    @FXML
    private TableColumn<KhachHang, String> tbColumnMaKH;

    @FXML
    private TableColumn<KhachHang, String> tbColumnHoVaTen;

    @FXML
    private TableColumn<KhachHang, LocalDate> tbColumnNgaySinh;

    @FXML
    private TableColumn<KhachHang, Integer> tbColumnSDT;

    @FXML
    private TableColumn<KhachHang, Boolean> tbColumnLaKhachQuen;

    @FXML
    private TableColumn<KhachHang, Integer> tbColumnTienDu;

    @FXML
    private TableColumn<KhachHang, String> tbColumnDiaChi;
    
    @FXML
    private TableView<KhachHang> tableViewKhachHang;
    
    
    private Stage stageThemKhachHang;
    
    private Stage stageChinhSuaKhachHang;


    @FXML
    void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	if(event.getSource() == btnThemKhachHang) {
    		if (stageThemKhachHang == null) {
				loadThemKhachHang();
    		}
    		else if (stageThemKhachHang.isShowing()) {
    			stageThemKhachHang.toFront();
    		}
    		else {
    			loadThemKhachHang();
    		}
    	} else if (event.getSource() == btnChinhSua) {
			KhachHang khachhang = tableViewKhachHang.getSelectionModel().getSelectedItem();
			if (khachhang != null) {
				if (stageChinhSuaKhachHang == null) {
					loadChinhSuaKhachHang(khachhang);
				} else if (stageChinhSuaKhachHang.isShowing()) {
					stageChinhSuaKhachHang.toFront();
				} else {
					loadChinhSuaKhachHang(khachhang);
				}
			}
    	} else if (event.getSource() == btnXoa) {
    		KhachHang khachhang = tableViewKhachHang.getSelectionModel().getSelectedItem();
    		if (khachhang != null) {
    			ProcessKhachHang.deleteKhachHang(khachhang);
    			tableViewKhachHang.setItems(data());
    		}
    	} else if (event.getSource() == btnTimKiem) {
    		tableViewKhachHang.setItems(data(ProcessKhachHang.executeQueryKH(ProcessKhachHang.getQuerySelect(textFieldHoVaTen.getText()))));
    	}
    	
    }

    @FXML
    void keyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if (event.getCode().equals(KeyCode.ENTER)) {
    		tableViewKhachHang.setItems(data(ProcessKhachHang.executeQueryKH(ProcessKhachHang.getQuerySelect(textFieldHoVaTen.getText()))));
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
		tbColumnMaKH.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
		tbColumnHoVaTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
		tbColumnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
		tbColumnSDT.setCellValueFactory(new PropertyValueFactory<>("Sdt"));
		tbColumnLaKhachQuen.setCellValueFactory(new PropertyValueFactory<>("LaKHQuen"));
		tbColumnTienDu.setCellValueFactory(new PropertyValueFactory<>("TienDu"));
		tbColumnDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
    	tableViewKhachHang.setItems(data());
	}
	
	
	
	private ObservableList<KhachHang> data() {
		ObservableList<KhachHang> dataKH = FXCollections.observableArrayList();
		ArrayList<KhachHang> arrKH = ProcessKhachHang.getAll();
		for (KhachHang kh : arrKH) {
			dataKH.add(kh);
		}
		return dataKH;
	}
  
	private ObservableList<KhachHang> data(ArrayList<KhachHang> arrKH) {
		ObservableList<KhachHang> dataKH = FXCollections.observableArrayList();
		for (KhachHang kh : arrKH) {
			dataKH.add(kh);
		}
		return dataKH;
	}
	private void loadThemKhachHang() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemKhachHang.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField textFieldMaKH = (TextField)fxmlLoader.getNamespace().get("textFieldMaKH");
		textFieldMaKH.setText(IDHelper.newUUIDString());
		stageThemKhachHang = new Stage();
		stageThemKhachHang.setScene(new Scene(root));
		stageThemKhachHang.setTitle("ThemKhachHang");
		stageThemKhachHang.setResizable(false);
		stageThemKhachHang.showAndWait();
		tableViewKhachHang.setItems(data());
	}
	private void loadChinhSuaKhachHang(KhachHang khachhang) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChinhSuaKhachHang.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		DatePicker dtPickerNgaySinh = (DatePicker)fxmlLoader.getNamespace().get("dtPickerNgaySinh");
		TextField textFieldHoVaTen = (TextField)fxmlLoader.getNamespace().get("textFieldHoVaTen");
		TextField textFieldTienDu = (TextField)fxmlLoader.getNamespace().get("textFieldTienDu");
		RadioButton rbLaKhachQuen_Yes = (RadioButton)fxmlLoader.getNamespace().get("rbLaKhachQuen_Yes");
		RadioButton rbLaKhachQuen_No = (RadioButton)fxmlLoader.getNamespace().get("rbLaKhachQuen_No");
		TextField textFieldSDT = (TextField)fxmlLoader.getNamespace().get("textFieldSDT");
		TextField textFieldDiaChi = (TextField)fxmlLoader.getNamespace().get("textFieldDiaChi");
		TextField textFieldMaKH = (TextField)fxmlLoader.getNamespace().get("textFieldMaKH");
		dtPickerNgaySinh.setValue(khachhang.getNgaySinh());
		textFieldHoVaTen.setText(khachhang.getHoTen());
		textFieldTienDu.setText(String.valueOf(khachhang.getTienDu()));
		textFieldSDT.setText(String.valueOf(khachhang.getSdt()));
		textFieldDiaChi.setText(khachhang.getDiaChi());
		textFieldMaKH.setText(khachhang.getMaKH());
		if (khachhang.isLaKHQuen()) {
			rbLaKhachQuen_Yes.setSelected(true);
		} else if (!khachhang.isLaKHQuen()) {
			rbLaKhachQuen_No.setSelected(true);
		}
		ChinhSuaKhachHangController.setPreKhachHang(khachhang);
		stageChinhSuaKhachHang = new Stage();
		stageChinhSuaKhachHang.setScene(new Scene(root));
		stageChinhSuaKhachHang.setTitle("ChinhSuaKhachHang");
		stageChinhSuaKhachHang.setResizable(false);
		stageChinhSuaKhachHang.showAndWait();
		tableViewKhachHang.setItems(data());
	}
}
