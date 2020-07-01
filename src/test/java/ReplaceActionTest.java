import org.junit.Test;
import tracker.model.Item;
import tracker.StubInput;
import tracker.MemTracker;
import tracker.actions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReplaceActionTest {
    @Test
    public void whenItemReplaced() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        MemTracker tracker = new MemTracker();
        Item item = new Item("Test");
        tracker.add(item);

        String[] answers = {
                item.getId(),
                "Replaced"
        };

        StubInput input = new StubInput(answers);
        new ReplaceAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Replace complete successfully.")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }

    @Test
    public void whenItemNotReplaced() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        MemTracker tracker = new MemTracker();
        Item item = new Item("Test");
        tracker.add(item);

        String[] answers = {
                "0",
                "Replaced"
        };

        StubInput input = new StubInput(answers);
        new ReplaceAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Cannot find item with entered id.")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }
}
