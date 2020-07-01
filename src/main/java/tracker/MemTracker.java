package tracker;

import tracker.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс хранилища данных
 * tracker
 * @author Kirill Asmanov
 * @since 22.03.2020
 */
public class MemTracker implements Store {
    /**
     * Список для хранения заявок
     */
    private final List<Item> items = new ArrayList<>();


    /**
     * Метод реализующий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Возвращает копию массива this.items без null элементов
     * @return - массив без null элементов
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Ищет элементы по имени в массиве
     * @param key - искомое имя
     * @return - массив объектов, с совпадающими с искомым именами
     */
    public List<Item> findByName(String key) {
        List<Item> chosen = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                chosen.add(item);
            }
        }
        return chosen;
    }

    /**
     * Ищет конкретный объект в массиве по уникальному id
     * @param id - id, который ищем
     * @return искомый объект, либо null, если совпадений по id нет
     */
    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    /**
     * Производит поиск индекса по id
     * @param id - id искомого элемента
     * @return найденный индекс, либо -1, если индекса не существует
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * Меняет значение заявки на другое, при этом сохраняя id
     * @param id - id заявки, которую надо заменить
     * @param item - заявка, на которую надо заменить
     */
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Удаляет элемент из массива
     * @param id - id удаляемого элемента
     */
    public boolean delete(String id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void init() {
        // added to maintain tests
    }

    @Override
    public void close() throws Exception {
        // added to maintain tests
    }
}
