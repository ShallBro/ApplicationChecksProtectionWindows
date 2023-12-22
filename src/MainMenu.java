import java.util.Scanner;

public class MainMenu implements Menu {
    private int flag = 0;
    private int flag1 = 0;
    private int flagMain = 0; // флаг возвращающий в главное меню
    private int resultInet = 0;
    private int resultInstFire = 0;
    private int resultWorkFire = 0;
    private int resultInstAnti = 0;
    private int resultWorkAnti = 0;
    private int resultTestAnti = 0;
    private Scanner scanner = new Scanner(System.in);
    private Menu menu;

    @Override
    public void menu() {
        System.out.println("-----------------------------------");
        System.out.println("Введите номер пункта:");
        System.out.println("1.Проверка межесетевого экрана");
        System.out.println("2.Проверка антивирусного программного обеспечения");
        System.out.println("3.Вывести результаты");
        System.out.println("4.Сохранить результаты в файл");
        System.out.println("5.Выход из программы");
        System.out.println("-----------------------------------");
    }

    @Override
    public void error(){
        System.out.println("Введен не верный пункт меню!");
    }

    @Override
    public void validationMenu() {
        menu();
        while (true){
            if(!scanner.hasNextInt()){
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
    public void chooseNumber(int number) {
        switch (number){
            case 1:
                menu = new MenuFirewall();
                menu.validationMenu();
                break;
            case 2:
                menu = new MenuAntivirus();
                menu.validationMenu();
                break;
            case 3:
            case 4:
            case 5:
                System.exit(0);
            default:
                error();
                validationMenu();
        }
    }


    public void chooseNumberFirewall(){}
    public void chooseNumberAntivirus(){}
}
