

public class MenuAntivirus implements Menu {
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
    public void validationMenu() {

    }

    @Override
    public void chooseNumber(int num) {

    }
}
