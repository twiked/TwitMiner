package badgers.fed.twitminer;

import java.util.Date;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TTGetter {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Twitter twitter = new TwitterFactory().getInstance();
        AccessToken acc = new AccessToken("15770377-lxF00DWbbjaqr2TE0STRLA69NrInWWE19Iku8CEAj", "DTZd1igxzXKstsHIUZi9KD15LW1KQXrfp6lvVhpE");
        twitter.setOAuthAccessToken(acc);
        try{
        	Trends tr;
        	Date date;
        	String li = null;
        	ResponseList<Location> locations = twitter.getAvailableTrends();
        			
        	for (Location i : locations)
        	{
        		try{
        		tr = twitter.getLocationTrends(i.getWoeid());
        		date = tr.getAsOf();
        		Trend [] ttt = tr.getTrends();
        		li = date + "; ";
        		String cname = i.getCountryName();
        		if (cname == null)
        			//Fixme, il faut gérer le cas worldwide 
        			li += "Worldwide";
        		else
        			li+= cname;
        		
        		for (Trend t : ttt)
        		{
        			li += "; " + t.getName();
        		}
        		System.out.println(li);
        		} catch(TwitterException te)
        		{
        			te.printStackTrace();
        		}
        	}
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("TwitterException");
            System.exit(-1);
        }
    }
}
