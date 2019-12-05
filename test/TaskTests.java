import com.olimp.Main.Parser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTests {
    @Test
    public void fixedTests() {
        assertEquals(1 , Parser.parseInt("one"));
        assertEquals(20 , Parser.parseInt("twenty"));
        assertEquals(246 , Parser.parseInt("two hundred forty-six"));
    }
}
