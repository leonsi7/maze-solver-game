package ui;

import javax.swing.* ;

import maze.MazeReadingException;
import model.MazeSolverModel;

import java.awt.event.*;

public class SolveButton extends JButton implements ActionListener
{
	private final MazeSolver mazeSolver ;
	
	public SolveButton(MazeSolver mazeSolver)
	{
		super("Resoudre le labyrinthe") ; // Button's text
		
		this.mazeSolver = mazeSolver ;
		addActionListener(this);
		
	}

	public void notifyForUpdate() {
		//setEnabled(!mazeSolver.getMazeSolverModel().mazeSolved());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			MazeSolverModel mazeSolverModel = mazeSolver.getMazeSolverModel();
			if ((!mazeSolver.getMazeSolverModel().mazeSolved()) && (mazeSolverModel.getThereIsMaze()))
			try {
				mazeSolverModel.solveMaze();
			} catch (MazeReadingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
}