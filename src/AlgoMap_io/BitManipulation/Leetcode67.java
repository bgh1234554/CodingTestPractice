package AlgoMap_io.BitManipulation;

import java.math.BigInteger;

/*
Given two binary strings a and b, return their sum as a binary string.
 */
public class Leetcode67 {
    public static void main(String[] args) {

    }
    //ChatGPT - 문자열의 끝부터 한 자리씩 더하는 방식 - 실행시간 1ms
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        //맨 뒷자리부터 진짜 하나씩 더한다.
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';

            //더한 걸 이진수로 바꿔야 하나까 2로 나눠서 나머지 비트를 구해야 한다.
            sb.append(sum % 2);      // 현재 자리 비트
            carry = sum / 2;         // 다음 자리로 넘길 올림값
        }
        //뒷자리부터 더했으니까 뒤집기
        return sb.reverse().toString();
    }
    //BigInteger 클래스를 이용한 연산 - 실행시간 6ms
    public String addBinaryBigInteger(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);

        while (y.compareTo(BigInteger.ZERO) != 0) {
            /*
            이진수 덧셈의 핵심 비트 연산 원리
            x ^ y : 이진수 덧셈에서 "올림 없는 합"을 구함
            → 두 비트가 다르면 1, 같으면 0 (ex. 1+0 = 1, 1+1 = 0)

            x & y : "올림이 발생할 위치"를 계산 (1+1일 때만 1)
            shiftLeft(1) : 올림은 왼쪽으로 한 자리 이동해야 하므로 한 칸 밀어줌
            → 이게 실제로 다음 연산 때 더해질 carry 역할이 됨
             */
            BigInteger withoutCarry = x.xor(y);         // 단순 합
            BigInteger carry = x.and(y).shiftLeft(1);   // 올림값
            x = withoutCarry;
            y = carry;
        }

        return x.toString(2);
    }
}
