package 类加载;

public class Fu {
    static {
        System.out.println("0");
    }
    public static String a = "111";
    {
        System.out.println("00");
    }
}

