package ui;

import javax.swing.event.MouseInputAdapter;

import model.MazeSolverModel;

import java.awt.event.*;
import java.util.ArrayList;

public class MazePanelMouseListener extends MouseInputAdapter {
	private final MazeSolver mazeSolver;
	

	public MazePanelMouseListener(MazeSolver mazeSolver) {
		super();
		this.mazeSolver = mazeSolver;
	}



	@Override
	public final void mousePressed(final MouseEvent e) {
	    
	    MazeSolverModel mazeSolverModel = mazeSolver.getMazeSolverModel();
	    int x = e.getX();
	    int y = e.getY();
	    ArrayList<PaintedMBox> paintedBoxes = mazeSolverModel.getPaintedBoxes();
	    for(PaintedMBox paintedBox : paintedBoxes) {
	        if (paintedBox.isCursorOn(x,y)){
	        	paintedBox.changeType();
	        	
	        }
	    }
	}
}
