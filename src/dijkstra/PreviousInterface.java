package dijkstra;

import java.util.ArrayList;

import maze.MazeReadingException;

public interface PreviousInterface {
	
	/**
	 * Permet d'avouter un point ainsi que son pr�d�cesseur
	 * @param v Le point � ajouter
	 * @param p Son pr�d�cesseur
	 */
	public void add_previous(VertexInterface v, VertexInterface p);
	/**
	 * Permet de r�cup�rer le pr�d�cesseur d'un point
	 * @param v Le point
	 * @return Son pr�decesseur
	 */
	public VertexInterface get_previous(VertexInterface v);
	/**
	 * Permet de r�cup�rer le chemin entre deux points
	 * @param A Le point de d�part
	 * @param D L'arriv�e
	 * @return Liste des points sur lequel passe le chemin
	 * @throws MazeReadingException Si il n'y a pas de chemin
	 */
	public ArrayList<VertexInterface> get_way(VertexInterface A, VertexInterface D ) throws MazeReadingException;
}
