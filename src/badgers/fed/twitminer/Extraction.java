package badgers.fed.twitminer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Extraction {
	/**
	 * @param args
	 */
	static ArrayList<String> keywords = new ArrayList<String>();
	public static void main(String[] args) {
		byte[] semicolon = new String(";").getBytes();
		byte[] newline = new String("\n").getBytes();
		
		try {
		
		BufferedInputStream i = new BufferedInputStream(new FileInputStream(new File("./trends.csv")));
		BufferedWriter o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("trans"))));
		
		byte[] buf = new byte[1];
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
				byte[] TTbuffer = new byte[140];
				int j = 0;
				i.skip(1);
				i.read(buf);
				//Dans un TT
				while(!Arrays.equals(buf, semicolon) && !Arrays.equals(buf, newline)) {
					TTbuffer[j] = buf[0];
					++j;
					i.read(buf);
				}
				//Sauvegarde (si besoin) dans l'Arraylist, et écrit l'index de l'item dans le fichier transactions
				//System.out.println(new String(TTbuffer));
				o.write(save(new String(TTbuffer)) + " ");
			} while(!Arrays.equals(newline, buf));
			o.write('\n');
		}
		serializeKeywords();
		o.close();
//		Runtime r = new Runtime();
//		r.exec("./apriori ");
		
	   } catch (FileNotFoundException e) {
	       e.printStackTrace();
	   } catch (IOException e) {
		e.printStackTrace();
	}
	}
	private static void deserializeKeywords() {
		
		try {
			ObjectInputStream keywordsSave = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("./keywords"))));
			List<String> a = (List<String>) keywordsSave.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		keywords.add(TT);
		return rank;
			
	}
	private static void serializeKeywords()
	{
		try {
			ObjectOutputStream keywordsSave = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("./keywords"))));
			keywordsSave.writeObject(keywords);
			System.out.println(keywords.size());
			keywordsSave.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
