package cn.chasedream.exception;

import com.chasedream.utils.Out;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ExceptionHandle {
    public static void main(String[] args) {
        ExceptionHandle eh = new ExceptionHandle();
//        try {
//            eh.test();
//        } catch (FileNotFoundException e){
//            e.printStackTrace(); // 此处打印堆栈信息，工程项目中不建议打印堆栈
//        }

        try {
            Out.println("aa:" + func());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test() throws FileNotFoundException {
        method1();
    }

    private void method1() throws FileNotFoundException {
        method2();
    }

    private void method2() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    private static int func() throws Exception {
        int elm = 1;
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 2; i++) {
            try {
                throw new Exception("bb");
            } catch (Exception ex) {
                throw  ex;
                //ex.printStackTrace();
            } finally {
                continue;
            }
        }

        return 0;
    }
}
