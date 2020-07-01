import org.junit.Test;
import tracker.model.Item;
import tracker.MemTracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNull.nullValue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenAddNewThreeItemsAndFindAll() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        List<Item> result = tracker.findAll();
        List<Item> excepted = new ArrayList<>();
        excepted.add(item1);
        excepted.add(item2);
        excepted.add(item3);

        assertThat(result, is(excepted));
    }

    @Test
    public void whenFindByName() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("I have a twin");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("I have a twin");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);

        List<Item> result = tracker.findByName("I have a twin");
        List<Item> excepted = new ArrayList<>();
        excepted.add(item1);
        excepted.add(item4);

        assertThat(result, is(excepted));
    }

    @Test
    public void whenFindByNameAndHasNoMatches() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("I have a twin");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("I have a twin");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);

        List<Item> result = tracker.findByName("you can't find me");
        List<Item> excepted = new ArrayList<>();

        assertThat(result, is(excepted));
    }

    @Test
    public void whenFindByID() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item1);
        tracker.add(item2);

        Item result = tracker.findById(item2.getId());
        assertThat(result.getName(), is(item2.getName()));
    }

    @Test
    public void whenFindByIdAndHasNoMatches() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        tracker.add(item1);
        tracker.add(item2);

        Item result = tracker.findById("Such id cannot exist");
        Item excepted = null;
        assertThat(result, is(excepted));
    }

    @Test
    public void whenReplace() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }


}
