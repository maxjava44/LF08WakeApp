package de.wakeapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import org.json.JSONObject;

public class Utility {
	
	
	public static int getWegzeit(String wp0,String wp1,boolean transit,Calendar cal) throws IOException {
		int stunde = cal.get(Calendar.HOUR_OF_DAY);
		if(stunde > 12) {
			stunde -= 12;
		}
		boolean pm = cal.get(Calendar.HOUR_OF_DAY) > 12;
		String timeString = "" + stunde + ":" + cal.get(Calendar.MINUTE) + ":" + "00" + (pm ? "PM" : "AM"); 
		System.out.println(timeString);
		String urlstring = "";
		if (transit) {
			urlstring = "http://dev.virtualearth.net/REST/V1/Routes/Transit?wp.0=" + URLEncoder.encode(wp0,"UTF-8") + "&wp.1=" + URLEncoder.encode(wp1,"UTF-8") + "&timeType=Arrival&dateTime=" + timeString + "&key=AgNL1JaFIMsuje7azGx4HwxWEIQatBr1SEFi1TTL9MoITbMW9s0-BU615wdv9hC4";
		}else {
			urlstring = "http://dev.virtualearth.net/REST/V1/Routes/Driving?wp.0=" + URLEncoder.encode(wp0,"UTF-8") + "&wp.1=" + URLEncoder.encode(wp1,"UTF-8") + "&timeType=Arrival&dateTime=" + timeString +"&key=AgNL1JaFIMsuje7azGx4HwxWEIQatBr1SEFi1TTL9MoITbMW9s0-BU615wdv9hC4";
		}
		URL obj = new URL(urlstring);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            JSONObject jo = new JSONObject(response.toString());
    		return ((JSONObject) ((JSONObject) jo.getJSONArray("resourceSets").get(0)).getJSONArray("resources").get(0)).getInt("travelDuration");
        } else if(responseCode == HttpURLConnection.HTTP_NOT_FOUND){
        	new Fehler("Die angegebenen Adressen konnten nicht gefunden werden");
        } else {
        	new Fehler("Ein unbekannter Fehler ist aufgetreten");
        }
		return 0;
	}
}
