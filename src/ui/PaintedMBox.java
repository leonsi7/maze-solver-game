package ui;
import maze.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.* ;
import java.awt.event.*;
import model.*;
import java.util.ArrayList;

public class PaintedMBox extends JPanel {
	
	//
	private MazePanelMouseListener paintedMBoxMouseListener;
	//
    
    private MazeSolver mazeSolver;
	
	private MBox box;
	private int coordX;
	private int coordY;
	
	private static int shapeX;
	private static int shapeY;
	private int mazeShape;
	
	public PaintedMBox(MazeSolver mazeSolver,MBox box, int coordX, int coordY, int shapeX, int shapeY, int mazeShape) {
		super();
		
		
		this.box = box;
		this.coordX=coordX;
		this.coordY=coordY;
		PaintedMBox.shapeX=shapeX;
		PaintedMBox.shapeY=shapeY;
		this.mazeShape=mazeShape;
		this.mazeSolver = mazeSolver;
	}
	
    public void paint(Graphics g) {
        super.paintComponent(g);
        switch(this.box.getLabel()) {
        case "D" : {g.setColor(Color.RED);
        }break;
        case "E" : {g.setColor(Color.WHITE);
        if ((this.box.getWentThrough()==true)&&(mazeSolver.getMazeSolverModel().mazeSolved())) g.setColor(Color.YELLOW);
        else  g.setColor(Color.WHITE);
        }break;
        case "A" : {g.setColor(Color.GREEN);
        }break;
        case "W" : {g.setColor(Color.BLACK);
        }break;
        default: {
        	g.setColor(Color.GRAY);
        }break;
        }
        
        g.fillRect((int) mazeShape*coordY/(shapeY), (int) mazeShape*coordX/(shapeX), ((int)mazeShape/(shapeY))-1,((int)mazeShape/(shapeX))-1);
    }
    public MBox getBox() {
    	return box;
    }    

    public void changeType() {
    	MazeSolverModel mazeSolverModel = this.mazeSolver.getMazeSolverModel();
    	mazeSolverModel.setWentThroughFalse();
        switch (this.box.getLabel()) {
            case "E" : {
                Maze maze = mazeSolverModel.getMaze();
                String choose = mazeSolverModel.whatTypeOfBoxChoose();
                switch(choose){
                	case "A":{maze.setMBox(coordX, coordY, new ABox(coordX,coordY));} break;
                	case "D":{maze.setMBox(coordX, coordY, new DBox(coordX,coordY));} break;
                	default :{maze.setMBox(coordX, coordY, new WBox(coordX,coordY));} break;
                }
                mazeSolverModel.setBooleanAD("EorW");
            }break;
            case "W" :{
            	Maze maze = mazeSolverModel.getMaze();
                String choose = mazeSolverModel.whatTypeOfBoxChoose();
                switch(choose){
                	case "A":{maze.setMBox(coordX, coordY, new ABox(coordX,coordY));} break;
                	case "D":{maze.setMBox(coordX, coordY, new DBox(coordX,coordY));} break;
                	default :{maze.setMBox(coordX, coordY, new EBox(coordX,coordY));} break;
                }
                mazeSolverModel.setBooleanAD("EorW");
            }break;
            case "A":{
            	Maze maze = mazeSolverModel.getMaze();
                maze.setMBox(coordX, coordY, new EBox(coordX,coordY));
                mazeSolverModel.setBooleanAD("A");
            }break;
            case "D":{
            	Maze maze = mazeSolverModel.getMaze();
                maze.setMBox(coordX, coordY, new EBox(coordX,coordY));
                mazeSolverModel.setBooleanAD("D");
            }break;
            default : break;
        }
        mazeSolverModel.stateChanges();
        
    }
    
    public static int[] getShape() {
    	return new int[]{shapeX, shapeY};
    }
    
    public int[] getCoord() {
    	return new int[]{this.coordX, this.coordY};
    }

	public boolean isCursorOn(int x, int y) {
		if ((x> mazeShape*coordY/(shapeY)) && (x<= mazeShape*coordY/(shapeY) + mazeShape/(shapeY)-1)
				&& (y>mazeShape*coordX/(shapeX)) && (y<= mazeShape*coordX/(shapeX) + mazeShape/(shapeX)-1)) {
			return true;
		}
		return false;
	}

    
}