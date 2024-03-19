package shared;

import App1.LinkList;
import App2.BSTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class FileManage {
    private static final String TAXPAYER_URL = "Taxpayer.txt";
    private static final String OUTPUT_URL = "Output.txt";
    public void loadFromFile(LinkList<TaxPayer> list) {
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
        }
    }


    public void saveToFile(LinkList list) {
        List<TaxPayer> taxPayerList = new ArrayList<TaxPayer>((Collection<? extends TaxPayer>) list); // Assuming LinkList implements Iterable

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_URL))) {
            for (TaxPayer taxPayer : taxPayerList) {
                // Write each TaxPayer object followed by a newline
                writer.write(taxPayer.toString());
                writer.newLine();
            }
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
