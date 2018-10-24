package dictionary.hh;

import javax.print.DocFlavor;
import java.util.Scanner;

public class DictionaryCommandLine {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void docfile(){
        dictionaryManagement.insertFromFile();
    }

    public void In(){
        dictionaryManagement.showAllWords();
    }

    public void Sua(String ta, String tv){
        dictionaryManagement.suaTu(ta,tv);
    }

    public void cacPhuongthuc(String tu, String tv){

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("(1) Tra tu: dictionaryLookup ");
        System.out.println("     --------------       ");
        System.out.println("(2) Tra tu gan dung: traDictionary ");
        System.out.println("     --------------        ");
        System.out.println("(3) Them tu moi: insertFromCommandline ");
        System.out.println("     --------------        ");
        System.out.println("(4) Xoa tu: xoaTu");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("\nMoi ban chon cac phuong thuc ...");
        Scanner scanner1 = new Scanner(System.in);
        char chon = scanner1.next().charAt(0);
        switch (chon){
            case '1':
                dictionaryManagement.dictionaryLookup(tu);
                break;
            case '2':
                dictionaryManagement.traDictionary(tu);
                break;
            case '3':
                dictionaryManagement.insertFromCommandline(tu,tv);
                break;
            case '4':
                dictionaryManagement.xoaTu(tu);
                break;
        }

    }
}