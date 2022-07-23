package ui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.* ;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.Maze;
import maze.MazeReadingException;
import model.*;
/**
 * Classe de la fen�tre principale
 * @author L�on
 *
 */
public class MazeSolver extends JFrame implements ChangeListener
{
	private final MazeMenuBar mazeMenuBar ;
	private final WindowPanel windowPanel ;
	private MazeSolverModel mazeSolverModel = new MazeSolverModel();
/**
 * Constructeur
 * Il initie l'application, puis ouvre le fichier open-example
 * @throws IOException 
 */
    public MazeSolver()
   {
       super("MazeSolver") ; // Window title
  	   setJMenuBar(mazeMenuBar = new MazeMenuBar(this)) ;
	   setContentPane(windowPanel = new WindowPanel(this));
	   mazeSolverModel.addObserver(this);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; // Explicit !
       setResizable(false);
       pack() ;            // Components sizes and positions
       setVisible(true) ;
       try {
    	   setIconImage(ImageIO.read(new File("data/icon.png")));
    	   }
       catch(IOException e) {
    	   e.printStackTrace();
       }
       mazeSolverModel.openFileMazeKnownPath("data/open-example");
	
   }
/**
 * Permet de r�cup�rer le mod�le de l'application
 * @return Le mod�le
 */
	public MazeSolverModel getMazeSolverModel() 
	{
		return mazeSolverModel;
	}
/**
 * Permet d'ajouter le mod�le de l'application
 * @param mazeSolverModel Le nouveau mod�le
 */
	public void setMazeSolverModel(MazeSolverModel mazeSolverModel) 
	{
		this.mazeSolverModel = mazeSolverModel;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		windowPanel.notifyForUpdate();	
		mazeMenuBar.notifyForUpdate();
	}
	/**
	 * Permet de r�cup�rer le labyrinthe si il y en a un
	 * @return Le labyrinthe courant
	 */
	public Maze getMaze() {
		return mazeSolverModel.getMaze();
	}

	
}