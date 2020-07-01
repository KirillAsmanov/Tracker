package tracker.actions;

import tracker.Input;
import tracker.Store;


/**
 * Интерфейс команды
 * tracker
 * @author Kirill Asmanov
 * @since 23.03.2020
 */
public interface UserAction {
    /**
     * содержит имя операции
     * @return - имя
     */
    String name();

    /**
     * Выполняет операцию
     * @param input - вводимые данные
     * @param tracker - хранилище данных
     * @return - true, если это не операция выхода из системы
     */
    boolean execute(Input input, Store tracker);
}
