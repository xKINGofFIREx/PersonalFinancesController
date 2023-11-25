package model;

import java.time.LocalDate;

public class Transaction {
    private long id;
    private TransactionType type;
    private long sum;
    private LocalDate date;

    public Transaction(long id, TransactionType type, long sum, LocalDate date) {
        this.id = id;
        this.type = type;
        this.sum = sum;
        this.date = date;
    }

    public Transaction() {

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return this.type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public long getSum() {
        return this.sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
