package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class BeginTutorialMenuItem  extends JMenuItem implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	private final MazeSolver mazeSolver;



	public BeginTutorialMenuItem (final MazeSolver mazeSolver) {
		super("Comment ça marche ?");
		
		 this.mazeSolver = mazeSolver ;
	     addActionListener(this);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mazeSolver.getMazeSolverModel().openHelpHowToUse();
		
	}

}
