# Java基本句法

Java程序是一个集合，这个集合中包含许多对象；这些对象彼此之间通过方法调用进行交流，从而达到良好协作的目的。下面是对Java中[类和对象](https://www.geeksforgeeks.org/classes-objects-java/)、[方法](geeksforgeeks.org/methods-in-java/)、[实例变量](https://www.geeksforgeeks.org/variables-in-java/)、句法和语义的简要讨论。

## Java中的基本术语

**1、类：**类是类实例（对象）的蓝图（计划）。 它可以定义为描述与其实例相关的数据和行为的模板。

* 示例：房子的蓝图是类。

**2、对象：**对象是类的一个实例。 它是一个具有行为和状态的实体。

* 示例：汽车是一个对象，其状态为：品牌、颜色和车牌。
* 行为：在路上奔跑。

**3、方法：**对象的行为就是方法。

* 示例：燃油指示器指示汽车中剩余的燃油量。

**4、实例变量：**每个对象都有自己独特的一组实例变量。 对象的状态通常由分配给这些实例变量的值创建。

**示例：**在控制台中编译和运行 java 程序的步骤

```
javac GFG.java
java GFG
```

```java
public class GFG {
    public static void main(String[] args)
    {
        System.out.println("GFG!");
    }
}
```

**输出**

```
GFG!
```

注意：当类是public修饰的时候，文件名必须和类名一致。

## 句法

**1、Java中的注释**

在Java中有三种类型的注释 

 **i、** 单行注释

```
// System.out.println("GFG!");
```

**ii、** 多行注释

```
/*
    System.out.println("GFG!");
    System.out.println("Alice!");
*/
```

**iii、** 文档注释

```
/** documentation */
```

**2、源文件名**

源文件名必须准确匹配public类名，并以**.java**后缀结尾。如果文件中不包含任何public类，则文件名可以不一样。假设你有一个public类**GFH**。

```
GFG.java // valid syntax
gfg.java // invalid syntax
```

**3、大小写敏感**

Java是一个大小写敏感的语言，这就意味着标识符***AB,Ab,aB***以及***ab***在Java中是不一样的。

```
System.out.println("Alice"); // valid syntax
system.out.println("Alice"); // invalid syntax
```

**4、类名**

**i、**类的第一个字母应该是大写的（小写也允许，但是不推荐）

**ii、**如果使用多个单词组成类名，则每个内部单词的首字母应为大写。 允许使用下划线，但不建议使用。 还允许使用数字和货币符号，但也不鼓励使用后者，因为它们用于特殊目的（用于内部类和匿名类）。

```java
class MyJavaProgram    // valid syntax
class 1Program         // invalid syntax
class My1Program       // valid syntax
class $Program         // valid syntax, but discouraged
class My$Program       // valid syntax, but discouraged (inner class Program inside the class My)
class myJavaProgram    // valid syntax, but discouraged
```

**5、public static void main(String [] args)**

main()方法是进入Java程序的主入口；也就是从这里开始处理的。同样也允许使用求方法签名 **public static void main(String… args)**。

**6、方法名**

**i、**所有的方法名应该以小写字母开始

**ii、**如果使用多个单词组成方法名，每一个内部单词的首字母应为大写。允许使用下划线，但不建议使用。还允许使用数字和货币符号。

```
public void employeeRecords() // valid syntax
public void EmployeeRecords() // valid syntax, but discouraged
```

**7、Java标识符**

标识符是局部变量、实例和类变量以及标签的名称，也是类、包、模块和方法的名称。 所有 Unicode 字符都是合法的，而不仅仅是 ASCII 子集。

**i、**所有标识符都可以以字母、货币符号或下划线 (_) 开头。 根据惯例，变量的字母应该小写。

**ii、**标识符的第一个字符后面可以是字母、数字、货币符号和下划线的任意组合。 变量名不建议使用下划线。 常量（static final属性和枚举）应全部为大写字母。

**iii、**最重要的是标识符是大小写敏感的。

**iv、**关键字不能用作标识符，因为它是保留字段并具有某些特殊含义。

```
Legal identifiers: MinNumber, total, ak74, hello_world, $amount, _under_value
Illegal identifiers: 74ak, -amount
```

**8、Java中的空格**

仅包含空格（可能带有注释）的行称为空行，Java 编译器完全忽略它。

**9、访问修饰符：**这些修饰符控制着类和方法的作用域。

* **访问修饰符：**default、public、protected、private
* **非访问修饰符：**final、abstract、strictfp

**10、理解访问修饰符**

| 访问修饰符 | 类内部 | 包内 | 包外、仅子类 | 包外 |
| ---------- | ------ | ---- | ------------ | ---- |
| Private    | Y      | N    | N            | N    |
| Default    | Y      | Y    | N            | N    |
| Protected  | Y      | Y    | Y            | N    |
| Public     | Y      | Y    | Y            | Y    |

**11、Java关键字**

**关键字或保留字**是一种语言中用于某些内部流程或表示某些预定义操作的字词。 因此，这些词不允许用作变量名或对象。

| abstract | assert       | boolean  | break      |
| -------- | ------------ | -------- | ---------- |
| byte     | case         | catch    | char       |
| class    | const        | continue | default    |
| do       | double       | else     | enum       |
| extends  | final        | finally  | float      |
| for      | goto         | if       | implements |
| import   | instanceof   | int      | interface  |
| long     | native       | new      | package    |
| private  | protected    | public   | return     |
| short    | static       | strictfp | super      |
| switch   | synchronized | this     | throw      |
| throws   | transient    | try      | void       |
| volatile | while        |          |            |