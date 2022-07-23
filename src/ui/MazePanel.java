package ui;

import javax.swing.* ;

import maze.*;

import java.awt.* ;
import java.util.*;

public class MazePanel extends JPanel
{
	private final MazeSolver mazeSolver ;
	private static int mazeSize = 800;
	
	private MazePanelMouseListener mListener;
	
	public static int getmazeSize() {
		return mazeSize;
	}
	
	
	public MazePanel(MazeSolver mazeSolver) {
		this.mazeSolver = mazeSolver ;
		mListener = new MazePanelMouseListener(mazeSolver);
		addMouseListener(mListener);
		setLayout(new BorderLayout()) ;
		setPreferredSize(new Dimension(mazeSize,mazeSize)) ;
		setBackground(Color.BLACK) ; 
		
	}
	
	public void notifyForUpdate() {
		//On pourrait faire un dijonction de cas entre tout redessiner et redessiner uniquement une case
		setPreferredSize(new Dimension(mazeSize,mazeSize)) ;
		repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		if(mazeSolver.getMaze() != null) {
		super.paintComponent(g);
	    mazeSize = getWidth();
	    int shape = mazeSolver.getMaze().getSize();
	    ArrayList<ArrayList<MBox>> boxes = mazeSolver.getMaze().getBoxes();
	    ArrayList<PaintedMBox> paintedBoxes = new ArrayList<PaintedMBox>();
	    int i = 0;
	    for (ArrayList<MBox> list : boxes) {
	        int j = 0;
	    	for (MBox box : list) {
	    		PaintedMBox paintedBox = new PaintedMBox(mazeSolver,box,i,j,shape,shape,mazeSize);
	    		paintedBox.paint(g);
	    		paintedBoxes.add(paintedBox);
	    		j++;
	    	}
	    	i++;
	    }
	    mazeSolver.getMazeSolverModel().setPaintedBoxes(paintedBoxes);
	    }
		
	}
}