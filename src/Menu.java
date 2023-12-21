import java.util.Scanner;

public class Menu {
    private int flag = 0;
    private int flag1 = 0;
    private int flagMain = 0; // флаг возвращающий в главное меню
    private int resultInet = 0;
    private int resultInstFire = 0;
    private int resultWorkFire = 0;
    private int resultInstAnti = 0;
    private int resultWorkAnti = 0;
    private int resultTestAnti = 0;

    public void mainMenu(){
        System.out.println("-----------------------------------");
        System.out.println("Введите номер пункта:");
        System.out.println("1.Проверка межесетевого экрана");
        System.out.println("2.Проверка антивирусного программного обеспечения");
        System.out.println("3.Вывести результаты");
        System.out.println("4.Сохранить результаты в файл");
        System.out.println("5.Выход из программы");
        System.out.println("-----------------------------------");
    }
    public void checkFirewall(){
        System.out.println("----Проверка межсетевого экрана----");
        System.out.println("1.Проверка подключения к Интернету");
        System.out.println("2.Проверка наличия установленного межсетевого экрана");
        System.out.println("3.Проверка работоспособности межсетевого экрана");
        System.out.println("4.Назад");
        System.out.println("-----------------------------------");
    }
    public void checkAntivirus(){
        System.out.println("----Проверка антивирусного программного обеспечения----");
        System.out.println("1.Проверка наличия установленного антивируса");
        System.out.println("2.Проверка работоспособности антивирусного ПО");
        System.out.println("3.Тестирование антивирусного ПО");
        System.out.println("4.Назад");
        System.out.println("-----------------------------------");
    }
    public void error(){
        System.out.println("Введен не верный пункт меню!");
    }
    public void validationMainMenu(Menu menu, Scanner scanner){
        menu.mainMenu();
        while (true){
            if(!scanner.hasNextInt()){
                menu.error();
                menu.mainMenu();
                continue;
            }
            else {
                int num = scanner.nextInt();
                chooseNumberMain(num);
            }
        }
    }
    public void chooseNumberMain(int number){
        switch (number){
            case 1:
                checkFirewall();
                break;
            case 2:
                checkAntivirus();
                break;
            case 3:
            case 4:
            case 5:
                System.exit(0);
            default:
                error();
                validationMainMenu(new Menu(), new Scanner(System.in));
        }
    }

    public void chooseNumberFirewall(){}
    public void chooseNumberAntivirus(){}
}
