package tracker.model;

import java.util.Objects;
/**
 * Класс модели заявки
 * tracker
 * @author Kirill Asmanov
 * @since 22.03.2020
 */
public class Item {
    /**
     * Содержит уникальный ключ заявки
     */
    private String id;
    /**
     * Содержит имя заявки
     */
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id)
                && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
