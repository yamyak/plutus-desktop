package com.forge.mammon.plutus01;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Holding {
	
	private StringProperty name;
	
	private StringProperty symbol;
	
	private FloatProperty shares;
	
	private FloatProperty price;
	
	private FloatProperty gain;
	
	private FloatProperty percent_gain;
	
	private FloatProperty value;
	
	Holding(String name, String symbol, float shares, float price, float gain, float percent_gain, float value)
	{
		this.name = new SimpleStringProperty(name);
		this.symbol = new SimpleStringProperty(symbol);
		this.shares = new SimpleFloatProperty(shares);
		this.price = new SimpleFloatProperty(price);
		this.gain = new SimpleFloatProperty(gain);
		this.percent_gain = new SimpleFloatProperty(percent_gain);
		this.value = new SimpleFloatProperty(value);
	}
	
	public void setName(String name)
	{
		this.name.set(name);
	}
	
	public void setSymbol(String symbol)
	{
		this.symbol.set(symbol);
	}
	
	public void setShares(float shares)
	{
		this.shares.set(shares);
	}
	
	public void setPrice(float price)
	{
		this.price.set(price);
	}
	
	public void setGain(float gain)
	{
		this.gain.set(gain);
	}
	
	public void setPercentGain(float gain)
	{
		this.percent_gain.set(gain);
	}
	
	public void setValue(float value)
	{
		this.value.set(value);
	}
	
	public String getName()
	{
		return name.get();
	}
	
	public String getSymbol()
	{
		return symbol.get();
	}
	
	public Float getShares()
	{
		return shares.get();
	}
	
	public Float getPrice()
	{
		return price.get();
	}
	
	public Float getGain()
	{
		return gain.get();
	}
	
	public Float getPercentGain()
	{
		return percent_gain.get();
	}
	
	public Float getValue()
	{
		return value.get();
	}
	
	public StringProperty getNameProperty()
	{
		return name;
	}
	
	public StringProperty getSymbolProperty()
	{
		return symbol;
	}
	
	public FloatProperty getSharesProperty()
	{
		return shares;
	}
	
	public FloatProperty getPriceProperty()
	{
		return price;
	}
	
	public FloatProperty getGainProperty()
	{
		return gain;
	}
	
	public FloatProperty getPercentGainProperty()
	{
		return percent_gain;
	}
	
	public FloatProperty getValueProperty()
	{
		return value;
	}
}
