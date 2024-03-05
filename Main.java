import App1.App1;
import shared.Input;
import App2.App2;

public class Main {
    public static void main(String[] args) {
        App1 app1 = new App1();
        App2 app2 = new App2();
        Input input = new Input();
        // Get choice to run Application
        int choice = input.getInt("Get your choice (1 -> 2): ", 1, 2);
        switch (choice) {
            case 1 -> app1.run();
            case 2 -> app2.run();
        }
    }
}
