package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class GenerateMazeMenuItem extends JMenuItem implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final MazeSolver mazeSolver ;

	   public GenerateMazeMenuItem(final MazeSolver mazeSolver)
	   {
	      super("Générer un nouveau labyrinthe...") ; // Text of menu item

	      this.mazeSolver = mazeSolver ;
	      addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeSolver.getMazeSolverModel().generateMaze();
		
	}
}
