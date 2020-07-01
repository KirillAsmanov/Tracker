package tracker.actions;

import tracker.Input;
import tracker.Store;
import tracker.model.Item;

/**
 * Класс команды поиска элементов по id
 * tracker
 * @author Kirill Asmanov
 * @since 26.03.2020
 */
public class FindByIdAction implements UserAction {
    /**
     * содержит имя операции
     *
     * @return - имя
     */
    @Override
    public String name() {
        return "=== Find by id ===";
    }

    /**
     * Выполняет операцию
     *
     * @param input   - вводимые данные
     * @param tracker - хранилище данных
     * @return - true, если это не операция выхода из системы
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Name: " + item.getName());
        } else {
            System.out.println("Cannot find item with entered id.");
        }
        return true;
    }
}
