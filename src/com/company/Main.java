package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class Main
{
    public static void main(String[] args) {
        var n = 1_000_000_289L;

        // values passed as closures must be final:
        final var result = new Result();

        var thread = new Thread(() -> {
            // this lambda can see the variable 'result',
            // because it has been enclosed in a CLOSURE:
            result.isPrime = isPrime(n);
        });
        thread.start();

        var timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run() {
                if (result.isPrime != null) {
                    timer.cancel();
                    System.out.println(n + " is prime? " + result.isPrime);
                } else {
                    System.out.print(".");
                }
            }
        }, 0, 500);
    }

    static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class Result
{
    public Boolean isPrime;
}
