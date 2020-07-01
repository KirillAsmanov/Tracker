import org.junit.Test;
import tracker.model.Item;
import tracker.StubInput;
import tracker.MemTracker;

import tracker.actions.FindByNameAction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class FindByNameActionTest {
    @Test
    public void whenItemsFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Du");
        Item item2 = new Item("Du");
        Item item3 = new Item("Hast");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String[] answers = {
                item2.getName()
        };
        StubInput input = new StubInput(answers);
        new FindByNameAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item1.getId() + ". " + item1.getName())
                .add(item2.getId() + ". " + item2.getName())
                .add("==== End of array ====")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }

    @Test
    public void whenItemsNotFound() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));

        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Du");
        Item item2 = new Item("Du");
        Item item3 = new Item("Hast");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String[] answers = {
                "mich"
        };
        StubInput input = new StubInput(answers);
        new FindByNameAction().execute(input, tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Cannot find item with entered name.")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));

        System.setOut(def);
    }
}
