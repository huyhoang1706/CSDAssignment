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
                TaxPayer taxPayer = getDetails(myReader);
                list.addToEnd(taxPayer);
            }
            System.out.println("Load successful!");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. FILE NOT FOUND");
        }
    }

    private TaxPayer getDetails(Scanner myReader) {
        String data = myReader.nextLine();
        String[] details = data.split(", ");
        TaxPayer taxPayer = new TaxPayer();
        taxPayer.setCode(Integer.parseInt(details[0]));
        taxPayer.setName(details[1]);
        taxPayer.setIncome(Double.parseDouble(details[2]));
        taxPayer.setDeduct(Double.parseDouble(details[3]));
        taxPayer.setTax(taxPayer.getTax());
        return taxPayer;
    }

    public void loadFromFile(BSTree tree) {
        try {
            File file = new File(TAXPAYER_URL);
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()){
                TaxPayer taxPayer = getDetails(myReader);
                tree.insert(taxPayer);
            }
            System.out.println("Load successful!");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. FILE NOT FOUND");
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
