package com.arief.fx.controllers;

import static org.mockito.Mockito.never;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arief.fx.configuration.AbstractFxController;
import com.arief.fx.entity.Gender;
import com.arief.fx.entity.Pegawai;
import com.arief.fx.services.PegawaiRepository;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

@Component
public class FormController extends AbstractFxController{

	
	@Autowired
	private MainController main;
	
	@FXML
	private TextField fieldNama;
	@FXML
	private Button bSimpan,bMenu;
	@FXML
	private RadioButton bMale,bFemale;
	
	@Autowired
	private PegawaiRepository repo;
	
	private ToggleGroup tg;
	
	private void setUpRadioButton(ToggleGroup tg) {
		bMale.setToggleGroup(tg);
		bFemale.setToggleGroup(tg);
	}
	
	private RadioButton rb; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 tg = new ToggleGroup();
	    
		 setUpRadioButton(tg);
		 
		 tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			    rb = (RadioButton)newValue; 
			}
		});
		 
		 
	}


	
	public void doSave(ActionEvent ev) {
		try {
			
			if(!fieldNama.getText().trim().equals("") || rb!=null) {
				Pegawai p = new Pegawai();
			   
				UUID uuid = UUID.randomUUID();
			   
				p.setIdPegawai(uuid.toString());
				p.setNamaPegawai(fieldNama.getText().trim());
				
			   	if(rb.getText().trim().equals("Male") || rb.getText().contains("male")) {
					p.setGender(Gender.Male);
				}else if(rb.getText().trim().equals("Female") || rb.getText().contains("female")) {
					p.setGender(Gender.Female);
				}else{
					// rb ==  null
				}
				
			   	
			   	repo.save(p);
			   	buatAlert("Penyimpanan Data berhasil", AlertType.CONFIRMATION);
			   	refreshFields();
			}
		}catch (Exception e) {
			buatAlert("Penyimpanan Data Gagal , " + " RadioButton belum dipilih", AlertType.ERROR);
			e.printStackTrace();
		}
	}
	
	public void doBack(ActionEvent ev) {
		Stage st  = (Stage)((Node)ev.getSource()).getScene().getWindow();
		main.stageReload(st, MainController.class, "main.fxml");
	}
	
	
	private void refreshFields() {
		fieldNama.setText("");
		tg.selectToggle(null);
	}
	
	private void buatAlert(String contentText,AlertType alertType) {
		Alert alert =new Alert(alertType);
		alert.setTitle("Spring Boot Fx Dialog");
		alert.setContentText(contentText);
		
		ButtonType ok = new ButtonType("Close", ButtonData.APPLY);
		alert.getButtonTypes().setAll(ok);
		alert.show();
	}
	
}
