package gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import badgers.fed.twitminer.Serializer;
import badgers.fed.twitminer.model.Motif;

public class MainPanel extends JPanel{
	public MainPanel() {
		HashMap<Motif, Motif> dfs = Serializer.deSerializeDF();
		String title[] = {"Motif impliquant", "Support", "Motif impliqué", "Support", "Confiance"};
		Object[][] data = new Object[dfs.size()][5];
		Set<Motif> implicateurs =  dfs.keySet();
		Iterator<Motif> it = implicateurs.iterator();
		int i = 0;
		while(it.hasNext()) {
			Motif m = it.next();
			data[i][0] = m.toString();
			data[i][1] = m.getFreq();
			data[i][2] = dfs.get(m).toString();
			data[i][3] = dfs.get(m).getFreq();
			data[i][4] = String.valueOf(dfs.get(m).getFreq()/m.getFreq());
			++i;
		}
			
		
//		JTable table = new JTable(data, title);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		JScrollPane jsp = new JScrollPane(table);
//		this.add(jsp);
		this.add(new JButton());
	}
}
