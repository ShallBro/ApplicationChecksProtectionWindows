

import java.io.*;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.String;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    // Консольное меню
    public static void main(String[] args) throws java.io.IOException{
        int flag = 0;
        int flag1 = 0;
        int flagMain = 0; // флаг возвращающий в главное меню
        int resultInet = 0;
        int resultInstFire = 0;
        int resultWorkFire = 0;
        int resultInstAnti = 0;
        int resultWorkAnti = 0;
        String result1 = "";
        String result2 = "";
        String result3 = "";
        String result4 = "";
        String result5 = "";
        do {
            System.out.println("-----------------------------------");
            System.out.println("Введите номер пункта:");
            System.out.println("1.Проверка межесетевого экрана");
            System.out.println("2.Проверка антивирусного программного обеспечения");
            System.out.println("3.Вывести результаты");
            System.out.println("4.Сохранить результаты в файл");
            System.out.println("5.Выход из программы");
            System.out.println("-----------------------------------");
            int n = scanner.nextInt();
            switch (n){
                case 1:
                    do {
                        System.out.println("----Проверка межсетевого экрана----");
                        System.out.println("1.Проверка подключения к Интернету");
                        System.out.println("2.Проверка наличия установленного межсетевого экрана");
                        System.out.println("3.Проверка работоспособности межсетевого экрана");
                        System.out.println("4.Назад");
                        System.out.println("-----------------------------------");
                        int n1 = scanner.nextInt();
                        switch (n1){
                            case 1:
                                if(checkInternet()){
                                    System.out.println("Интернет соединение есть");
                                    resultInet = 1;
                                    flag = 0;
                                }
                                else
                                    System.out.println("Интернет соединения нет");
                                    resultInet = 1;
                                    flag = 0;
                                break;
                            case 2:
                                checkInstalledFireWall();
                                resultInstFire = 1;
                                flag = 0;
                                break;
                            case 3:
                                if (checkWorkingFireWall()){
                                    System.out.println("Межсетевой экран функционирует правильно");
                                    resultWorkFire = 1;
                                    flag = 0;
                                }
                                else {
                                    resultWorkFire = 1;
                                    System.out.println("Межсетевой экран функционирует неверно, или не функционирует вовсе!");
                                    flag = 0;
                                }
                                break;
                            case 4:
                                flag = 1; // чтобы вернуться назад в главное меню
                                break;
                            default:
                                System.out.println("Введен не верный пункт меню!");
                                flag = 0;
                                break;
                        }
                    } while (flag == 0);
                    break;
                case 2:
                    do {
                        System.out.println("----Проверка антивирусного программного обеспечения----");
                        System.out.println("1.Проверка наличия установленного антивируса");
                        System.out.println("2.Проверка работоспособности антивирусного ПО");
                        System.out.println("3.Назад");
                        System.out.println("-----------------------------------");
                        int n2 = scanner.nextInt();
                        switch (n2){
                            case 1:
                                String value = Main.readRegistry("HKLM\\SOFTWARE\\Microsoft\\Windows Defender"
                                        , "DisableAntivirus");
                                System.out.println(value);
                                resultInstAnti = 1;
                                flag1 = 0;
                                break;
                            case 2:
                                if(checkWorkingAntivirus("MsMpEng.exe")) {
                                    System.out.println("Антивирус работает");
                                    resultWorkAnti = 1;
                                    flag1 = 0;
                                }
                                else {
                                    System.out.println("Антивирус не работает");
                                    resultWorkAnti = 1;
                                    flag1 = 0;
                                }
                                break;
                            case 3:
                                flag1 = 1; // чтобы вернуться назад
                                break;
                            default:
                                System.out.println("Введен не верный пункт меню!");
                                flag1 = 0;
                                break;
                        }

                    } while (flag1 == 0);
                    break;
                case 3:
                    if (resultInet == 1){
                        if(checkInternet()) {
                            System.out.println("Интернет соединение есть");
                            result1 = "Интернет соединение есть";
                        }
                        else {
                            System.out.println("Интернет соединения нет");
                            result1 = "Интернет соединения нет";
                        }
                    }
                    else {
                        System.out.println("1. Тестирование подключения к интернету не выполнялось");
                        result1 = "1. Тестирование подключения к интернету не выполнялось";
                    }
                    if (resultInstFire == 1)
                        if (checkInstalledFireWall()){
                            result2 = "Фаервол установлен!";
                        }
                        else
                            result2 = "Фаервол не установлен!";
                    else {
                        System.out.println("2. Проверка наличия фаервола на данном ПК не выполнялась");
                        result2 = "2. Проверка наличия фаервола на данном ПК не выполнялась";
                    }

                    if (resultWorkFire == 1) {
                        if (checkWorkingFireWall()) {
                            System.out.println("Межсетевой экран функционирует правильно");
                            result3 = "Межсетевой экран функционирует правильно";
                        }
                        else {
                            System.out.println("Межсетевой экран функционирует неверно, или не функционирует вовсе!");
                            result3 = "Межсетевой экран функционирует неверно, или не функционирует вовсе!";
                        }
                    }
                    else {
                        System.out.println("3. Тестирования фаервола на данном ПК не выполнялась");
                        result3 = "3. Тестирования фаервола на данном ПК не выполнялась";
                    }
                    if (resultInstAnti == 1){
                        String value = Main.readRegistry("HKLM\\SOFTWARE\\Microsoft\\Windows Defender"
                                , "DisableAntivirus");
                        System.out.println(value);
                        result4 = value;
                    }
                    else {
                        System.out.println("4. Проверка наличия антивируса на данном ПК не выполнялась");
                        result4 = "4. Проверка наличия антивируса на данном ПК не выполнялась";
                    }
                    if (resultWorkAnti == 1){
                        if(checkWorkingAntivirus("MsMpEng.exe")) {
                            System.out.println("Антивирус работает");
                            result5 = "Антивирус работает";
                        }
                        else {
                            System.out.println("Антивирус не работает");
                            result5 = "Антивирус не работает";
                        }
                    }
                    else {
                        System.out.println("5. Тестирования антивируса на данном ПК не выполнялась");
                        result5 = "5. Тестирования антивируса на данном ПК не выполнялась";
                    }

                    resultInet = 0;
                    resultInstFire = 0;
                    resultWorkFire = 0;
                    resultInstAnti = 0;
                    resultWorkAnti = 0;
                    break;
                case 4:
                    Savefile(result1,result2,result3,result4,result5);
                    break;
                case 5:
                    flagMain = 1;
                    break;
                default:
                    System.out.println("Введен не верный пункт меню!");
                    break;
            }

        } while (flagMain == 0);

    }

   // Проверка наличия подключения к Интернету.
    public static boolean checkInternet(){
        Boolean result = false;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL("https://ru.stackoverflow.com/").openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    // Проверка наличия установленного межсетевого экрана
    public static boolean checkInstalledFireWall(){
        boolean result;
        Path path = Paths.get("C:\\WINDOWS\\System32\\Firewall.cpl");
        if (Files.exists(path)){
            System.out.println("Фаервол установлен!");
            result = true;
            return result;
        }
        else
            System.out.println("Фаервол не установлен!");
            result = false;
            return result;
    }

    // Проверка работоспособности межсетевого экрана
    public static boolean checkWorkingFireWall() {
        boolean result;
        HttpURLConnection url1 = null;
        String res = null;
        try {
            url1 = (HttpURLConnection) new URL("https://ru.stackoverflow.com/").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url1.getURL().openStream()));
            res = reader.readLine();
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (res == null) {
            result = false;
            return result;
        }
        else{
            result = true;
            return result;
        }
    }

    // Проверка наличия установленного антивируса через чтения регистра
    public static final String readRegistry(String location, String key) {
        String error = "Антивирус не установлен в системе";
        String successfully = "Антивирус установлен в системе";
        String check = new String("\r\n\r\n").intern();
        try {
            // Запустить reg-запрос, затем прочитать вывод с помощью StreamReader (внутренний класс)
            Process process = Runtime.getRuntime().exec("reg query " +
                    '"'+ location + "\" /v " + key);

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
            String output = reader.getResult();

            // Вывод имеет следующий формат:
            // <Информация о версии> <ключ> <тип реестра> <значаение>
            if( ! output.contains("\t")){
                if(output.isEmpty() || output.equals(check))
                    return error;
                return successfully;
            }
            return error;
        }
        catch (Exception e) {
            return null;
        }

    }
    // Внутренний класс
    static class StreamReader extends Thread {
        private InputStream is;
        private StringWriter sw= new StringWriter();

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1)
                    sw.write(c);
            }
            catch (IOException e) {
            }
        }

        public String getResult() {
            return sw.toString();
        }
    }

    public static boolean checkWorkingAntivirus(String word){
        boolean result = false;
        try {
            String process;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null){
                if(process.indexOf(word) >= 0){
                    result = true;
                }
            }
        }catch (Exception err){
            err.printStackTrace();
        }
        return result;
    }

    public static void Savefile(String result1,String result2,String result3,String result4,String result5) {
        try{
            File file = new File("results.txt");
            if(!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.println(result1);
            pw.println(result2);
            pw.println(result3);
            pw.println(result4);
            pw.println(result5);
            pw.close();
        } catch (IOException e){
            System.out.print("Error: " + e);
        }
    }
}
