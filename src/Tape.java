import java.util.ArrayList;

//Класс ленты

public class Tape {
    private ArrayList<Character> charIn = new ArrayList<>();
    private int state;

    Tape (int start, String chars){
        this.state = start;
        for (int i = 0; i < chars.length(); i++) {
            charIn.add(chars.charAt(i));
        }
    }



    public void setCharIn(ArrayList<Character> charIn) {
        this.charIn = charIn;
    }

    public int getState() {
        return state;
    }

    public ArrayList<Character> getCharIn() {
        return charIn;
    }
    //каретка сдвигается вперед или назад

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

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (char comm : charIn){
            stringBuilder.append(comm);
        }
        return stringBuilder.toString();
    }



}
