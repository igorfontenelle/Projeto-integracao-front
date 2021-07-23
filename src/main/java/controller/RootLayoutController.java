package main.java.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.java.models.MeasurerModel;


public class RootLayoutController {

	@FXML
	public ComboBox<String> cbLines;
	
	@FXML
	public TitledPane titledPaneLinhas;
	
	@FXML
	public TitledPane titledPaneModelos;
	
	@FXML
	public TreeView<String> treeViewLines;
	
	@FXML
	public Button btnReset;
	
	@FXML
	public void initialize() { 
		//Impede o usuario abrir o TitledPane Modelos
		titledPaneModelos.setDisable(true);
		setCbLines();
	}
	
	@FXML
	public void handleCbLines() {
		titledPaneModelos.setDisable(false);
		titledPaneModelos.setContent(treeViewLines);
		setTreeViewLines();
		
	}
	
	ControllerApi apiController = new ControllerApi();
	// Lista de todos as linhas, categorias e modelos de medidores
	List<MeasurerModel> listMeasurers = apiController.apiGetListMeasurers();
	// Lista de todas as linhas dos medidores
	List<String> listLines = new ArrayList<>();
		
	//Responsavel por popular os dados da ComboBox
	public void setCbLines() {
		
		for (MeasurerModel model: listMeasurers) {
			if (!listLines.contains(model.getLine())) {
				listLines.add(model.getLine());
			}
			
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(listLines);
		cbLines.setItems(list);
		
	}
	
	public void setTreeViewLines() {
		String s = cbLines.getSelectionModel().getSelectedItem();
		List<String> listCategories = new ArrayList<>();
		TreeItem<String> rootItem = new TreeItem<>(s);
		
		for (MeasurerModel model: listMeasurers) {
			if (model.getLine().equals(s) && !listCategories.contains(model.getCategory())) {
				listCategories.add(model.getCategory());
			}
		}
		
		for (String category: listCategories) {
			TreeItem<String> categoryItem = new TreeItem<String>(category);
			
			for(MeasurerModel model: listMeasurers) {
				
				if (model.getCategory().equals(category)) {
					TreeItem<String> modelItem = new TreeItem<String>(model.getModel());
					categoryItem.getChildren().add(modelItem);
				}
			}
			
			rootItem.getChildren().add(categoryItem);
		}
		treeViewLines.setRoot(rootItem);

	}
	
	@FXML
	public void handleBtnReset() {
		titledPaneLinhas.setExpanded(false);
		titledPaneModelos.setExpanded(false);
		titledPaneModelos.setDisable(true);
		cbLines.getSelectionModel().clearSelection();
	}
}
