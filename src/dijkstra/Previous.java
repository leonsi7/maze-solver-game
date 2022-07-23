package dijkstra;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import maze.MazeReadingException;

public class Previous implements PreviousInterface{

	private Hashtable<VertexInterface,VertexInterface> previous;
	VertexInterface v;
	
	public Previous() {
		this.previous = new Hashtable<VertexInterface, VertexInterface>();
	}
	@Override
	public void add_previous(VertexInterface v, VertexInterface father) {
		previous.put(v, father);
	}

	@Override
	public VertexInterface get_previous(VertexInterface v) {
		return previous.get(v);
		
	}
	public ArrayList<VertexInterface> get_way(VertexInterface A, VertexInterface D ) throws MazeReadingException {
		if (!this.previous.containsKey(D))
			throw new MazeReadingException("No way can be found between D and A for the current maze");
		
		ArrayList<VertexInterface> res = new ArrayList<VertexInterface>();
		VertexInterface pivot = D;
		while (pivot != A) {
			res.add(pivot);
			pivot.setWentThrough(true);
			pivot = get_previous(pivot);
		}
		res.add(A);
		return res;
		
	}

}
