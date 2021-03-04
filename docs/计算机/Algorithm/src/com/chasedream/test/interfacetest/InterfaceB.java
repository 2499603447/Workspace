package cn.chasedream.interfacetest;

public interface InterfaceB {
    /**
     * 静态方法
     */
    static void showStatic() {
        System.out.println("InterfaceB++showStatic");
    }

    /**
     * 默认方法
     */
    default void showDefault() {
    }
    void print();

}