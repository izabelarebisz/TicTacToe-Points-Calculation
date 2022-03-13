package javaapplication1;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.*;

public class JavaApplication1 {
    
    static int sprawdz(char tab[], char znak){ //sprawdza tablicę 5-elementową: wiersz, kolumnę lub diagonalę
        String napis = new String(tab);        
        for (int i=0;i<4;i++){
            String wzorzec = ""; 
            for (int j=0;j<5-i;j++){
                wzorzec += znak;  
            }  
            Pattern p = Pattern.compile(wzorzec);
            Matcher m = p.matcher(napis);
                if(m.find())
                {                   
                    switch(i){
                        case 0:
                            return 15;
                        case 1:
                            return 7;
                        case 2:
                            return 3;
                        case 3:  
                            if (napis.equals("xxoxx") || napis.equals("ooxoo")) return 2;  
                            return 1;
                        default:
                            return 0;
                    }            
                } 
        } return 0;
    }
    
    
    static int oblicz(char tab[][], char znak){ //wycina tablice 5-elementowe z macierzy    
        int wynik = 0;
        char[] a = new char[5];
        
        //WIERSZE
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
            a[j] = tab[i][j];
            } wynik += sprawdz(a,znak);  
        }
        
        //KOLUMNY
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
            a[j] = tab[j][i];
            } wynik += sprawdz(a,znak); 
        }
               
        //DIAGONALE       
        for(int k=0;k<5;k++)
            a[k]=' ';
        
        for(int j=-5;j<5;j++)
        {
        for (int i=0;i<5;i++){
            if(i+j<5 && i+j>-1)
            a[i] = tab[i+j][i];
        }
        wynik+=sprawdz(a,znak);
        for(int k=0;k<5;k++)
            a[k]=' ';
        }
        //============================
        for(int k=0;k<5;k++)
            a[k]=' ';
        
        for(int j=-5;j<5;j++)
        {
        for (int i=0;i<5;i++){
            if(i+j<5 && i+j>-1)
            a[i] = tab[i+j][4-i];
        }
        wynik+=sprawdz(a,znak);
        for(int k=0;k<5;k++)
            a[k]=' ';
        }
        
        return wynik;
    }
               
   
    
    public static void main(String[] args) {
        char[][] tab = new char[5][5];
        try{
            Scanner odczyt = new Scanner(new File("gra.txt"));
            for (int i=0;i<5;i++){
                try{
                    String tekst = odczyt.nextLine();
                
                String[] txt = tekst.split(" ");
                if (txt.length > 5 || txt.length < 5) {
                    System.out.println("Znaleziono wiersz z nieprawidłową ilością znaków ");
                    return;
                }  
                for (int j=0;j<5;j++){
                    char x = txt[j].charAt(0);
                    if (x != 'x' && x != 'o') {
                        System.out.println("Znaleziono znak inny niż dozwolone ");
                        return;
                    }   
                    tab[i][j] = x;
                }
                }
                catch(Exception e){
                    System.out.println("Plik nie zawiera wystarczającej ilości wierszy ");
                    return;
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Nie udało się otworzyć pliku");
            return;
        }
        
        int x = oblicz(tab,'x');
        int o = oblicz(tab,'o');


        System.out.println("x: " + x);
        System.out.println("o: " + o);
        
        if (x>o){
            System.out.println("Wygrywa gracz stawiający znaki 'x'. Gratulacje!");
        }
        else if (o>x){
            System.out.println("Wygrywa gracz stawiający znaki 'o'. Gratulacje!");
        }
        else System.out.println("Remis!");

        
        }
}