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
	private HashMap<Motif, Motif> globale;
	private List <Motif> glob;
	BufferedWriter o;
	BufferedReader i;
	private int minConf;
	
	public static void main(String[] args) {
		new ExtractionDF(0.3);
	}
	public ExtractionDF(double seuil) {
		try {
			o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./dfs"))));
			i = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./trans_50.out.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Hashmap contenant toutes les transactions, associées à leur fréquence
		glob = new ArrayList <Motif>();
		globale = new HashMap<Motif, Motif>();
		
		char[] buf = new char[1];
		try {
			System.out.println(i.readLine());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
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
			}
			final int globSize = glob.size();
			System.out.println("Glob Size :" +globSize);
			
			//Pour chaque motif fréquent
			for(int ai = 0; ai < glob.size(); ++ai) {
				Motif a = new Motif(glob.get(ai));
				int bi = ai;
				
				//Rechercher un sur-ensemble
				while(bi < globSize)
				{
					//Un sur-motif potentiel
					Motif c = glob.get(bi);
					//Bizzarrerie Java ...
					Motif d = null;
					
					if (c.containsAll(a)) {
						
						//Sur-ensemble trouvé, on le copie pour pouvoir supprimer l'implicateur
						 d = new Motif(c);
					
					
						//Suppression directe dans le sur-ensemble, car inutile
						//pour le calcul de la confiance
						d.removeAll(c);
						
						//Recherche de la fréquence de ce sous-sur-ensemble,
						//e contenant ce dernier
						
						try {
							Thread.sleep(300);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for(Motif e : glob.subList(0, bi)){
							if(e.equals(d)) {
								System.out.println(e.getFreq()/a.getFreq());
								if(e.getFreq()/a.getFreq() > minConf)
									globale.put(a,e);
	//								System.out.println(a.toString() + " implique " + e.toString());
								break;
							}			
						}
						
					}
					bi++;
				}
			}
					System.out.println(globale.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
		
