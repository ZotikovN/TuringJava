
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class Tests {

    @Test
    public void zeroToOne() throws FileNotFoundException{
        String expected = "111111 ";
        List<String> res = Arrays.asList("-input", "Tests/input.txt", "-start", "0", "-commands", "Tests/commands.txt");
        Main.main(res);
        assertEquals(expected, Main.result);

    }

    @Test
    public void zeroToOneOneToZero() throws FileNotFoundException{
        String expected = "110111 ";
        List<String> res = Arrays.asList("-input", "Tests/input.txt", "-start", "0", "-commands", "Tests/commands1.txt");
        Main.main(res);
        assertEquals(expected, Main.result);

    }

    @Test
    public void addBin() throws FileNotFoundException{
        String expected = " 10010         ";
        List<String> res = Arrays.asList("-input", "Tests/input1.txt", "-start", "10", "-commands", "Tests/commands2.txt");
        Main.main(res);
        assertEquals(expected, Main.result);

    }

}
