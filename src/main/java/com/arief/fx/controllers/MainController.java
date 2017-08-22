package com.arief.fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arief.fx.configuration.AbstractFxController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Component
public class MainController extends AbstractFxController {

	
	@Autowired
	private FormController form;
	@Autowired
	private TableController table;
	
	
	@FXML
	private TextField fieldNama;
	@FXML
	private BorderPane borderMain;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	
	public void setCenter(Node newNode) {
		borderMain.setCenter(newNode);
	}
	
	public void stageReload(Stage st,Class<? extends AbstractFxController> c,String fxml) {
		Parent p = (Parent)((AbstractFxController)context.getBean(c)).initNodeForView(fxml);
	
		st.setScene(new Scene(p));
		st.show();
	}
	
	public void showForm(ActionEvent ev) {
		Stage st = (Stage) ((Node)ev.getSource()).getScene().getWindow();
		stageReload(st, FormController.class, "form.fxml");
	}
	
	public void showData(ActionEvent ev) {
		setCenter(table.initNodeForView("tabel.fxml"));
	}
	
}
