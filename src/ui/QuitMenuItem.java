package ui ;

import javax.swing.* ;
import java.awt.event.*;

public class QuitMenuItem extends JMenuItem implements ActionListener
{
   private final MazeSolver mazeSolver ;

   public QuitMenuItem(MazeSolver mazeSolver)
   {
      super("Quitter") ; // Text of menu item

      this.mazeSolver = mazeSolver ;
      addActionListener(this);
   }

@Override
	public void actionPerformed(ActionEvent e) {
		if(mazeSolver.getMazeSolverModel().quitMazeRequest()) System.exit(0);
	}
}