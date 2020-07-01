import org.junit.Test;
import tracker.model.Item;
import tracker.model.SortByNameItem;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortByNameItemTest {
    @Test
    public void sortByNameReverse() {
        List<Item> testList = new ArrayList<>();
        Item a = new Item("A");
        Item b = new Item("B");
        Item c = new Item("C");
        testList.add(b);
        testList.add(a);
        testList.add(c);
        testList.sort(new SortByNameItem());
        List<Item> expect = new ArrayList<>();
        expect.add(a);
        expect.add(b);
        expect.add(c);
        assertThat(testList, is(expect));
    }
}