package tracker;
/**
 * Класс ввода данных в программу для тестирования
 * tracker
 * @author Kirill Asmanov
 * @since 24.03.2020
 */
public class StubInput implements Input {
    /**
     * Содержит массив введенных пользователем ответов
     */
    private String[] answers;
    /**
     * Содержит указатель на текущий индекс в массиве answers
     */
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * просит пользователя ввести строку
     * @param question - входное сообщение
     * @return - введенная строка
     */
    @Override
    public String askStr(String question) {
        return answers[position++];
    }

    /**
     * просит пользователя ввести число
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
