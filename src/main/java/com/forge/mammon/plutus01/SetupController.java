package com.forge.mammon.plutus01;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetupController {
	
	@FXML
	private TextField consumer_key;
	
	@FXML
	private TextField consumer_secret;
	
	@FXML
	private TextField oauth_token;
	
	@FXML
	private TextField oauth_token_secret;
	
	@FXML
	private Button ok;
	
	@FXML
	private Button cancel;
	
	private MainApp mainApp;
	private Stage dialogStage;
	
	private boolean okClicked = false;
	private OAuthKeys key = null;
	
	public SetupController()
	{
		
	}
	
	private boolean InputValid()
	{
		boolean status = (consumer_key.getText().length() > 0);
		status &= (consumer_secret.getText().length() > 0);
		status &= (oauth_token.getText().length() > 0);
		status &= (oauth_token_secret.getText().length() > 0);
		
		return status;
	}
	
	@FXML
	private void onOK()
	{
		if(InputValid())
		{
			okClicked = true;
			key = new OAuthKeys(consumer_key.getText(), consumer_secret.getText(),
					oauth_token.getText(), oauth_token_secret.getText());
			dialogStage.close();
		}
	}
	
	@FXML
	private void onCancel()
	{
		dialogStage.close();
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}

	public void setDialogStage(Stage dialogStage) 
	{
        this.dialogStage = dialogStage;
    }
	
	public boolean isOkClicked() 
	{
        return okClicked;
    }
	
	public OAuthKeys getKeys() 
	{
        return key;
    }
}
