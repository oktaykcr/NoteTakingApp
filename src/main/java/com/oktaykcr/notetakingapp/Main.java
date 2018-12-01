package com.oktaykcr.notetakingapp;

import com.oktaykcr.util.Library;

import javax.swing.*;


public class Main extends Library {
	public final static int mainWidth=314;
	public final static int mainHeight=691;
	static JFrame mainFrame;
	private JLayeredPane lp;
	
	//CLASS
	static Menu menu;
	static NewNotePanel nNotePanel;
	static NotePanel notePanel;
	
	static JDBCConnection database;
	
	private void init(){
		mainFrame=new JFrame();
		mainFrame.setResizable(false);
		lp=new JLayeredPane();
		new ResourceFactory();//images initializing
	}
	
	public Main(){
		init();
		mainFrame.setIconImage(ResourceFactory.icons[32].getImage()); // PROGRAM ICON
		createFrame(mainFrame, lp, null, "Note Taking App", mainWidth, mainHeight, true);
		
		//Class Init
		menu=new Menu(true);
		nNotePanel=new NewNotePanel(false);
		notePanel=new NotePanel(false);
		//PANELS
		lp.add(menu.getPanel(),new Integer(1));
		lp.add(nNotePanel.getPanel(),new Integer(2));
		lp.add(notePanel.getPanel(), new Integer(3));
		
		//LOADING DATA
		
		database=new JDBCConnection();
		nNotePanel.setBlocks(database.loadDatas("noteBlocks"));
		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}

