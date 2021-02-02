package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Helper.DateHelper;
import Perform_Object.HoaDon_Perform;
import Process.ProcessHoaDon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class BaoCaoController implements Initializable {
	@FXML
	private TableView<HoaDon_Perform> tbViewThu;

	@FXML
	private TableColumn<HoaDon_Perform, String> columnThu_MaHoaDon;

	@FXML
	private TableColumn<HoaDon_Perform, LocalDate> columnThu_NgayLap;

	@FXML
	private TableColumn<HoaDon_Perform, String> columnThu_ThongTin;

	@FXML
	private TableColumn<HoaDon_Perform, Double> columnThu_GiaTri;

	@FXML
	private Label lbTongThuNhap;

	@FXML
	private TableView<HoaDon_Perform> tbViewChi;

	@FXML
	private TableColumn<HoaDon_Perform, String> columnChi_MaHoaDon;

	@FXML
	private TableColumn<HoaDon_Perform, LocalDate> columnChi_NgayLap;

	@FXML
	private TableColumn<HoaDon_Perform, String> columnChi_ThongTin;

	@FXML
	private TableColumn<HoaDon_Perform, Double> columnChi_GiaTri;

	@FXML
	private Label lbTongChiTieu;

	@FXML
	private DatePicker dtTuNgay;

	@FXML
	private DatePicker dtDenNgay;

	@FXML
	private Button btnXem;

	@FXML
	private Label lbTongDoanhThu;

	double TongChi = 0.0;
	double TongThu = 0.0;
	
	public double getTongChi() {
		return TongChi;
	}

	public void setTongChi(double tongChi) {
		TongChi = tongChi;
	}

	public void setTongThu(double tongThu) {
		TongThu = tongThu;
	}

	@FXML
	void onClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		tbViewChi.setItems(dataChi(ProcessHoaDon.executeQueryHoaDonChi(ProcessHoaDon.getQueryHoaDonChi(DateHelper.format(dtTuNgay.getValue()), DateHelper.format(dtDenNgay.getValue())))));
		tbViewThu.setItems(dataThu(ProcessHoaDon.executeQueryHoaDonThu(ProcessHoaDon.getQueryHoaDonThu(DateHelper.format(dtTuNgay.getValue()), DateHelper.format(dtDenNgay.getValue())))));
		lbTongDoanhThu.setText(String.valueOf(TongThu-TongChi));
	}

	@FXML
	void onKeyPressed(KeyEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		columnThu_MaHoaDon.setCellValueFactory(new PropertyValueFactory<>("MaHoaDon"));
		columnThu_NgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayLapHoaDon"));
		columnThu_GiaTri.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
		columnThu_ThongTin.setCellValueFactory(new PropertyValueFactory<>("Mota"));
		tbViewThu.setItems(dataThu());
		columnChi_MaHoaDon.setCellValueFactory(new PropertyValueFactory<>("MaHoaDon"));
		columnChi_NgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayLapHoaDon"));
		columnChi_GiaTri.setCellValueFactory(new PropertyValueFactory<>("GiaTri"));
		columnChi_ThongTin.setCellValueFactory(new PropertyValueFactory<>("Mota"));
		tbViewChi.setItems(dataChi());
		lbTongDoanhThu.setText(String.valueOf(TongThu-TongChi));
	}
	
	private double setTongChi() {
		return 0.0;
	}
	
	private double getTongThu() {
		return 0.0;
	}

	private ObservableList<HoaDon_Perform> dataChi() {
		ObservableList<HoaDon_Perform> dataHD = FXCollections.observableArrayList();
		ArrayList<HoaDon_Perform> arrHD = ProcessHoaDon.getAllHoaDonChi();
		double tongchi = 0.0;
		for (HoaDon_Perform hd : arrHD) {
			dataHD.add(hd);
			tongchi = tongchi + hd.getGiaTri();
		}
		setTongChi(tongchi);
		lbTongChiTieu.setText(String.valueOf(TongChi));
		return dataHD;
	}
	
	private ObservableList<HoaDon_Perform> dataChi(ArrayList<HoaDon_Perform> arrHD) {
		ObservableList<HoaDon_Perform> dataHD = FXCollections.observableArrayList();
		double tongchi = 0.0;
		for (HoaDon_Perform hd : arrHD) {
			dataHD.add(hd);
			tongchi = tongchi + hd.getGiaTri();
		}
		setTongChi(tongchi);
		lbTongChiTieu.setText(String.valueOf(TongChi));
		return dataHD;
	}
	private ObservableList<HoaDon_Perform> dataThu(ArrayList<HoaDon_Perform> arrHD) {
		ObservableList<HoaDon_Perform> dataHD = FXCollections.observableArrayList();
		double tongthu = 0.0;
		for (HoaDon_Perform hd : arrHD) {
			dataHD.add(hd);
			tongthu = tongthu + hd.getGiaTri();
		}
		setTongThu(tongthu);
		lbTongThuNhap.setText(String.valueOf(TongThu));
		return dataHD;
	}

	private ObservableList<HoaDon_Perform> dataThu() {
		ObservableList<HoaDon_Perform> dataHD = FXCollections.observableArrayList();
		ArrayList<HoaDon_Perform> arrHD = ProcessHoaDon.getAllHoaDonThu();
		double tongthu = 0.0;
		for (HoaDon_Perform hd : arrHD) {
			dataHD.add(hd);
			tongthu = tongthu + hd.getGiaTri();
		}
		setTongThu(tongthu);
		lbTongThuNhap.setText(String.valueOf(TongThu));
		return dataHD;
	}
}
