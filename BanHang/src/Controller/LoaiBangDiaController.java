package Controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.IDHelper;
import Object.KhachHang;
import Perform_Object.LoaiBangDia_Perform;
import Process.ProcessKhachHang;
import Process.ProcessLoaiBangDia;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoaiBangDiaController implements Initializable {
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
	private Button btnChinhSua;

	@FXML
	private Button btnXoa;

	@FXML
	private Button btnTimKiem;

	@FXML
	private Button btnThemLoaiBangDia;

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

	private Stage stageChinhSuaLoaiBangDia;
	
	private Stage stageThemLoaiBangDia;
	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnChinhSua) {
			LoaiBangDia_Perform loaiBangDia = tableViewLoaiBangDia.getSelectionModel().getSelectedItem();
			if (loaiBangDia != null) {
				if (stageChinhSuaLoaiBangDia == null) {
					loadChinhSuaLoaiBangDia(loaiBangDia);
				} else if (stageChinhSuaLoaiBangDia.isShowing()) {
					stageChinhSuaLoaiBangDia.toFront();
					
				} else {
					loadChinhSuaLoaiBangDia(loaiBangDia);
				}
			}
		} else if (event.getSource() == btnThemLoaiBangDia) {
			if (stageThemLoaiBangDia == null) {
				loadThemLoaiBangDia();
			} else if (stageThemLoaiBangDia.isShowing()) {
				stageThemLoaiBangDia.toFront();
			} else {
				loadThemLoaiBangDia();
			}

		} else if (event.getSource() == btnXoa) {
			LoaiBangDia_Perform temp = tableViewLoaiBangDia.getSelectionModel().getSelectedItem();
			if (temp != null) {
				ProcessLoaiBangDia.deleteLoaiBangDia(temp);
				tableViewLoaiBangDia.setItems(data());
			}
		} else if (event.getSource() == btnTimKiem) {
			tableViewLoaiBangDia.setItems(data(ProcessLoaiBangDia.executeQuerySelectLoaiBangDia(
					ProcessLoaiBangDia.getQuerySelectLoaiBangDia(tfMaLoaiBangDia.getText(), tfTenLoaiBangDia.getText(),
							tfNoiDung.getText(), tfTienMin.getText(), tfTienMax.getText()))));
		}
	}
	

    @FXML
    void onKeyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if (event.getCode().equals(KeyCode.ENTER)) {
    		tableViewLoaiBangDia.setItems(data(ProcessLoaiBangDia.executeQuerySelectLoaiBangDia(
					ProcessLoaiBangDia.getQuerySelectLoaiBangDia(tfMaLoaiBangDia.getText(), tfTenLoaiBangDia.getText(),
							tfNoiDung.getText(), tfTienMin.getText(), tfTienMax.getText()))));
    	}
    }
	private void loadChinhSuaLoaiBangDia(LoaiBangDia_Perform loaiBangDia) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChinhSuaLoaiBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		
		TextField tfMaLoaiBangDia = (TextField)fxmlLoader.getNamespace().get("tfMaLoaiBangDia");
		TextField tfTenLoaiBangDia = (TextField)fxmlLoader.getNamespace().get("tfTenLoaiBangDia");
		ChoiceBox<String> choiceBoxNoiDung = (ChoiceBox<String>)fxmlLoader.getNamespace().get("choiceBoxNoiDung");
		choiceBoxNoiDung.getItems().addAll(ProcessLoaiBangDia.getAllTenNoiDung());
		TextField tfGiaTri = (TextField)fxmlLoader.getNamespace().get("tfGiaTri");
		ImageView imageDia = (ImageView)fxmlLoader.getNamespace().get("imageDia");
		tfMaLoaiBangDia.setText(loaiBangDia.getMaLoaiBangDia());
		tfTenLoaiBangDia.setText(loaiBangDia.getTenLoaiBangDia());
		choiceBoxNoiDung.getSelectionModel().select(loaiBangDia.getTenLoaiNoiDung());
		tfGiaTri.setText(String.valueOf(loaiBangDia.getGiaTri()));
		Path root_Images = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path destination = Paths.get(root_Images.toString(),"src", "Images","Poster", loaiBangDia.getMaLoaiBangDia() + ".png");
		if (Files.exists(destination)) {
			Image temp = new Image(destination.toUri().toString());
			imageDia.setImage(temp);
		}
		stageChinhSuaLoaiBangDia = new Stage();
		stageChinhSuaLoaiBangDia.setTitle("ChinhSuaLoaiBangDia");
		stageChinhSuaLoaiBangDia.setScene(new Scene(root));
		stageChinhSuaLoaiBangDia.setResizable(false);
		stageChinhSuaLoaiBangDia.showAndWait();
		tableViewLoaiBangDia.setItems(data());
	}
	
	private void loadThemLoaiBangDia() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ThemLoaiBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaLoaiBangDia = (TextField)fxmlLoader.getNamespace().get("tfMaLoaiBangDia");
		ChoiceBox<String> choiceBoxNoiDung = (ChoiceBox)fxmlLoader.getNamespace().get("choiceBoxNoiDung");
		choiceBoxNoiDung.getItems().addAll(ProcessLoaiBangDia.getAllTenNoiDung());
		tfMaLoaiBangDia.setText(IDHelper.newUUIDString());
		
		stageThemLoaiBangDia = new Stage();
		stageThemLoaiBangDia.setTitle("ThemLoaiBangDia");
		stageThemLoaiBangDia.setScene(new Scene(root));
		stageThemLoaiBangDia.setResizable(false);
		stageThemLoaiBangDia.showAndWait();
		tableViewLoaiBangDia.setItems(data());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("MaLoaiBangDia"));
		columnTenLoaiBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("TenLoaiNoiDung"));
		columnGiaTri.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
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
