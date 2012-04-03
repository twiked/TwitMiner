package badgers.fed.twitminer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import badgers.fed.twitminer.model.Motif;

public class Nettoyage {
	HashMap<Motif, Motif> dfs;
	
	//Dictionnaire à remplir
	HashMap<String, String> dic;
	
	ArrayList<String> keywords;

	public static void main(String[] args) {
		new Nettoyage();
	}

	public Nettoyage() {
		dfs = deSerializeDF();
		cleanSynonyms();
	}

	public void cleanSynonyms() {
		keywords = (ArrayList<String>) deSerializeKeywords();
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("./trends.syno")), "UTF-8"));
			dic = new HashMap<String, String>();
			String currLine = new String();
			currLine = is.readLine();
			while (true) {
				String[] s = currLine.split(String.valueOf('"'));
				dic.put(s[1], s[3]);
				currLine = is.readLine();
				if(currLine == null)
					break;
			}
			
			//Etablissement de la liste de keywords à changer
			HashMap<Integer, Integer> synos = new HashMap<Integer, Integer>();
			for(String s : dic.keySet()) {
				int rangKeyToChange = keywords.indexOf(s);
				if(rangKeyToChange != -1) {
//					keywords.remove(rangKeyToChange);
					int rangKeySyno = keywords.indexOf(dic.get(s));
					if (rangKeySyno != -1)
						synos.put(rangKeyToChange, rangKeySyno);
					else
						keywords.add(dic.get(s));
						synos.put(rangKeyToChange, dic.size() - 1);
				}
			}
			//Remplacement effectif des synonymes
			
			//Pour chaque DF
			for(Motif m : dfs.keySet()) {
				//Nettoyage des motifs implicateurs (X)
				
				List<Integer> l = m.getMotif();
				//Pour chaque mot-clé à remplacer potentiellement
				for(int i : synos.keySet()) {
					int rang = l.indexOf(i);
					//Le mot clé doit être remplacé					
					if(rang != -1) {
						System.out.println(keywords.get(i) + " remplacé par " + keywords.get(synos.get((Object)i)));
						l.set(rang, synos.get(i));
						//Suppression des autres occurences du mot-clé
						l.remove((Object)i);
					}
				}
					
				//Nettoyage des motifs impliqués (Y-X)
				
				List<Integer> ll = dfs.get(m).getMotif();
				//Pour chaque mot-clé à remplacer potentiellement
				for(int j : synos.keySet()) {
					int rangg = ll.indexOf(j);
					
					if(rangg != -1) {
						//Le mot clé doit être remplacé
						System.out.println(keywords.get(rangg) + " remplacé par " + keywords.get(synos.get((Object)j)));
						ll.set(rangg, synos.get(j));
						//Suppression des autres occurences du mot-clé
						ll.remove(ll);
					}
				}
			}
			System.out.println("Taille finale de keywords : " + keywords.size());
				
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	HashMap<Motif, Motif> deSerializeDF() {
		try {
			ObjectInputStream DFSave = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File(
							"./DFserialized"))));
			HashMap<Motif, Motif> h = (HashMap<Motif, Motif>) DFSave
					.readObject();
			DFSave.close();
			return h;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	List<String> deSerializeKeywords() {
		try {
			ObjectInputStream keywordsSave = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File(
							"./keywordsmap"))));
			List<String> l = (List<String>) keywordsSave.readObject();
			keywordsSave.close();
			System.out.println("Taille initiale de keywords : " + l.size());
			return l;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
