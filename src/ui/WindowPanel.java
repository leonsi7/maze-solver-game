package ui;

import javax.swing.* ;
import java.awt.*;

public class WindowPanel extends JPanel
{
	
	private final MazePanel mazePanel;
    private final ButtonsPanel buttonsPanel ;
    
	public WindowPanel(MazeSolver mazeSolver)
	{
		setLayout(new BorderLayout()) ;
		add(mazePanel = new MazePanel(mazeSolver), BorderLayout.CENTER) ;
		add(buttonsPanel = new ButtonsPanel (mazeSolver), BorderLayout.SOUTH) ;
	}
	public void notifyForUpdate() 
	{
	   mazePanel.notifyForUpdate() ;
	   buttonsPanel.notifyForUpdate() ;
	}

	
}
