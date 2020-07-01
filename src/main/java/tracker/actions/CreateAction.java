package tracker.actions;

import tracker.Input;
import tracker.Store;
import tracker.model.Item;

/**
 * Класс команды добавления элементов в массив
 * tracker
 * @author Kirill Asmanov
 * @since 26.03.2020
 */
public class CreateAction implements UserAction {
    /**
     * содержит имя операции
     *
     * @return - имя
     */
    @Override
    public String name() {
        return "=== Create new item ==";
    }

    /**
     * Выполняет операцию добавления нового элемента в массив
     *
     * @param input   - вводимые данные
     * @param tracker - хранилище данных
     * @return - true, если это не операция выхода из системы
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        return true;
    }
}
