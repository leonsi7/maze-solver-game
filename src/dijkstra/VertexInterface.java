package dijkstra;

public interface VertexInterface {
	
	/**
	 * Permet de récupérer l'abcisse d'un point
	 * @return Son abcisse
	 */
	public int getX();
	/**
	 * Permet de récupérer l'ordonnée d'un point
	 * @return Son ordonnée
	 */
	public int getY();
	
	/**
	 * Permet de récupérer le type de la case (A,E,W ou D)
	 * @return Le type
	 */
	public String getLabel() ;
	/**
	 * Permet de dire à une case si le chemin passe sur elle ou non
	 * @param wentThrough vrai si le chemin passe dessus
	 */
	public void setWentThrough(boolean wentThrough);
}
