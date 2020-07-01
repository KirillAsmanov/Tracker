import org.junit.Test;
import tracker.model.Item;
import tracker.StubInput;
import tracker.MemTracker;
import tracker.actions.DeleteAction;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class DeleteActionTest {
    @Test
    public void whenDeleteItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("new item");
        tracker.add(item);
        String[] answers = {
                item.getId()
        };
        StubInput input = new StubInput(answers);
        new DeleteAction().execute(input, tracker);
        Item deleted = tracker.findById(answers[0]);
        assertThat(deleted, is(nullValue()));
    }
}
