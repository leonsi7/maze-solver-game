package maze;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {
	private int X;
	private int Y;
	private boolean wentThrough; //Booleen indiquant si le chemin allant de D � A ustilise ce sommet.
	/**
	 * Constructeur de MBox, on indique � quel endroit se situe la case
	 * @param X Son abcisse
	 * @param Y Son ordonn�e
	 */
	public MBox(int X, int Y) {
		this.X = X;
		this.Y = Y;
		wentThrough = false;
		
	}
	/**
	 * R�cup�re l'abcisse de la case
	 */
	public final int getX() {
		return X;
	}
/**
 * R�cup�re l'ordonn�e de la case
 */
	public final int getY() {
		return Y;
	}
	
	/**
	 * Permet de dire � quel type appartient la case
	 */
	public abstract String getLabel();
	/**
	 * Pemet de dire si le chemin passe sur cette case
	 */
	public void setWentThrough(boolean wentThrough) {
		this.wentThrough = wentThrough;
	}
	/**
	 * Premt de savoir si le chemin passe sur cette case
	 * @return Vrai si le chamin passe sur cette case
	 */
	public boolean getWentThrough() {
		return wentThrough;
	}
}
