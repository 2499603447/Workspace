# JVM如何工作 - JVM架构

前面简单介绍了JVM的工作原理，接下来我们从类加载器的三个主要活动：加载、链接和初始化展开来讨论下其工作原理。

### **加载**

类加载器读取"*.class*"文件，生成对应的二进制数据并保存在方法区。对每一个"*.class*"文件，JVM在方法区存储以下信息。

* 所加载的类以及其直接父类的完全限定名
* "*.class*"是否与Class或Interface或Enum相关
* 修饰符、变量和方法信息等。

加载“.class”文件后，JVM 在堆内存中创建一个 Class 类型的对象来表示该文件。 请注意，该对象是 java.lang 包中预定义的 Class 类型。 程序员可以使用这些 Class 对象来获取类级别的信息，例如类的名称、父名称、方法和变量信息等。要获取此对象引用，我们可以使用 Object 类的 getClass() 方法。

```java
// A Java program to demonstrate working
// of a Class type object created by JVM
// to represent .class file in memory.
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Java code to demonstrate use
// of Class object created by JVM
public class Test {
	public static void main(String[] args)
	{
		Student s1 = new Student();

		// Getting hold of Class
		// object created by JVM.
		Class c1 = s1.getClass();

		// Printing type of object using c1.
		System.out.println(c1.getName());

		// getting all methods in an array
		Method m[] = c1.getDeclaredMethods();
		for (Method method : m)
			System.out.println(method.getName());

		// getting all fields in an array
		Field f[] = c1.getDeclaredFields();
		for (Field field : f)
			System.out.println(field.getName());
	}
}

// A sample class whose information
// is fetched above using its Class object.
class Student {
	private String name;
	private int roll_No;

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getRoll_no() { return roll_No; }
	public void setRoll_no(int roll_no)
	{
		this.roll_No = roll_no;
	}
}

```

**输出**

```
Student
getName
setName
getRoll_no
setRoll_no
name
roll_No
```

**注意：**对于每个加载的“.class”文件，只会创建一个类的对象。

```java
Student s2 = new Student();
// c2 will point to same object where 
// c1 is pointing
Class c2 = s2.getClass();
System.out.println(c1==c2); // true
```

### **链接**

执行验证、准备和解析（可选）。

验证：它确保 .class 文件的正确性，即检查该文件是否由有效的编译器正确格式化和生成。 如果验证失败，我们会得到运行时异常 java.lang.VerifyError。 这个活动过程由组件 ByteCodeVerifier 完成。 一旦这个活动完成，那么类文件就可以编译了。
准备工作：JVM 为类变量分配内存并将内存初始化为默认值。
解析：这是将符号引用替换为直接引用的过程。 它是通过搜索方法区域来定位引用的实体来完成的。

### **初始化**

在这个阶段，所有静态变量都被分配了它们在代码和静态块（如果有的话）中定义的值。 这在类中从上到下执行，在类层次结构中从父到子执行。
一般来说，有三种类加载器：

* 引导类加载器：每个 JVM 实现都必须有一个引导类加载器，能够加载受信任的类。 它加载存在于“JAVA_HOME/jre/lib”目录中的核心 java API 类。 这条路径通常被称为引导路径。 它以 C、C++ 等本地语言实现。
* 扩展类加载器：它是引导类加载器的子类。 它加载扩展目录“JAVA_HOME/jre/lib/ext”（扩展路径）或 java.ext.dirs 系统属性指定的任何其他目录中存在的类。 它由 sun.misc.Launcher$ExtClassLoader 类在 java 中实现。
* System/Application 类加载器：它是扩展类加载器的子类。 它负责从应用程序类路径加载类。 它在内部使用映射到 java.class.path 的环境变量。 它也由 sun.misc.Launcher$AppClassLoader 类在 Java 中实现。

```java
// Java code to demonstrate Class Loader subsystem
public class Test {
	public static void main(String[] args)
	{
		// String class is loaded by bootstrap loader, and
		// bootstrap loader is not Java object, hence null
		System.out.println(String.class.getClassLoader());

		// Test class is loaded by Application loader
		System.out.println(Test.class.getClassLoader());
	}
}

```

**输出**

```
null
jdk.internal.loader.ClassLoaders$AppClassLoader@8bcc55f
```

**注意：**

JVM 遵循Delegation-Hierarchy 原则来加载类。 系统类加载器将加载请求委托给扩展类加载器，扩展类加载器将请求委托给引导类加载器。 如果在引导路径中找到类，则加载该类，否则请求再次传输到扩展类加载器，然后再传输到系统类加载器。 最后，如果系统类加载器加载类失败，就会得到运行时异常*java.lang.ClassNotFoundException*。

![jvm](assets/images/jvmclassloader.jpg)

### JVM内存

* **方法区：**在方法区，存储了类名、直接父类名、方法和变量信息等所有类级别的信息，包括静态变量。 每个JVM只有一个方法区，它是一个共享资源。
* **堆区：**所有对象的信息都存放在堆区。 每个 JVM 也有一个堆区。 它也是一种共享资源。
* **栈区：**对于每个线程，JVM 创建一个存储在这里的运行时栈。 该栈的每个块都称为栈帧，其中存储方法调用。 该方法的所有局部变量都存储在它们相应的栈帧中。 线程终止后，其运行时栈将被 JVM 销毁。 它不是共享资源。
* **PC寄存器：**存放线程当前执行指令的地址。 显然，每个线程都有单独的 PC 寄存器。
* **本机方法栈：**对于每个线程，都会创建一个单独的本地栈。 它存储本地方法信息。

![jvm2](assets/images/jvm-memory-2.jpg)

**执行引擎**

执行引擎执行“.class”（字节码）。 它逐行读取字节码，使用各种内存区域中存在的数据和信息并执行指令。 它可以分为三个部分：

* **解释器：**逐行解释字节码，然后执行。 这里的缺点是当一个方法被多次调用时，每次都需要解释。
* **即时编译器（JIT）：**用于提高解释器的效率。 它编译整个字节码并将其更改为本地代码，因此每当解释器看到重复的方法调用时，JIT 会为该部分提供直接的本地代码，因此不需要重新解释，从而提高了效率。
* **垃圾收集器：**它销毁未引用的对象。 有关垃圾收集器的更多信息，请参阅[垃圾收集器](https://www.geeksforgeeks.org/garbage-collection-java/)。

**Java 本地接口（JNI）**

它是与本地方法库交互并提供执行所需的本地库（C、C++）的接口。 它使 JVM 能够调用 C/C++ 库。

**本地方法库**

它是执行引擎所需的本地库（C、C++）的集合。