package badgers.fed.twitminer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import badgers.fed.twitminer.model.Motif;

public class Serializer {

	/**
	 * Serialize the given keywords dictionary to a hard-coded file
	 * 
	 * 
	 * @param keywords
	 */
	static public void serializeKeywords(ArrayList<String> keywords) {
		try {
			ObjectOutputStream keywordsSave = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File(
							"./keywordsmap"))));
			keywordsSave.writeObject(keywords);
			keywordsSave.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bring back the dictionnary of keywords from the hard-coded file
	 * 
	 * @return the dictionnary of keywords
	 */
	@SuppressWarnings("unchecked")
	static public List<String> deSerializeKeywords() {
		try {
			ObjectInputStream keywordsSave = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File(
							"./keywordsmap"))));
			List<String> l = (List<String>) keywordsSave.readObject();
			keywordsSave.close();
			return l;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Serialize the given list of functional dependencies
	 * 
	 * @param List of functional dependencies to be serialized
	 *            
	 */
	static public void serializeDF(List<List<Motif>> globale2) {
		ObjectOutputStream DFs;
		try {
			DFs = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(new File("./DFserialized"))));
			DFs.writeObject(globale2);
			DFs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Bring back the functional dependencies from the hard-coded file
	 * 
	 * @return List of functional dependencies
	 */
	@SuppressWarnings("unchecked")
	static public List<List<Motif>> deSerializeDF() {
		try {
			ObjectInputStream DFSave = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(new File(
							"./DFserialized"))));
			List<List<Motif>> h = (List<List<Motif>>) DFSave.readObject();
			DFSave.close();
			return h;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
