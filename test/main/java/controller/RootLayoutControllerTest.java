package main.java.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.powermock.api.mockito.PowerMockito;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeView;

public class RootLayoutControllerTest extends ApplicationTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	public RootLayoutController rlc;
	
	@Before
	public void initTest() {
		rlc = spy(RootLayoutController.class);
		
		rlc.cbLines = new ComboBox<String>();
		rlc.titledPaneLinhas = new TitledPane();
		rlc.titledPaneModelos = new TitledPane();
		rlc.treeViewLines = new TreeView<String>();
	}
	
	@After
	public void finishTest() {
		rlc = null;
	}
	
	@Test
	public void testInitialize01() {
		PowerMockito.doNothing().when(rlc).setCbLines();
		rlc.initialize();
		verify(rlc, times(1)).setCbLines();
		
	}

	@Test
	public void testInitialize02() {
		PowerMockito.doNothing().when(rlc).setCbLines();
		rlc.titledPaneModelos.setDisable(false);
		rlc.initialize();
		assertEquals(true, rlc.titledPaneModelos.isDisable());
		
	}
	
	@Test
	public void testHandleCbLines() {
		PowerMockito.doNothing().when(rlc).setTreeViewLines();
		rlc.handleCbLines();
		verify(rlc, times(1)).setTreeViewLines();
	}
	
	@Test
	public void testHandleBtnReset() {
		rlc.titledPaneLinhas.setExpanded(true);
		rlc.titledPaneModelos.setExpanded(true);
		rlc.titledPaneModelos.setDisable(false);
		
		rlc.setCbLines();
		rlc.cbLines.getSelectionModel().selectFirst();
		
		rlc.handleBtnReset();
		
		error.checkThat(rlc.titledPaneLinhas.isExpanded(), is(false));
		error.checkThat(rlc.titledPaneModelos.isExpanded(), is(false));
		error.checkThat(rlc.titledPaneModelos.isDisable(), is(true));
		error.checkThat(rlc.cbLines.getSelectionModel().isEmpty(), is(true));
	}
	
	@Test
	public void testHandleBtnReset01() {
		rlc.titledPaneLinhas.setExpanded(true);
		rlc.handleBtnReset();
		assertEquals(false, rlc.titledPaneLinhas.isExpanded());
	}
	
	@Test
	public void testHandleBtnReset02() {
		rlc.titledPaneModelos.setExpanded(true);
		rlc.handleBtnReset();
		assertEquals(false, rlc.titledPaneModelos.isExpanded());
	}
	
	@Test
	public void testHandleBtnReset03() {
		rlc.titledPaneModelos.setDisable(false);
		rlc.handleBtnReset();
		assertEquals(true, rlc.titledPaneModelos.isDisabled());
	}
	
	@Test
	public void testHandleBtnReset04() {
		rlc.setCbLines();
		rlc.cbLines.getSelectionModel().selectFirst();
		rlc.handleBtnReset();
		assertEquals(true, rlc.cbLines.getSelectionModel().isEmpty());
	}
	
	@Test
	public void testSetCbLines() {
		rlc.setCbLines();
		assertEquals(false, rlc.listLines.isEmpty());
	}
	
	@Test
	public void testSetCbLines1() {
		rlc.setCbLines();
		assertEquals(false, rlc.cbLines.getItems().isEmpty());
	}
	
	@Test
	public void testSetTreeViewLines() {
		rlc.cbLines.getSelectionModel().select("Ares");
		rlc.setTreeViewLines();
		assertEquals("Ares", rlc.treeViewLines.getRoot().getValue());
	}
	
	@Test
	public void testSetTreeViewLinesCat() {
		rlc.cbLines.getSelectionModel().select("Ares");
		rlc.setTreeViewLines();
		assertEquals("Ares TB", rlc.treeViewLines.getRoot().getChildren().get(0).getValue());
	}
	
	@Test
	public void testSetTreeViewLinesCat1() {
		rlc.cbLines.getSelectionModel().select("Ares");
		rlc.setTreeViewLines();
		assertEquals("Ares THS", rlc.treeViewLines.getRoot().getChildren().get(1).getValue());
	}
	
	@Test
	public void testSetTreeViewLinesMod() {
		rlc.cbLines.getSelectionModel().select("Ares");
		rlc.setTreeViewLines();
		assertEquals("ARES 7021", rlc.treeViewLines.getRoot().getChildren().get(0).getChildren().get(0).getValue());
	}
}
