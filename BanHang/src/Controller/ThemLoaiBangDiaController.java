package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

import Process.ProcessLoaiBangDia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ThemLoaiBangDiaController {

	@FXML
	private Button btnThem;

	@FXML
	private TextField tfMaLoaiBangDia;

	@FXML
	private TextField tfGiaTri;

	@FXML
	private TextField tfTenLoaiBangDia;

	@FXML
	private ChoiceBox<String> choiceBoxNoiDung;

	@FXML
	private ImageView imageDia;

	@FXML
	private Button btnThayDoiHinh;

	private FileChooser fileChooser = new FileChooser();

	private File filePath;

	@FXML
	void onClick(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		if (event.getSource() == btnThayDoiHinh) {
			Stage stage = (Stage) btnThayDoiHinh.getScene().getWindow();
			loadThayDoiHinh(stage);
		} else if (event.getSource() == btnThem) {
			ProcessLoaiBangDia.insertLoaiBangDia(tfMaLoaiBangDia.getText(), tfTenLoaiBangDia.getText(),
					choiceBoxNoiDung.getSelectionModel().getSelectedItem(), Double.parseDouble(tfGiaTri.getText()));
			Stage stage = (Stage) btnThem.getScene().getWindow();
			stage.close();
		}
	}

	private void loadThayDoiHinh(Stage stage) throws IOException {
    	
    	fileChooser.setTitle("HinhAnhBangDia");
    	ExtensionFilter ex = new ExtensionFilter("Image Files", "*.png");
    	fileChooser.getExtensionFilters().add(ex);
    	filePath = fileChooser.showOpenDialog(stage);
    	String fromfile = filePath.toString();
    	Path newPathImage = ProcessLoaiBangDia.moveImageFile(fromfile,tfMaLoaiBangDia.getText());
    	imageDia.setImage(new Image(newPathImage.toUri().toString()));
    	
    }

}
