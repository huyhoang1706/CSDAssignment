package App1;

import shared.Input;
import shared.FileManage;
import shared.TaxPayer;

public class App1 {
    private final Input input = new Input();
    private final FileManage fileManager = new FileManage();
    private final LinkList linkList = new LinkList();
    public void run() {

        while (true) { // Infinite Loop
            // Display Menu
            Menu.display();
            // Get choice from user
            int choice = input.getInt("Your Selection (0 -> 10): ", 0, 10);
            // Perform action base on choice
            switch (choice) {
                case 0 -> System.exit(0);
                case 1 -> fileManager.loadFromFile(linkList);
                case 2 -> linkList.addToEnd();
                case 3 -> linkList.displayData(linkList);
                case 4 -> fileManager.saveToFile(linkList);
                case 5 -> linkList.searchByCode();
                case 6 -> linkList.deleteByCode();
                case 7 -> linkList.sortByCode();
                case 8 -> linkList.addToBeginning();
                case 9 -> linkList.addAfterPosition();
                case 10 -> linkList.deleteAt();
            }
        }
    }
}