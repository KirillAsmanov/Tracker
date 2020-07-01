import org.junit.Test;
import tracker.model.Item;
import tracker.StubInput;
import tracker.MemTracker;

import tracker.actions.FindByIdAction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindByIdActionTest {
    @Test
    public void whenItemFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        MemTracker tracker = new MemTracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                item.getId()
        };
        StubInput input = new StubInput(answers);
        new FindByIdAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Name: " + item.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }

    @Test
    public void whenItemNotFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        MemTracker tracker = new MemTracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                "0"
        };
        StubInput input = new StubInput(answers);
        new FindByIdAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Cannot find item with entered id.")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }
}
