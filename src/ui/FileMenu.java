package ui ;

import javax.swing.* ;

public class FileMenu extends JMenu
{
   //...
   private final NewMazeMenuItem newMazeMenuItem;
   private final GenerateMazeMenuItem generateMazeMenuItem;
   private final QuitMenuItem quitMenuItem ;
   private final OpenFileMenuItem openFileMenuItem ;
   private final SaveFileAsMenuItem saveFileAsMenuItem;
   private final SaveFileMenuItem saveFileMenuItem;


   public FileMenu(MazeSolver mazeSolver)
   {
      super("Fichier") ; // Text of the menu

      // Create and add menu items
      //...
      
      add(newMazeMenuItem = new NewMazeMenuItem(mazeSolver));
      add(generateMazeMenuItem = new GenerateMazeMenuItem(mazeSolver));
      add(openFileMenuItem = new OpenFileMenuItem(mazeSolver)) ;
      add(saveFileMenuItem = new SaveFileMenuItem(mazeSolver));
      add(saveFileAsMenuItem = new SaveFileAsMenuItem(mazeSolver));
      add(quitMenuItem = new QuitMenuItem(mazeSolver)) ;
   }


public void notifyForUpdate() {
	saveFileMenuItem.notifyForUpdate();
	saveFileAsMenuItem.notifyForUpdate();
}

}