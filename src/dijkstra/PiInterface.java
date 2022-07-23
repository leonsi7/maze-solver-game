package dijkstra;

import java.util.Hashtable;

public interface PiInterface {
	
	/**
	 * Ajoute un point et sa distance à la racine 
	 * @param v Le point à ajouter
	 * @param distance_min Sa distance (minimale) à la racine
	 */
	public void add_distance(VertexInterface v, int distance_min);
	/**
	 * Permet de récupérer la distance d'un point à la racine
	 * @param v Le point considéré
	 * @return La distance à la racine
	 */
	public int get_distance(VertexInterface v);
	/**
	 * Permet de récupérer la liste Pi
	 * @return La liste Pi
	 */
	public Hashtable<VertexInterface,Integer> getPi();
}
