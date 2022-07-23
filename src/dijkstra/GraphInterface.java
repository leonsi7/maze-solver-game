package dijkstra;


import java.util.ArrayList;

import maze.MBox;

public interface GraphInterface {
	
	/**
	 * Permet de r�cup�rer tous les voisins d'un point 
	 * @param vertex Le point consid�r�
	 * @return La liste des voisins
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) ;
	/**
	 * Permet de r�cup�rer tous les points du labyrinthe
	 * @param vertex Le point consid�r�
	 * @return le tableau de points
	 */
	public ArrayList<VertexInterface> getAllVertices() ;
	/**
	 * Permet de donner la distance entre deux points
	 * @param src Le premier point
	 * @param dst Le deuxi�me
	 * @return La distance d=entre ces points
	 */
	public int getWeight(VertexInterface src,VertexInterface dst) ;
/**
 * Permet de donner tous les vertex dans une liste et non un tableau
 * @return La liste contenant tous les points
 */
	public ArrayList<MBox> getBoxes_simpleListe();

}
