package com.example.spring08fpt.service;

import java.text.DecimalFormat;

public class InTien {
    public void In(int money) {
        DecimalFormat format = new DecimalFormat("#,###");
        final int FIVE_HUNDRED_THOUSAND = 5000000;
        final int TWO_HUNDRED_THOUSAND = 200000;
        final int ONE_HUNDRED_THOUSAND = 100000;
        final int FIFTY_THOUSAND = 50000;
        final int TWEENTY_THOUSAND = 20000;
        final int TEN_THOUSAND = 10000;

        int fiveHunderdThousand = 0;
        int twoHunderdThousand = 0;
        int oneHunderdThousand = 0;
        int fiftyThousand = 0;
        int tweentyThousand = 0;
        int tenThousand = 0;
        do {
            if(money >=500000){
                fiveHunderdThousand = money /500000;
                money = money % 500000;
                System.out.printf("Mệnh giá tiền %sđ có : %d tờ\n", format.format(500000), fiveHunderdThousand);
            }
            if(money >= 200000){
                twoHunderdThousand = money / 200000;
                money = money % 200000;
                System.out.printf("Mệnh giá tiền %sđ có : %d tờ\n", format.format(200000), twoHunderdThousand);
            }
            if(money >= 100000){
                oneHunderdThousand = money / 100000;
                money = money % 100000;
                System.out.printf("Mệnh giá tiền %sđ có : %d tờ\n", format.format(100000), oneHunderdThousand);
            }
            if(money >= 50000){
                fiftyThousand = money / 50000;
                money = money % 50000;
                System.out.printf("Mệnh giá tiền %sđ có : %d tờ\n", format.format(50000), fiftyThousand);
            }
            if(money >= 20000){
                tweentyThousand = money / 20000;
                money = money % 20000;
                System.out.printf("Mệnh giá tiền %sđ có : %d tờ\n", format.format(20000), tweentyThousand);
            }
            if(money >= 10000){
                tenThousand = money / 10000;
                money = money % 10000;
                System.out.printf("Mệnh giá tiền %sđ có : %d tờ", format.format(10000), tenThousand);
            }
        } while (money != 0);
    }
}
