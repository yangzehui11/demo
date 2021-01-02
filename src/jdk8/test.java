package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args){

        Student z1 = new Student();
        z1.setName("qq");
        z1.setScore(77);
        z1.setSubject("语文");
        Student z2 = new Student();
        z2.setName("qq");
        z2.setScore(88);
        z2.setSubject("数学");
        Student z3 = new Student();
        z3.setName("aaaa");
        z3.setScore(99);
        z3.setSubject("语文");
        Student z4 = new Student();
        z4.setName("aaaaaaaa");
        z4.setScore(82);
        z4.setSubject("数学");
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i <100000 ; i++) {
            students.add(z1);
            students.add(z2);
            students.add(z3);
            students.add(z4);
        }

        //串行顺序流 stream
        //当循环次数为100000时，输出不依然是有序的 证明是在单线程执行
        //students.stream().filter(student -> student.score > 80).forEach(student -> System.out.println(student));

        //并行流 parallelStream
        //当循环次数为100000时，输出不是有序的 证明是在多线程执行
       // students.parallelStream().filter(student -> student.score > 80).forEach(student -> System.out.println(student));

        //此时执行是有序的 证明sequential()可以把并行流转为串行流
        students.parallelStream().sequential().filter(student -> student.score > 80).forEach(student -> System.out.println(student));
    }
}
