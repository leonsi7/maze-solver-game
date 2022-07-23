package dijkstra;

import java.util.ArrayList;

import maze.MazeReadingException;

public interface PreviousInterface {
	
	/**
	 * Permet d'avouter un point ainsi que son prédécesseur
	 * @param v Le point à ajouter
	 * @param p Son prédécesseur
	 */
	public void add_previous(VertexInterface v, VertexInterface p);
	/**
	 * Permet de récupérer le prédécesseur d'un point
	 * @param v Le point
	 * @return Son prédecesseur
	 */
	public VertexInterface get_previous(VertexInterface v);
	/**
	 * Permet de récupérer le chemin entre deux points
	 * @param A Le point de départ
	 * @param D L'arrivée
	 * @return Liste des points sur lequel passe le chemin
	 * @throws MazeReadingException Si il n'y a pas de chemin
	 */
	public ArrayList<VertexInterface> get_way(VertexInterface A, VertexInterface D ) throws MazeReadingException;
}
