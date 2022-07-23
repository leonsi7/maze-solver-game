package dijkstra;

import java.util.Hashtable;

public interface PiInterface {
	
	/**
	 * Ajoute un point et sa distance � la racine 
	 * @param v Le point � ajouter
	 * @param distance_min Sa distance (minimale) � la racine
	 */
	public void add_distance(VertexInterface v, int distance_min);
	/**
	 * Permet de r�cup�rer la distance d'un point � la racine
	 * @param v Le point consid�r�
	 * @return La distance � la racine
	 */
	public int get_distance(VertexInterface v);
	/**
	 * Permet de r�cup�rer la liste Pi
	 * @return La liste Pi
	 */
	public Hashtable<VertexInterface,Integer> getPi();
}
