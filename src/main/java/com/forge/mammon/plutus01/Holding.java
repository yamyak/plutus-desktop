package com.forge.mammon.plutus01;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Holding {
	
	private StringProperty name;
	
	private StringProperty symbol;
	
	private StringProperty shares;
	
	private StringProperty current_price;
	
	private StringProperty days_gain;
	
	private StringProperty days_percent_gain;
	
	private StringProperty days_total_gain;
	
	private StringProperty original_value;
	
	private StringProperty current_value;
	
	private StringProperty total_gain;
	
	private StringProperty total_percent_gain;
	
	Holding(String name, String symbol, String shares, String current_price, String days_gain, String days_percent_gain,
			String days_total_gain, String original_value, String current_value, String total_gain,
			String total_percent_gain)
	{
		this.name = new SimpleStringProperty(name);
		this.symbol = new SimpleStringProperty(symbol);
		this.shares = new SimpleStringProperty(shares);
		this.current_price = new SimpleStringProperty(current_price);
		this.days_gain = new SimpleStringProperty(days_gain);
		this.days_percent_gain = new SimpleStringProperty(days_percent_gain);
		this.days_total_gain = new SimpleStringProperty(days_total_gain);
		this.original_value = new SimpleStringProperty(original_value);
		this.current_value = new SimpleStringProperty(current_value);
		this.total_gain = new SimpleStringProperty(total_gain);
		this.total_percent_gain = new SimpleStringProperty(total_percent_gain);
	}
	
	public void setName(String name)
	{
		this.name.set(name);
	}
	
	public void setSymbol(String symbol)
	{
		this.symbol.set(symbol);
	}
	
	public void setShares(String shares)
	{
		this.shares.set(shares);
	}
	
	public void setCurrentPrice(String current_price)
	{
		this.current_price.set(current_price);
	}
	
	public void setDaysGain(String days_gain)
	{
		this.days_gain.set(days_gain);
	}
	
	public void setDaysPercentGain(String days_percent_gain)
	{
		this.days_percent_gain.set(days_percent_gain);
	}
	
	public void setDaysTotalGain(String days_total_gain)
	{
		this.days_total_gain.set(days_total_gain);
	}
	
	public void setOriginalValue(String original_value)
	{
		this.original_value.set(original_value);
	}
	
	public void setCurrentValue(String current_value)
	{
		this.current_value.set(current_value);
	}
	
	public void setTotalGain(String total_gain)
	{
		this.total_gain.set(total_gain);
	}
	
	public void setTotalPercentGain(String total_percent_gain)
	{
		this.total_percent_gain.set(total_percent_gain);
	}
	
	public String getName()
	{
		return name.get();
	}
	
	public String getSymbol()
	{
		return symbol.get();
	}
	
	public String getShares()
	{
		return shares.get();
	}
	
	public String getCurrentPrice()
	{
		return current_price.get();
	}
	
	public String getDaysGain()
	{
		return days_gain.get();
	}
	
	public String getDaysPercentGain()
	{
		return days_percent_gain.get();
	}
	
	public String getDaysTotalGain()
	{
		return days_total_gain.get();
	}
	
	public String getOriginalValue()
	{
		return original_value.get();
	}
	
	public String getCurrentValue()
	{
		return current_value.get();
	}
	
	public String getTotalGain()
	{
		return total_gain.get();
	}
	
	public String getTotalPercentGain()
	{
		return total_percent_gain.get();
	}
	
	public StringProperty getNameProperty()
	{
		return name;
	}
	
	public StringProperty getSymbolProperty()
	{
		return symbol;
	}
	
	public StringProperty getSharesProperty()
	{
		return shares;
	}
	
	public StringProperty getCurrentPriceProperty()
	{
		return current_price;
	}
	
	public StringProperty getDaysGainProperty()
	{
		return days_gain;
	}
	
	public StringProperty getDaysPercentGainProperty()
	{
		return days_percent_gain;
	}
	
	public StringProperty getDaysTotalGainProperty()
	{
		return days_total_gain;
	}
	
	public StringProperty getOriginalValueProperty()
	{
		return original_value;
	}
	
	public StringProperty getCurrentValueProperty()
	{
		return current_value;
	}
	
	public StringProperty getTotalGainProperty()
	{
		return total_gain;
	}
	
	public StringProperty getTotalPercentGainProperty()
	{
		return total_percent_gain;
	}
}
