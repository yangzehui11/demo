package jdk8;

import java.util.function.BiFunction;

//方法引用
public class test2 {
    public static void main (String[] args){
        // 类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;//简化写法
        Boolean result2 = fun2.apply("hello", "world");
        System.out.println(result2);

        //类名：：静态方法
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
        Integer result = biFun2.apply(300, 200);
        System.out.println(result);


    }
}
