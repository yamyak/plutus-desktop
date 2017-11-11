package com.forge.mammon.plutus01;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PortfolioController {
	
	@FXML
	private TableView<Holding> table;
	
	@FXML 
	private TableColumn<Holding, String> nameColumn;
	
	@FXML 
	private TableColumn<Holding, String> symbolColumn;
	
	@FXML 
	private TableColumn<Holding, Float> sharesColumn;
	
	@FXML 
	private TableColumn<Holding, Float> priceColumn;
	
	@FXML 
	private TableColumn<Holding, Float> gainColumn;
	
	@FXML 
	private TableColumn<Holding, Float> percentGainColumn;
	
	@FXML 
	private TableColumn<Holding, Float> valueColumn;
	
	private MainApp mainApp;
	
	private PortfolioAlgorithm algo;
	
	public PortfolioController()
	{
		algo = new PortfolioAlgorithm();
	}
	
	@FXML
	private void initialize()
	{
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		symbolColumn.setCellValueFactory(cellData -> cellData.getValue().getSymbolProperty());
		sharesColumn.setCellValueFactory(cellData -> cellData.getValue().getSharesProperty().asObject());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
		gainColumn.setCellValueFactory(cellData -> cellData.getValue().getGainProperty().asObject());
		percentGainColumn.setCellValueFactory(cellData -> cellData.getValue().getPercentGainProperty().asObject());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueProperty().asObject());
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		
		table.setItems(algo.getHoldingData());
	}
	
	public void initAccount()
	{
		// load data using algorithm
	}

}
