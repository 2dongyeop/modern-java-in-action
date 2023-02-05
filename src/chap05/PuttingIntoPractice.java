package chap05;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PuttingIntoPractice {

    public static void main(final String... args) {
        //comment 거래자 생성 : 이름, 도시
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");


        //comment 트랜잭션 생성 : 거래자, 년도, 값
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        //comment 1: 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        List<Transaction> answer1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println("answer1 = " + answer1);


        //comment 2: 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> answer2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println("answer2 = " + answer2);


        //comment 3: 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 나열하시오.
        List<Trader> answer3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(comparing(Trader::getName))
                .distinct()
                .collect(toList());
        System.out.println("answer3 = " + answer3);


        //comment 4: 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        List<String> answer4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .distinct()
                .collect(toList());
        System.out.println("answer4 = " + answer4);


        //comment 5: 밀라노에 거래자가 있는가?
        boolean milan = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println("milan = " + milan);


        //comment 6: 케임브리지에 거주하는 거래자의 모든 트랜잭션을 출력하시오.
        List<Integer> answer6 = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .collect(toList());
        System.out.println("answer6 = " + answer6);


        //comment 7: 전체 트랜잭션 중 최댓값은 얼마인가?
        Integer max = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(0, Integer::max);
        System.out.println("max = " + max);


        //comment 8-1: 전체 트랜잭션 중 최솟값은 얼마인가?
        Optional<Integer> min = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::min);
        System.out.println("min = " + min);


        //comment 8-2
        Optional<Transaction> min2 = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println("min2 = " + min2);


        //comment 8-3
        // 가장 작은 값을 가진 거래 탐색
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));
        // 거래가 없을 때 기본 문자열을 사용할 수 있도록발견된 거래가 있으면 문자열로 바꾸는 꼼수를 사용함(예, the Stream is empty)
        System.out.println(smallestTransaction.map(String::valueOf).orElse("No transactions found"));
    }
}
