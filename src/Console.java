import menu.MainMenu;
import menu.Menu;

import java.io.IOException;

public class Console {
    public static void main(String[] args) throws IOException {
        String[] results = {
                "1. Тестирование подключения к интернету не выполнялось",
                "2. Проверка наличия фаервола на данном ПК не выполнялась",
                "3. Тестирования фаервола на данном ПК не выполнялась",
                "4. Проверка наличия антивируса на данном ПК не выполнялась",
                "5. Работоспособность антивируса на данном ПК не выполнялась"};
        Menu menu = new MainMenu();
        menu.validationMenu(results);
    }
}
