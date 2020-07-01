package tracker.model;

import java.util.Comparator;
/**
 * 0. Сортировка[#244999]
 * Реализует компаратор по имени
 * @author Kirill Asmanov
 * @since 24.03.2020
 */
public class SortByNameItem implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
