package jdk8;

import jdk动态代理.Chinese;
import jdk动态代理.JdkProxyFactory;
import jdk动态代理.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("qq");
        s1.setScore(77);
        s1.setSubject("语文");
        Student s2 = new Student();
        s2.setName("qq");
        s2.setScore(88);
        s2.setSubject("数学");
        Student s3 = new Student();
        s3.setName("aa");
        s3.setScore(99);
        s3.setSubject("语文");
        Student s4 = new Student();
        s4.setName("aa");
        s4.setScore(82);
        s4.setSubject("数学");
        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s4);
        List<Student> collect = students.stream().filter(student -> student.score > 80).//过滤出ma'p中分数大于80的学生对象
                distinct().//对整个学生对象去重
                limit(5).//取前五个对象
                skip(1).//跳过第一个对象
                collect(Collectors.toList());//把流转成list集合
       // System.out.println(collect.toString());
        //根据学生的名字去重
        List<Student> collect1 = students.stream().filter(distinctByKey(student -> student.name))//filter(Predicate<? super T> predicate) 执行为true才保留这个对象
                .collect(Collectors.toList());
        System.out.println(collect1);

        //函数式接口：有且仅有一个抽象方法的接口(正好符合Lambda表达式条件)
        //1.判断型接口：predicate  唯一抽象方法test()
        Predicate<Student> p1 = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.score>80;
            }

        };
        Predicate<Student> p2 = student -> student.name.equals("qq");
        System.out.println(p1.test(s1));//输出false
        System.out.println(p1.negate().test(s1));//输出true  negate()=!
        System.out.println(p1.and(p2).test(s2));//输出true  条件：分数大于80并且name为qq。 and()=&
        System.out.println(p1.or(p2).test(s3));//输出true   条件：分数大于80或者name为qq.  or()=|
        //2.函数型接口 Function  唯一抽象方法 apply()
        //“应用于先做什么，再做什么”的场景
        Function function = new Function<Student,Object>() {
            @Override
            public Object apply(Student o) {
                System.out.println("function："+o);
                o.setScore(o.score+10);
                return o;
            }
        };
        Function<Student,Student> function1 = student -> {
            System.out.println("function1:"+student);
            student.setName("23333");
            return student;
        };
        function.andThen(function1).apply(s1);//andThen(）  先执行function,function的返回值作为function1的参数
        function.compose(function1).apply(s1);//compose()   先执行function1，

        //3.消费型接口 Consumer 唯一抽象方法 accept
        //与Function的区别是Consumer没有返回值
        Consumer<Student> consumer = new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                student.setName("consumer");
                System.out.println(student);
            }
        };
        Consumer<Student> consumer1 = student -> {
            student.setName("consumer1");
            System.out.println(student);
        };
        consumer.andThen(consumer1).accept(s1);

        //生产型接口 Supplier  唯一抽象方法 T  get();
        //get()方法 不传入参数，返回一个值   用于创建对象
        Supplier<Student> supplier = new Supplier<Student>() {
            @Override
            public Student get() {
                return new Student();
            }
        };
        Student student = supplier.get();
        Supplier<Student> supplier1 = ()->new Student();//Supplier简化语法
        Supplier<Student> supplier2 = Student::new;//极简语法--构造器引用


    }

//使用流在list中根据对象某一属性去重
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();//并行流使用时可能出现并发问题，所以此处用currentHashMap
        return t -> seen.putIfAbsent(keyExtractor.apply(t), true) == null;//keyExtractor.apply()得到学生姓名
        //putIfAbsent   如果传入key对应的value已经存在，就返回存在的value，不进行替换。如果不存在，就添加key和value，返回null
        //返回null就证明不重复，执行该Predicate就返回true
        //keyExtractor.apply(t)是集合的key,true是集合的value,value并没有什么用，只是因为需要用map的key去重，而value必须有值
    }

}