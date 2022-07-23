package dijkstra;


import java.util.ArrayList;

import maze.MBox;

public interface GraphInterface {
	
	/**
	 * Permet de récupérer tous les voisins d'un point 
	 * @param vertex Le point considéré
	 * @return La liste des voisins
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) ;
	/**
	 * Permet de récupérer tous les points du labyrinthe
	 * @param vertex Le point considéré
	 * @return le tableau de points
	 */
	public ArrayList<VertexInterface> getAllVertices() ;
	/**
	 * Permet de donner la distance entre deux points
	 * @param src Le premier point
	 * @param dst Le deuxième
	 * @return La distance d=entre ces points
	 */
	public int getWeight(VertexInterface src,VertexInterface dst) ;
/**
 * Permet de donner tous les vertex dans une liste et non un tableau
 * @return La liste contenant tous les points
 */
	public ArrayList<MBox> getBoxes_simpleListe();

}
