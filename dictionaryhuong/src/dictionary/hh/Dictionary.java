package dictionary.hh;

import java.util.ArrayList;

public class Dictionary{
    public ArrayList<Word> list = new ArrayList<>();

    public void pushArray(Word arr){
        list.add(arr);
    }

    public String toString(int vitri,int dem){
        Word s = list.get(vitri);
        return "    |"+dem+"    |"+s.getTarget()+"          |"+s.getExplain();
    }

    public Word getArray(int vitri){
        Word s1 =  list.get(vitri);
        return s1;
    }
}
