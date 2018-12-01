package com.oktaykcr.notetakingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Note implements ActionListener {
	private String note;
	private int symbolInfo=0;//0:thick 1:cross 2:exclamation
	
	private JPanel panel;
	private JLabel[] labels;//0:Background Image 1:Text Label 2:Symbol Image
	private JButton trashButton;
	
	public Note(String note,int symbolInfo,JPanel panel){
		this.note=note;
		this.symbolInfo=symbolInfo;
		this.panel=panel;
		
		labels=new JLabel[3];
		labels[0]=new JLabel();//BG
		labels[0].setSize(772, 48);
		labels[1]=new JLabel();//TEXT
		labels[2]=new JLabel();//SYMBOL
		
		addImgButton(4);
		
		addLabel(1, note);//TEXT
		
		labels[0].add(labels[1]);
		labels[0].add(labels[2]);
		labels[0].add(trashButton);
		panel.add(labels[0]);
		
		trashButton.setBounds(721, 9, 34, 32);
		labels[1].setBounds(40, 8, 685, 32);//TEXT
		
		//trashButton ANIMATION
		trashButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				trashButton.setIcon(ResourceFactory.icons[29]);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				trashButton.setIcon(ResourceFactory.icons[4]);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				trashButton.setIcon(ResourceFactory.icons[28]);
			}
		});
		
		
		labels[0].setIcon(ResourceFactory.icons[19]);
		switch(symbolInfo){
			case 0:
				labels[2].setIcon(null);//NO ICON
				break;
			case 1:
				labels[2].setBounds(0, 13, 34, 21);//THICK
				labels[2].setIcon(ResourceFactory.icons[16]);
				break;
			case 2:
				labels[2].setBounds(0, 13, 30, 21);//CROSS
				labels[2].setIcon(ResourceFactory.icons[17]);
				break;
			case 3:
				labels[2].setBounds(6, 13, 18, 21);//EXCLAMATION
				labels[2].setIcon(ResourceFactory.icons[18]);
				break;
		}
		
		labels[0].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				symbolChange();
			}
		});
		panel.revalidate();
	}
	
	private void addImgButton(int iconIndex){
		trashButton=new JButton(ResourceFactory.icons[iconIndex]);
		trashButton.addActionListener(this);
		trashButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		trashButton.setContentAreaFilled(false);
		trashButton.setOpaque(false);
		trashButton.setFocusPainted(false);
	}
	private void addLabel(int index,String text){
		labels[index].setText(text);
		labels[index].setForeground(new Color(255,255,255));
	}
	private void symbolChange(){
		if(Note.this.symbolInfo<3) Note.this.symbolInfo+=1;
		else Note.this.symbolInfo=0;
		
		switch(Note.this.symbolInfo){
		case 0:
			labels[2].setIcon(null);//NO ICON
			break;
		case 1:
			labels[2].setBounds(0, 13, 34, 21);//THICK
			labels[2].setIcon(ResourceFactory.icons[16]);
			break;
		case 2:
			labels[2].setBounds(0, 13, 30, 21);//CROSS
			labels[2].setIcon(ResourceFactory.icons[17]);
			break;
		case 3:
			labels[2].setBounds(6, 13, 18, 21);//EXCLAMATION
			labels[2].setIcon(ResourceFactory.icons[18]);
			break;
		}
		//UPDATE NOTES AFTER DELETE PROCESS
		String blockNotes="-_-";//database keeps notes in one variable
		for(int i=0;i<Main.notePanel.getCurrentBlock().getNotes().size();i++) {
			blockNotes+=Main.notePanel.getCurrentBlock().getNotes().get(i).getSymbolInfo()+"-"+Main.notePanel.getCurrentBlock().getNotes().get(i).getNote()+"-_-";
		}
		Main.database.updateNotes(Main.notePanel.getCurrentBlock().getTitle(), blockNotes);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(trashButton)){
			Main.notePanel.getCurrentBlock().getNotes().remove(this);
			panel.remove(labels[0]);
			panel.revalidate();
			panel.repaint();
			
			//UPDATE NOTES AFTER DELETE PROCESS
			String blockNotes="-_-";//database keeps notes in one variable
			for(int i=0;i<Main.notePanel.getCurrentBlock().getNotes().size();i++) {
				blockNotes+=Main.notePanel.getCurrentBlock().getNotes().get(i).getSymbolInfo()+"-"+Main.notePanel.getCurrentBlock().getNotes().get(i).getNote()+"-_-";
			}
			Main.database.updateNotes(Main.notePanel.getCurrentBlock().getTitle(), blockNotes);
		}
	}
	
	public String getNote(){
		return this.note;
	}
	public void setNote(String note){
		this.note=note;
	}
	public int getSymbolInfo(){
		return this.symbolInfo;
	}
	public void setSymbolInfo(int symbolInfo){
		this.symbolInfo=symbolInfo;
	}
}
