package AlgoMap_io.HashmapsAndSets;

import java.util.HashMap;
import java.util.Map;

/*
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.
 */
public class Leetcode1189 {
    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
    }
    public static int maxNumberOfBalloons(String text) {
        int[] counts = new int[26]; int amount=0;
        for(char c: text.toCharArray()){
            counts[c-'a']++;
        }
        while(counts['b'-'a']>=1&&counts['a'-'a']>=1&&counts['l'-'a']>=2&&counts['o'-'a']>=2&&counts['n'-'a']>=1){
            amount++;
            counts['b'-'a']-=1;counts['a'-'a']-=1;counts['l'-'a']-=2;counts['o'-'a']-=2;counts['n'-'a']-=1;
        }
        return amount;
    }
    //5ms 걸리지만 HashMap 사용, containsKey 메서드 사용
    public static int maxNumberOfBalloonsWithHashMap(String text) {
        Map<Character, Integer> counter = new HashMap<>();
        String balloon = "balloon";

        for (char c : text.toCharArray()) {
            if (balloon.indexOf(c) != -1) {
                counter.put(c, counter.getOrDefault(c, 0) + 1);
            }
        }

        if (!counter.containsKey('b') ||
                !counter.containsKey('a') ||
                !counter.containsKey('l') ||
                !counter.containsKey('o') ||
                !counter.containsKey('n')) {
            return 0;
        } else {
            return Math.min(Math.min(counter.get('b'), counter.get('a')),
                    Math.min(counter.get('l') / 2,
                            Math.min(counter.get('o') / 2, counter.get('n'))));
        }
    }
}
