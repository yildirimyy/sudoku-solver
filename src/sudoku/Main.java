package sudoku;

import java.awt.EventQueue;
import java.io.File;
import java.util.Vector;

public class Main {
	
	public static int[][] sudokuMatris;
	//public static String fileName = "sudoku.txt";
	
	public static String fileThread1 = "fileThread1.txt";
	public static String fileThread2 = "fileThread2.txt";
	public static String fileThread3 = "fileThread3.txt";
	
	public static boolean thread1Basarili = false;
	public static boolean thread2Basarili = false;
	public static boolean thread3Basarili = false;
	
	public static long thread1Time = 0;
	public static long thread2Time = 0;
	public static long thread3Time = 0;
	
	
	public static Vector<Konum> konumlar;
	public static Sudoku sudoku1;
	public static Sudoku sudoku2;
	public static Sudoku sudoku3;
	
	public static Vector<Konum> thread1Adimlar;
	public static Vector<Konum> thread2Adimlar;
	public static Vector<Konum> thread3Adimlar;
	
	
	public static void eski_cozumleri_temizle() {
		//eski cozumleri temizle
		try {
			new File(fileThread1).delete();
			new File(fileThread1).createNewFile();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			new File(fileThread2).delete();
			new File(fileThread2).createNewFile();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			new File(fileThread3).delete();
			new File(fileThread3).createNewFile();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
	
	public static void threadleri_baslat(){
		
		sudoku1 = new Sudoku(konumlar.get(0).getX(),konumlar.get(0).getY(), sudokuMatris);
		sudoku2 = new Sudoku(konumlar.get(1).getX(),konumlar.get(1).getY(), sudokuMatris);	
		sudoku3 = new Sudoku(konumlar.get(2).getX(),konumlar.get(2).getY(), sudokuMatris);
		
		ThreadGroup threadGroup = new ThreadGroup("parentThread");
		
		//3 thread
		Thread threadSudoku1 = new Thread(threadGroup, new Runnable() {
			
			public void run() {
				try {				
					Thread.sleep(100);
					long runTime = System.currentTimeMillis();
					
					//coz methodunun cagirilmasi
					if (new Coz().sudoku_coz(sudoku1.getX(), sudoku1.getY(), sudoku1, 1)) {

						threadGroup.interrupt();
						thread1Basarili = Coz.kontrol(sudoku1);
						thread1Time = System.currentTimeMillis() - runTime;
						return;
					} else {
						//System.out.println("sonuc yok");
						thread1Basarili = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
					thread1Basarili = false;
					return;
				}

			}
		});
		
		
		Thread threadSudoku2 = new Thread(threadGroup, new Runnable() {
			
			public void run() {

				try {
					Thread.sleep(100);
					long runTime = System.currentTimeMillis();
					if (new Coz().sudoku_coz(sudoku2.getX(), sudoku2.getY(), sudoku2, 2)) {

						threadGroup.interrupt();
						thread2Basarili = Coz.kontrol(sudoku2);
						thread2Time = System.currentTimeMillis() - runTime;
						return;
					} else {
						thread2Basarili = false;
						//System.out.println("sonuc yok");
					}
				} catch (Exception e) {
					// TODO: handle exception
					thread2Basarili = false;
					return;
				}

			}
		});
		
		
		Thread threadSudoku3 = new Thread(threadGroup, new Runnable() {

			public void run() {

				try {
					Thread.sleep(100);
					long runTime = System.currentTimeMillis();
					if (new Coz().sudoku_coz(sudoku3.getX(), sudoku3.getY(), sudoku3, 3)) {

						threadGroup.interrupt();
						thread3Basarili = Coz.kontrol(sudoku3);
						thread3Time = System.currentTimeMillis() - runTime;
						return;
					} else {
						//System.out.println("sonuc yok");
						thread3Basarili = false;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					thread3Basarili = false;
					return;
				}

			}			
			
		});
		
		threadSudoku1.start();
		threadSudoku2.start();
		threadSudoku3.start();
		
		
		while (threadSudoku1.isAlive() && threadSudoku3.isAlive() && threadSudoku3.isAlive()) {
			//threadlerden birinin bitmesini bekle
		}
		
		//her ihtimale karsi threadleri tek tek durdurma
		threadSudoku1.interrupt();
		threadSudoku2.interrupt();
		threadSudoku3.interrupt();
		
		
		//adimlari thread dosyalarına yazma
		Dosya.ThreadAdimlariniYerlestir();
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
	
		sudokuMatris = new int[9][9];
		
		thread1Adimlar = new Vector<>();
		thread2Adimlar = new Vector<>();
		thread3Adimlar = new Vector<>();
		
		eski_cozumleri_temizle();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					GuiMain frame = new GuiMain();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}
	
	
}
