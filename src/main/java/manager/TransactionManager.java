package manager;

import model.Transaction;
import model.TransactionType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class TransactionManager {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static long ids = 1;
    private static Map<Long, Transaction> transactions = new HashMap<>();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

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
                .reduce(0L, Long::sum)
                - transactions.values().stream()
                        .filter(t -> t.getType() == TransactionType.OUTCOME)
                        .map(Transaction::getSum)
                        .reduce(0L, Long::sum);

        System.out.println("Текущий баланс: " + balance);
    }

    public static void showStatistics(LocalDate startDate, LocalDate endDate) {
        Long balance = transactions.values().stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .filter(t -> t.getDate().isAfter(startDate))
                .filter(t -> t.getDate().isBefore(endDate))
                .map(Transaction::getSum)
                .reduce(0L, Long::sum)
                - transactions.values().stream()
                        .filter(t -> t.getType() == TransactionType.OUTCOME)
                        .filter(t -> t.getDate().isAfter(startDate))
                        .filter(t -> t.getDate().isBefore(endDate))
                        .map(Transaction::getSum)
                        .reduce(0L, Long::sum);

        System.out.println("Текущий баланс: " + balance);
    }

    public static void saveTransactions() {
        try {
            objectMapper.writeValue(new File("src/main/resources/transactions.json"), transactions);
        } catch (IOException e) {
            System.out.println("Неудалось сохранить трансакции в файл");
            e.printStackTrace();
        }
    }

    public static void loadTrasactions() {
        try {
            transactions = objectMapper.readValue(new File("src/main/resources/transactions.json"),
                    new TypeReference<HashMap<Long, Transaction>>() {
                    });
        } catch (IOException e) {
            System.out.println("Неудалось прочитать файл");
        }
    }
}
