import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.*;

import java.io.*;


public class Main {

    public static String result;

    public static void main(List<String> args) throws FileNotFoundException {
        new Main().launch(args);
    }


    private void launch(List<String> args) throws FileNotFoundException {
        CmdLineParser cmd = new CmdLineParser(this);
        try {
            cmd.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            return;
        }
        try {
            if (input.isEmpty())
                throw new IllegalArgumentException("Нет файла с коммандами");
            if (commands.isEmpty())
                throw new IllegalArgumentException("Нет файла с коммандами");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        String fileIn = fileInputReader(input);
        List<String> commIn = fileCommandReader(commands);
        try {
            Calculation main = new Calculation(start, fileIn, commIn);
            Process proc = main.start();
            if (proc == Process.STOP) {
                System.out.println("Работа симмулятора завершена некорректно");
            }
            result = stringOutput(main.getLocalTape());
            System.out.println("команды: "+commands+", лента: "+input);
            System.out.println("Результат работы: " + result);
        }
        catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }





    //Флаги


    //Входная лента
    @Option(name = "-input", usage = "input", required = true)
    private String input = "";

    //Ячейка, с которой процесс начинается
    @Option(name = "-start", usage = "start")
    private int start = 0;

    //файл с командами
    @Option(name = "-commands", usage = "commands", required = true)
    private String commands = "";



    //Считывает входящий файл ленты
    private String fileInputReader(String path) throws FileNotFoundException{
        File filePath = new File(path);
        Scanner scanner = new Scanner(filePath);
        StringBuilder f = new StringBuilder();
        while (scanner.hasNextLine()){
            f.append(scanner.nextLine());
        }
        String tape = f.toString();
        return tape;
    }


    //Считывает входящий файл команд
    private List<String> fileCommandReader(String path) throws FileNotFoundException{
        File filePath = new File(path);
        Scanner scanner = new Scanner(filePath);
        StringBuilder f = new StringBuilder();
        List<String> board = new ArrayList<>();
        while (scanner.hasNextLine()){
            f.append(scanner.nextLine());
            board.add(f.toString());
            f = new StringBuilder();
        }
        scanner.close();
        return board;
    }



    private String stringOutput (Tape tape) {
        String result = tape.toString();
        return result;
    }





}
