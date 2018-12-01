package com.oktaykcr.notetakingapp;


import com.oktaykcr.util.Library;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends Library implements ActionListener{
	private JPanel panel;
	
	//Note Block Panel
	private JPanel noteBlockPanel;
	private JScrollPane sp;
	private NoteBlock block;
	
	private JButton[] buttons; private int buttonsSize=1;//New Note Button
	private JLabel[] labels;private int labelSize=2;//0:bg 1:filter bg
	private JTextField[] fields;//FILTER
	
	public void init(){
		//MENU PANEL
		panel=new JPanel();
		panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
		panel.setLayout(null);
		
		//NOTE PANEL
		noteBlockPanel=new JPanel();
		noteBlockPanel.setLayout(new BoxLayout(noteBlockPanel, BoxLayout.Y_AXIS));
		noteBlockPanel.setVisible(true);
		noteBlockPanel.setOpaque(false);
		//JScrollPane
		sp=new JScrollPane(noteBlockPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(15, 74, 288, 558);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setBorder(BorderFactory.createEmptyBorder());
		//ScrollBar
		sp.getVerticalScrollBar().setPreferredSize(new Dimension(0, Integer.MAX_VALUE));//invisible scrollbar
		sp.getVerticalScrollBar().setUnitIncrement(5);//scroll speed
		
		panel.add(sp);
		
		//ARRAYS
		buttons=new JButton[buttonsSize];
		labels=new JLabel[labelSize];
		fields=new JTextField[1];
	}
	
	public Menu(boolean visibility){
		init();
		panel.setVisible(visibility);
		
		addTextField(panel, fields, 0, 142, 19, 128, 32);//FILTER TEXT FIELD
		/*fields[0].setText("F I L T E R");//FILTER DEFAULT TEXT
		fields[0].addMouseListener(new MouseAdapter() {//to clear filter text
			 @Override
	            public void mouseClicked(MouseEvent e){
	                fields[0].setText("");
	            }
		});*/
		fields[0].getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateFilter();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateFilter();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateFilter();
			}
		});
		addImgButton(panel, buttons, 0, ResourceFactory.icons, 1, this, 19, 19, 117, 42);//NEW NOTE BUTTON
		//newNoteButton ANIMATIONS
		buttons[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				buttons[0].setIcon(ResourceFactory.icons[21]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				buttons[0].setIcon(ResourceFactory.icons[1]);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				buttons[0].setIcon(ResourceFactory.icons[20]);
			}
		});
		addImgLabel(panel, labels, 1, ResourceFactory.icons, 2, 135, 19, 164, 42);//FILTER BG
		addImgLabel(panel, labels, 0, ResourceFactory.icons, 0, 0, 0, 308, 662);//BG
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttons[0])){//new Note Button
			panel.setVisible(false);
			Main.nNotePanel.getPanel().setVisible(true);
		}
		
	}
	public void updateFilter(){
		String text=fields[0].getText().toLowerCase();
		if(text.isEmpty()){
			noteBlockPanel.removeAll();
			noteBlockPanel.revalidate();
			noteBlockPanel.repaint();
			ArrayList<NoteBlock> tempBlocks=new ArrayList<NoteBlock>(Main.nNotePanel.getBlocks());
			Main.nNotePanel.getBlocks().clear();
			for(int i=0;i<tempBlocks.size();i++){
				Main.nNotePanel.getBlocks().add(new NoteBlock(tempBlocks.get(i).getTitle(), tempBlocks.get(i).getDescription(), tempBlocks.get(i).getColor(), tempBlocks.get(i).getNotes(), noteBlockPanel));
			}
		}
		else{
			noteBlockPanel.removeAll();
			noteBlockPanel.revalidate();
			noteBlockPanel.repaint();
			for(int i=0;i<Main.nNotePanel.getBlocks().size();i++){
				NoteBlock tempBlock=Main.nNotePanel.getBlocks().get(i);
				if(tempBlock.getTitle().length()>=text.length())
					if(text.equals(tempBlock.getTitle().toLowerCase().substring(0, text.length()))){
						Main.nNotePanel.getBlocks().set(i, new NoteBlock(tempBlock.getTitle(),tempBlock.getDescription(),tempBlock.getColor(),tempBlock.getNotes(), noteBlockPanel));
					}
			}
		}
	}
	
	public JPanel getPanel(){
		return this.panel;
	}
	
	public JPanel getNoteBlockPanel(){
		return this.noteBlockPanel;
	}
}
