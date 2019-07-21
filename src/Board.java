import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
    private List<Command> commands = new ArrayList<>();


    Board(List<String> board){
        int ownState = 0;
        for (String row : board) {
            ownState += 1;
            int state;
            char command;
            char direction;
            char charChange;
            String[] array = row.split("[,]");
            for (String temp:array) {
                charChange = temp.charAt(0);
                command = temp.charAt(1);
                direction = temp.charAt(2);
                state = temp.charAt(3) - '0';
                if (!(direction == '>' || direction == '<' || direction == '=' || direction == '#')){
                    throw new IllegalArgumentException("неверный символ");
                }
                Command commandToAdd = new Command(ownState, charChange, state, command, direction);
                commands.add(commandToAdd);
            }
        }


    }

    public List<Command> getCommands() {
        return commands;
    }

    class Command{
        private int ownState;
        private char charChange;
        private int state;
        private char toChar;
        private char direction;

        Command(int ownState, char charChange, int state, char toChar, char direction){
            this.ownState = ownState;
            this.charChange = charChange;
            this.state = state;
            this.toChar = toChar;
            this.direction = direction;
        }

        public int getOwnState() {
            return ownState;
        }

        public char getCharChange() {
            return charChange;
        }


        int getState() {
            return state;
        }

        public char getToChar (){
            return toChar;
        }

        public char getDirection() {
            return direction;
        }
    }
}
