package com.forge.mammon.plutus01;

public class OAuthKeys {
	
	private String consumer_key;
	
	private String consumer_secret;
	
	private String oauth_token;
	
	private String oauth_token_secret;
	
	public OAuthKeys(String consumer_key,
			String consumer_secret,
			String oauth_token,
			String oauth_token_secret)
	{
		this.consumer_key = consumer_key;
		this.consumer_secret = consumer_secret;
		this.oauth_token = oauth_token;
		this.oauth_token_secret = oauth_token_secret;
	}
	
	public String getConsumerKey() 
	{
        return consumer_key;
    }
	
	public String getConsumerSecret() 
	{
        return consumer_secret;
    }
	
	public String getOAuthToken() 
	{
        return oauth_token;
    }
	
	public String getOAuthTokenSecret() 
	{
        return oauth_token_secret;
    }

}
