package tracker;
/**
 * Класс, отвечающий за ввод и проверку данных, введенных пользователем
 * tracker
 * @author Kirill Asmanov
 * @since 29.03.2020
 */
public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }


    /**
     * просит пользователя ввести строку
     *
     * @param question - входное сообщение
     * @return - введенная строка
     */
    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    /**
     * просит пользователя ввести число и выводит входное сообщение на консоль
     *
     * @param question - входное сообщение
     * @return - введенное число
     */
    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
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
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}

