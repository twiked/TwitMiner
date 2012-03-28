package badgers.fed.twitminer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import badgers.fed.twitminer.model.Motif;


public class ExtractionDF {
	private HashMap<List<Integer>, Integer> globale;
	private List <Motif> glob;
	BufferedWriter o;
	BufferedReader i;
	private int minConf;
	
	public static void main(String[] args) {
		new ExtractionDF(3);
	}
	public ExtractionDF(int seuil) {
		try {
			BufferedWriter o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./dfs"))));
			BufferedReader i = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./trans_50.out.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Hashmap contenant toutes les transactions, associées à leur fréquence
		globale = new HashMap<List<Integer>, Integer>();
		char[] buf = new char[1];
		try {
		i.readLine();
		
		while (true)
		{
			//Ligne du fichier courante
			String motifLineunparsed = i.readLine();
			
			//Si arrivée à la fin du fichier
			if(motifLineunparsed == null)
				break;
			
			//Ligne découpée
			String [] currMotifLine = motifLineunparsed.split(" ");
			
			//Lecture de la fréquence pour l'ensemble de la transaction
			int freq = Integer.parseInt((String) currMotifLine[currMotifLine.length - 1].subSequence(1, currMotifLine[currMotifLine.length - 1].length() -1));
			
			//Création de l'objet Motif avec sa fréquence
			Motif m = new Motif(freq);
			
			//Boucle de construction du motif fréquent
			for(int j=0; j < currMotifLine.length - 1; j++)
			{
				m.addItem(Integer.parseInt(currMotifLine[j]));
			}
			
			glob.add(m);
			
			//Comparaison des sur-ensembles
			for(Motif a : glob) {
				for(Motif b : glob)
					if (b.containsAll(a))
						if(b.getFreq()/a.getFreq() > minConf)
							
			}
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
		
