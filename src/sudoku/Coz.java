package sudoku;

import sudoku.Sudoku.Kutu;

public class Coz {
	
	//sudoku icerisinde cozum sırasında 0 kalıp kalmadıgının kontrolü
	boolean success_sudoku_coz(Sudoku sudoku) {
		
		for (int i = 0; i < sudoku.getBoyut(); i++) {
			for (int j = 0; j < sudoku.getBoyut(); j++) {
				if(sudoku.getSonucmatris()[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//threadler icinden cagirilan coz fonksiyonu threadId e göre adimlara ekle
	synchronized boolean sudoku_coz(int x, int y, Sudoku sudoku, int threadId) throws InterruptedException {

		if(Thread.currentThread().isInterrupted())
			return false;
		
		//matrisin boyutu asildiysa ve cozum bitmediyse bastan basla
		if (x >= 9) {
			x = 0;
			if (++y >= 9) {
				if(success_sudoku_coz(sudoku)) {
					return true;
				}else {
					y = 0;
					return sudoku_coz(x, y, sudoku, threadId);
				}
				
			}
		}
		
		//0 degilse satir atla
		if(sudoku.getSonucmatris()[x][y] != 0) 
			return sudoku_coz(x+1, y, sudoku, threadId);
		
		//sırayla sayıları dene
		for (int sayi = 1; sayi <= 9; ++sayi) {
			
			if(Thread.currentThread().isInterrupted())
				return false;
			
			//sayiların kontrolü
			boolean satirkontrol = satirlari_kontrol_et(sudoku, sayi, y);
			boolean sutunkontrol = sutunlari_kontrol_et(sudoku, sayi, x);
			boolean kutukontrol = alani_kontrol_et(sudoku, sayi, x, y);
			
			
			if(Thread.currentThread().isInterrupted())
				return false;
			
			//id e göre adimlara timer icin ekleme
			if(threadId == 1) {
				Main.thread1Adimlar.add(new Konum(x, y, sayi));
			}else if(threadId == 2) {
				Main.thread2Adimlar.add(new Konum(x, y, sayi));
			}else if(threadId == 3) {
				Main.thread3Adimlar.add(new Konum(x, y, sayi));
			}
			
			//sayi yoksa sonuc matrise ekle
			if (satirkontrol && sutunkontrol && kutukontrol) {
				sudoku.sonucmatris[x][y] = sayi;				
				
				if (sudoku_coz(x + 1, y, sudoku, threadId))
					return true;
			}
		
		
		}

		//id e göre adimlara timer icin ekleme
		if(threadId == 1) {
			Main.thread1Adimlar.add(new Konum(x, y, 0));
		}else if(threadId == 2) {
			Main.thread2Adimlar.add(new Konum(x, y, 0));
		}else if(threadId == 3) {
			Main.thread3Adimlar.add(new Konum(x, y, 0));
		}
		
		//sayi varsa 0 yaz geri dön
		sudoku.getSonucmatris()[x][y] = 0;
		return false;
	}
	
	//satirlar atlayarak sutun sayı kontrolü
	synchronized static boolean satirlari_kontrol_et(Sudoku sudoku, int sayi, int y) {		
		for (int x = 0; x < sudoku.getBoyut(); ++x)
			if (sudoku.sayi_kontrol(x, y, sayi))
				return false;
		
		return true;
	}
	
	//sutunlar atlayarak satir sayı kontrolü
	synchronized static boolean sutunlari_kontrol_et(Sudoku sudoku, int sayi, int x) {
		for (int y = 0; y < sudoku.getBoyut(); ++y)
			if (sudoku.sayi_kontrol(x, y, sayi))
				return false;
		
		return true;
	}
	
	//kutunun kontrol edilmesi
	synchronized static boolean alani_kontrol_et(Sudoku sudoku, int sayi, int x, int y) {
		
		//kutunun baslangıc noktasının belirlenmesi
		Kutu kutu = sudoku.new Kutu(x,y);
		
		for (int i = 0; i < sudoku.getBoyut()/3; ++i) // box
			for (int j = 0; j < sudoku.getBoyut()/3; ++j)
				if (sayi == sudoku.getSonucmatris()[kutu.getX() + i][kutu.getY() + j])
					return false;
		
		return true;
	}
	
	//bitis sonrası dogru cozum yapılmış mı kontrolü
	static boolean kontrol(Sudoku sudoku) {
		
		for (int x = 0; x < sudoku.sonucmatris.length; x++) {
			for (int y = 0; y < sudoku.sonucmatris.length; y++) {
				
				int sayi = sudoku.sonucmatris[x][y];
				int sayac = 0;
				
				//sutun
				for (int j = 0; j < sudoku.getBoyut(); j++) {
					if(sudoku.sayi_kontrol(x, j, sayi)) {
						sayac++;
					}
				}
				
				if(sayac > 1)
					return false;
				
				sayac = 0;
				//satir
				for (int i = 0; i < sudoku.getBoyut(); i++) {
					if(sudoku.sayi_kontrol(i, y, sayi)) {
						sayac++;
					}
				}
				
				if(sayac > 1)
					return false;
				
				sayac = 0;
				//kutu
				Kutu kutu = sudoku.new Kutu(x,y);
				for (int i = 0; i < sudoku.getBoyut()/3; i++) {
					for (int j = 0; j < sudoku.getBoyut()/3; j++) {
						if (sayi == sudoku.getSonucmatris()[kutu.getX() + i][kutu.getY() + j]) {
							sayac++;
						}
					}
				}
				
				if(sayac > 1)
					return false;
								
			}
		}		
		
		return true;
	}
	
	
	
	

}
