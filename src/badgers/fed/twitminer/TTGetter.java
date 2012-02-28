package badgers.fed.twitminer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TTGetter {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
        	Twitter twitter = new TwitterFactory().getInstance();
        	
        	ResponseList<Location> locations = twitter.getAvailableTrends();
        	Trends wwtrends =  twitter.getLocationTrends(1);
        	Trend [] ttt = wwtrends.getTrends();
        	Date date = wwtrends.getAsOf();
        	String li = date.toString() + "; WorldWide";
        	for (Trend i : ttt)
        	{
        		li += "; " + i.getName();
        	}
        	System.out.println(li);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("TwitterException");
            System.exit(-1);
        }
    }
}
