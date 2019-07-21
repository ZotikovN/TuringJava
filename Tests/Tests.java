
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class Tests {

    @Test
    public void zeroToOne() throws FileNotFoundException{
        String expected = "111111____";
        List<String> res = Arrays.asList("-input", "Tests/input.txt", "-start", "0", "-commands", "Tests/commands.txt");
        Main.main(res);
        assertEquals(expected, Main.result);

    }

    @Test
    public void zeroToOneOneToZero() throws FileNotFoundException{
        String expected = "110111____";
        List<String> res = Arrays.asList("-input", "Tests/input.txt", "-start", "0", "-commands", "Tests/commands1.txt");
        Main.main(res);
        assertEquals(expected, Main.result);

    }

}
