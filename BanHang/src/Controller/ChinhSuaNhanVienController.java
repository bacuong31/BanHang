package Controller;

import java.sql.SQLException;

import Database.NhanVien_DAO;
import Helper.DateHelper;
import Object.NhanVien;
import Process.ProcessNhanVien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChinhSuaNhanVienController {

    @FXML
    private Button btnLuu;

    @FXML
    private TextField tfMaNV;

    @FXML
    private TextField tfTaiKhoan;

    @FXML
    private TextField tfMatKhau;

    @FXML
    private TextField tfHoTen;

    @FXML
    private DatePicker dtNgaySinh;
    
    private static NhanVien PreNhanVien;
    
    
    public NhanVien getPreNhanVien() {
		return PreNhanVien;
	}

	public static void setPreNhanVien(NhanVien preNhanVien) {
		PreNhanVien = preNhanVien;
	}

	@FXML
    private Button btnReset;

    @FXML
    private TextField tfSDT;

    @FXML
    void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		if (event.getSource() == btnLuu) {
			ProcessNhanVien.updateNhanVien(tfMaNV.getText(), tfHoTen.getText(), dtNgaySinh.getValue(),
					Integer.valueOf(tfSDT.getText()), tfTaiKhoan.getText(), tfMatKhau.getText());
			Stage stage = (Stage) btnLuu.getScene().getWindow();
			stage.close();
		} else if (event.getSource() == btnReset) {
			resetNhanVien(PreNhanVien);
		}
    }
    
    private void resetNhanVien(NhanVien preNhanVien) {
    	tfMaNV.setText(preNhanVien.getMaNV());
		tfTaiKhoan.setText(preNhanVien.getUsername());
		tfMatKhau.setText(preNhanVien.getPassword());
		tfHoTen.setText(preNhanVien.getHoTen());
		tfSDT.setText(String.valueOf(preNhanVien.getSdt()));
		dtNgaySinh.setValue(preNhanVien.getNgaySinh());
    }

}
