package fareApp.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

public class DistanceFinder {
	
	private String rString = new String();
	
	String getDistance(String orginCity, String destinationCity){

		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +orginCity + "+MN" +
				"&destinations=" + destinationCity +"+MN" + "&key=AIzaSyAYq2wYjHYGKCaSJoZJOYYcBux90_aLsq8";
		
		Window.alert(url);
		URL.encode(url);

		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
		 Request request = builder.sendRequest(null, new RequestCallback() {

		 @Override
		public void onError(Request request, Throwable exception) {
		 Window.alert("onError: Couldn't retrieve JSON");
		 }

		 @Override
		public void onResponseReceived(Request request, Response response) {
		 Window.alert(response.getText());
	     if (200 == response.getStatusCode()) {
		 String rt = response.getText();
		 rString = toFinishedString(rt); //sets testList to an testlist
		 } else {
		 Window.alert("Couldn't retrieve JSON (" + response.getStatusCode()
		 + ")");

		 }
		 }
		 });
		} catch (RequestException e) {
		 Window.alert("RequestException: Couldn't retrieve JSON");
		}

		 return rString;
	 }
		
	protected String toFinishedString(String rt){
		String finishedString = new String();
		String sAll = rt;
		
		JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll);
		JSONValue jTry = jA.get("rows");
		
		JSONObject jb = (JSONObject)JSONParser.parseLenient(sAll);
		jTry = jb.get("elements");
		
		JSONObject jd = (JSONObject)JSONParser.parseLenient(sAll);
		jTry = jd.get("distance");

		JSONObject jc = (JSONObject)JSONParser.parseLenient(jTry.toString());
		JSONValue value = jc.get("value");
		
		finishedString = value.toString();
	    
		Window.alert("me made it");
		
		return finishedString;
	}
}
