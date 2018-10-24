package dictionary.hh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class DictionaryManagement {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Word> list1 = new ArrayList<>();
    ArrayList<Word> list2 = new ArrayList<>();


    Dictionary dictionary = new Dictionary();
    //Ham nhap lieu
    public boolean insertFromCommandline(String tu , String nghia){

        int soluonng = 0;

        for (int j = 0 ; j < dictionary.list.size(); j++) {
            if (dictionary.list.get(j).getTarget().equals(tu)) {
                soluonng++;
                System.out.println("Trong tu dien da co tu nay!!!...");
                break;

            }
        }
            if (soluonng == 0){
                Word word1 = new Word(tu,nghia);
                dictionary.pushArray(word1);
                System.out.println("Them tu thanh cong...");
                return true;
            }
            return false;
    }

    public void insertFromFile(){
        File file = new File("C:\\Baitaplon1\\oop2018baitaplon\\dictionaryhuong\\src\\dictionary\\hh\\Dictionary1.txt");
        try(Scanner scanner1 = new Scanner(file)) {

            String dong;
            while (scanner1.hasNext()){
                dong = scanner1.nextLine();
                Word s= new Word("","");
                String[] s1 = dong.split("\t");
                s.setTarget(s1[0].trim());
                s.setExplain(s1[1]);
                dictionary.pushArray(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Word dictionaryLookup(String english){
        int dem = 0;
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien hien tai dang trong: ");
        }else {

            for (int i = 0; i < dictionary.list.size(); i++) {
                if (dictionary.list.get(i).getTarget().equals(english)) {
                    dem++;
                    System.out.println("Tu ban can tim la: ");
                    System.out.println(dictionary.list.get(i).getTarget() + " "
                            + dictionary.list.get(i).getExplain());
                    return dictionary.list.get(i);

                }
            }
            if (dem == 0) {
                System.out.println("Xin loi! Tu dien hien tai khong co tu nay:");
            }
        }
       return null;
    }

   /* public boolean traDictionary(String tu){
        int dem = 0;
        int demtu = 0;
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien trong moi ban nhap tu vao tu dien: ");
        }
            for (int i = 0 ; i < dictionary.list.size() ; i++ ) {
                String ta = dictionary.list.get(i).getTarget().substring(0, tu.length());
                if (ta.equals(tu)) {
                    dem++;
                    list1.add(dictionary.list.get(i));
                }
            }
            if (dem != 0){
                return true;
            }

        return false;
    }*/

    public int traDictionary(String tu ){
        int dem = 0;
        int sl = tu.length();
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien trong moi ban nhap tu vao tu dien: ");
        }
        System.out.println("\t|Stt"+"\t\t|English"+"\t\t\t\t|Vietnamese");
        for (int i = 0 ; i < dictionary.list.size() ; i++ ){
            if (dictionary.list.get(i).getTarget().substring(0,sl).startsWith(tu)){
                dem++;
                System.out.println(dictionary.list.get(i).getTarget() +
                        "  " + dictionary.list.get(i).getExplain());
                list1.add(dictionary.list.get(i));
            }

        }
        if (dem == 0){
            System.out.println("Trong tu dien ko co tu nay"); }
        return dem;
    }

    public int xoaTu(String ta , String tv){
        int dem =0 ;
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien hien tai dang trong: ");
        }else{
            for (int i = 0 ; i < dictionary.list.size() ; i++ ){
                if (dictionary.list.get(i).getTarget().equals(ta)){
                    dem++;
                    dictionary.list.remove(dictionary.list.get(i));
                }
            }
            if (dem == 0){
                System.out.println("Trong tu dien khong co tu ban muon xoa!!!");
            }else System.out.println("Ban da xoa tu thanh cong ");

        }
        return dem;

    }

    public boolean suaTu(String nhaptu , String tv){

        int dem = 0;
        if(dictionary.list.isEmpty()){
            System.out.println("Tu dien hien ta dang rong!!!");
        }else{

            for (int i = 0 ; i < dictionary.list.size() ; i++ ){
                if (nhaptu.equals(dictionary.list.get(i).getTarget())){
                    dem++;
                    String ta = dictionary.list.get(i).getTarget();
                    System.out.println("Da tim thay tu muon sua: ");
                    System.out.println("     ---------------         ");
                    Word word1 = new Word(ta,tv);
                    dictionary.list.set(i,word1);
                }
            }
            if (dem == 0 ){
                System.out.println("Khong tim tu muon sua!!!");
                return true;
            }
        }
        return false;
    }

    public void file(){
        if (dictionary.list.isEmpty()){
            System.out.println("Tu dien dang rong!!!");
        }
        for (Word arr : dictionary.list){
            list2.add(arr);
        }
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
