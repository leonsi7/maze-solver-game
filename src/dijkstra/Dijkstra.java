package dijkstra;

import java.util.ArrayList;

/**
 * Classe instanciant la m�thode permettant de r�soudre les labyrinthes en utilisant l'algorithme de Dijkstra
 * @author L�on
 *
 */
public class Dijkstra {
	/**
	 * Algorithme de dijkstra
	 * @param g Le labyrinthe � r�soudre
	 * @param r Le point de d�part pour l'algorithme de dijkstra
	 * @param size La taille du labyrinthe
	 * @return La liste des pr�decesseur de chaque point
	 */
	
	public static PreviousInterface previous(GraphInterface g,VertexInterface r, int size) {
		
		
		ASetInterface a = new ASet();
		PiInterface pi = new Pi();
		PreviousInterface previous = new Previous();
		
		
		
		
		a.add(r);
		VertexInterface pivot = r;
		pi.add_distance(r, 0);
		ArrayList<VertexInterface> tableauDeSommets = g.getAllVertices();
		for(VertexInterface s : tableauDeSommets) {if (s != r ) pi.add_distance(s, size*size+1);}
		for(int j = 0; j< tableauDeSommets.size();j++) {
			for(VertexInterface y : g.getSuccessors(pivot)) {
				if (!a.search(y)) {
					int l =  (int) (pi.get_distance(pivot) + g.getWeight(y, pivot));
					if (l < pi.get_distance(y)) {
						pi.add_distance(y, l);
						previous.add_previous(y,pivot);
					}
				}
			}
			int min = size*size +1;
			for (VertexInterface y : tableauDeSommets) {
				int dist = pi.get_distance(y);
				if ((!a.search(y)) && (dist<=min)) {
					min = dist;
					pivot = y;
				}
			}
			a.add(pivot);
			}
			
		return previous;
			}


}
