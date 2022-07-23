package ui;

import java.awt.*;
import javax.swing.* ;

public class ButtonsPanel extends JPanel {
	private final SolveButton solveButton ;
	private final CancelButton  cancelButton;
	
	public ButtonsPanel(MazeSolver mazesolver) {
		setLayout(new GridLayout(1,2)) ; // 1 row, 2 columns
		
		add(solveButton  = new  SolveButton(mazesolver)) ;
		add(cancelButton   = new  CancelButton(mazesolver)) ;
	}
	public void notifyForUpdate() {
	   solveButton.notifyForUpdate() ;
	   cancelButton.notifyForUpdate() ;
	}
}