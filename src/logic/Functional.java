package logic;

import java.io.*;
import java.io.FileReader;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.String;
import java.awt.*;



public class Functional {

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
        HttpURLConnection url1;
        String res = null;
        try {
            url1 = (HttpURLConnection) new URL("https://ru.stackoverflow.com/").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url1.getURL().openStream()));
            res = reader.readLine();
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (res == null) {
            result = true;
            return result;
        }
        else{
            result = false;
            return result;
        }
    }

    // Проверка наличия установленного антивируса через чтения реестра
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
                if(output.isEmpty() || output.equals(check)) {
                    return error;
                }
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
    public static boolean testAntivirus() throws IOException {
        String g = "X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*";
        BufferedReader br = null;
        boolean result = true;
        try {
            File file = new File("test.txt");
            if(!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.println(g);
            pw.close();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("C:\\Users\\artem\\IdeaProjects\\Java Projects\\ApplicationChecksProtectionWindows\\test.txt"));
                br = new BufferedReader(new FileReader("test.txt"));
                String line;
                if((line = br.readLine()) != null){
                    result = false;
                    return result;
                }
                else{
                    result = true;
                    return result;
                }
            }
        } catch (Exception e){
            return result;
        }
        return result;
    }

    public static void saveFile(String[] results) {
        try{
            File file = new File("results.txt");
            if(!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < results.length; i++) {
                pw.println(results[i]);
            }
            pw.close();
        } catch (IOException e){
            System.out.print("Error: " + e);
        }
    }
}
