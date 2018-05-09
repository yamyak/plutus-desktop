package com.forge.mammon.plutus01;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PortfolioController {
	
	private static final Color GOOD = Color.GREEN;
	private static final Color BAD = Color.ORANGERED;
	
	@FXML
	private TableView<Holding> table;
	
	@FXML 
	private TableColumn<Holding, String> nameColumn;
	
	@FXML 
	private TableColumn<Holding, String> symbolColumn;
	
	@FXML 
	private TableColumn<Holding, String> sharesColumn;
	
	@FXML 
	private TableColumn<Holding, String> currentPriceColumn;
	
	@FXML 
	private TableColumn<Holding, String> daysGainColumn;
	
	@FXML 
	private TableColumn<Holding, String> daysPercentColumn;
	
	@FXML
	private TableColumn<Holding, String> daysTotalGainColumn;
	
	@FXML 
	private TableColumn<Holding, String> originalValueColumn;
	
	@FXML 
	private TableColumn<Holding, String> currentValueColumn;
	
	@FXML 
	private TableColumn<Holding, String> totalGainColumn;
	
	@FXML 
	private TableColumn<Holding, String> totalPercentColumn;
	
	@FXML
	private Button refresh;
	
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
		sharesColumn.setCellValueFactory(cellData -> cellData.getValue().getSharesProperty());
		currentPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentPriceProperty());
		daysGainColumn.setCellValueFactory(cellData -> cellData.getValue().getDaysGainProperty());
		daysPercentColumn.setCellValueFactory(cellData -> cellData.getValue().getDaysPercentGainProperty());
		daysTotalGainColumn.setCellValueFactory(cellData -> cellData.getValue().getDaysTotalGainProperty());
		originalValueColumn.setCellValueFactory(cellData -> cellData.getValue().getOriginalValueProperty());
		currentValueColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentValueProperty());
		totalGainColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalGainProperty());
		totalPercentColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalPercentGainProperty());
		
    // For all gain/loss associated columns, add functionality to change
    // text color to red or green based on whether the the data represents
    // a gain or a loss
    
		daysGainColumn.setCellFactory(column -> 
		{
			return new TableCell<Holding, String>() 
			{
				@Override
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);
					
					if(item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						setText(item);
						
						if(item.indexOf("-") == -1)
						{
							setTextFill(GOOD);
						}
						else if(item.indexOf("-") != 0)
						{
							setTextFill(BAD);
						}
					}
				}
			};
		});
		
		daysPercentColumn.setCellFactory(column -> 
		{
			return new TableCell<Holding, String>() 
			{
				@Override
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);
					
					if(item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						setText(item);
						
						if(item.indexOf("-") != -1)
						{
							setTextFill(BAD);
						}
						else
						{
							setTextFill(GOOD);
						}
					}
				}
			};
		});
		
		daysTotalGainColumn.setCellFactory(column -> 
		{
			return new TableCell<Holding, String>() 
			{
				@Override
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);
					
					if(item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						setText(item);
						
						if(item.indexOf("-") != -1)
						{
							setTextFill(BAD);
						}
						else
						{
							setTextFill(GOOD);
						}
					}
				}
			};
		});
		
		totalGainColumn.setCellFactory(column -> 
		{
			return new TableCell<Holding, String>() 
			{
				@Override
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);
					
					if(item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						setText(item);
						
						if(item.indexOf("-") != -1)
						{
							setTextFill(BAD);
						}
						else
						{
							setTextFill(GOOD);
						}
					}
				}
			};
		});
		
		totalPercentColumn.setCellFactory(column -> 
		{
			return new TableCell<Holding, String>() 
			{
				@Override
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);
					
					if(item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						setText(item);
						
						if(item.indexOf("-") != -1)
						{
							setTextFill(BAD);
						}
						else
						{
							setTextFill(GOOD);
						}
					}
				}
			};
		});
	}
	
  
  // Display the account setup dialog
	private boolean displaySetup()
	{
		boolean status = false;
		
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("SetupView.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        Stage dialogStage = new Stage();
            dialogStage.setTitle("Setup Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            SetupController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();
            
            // retrieve the OAuth key data when entered
            status = controller.isOkClicked();
            status = (status && algo.setupAccount(controller.getKeys()));
            
            if(!status)
            {
            	Stage stage = (Stage) table.getScene().getWindow();
              stage.close();
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}
	
	public void setup(MainApp mainApp)
	{
		this.mainApp = mainApp;
		
		boolean status = algo.checkForAccount();
		
		if(!status)
		{
			status = displaySetup();
		}
		
		if(status)
		{
			algo.loadPortfolioData();
		
			table.setItems(algo.getHoldingData());
		}
	}
	
	@FXML
	public void onRefresh()
	{
		table.getItems().clear();
		
		algo.refreshPortfolioData();
	}
}
