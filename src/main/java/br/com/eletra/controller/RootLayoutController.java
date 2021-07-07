package br.com.eletra.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.eletra.MainApp;
import br.com.eletra.dao.MeasurerJpaDAO;
import br.com.eletra.model.MeasurerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
	@FXML
	private Button btnReset;
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
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
	
	// Lista de todos as linhas, categorias e modelos de medidores
	List<MeasurerModel> listMeasurers = MeasurerJpaDAO.getInstance().findAll();
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
