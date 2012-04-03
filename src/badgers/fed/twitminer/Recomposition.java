package badgers.fed.twitminer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class Recomposition {

	/**
	 * @param args
	 */
	static List<String> keywords;
	public static void main(String[] args) {
		
		deserializeKeywords();
		
		String item = new String();
		try {
			BufferedReader i = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./resultat"))));
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("assoc"))));
			char[] buf = new char[1];
			while (i.read(buf) != -1 && buf[0] != '\n');
			i.skip(1);
			while(i.read(buf) != -1)
			{
				while (buf[0]!= '(')
				{
					while (buf[0] != ' ')
					{
						item += buf[0];
						i.read(buf);
					}
					os.write(keywords.get(Integer.valueOf(item)) + " ");
					item = new String();
					i.read(buf);
				}
				os.write(' ');
				while(buf[0] != '\n')
				{
					os.write(buf);
					i.read(buf);
				}
				os.write('\n');
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void deserializeKeywords()
	{
		try {
			ObjectInputStream keywordsRead = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("./keywords"))));
			keywords = (List<String>) keywordsRead.readObject();
			System.out.println(keywords.size());
			keywordsRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
