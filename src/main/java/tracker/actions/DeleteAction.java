package tracker.actions;

import tracker.Input;
import tracker.Store;


/**
 * Класс команды удаления элементов по id
 * tracker
 * @author Kirill Asmanov
 * @since 26.03.2020
 */
public class DeleteAction implements UserAction {
    /**
     * содержит имя операции
     *
     * @return - имя
     */
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    /**
     * Выполняет операцию удаления
     *
     * @param input   - вводимые данные
     * @param tracker - хранилище данных
     * @return - true, если это не операция выхода из системы
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        String id = input.askStr("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Delete complete successfully.");
        } else {
            System.out.println("Cannot find item with entered id.");
        }
        return true;
    }
}
