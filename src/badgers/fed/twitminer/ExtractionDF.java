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
import java.util.List;

import badgers.fed.twitminer.model.Motif;

public class ExtractionDF {
	private ArrayList<List<Motif>> globale;
	private List<Motif> glob;
	BufferedWriter o;
	BufferedReader i;
	private double minConf;

	public static void main(String[] args) {
		new ExtractionDF(0.6);
	}

	public ExtractionDF(double seuil) {
		try {
			this.minConf = seuil;
			o = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					new File("./dfs"))));
			i = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File("./resultat"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Hashmap contenant toutes les transactions, associées à leur fréquence
		glob = new ArrayList<Motif>();
		globale = new ArrayList<List<Motif>>();

		try {
			i.readLine();
			
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
			i.close();

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
						if (y.getFreq() / x.getFreq() >= minConf) {
							// System.out.println(y.toString() + "#" +
							// x.toString());
							// Z = Y - X (motif impliqué), attention la
							// fréquence de z est donc la fréquence de y.
							Motif z = new Motif(y);
							z.removeAll(x);
							List<Motif> al = new ArrayList<Motif>();
							al.add(x);
							al.add(z);
							globale.add(al);
						}
					}
					--bi;
				}
			}
			Serializer.serializeDFMap(globale);
			
			o.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
