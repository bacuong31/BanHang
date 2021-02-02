package Controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Database.DatabaseManager;
import Global.GlobalParameter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
public class LoginSceneController {

    @FXML
    private TextField textFieldTaiKhoan;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button btnDangNhap;
    @FXML
    private Label labelThongBao;
    
    private final String ad = "admin";
    @FXML
    private Button btnKhachHangDangNhap;
    @FXML
    void onClick(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
		if (event.getSource() == btnDangNhap) {
			if (textFieldTaiKhoan.getText().equals(ad) && textFieldPassword.getText().equals(ad)) {
				getNVDangNhap();
				Stage stage = (Stage) btnDangNhap.getScene().getWindow();
				stage.close();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainScreen.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage mainScreenStage = new Stage();
				mainScreenStage.setScene(new Scene(root));
				mainScreenStage.setResizable(false);
				mainScreenStage.setTitle("BangDia");
				mainScreenStage.show();
			} else if (textFieldTaiKhoan.getText().isBlank() == false
					&& textFieldPassword.getText().isBlank() == false) {
				if(validateLogin()) {
					getNVDangNhap();
				}

			} else {
				labelThongBao.setText("Bạn chưa nhập tài khoản hoặc mật khẩu!");
			}
		} else if (event.getSource() == btnKhachHangDangNhap) {
			Stage stage = (Stage) btnDangNhap.getScene().getWindow();
			stage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Button btnNhapDia = (Button) fxmlLoader.getNamespace().get("btnNhapDia");
			Button btnThueDia = (Button) fxmlLoader.getNamespace().get("btnThueDia");
			Button btnTraDia = (Button) fxmlLoader.getNamespace().get("btnTraDia");
			Button btnQuanLy = (Button) fxmlLoader.getNamespace().get("btnQuanLy");
			Button btnBaoCao = (Button) fxmlLoader.getNamespace().get("btnBaoCao");
			btnNhapDia.setDisable(true);
			btnThueDia.setDisable(true);
			btnTraDia.setDisable(true);
			btnQuanLy.setDisable(true);
			btnBaoCao.setDisable(true);
			Stage mainScreenStage = new Stage();
			mainScreenStage.setScene(new Scene(root));
			mainScreenStage.setResizable(false);
			mainScreenStage.setTitle("BangDia");
			mainScreenStage.show();

		}

    }
	@FXML
    void onPressed(KeyEvent event) throws ClassNotFoundException, SQLException, IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			if (textFieldTaiKhoan.getText().equals(ad) && textFieldPassword.getText().equals(ad)) {
				getNVDangNhap();
				Stage stage = (Stage) btnDangNhap.getScene().getWindow();
				stage.close();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainScreen.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage mainScreenStage = new Stage();
				mainScreenStage.setScene(new Scene(root));
				mainScreenStage.setResizable(false);
				mainScreenStage.setTitle("BangDia");
				mainScreenStage.show();
		
			} else if (textFieldTaiKhoan.getText().isBlank() == false
					&& textFieldPassword.getText().isBlank() == false) {
				if(validateLogin()) {
					getNVDangNhap();
				}
			} else {
				labelThongBao.setText("Bạn chưa nhập tài khoản hoặc mật khẩu!");
			}
		}
    }
	private boolean validateLogin() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Connection conn = DatabaseManager.getInstance().getConnection();
		System.out.println("validate");
		String sqlQuery = "SELECT COUNT(*) FROM NHANVIEN WHERE Username = ? AND Password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, textFieldTaiKhoan.getText());
		pstmt.setString(2, textFieldPassword.getText());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getInt(1) == 1) {
				Stage stage = (Stage) btnDangNhap.getScene().getWindow();
				stage.close();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainScreen.fxml"));
				
				Parent root = (Parent) fxmlLoader.load();
				Button btnQuanLy = (Button) fxmlLoader.getNamespace().get("btnQuanLy");
				btnQuanLy.setDisable(true);
				Stage mainScreenStage = new Stage();
				mainScreenStage.setScene(new Scene(root));
				mainScreenStage.setResizable(false);
				mainScreenStage.setTitle("BangDia");
				mainScreenStage.show();
				return true;
			} else {
				labelThongBao.setText("Tài khoản hoặc mật khẩu sai!");
				return false;
			}
		}
		
	
		return false;
	}
	
	private void getNVDangNhap() throws ClassNotFoundException, SQLException {
		Connection conn = DatabaseManager.getInstance().getConnection();
		String sqlQuery = "SELECT MaNV, HoTen FROM NHANVIEN WHERE Username = ? AND Password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
		pstmt.setString(1, textFieldTaiKhoan.getText());
		pstmt.setString(2, textFieldPassword.getText());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			GlobalParameter.MaNhanVienDangNhap = rs.getString(1);
			GlobalParameter.TenNhanVienDangNhap = rs.getString(2);
		}
	}
    
    
}
