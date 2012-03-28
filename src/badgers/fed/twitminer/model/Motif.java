package badgers.fed.twitminer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings("unchecked")
public class Motif implements List<Integer>{
	private List<Integer> motif;
	private int freq;
	
	public Motif(int freq)
	{
		this.setFreq(freq);
		this.setMotif(new ArrayList<Integer>());
	}
	
	public List<Integer> getMotif() {
		return motif;
	}
	public void setMotif(List<Integer> motif) {
		this.motif = motif;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public void addItem(int item) {
		this.motif.add(item);
	}

	@Override
	public int size() {
		return motif.size();
	}

	@Override
	public boolean isEmpty() {
		return motif.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return motif.contains(o);
	}

	@Override
	public Iterator<Integer> iterator() {
		return motif.iterator();
	}

	@Override
	public Integer[] toArray() {
		return (Integer[]) motif.toArray();
	}

	@Override
	public Integer[] toArray(Object[] a) {
		return null;
	}

	@Override
	public boolean add(Integer e) {
		return motif.add((Integer) e);
	}

	@Override
	public boolean remove(Object o) {
		return motif.remove(o);
	}

	@Override
	public boolean containsAll(Collection c) {
		return motif.containsAll(c);
	}

	@Override
	public boolean addAll(Collection c) {
		return motif.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		return motif.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection c) {
		return motif.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection c) {
		return motif.retainAll(c);
	}

	@Override
	public void clear() {
		motif.clear();
		freq = 0;
	}

	@Override
	public Integer get(int index) {
		return motif.get(index);
	}

	@Override
	public Integer set(int index, Integer element) {
		return motif.set(index, (Integer) element);
	}

	@Override
	public void add(int index, Integer element) {
		motif.add(index, (Integer) element);
	}

	@Override
	public Integer remove(int index) {
		return motif.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return motif.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return motif.lastIndexOf(o);
	}

	@Override
	public ListIterator<Integer> listIterator() {
		return motif.listIterator();
	}

	@Override
	public ListIterator<Integer> listIterator(int index) {
		return motif.listIterator(index);
	}

	@Override
	public List<Integer> subList(int fromIndex, int toIndex) {
		//La fréquence devrait être obtenable
		return motif.subList(fromIndex, toIndex);
	}
	
}