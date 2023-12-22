package menu;

import logic.Functional;

import java.io.IOException;
import java.util.Scanner;

public class MenuAntivirus implements Menu {
    private Scanner scanner = new Scanner(System.in);
    private Menu menu;
    @Override
    public void menu() {
        System.out.println("----Проверка антивирусного программного обеспечения----");
        System.out.println("1.Проверка наличия установленного антивируса");
        System.out.println("2.Проверка работоспособности антивирусного ПО");
        System.out.println("3.Тестирование антивирусного ПО");
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
    public void chooseNumber(int num,String[] strArray) throws IOException {
        switch (num) {
            case 1:
                String value = Functional.readRegistry("HKLM\\SOFTWARE\\Microsoft\\Windows Defender"
                        , "DisableAntiSpyware");
                System.out.println(value);
                strArray[3] = value;
                break;
            case 2:
                checkWorkAntivirus(strArray);
                break;
            case 3:
                testAntivirus(strArray);
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

    public void checkWorkAntivirus(String[] strArray){
        if(Functional.checkWorkingAntivirus("MsMpEng.exe")){
            strArray[3] = "Антивирус работает";
            System.out.println(strArray[3]);
        }
        else {
            strArray[3] = "Антивирус не работает";
            System.out.println(strArray[3]);
        }
    }

    public void testAntivirus(String[] strArray) throws IOException {
        if(Functional.testAntivirus()){
            strArray[4] = "Антивирус работает правильно";
            System.out.println(strArray[4]);
        }
        else {
            strArray[4] = "Антивирус работает неправильно";
            System.out.println(strArray[4]);
        }
    }
}
