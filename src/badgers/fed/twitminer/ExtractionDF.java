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
	private List<Motif> glob;
	BufferedWriter o;
	BufferedReader i;
	private int minConf;

	public static void main(String[] args) {
		new ExtractionDF(0.8);
	}

	public ExtractionDF(double seuil) {
		try {
			o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					new File("./dfs"))));
			i = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File("./trans_50.out.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Hashmap contenant toutes les transactions, associées à leur fréquence
		glob = new ArrayList<Motif>();
		globale = new HashMap<Motif, Motif>();

		try {
			i.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			while (true) {
				// Ligne du fichier courante
				String motifLineunparsed = i.readLine();

				// Si arrivée à la fin du fichier
				if (motifLineunparsed == null)
					break;

				// Ligne découpée
				String[] currMotifLine = motifLineunparsed.split(" ");

				// Lecture de la fréquence pour l'ensemble de la transaction
				int freq = Integer
						.parseInt((String) currMotifLine[currMotifLine.length - 1]
								.subSequence(1,
										currMotifLine[currMotifLine.length - 1]
												.length() - 1));

				// Création de l'objet Motif avec sa fréquence
				Motif m = new Motif(freq);

				// Boucle de construction du motif fréquent
				for (int j = 0; j < currMotifLine.length - 1; j++) {
					m.addItem(Integer.parseInt(currMotifLine[j]));
				}
				glob.add(m);
			}
			final int globSize = glob.size();
			System.out.println(globSize);
			// Pour chaque motif Y fréquent
			for (int ai = globSize - 1; ai >= 0; --ai) {

				Motif y = new Motif(glob.get(ai));
				int bi = ai - 10;

				// Rechercher un sous-ensemble X
				while (bi >= 0) {
					// Un sous-motif potentiel
					Motif x = glob.get(bi);

					// Tel que X c Y
					if (y.containsAll(x)) {
						if (y.getFreq() / x.getFreq() >= seuil) {
							//System.out.println(y.toString() + "#" + x.toString());
							// Z = Y - X (motif impliqué)
							Motif z = new Motif(y);
							z.removeAll(x);
							System.out.println(x.getMotif().toString() + " implique " + z.getMotif().toString() + "(" + y.getFreq() / x.getFreq() + ")");
							
							globale.put(x, z);
						}
					}
					--bi;
				}
			}
			System.out.println(globale.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
