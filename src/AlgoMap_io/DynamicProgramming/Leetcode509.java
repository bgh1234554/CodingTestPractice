package AlgoMap_io.DynamicProgramming;
/*
The Fibonacci numbers, commonly denoted F(n) form a sequence,
called the Fibonacci sequence,
such that each number is the sum of the two preceding ones,
starting from 0 and 1.

That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.

Given n, calculate F(n).
 */
public class Leetcode509 {
    public int fib(int n){
        int[] fibs = new int[n+2]; //n+1로 하면 n이 0일때 IndexOutOfBounds 발생
        fibs[0] = 0; fibs[1] = 1;
        for(int i = 2; i <= n; i++){
            fibs[i] = fibs[i-1] + fibs[i-2];
        }
        return fibs[n];
    }
}
