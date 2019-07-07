import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.*;

import java.io.*;


public class Main {

    public static void main(String[] args) {
        new Main().launch(args);
    }


    private void launch(String[] args) {
        CmdLineParser cmd = new CmdLineParser(this);
        try {
            cmd.parseArgument(args);
        } catch (CmdLineException a) {
            System.err.println(a.getMessage());
            cmd.printUsage(System.err);
        }
        if (commands.isEmpty())
            throw new IllegalArgumentException("Нет файла с коммандами");
        if (output.isEmpty())
            throw new IllegalArgumentException("Нет выходного файла");

        Calculation main = new Calculation(start, commands, output);
        main.start();
    }

    //Комманды ввода в консоль


    //Ячейка, с которой процесс начинается
    @Option(name = "-start", usage = "start")
    private int start = 1;

    //файл с командами
    @Option(name = "-commands", usage = "commands")
    private String commands = "";

    //выходной файл
    @Option(name = "-output", usage = "output")
    private String output = "";


    //вывод значений в указанный тектовый файл
    void output (String outputFile) {
        //...
    }
}
