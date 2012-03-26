package badgers.fed.twitminer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
        Twitter twitter = new TwitterFactory().getInstance();
        AccessToken acc = new AccessToken("15770377-lxF00DWbbjaqr2TE0STRLA69NrInWWE19Iku8CEAj", "DTZd1igxzXKstsHIUZi9KD15LW1KQXrfp6lvVhpE");
        twitter.setOAuthAccessToken(acc);
        BufferedOutputStream fos = null;
        try {
			fos = new BufferedOutputStream(new FileOutputStream(new File("trends.xml"), true));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        while (true)
        {
        try{
        	Trends tr;
        	Date date = new Date();
        	String li = null;
        	ResponseList<Location> locations = twitter.getAvailableTrends();
        			
        	ArrayList<Location> wantedlocations= new ArrayList<Location>();
        	
        	int listwanted[] = {
        			1,			23424748, 23424768, 23424775, 23424803, 23424829, 
        			23424853, 	23424975, 23424977, 23424969, 23424909, 23424800,
        			23424819, 	23424782, 23424942, 23424934, 23424848, 23424747,
        			23424908, 	23424954
        	};
        	
//        	int listwanted[] = {1};
        	
            for (Location l : locations)
            {
            	int i=0;
            	while(i<20 && l.getWoeid() != listwanted[i])
            		++i;
            	if(i<20)
            		wantedlocations.add(l);
            }
            
            
            try {
    			fos = new BufferedOutputStream(new FileOutputStream(new File("trends.xml"), true));
    		} catch (FileNotFoundException e1) {
    			e1.printStackTrace();
    		}
            
        	for (Location i : wantedlocations)
        	{
        		try{
        		tr = twitter.getLocationTrends(i.getWoeid());
        		Trend [] ttt = tr.getTrends();
        		li = date + "; " + i.getName();
        		
        		for (Trend t : ttt)
        		{
        			li += "; " + t.getName();
        		}
        		li += '\n';
        		try {
					fos.write(li.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
        		} catch(TwitterException te)
        		{
        			te.printStackTrace();
        		}
        	}
        	
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("TwitterException");
            System.exit(-1);
        }
        fos.close();
        Thread.sleep(300000);
    }
	}
}
