package dictionary.hh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);


    Dictionary dictionary = new Dictionary();
    //Ham nhap lieu
    public void insertFromCommandline(){

        System.out.println("Nhap so luong tu moi : ");
        int dem = scanner.nextInt();

        for (int i = 0 ; i < dem ; i++){

            int soluonng = 0;

            System.out.println("Nhap tu moi thu: "+(i+1));
            String tu = scanner.next().toLowerCase();
            scanner.nextLine();
            System.out.println("Nhap nghia cua tu thu : "+(i+1));
            String nghia = scanner.nextLine().toLowerCase();
            //scanner.nextLine();
            for (int j = 0 ; j < dictionary.list.size(); j++){
                if (dictionary.list.get(j).getTarget().equals(tu)){
                    soluonng++;
                    System.out.println("Trong tu dien da co tu nay: ");
                    break;
                }
            }

            if (soluonng == 0){
                Word word1 = new Word(tu,nghia);
                dictionary.pushArray(word1);
            }
        }

    }
    public void insertFromFile(){
        File file = new File("C:\\Baitaplon1\\oop2018baitaplon\\dictionaryhuong\\src\\dictionary.txt");
        try(Scanner scanner1 = new Scanner(file)) {

            String dong;
            while (scanner1.hasNext()){
                dong = scanner1.nextLine();
                Word s= new Word("","");
                int i , j;
                for ( i = 0 ; i < dong.length() ; i++ ){

                    if ((int)dong.charAt(i) == 9){
                        break;
                    }
                    s.target += dong.charAt(i);

                }

                for ( j = i + 1; j < dong.length() ; j++ ){
                    s.explain += dong.charAt(j);
                }

                dictionary.pushArray(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void dictionaryLookup(){
        int dem = 0;
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien hien tai dang trong: ");
        }else{
            System.out.println("Moi ban nhap tu English :");
            String english = scanner.next().toLowerCase();
            for (int i = 0 ; i < dictionary.list.size() ; i++){
                if (dictionary.list.get(i).getTarget().equals(english)){
                    dem++;
                    System.out.println("Tu ban can tim la: ");
                    System.out.println(dictionary.list.get(i).getTarget()+ " "
                            + dictionary.list.get(i).getExplain());

                }
            }
            if (dem == 0){
                System.out.println("Xin loi! Tu dien hien tai khong co tu nay:");
            }

            String lap;
            do {
                System.out.println("Ban co muon tiep tuc tra tu Y/N :");
                lap = scanner.next();
                if (lap.equals("N")){
                    return;
                }
                if (lap.equals("Y")){
                    this.dictionaryLookup();
                }
            }while (!lap.equals("N") && !lap.equals("Y"));
        }
    }

    public void traDictionary(){
        //Scanner nhaptu = new Scanner(System.in);
        System.out.println("Moi ban nhap tu: ");
        String tu = scanner.next().toLowerCase();
        int dem = 0;
        int demtu = 0;
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien trong moi ban nhap tu vao tu dien: ");
        }else{
            for (int i = 0 ; i < dictionary.list.size() ; i++ ){
                if (dictionary.list.get(i).getTarget().substring(0,tu.length()).equals(tu)){
                    dem++;
                }
                if (dem !=0 )
                    break;
            }
            if (dem == 0){
                System.out.println("Trong tu dien ko co tu nay");
                String thulai;
                do {
                    System.out.println("" + "Ban co muon nhap lai ko Y/N : ");
                    thulai = scanner.next();
                    if (thulai.equals("N")){
                        return;
                    }else {
                        this.traDictionary();
                    }
                }while (!thulai.equals("N") && !thulai.equals("Y"));

            } else {
                System.out.println("    |Stt" + "   |English" + "       |Vietnamese");
                for (int i = 0 ; i < dictionary.list.size() ; i++){
                    if (dictionary.list.get(i).getTarget().substring(0 , tu.length()).equals(tu)){
                        ++demtu;
                        System.out.println(dictionary.toString(i,demtu));
                    }
                }
            }
        }
    }

    public void xoaTu(){
        String tuxoa;
        int dem =0 ;
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien hien tai dang trong: ");
        }else{
            System.out.println("Moi ban nhap tu can xoa: ");
            tuxoa = scanner.next().toLowerCase();
            for (int i = 0 ; i < dictionary.list.size() ; i++ ){
                if (dictionary.list.get(i).getTarget().equals(tuxoa)){
                    dem++;
                    dictionary.list.remove(dictionary.list.get(i));
                    break;
                }
            }
            if (dem == 0){
                System.out.println("Trong tu dien khong co tu ban muon xoa!!!");
            }else System.out.println("Ban da xoa tu thanh cong ");

        }


        String laplai;
        do {
            System.out.println("Ban co muon nhap xoa tu nua khong Y/N ?");
            laplai = scanner.next();
            if (laplai.equals("Y")){
                this.xoaTu();
            }
        }while (laplai.equals("Y") && laplai.equals("N"));
    }


    public void showAllWords(){
        System.out.println("    |No"+"  |English"+"         |Vietnamese"+"\n");

        for (int i = 0 ; i < dictionary.list.size() ; i++){
            int dem = i+1;
            System.out.println("\n");
            System.out.println(dictionary.toString(i,i+1));
        }
    }
}
