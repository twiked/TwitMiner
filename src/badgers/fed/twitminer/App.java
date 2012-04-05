package badgers.fed.twitminer;

import java.io.IOException;

import gui.MainWindow;

public class App {
	static int seuilSupport = 100;
	static double seuilConfiance = 0.6;
	public static void main(String[] args) {
		if (args.length == 3) {
			seuilSupport = Integer.valueOf(args[1]);
			seuilConfiance = Double.valueOf(args[2]);
		}
			
		//À décommenter pour effectuer les "phases" du projet
		try {
//			new ExtractionTT();
			Process p = Runtime.getRuntime().exec("./apriori ./data/trans " + String.valueOf(seuilSupport) + " ./data/resultat");
			p.waitFor();
			new ExtractionDF(seuilConfiance);
			new DFWriter();
			new Nettoyage();
			new MainWindow();
		} catch (IOException e) {
			System.err.println("Erreur lors de l'exécution de apriori");
		} catch (InterruptedException e) {
			System.err.println("Erreur lors de l'exécution de apriori");
		}
	}
}
