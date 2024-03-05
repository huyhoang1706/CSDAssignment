package shared;

import App1.LinkList;
import App2.BSTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileManage {
    private static final String TAXPAYER_URL = "Taxpayer.txt";
    private static final String OUTPUT_URL = "Output.txt";
    public void loadFromFile(LinkList list) {
        try {
            File file = new File(TAXPAYER_URL);
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] details = data.split("_");
                TaxPayer taxPayer = new TaxPayer();
                double income = Double.parseDouble(details[2]);
                double deduction = Double.parseDouble(details[3]);
                taxPayer.setCode(Integer.parseInt(details[0]));
                taxPayer.setName(details[1]);
                taxPayer.setIncome(income);
                taxPayer.setDeduct(deduction);
                taxPayer.setTax(taxPayer.getTax());
                list.addToEnd(taxPayer);
            }
            System.err.println("Load successful!");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    public void loadFromFile(BSTree tree) {
        try {
            File file = new File(TAXPAYER_URL);
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] details = data.split("_");
                TaxPayer taxPayer = new TaxPayer();
                double income = Double.parseDouble(details[2]);
                double deduction = Double.parseDouble(details[3]);
                taxPayer.setCode(Integer.parseInt(details[0]));
                taxPayer.setName(details[1]);
                taxPayer.setIncome(income);
                taxPayer.setDeduct(deduction);
                taxPayer.setTax(taxPayer.getTax());
                tree.insert(taxPayer);
            }
            System.err.println("Load successful!");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    public void saveToFile(LinkList list) {
        try {
            FileWriter fileWriter = new FileWriter(OUTPUT_URL);
/*            for (TaxPayer taxPayer : list) { // Muốn fix thì phải implements Iterable
                fileWriter.write(taxPayer.toString());
            }*/
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(List<String> list) {
        try {
            FileWriter fileWriter = new FileWriter(OUTPUT_URL);
            for (String elem : list) {
                fileWriter.write(elem + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
