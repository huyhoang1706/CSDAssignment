package App2;
import shared.FileManage;
import shared.Input;

public class App2 {
    private final Input input = new Input();
    private final FileManage fileManage = new FileManage();
    private final AVLTree avlTree = new AVLTree();
    private final BSTree bsTree = new BSTree();
    public void run() {
        while (true) { // Infinite Loop
            // Display Menu
            Menu.display();
            // Get choice from user
            int choice = input.getInt("Your Selection (0 -> 10): ", 0, 10);
            // Perform action base on choice
            switch (choice) {
                case 0 -> System.exit(0);
                case 1 -> fileManage.loadFromFile(bsTree);
                case 2 -> bsTree.insert() ;
                case 3 -> bsTree.inOrderTraverse();
                case 4 -> bsTree.preOrderTraverse();
                case 5 -> bsTree.breadthFirstTraverse();
                case 6 -> bsTree.inOrderTraverseToFile();
                case 7 -> bsTree.searchByCode();
                case 8 -> bsTree.deleteCode();
                case 9 -> bsTree.balance();
                case 10 -> bsTree.count();
            }
        }
    }
}
