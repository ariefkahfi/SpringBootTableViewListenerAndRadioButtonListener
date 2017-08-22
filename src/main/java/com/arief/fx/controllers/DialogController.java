package com.arief.fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arief.fx.configuration.AbstractFxController;
import com.arief.fx.entity.Gender;
import com.arief.fx.entity.Pegawai;
import com.arief.fx.services.PegawaiRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

@Component
public class DialogController  extends AbstractFxController{

	@FXML
	private Label labelNama,labelGender;
	@FXML
	private TextArea labelId;
	
	
	@Autowired
	private PegawaiRepository repo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	public void setLabelText(Pegawai p) {
		
		Pegawai findOne = repo.findOne(p.getIdPegawai());
		
		String id = findOne.getIdPegawai();
		String nama = findOne.getNamaPegawai();
		String gender =(String)((Gender)findOne.getGender()).toString();
		
		labelId.setText(id);
		labelNama.setText(nama);
		labelGender.setText(gender);
		
		/*System.err.println("id : " + id);
		System.err.println("Nama : " + nama);
		System.err.println("Gender : " + gender);*/
		
	}

}
