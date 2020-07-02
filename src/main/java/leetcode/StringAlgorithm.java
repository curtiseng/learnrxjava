package leetcode;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author yangzifeng
 */
public class StringAlgorithm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String path = in.next();
        System.out.println(path(path));
    }

    static String path(String path) {
        StringBuilder outPath = new StringBuilder();
        Stack<java.lang.String> input = new Stack<>();
        Stack<java.lang.String> output = new Stack<>();
        java.lang.String[] pathArr = path.split("/");
        for (java.lang.String s : pathArr) {
            input.push(s);
        }
        while (!input.empty()) {
            java.lang.String item = input.pop();
            if ("..".equals(item)) {
                input.pop();
                output.push(input.pop());
            } else if (".".equals(item)) {
                output.push(input.pop());
            } else {
                output.push(item);
            }
        }
        outPath.append(output.pop());
        while (!output.empty()) {
            outPath.append("/").append(output.pop());
        }
        return outPath.toString();
    }
}
