package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import badgers.fed.twitminer.Serializer;
import badgers.fed.twitminer.model.Motif;

public class MainPanel extends JTabbedPane{
	
	JTable table = null;
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
		
		JPanel tab1 = new JPanel();
		
		JPanel tab2 = new JPanel();
		
		JTextField txtFld = new JTextField(10);
		txtFld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTextField txtFld = (JTextField) arg0.getSource();
				try
				{
					String Str = txtFld.getText();
				}
				catch (NullPointerException e) {
				}
				
			}
			}
		);
		
		tab1.setLayout(new BorderLayout());
		
		table = new JTable(data, title);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane jsp = new JScrollPane(table);
		tab1.add(jsp, BorderLayout.CENTER);
		
		this.addTab("Affichage Résultats", tab1);
	}
}
