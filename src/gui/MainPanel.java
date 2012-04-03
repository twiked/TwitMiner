package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import badgers.fed.twitminer.Serializer;
import badgers.fed.twitminer.model.Motif;

public class MainPanel extends JTabbedPane{
	
	JTable table = null;
	List<String> keywords = Serializer.deSerializeKeywords();
	public MainPanel() {
		List<List<Motif>> dfs = Serializer.deSerializeDF();
		String title[] = {"Motif impliquant", "Support", "Motif impliqué", "Support", "Confiance"};
		Object[][] data = new Object[dfs.size()][5];
		int i = 0;
		for(List<Motif> m : dfs) {
			data[i][0] = m.get(0).toString(keywords);
			data[i][1] = m.get(0).getFreq();
			data[i][2] = m.get(1).toString(keywords);
			data[i][3] = m.get(1).getFreq();
			data[i][4] = String.valueOf(m.get(1).getFreq()/m.get(0).getFreq());
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
					//table.changeSelection(rowIndex, columnIndex, toggle, extend)
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
		this.add(jsp);
//		this.add(new JButton());
	}
}
