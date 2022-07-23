package dijkstra;

import java.util.HashSet;
import java.util.Set;

public class ASet implements ASetInterface {
	private Set<VertexInterface> list;
	
	public ASet() {
		this.list = new HashSet<VertexInterface>();
	}
	
	public Set<VertexInterface> getList(){
		return list;
	}
	
	@Override
	public boolean search(VertexInterface S) {
		return list.contains(S);
	}

	@Override
	public void add(VertexInterface S) {
		list.add(S);
		
	}

}
