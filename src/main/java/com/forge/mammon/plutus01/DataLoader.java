package com.forge.mammon.plutus01;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class DataLoader {
	
	private OAuth10aService service;
	
	private OAuth1AccessToken accessToken;
	
	public DataLoader()
	{
	}
	
	public DataLoader(String consumer_key, String consumer_secret, String oauth_token, String oauth_token_secret)
	{
		initLoader(consumer_key, consumer_secret, oauth_token, oauth_token_secret);
	}
	
	public void initLoader(String consumer_key, String consumer_secret, String oauth_token, String oauth_token_secret)
	{
		if(service == null)
		{
			service = new ServiceBuilder(consumer_key)
        		.apiSecret(consumer_secret)
        		.build(TradeKingApi.instance());
			accessToken = new OAuth1AccessToken(oauth_token, oauth_token_secret);
		}
	}

	public Response get(String request_url)
	{
		Response response = null;
		
		OAuthRequest request = new OAuthRequest(Verb.GET, request_url);
		service.signRequest(accessToken, request);
		
		try
		{
			response = service.execute(request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response = null;
		}
		
		return response;
	}
}
