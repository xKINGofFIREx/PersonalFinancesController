package manager;

import model.Transaction;
import model.TransactionType;

import java.util.Map;
import java.util.HashMap;

public class TransactionManager {
    private static long ids = 1;
    private static Map<Long, Transaction> transactions = new HashMap<>(); 
    
    public static void addTransaction(Transaction transaction) {
        transaction.setId(ids++);
        transactions.put(transaction.getId(), transaction);
    }

    public static void updateTransaction(Transaction transaction) {
        transactions.put(transaction.getId(), transaction);
    }

    public static void deleteTranscation(long transId) {
        transactions.remove(transId);
    }

    public static void showAllTransactions() {
        transactions.values().forEach(System.out::println);
    }

    public static void showBalance() {
        Long balance = transactions.values().stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .map(Transaction::getSum)
                .reduce(0L, Long::sum) - transactions.values().stream()
                .filter(t -> t.getType() == TransactionType.OUTCOME)
                .map(Transaction::getSum)
                .reduce(0L, Long::sum);

        System.out.println("Текущий баланс: " + balance);
    }

    public static void showStatistics() {

    }
}
