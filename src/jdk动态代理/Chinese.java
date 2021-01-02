package jdk动态代理;

public class Chinese implements Person {
    @Override
    public String speak() {
        System.out.println("早上好");
        return "早上好";
    }
}
