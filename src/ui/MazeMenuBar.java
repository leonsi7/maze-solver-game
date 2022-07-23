
package ui;

import javax.swing.* ;

public class MazeMenuBar extends JMenuBar
{

	private final FileMenu fileMenu ;
	private final HelpMenu helpMenu;
	
	public MazeMenuBar(MazeSolver mazeSolver)
	{
		super() ;
		
		// Create and add menus
		add(fileMenu = new FileMenu(mazeSolver)) ;
		add(helpMenu = new HelpMenu(mazeSolver));

	}

	public void notifyForUpdate() {
		fileMenu.notifyForUpdate();
		
	}
	

	
}