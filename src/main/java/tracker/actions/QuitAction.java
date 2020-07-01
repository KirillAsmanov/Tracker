package tracker.actions;

import tracker.Input;
import tracker.Store;

/**
 * Класс команды выхода
 * tracker
 * @author Kirill Asmanov
 * @since 26.03.2020
 */
public class QuitAction implements UserAction {
    /**
     * содержит имя операции
     *
     * @return - имя
     */
    @Override
    public String name() {
        return "=== Quit ===";
    }

    /**
     * Выполняет операцию выхода
     *
     * @param input   - вводимые данные
     * @param tracker - хранилище данных
     * @return - true, если это не операция выхода из системы
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        return false;
    }
}
