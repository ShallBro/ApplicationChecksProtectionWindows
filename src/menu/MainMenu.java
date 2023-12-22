package menu;

import logic.Functional;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu implements Menu {
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
    public void validationMenu(String[] strArray) throws IOException {
        while (true){
            menu();
            if(!scanner.hasNextInt()){
                error();
            }
            else {
                int num = scanner.nextInt();
                chooseNumber(num,strArray);
            }
        }
    }

    @Override
    public void chooseNumber(int number, String[] strArray) throws IOException {
        switch (number){
            case 1:
                menu = new MenuFirewall();
                menu.validationMenu(strArray);
                break;
            case 2:
                menu = new MenuAntivirus();
                menu.validationMenu(strArray);
                break;
            case 3:
                printResults(strArray);
                break;
            case 4:
                Functional.saveFile(strArray);
                break;
            case 5:
                scanner.close();
                System.exit(0);
            default:
                error();
                break;
        }
    }

    public void printResults(String[] strArray){
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(strArray[i]);
        }
    }

}
