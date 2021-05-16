

## 泛型

Java中的泛型和c++中的templates有些类似。允许诸如Integer,  String以及用户自定义的类型作为方法，类以及接口的参数。例如, HashSet,ArrayList, HashMap等能够很好地支持泛型。我们通过泛型使用任何类型。

### 泛型类

和c++类似，在泛型类的创建过程中我们使用<>去指定参数类型。我们使用以下语法去创建泛型类对象。

```java
// To create an instance of generic class 
BaseType <Type> obj = new BaseType <Type>()

Note: In Parameter type we can not use primitives like 
      'int','char' or 'double'.
```

```java
// A Simple Java program to show working of user defined 
// Generic classes 
   
// We use < > to specify Parameter type 
class Test<T> 
{ 
    // An object of type T is declared 
    T obj; 
    Test(T obj) {  this.obj = obj;  }  // constructor 
    public T getObject()  { return this.obj; } 
} 
   
// Driver class to test above 
class Main 
{ 
    public static void main (String[] args) 
    { 
        // instance of Integer type 
        Test <Integer> iObj = new Test<Integer>(15); 
        System.out.println(iObj.getObject()); 
   
        // instance of String type 
        Test <String> sObj = 
                          new Test<String>("GeeksForGeeks"); 
        System.out.println(sObj.getObject()); 
    } 
}
```

输出：

```
15
GeeksForGeeks
```

我们也可以使用多个参数作为泛型类的参数

```java
// A Simple Java program to show multiple 
// type parameters in Java Generics 
  
// We use < > to specify Parameter type 
class Test<T, U> 
{ 
    T obj1;  // An object of type T 
    U obj2;  // An object of type U 
  
    // constructor 
    Test(T obj1, U obj2) 
    { 
        this.obj1 = obj1; 
        this.obj2 = obj2; 
    } 
  
    // To print objects of T and U 
    public void print() 
    { 
        System.out.println(obj1); 
        System.out.println(obj2); 
    } 
} 
  
// Driver class to test above 
class Main 
{ 
    public static void main (String[] args) 
    { 
        Test <String, Integer> obj = 
            new Test<String, Integer>("GfG", 15); 
  
        obj.print(); 
    } 
}
```

输出：

```
GfG
15
```

### 泛型函数

我们还可以根据传递给泛型方法的参数类型编写可供不同类型的参数调用的泛型函数，编译器会处理每个方法。

```java
// A Simple Java program to show working of user defined 
// Generic functions 
   
class Test 
{ 
    // A Generic method example 
    static <T> void genericDisplay (T element) 
    { 
        System.out.println(element.getClass().getName() + 
                           " = " + element); 
    } 
   
    // Driver method 
    public static void main(String[] args) 
    { 
         // Calling generic method with Integer argument 
        genericDisplay(11); 
   
        // Calling generic method with String argument 
        genericDisplay("GeeksForGeeks"); 
   
        // Calling generic method with double argument 
        genericDisplay(1.0); 
    } 
}
```

输出：

```
java.lang.Integer = 11
java.lang.String = GeeksForGeeks
java.lang.Double = 1.0
```

### 泛型的好处

使用泛型编写的代码有着如下的优势：

1. 代码复用：对于一些方法/类/接口我们只需要写一次，就可以用于任何我们想要的类型
2. 类型安全：使用泛型可以使得错误能够在编译时期就得到发现，而不是在运行时期（在编译时时期就知道代码的问题总是好过运行时因问题而导致运行失败）。假设你想创建一个ArrayList保存学生的姓名，如果你在程序中错误地将整型对象add进去而不是string对象，这种情况编译器是允许的；但是，当我们在运行时从ArrayList中遍历数据的时候，就会引发问题。

```java
// A Simple Java program to demonstrate that NOT using 
// generics can cause run time exceptions 
import java.util.*; 
  
class Test 
{ 
    public static void main(String[] args) 
    { 
        // Creatinga an ArrayList without any type specified 
        ArrayList al = new ArrayList(); 
  
        al.add("Sachin"); 
        al.add("Rahul"); 
        al.add(10); // Compiler allows this 
  
        String s1 = (String)al.get(0); 
        String s2 = (String)al.get(1); 
  
        // Causes Runtime Exception 
        String s3 = (String)al.get(2); 
    } 
} 
```

输出：

```
Exception in thread "main" java.lang.ClassCastException: 
   java.lang.Integer cannot be cast to java.lang.String
	at Test.main(Test.java:19)
```

> 泛型是如何解决这个问题的？
>
> 在定义ArrayList的时候，我们可以指定list只能接受String对象
>
> ```java
> // Using generics converts run time exceptions into  
> // compile time exception. 
> import java.util.*; 
> 
> class Test 
> { 
>  public static void main(String[] args) 
>  { 
>      // Creating a an ArrayList with String specified 
>      ArrayList <String> al = new ArrayList<String> (); 
> 
>      al.add("Sachin"); 
>      al.add("Rahul"); 
> 
>      // Now Compiler doesn't allow this 
>      al.add(10);  
> 
>      String s1 = (String)al.get(0); 
>      String s2 = (String)al.get(1); 
>      String s3 = (String)al.get(2); 
>  } 
> } 
> ```
>
> 输出：
>
> ```
> 15: error: no suitable method found for add(int)
>      al.add(10); 
>        ^
> ```

3. 省去了单个的类型转换：如果我们不使用泛型，在上面的例子中我们每次遍历ArrayList时，必须做类型转换。每次遍历操作都进行类型转换是一件很让人头疼的事情。如果我们已经知道我们的ArrayList只能存放string数据，那么我们就不需要每次都进行类型转换了。

```java
// We don't need to typecast individual members of ArrayList 
import java.util.*; 
  
class Test 
{ 
    public static void main(String[] args) 
    { 
        // Creating a an ArrayList with String specified 
        ArrayList <String> al = new ArrayList<String> (); 
  
        al.add("Sachin"); 
        al.add("Rahul"); 
  
        // Typecasting is not needed  
        String s1 = al.get(0); 
        String s2 = al.get(1); 
    } 
} 
```

4. 实现泛型算法：通过使用泛型，我们可以实现适用于不同类型对象的算法，并且它们同样是线程安全的。

## Optional

### 定义

> 概念

> 常见的使用场景

> 异常处理