package tracker.actions;

import tracker.Input;
import tracker.Store;
import tracker.model.Item;
/**
 * Класс команды замены элемента
 * tracker
 * @author Kirill Asmanov
 * @since 26.03.2020
 */
public class ReplaceAction implements UserAction {
    /**
     * содержит имя операции
     *
     * @return - имя
     */
    @Override
    public String name() {
        return "=== Replace Item ===";
    }

    /**
     * Выполняет операцию замены элемента на новый
     *
     * @param input   - вводимые данные
     * @param tracker - хранилище данных
     * @return - true, если это не операция выхода из системы
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        String name = input.askStr("Enter a new name: ");
        Item newItem = new Item(name);
        if (tracker.replace(id, newItem)) {
            System.out.println("Replace complete successfully.");
        } else {
            System.out.println("Cannot find item with entered id.");
        }
        return true;
    }
}
