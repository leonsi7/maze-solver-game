package dijkstra;

import java.util.Set;

public interface ASetInterface {
	
	/**
	 * Permet de chercher si un point est dans la liste A
	 * @param S Le point � chercher
	 * @return Vrai si le point y est
	 */
	public boolean search(VertexInterface S); 
	/**
	 * Ajoute un point � la liste
	 * @param S Le point � ajouter
	 */
	public void add(VertexInterface S); 
	/**
	 * Permet de r�cup�rer toute la liste des points
	 * @return La liste
	 */
	public Set<VertexInterface> getList();
}
