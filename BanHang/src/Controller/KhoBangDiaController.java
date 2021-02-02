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

import Perform_Object.BangDia_Perform;
import Process.ProcessBangDia;
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

public class KhoBangDiaController implements Initializable{
	@FXML
    private TableView<BangDia_Perform> tbViewBangDia;

    @FXML
    private TableColumn<BangDia_Perform, String> columnMaBangDia;

    @FXML
    private TableColumn<BangDia_Perform, String> columnTenBangDia;

    @FXML
    private TableColumn<BangDia_Perform, String> columnNoiDung;

    @FXML
    private TableColumn<BangDia_Perform, String> columnGiaTien;

    @FXML
    private Button btnXemChiTiet;

    @FXML
    private Button btnXoa;

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


	
	private Stage stageChiTietBangDia;


    @FXML
    void keyPressed(KeyEvent event) throws ClassNotFoundException, SQLException {
    	if(event.getCode().equals(KeyCode.ENTER)) {
    		String noiDung = "";
			if(choiceBoxNoiDung.getSelectionModel().getSelectedItem() != null) {
				noiDung = choiceBoxNoiDung.getSelectionModel().getSelectedItem();
			};
			tbViewBangDia.setItems(data(ProcessBangDia.executeQuerySelectKhoBangDia(ProcessBangDia.getQuerySelectKhoBangDia(tfTenBangDia.getText(), noiDung, tfTienMin.getText(), tfTienMax.getText()))));
		
    	}
    }
	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnXemChiTiet) {
			BangDia_Perform bd = tbViewBangDia.getSelectionModel().getSelectedItem();
			if (bd != null) {
				if (stageChiTietBangDia == null) {
					loadChiTietBangDia(bd);
				} else if (stageChiTietBangDia.isShowing()) {
					stageChiTietBangDia.toFront();
				} else {
					loadChiTietBangDia(bd);
				}
			}
		} else if (event.getSource() == btnTimKiem) {
			String noiDung = "";
			if(choiceBoxNoiDung.getSelectionModel().getSelectedItem() != null) {
				noiDung = choiceBoxNoiDung.getSelectionModel().getSelectedItem();
			};
			tbViewBangDia.setItems(data(ProcessBangDia.executeQuerySelectKhoBangDia(ProcessBangDia.getQuerySelectKhoBangDia(tfTenBangDia.getText(), noiDung, tfTienMin.getText(), tfTienMax.getText()))));
		}
	}

	private ObservableList<BangDia_Perform> data(ArrayList<BangDia_Perform> arr) {
		ObservableList<BangDia_Perform> data = FXCollections.observableArrayList();
		data.addAll(arr);
		return data;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaBangDia.setCellValueFactory(new PropertyValueFactory<>("MaBangDia"));
		columnTenBangDia.setCellValueFactory(new PropertyValueFactory<>("TenLoaiBangDia"));
		columnNoiDung.setCellValueFactory(new PropertyValueFactory<>("TenLoaiNoiDung"));
		columnGiaTien.setCellValueFactory(new PropertyValueFactory<>("GiaTien"));
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

	private ObservableList<BangDia_Perform> data() throws ClassNotFoundException, SQLException {
		ObservableList<BangDia_Perform> data = FXCollections.observableArrayList();
		ArrayList<BangDia_Perform> arr = ProcessBangDia.getAll();
		data.addAll(arr);
		return data;
	}
	
	private void loadChiTietBangDia(BangDia_Perform bd) throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChiTietBangDia.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField textFieldMaBangDia = (TextField)fxmlLoader.getNamespace().get("textFieldMaBangDia");
		
		TextField textFieldTenBangDia = (TextField)fxmlLoader.getNamespace().get("textFieldTenBangDia");
		TextField textFieldNoiDung = (TextField)fxmlLoader.getNamespace().get("textFieldNoiDung");
		TextField textFieldGiaTien = (TextField)fxmlLoader.getNamespace().get("textFieldGiaTien");
		ImageView imageViewBangDia = (ImageView)fxmlLoader.getNamespace().get("imageViewBangDia");
		textFieldMaBangDia.setText(bd.getMaBangDia());
		textFieldTenBangDia.setText(bd.getTenLoaiBangDia());
		textFieldNoiDung.setText(bd.getTenLoaiNoiDung());
		textFieldGiaTien.setText(String.valueOf(bd.getGiaTien()));
		Path root_Images = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path destination = Paths.get(root_Images.toString(),"src", "Images","Poster", ProcessBangDia.getMaLoaiBangDia(bd.getMaBangDia()) + ".png");
		if (Files.exists(destination)) {
			Image temp = new Image(destination.toUri().toString());
			imageViewBangDia.setImage(temp);
		}
		
		
		stageChiTietBangDia = new Stage();
		stageChiTietBangDia.setTitle("ChiTietBangDia");
		stageChiTietBangDia.setScene(new Scene(root));
		stageChiTietBangDia.setResizable(false);
		stageChiTietBangDia.show();
	}
}
