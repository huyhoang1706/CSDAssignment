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
                case 1 -> fileManage.loadFromFile(avlTree);
                case 2 -> avlTree.insert() ;
                case 3 -> avlTree.inOrderTraverse();
                case 4 -> avlTree.preOrderTraverse();
                case 5 -> avlTree.breadthFirstTraverse();
                case 6 -> avlTree.inOrderTraverseToFile();
                case 7 -> avlTree.searchByCode();
                case 8 -> avlTree.deleteCode();
                case 9 -> bsTree.balance();
                case 10 -> avlTree.count();
            }
        }
    }
}
