package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Dosya {
	
	static File file;
	
	//secilen sudoku dosyasının okunması
	public static void DosyaOku(){
        
        try {
            Scanner scanner = new Scanner(file);
            
            int i = 0;
            while(scanner.hasNextLine()){
                
            	//satir oku
                String satir= scanner.nextLine();
                
                int j = 0;
                for (char c: satir.toCharArray()) {
                	String s = Character.toString(c);
                	if(tryParseInt(s)) {
                		Main.sudokuMatris[i][j] = Integer.parseInt(s);
                	}else {
                		Main.sudokuMatris[i][j] = 0;
                	}
                	j++;
                }
                
               i++;
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            // TODO zAuto-generated catch block
            e.printStackTrace();
            System.out.println("Dosya Yok..");
            System.exit(1);
            
        }
        
	}
	
	//grafik arayuz olmayınca eski kullanılan
	public static void DosyaOku(String filename){
        
        try {
        	file = new File(filename); 
            Scanner scanner = new Scanner(file);
            
            int i = 0;
            while(scanner.hasNextLine()){
                
            	//satir oku
                String satir= scanner.nextLine();
                
                int j = 0;
                for (char c: satir.toCharArray()) {
                	String s = Character.toString(c);
                	if(tryParseInt(s)) {
                		Main.sudokuMatris[i][j] = Integer.parseInt(s);
                	}else {
                		Main.sudokuMatris[i][j] = 0;
                	}
                	j++;
                }
                
               i++;
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            // TODO zAuto-generated catch block
            e.printStackTrace();
            System.out.println("Dosya Yok..");
            System.exit(1);
            
        }
        
	}
	
	//sudoku dosyası okunurken * kontrolü
	static boolean tryParseInt(String sayi) {  
	     try {  
	         Integer.parseInt(sayi);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	//thread adimlarının 3 thread ile 3 dosyaya yazılması
	public static void ThreadAdimlariniYerlestir() {	
        try {

			Thread file1th = new Thread(new Runnable() {

				public void run() {
					File file1 = new File(Main.fileThread1);
					String metin = "";
		            try {
						if(!file1.exists()){	
							file1.createNewFile();
			            }
			            FileOutputStream fos1 = new FileOutputStream(file1 , true);
			            
			            for (Konum item : Main.thread1Adimlar) {
							metin = item.getX() + "," + item.getY() + "," + item.getVal() + "\n";

				            fos1.write(metin.getBytes());
						}
			           		            	
			            fos1.flush();
			            fos1.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			});

			Thread file2th = new Thread(new Runnable() {

				public void run() {
					File file2 = new File(Main.fileThread2);
					String metin = "";
		            try {
						if(!file2.exists()){	
							file2.createNewFile();
			            }
			            FileOutputStream fos2 = new FileOutputStream(file2 , true);
			            
			            for (Konum item : Main.thread2Adimlar) {
							metin = item.getX() + "," + item.getY() + "," + item.getVal() + "\n";

				            fos2.write(metin.getBytes());
						}
			           		            	
			            fos2.flush();
			            fos2.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			});

			Thread file3th = new Thread(new Runnable() {

				public void run() {
					File file3 = new File(Main.fileThread3);
					String metin = "";
		            try {
						if(!file3.exists()){	
							file3.createNewFile();
			            }
			            FileOutputStream fos3 = new FileOutputStream(file3 , true);
			            
			            for (Konum item : Main.thread3Adimlar) {
							metin = item.getX() + "," + item.getY() + "," + item.getVal() + "\n";

				            fos3.write(metin.getBytes());
						}
			           		            	
			            fos3.flush();
			            fos3.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			});
			
			file1th.start();
			file2th.start();
			file3th.start();
			
			file1th.join();
			file2th.join();
			file3th.join();
			         
        } catch (Exception e) {
            // TODO zAuto-generated catch block
            e.printStackTrace();
            System.out.println("Dosya Yok..");
            System.exit(1);
            
        }
		
		
	}
}
