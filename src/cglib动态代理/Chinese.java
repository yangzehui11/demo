package cglib动态代理;

import jdk动态代理.Person;

public class Chinese implements Person {
    @Override
    public String speak() {
        System.out.println("早上好");
        return "早上好";
    }
}
