package by.zelenevsky.sort;


import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class RowsCollection implements Comparable{

    @Override
    public int compareTo(Object obj){
        RowsCollection r = (RowsCollection) obj;
        Integer index = 0;
        for (String s1 : this.getRow()){
            String s2 = r.getRow().get(index);
            if (NumberUtils.isNumber(s1) & !NumberUtils.isNumber(s2)) {
                index = 0;
                return -1;
            }
            if (!NumberUtils.isNumber(s1) & NumberUtils.isNumber(s2)) {
                index = 0;
                return 1;
            }
            if (NumberUtils.isNumber(s1) & NumberUtils.isNumber(s2)) {
                if (Double.parseDouble(s1) < Double.parseDouble(s2)) {
                    index = 0;
                    return -1;
                }
                if (Double.parseDouble(s1) > Double.parseDouble(s2)) {
                    index = 0;
                    return 1;
                }
                index++;
                continue;
            }
            if(s1.compareTo(s2) == 0){
                index++;
                continue;
            } else {
                return s1.compareTo(s2);
            }
        }
        return 1; // если элементы одинаковые, то возвращаем все равно 1, чтобы можно было добавить в TreeSet повторяющиеся элементы
    }

    private List<String> row = new ArrayList<String>();

    public List<String> getRow() {
        return row;
    }

    public void setRow(List<String> row) {
        this.row = row;
    }

    public void addElements(String s){
        this.row.add(s);
    }

}
