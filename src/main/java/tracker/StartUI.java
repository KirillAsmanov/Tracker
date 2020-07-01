package tracker;

import tracker.actions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс интерфейса программы
 * tracker
 * @author Kirill Asmanov
 * @since 23.03.2020
 */
public class StartUI {
    /**
     * Осуществляет связь между элементами меню и логикой приложения
     * @param input - интерфейс ввода данных
     * @param tracker - экземпляр хранилища
     */
    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        this.showMenu(actions);
        while (run) {
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    /**
     * Выводит в консоль меню приложения
     */
    private void showMenu(List<UserAction> actions) {
        System.out.println("Menu:");
        for (UserAction action : actions) {
            System.out.println(actions.indexOf(action) + ". " + action.name());
        }
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction());
            actions.add(new ShowAllAction());
            actions.add(new ReplaceAction());
            actions.add(new DeleteAction());
            actions.add(new FindByIdAction());
            actions.add(new FindByNameAction());
            actions.add(new QuitAction());
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
