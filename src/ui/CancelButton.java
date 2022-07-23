package ui;

import javax.swing.* ;

import maze.MazeReadingException;
import model.MazeSolverModel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.* ;

import maze.MazeReadingException;

import java.awt.event.*;


public class CancelButton extends JButton implements ActionListener
{
	private final MazeSolver mazeSolver ;
	
	public CancelButton(MazeSolver mazesolver)
	{
		super("Annuler") ; // Button's text
		
		this.mazeSolver = mazesolver ;
		addActionListener(this);
	}
	public void notifyForUpdate() 
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MazeSolverModel mazeSolverModel = mazeSolver.getMazeSolverModel();
		mazeSolverModel.setWentThroughFalse();
		mazeSolver.getMazeSolverModel().setMazeSolved(false);
	
}
}