package com.oktaykcr.notetakingapp;


import com.oktaykcr.util.Library;

import javax.swing.*;
import java.awt.*;

public class ResourceFactory extends Library {
	public enum res{
		backgroundimage,
		newNoteButton,
		newNoteBG,
		nNoteEditButton,
		filterField,
		editButton,
		trashButton,
		trashButton2,
		createButton,
		addButton,
		backButton,
		noteBlock,
		noteBlock2,
		noteBlock3,
		noteBlock4,
		noteBlock5,
		notePanelBG,
		noteBG,
		sym_thick,
		sym_cross,
		sym_exclamation,
		click_newNoteButton,
		hover_newNoteButton,
		click_backButton,
		hover_backButton,
		click_createButton,
		hover_createButton,
		click_editButton,
		hover_editButton,
		click_trashButton,
		hover_trashButton,
		click_addButton,
		hover_addButton,
		programicon
	}
	public static Font font=new Font("Consolas",Font.PLAIN,35);
	public static ImageIcon[] icons;
	private int iconsSize=33;
	
	public ResourceFactory(){
		icons=new ImageIcon[iconsSize];
		//IMAGE INITIALIZE
		imageIconInit(icons, 0, res.backgroundimage.toString()+".png");
		imageIconInit(icons, 1, res.newNoteButton.toString()+".png");
		imageIconInit(icons, 2, res.filterField.toString()+".png");
		imageIconInit(icons, 3, res.editButton.toString()+".png");
		imageIconInit(icons, 4, res.trashButton.toString()+".png");
		imageIconInit(icons, 5, res.noteBlock.toString()+".png");
		imageIconInit(icons, 6, res.noteBlock2.toString()+".png");
		imageIconInit(icons, 7, res.noteBlock3.toString()+".png");
		imageIconInit(icons, 8, res.noteBlock4.toString()+".png");
		imageIconInit(icons, 9, res.noteBlock5.toString()+".png");
		imageIconInit(icons, 10, res.newNoteBG.toString()+".png");
		imageIconInit(icons, 11, res.createButton.toString()+".png");
		imageIconInit(icons, 12, res.nNoteEditButton.toString()+".png");
		imageIconInit(icons, 13, res.backButton.toString()+".png");
		imageIconInit(icons, 14, res.notePanelBG.toString()+".png");
		imageIconInit(icons, 15, res.addButton.toString()+".png");
		imageIconInit(icons, 16, res.sym_thick.toString()+".png");
		imageIconInit(icons, 17, res.sym_cross.toString()+".png");
		imageIconInit(icons, 18, res.sym_exclamation.toString()+".png");
		imageIconInit(icons, 19, res.noteBG.toString()+".png");
		imageIconInit(icons, 20, res.click_newNoteButton+".png");
		imageIconInit(icons, 21, res.hover_newNoteButton+".png");
		imageIconInit(icons, 22, res.click_backButton+".png");
		imageIconInit(icons, 23, res.hover_backButton+".png");
		imageIconInit(icons, 24, res.click_createButton+".png");
		imageIconInit(icons, 25, res.hover_createButton+".png");
		imageIconInit(icons, 26, res.click_editButton+".png");
		imageIconInit(icons, 27, res.hover_editButton+".png");
		imageIconInit(icons, 28, res.click_trashButton+".png");
		imageIconInit(icons, 29, res.hover_trashButton+".png");
		imageIconInit(icons, 30, res.click_addButton+".png");
		imageIconInit(icons, 31, res.hover_addButton+".png");
		imageIconInit(icons, 32, res.programicon+".png");
	}
	
}
