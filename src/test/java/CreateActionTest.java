import org.junit.Test;
import tracker.model.Item;
import tracker.StubInput;
import tracker.MemTracker;
import tracker.actions.CreateAction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CreateActionTest {
    @Test
    public void whenCreateItem() {
        String[] answers = {"Test Item"};
        MemTracker tracker = new MemTracker();
        StubInput input = new StubInput(answers);
        new CreateAction().execute(input, tracker);
        Item created = tracker.findAll().get(0);
        Item expected = new Item("Test Item");
        assertThat(created.getName(), is(expected.getName()));
    }
}
