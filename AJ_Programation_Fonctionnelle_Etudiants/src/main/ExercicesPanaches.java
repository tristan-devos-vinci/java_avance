
package main;

import domaine.Trader;
import domaine.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExercicesPanaches {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        ExercicesPanaches main = new ExercicesPanaches(transactions);
        main.run();
    }

    private List<Transaction> transactions;

    public ExercicesPanaches(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void run() {
        // Complete the methods below based on the exercise descriptions
        exercice1();
        exercice2();
        exercice3();
        exercice4();
        exercice5();
        exercice6();
    }

    private void exercice1() {
        // TODO: Filter transactions of Cambridge, map to their values, and find max.
        var s = transactions
                .stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .max(Comparator.comparingInt(Integer::intValue))
                .orElse(-1);
        System.out.println(s);
    }

    private void exercice2() {
        // TODO: Filter transactions for traders in Cambridge, group by trader, and count their transactions.
        var s = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(Transaction::getTrader, Collectors.counting()));
        System.out.println(s);
    }

    private void exercice3() {
        // TODO: Filter transactions over 500, map trader names, sort by name length, find the longest.
        var s = transactions
                .stream()
                .filter(t -> t.getValue() > 500)
                .map(t -> t.getTrader().getName())
                .sorted()
                .max(Comparator.comparingInt(String::length));
        System.out.println(s);

        
    }

    private void exercice4() {
        // TODO: Group transactions by city, map to transaction values, and compute the average.
        var s = transactions
                .stream()
                .collect(Collectors.groupingBy(t -> t.getTrader().getCity(), Collectors.averagingInt(Transaction::getValue)));
        System.out.println(s);
    }

    private void exercice5() {
        // TODO: Filter transactions in Milan, map to values, find the min, and handle empty results.
        var s = transactions
                .stream()
                .filter((t -> t.getTrader().getCity().equals("Milan")))
                .map(Transaction::getValue)
                .min(Comparator.comparingInt(Integer::intValue))
                .orElse(-1);
        System.out.println(s);
    }
    private void exercice6() {
        // TODO: group transaction by year
        Map<Integer, List<Transaction>> pg = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getYear));
        System.out.println(pg);
    }
}
