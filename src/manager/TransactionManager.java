package manager;

import model.Transaction;
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
}
