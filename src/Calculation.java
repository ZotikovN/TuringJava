
import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

//класс вычисления

public class Calculation {
    private int start;
    private String input;
    private List<String> commands;
    private Tape localTape;
    private Board board;
    private Process process;
    private int head;
    private final static int maxTapeValue = 10000;



    Calculation(int start, String input, List<String> commands){
        this.localTape = new Tape(start, input);
        this.head = start;
        this.board = new Board(commands);
    }

    Process start(){
        process = Process.PROCESSING;
        processingBegin(1);
        return process;

    }

    private void processingBegin(int state){
        Board processBoard = board;
        List<Board.Command> allCommands = processBoard.getCommands();
        System.out.println("Начинается работа");
        for (Board.Command temp:allCommands){
            System.out.println(temp.getOwnState() + " " + temp.getCharChange() + " " + temp.getDirection());
            processing(temp.getOwnState(), temp.getCharChange());
            break;

        }

    }

    private void processing(int state, char charChange){
        Board processBoard = board;
        List<Board.Command> allCommands = processBoard.getCommands();
        for (Board.Command temp:allCommands){
            System.out.println("tape processing "+localTape.toString());
//            System.out.println("direction "+temp.getDirection()+ ", state and char " + state + " " + charChange);
//            System.out.println("Head "+head+ ": " +localTape.getCharIn().get(head));
//            System.out.println(temp.getState());
            if (temp.getOwnState()==state && temp.getCharChange()== charChange){
                if (temp.getState() == -1){
                    process = Process.DONE;
                    break;
                }
                else {
                    if (localTape.getCharIn().get(head) == temp.getCharChange()){
                        if(temp.getDirection()== '<'){
                            localTape.getCharIn().set(head, temp.getToChar());
                            head-=1;
                            localTape.goForward(false);
                            processing(temp.getState(), localTape.getCharIn().get(head));
//                            System.out.println("1");
                            break;
                        }
                        else if(temp.getDirection()== '>'){
                            localTape.getCharIn().set(head, temp.getToChar());
                            head+=1;
                            char inf = ' ';
                            if (localTape.getCharIn().get(head-1) == localTape.getCharIn().getLast() ||
                                    head-1 == localTape.getCharIn().indexOf(localTape.getCharIn().getLast())){
                                if (localTape.getCharIn().get(head - 1) == maxTapeValue) {
                                    throw new IndexOutOfBoundsException("Переполнение памяти");
                                }
                                localTape.getCharIn().add(inf);

                            }
                            localTape.goForward(true);
                            processing(temp.getState(), localTape.getCharIn().get(head));
//                            System.out.println("2");
                            break;
                        }
                        else if(temp.getDirection()== '='){
                            localTape.getCharIn().set(head, temp.getToChar());
                            processing(temp.getState(), localTape.getCharIn().get(head));
//                            System.out.println("3");
                            break;
                        }
                    }
                    else {
                        if(temp.getDirection()== '<'){
                        head-=1;
                        localTape.goForward(false);
                        processing(temp.getState(), localTape.getCharIn().get(head));
                        break;
                        }
                        else if(temp.getDirection()== '>'){
                        head+=1;
                        char inf = ' ';
                        if (localTape.getCharIn().get(head-1) == localTape.getCharIn().getLast() ||
                                head-1 == localTape.getCharIn().indexOf(localTape.getCharIn().getLast())){
                            localTape.getCharIn().add(inf);
                        }
                        localTape.goForward(true);
                        processing(temp.getState(), localTape.getCharIn().get(head));
                        break;
                        }
                        else if(temp.getDirection()== '='){
                        processing(temp.getState(), localTape.getCharIn().get(head));
                        break;
                        }
                    }

                }
            }

        }
    }


    void output (String output) throws FileNotFoundException {
        File result = new File(output);
        PrintWriter printWriter = new PrintWriter(result);
        for (char ch : localTape.getCharIn()){
            printWriter.print(ch);
        }
        printWriter.close();
    }



    public Tape getLocalTape() {
        return localTape;
    }
}

enum Process {PROCESSING, STOP, DONE}
