package ui;

import javax.swing.* ;
import javax.swing.filechooser.FileSystemView;

import maze.Maze;
import maze.MazeReadingException;
import model.MazeSolverModel;

import java.awt.event.*;
import java.io.File;


public class SaveFileMenuItem extends JMenuItem implements ActionListener {
	private final MazeSolver mazeSolver ;

	   public SaveFileMenuItem(MazeSolver mazeSolver)
	   {
	      super("Enregistrer") ; // Text of menu item

	      this.mazeSolver = mazeSolver ;
	      MazeSolverModel mazeSolverModel = mazeSolver.getMazeSolverModel();
	      addActionListener(this);
	      setVisible((mazeSolverModel.getThereIsMaze())&&(!mazeSolverModel.isPathNull()));
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeSolver.getMazeSolverModel().saveFile(false);
		
	}

	public void notifyForUpdate() {
		MazeSolverModel mazeSolverModel = mazeSolver.getMazeSolverModel();
		setVisible((mazeSolverModel.getThereIsMaze())&&(!mazeSolverModel.isPathNull()));
		
	}
}
