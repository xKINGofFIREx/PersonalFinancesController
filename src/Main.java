import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Thread;
import java.time.LocalDate;

import manager.TransactionManager;
import model.Transaction;
import model.TransactionType;

public class Main {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader clReader = new BufferedReader(new InputStreamReader(System.in)); 

        while (true) {
            clearConsole();

            System.out.println("1. Добавить транзакцию");
            System.out.println("2. Просмотреть транзакции");
            System.out.println("3. Просмотреть общий баланс");
            System.out.println("4. Просмотреть статистику");
            System.out.println("5. Выход");
            System.out.print("Валидация ввода: ");

            try {
                int input = Integer.parseInt(clReader.readLine());

                if (input == 1) {
                    Transaction transaction = new Transaction();

                    clearConsole();

                    System.out.print("Введите тип транзакции(0 - income, 1 - outcome): ");
                    transaction.setType(Integer.parseInt(clReader.readLine()) == 1 
                                    ? TransactionType.OUTCOME : TransactionType.INCOME);

                    System.out.print("Введите сумму транзакции: ");
                    transaction.setSum(Integer.parseInt(clReader.readLine()));

                    System.out.print("Введите дату транзакции(yyyy-mm-dd): ");
                    transaction.setDate(LocalDate.parse(clReader.readLine()));

                    TransactionManager.addTransaction(transaction);
                } else if (input == 2) {
                    clearConsole();

                    TransactionManager.showAllTransactions();

                    pressEnter();
                    clReader.readLine();
                } else if (input == 3) {
                    clearConsole();

                    TransactionManager.showBalance();

                    pressEnter();
                    clReader.readLine();
                } else if (input == 4) {

                } else if (input == 5) {
                    clearConsole();
                    break;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                clearConsole();
                System.out.println("Неверная команда, число должно быть одним из предложенных");
                Thread.sleep(3000);
            }
        }

        clReader.close();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[J");
    }

    private static void pressEnter() {
        System.out.print("Для выхода из просмотра нажмите - Enter");
    }
}
