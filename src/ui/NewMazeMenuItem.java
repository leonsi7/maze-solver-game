package ui;

import javax.swing.* ;

import java.awt.event.*;



public class NewMazeMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final MazeSolver mazeSolver ;

	   public NewMazeMenuItem(final MazeSolver mazeSolver)
	   {
	      super("Nouveau labyrinthe...") ; // Text of menu item

	      this.mazeSolver = mazeSolver ;
	      addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeSolver.getMazeSolverModel().createMaze();
		
	}

	public void notifyForUpdate() {
		
		
	}
}
