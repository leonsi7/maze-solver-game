package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface{
	private Hashtable<VertexInterface,Integer> Pi;
	
	public Pi() {
		this.Pi = new Hashtable<VertexInterface, Integer>();
	}
	
	@Override
	public void add_distance(VertexInterface v, int minDistance) {
		Pi.put(v, (Integer) minDistance);
		
	}

	@Override
	public int get_distance(VertexInterface v) {
		return Pi.get(v);
	}
	
	public Hashtable<VertexInterface,Integer> getPi(){
		return Pi;
	}
}
