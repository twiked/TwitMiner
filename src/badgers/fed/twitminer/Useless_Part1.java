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

public class Useless_Part1 {
	private HashMap<List<Integer>, Integer> globale;
	private HashMap<List<Integer>, Integer> subs;
	private int count = 0;
	public static void main(String[] args) {
		new Useless_Part1();
	}
	
	
	public Useless_Part1() {
	try {
		BufferedReader i = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./trans_50.out.txt"))));
		BufferedWriter o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./dfs"))));
		
		//Hashmap contenant toutes les transactions, associées à leur fréquence
		globale = new HashMap<List<Integer>, Integer>();
		subs = new HashMap<List<Integer>, Integer>();
		i.readLine();
		
		while (true)
		{
			//Ligne du fichier courante
			String currMotifLineunparsed = i.readLine();
			
			//Si arrivée à la fin du fichier
			if(currMotifLineunparsed == null)
				break;
			//Ligne quasi-parsée
			String [] currMotifLine = currMotifLineunparsed.split(" ");
			
			//Lecture de la fréquence pour l'ensemble de la transaction
			int freq = Integer.parseInt((String) currMotifLine[currMotifLine.length - 1].subSequence(1, currMotifLine[currMotifLine.length - 1].length() -1));
			
			//Liste qui va contenir les items, à insérer dans la hashmap globale
			List<Integer> motifLineList = new ArrayList<Integer>();
			
			for(int j=0; j < currMotifLine.length - 1; j++)
			{
				motifLineList.add(Integer.parseInt(currMotifLine[j]));
			}
			//Ajout du mapping Transaction - Frequence associée
			globale.put(motifLineList, freq);
		}
//		for (List<Integer> ds : globale.keySet())
//			ofuck.write(ds.toString() + '\n');

		for (List<Integer> gh : globale.keySet())
		{
			ArrayList<Integer> zez = new ArrayList<Integer>(gh);
			for (Integer in : gh)
			{
				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(in);
				zez.removeAll(al);
				
				createSub(zez, globale.get(gh), al);
			}
				
		}
		System.out.println("Subsize : " + subs.size());
		for (List<Integer> gh : subs.keySet())
		{
			String li = new String();
			for(Integer trt : gh)
			{
				li += trt + " -- ";
			}
			li += globale.get(gh) + "\n";
			o.write(li);
		}
		System.out.println(count);
		
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	private void createSub(List<Integer> l, int freq, List<Integer> currentSet) {
		count++;
		subs.put(currentSet, freq);
		ArrayList<Integer> xd = new ArrayList<Integer>(l);
		for(Integer in : l) {
			ArrayList<Integer> lolz = new ArrayList<Integer> (currentSet);
			lolz.add(in);
			xd.removeAll(lolz);
				createSub(xd, freq, lolz);
		}
		
	}
}
