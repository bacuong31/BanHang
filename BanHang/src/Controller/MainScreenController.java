package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainScreenController {

	@FXML
	private Button btnQuanLy;

	@FXML
	private Button btnNhapDia;

	@FXML
	private Button btnThueDia;

	@FXML
	private Button btnTraDia;

	@FXML
	private Button btnKhoBangDia;

	@FXML
	private Button btnBaoCao;

	@FXML
	private AnchorPane acpLayout;

	@FXML
	Node KhoBangDiaPane;
	@FXML
	Node QuanLyPane;
	@FXML
	Node ThueDiaPane;
	@FXML
	Node TraDiaPane;
	@FXML
	Node NhapDiaPane;
	@FXML
	Node BaoCaoPane;

	@FXML
	void initialize() throws IOException {
		QuanLyPane = (Node) FXMLLoader.load(getClass().getResource("/FXML/QuanLy.fxml"));
		KhoBangDiaPane = (Node) FXMLLoader.load(getClass().getResource("/FXML/KhoBangDia.fxml"));
		ThueDiaPane = (Node) FXMLLoader.load(getClass().getResource("/FXML/ThueDia.fxml"));
		TraDiaPane = (Node) FXMLLoader.load(getClass().getResource("/FXML/TraDia.fxml"));
		NhapDiaPane = (Node) FXMLLoader.load(getClass().getResource("/FXML/NhapDia.fxml"));
		BaoCaoPane = (Node) FXMLLoader.load(getClass().getResource("/FXML/BaoCao.fxml"));
	}

	@FXML
	private void handleClicks(ActionEvent event) {
		if (event.getSource() == btnNhapDia) {
			acpLayout.getChildren().setAll(NhapDiaPane);
		} else if (event.getSource() == btnThueDia) {
			acpLayout.getChildren().setAll(ThueDiaPane);
		} else if (event.getSource() == btnTraDia) {
			acpLayout.getChildren().setAll(TraDiaPane);
		} else if (event.getSource() == btnKhoBangDia) {
			acpLayout.getChildren().setAll(KhoBangDiaPane);
		} else if (event.getSource() == btnQuanLy) {
			acpLayout.getChildren().setAll(QuanLyPane);
		} else if (event.getSource() == btnBaoCao) {
			acpLayout.getChildren().setAll(BaoCaoPane);
		}
	}

}
