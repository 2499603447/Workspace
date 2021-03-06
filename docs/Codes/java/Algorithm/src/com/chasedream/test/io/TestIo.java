package cn.chasedream.io;

import java.io.Writer;
import java.net.ServerSocket;
import java.time.Year;
import java.util.Date;

public class TestIo {
    public static void main(String[] args) {
        System.out.println("hello");
        String str1 = "Stringa";
        String str2 = "Stringt";
        String str3 = "Strings123";

        int result = str1.compareTo(str2);
        System.out.println(result);

        result = str2.compareTo(str3);
        System.out.println(result);

        result = str3.compareTo(str1);
        System.out.println(result);
        String s = "hello";

        String t = "hello";

        char c[] = {'h','e','l','l','o'} ;

        s.equals(t);
        t.equals(c);

        char myChar = 'g';
        String myStr = Character.toString(myChar);
        System.out.println("String is: "+myStr);
        myStr = String.valueOf(myChar);
        System.out.println("String is: "+myStr);

        float a= 1.1F;
        Enclosingone eo = new Enclosingone();
        Enclosingone.InsideOne ei=eo.new InsideOne();

    }
}
class Enclosingone
{
    public class InsideOne {}
}



abstract class Test{
    String A ="";
    char b = 's';
    public static String B ="";
    // public abstract void method(int a);
    public void method(){}
    static void getName() {

    }
    private void print() {

    }

    public static void main(String[] args) {
    }

    Test() {

    }

}
