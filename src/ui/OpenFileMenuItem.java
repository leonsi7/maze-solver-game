package ui;

import javax.swing.* ;
import javax.swing.filechooser.FileSystemView;

import maze.Maze;
import maze.MazeReadingException;
import model.MazeSolverModel;

import java.awt.event.*;
import java.io.File;


public class OpenFileMenuItem extends JMenuItem implements ActionListener {
	private final MazeSolver mazeSolver ;

	   public OpenFileMenuItem(MazeSolver mazeSolver)
	   {
	      super("Ouvrir un fichier...") ; // Text of menu item

	      this.mazeSolver = mazeSolver ;
	      addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
			mazeSolver.getMazeSolverModel().openFileMaze();
		
	}
}
