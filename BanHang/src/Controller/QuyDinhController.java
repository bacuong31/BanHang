package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.IDHelper;
import Perform_Object.HoaDon_Perform;
import Perform_Object.QuyDinh_Perform;
import Process.ProcessThamSo;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class QuyDinhController implements Initializable {

    @FXML
    private TableView<QuyDinh_Perform> tableViewQuyDinh;

    @FXML
    private TableColumn<QuyDinh_Perform, String> columnMaQD;

    @FXML
    private TableColumn<QuyDinh_Perform, String> columnTenQD;

    @FXML
    private TableColumn<QuyDinh_Perform, Double> columnGiaTri;

    @FXML
    private TableColumn<QuyDinh_Perform, String> columnChuThich;

    @FXML
    private TextField tfTenQuyDinh;

    @FXML
    private Button btnTimKiem;

    @FXML
    private Button btnChinhSua;
    
    private Stage stageChinhSua;

    @FXML
    void keyPressed(KeyEvent event) {

    }

    @FXML
    void onClick(ActionEvent event) throws ClassNotFoundException, IOException, SQLException {
		if (event.getSource() == btnChinhSua) {
			QuyDinh_Perform temp = tableViewQuyDinh.getSelectionModel().getSelectedItem();
			if (temp != null) {
				if (stageChinhSua == null) {
					loadChinhSua(temp);
				} else if (stageChinhSua.isShowing()) {
					stageChinhSua.toFront();
				} else {
					loadChinhSua(temp);
				}
			}
		}
	}

    private void loadChinhSua(QuyDinh_Perform temp) throws IOException, ClassNotFoundException, SQLException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ChinhSuaQuyDinh.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		TextField tfMaQuyDinh = (TextField)fxmlLoader.getNamespace().get("tfMaQuyDinh");
		TextField tfTenQuyDinh = (TextField)fxmlLoader.getNamespace().get("tfTenQuyDinh");
		TextField tfGiaTri = (TextField)fxmlLoader.getNamespace().get("tfGiaTri");
		TextField tfChuThich = (TextField)fxmlLoader.getNamespace().get("tfChuThich");
		tfMaQuyDinh.setText(temp.getMaQuyDinh());
		tfTenQuyDinh.setText(temp.getTenQuyDinh());
		tfGiaTri.setText(String.valueOf(temp.getGiaTri()));
		tfChuThich.setText(temp.getChuThich());
		stageChinhSua = new Stage();
		stageChinhSua.setTitle("ChinhSuaQuyDinh");
		stageChinhSua.setScene(new Scene(root));
		stageChinhSua.setResizable(false);
		stageChinhSua.showAndWait();
		tableViewQuyDinh.setItems(data());
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnMaQD.setCellValueFactory(new PropertyValueFactory<>("MaQuyDinh"));
		columnTenQD.setCellValueFactory(new PropertyValueFactory<>("TenQuyDinh"));
		columnGiaTri.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
		columnChuThich.setCellValueFactory(new PropertyValueFactory<>("ChuThich"));

		try {
			tableViewQuyDinh.setItems(data());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ObservableList<QuyDinh_Perform> data() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ObservableList<QuyDinh_Perform> data = FXCollections.observableArrayList();
		ArrayList<QuyDinh_Perform> arr = ProcessThamSo.getAll();
		data.addAll(arr);
		return data;
	}
}
