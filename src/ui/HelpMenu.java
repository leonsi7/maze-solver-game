package ui;

import javax.swing.JMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpMenu extends JMenu  {

	private static final long serialVersionUID = 1L;
	private final BeginTutorialMenuItem beginTutorialMenuItem;
	private final HelpEditorMenuItem helpEditorMenuItem;
	private final CreditsMenuItem creditsMenuItem;
	private MazeSolver mazeSolver;

	public HelpMenu(MazeSolver mazeSolver) {
		super("Aide");
		this.mazeSolver = mazeSolver;
		add(beginTutorialMenuItem = new BeginTutorialMenuItem(mazeSolver));
		add(helpEditorMenuItem = new HelpEditorMenuItem(mazeSolver));
		add(creditsMenuItem = new CreditsMenuItem(mazeSolver));
	}
	

}
