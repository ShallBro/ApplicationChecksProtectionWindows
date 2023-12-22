import java.util.Scanner;

public class MenuFirewall implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private Menu menu;

    @Override
    public void menu() {
        System.out.println("----Проверка межсетевого экрана----");
        System.out.println("1.Проверка подключения к Интернету");
        System.out.println("2.Проверка наличия установленного межсетевого экрана");
        System.out.println("3.Проверка работоспособности межсетевого экрана");
        System.out.println("4.Назад");
        System.out.println("-----------------------------------");
    }

    @Override
    public void error() {
        System.out.println("Введен не верный пункт меню!");
    }

    @Override
    public void validationMenu() {
        menu();
        while (true){
            if(!scanner.hasNextInt()) {
                error();
                menu();
                continue;
            }
            else {
                int num = scanner.nextInt();
                chooseNumber(num);
            }
        }
    }

    @Override
    public void chooseNumber(int num) {
        switch (num){
            case 1:

                break;
            case 2:
                break;
            case 3:
            case 4:
                menu = new MainMenu();
                menu.validationMenu();
            default:
                error();
                validationMenu();
        }
    }
}
