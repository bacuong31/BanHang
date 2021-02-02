package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.IDHelper;
import Object.DonViCungCap;
import Object.KhachHang;
import Process.ProcessDVCC;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DonViCungCapController implements Initializable{
    @FXML
    private TableView<DonViCungCap> tableViewDVCC;

    @FXML
    private TableColumn<DonViCungCap, String> columnMaDVCC;

    @FXML
    private TableColumn<DonViCungCap, String> columnTenDVCC;

    @FXML
    private TableColumn<DonViCungCap, Integer> columnSDT;

    @FXML
    private TableColumn<DonViCungCap, String> columnDiaChi;

    @FXML
    private TextField tfTenDVCC;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnThemDVCC;

    @FXML
    private Button btnChinhSua;

    @FXML
    private Button btnXoa;
    
    private Stage stageThemDVCC;
    
    private Stage stageChinhSuaDVCC;

    @FXML
    void keyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if (event.getCode().equals(KeyCode.ENTER)) {
    		tableViewDVCC.setItems(data(ProcessDVCC.executeQuerySelectDVCC(ProcessDVCC.getQuerySelectDVCC(tfTenDVCC.getText()))));
    	}
    }

    @FXML
    void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
    	if(event.getSource() == btnThemDVCC) {
    		if (stageThemDVCC == null) {
    			loadThemDVCC();
    		} else if (stageThemDVCC.isShowing()) {
    			stageThemDVCC.toFront();
    		} else {
    			loadThemDVCC();
    		}
    	} else if (event.getSource() == btnChinhSua) {
			DonViCungCap dvcc = tableViewDVCC.getSelectionModel().getSelectedItem();
			if (dvcc != null) {
				if (stageChinhSuaDVCC == null) {
					loadChinhSuaDVCC(dvcc);
				} else if (stageChinhSuaDVCC.isShowing()) {
					stageChinhSuaDVCC.toFront();
				} else {
					loadChinhSuaDVCC(dvcc);
				}
			}
		} else if (event.getSource() == btnXoa) {
			DonViCungCap dvcc = tableViewDVCC.getSelectionModel().getSelectedItem();
			if (dvcc != null) {
				ProcessDVCC.deleteDVCC(dvcc);
				tableViewDVCC.setItems(data());
			}
		} else if (event.getSource() == btnTimKiem) {
			tableViewDVCC.setItems(data(ProcessDVCC.executeQuerySelectDVCC(ProcessDVCC.getQuerySelectDVCC(tfTenDVCC.getText()))));
		}
    }
    
	private ObservableList<DonViCungCap> data(ArrayList<DonViCungCap> arr) {
		ObservableList<DonViCungCap> dataDVCC = FXCollections.observableArrayList();
		for (DonViCungCap dvcc : arr) {
			dataDVCC.add(dvcc);
		}
		return dataDVCC;
	}

	private void loadThemDVCC() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemDVCC.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaDVCC = (TextField)fxmlLoader.getNamespace().get("tfMaDVCC");
		tfMaDVCC.setText(IDHelper.newUUIDString());
		stageThemDVCC = new Stage();
		stageThemDVCC.setScene(new Scene(root));
		stageThemDVCC.setTitle("ThemDVCC");
		stageThemDVCC.setResizable(false);
		stageThemDVCC.showAndWait();
		tableViewDVCC.setItems(data());
    }
    
    private void loadChinhSuaDVCC(DonViCungCap dvcc) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChinhSuaDVCC.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaDVCC = (TextField)fxmlLoader.getNamespace().get("tfMaDVCC");
		TextField tfTenDVCC = (TextField)fxmlLoader.getNamespace().get("tfTenDVCC");
		TextField tfSDT = (TextField)fxmlLoader.getNamespace().get("tfSDT");
		TextField tfDiaChi = (TextField)fxmlLoader.getNamespace().get("tfDiaChi");
		tfMaDVCC.setText(dvcc.getMaDVCC());
		tfTenDVCC.setText(dvcc.getTenDVCC());
		tfSDT.setText(String.valueOf(dvcc.getSDT()));
		tfDiaChi.setText(dvcc.getDiaChi());
		ChinhSuaDVCCController.setPreDVCC(dvcc);
		stageChinhSuaDVCC = new Stage();
		stageChinhSuaDVCC.setScene(new Scene(root));
		stageChinhSuaDVCC.setTitle("ChinhSuaDVCC");
		stageChinhSuaDVCC.setResizable(false);
		stageChinhSuaDVCC.showAndWait();
		tableViewDVCC.setItems(data());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaDVCC.setCellValueFactory(new PropertyValueFactory<>("MaDVCC"));
		columnTenDVCC.setCellValueFactory(new PropertyValueFactory<>("TenDVCC"));
		columnSDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
		columnDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
		tableViewDVCC.setItems(data());
	}

	private ObservableList<DonViCungCap> data() {
		ObservableList<DonViCungCap> dataDVCC = FXCollections.observableArrayList();
		ArrayList<DonViCungCap> arrDVCC = ProcessDVCC.getAll();
		for (DonViCungCap temp : arrDVCC) {
			dataDVCC.add(temp);
		}
		return dataDVCC;
	}
}
