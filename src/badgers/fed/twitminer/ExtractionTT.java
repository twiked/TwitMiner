package badgers.fed.twitminer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ExtractionTT {
	/**
	 * @param args
	 */
	static ArrayList<String> keywords = new ArrayList<String>();
	public static void main(String[] args) {
		char[] semicolon = new String(";").toCharArray();
		char[] newline = new String("\n").toCharArray();
		
		try {
			BufferedReader i = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./bon2.csv")), "UTF-8"));
			BufferedWriter o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("trans"))));
			
			char[] buf = new char[1];
			int count = 0;
			//Dans le fichier
			while (i.read(buf) != -1)
			{
				//Dans une ligne
				while (count < 2) {
					//
					i.read(buf);
					if(Arrays.equals(semicolon, buf))
						count++;
				}
				count = 0;
				i.skip(1);
				// Dans la liste de TT
				do
				{
					i.skip(1);
					i.read(buf);
					String ttString = new String();
					//Dans un TT
					while(!Arrays.equals(buf, semicolon) && !Arrays.equals(buf, newline)) {
						ttString += (char) buf[0];
						i.read(buf);
					}
					//Sauvegarde (si besoin) dans l'Arraylist, et écrit l'index de l'item dans le fichier transactions
					//System.out.println(new String(TTbuffer));
					o.write(save(ttString) + " ");
				} while(!Arrays.equals(newline, buf));
				o.write('\n');
			}
			Serializer.serializeKeywords(keywords);
			o.close();
	//		Runtime r = new Runtime();
	//		r.exec("./apriori ");
			
	   } catch (FileNotFoundException e) {
	       e.printStackTrace();
	   } catch (IOException e) {
		e.printStackTrace();
	}
	}
	private static int save(String TT)
	{
		int rank = 0;
		for(String s : keywords)
		{
			if(s.equals(TT)) {
				break;
			}
			++rank;
		}
		if(rank == keywords.size())
			keywords.add(TT);
		return rank;
			
	}
}
