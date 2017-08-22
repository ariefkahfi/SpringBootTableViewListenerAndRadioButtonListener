package com.arief.fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

@Component
public class TableController extends AbstractFxController{

	
	@Autowired
	private PegawaiRepository repo;
	
	@FXML
	private TableView<Pegawai> tableView;
	@FXML
	private TableColumn<Pegawai, String> columnId;
	@FXML
	private TableColumn<Pegawai, String> columnNama;
	@FXML
	private TableColumn<Pegawai, Gender> columnGender;
	
	
	private ContextMenu buildContextMenu(TableView tv) {
		ContextMenu contextMenu = new ContextMenu();
		
		MenuItem close = new MenuItem("deselect");
		close.setOnAction(e->{
			tv.getSelectionModel().clearSelection();
		});
		
		contextMenu.getItems().add(close);
		return contextMenu;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUpColumns();
		refreshData();
		
		ContextMenu cm = buildContextMenu(tableView);
		
		tableView.setContextMenu(cm);

		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pegawai>() {

			@Override
			public void changed(ObservableValue<? extends Pegawai> observable, Pegawai oldValue, Pegawai newValue)
			{
				
				int idx =tableView.getSelectionModel().getSelectedIndex();
				if(newValue!=null) {
					buatAlert(newValue);
				}
			}
		});
	}
	
	private void setUpColumns() {
		columnId.setCellValueFactory(new PropertyValueFactory<>("idPegawai"));
		columnNama.setCellValueFactory(new PropertyValueFactory<>("namaPegawai"));
		columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
	}


	
	private void buatAlert(Pegawai p) {
		Dialog<ButtonType> alert = new Dialog<ButtonType>();
		alert.setTitle("Pegawai Detail");
		
		AbstractFxController abs = (AbstractFxController)context.getBean(DialogController.class);
		
		DialogController dialog = (DialogController)abs;
		
		GridPane grid = new GridPane();
		grid.add(new Label("Hello"), 0, 0);
		grid.add(new Label("World"), 1, 0);
		
		alert.getDialogPane().setContent(dialog.initNodeForView("dialog.fxml"));
	
		dialog.setLabelText(p);
		
		ButtonType ok = new ButtonType("Close", ButtonData.APPLY);
		
		alert.getDialogPane().getButtonTypes().setAll(ok);
		
		alert.show();
	}
	
	private void refreshData() {
		tableView.getItems().clear();
		tableView.getItems().addAll(repo.findAll());
	}

	public void doRefresh(ActionEvent ev) {
		refreshData();
	}
	

}
