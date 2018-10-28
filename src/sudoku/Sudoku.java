package sudoku;

public class Sudoku {
	
	private final int boyut = 9;
	int x,y;
	//dosyadan okunan matris
	int[][] matris;
	
	//islemler sonucu olusan matris
	int[][] sonucmatris;
	
	
	public Sudoku() {
		// TODO Auto-generated constructor stub
	}
	
	//matris ile sudoku olusturma
	public Sudoku(int [][] matris) {
		this.matris = new int[boyut][boyut];
		this.sonucmatris = new int[boyut][boyut];
		
		for (int i = 0; i < matris.length; i++) {
			for (int j = 0; j < matris.length; j++) {
				this.matris[i][j] = matris[i][j];
				this.sonucmatris[i][j] = matris[i][j];
			}
		}
		
	}
	
	public Sudoku(int x, int y, int [][] matris) {
		this.x = x;
		this.y = y;
		this.matris = new int[boyut][boyut];
		this.sonucmatris = new int[boyut][boyut];
		
		for (int i = 0; i < matris.length; i++) {
			for (int j = 0; j < matris.length; j++) {
				this.matris[i][j] = matris[i][j];
				this.sonucmatris[i][j] = matris[i][j];
			}
		}
		
	}
	
	public boolean sayi_kontrol(int x, int y, int sayi) {
		return this.sonucmatris[x][y] == sayi;
	}
	
	public int getBoyut() {
		return boyut;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int[][] getMatris() {
		return matris;
	}

	public void setMatris(int[][] matris) {
		this.matris = matris;
	}

	public int[][] getSonucmatris() {
		return sonucmatris;
	}

	public void setSonucmatris(int[][] sonucmatris) {
		this.sonucmatris = sonucmatris;
	}
	
	class Kutu{
		int x;
		int y;
		
		public Kutu() {
			// TODO Auto-generated constructor stub
		}
		
		public Kutu(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			setKutuKonum();
		}
		
		public void setKutuKonum() {
			this.x = (this.x / 3) * 3;
			this.y = (this.y / 3) * 3;
		}
		
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
				
				
	}
	
}
