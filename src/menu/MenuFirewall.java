package menu;

import logic.Functional;

import java.io.IOException;
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
    public void validationMenu(String[] strArray) throws IOException {
        while (true){
            menu();
            if(!scanner.hasNextInt()) {
                error();
            }
            else {
                int num = scanner.nextInt();
                chooseNumber(num,strArray);
            }
        }
    }

    @Override
    public void chooseNumber(int num, String[] strArray) throws IOException {
        switch (num) {
            case 1:
                checkInternet(strArray);
                break;
            case 2:
                checkInstallFirewall(strArray);
                break;
            case 3:
                checkWorkFirewall(strArray);
                break;
            case 4:
                menu = new MainMenu();
                menu.validationMenu(strArray);
                break;
            default:
                error();
                validationMenu(strArray);
                break;
        }
    }

    public void checkInternet(String[] strArray){
        if (Functional.checkInternet()){
            strArray[0] = "Интернет соединение есть";
            System.out.println(strArray[0]);
        } else {
            strArray[0] = "Интернет соединения нет";
            System.out.println(strArray[0]);
        }
    }

    public void checkInstallFirewall(String[] strArray){
        if (Functional.checkInstalledFireWall()){
            strArray[1] = "Фаервол установлен!";
        }
        else {
            strArray[1] = "Фаервол не установлен!";
        }
    }

    public void checkWorkFirewall(String[] strArray) {
        if (Functional.checkWorkingFireWall()){
            strArray[2] = "Межсетевой экран функционирует правильно";
        }
        else {
            strArray[2] = "Межсетевой экран функционирует неверно, или не функционирует вовсе!";
        }
    }
}
