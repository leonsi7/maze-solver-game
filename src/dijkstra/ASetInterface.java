package dijkstra;

import java.util.Set;

public interface ASetInterface {
	
	/**
	 * Permet de chercher si un point est dans la liste A
	 * @param S Le point à chercher
	 * @return Vrai si le point y est
	 */
	public boolean search(VertexInterface S); 
	/**
	 * Ajoute un point à la liste
	 * @param S Le point à ajouter
	 */
	public void add(VertexInterface S); 
	/**
	 * Permet de récupérer toute la liste des points
	 * @return La liste
	 */
	public Set<VertexInterface> getList();
}
