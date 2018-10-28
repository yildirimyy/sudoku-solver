package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Konum {
	private int x;
	private int y;
	private int val;
	
	public Konum() {
		// TODO Auto-generated constructor stub
	}
	public Konum(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
	public Konum(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//3 farklı x, y ikilisi üret 
	public Vector<Konum> rasgele_konum(){
		
		//random kullanılmamasının sebebi
		//atanan konumun listeden cikarilmasi
		
		int x1 = 0, x2 = 0, x3 = 0;
		int y1 = 0, y2 = 0, y3 = 0;
		
		ArrayList<Integer>  konumlarX = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			konumlarX.add(i);
		}
		
		ArrayList<Integer>  konumlarY = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			konumlarY.add(i);
		}
		
		//x
		//x leri karıstır x1 ata atadigini sil
		Collections.shuffle(konumlarX);
		x1 = konumlarX.get(0).intValue();
		konumlarX.remove(0);
		
		//x leri karıstır x2 ata atadigini sil
		Collections.shuffle(konumlarX);
		x2 = konumlarX.get(0).intValue();
		konumlarX.remove(0);

		//x leri karıstır x3 ata atadigini sil
		Collections.shuffle(konumlarX);
		x3 = konumlarX.get(0).intValue();
		konumlarX.remove(0);
		
		
		//y
		//y leri karıstır y1 ata atadigini sil
		Collections.shuffle(konumlarY);
		y1 = konumlarY.get(0).intValue();
		konumlarY.remove(0);

		//y leri karıstır y2 ata atadigini sil
		Collections.shuffle(konumlarY);
		y2 = konumlarY.get(0).intValue();
		konumlarY.remove(0);

		//y leri karıstır y3 ata atadigini sil
		Collections.shuffle(konumlarY);
		y3 = konumlarY.get(0).intValue();
		konumlarY.remove(0);

		//olusan rastgele konumları ata ve dondur
		Vector<Konum> konumlar = new Vector<>();
		konumlar.add(new Konum(x1, y1));
		konumlar.add(new Konum(x2, y2));
		konumlar.add(new Konum(x3, y3));
		
		return konumlar;
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getVal() {
		return val;
	}
	
}