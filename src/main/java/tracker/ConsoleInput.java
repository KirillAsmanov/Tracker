package tracker;
import java.util.Scanner;
/**
 * Класс, отвечающий за ввод данных пользователем
 * tracker
 * @author Kirill Asmanov
 * @since 23.03.2020
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    /**
     * просит пользователя ввести строку и выводит входное сообщение на консоль
     * @param question - входное сообщение
     * @return - введенная строка
     */
    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
    /**
     * просит пользователя ввести число и выводит входное сообщение на консоль
     * @param question - входное сообщение
     * @return - введенное число
     */
    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    /**
     * Просит ввести пользователя число в диапазоне от 0 до max
     *
     * @param question - входное сообщение
     * @param max      - верхняя граница диапазона
     * @return - введенное число
     */
    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
