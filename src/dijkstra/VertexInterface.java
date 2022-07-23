package dijkstra;

public interface VertexInterface {
	
	/**
	 * Permet de r�cup�rer l'abcisse d'un point
	 * @return Son abcisse
	 */
	public int getX();
	/**
	 * Permet de r�cup�rer l'ordonn�e d'un point
	 * @return Son ordonn�e
	 */
	public int getY();
	
	/**
	 * Permet de r�cup�rer le type de la case (A,E,W ou D)
	 * @return Le type
	 */
	public String getLabel() ;
	/**
	 * Permet de dire � une case si le chemin passe sur elle ou non
	 * @param wentThrough vrai si le chemin passe dessus
	 */
	public void setWentThrough(boolean wentThrough);
}
