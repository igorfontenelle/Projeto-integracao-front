package br.com.eletra.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.eletra.MainApp;
import br.com.eletra.model.Ares;
import br.com.eletra.model.Cronos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class RootLayoutController {

	@FXML
	private ComboBox<String> cbLines;
	
	@FXML
	private TitledPane titledPaneLinhas;
	
	@FXML
	private TitledPane titledPaneModelos;
	
	@FXML
	private TreeView<String> treeViewLines;
	
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
	
	Ares ares;
	Cronos cronos;
	
	@FXML
	public void initialize() { 
		//Impede o usuario abrir o TitledPane Modelos
		titledPaneModelos.setDisable(true);
		setCbLines();
	}
	
	@FXML
	public void handleCbLines() {
		String s = cbLines.getSelectionModel().getSelectedItem().toString();
		titledPaneModelos.setDisable(false);
		titledPaneModelos.setContent(treeViewLines);
		
		if(s.equals(Cronos.CRONOS.getValor())) {
			setTreeViewLines(Cronos.CRONOS.toString());
		}
		else if(s.equals(Ares.ARES.getValor())) {
			setTreeViewLines(Ares.ARES.toString());
		}
		
	}
	
	//Responsável por popular os dados da ComboBox
	public void setCbLines() {
		ObservableList<String> list = FXCollections.observableArrayList(Cronos.CRONOS.getValor(), Ares.ARES.getValor());
		cbLines.setItems(list);
		
	}
	
	public void setTreeViewLines(String s) {
		
		TreeItem<String> rootItem = new TreeItem<>(s);
		
		if (s.equals(Ares.ARES.toString())) {
			TreeItem<String> categoryItem = new TreeItem<String>();
			
			for (Ares i: Ares.values()) {
				if (i.equals(Ares.ARES)) {
					continue;
				}
				if (i.equals(Ares.ARESTB)) {
					categoryItem = new TreeItem<>(i.getValor());
					continue;
				}
				if (i.equals(Ares.ARESTHS)) {
					rootItem.getChildren().add(categoryItem);
					categoryItem = new TreeItem<>(i.getValor());
					continue;
				}
				categoryItem.getChildren().add(new TreeItem<String>(i.getValor()));
				
			}
			rootItem.getChildren().add(categoryItem);
			treeViewLines.setRoot(rootItem);
			
		}
		
		if (s.equals(Cronos.CRONOS.getValor())) {
			TreeItem<String> categoryItem = new TreeItem<>();
			
			for (Cronos i: Cronos.values()) {
				if (i.equals(Cronos.CRONOS)) {
					continue;
				}
				if (i.equals(Cronos.CRONOSOLD)){
					categoryItem = new TreeItem<>(Cronos.CRONOSOLD.getValor());
					continue;
				}
				
				if (i.equals(Cronos.CRONOSL)) {
					rootItem.getChildren().add(categoryItem);
					categoryItem = new TreeItem<>(Cronos.CRONOSL.getValor());
					continue;
				}
				
				if (i.equals(Cronos.CRONOSNG)) {
					rootItem.getChildren().add(categoryItem);
					categoryItem = new TreeItem<>(Cronos.CRONOSNG.getValor());
					continue;
				}
				categoryItem.getChildren().add(new TreeItem<String>(i.getValor()));
			}
			rootItem.getChildren().add(categoryItem);
			treeViewLines.setRoot(rootItem);

		}
	}
}
