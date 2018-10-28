package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;


public class SudokuCizim extends JPanel {
	Color background;
	
	int boyut = 9;
	
	//thread sonuclari
	public SudokuCizim(boolean success , int selectx, int selecty, int [][] sonucMatris){
		setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
              
        for (int i = 0; i <boyut ; i++) {
            for (int j = 0; j < boyut; j++) {
                gbc.gridx = j;
                gbc.gridy = i;

                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(30, 30));
                if(i == selectx && j == selecty) {
                	if(success)
                		panel.setBackground(Color.GREEN);
                	else
                		panel.setBackground(Color.RED);
                }
                	
                
                Border border = null;                                             
                border = new CompoundBorder();
                                
                
                if(((j+1) % 3 == 0) && (i+1) % 3 == 0) {
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                }else if((i+1) % 3 == 0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                }else if((j+1) % 3 == 0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                }else {
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                
                //sayilar
                String numara = ""+ sonucMatris[i][j];
                
                panel.add(new JLabel(numara));
        
            	
                panel.setBorder(border);
                add(panel, gbc);
            }
        }	
		
	}
	
	//dosya yükledikten sonra baslangic
	public SudokuCizim(boolean fresh, int selectx, int selecty){
		setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
              
        for (int i = 0; i <boyut ; i++) {
            for (int j = 0; j < boyut; j++) {
                gbc.gridx = j;
                gbc.gridy = i;

                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(30, 30));
                if(i == selectx && j == selecty)
                	panel.setBackground(Color.RED);
		
                Border border = null;                                             
                border = new CompoundBorder();
                                
                
                if(((j+1) % 3 == 0) && (i+1) % 3 == 0) {
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                }else if((i+1) % 3 == 0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                }else if((j+1) % 3 == 0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                }else {
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                
                //sayilar
                String numara = "";
                if(fresh) {
                	numara = ""+ 0;
                }else {
                	numara=""+ Main.sudokuMatris[i][j];
                }
                
                panel.add(new JLabel(numara));          
            	
                panel.setBorder(border);
                add(panel, gbc);
            }
        }
        	
		
	}
	
	//adimlama
	public SudokuCizim(int selectx, int selecty, int [][] sonucMatris) {
		setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
              
        for (int i = 0; i <boyut ; i++) {
            for (int j = 0; j < boyut; j++) {
                gbc.gridx = j;
                gbc.gridy = i;

                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(30, 30));
                if(i == selectx && j == selecty) {
                	panel.setBackground(Color.MAGENTA);
                }
                	
                
                Border border = null;                                             
                border = new CompoundBorder();
                                
                
                if(((j+1) % 3 == 0) && (i+1) % 3 == 0) {
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
                }else if((i+1) % 3 == 0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
                }else if((j+1) % 3 == 0){
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
                }else {
                	border = BorderFactory.createCompoundBorder(border, BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                
                //sayilar
                String numara = ""+ sonucMatris[i][j];
                
                panel.add(new JLabel(numara));
        
            	
                panel.setBorder(border);
                add(panel, gbc);
            }
        }	
		
		
		
	}
	
}