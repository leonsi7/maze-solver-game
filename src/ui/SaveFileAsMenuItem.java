package ui;

import javax.swing.* ;
import javax.swing.filechooser.FileSystemView;

import maze.Maze;
import maze.MazeReadingException;
import model.MazeSolverModel;

import java.awt.event.*;
import java.io.File;


public class SaveFileAsMenuItem extends JMenuItem implements ActionListener {
	private final MazeSolver mazeSolver ;

	   public SaveFileAsMenuItem(MazeSolver mazeSolver)
	   {
	      super("Enregistrer sous") ; // Text of menu item

	      this.mazeSolver = mazeSolver ;
	      addActionListener(this);
	      setVisible(mazeSolver.getMazeSolverModel().getThereIsMaze());
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeSolver.getMazeSolverModel().saveFile(true);
		
	}

	public void notifyForUpdate() {
		setVisible(mazeSolver.getMazeSolverModel().getThereIsMaze());
		
	}
}
