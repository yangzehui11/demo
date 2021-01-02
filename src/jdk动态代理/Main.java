package jdk动态代理;

public class Main {
    public static void main(String[] args) {

        Person person = (Person) JdkProxyFactory.getProxy(new Chinese());
        person.speak();
    }
}