package menu;

import java.io.IOException;

public interface Menu {
    void menu();
    void error();
    void validationMenu(String[] strings) throws IOException;
    void chooseNumber(int num, String[] strings) throws IOException;
}
