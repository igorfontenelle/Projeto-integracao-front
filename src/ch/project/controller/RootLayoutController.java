package ch.project.controller;

import ch.project.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
		this.mainApp = mainApp;
	}
	
	
	@FXML
	public void initialize() { 
		//Impede o usuário abrir o TitledPane Modelos
		titledPaneModelos.setDisable(true);
		setCbLines();
	}
	
	@FXML
	public void handleCbLines() {
		String s = cbLines.getSelectionModel().getSelectedItem().toString();
		titledPaneModelos.setDisable(false);
		titledPaneModelos.setContent(treeViewLines);
		
		if(s.equals("Cronos")) {
			setTreeViewLines("Cronos");
		}
		else if(s.equals("Ares")) {
			setTreeViewLines("Ares");
		}
		
	}
	
	//Responsável por popular os dados da ComboBox
	public void setCbLines() {
		ObservableList<String> list = FXCollections.observableArrayList("Cronos", "Ares");
		cbLines.setItems(list);
		
		
	}
	
	public void setTreeViewLines(String s) {
		
		TreeItem<String> rootItem = new TreeItem<>(s);
		if (s.equals("Ares")) {
			TreeItem<String> lineItem = new TreeItem<>("Ares TB");
			
			TreeItem<String> branchItem1 = new TreeItem<>("Ares TB 7021");
			TreeItem<String> branchItem2 = new TreeItem<>("Ares TB 7031");
			TreeItem<String> branchItem3 = new TreeItem<>("Ares TB 7023");
			lineItem.getChildren().addAll(branchItem1, branchItem2, branchItem3);
			rootItem.getChildren().add(lineItem);
			
			TreeItem<String> lineItem1 = new TreeItem<>("Ares THS");
			
			lineItem1.getChildren().add(new TreeItem<>("Ares THS 8023 15"));
			lineItem1.getChildren().add(new TreeItem<>("Ares THS 8023 200"));
			lineItem1.getChildren().add(new TreeItem<>("Ares THS 8023 2,5"));
			rootItem.getChildren().add(lineItem1);
			
			treeViewLines.setRoot(rootItem);
		}
		
		if (s.equals("Cronos")) {
			//Adicionando a Categoria Cronus Old com respectivos modelos
			TreeItem<String> categoryItem = new TreeItem<>("Cronus Old");
			
			TreeItem<String> branchItem1 = new TreeItem<>("Cronos 6001‑A");
			TreeItem<String> branchItem2 = new TreeItem<>("Cronos 6003");
			TreeItem<String> branchItem3 = new TreeItem<>("Cronos 7023");
			categoryItem.getChildren().addAll(branchItem1, branchItem2, branchItem3);
			rootItem.getChildren().add(categoryItem);
			
			//Adicionando a Categoria Cronus L com respectivos modelos
			TreeItem<String> categoryItem1 = new TreeItem<>("Cronos L");
			
			categoryItem1.getChildren().add(new TreeItem<>("Cronos 6021L"));
			categoryItem1.getChildren().add(new TreeItem<>("Cronos 6021L"));
			categoryItem1.getChildren().add(new TreeItem<>("Cronos 7023L"));
			rootItem.getChildren().add(categoryItem1);
			
			//Adicionando a categoria Cronus-NG com respectivos modelos;
			TreeItem<String> categoryItem2 = new TreeItem<>("Cronos-NG");
			
			categoryItem2.getChildren().add(new TreeItem<>("Cronos 6001-NG"));
			categoryItem2.getChildren().add(new TreeItem<>("Cronos 6003-NG"));
			categoryItem2.getChildren().add(new TreeItem<>("Cronos 6021-NG"));
			categoryItem2.getChildren().add(new TreeItem<>("Cronos 6031-NG"));
			categoryItem2.getChildren().add(new TreeItem<>("Cronos 7021-NG"));
			categoryItem2.getChildren().add(new TreeItem<>("Cronos 7023-NG"));
			rootItem.getChildren().add(categoryItem2);
			
			treeViewLines.setRoot(rootItem);
		}
	}

}
