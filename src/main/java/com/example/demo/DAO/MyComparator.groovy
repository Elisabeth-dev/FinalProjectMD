package com.example.demo.DAO

import com.example.demo.entity.BankCard

class MyComparator implements Comparator<BankCard>{


    int compare(BankCard o1, BankCard o2) {
        if(o1.getCardNumber() > o2.getCardNumber()){
            return 1;
        } else if (o1.getCardNumber() < o2.getCardNumber()){
            return -1;
        } else {
            return 0;
        }
    }
}
