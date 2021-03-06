package cn.chasedream.classtest;

public class Test{
    private static float f=1.0f;
    static class InnerClass{
        protected static float func(){return f;}
    }
    public static void main(String[] args) {
        //System.out.println(new B().getValue());

        System.out.println((-12) % (-5));
        System.out.println((-12) % (5)); 
        System.out.println((12) % (-5));
        System.out.println((-5) % (-12));
    }
    static class A {
        protected int value;
        public A (int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value= value;
        }
        public int getValue() {
            try {
                value ++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
        }
    }
    static class B extends A {
        public B () {
            super(5);
            setValue(getValue()- 3);
        }

        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
