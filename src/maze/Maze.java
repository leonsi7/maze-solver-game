package maze;

import java.util.ArrayList;


import dijkstra.GraphInterface;
import dijkstra.VertexInterface;
import ui.MazePanel;

import java.io.*;


/**
 * Classe permettant de créer un labyrinthe
 * @author Léon
 *
 */
public class Maze implements GraphInterface {
	private int mazeSize =0;
	private ArrayList<ArrayList<MBox>> boxes = new ArrayList<ArrayList<MBox>>();
	

	
/**
 * Permet de changer une case 
 * @param i Ligne de la case
 * @param j Colonne de la case
 * @param mBox La nouvelle case que l'on veut mettre à cet emplacement
 */
	
	public void setMBox(int i,int j, MBox mBox) {
		ArrayList<MBox> line = boxes.get(i);
		line.set(j, mBox);
		boxes.set(i, line);
	}
		
	
	@Override
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		int X = vertex.getX();
		int Y = vertex.getY();
		
		ArrayList<VertexInterface> res = new ArrayList<VertexInterface>();
		for(int i=-1;i<2;i++) { // On parcourt tous les voisins de la case concernée 
			for(int j=-1;j<2;j++) {
				if (((i==0) || (j==0)) && (j!=i) && (i+X >= 0) && (i+X < mazeSize) &&(j+Y >= 0) && (j+Y < mazeSize)) {
					if (!(this.boxes.get(i+X).get(j+Y).getLabel() == "W")) {
					res.add((MBox)boxes.get(i+X).get(j+Y));//On ajoute les voisins dans notre liste résultat
					}
				}
			}
		}
		return res;
	}
	

	@Override
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> res = new ArrayList<VertexInterface>();
		for(int i = 0; i<mazeSize; i++) { // On parcourt le table graphe en entier
			for(int j = 0; j<mazeSize; j++) {
				
				res.add(boxes.get(i).get(j)); // On récupère tous les sommets
			}
		}
		return res;
	}

	@Override
	public int getWeight(VertexInterface src, VertexInterface dst) {
		if (getSuccessors(dst).contains(src))
			return 1;
		return 10000;
	}
	/**
	 * Permet d'ouvrir un fichier texte et de le convertir (si possible) en labyrinthe
	 * @param fileName Le nom du fichier à ouvrir
	 * @throws MazeReadingException Exception levée si le fichier conidéré n'est pas sous le bon format
	 * @throws IOException Erreurs de lectures
	 */
	public final void initFromTextFile(String fileName) throws MazeReadingException, IOException {
	    BufferedReader bufferedreader = null;
	    FileReader filereader = null;
	    int mazeSizeX = 0;
	    int mazeSizeY = 0;
			String strCurrentLine;
			filereader = new FileReader(fileName);
			bufferedreader = new BufferedReader(filereader);
			int i = 0;
			int cmptAorD = 0;
		    while ((strCurrentLine = bufferedreader.readLine()) != null) {
		    	
		    	//if (i > 0) { //On commence à comparer les lignes 2 à 2 qu'à partir de la deuxième
			    //	if ((strPrecLine.length() != strCurrentLine.length()))
			    //		throw new MazeReadingException("2 lines have different length ", fileName,i); //Les lignes n'ont pas la même taille
			    //	}
		    	ArrayList<MBox> line = new ArrayList<MBox>();
		    	int j = 0;
		    	while (j < strCurrentLine.length())
		    	{
		    	char l;
		    	l = strCurrentLine.charAt(j);
		    	if (Character.compare(l, 'E') == 0) 
		    		line.add(new EBox(i,j));
		    	else if (Character.compare(l, 'D') == 0) {
		    		line.add(new DBox(i,j));
		    		cmptAorD++;
		    	}
		    	else if (Character.compare(l, 'A') == 0) {
		    		line.add(new ABox(i,j));
		    		cmptAorD++;
		    	}
		    	else if (Character.compare(l, 'W') == 0) 
		    		line.add(new WBox(i,j));
		    	else if (Character.compare(l, ' ') == 0);
		    		
		    	else
		    		throw new MazeReadingException("Wrong letter (letter A,E,W or D is required) ",fileName,i,j); // La lettre n'est pas A,E,D ou W
		    	j++;
		    	
		    	}
		    	this.boxes.add(line);
				mazeSizeY = j;
		    	i++;
		        }
		    mazeSizeX = i;
		    if (mazeSizeX != mazeSizeY) throw new MazeReadingException("Your maze isn't a square ",fileName);
		    if (cmptAorD > 2) throw new MazeReadingException("The maze have too many A or D ",fileName);
		    this.mazeSize = mazeSizeX;
		    if (bufferedreader != null)
		        bufferedreader.close();
            if (filereader != null)
            	filereader.close();
	}
/**
 * Permet d'enregistrer le labyrinthe considéré
 * @param fileName Le nom du nouveau fichier
 * @throws FileNotFoundException Si le fichier n'est pas trouvé
 * @throws UnsupportedEncodingException Erreurs d'encodage
 */

	public final void saveToTextFile (String fileName) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		for(int i = 0; i<mazeSize; i++) {
			for(int j=0; j<mazeSize; j++) {
				if (boxes.get(i).get(j).getLabel() =="E") 
					writer.print("E");
				if (boxes.get(i).get(j).getLabel()=="D") //J'avais mis des instanceof !!
					writer.print("D");
				if (boxes.get(i).get(j).getLabel() == "W")
					writer.print("W");
				if (boxes.get(i).get(j).getLabel() =="A")
					writer.print("A");
			}
			writer.println("");
		}
		writer.close();
		
	}
/**
 * Permet de trouver le point d'arrivée
 * @return Le point d'arrivée
 */
	public ArrayList<Integer> searchA() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		for(Integer i = 0; i<mazeSize;i++) {
			for(Integer j = 0; j<mazeSize; j++) {
				if(boxes.get(i).get(j).getLabel() == "A") {
					res.add(i);
					res.add(j);
				}
			}
		}
		return res;
	}
/**
 * Pert de trouver le point de départ
 * @return Le point de départ
 */
	public ArrayList<Integer> searchD() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(Integer i = 0; i<mazeSize;i++) {
			for(Integer j = 0; j<mazeSize; j++) {
				if(boxes.get(i).get(j).getLabel()=="D") {
					res.add(i);
					res.add(j);
				}
			}
		}
		return res;
	}
	/**
	 * Donne la liste des points dans un tableau
	 * @return Le tableau contenant la liste des points
	 */
	public ArrayList<ArrayList<MBox>> getBoxes() {
		return boxes;
	}
	/**
	 * Donne la liste des points dans une liste
	 * @return La liste contenant les points
	 */
	public ArrayList<MBox> getBoxes_simpleListe(){
		ArrayList<MBox> res = new ArrayList<MBox>();
		for (ArrayList<MBox> liste : boxes) {
			for (MBox sommet : liste) {
				res.add(sommet);
			}
		}
		return res;
	}
	/**
	 * Renvoir la taille du labyrinthe
	 * @return La taille
	 */

	public int getSize() {
		return mazeSize;
	}

	/**
	 * Permet de mettre tous les points en "non passés"
	 */
	public void setWentThroughFalse() {
		for(ArrayList<MBox> line : boxes) {
			for(MBox box : line) {
				box.setWentThrough(false);
			}
		}
		
	}
	/**
	 * Crée un nouveau labyrinthe
	 * @param size La taille du nouveau labyrinthe
	 */

	public void createNewMaze(int size) {
		this.mazeSize = size;
		ArrayList<ArrayList<MBox>> boxes = new ArrayList<ArrayList<MBox>>();
		for(int i=0;i<size;i++) {
			ArrayList<MBox> line = new ArrayList<MBox>();
			for(int j=0;j<size;j++) {
				line.add(new EBox(i,j));
			}
			boxes.add(line);
		}
		this.boxes = boxes;
	}
	
	/**
	 * Remplit un labyrinthe avec des murs selon la probabilité p
	 * @param p La probabilité d'avoir un mur
	 */

	public void fillNewMaze(float p) {
		int coordXA = mazeSize-1;
		int coordYA = mazeSize-1;
		int coordXD = 0;
		int coordYD = 0;
		for(int i = 0; i<mazeSize; i++) { // On parcourt le table graphe en entier
			for(int j = 0; j<mazeSize; j++) {
				if ((i==coordXA)&&(j==coordYA)) setMBox(i,j,new ABox(i, j));
				else if ((i==coordXD)&&(j==coordYD)) setMBox(i,j,new DBox(i, j));
				else if (Math.random() < p) setMBox(i,j,new WBox(i, j));
				else setMBox(i,j,new EBox(i, j));
			}
		}
	}
}
