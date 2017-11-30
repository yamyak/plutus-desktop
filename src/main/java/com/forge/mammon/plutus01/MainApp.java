package com.forge.mammon.plutus01;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application
{
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Plutus");
		
		initRootLoader();
		
		showPortfolio();
	}
	
	public void initRootLoader()
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("RootView.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void showPortfolio()
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("PortfolioView.fxml"));
			AnchorPane anchor = (AnchorPane) loader.load();
			
			rootLayout.setCenter(anchor);
			
			PortfolioController controller = loader.getController();
			controller.setup(this);
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() 
	{
        return primaryStage;
    }
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
