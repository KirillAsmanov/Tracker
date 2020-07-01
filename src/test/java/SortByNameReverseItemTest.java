import org.junit.Test;
import tracker.model.Item;
import tracker.model.SortByNameReverseItem;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortByNameReverseItemTest {
    @Test
    public void sortByNameReverse() {
        List<Item> testList = new ArrayList<>();
        Item a = new Item("A");
        Item b = new Item("B");
        Item c = new Item("C");
        testList.add(a);
        testList.add(b);
        testList.add(c);
        testList.sort(new SortByNameReverseItem());
        List<Item> expect = new ArrayList<>();
        expect.add(c);
        expect.add(b);
        expect.add(a);
        assertThat(testList, is(expect));
    }
}
