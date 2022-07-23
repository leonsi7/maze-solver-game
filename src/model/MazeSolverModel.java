
package model;

import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.* ;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

import dijkstra.Dijkstra;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;
import ui.PaintedMBox;

/**
 * Modèle de l'interface graphique
 */
public class MazeSolverModel {
	
	
  private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>() ;
  private Maze maze;
  private ArrayList<PaintedMBox> paintedBoxes = new ArrayList<PaintedMBox>();
  private boolean modified = false;
  private int size;
  private boolean solved;
  private boolean thereIsMaze = (maze != null);
  private String fileName;
  private boolean isA;
  private boolean isD;
  private boolean isDlackLast;
  
  /**
   * Indique si il y a ou pas un nom du fichier associe au labyrinthe actuel
   * @return booleen vrai si le labyrinthe actuel possède un nom de fichier
   */
  public boolean isPathNull() {
	  
	  return (fileName == null);
  }

  /**
   * Resout le labyrinthe 
   * @exception MazeReadingException revoie cette exception si il n'y a pas la lettre A et D, ou si il n'y a pas de chemis possible
   */
  public void solveMaze() throws MazeReadingException {
	  
	  
	  	ArrayList<Integer> coordD = maze.searchD(); //On cherche le point de dÃ©part
	  	ArrayList<Integer> coordA = maze.searchA();//On cherche le point d'arrivÃ©e
	  	boolean quit = false;
	 try {
	  	if ((coordA.isEmpty()) || (coordD.isEmpty())) {
	  		sendErrorMessage("Il manque la case arrivee ou depart !");
	  		throw new MazeReadingException("A or D is lacking");
	  	}
	 }
	 catch (MazeReadingException e) {
	  		e.printStackTrace();
	  		quit = true;
	  	}
	 if (!quit) {
	 try {
		VertexInterface D = maze.getBoxes().get(coordD.get(0)).get(coordD.get(1)); //Recupère le point de depart
		VertexInterface A = maze.getBoxes().get(coordA.get(0)).get(coordA.get(1));
		PreviousInterface previous = Dijkstra.previous(maze, A, size);//Recupère le point d'arrivee
		previous.get_way(A, D);
		solved = true;
		stateChanges();
	 }
	 catch (MazeReadingException e) {
		sendErrorMessage("Il n'y a pas de solutions possibles pour ce labyrinthe !");
		e.printStackTrace();
	    }
	 }
  }
  public void addObserver(ChangeListener listener) {
	    listeners.add(listener) ;
  }
  /**
   * Lorsqu'un changement d'affichage doit avoir lieu
   */
  public void stateChanges() {
	  
        ChangeEvent evt = new ChangeEvent(this) ;
        for (ChangeListener listener : listeners)
        	listener.stateChanged(evt);
  }
  /**
   * Recupère le labyrinthe courant
   * @return l'objet Maze actuel
   */
  public Maze getMaze() {
	  
	  return this.maze;
  }
  
  /**
   * Permet de dire si le labyrinthe est resolu
   * @param booleen indiquant si le labyrinthe est resolu
   */
  public void setMazeSolved(boolean mazeSolved) {
	 
	  this.solved = mazeSolved;
	  stateChanges();
  }
  
  /**
   * Permet de savoir si le labyrinthe est resolu
   * @return booleen disant si le labyrinthe est resolu
   */
  public boolean mazeSolved() {
	  
	  return solved;
  }
  
  /**
   * Procedure pour ouvrir un fichier : on demande dans un premier temps quel fichier ouvrir, ensuite on demande à Maze de l'ouvrir et de creer le labyrinthe correspondant
   */
  public void openFileMaze() {
	  
	  if(quitMazeRequest()) {
		  JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		  choose.setCurrentDirectory(new File("data"));
		  // Ouvrir le fichier
		  int res = choose.showOpenDialog(null);
		  // Enregistrer le fichier
		  // int res = choose.showSaveDialog(null);
		  if (res == JFileChooser.APPROVE_OPTION) {
			  File file = choose.getSelectedFile();
			  System.out.println(file.getAbsolutePath());
			  this.fileName = file.getAbsolutePath();
			  openFileMazeKnownPath(fileName);
			  this.thereIsMaze = (maze != null);
			  sendErrorMessage("Veuillez modifier le labyrinthe avant de le resoudre. \n"+"Il y a quelque fois des erreurs si l'on ne le fait pas...");
		  }
	  }
  }

  /**
   * Permet d'ouvrir une boite de dialogue avec le message donne
   * @param message Message à afficher
   */
  private void sendErrorMessage(String message) {
	
	JFrame frame = new JFrame("Erreur");
	JOptionPane.showMessageDialog(frame, message);
	
  }
  /**
   * Accède au booleen disant si il y a un labyrinthe courant
   * @return Renvoie vrai si il y a un labyrinthe courant
   */

  public boolean getThereIsMaze() {
		return thereIsMaze;
	}
  /**
   * Permet de sauvegarder le labyrinthe courant
   * @param isNewPath booleen vrai si le labyrinthe courant possède un nom de fichier, auquel cas pas besoin d'ouvrir une boite de dialogue
   */

  public void saveFile(boolean isNewPath)  {
	  try {
		  String newSaveFile = null;
		  if (isNewPath) {
			  JFileChooser saveFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			  saveFile.setCurrentDirectory(new File("data"));
			  if (saveFile.showSaveDialog(saveFile) == JFileChooser.APPROVE_OPTION) {
				  File file = saveFile.getSelectedFile();
				  newSaveFile = file.getAbsolutePath();
			  }
		  }
		  else   {
			  newSaveFile = fileName;
		  }
		  maze.saveToTextFile(newSaveFile);
		  this.fileName = newSaveFile;
		  this.modified = false;
	  }
		
	 catch (FileNotFoundException e) {
		sendErrorMessage("Le fichier n'a pas ete trouve");
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		sendErrorMessage("Il y a une erreur d'encodage...");
		e.printStackTrace();
	}
	  
	  
	  
	}
  /**
   * Permet de donner la taille du labyrinthe
   * @param size La nouvelle taille du labyrinthe
   */

  	public void setSize(int size) {
		this.size = size;
	}

  	/**
  	 * Permet d'ajouter la liste des elements PaintedBox du labyrinthe
  	 * @param paintedBoxes La liste
  	 */
	public void setPaintedBoxes(ArrayList<PaintedMBox> paintedBoxes) {
		this.paintedBoxes = paintedBoxes;
	}
	/**
	 * Permet de recuperer la liste de PaintedBox du Labyrinthe
	 * @return La liste
	 */

	public ArrayList<PaintedMBox> getPaintedBoxes(){
		return this.paintedBoxes;
	}

	/**
	 * Permet d'indiquer à toutes les cases du labyrinthe de mettre le booleen wentThrough en false, ce qui permet d'enlever le chemin solution
	 */
	public void setWentThroughFalse() {
		maze.setWentThroughFalse();
		this.solved = false;
		//On met tous les MBox en non passes
	}
	/**
	 * Permet de creer un labyrinthe
	 * Dans un premier temps, on demande à l'utilisateru la taille souhaitee, puis on demande à Maze de creer ce labyrinthe vide
	 */
	public void createMaze() {
		if(quitMazeRequest()) {
			int newSize = 0;
			JFrame parent = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			JSlider slider = getSlider1(optionPane);
			optionPane.setMessage(new Object[] {"Veuillez bouger le slider"," ", "Selectionnez la taille du nouveau labyrinthe : ", slider });
			optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
			optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
			JDialog dialog = optionPane.createDialog(parent, "Taille du labyrinthe");
			dialog.setVisible(true);
			Object result = optionPane.getInputValue();
			if (result instanceof Integer) {
				newSize = (int) optionPane.getInputValue();
				if(newSize != 0) {
					this.maze = new Maze();
					this.maze.createNewMaze(newSize);
					this.thereIsMaze = (maze != null);
					this.size = (int) newSize;
					this.isA = false;
					this.isD = false;
					this.isDlackLast = true;
					this.modified =true;
					stateChanges();
				}
				else sendErrorMessage("Vous ne pouvez pas creer un labyrinthe de taille 0 !");
			}
		}
	}
/**
 * Le slider pour demander la taille du labyrinthe souhaitee
 * @param optionPane Paramètre requis pour le slider
 * @return Le slider
 */
		static JSlider getSlider1(final JOptionPane optionPane) {
	        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	        slider.setMinorTickSpacing(10);
	        slider.setMajorTickSpacing(20);
	        slider.setPaintTicks(true);
	        slider.setPaintLabels(true);
	        ChangeListener changeListener = new ChangeListener() {
	          public void stateChanged(ChangeEvent changeEvent) {
	            JSlider theSlider = (JSlider) changeEvent.getSource();
	            if (!theSlider.getValueIsAdjusting()) {
	            	Integer res = new Integer(theSlider.getValue());
	            	int res2 = (int) res/2;
	            	if (res2==0) res2 = 1;
	            	int res3=2*res2;
	            	
	            	optionPane.setInputValue(res3);
	            }
	          }
	        };
	        slider.addChangeListener(changeListener);
	        return slider;
	      }
		/**
		 * Slider pour demander la proportion de mur dans le nouveau labyrinthe
		 * @param optionPane Paramètre requis pour le slider
		 * @return Le slider
		 */
		static JSlider getSlider2(final JOptionPane optionPane) {
	        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
	        slider.setMinorTickSpacing(5);
	        slider.setMajorTickSpacing(10);
	        slider.setPaintTicks(true);
	        slider.setPaintLabels(true);
	        ChangeListener changeListener = new ChangeListener() {
	          public void stateChanged(ChangeEvent changeEvent) {
	            JSlider theSlider = (JSlider) changeEvent.getSource();
	            if (!theSlider.getValueIsAdjusting()) {
	            	float res = theSlider.getValue();
	            	optionPane.setInputValue(res/100);
	            }
	          }
	        };
	        slider.addChangeListener(changeListener);
	        return slider;
	      }
		
		/**
		 * Permet de changer les paramètres isA, isD, et isDlackLast en fonction de la case que l'on clique dessus
		 * @param string La case que l'on vient de modifier
		 */
	public void setBooleanAD(String string) {
		switch(string) {
		case "A":{
			this.isA = false;
			this.isDlackLast = false;
			}break;
		case "D":{
			this.isD = false;
			this.isDlackLast = true;
			}break;
		case "EorW":{
			if(isDlackLast) {
				if(!isD) {
					isD = true;
					this.isDlackLast = false;
				}
				else if(!isA) isA=true;
			}
			else {
				if(!isA) {
					isA = true;
					this.isDlackLast = true;
				}
				else if(!isD) isD=true;
			}
		}break;
		default :break;
		}
		if (!isA && !isD) this.isDlackLast= true;
		this.modified = true;
	}
	/**
	 * Indique quel case il faut remplacer lors du clic
	 * @return La case à mettre au niveau du curseur
	 */

	public String whatTypeOfBoxChoose() {
		String choose= null;
		if (this.isDlackLast) {
			if (!isD) choose = "D";
			else if (!isA) choose = "A";
			else choose = "EorW";
		}
		else {
			if (!isA) choose = "A";
			else if (!isD) choose = "D";
			else choose = "EorW";
		}
		this.modified = true;
		return choose;
	}
	
	/**
	 * Procedure qui permet de verifier que l'on a bien sauvegarde le labyrinthe. Si ce n'est pas le cas, une boite de dialogue s'ouvre et demande
	 * a l'utilisateur si il veut l'enregistrer
	 * @return Vrai si la procedure doit continuer, faux pour annuler
	 */
	public boolean quitMazeRequest() {
		boolean continueProcess= true;
		if (this.modified) {
			JFrame frame = new JFrame();
		    Icon blueIcon = new ImageIcon("data/save.png");
		    Object stringArray[] = { "Enregistrer", "Ne pas enregistrer","Ne pas quitter" };
		    int inputValue = JOptionPane.showOptionDialog(frame, "Vous n'avez pas enregistre votre labyrinthe. Voulez-vous l'enregistrer ?", "Labyrinthe non enregistre",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, blueIcon, stringArray,
		        stringArray[0]);
			switch(inputValue) {
			case 0:{
				saveFile(true);
				continueProcess = true;
			}break;
			case 1:{
				continueProcess = true;
			}break;
			case 2:{
				continueProcess = false;
			}break;
			default: break;
			}

		}
		return continueProcess;
	}
/**
 * Permet de generer un labyrinthe avec des murs. La logiciel demande la taille et la proportion des murs, ensuite il genère un labyrinthe vide, puis le remplit.
 */

	public void generateMaze() {
		if(quitMazeRequest()) {
			int newSize = 0;
			JFrame parent = new JFrame();
			JOptionPane optionPane1 = new JOptionPane();
			JOptionPane optionPane2 = new JOptionPane();
			JSlider slider1 = getSlider1(optionPane1);
			JSlider slider2 =  getSlider2(optionPane2);
			optionPane1.setMessage(new Object[] { "Veuillez bouger les sliders", " ","Selectionnez la taille du labyrinthe à generer : ", slider1," ","Donnez le pourcentage de mur dans le labyrinthe : ", slider2 });
			optionPane1.setMessageType(JOptionPane.QUESTION_MESSAGE);
			optionPane1.setOptionType(JOptionPane.OK_CANCEL_OPTION);
			JDialog dialog = optionPane1.createDialog(parent, "Generateur de labyinthe");
			dialog.setVisible(true);
			Object result1 = optionPane1.getInputValue();
			Object result2 = optionPane2.getInputValue();
			if ((result1 instanceof Integer) && (result2 instanceof Float)) {
				newSize = (int) optionPane1.getInputValue();
				float p = (float) optionPane2.getInputValue();
				if(newSize != 0) {
					this.maze = new Maze();
					this.maze.createNewMaze(newSize);
					this.maze.fillNewMaze(p);
					this.thereIsMaze = (maze != null);
					this.size = (int) newSize;
					this.isA = true;
					this.isD = true;
					this.modified =true;
					stateChanges();
				}
				else sendErrorMessage("Vous ne pouvez pas creer un labyrinthe de taille 0 !");
				sendErrorMessage("Veuillez modifier le labyrinthe avant de le resoudre. \n"+"Il y a quelque fois des erreurs si l'on ne le fait pas...");
			}
		}
	}
/**
 * Ouvre l'aide de l'onglet "comment ça marche?"
 */

	public void openHelpHowToUse() {
		
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		Icon icon = new ImageIcon("data/question_mark.png");
		JOptionPane.showMessageDialog(frame,
			    "Bienvenue dans l'application MazeSolver ! \n"
			    + " \n"
			    + "Cette application permet de creer des labyrinthe (vides ou bien generes aleatoirement) et ensuite de les resoudre. \n"
			    + "Il existe aussi une fonctionnalite permettant d'enregistrer les labyrinthes dans un fichier texte particulier. \n"
			    + "\n"
			    + "Pour commencer, cliquez sur Fichier : \n"
			    + "- Vous pouvez creer un labyrinthe vide dans \"nouveau labyrinthe\". \n"
			    + "- Vous pouvez generer un labyrinthe aleatoire en indiquant la taille du labyrinthe souhaitee, puis la quantite de mur voulue. \n"
			    + "  (A noter que la quantite ne depasse pas 50% car au delà il n'y a très peu de chances de pouvoir trouver un chemin !) \n"
			    + " \n"
			    + "Ensuite vous pouvez editer le labyrinthe en cliquant sur les cases. Si vous ne comprenez pas comment ça marche, \n"
			    + "allez dans Aide>Editeur \n"
			    + "\n"
			    + "Enfin vous pouvez enregistrer le labyrinthe en allant sur Fichier>Enregistrer sous la première fois, puis Fichier>Enregistrer. \n"
			    + " \n"
			    + "Remarque : Il semblerait qu'il faille cliquer sur une case avant de resoudre le labyrinthe lorsqu'on en gènère ou quand on en ouvre un. \n"
			    + "\n"
			    + "En esperant que cette première aide vous soit utile ;)",
			    "Comment ça marche ?",
			    JOptionPane.INFORMATION_MESSAGE,
			    icon);
		
	}
	/**
	 * Ouvre l'aide de l'editeur
	 */
	
	public void openHelpEditor() {
		
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		Icon icon = new ImageIcon("data/question_mark.png");
		JOptionPane.showMessageDialog(frame,
			    "Comment utiliser l'editeur ? \n"
			    + " \n"
			    + "L'editeur est toujours actif lorsqu'il y a un labyrinthe ouvert. \n"
			    + "\n"
			    + "Pour l'utiliser, cliquez sur les cases : \n"
			    + "- La case noire est une case de mur, impossible de passer dessus. \n"
			    + "- La case blanche est une case vide, on peut passer dessus. \n"
			    + "- La case rouge est le depart. \n"
			    + "- La case verte est l'arrivee. \n"
			    + " \n"
			    + "Si il n'y a pas de case depart et arrivee, l'editeur les mettra en premier lorsque vous cliquerez sur une case. \n"
			    + "Ensuite vous changerez un mur en case vide et vice-versa. \n"
			    + "Enfin, vous pouvez cliquer sur les cases depart et arrivee qui partiront, mais qui reviendront lors des clics suivants.\n"
			    + " \n"
			    + "En esperant que cette deuxième aide vous soit utile ;)",
			    "Editeur de labyrinthe",
			    JOptionPane.INFORMATION_MESSAGE,
			    icon);
		
	}
	/**
	 * Ouvre les credits
	 */

	public void openCredits() {
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		Icon icon = new ImageIcon("data/credits.png");
		JOptionPane.showMessageDialog(frame,
			    "Une application cree par Leon S \n"
			    + "\n"
			    + "Projet d'INF103 du 06/03/2022"
			    + " \n"
			    + " \n"
			    
			    + "Cette application respecte les contraintes imposees :\n"
			    + "- Utiliser l'algorithme de Dijkstra pour resoudre le labyrinthe. \n"
			    + "- Ouvrir un labyrinthe depuis un fichier texte.\n"
			    + "- Afficher le labyrinthe et sa solution. \n"
			    + "- Enregistrer le labyrinthe dans un fichier texte. \n"
			    + "\n"
			    + "En esperant que cette application plaira au correcteur :)",
			    "Credits",
			    JOptionPane.INFORMATION_MESSAGE,
			    icon);
	}
	/**
	 * Permet d'ouvrir un fichier avec son nom qui est connu
	 * @param fileName Le nom du fichier à ouvrir
	 */
	
	public void openFileMazeKnownPath(String fileName) {
		Maze newMaze = new Maze();
		  try {
			  newMaze.initFromTextFile(fileName);
		  } catch (MazeReadingException e1) {
			  sendErrorMessage("Le fichier selectionne n'est pas sous le bon format : Il doit etre un carre contenant uniquement les lettre A,E,W ou D");
			  e1.printStackTrace();
		  } catch (IOException e2) {
			 sendErrorMessage("Une erreur de lecture de fichier est apparue (IOException)");
			e2.printStackTrace();
		  }
		  this.maze = newMaze;
		  this.thereIsMaze = (maze != null);
		  this.size = maze.getSize();
		  ArrayList<Integer> coordA = maze.searchA();
		  ArrayList<Integer> coordD = maze.searchD();
		  this.isA = true;
		  this.isD = true;
		  if (coordA.equals(new ArrayList<Integer>())) this.isA = false;
		  if (coordD.equals(new ArrayList<Integer>())) this.isD = false;
		  if (!isA && !isD) this.isDlackLast = true;
		  stateChanges();
	}
}
