package com.forge.mammon.plutus01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.github.scribejava.core.model.Response;

public class PortfolioAlgorithm {
	
	private static final String ALLY_PROFILE = "https://api.tradeking.com/v1/member/profile.json";
	private static final String ALLY_ACCOUNTS = "https://api.tradeking.com/v1/accounts.json";
	private static final String ALLY_ACCOUNTS_2 = "/balances.json";
	private static final String RATE_LIMIT_USED = "X-RateLimit-Used";
	private static final int RATE_LIMIT_VALUE = 180; 
	
	private ObservableList<Holding> holdingList = FXCollections.observableArrayList();
	
	private DataLoader loader;
	
	private float overall_original;
	private float overall_day_change;
	private float overall_change;
	
	public PortfolioAlgorithm()
	{
		loader = new DataLoader();
	}

	public ObservableList<Holding> getHoldingData()
	{
		return holdingList;
	}
	
	public boolean checkForAccount()
    {
		String path = System.getProperty("user.home") + "\\.plconfig";
    	File accountFile = new File(path);
	    	
    	return accountFile.exists();
    }
	
	private OAuthKeys getKeys()
	{
		String path = System.getProperty("user.home") + "\\.plconfig";
		String name = System.getProperty("user.name");
		
		OAuthKeys keys = null;
		
		try 
		{
			String temp;
			String consumer_key;
			String consumer_secret;
			String oauth_token;
			String oauth_token_secret;
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((temp = br.readLine()) != null)
			{
				consumer_key = Crypter.decrypt(temp, name);
				consumer_secret = Crypter.decrypt(br.readLine(), name);
				oauth_token = Crypter.decrypt(br.readLine(), name);
				oauth_token_secret = Crypter.decrypt(br.readLine(), name);
				keys = new OAuthKeys(consumer_key, consumer_secret, oauth_token, oauth_token_secret);
			}
			
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return keys;
	}
	
	private void parseHoldingData(JsonObject obj)
	{
		String name = obj.getAsJsonObject("displaydata").get("desc").getAsString();
		String symbol = obj.getAsJsonObject("displaydata").get("symbol").getAsString();
		String qty = obj.getAsJsonObject("displaydata").get("qty").getAsString();
		String current_price = obj.getAsJsonObject("displaydata").get("lastprice").getAsString();
		
		float total_price_change = obj.get("marketvaluechange").getAsFloat();
		float price_change = total_price_change/obj.get("qty").getAsFloat();
		String price_change_string = "$" + String.format("%.02f", price_change);
		
		float beg_price = obj.get("price").getAsFloat() - price_change;
		float percent_change = 100 * (price_change / beg_price);
		String percent_change_string = String.format("%.02f", percent_change) + "%";
		
		String original_value = obj.getAsJsonObject("displaydata").get("costbasis").getAsString();
		String current_value = obj.getAsJsonObject("displaydata").get("marketvalue").getAsString();
		
		float gain = obj.get("gainloss").getAsFloat();
		String total_gain = "$" + String.format("%.02f", gain);
		
		float total_percent = 100 * (gain / obj.get("costbasis").getAsFloat());
		String total_percent_string = String.format("%.02f", total_percent) + "%";
		
		float origin_value = obj.get("costbasis").getAsFloat();
		updateOverallCounts(origin_value, total_price_change, gain);
		
		Holding h = new Holding(name, symbol, qty, current_price, price_change_string, percent_change_string,
								original_value, current_value, total_gain, total_percent_string);
		
		holdingList.add(h);
	}
	
	private void updateOverallCounts(float original, float day_change, float gain)
	{
		overall_original += original;
		overall_day_change += day_change;
		overall_change += gain;
	}
	
	private void parseAccountData(JsonArray arr)
	{
		for(int i = 0; i < arr.size(); i++)
		{
			parseHoldingData(arr.get(i).getAsJsonObject());
		}
		
		float current_price = overall_original + overall_change;
		String price_string = "$" + String.format("%.02f", current_price);
		String day_change_string = "$" + String.format("%.02f",overall_day_change);
		float day_percent = 100 * overall_day_change/(current_price - overall_day_change);
		String day_per_str = String.format("%.02f", day_percent) + "%";
		String original_str = "$" + String.format("%.02f", overall_original);
		String gain_string = "$" + String.format("%.02f", overall_change);
		float all_percent = 100 * overall_change / overall_original;
		String gain_percent_str = String.format("%.02f", all_percent) + "%";
		
		Holding h = new Holding("TOTAL", "-", "-", price_string, day_change_string, day_per_str,
				original_str, price_string, gain_string, gain_percent_str);
		
		holdingList.add(h);
	}
	
	public boolean loadPortfolioData()
	{
		boolean status = true;
		
		OAuthKeys keys = getKeys();
		
		loader.initLoader(keys.getConsumerKey(), 
				keys.getConsumerSecret(), 
				keys.getOAuthToken(), 
				keys.getOAuthTokenSecret());
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try 
		{
			//Response response = loader.get(ALLY_PROFILE);
			//int rate = Integer.parseInt(response.getHeader(RATE_LIMIT_USED));
			//JsonObject body = gson.fromJson(response.getBody(), JsonObject.class);
			//String account = body.getAsJsonObject("response").getAsJsonObject("userdata")
			//					 .getAsJsonObject("account").get("account").getAsString();
			
			//Response response2 = loader.get(ALLY_ACCOUNTS_1 + account + ALLY_ACCOUNTS_2);
			Response response2 = loader.get(ALLY_ACCOUNTS);
			JsonObject body2 = gson.fromJson(response2.getBody(), JsonObject.class);
			
			//File accountFile = new File("accounts.txt");
			//BufferedWriter bw = new BufferedWriter(new FileWriter(accountFile));
			//bw.write(gson.toJson(body2));
			//bw.close();
			
			parseAccountData(body2.getAsJsonObject("response").getAsJsonObject("accounts")
								.getAsJsonObject("accountsummary").getAsJsonObject("accountholdings")
								.getAsJsonArray("holding"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		return status;
	}
	
	public boolean setupAccount(OAuthKeys keys)
	{
		String path = System.getProperty("user.home") + "\\.plconfig";
		String name = System.getProperty("user.name");
		
		File accountFile = new File(path);
		
		try 
    	{
			if(!accountFile.createNewFile())
			{
				return false;
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			String encrypt_str = Crypter.encrypt(keys.getConsumerKey(), name);
			bw.write(encrypt_str);
			bw.newLine();
			encrypt_str = Crypter.encrypt(keys.getConsumerSecret(), name);
			bw.write(encrypt_str);
			bw.newLine();
			encrypt_str = Crypter.encrypt(keys.getOAuthToken(), name);
			bw.write(encrypt_str);
			bw.newLine();
			encrypt_str = Crypter.encrypt(keys.getOAuthTokenSecret(), name);
			bw.write(encrypt_str);
			bw.newLine();
			bw.close();
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
		
		return true;
	}
}
