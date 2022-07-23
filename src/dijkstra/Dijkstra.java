package dijkstra;

import java.util.ArrayList;

/**
 * Classe instanciant la méthode permettant de résoudre les labyrinthes en utilisant l'algorithme de Dijkstra
 * @author Léon
 *
 */
public class Dijkstra {
	/**
	 * Algorithme de dijkstra
	 * @param g Le labyrinthe à résoudre
	 * @param r Le point de départ pour l'algorithme de dijkstra
	 * @param size La taille du labyrinthe
	 * @return La liste des prédecesseur de chaque point
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
