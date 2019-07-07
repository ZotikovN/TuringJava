import java.util.ArrayList;

//Класс ленты

public class Tape {
    ArrayList<Character> charIn = new ArrayList<>();
    int state;

    Tape (int start, String chars){
        this.state = start;
        for (int i = 0; i < chars.length(); i++) {
            charIn.add(chars.charAt(i));
        }
    }

    public int getState() {
        return state;
    }

    void goForward(boolean i){
        if (i) {
            state++;
        }
        else {
            if (state != 0)
            {
                state--;
            }
            else {
                throw new IllegalArgumentException();
            }
        }

    }

    
}
