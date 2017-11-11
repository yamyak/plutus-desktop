package com.forge.mammon.plutus01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PortfolioAlgorithm {
	
	private ObservableList<Holding> holdingList = FXCollections.observableArrayList();
	
	public PortfolioAlgorithm()
	{
	}

	public ObservableList<Holding> getHoldingData()
	{
		return holdingList;
	}
}
