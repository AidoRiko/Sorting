package by.zelenevsky.sort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;



public class VisualForm extends JFrame {

    private JButton runButton;
    private JTextArea inputTextArea; // область для ввода информации с клавиатуры
    private JTable outputTable; // таблица для вывода данных
    private int tableWidth = 0;
    private boolean tableIsAdded = false; // флаг для проверки, есть ли на экране таблица

    public VisualForm(String title){ // конструктор для создания формы
        super.setTitle(title);
        setLayout(new FlowLayout());
        runButton = new JButton("Sort");
        inputTextArea = new JTextArea(10, 50);
        add(inputTextArea);
        add(runButton);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == runButton){
                    if (tableIsAdded){
                        remove(outputTable);
                    }
                    TreeSet<RowsCollection> rows = feelTree(inputTextArea.getText());
                    outputTable = new JTable(rows.size(), tableWidth);
                    feelTable(rows);
                    add(outputTable);
                    tableIsAdded = true;
                    validate();
                }
            }
        });
    }

    public TreeSet<RowsCollection> feelTree(String s){ // возвращает дерево, заполненное строками, изменяет ширину таблицы
        String[] words;
        TreeSet<RowsCollection> rows = new TreeSet<RowsCollection>();
        for(int i =1, j=0; i < s.length(); i++){
            if(s.charAt(i) == '\n' || i == s.length()-1){
                RowsCollection rowCollection = new RowsCollection();
                words = s.substring(j, i).split(" ");
                j=i+1;
                for (int wordsIndex= 0; wordsIndex < words.length; wordsIndex++){
                    rowCollection.addElements(words[wordsIndex]);
                }
                if (rowCollection.getRow().size() > tableWidth){
                    tableWidth = rowCollection.getRow().size();
                }
                rows.add(rowCollection);
            }
        }
        return rows;
    }
    public void feelTable(TreeSet<RowsCollection> tree){ // заполняет таблицу элементами
        int rowNumber = 0, columnNumber = 0;
        for(RowsCollection rowsCollection : tree){
            for (String s: rowsCollection.getRow()){
                outputTable.setValueAt(s, rowNumber, columnNumber);
                columnNumber++;
            }
            columnNumber=0;
            rowNumber++;
        }
    }
}
