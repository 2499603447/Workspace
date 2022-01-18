# **The Java Language Specification**

Java SE 8 Edition

 

James Gosling

Bill Joy

Guy Steele

Gilad Bracha

Alex Buckley

 

译者:张德舟

2019.06.19

## 前言

1996年，James Gosling、Bill Joy和Guy Steele为Java® Language Specification的第一版写了下面一段话：

“我们认为Java编程语言是一种成熟的语言，并为广泛使用做好了准备。 尽管如此，我们期望该语言在未来几年会有所发展和演进。 我们希望以与现有应用程序完全兼容的方式来管理这种演变。”

Java SE 8 代表了Java语言历史上最大的一次演进。相对较少的一些特性-lambda表达式、方法引用和函数型接口-这些特性组合在一起提供一种融合了面向对象和函数型风格的编程模型。在Brian Goetz的领导下，以一种鼓励最佳实践的方式完成了这种融合，包括不可变性、无状态性和组合性，同时保留了“Java的感觉”，即可读性，简单性和通用性。

至关重要的是，Java SE平台库和Java语言共同演进。这就意味着使用lambda表达式和方法引用来表示行为，例如应用于每个列表中每个   元素的操作，是高效且“开箱即用”的。类似的，Java 虚拟机亦和Java语言共同演进，以确保在独立编译的约束前提下，那些支持库演进的缺省方法能够在编译时和运行时保持一致。

自1990年代以来，就已经提出了向java语言添加一流功能的倡议。2007年左右的BGGA和CICE提案为该主题带来了新的活力，而2009年左右在OpenJDK中创建Lambda项目引起了空前的关注。在Java SE 7中向JVM添加方法句柄为新的实现技术打开了一扇门，同时保留了“一次编写，随处运行”的特性。In time, language changes were overseen by JSR 335, *Lambda Expressions for the Java Programming Language*, whose Expert Group consisted of Joshua Bloch, Kevin Bourrillion, Andrey Breslav, Rémi Forax, Dan Heidinga, Doug Lea, Bob Lee, David Lloyd, Sam Pullara, Srikanth Sankaran, and Vladimir Zakharov.

编程语言的设计通常会涉及到复杂程度的把控，而这些对语言的使用者完全是隐藏的。（因此，编程语言的设计通常被比作冰山，90%是不可见的。）在JSR 335中，最大的复杂性隐藏于隐式类型的lambda表达式与重载解析的交互中。在此以及其他领域，Oracle的Dan. Smith完成了一项杰出的工作，即详尽地描述了Lambda表达式所期望的行为。在整篇规范中都可以找到他的话，包括在全新的第18章节“类型推断”中亦可找到他的话。

Java SE 8中另一项举措是提升注解的实用性，这是java语言最流行的功能之一。首先，Java语法已经扩展为允许在许多语言结构中对类型进行注解，从而为诸如Checker Framework之类的新型静态分析工具奠定了基础。这个特性时通过由Micheael Ernst和我本人，Doug Lea和Srikanth Sankaran专家组共同主导的JSR 308《Annotations on Java Types》描述的。该规范涉及的变化范围很广，Micheal Ernst和Werner Dietl多年来的不懈努力得到了广发认可。其次，注解可以在语言结构上“重复”，这对于特定领域配置使用注解进行建模的API大受裨益。Java EE中的Micheal Keith个Bill Shannon发起并倡导了此功能。

Oracle Java平台小组的许多同事为该规范提供了宝贵的支持，他们是：Leonid Arbouzov，Mandy Chung，Joe Darcy，Robert Field，JoelBorggrén-Franck，Sonali Goel，Jon Gibbons，Jeannette Hung，Stuart Marks，Eric McCorkle，Matherey Nunez，Mark Reinhold，Vicente Romero，John Rose，Georges Saab，Steve Sides，Bernard Traversat和Michel Trudeau。

也许，最真挚的感谢必须献给将该规范变为真实软件的编译器工程师。Oracle公司的Maurizio Cimadamore早期就开始在lambda表达式的设计及其在javac中的实现方面进行着超人般的工作。Jayaprakash Arthanareeswaran, Shankha Banerjee, Anirban Chakraborty, Andrew Clement, Stephan Herrmann, Markus Keller, Jesper Møller, Manoj Palat, Srikanth Sankaran, and Olivier Thomann提供了对Eclipse中Java SE 8 功能的支持。Anna Kozlova, Alexey Kudravtsev, and Roman Shevchenko提供了对Intellij中对Java SE 8的支持。他们所做的贡献值得整个java社区的感谢。

Java SE 8是Java语言的复兴。尽管有人在寻找“下一种伟大的语言”，但我们相信使用Java编程比以往任何时候都更加令人兴奋和高效。我们也希望它对你是经久耐用的。

Alex Buckley

Santa Clara, California
March, 2014









 

## 第一章 介绍

Java®编程语言是一种通用的，并发的，基于类的，面向对象的语言。它的设计非常简单，许多程序员都可以熟练掌握。Java编程语言与C和C ++相关，但组织方式有所不同，省略了C和C ++的许多方面，同时又包含了其他语言的一些想法。它旨在成为一种生产语言，而不是一种研究语言，因此，正如C. A. R. Hoare在其关于语言设计的经典论文中所建议的那样，该设计避免包括新的和未经测试的特征。

The Java programming language is strongly and statically typed。 此规范明确区分了编译时可以和必须检测的编译时错误，以及运行时发生的错误。 编译时通常包括将程序转换为与机器无关的字节码。 运行时活动包括加载和链接执行程序所需的类、生成可选的机器代码、程序的动态优化，以及实际的程序执行。

Java编程语言是一种相对高级的语言，你无法在其中找到任何代表机器详细信息的影子。它包括内存的自动管理，通常使用垃圾回收器来避免显式地释放所带来的的安全性问题（如C中的free，c++中的delete）。<font color=red>**High-performance garbage-collected implementations can have bounded pauses to support systems programming and real-time applications.**</font>该语言不包含任何不安全的设计，例如数组没有进行索引检查就进行访问，因为这种不安全的设计将会导致程序不按照指定的方式运行。

通常将Java编程语言编译为Java虚拟机规范Java SE 8 Edition中定义的字节码指令集和二进制格式。

### 1.1 Java语言规范组织结构

第二章：介绍了一些文法以及用于表示该语言文法（词法和句法）的一些符号。

第三章：介绍了基于C和C ++的Java编程语言的词法结构。 该语言以Unicode字符集编写。 它支持在仅支持ASCII的系统上写入Unicode字符。

第四章：介绍了类型，值和变量。其中类型细分为基本类型和引用类型。

基本类型在所有的机器和任何实现中的定义都是相同的。具体包括各种大小的以2的补码形式表示的整数，单精度和双精度的符合IEEE 754标准浮点数，boolean类型和表示单个Unicode字符的char类型。基本类型的值不共享状态。

引用类型包括类类型，接口类型和数组类型。引用类型是通过动态创建的对象实现的，这些对象可以是类或者数组的实例，对每个对象可以存在多个引用。所有对象（包括数组）都支持Object类的方法，这个类是整个类层次结构的（单）根。Java有一个预定义的String类，用以支持由Unicode字符组成的字符串。存在用于将基本数据类型包装在对象内部的类。在许多情况下，包装和解包装是由编译器自动执行的（在这种情况下，包装称为装箱，解包装称为拆箱）。类和接口的声明是可以泛化的，即可以用其他引用类型来参数化它们。然后可以用特定的类型参数调用此类声明。

变量是类型化的存储位置。基本类型的变量保存该确切基本类型的值；而类类型的变量可以持有一个空引用，或是一个可以指向类型为该类或其任何子类的对象的引用；接口类型的变量可以持有一个空引用，或是一个可以指向实现了该接口的任何类的实例的引用；数组类型的变量可以持有一个空引用，或是一个指向某个数组的引用；Object类类型的变量可以持有一个空引用，或是一个指向任何对象的引用，无论该对象是类实例还是数组。

第五章：第五章介绍了类型转换和数字提升。类型转换会改变编译时类型，有时还会更改表达式的值。这些类型转换包括基本类型和引用类型之间的装箱和拆箱转换。数值提升用于将数值运算符的操作数转换为可以执行操作的通用类型。在Java语言中不存在任何类型不安全的漏洞，因为在运行时会对引用类型的类型转换做类型检查，以确保类型安全。

第六章：介绍了声明和命名，以及如何确定命名时名称的含义。Java语言不要求类型或其他成员的声明必须位于使用它们的代码之前。声明的顺序仅对局部变量，局部类以及类或接口中字段的初始化程序的顺序有影响。

Java编程语言提供对名称作用域的控制，并且支持对包，类，接口成员的外部访问的限制。这样有助于将类型的实现与它的用户以及扩展类型分离开，从而有助于编写大型程序。这章节还介绍了推荐的命名习惯，它可以使得程序更加易读。

第七章：介绍了程序的结构，Java程序被组织成类似于Modula模块的程序包。包的成员包括类、接口和子包。包被划分为编译单元，编译单元包含类型声明，并且可以从其他包中导入类型以便使用短名字引用它们。包的名称位于层次化名称空间中，通常可以使用Internet域名系统来形成唯一的软件包名。

第八章：介绍了类。类成员包括类，接口，字段（变量）和方法。类变量每个类只存在一次。类方法的操作无需针对特定的对象引用。实例变量在类的实例对象中动态创建。实例方法需通过类的实例进行调用；为了支持面向对象，在其执行过程中，这些对象就变成了当前对象**this**。

类支持单一继承实现，这是指每一个类的实现都从单一的超类中派生的，且最终都是从Object派生的。类类型的变量可以引用该类的实例或者任何该类的子类的实例，从而允许新类型可以在现有的方法上支持多态。

类可以通过synchronized关键字支持并发编程。方法可以声明在其执行时可能会产生的受检查异常，这样可以通过编译时的检查确保异常情况得到处理。对象还可以定义finalize方法，该方法会在该对象被垃圾回收器回首之前调用，可以在这个方法中做一些对象的状态清除工作。

为了简单起见，该语言既没有从类的实现中分离出“声明头文件”，也没有单独的类型和层次结构。

类有一种特殊的结构-枚举，支持定义一个小的取值集合，且以一种类型安全的方法对其进行操作。和其他语言的枚举不同的是，该语言的枚举是对象，并且可以拥有自己的方法。

第九章：介绍了接口，即声明了一系列抽象方法，成员类型和常量。那些没有关联的多个类可以继承自一个接口类型。一个接口类型的变量可以指向实现了该接口的任何对象的引用。Java支持多重接口继承。

注解类型是特殊的接口，用来注解各类声明。这些注解不会对Java程序的语义造成任何影响。然而，他对各种各样的工具提供了有效的输入。

第十章：介绍了数组。Java中对数组的访问包括边界检查。数组是动态创建的对象并且可以复制给Object类型的变量。Java支持是元素为数组的数组，而不是多维数组。

第十一章：介绍了异常，这些异常是不可恢复的，并且和Java语言的语义及并发机制完全集成。有三种类型的异常：受检异常、运行时异常以及错误。编译器通过要求方法或者构造器只有在方法或构造器声明了该异常的情况下，才能产生受检异常，从而确保受检异常能够得到正确的处理。这种机制提供了异常处理程序是否存在的编译时检查，从而对编程起到了很大的帮助。大多数用户定义的异常都应该是受检异常；而被Java虚拟机探测到的程序中不合法的操作会产生运行时异常，例如NullPointerException；被Java虚拟机探测到的故障会产生错误，例如OutOfMemoryError。大多数的简单的程序无需费力处理错误。

第十二章：介绍了在程序执行过程中发生的活动。Java程序通常被存储为二进制文件，这些文件表示编译过的类和接口。这些二进制文件可以被加载到Java虚拟机中，和其他类和接口进行链接，以及初始化。

在初始化之后，类方法和类变量就可以使用了。一些类可以被初始化以创建这些类类型的新对象。如果一个变量是某个类的实例，那么该实例对象也将包含该类的超类的实例引用，且该对象的创建将会对其超类进行递归创建。

当某个对象不再被引用，它就可以被垃圾回收器回收。如果这个对象声明了对象终结器，那么对象终结器将会在对象被回收之前执行，从容给了对象最后的机会去清理相关资源，否则这些资源就有可能得不到释放。当不再需要某个类时，这个类就可以被卸载了。

第十三章：介绍了二进制的可兼容性， 说明了修改类型后对其他类型的影响，该影响主要体现在使用了修改后的类型而自身并没有重新编译的情况。这些讨论关乎类型开发者的利益，因为它们开发的类型经常会以一系列连续的版本发布，经由互联网广泛传播。良好的编程开发环境会在类型修改之后重新编译依赖它的代码，所以大多数程序员无需关心这些细节。

第十四章：介绍了语句块和语句，它的概念来源于C和C++。Java语言没有goto语句，但是包含break和continue的标签语句。和C不同，Java编程语言要求在控制流语句中必须是boolean或者Boolean表达式，并且不会将其他类型隐式地转换为boolean类型（除了拆箱），这种设计是因为希望在编译时能够捕获更多的错误。synchronized语句提供了基本的对象级别的监管和加锁机制。try语句可以包含catch和finally子句，防止控制流转移到该局部之外。

第十五章：介绍了表达式。这一章节详细地址说明了表达式的复制顺序，以提高其确定性和可移植性。重载的方法和构造器是在编译时解析的，即在众多可选的方法或构造器中挑选最具体的方法和构造器。

第十六章：介绍了确保局部变量在使用前一定被设置过的精确的方式。尽管其他所有变量都会自动初始化为缺省值，但是Java编程语言不会自动初始化局部变量，这样做是为了避免掩盖编程错误。

第十七章：介绍了线程和锁的语义，其基于随Mesa编程语言引入的基于监视器并发机制。Java编程语言为支持高性能实现的共享内存结构的多处理器制定了一种内存模型。

第十八章：介绍了各种类型推断算法，这些算法用于测试泛型方法的适用性并在泛型方法调用中推断类型。

第十九章：介绍了Java编程语言的文法。

### 1.2 示例程序

本书中给出的大多数程序样例都是可执行的，且都类似于下面的形式：

```java
class Test {
	public static void main(String[] args) {
        for (int i = 0; i < args.length; i++)
            System.out.print(i == 0 ? args[i] : " " + args[i]);
        System.out.println();
    }
}
```

在一台安装了Oracle JDK的机器上，将这个类保存为Test.java，可以下面的命令进行编译和执行：

> javac Test.java
>
> java Test Hello, world.

输出：

> Hello, world.

### 1.3 表示法

本规范中通篇引用的都是来自于Java SE平台的API中的类和接口。凡是使用单个字符*N*来表示的类或者接口（除非在实例中特别说明），都是表示在java.lang包中名为*N*的类或接口。对于那些在java.lang之外的其他包中的类或接口，将使用规范名称（第6.7节）。

用来说明本规范的非规范性信息将用楷体表示。

<font face="楷体">这是非规范性信息,其描述了直观知识、基本原理、建议、样例等</font>

Java编程语言的类型系统偶尔也会依赖替换的概念，例如，[F<sub>1</sub>:=T<sub>1</sub>,...,F<sub>n</sub>:=T<sub>n</sub>]表示对所有的*i*, 1 <= i <= n, F<sub>i</sub>会被T<sub>i</sub>替换。

### 1.4 与预定义的类和接口之间的关系

 如上所述，这篇规范经常会引用Java SE平台中的API中的类。尤其是，一些类和Java编程语言之间存在着特殊的关系，如Object、Class、ClassLoader、String以及Thread，和java.lang.reflect包中的类和接口，以及一些其他接口。本规范对这些类和接口的行为进行了限制，但是没有提供完整的规格说明。读者可以参考Java SE平台的API文档。

因此，本规范并没有提及任何反射的细节。Java语言的许多语言结构在核心反射API（java.lang.reflect）和语言模型API（javax.lang.model）中都有类似结构，但是本规范一般不做讨论。例如，在列举对象的创建方式时，本规范并不会将可以完成这一任务的核心反射API列入其中。读者应该了解Java语言中的这些额外的机制，尽管在本规范中并没有谈及它们。

### 1.5 反馈

读者可以将有关*Java语言规范*中技术上的错误、有歧义的地方反馈到jls-jvms-spec-comments@openjdk.java.net.

有关javac（Java编程语言参考的编译器）的行为相关的问题，特别是与本规范一致的问题，可以发送至compiler-dev@openjdk.java.net.

### 1.6 引用参考

* Apple Computer. Dylan Reference Manual. Apple Computer Inc., Cupertino, California. September 29, 1995.
* Bobrow, Daniel G., Linda G. DeMichiel, Richard P. Gabriel, Sonya E. Keene, Gregor Kiczales, and David A. Moon. Common Lisp Object System Specification, X3J13 Document 88-002R, June 1988; appears as Chapter 28 of Steele, Guy. Common Lisp: The Language, 2nd ed. Digital Press, 1990, ISBN 1-55558-041-6, 770-864.
* Ellis, Margaret A., and Bjarne Stroustrup. The Annotated C++ Reference Manual. Addison-Wesley, Reading, Massachusetts, 1990, reprinted with corrections October 1992, ISBN 0-201-51459-1.
* Goldberg, Adele and Robson, David. Smalltalk-80: The Language. Addison-Wesley, Reading, Massachusetts, 1989, ISBN 0-201-13688-0.
* Harbison, Samuel. Modula-3. Prentice Hall, Englewood Cliffs, New Jersey, 1992, ISBN 0-13-596396.
* Hoare, C. A. R. Hints on Programming Language Design. Stanford University Computer Science Department Technical Report No. CS-73-403, December 1973. Reprinted in SIGACT/SIGPLAN Symposium on Principles of Programming Languages. Association for Computing Machinery, New York, October 1973.
* IEEE Standard for Binary Floating-Point Arithmetic. ANSI/IEEE Std. 754-1985. Available from Global Engineering Documents, 15 Inverness Way East, Englewood, Colorado 80112-5704 USA; 800-854-7179.
* Kernighan, Brian W., and Dennis M. Ritchie. The C Programming Language, 2nd ed. Prentice Hall, Englewood Cliffs, New Jersey, 1988, ISBN 0-13-110362-8.
* Madsen, Ole Lehrmann, Birger Møller-Pedersen, and Kristen Nygaard. Object-Oriented Programming in the Beta Programming Language. Addison Wesley, Reading,
* Massachusetts, 1993, ISBN 0-201-62430-3. Mitchell, James G., William Maybury, and Richard Sweet. The Mesa Programming Language, Version 5.0. Xerox PARC, Palo Alto, California, CSL 79-3, April 1979.
* Stroustrup, Bjarne. The C++ Progamming Language, 2nd ed. Addison-Wesley, Reading, Massachusetts, 1991, reprinted with corrections January 1994, ISBN 0-201-53992-6.
* Unicode Consortium, The. The Unicode Standard, Version 6.2.0. Mountain View, California, 2012, ISBN 978-1-936213-07-8.

## 第二章 文法

本章节介绍了上下文无关的语法，用于本规范中来定义程序的词法和句法结构。

### 2.1 上下文无关的文法

上下文无关的语法由大量的产生式构成。每个产生式的左部都是由一个被称为“非终结符”的抽象符号，其右部是一个符号序列，由一个或多个非终结符和终结符构成。对于每一种语法，终结符都来自于特定的字母表。

Starting from a sentence consisting of a single distinguished nonterminal, called the goal symbol, a given context-free grammar specifies a language, namely, the set of possible sequences of terminal symbols that can result from repeatedly replacing any nonterminal in the sequence with a right-hand side of a production for which the nonterminal is the left-hand side.(待翻译)

### 2.2 词法

第3章给出了Java编程语言的词法。该词法以Unicode字符集中的字符作为其终结符。它定义了一组产生式，从目标符号Input开始（第3.5节），Unicode字符应该如何被转义成输入元素序列（第3.5节）。

这些输入元素在将空白字符（第3.6节）和注释（第3.7节）剔除后，就构成了Java编程语言的句法中的终结符，即符号（token）（第3.5节）。这些符号包括Java编程语言的标识符（第3.8节）、关键字（第3.9节）、字面常量（literal）（第3.10节）、分隔符（第3.11节）和操作符（第3.12节）。

### 2.3 句法

Java编程语言中的句法将在第4、6-10、14和15章中介绍。该句法将由词法定义的符号作为其终结符。其定义了一系列产生式，描述了从目标符号*CompilcationUnit*(第7.3节)开始，符号序列可以如何构成句法上正确的程序。
为了方便起见，句法相关的所有内容在第19章中集中体现。

### 2.4 语法表示

终结符在词法和句法产生式中将会以固定的宽度显示，并且贯穿本规范；只要文本中直接引用这样的终结符。在程序中将会直接以本规范中书写的方式显示。

非终结符将会以斜体显示。非终结符的定义由被定义的非终结符的名称引入，后跟一个冒号。非终结符的一个或多个替代定义紧跟在后续的行中。

> 例如，句法产生式：
>
> ​		*ifThenStatement:*
>
> ​			if (*Expression*) *Statement*
>
> 表示非终结符*ifThenStatement*表示if标记，紧跟着一个左括号，接着是一个*Expression*，再接着是一个右括号，最后是一个*Statement*

产生式右边的*{x}*表示x将会出现0或者多次。

> 例如，句法产生式：
>
> ​		*ArgumentList:*
>
> ​			*Argument {, Argument}*
>
> 表示*ArgumentList*包含一个*Argument*，紧跟着0个或多个逗号和*Argument*。结果就是*ArgumentList*包含任意正数个参数

产生式右手边的*[x]*表示x将会出现0次或者多次。也就是说，x是一个可选符号。这一包含可选符号的替代项实际上有两层含义：1）你可以忽略它，2）你需要包含它。

> 这就意味着：
>
> ​	*BreakStatement:*
>
> ​		break *[Identifier]* ;
>
> 是下面的缩写：
>
> ​	*BreakStatement*:
>
> ​		break ;
>
> ​		break *Identifier* ;
>
> 另一个例子，意味着：
>
> ​	*BasicForStatement:*
>
> ​		for ( *[ForInit]* ; *[Expression]* ; *[ForUpdate]* ) *Statement*
>
> 是下面的缩写：
>
> ​	*BasicForStatement:*
>
> ​		for ( ; *[Expression]* ; *[ForUpdate]* ) *Statement*
>
> ​		for ( *ForInit* ; *[Expression]* ; *[ForUpdate]* ) *Statement*
>
> 这又是下面的缩写：
>
> ​	*BasicForStatement:*
>
> ​		for ( ; ; *[ForUpdate]* ) *Statement*
>
> ​		for ( ; *Expression* ; *[ForUpdate]* ) *Statement*
>
> ​		for ( *ForInit* ; ; *[ForUpdate]* ) *Statement*
>
> ​		for ( *ForInit* ; *Expression* ; *[ForUpdate]* ) *Statement*
>
> 同时，又是下面的缩写：
>
> ​	*BasicForStatement:*
>
> ​		for ( ; ; ) *Statement*
>
> ​		for ( ; ; *ForUpdate* ) *Statement*
>
> ​		for ( ; *Expression* ; ) *Statement*
>
> ​		for ( ; *Expression* ; *ForUpdate* ) *Statement*
>
> ​		for ( *ForInit* ; ; ) *Statement*
>
> ​		for ( *ForInit* ; ; *ForUpdate* ) *Statement*
>
> ​		for ( *ForInit* ; *Expression* ; ) *Statement*
>
> ​		for ( *ForInit* ; *Expression* ; *ForUpdate* ) *Statement*
>
> 所以非终结符*BasicForStatement*实际上有8个可选的含义

如果终结符很长，一行写不下，那么我们可以在紧接着第二行上定义。

> 例如，句法表达式如下：
>
> ​	*NormalClassDeclaration:*
>
> ​		*{ClassModifier}* class *Identifier [TypeParameters]*
>
>  		*[Superclass] [Superinterfaces] ClassBody*
>
> 表示的是*NormalClassDeclaration*一个终结符

产生式右手边的短语*one of*表示接下来的一行或多行中的每一个终结符都是一个可以替代的定义。

> 例如，包含下面产生式的词法：
>
> ​	*ZeroToThree:*
>
> ​		*(one of)*
>
> ​		0 1 2 3
>
> 接近于下面的缩写：
>
> ​	*ZeroToThree:*
>
> ​		0
>
> ​		1
>
> ​		2
>
> ​		3

当产生式的可替代值是一个token时，表示构成这个token的将是一个字符序列。

> 因此，下面的产生式:
>
> ​	*BooleanLiteral:*
>
> ​		*(one of)*
>
> ​		true false
>
> 可简写为:
>
> ​	*BooleanLiteral:*
>
> ​		t r u e
>
> ​		f a l s e



产生式右侧可以通过使用“but not”短语指示哪些扩展是不应该出现的，这样一些特定的扩展就不允许出现在这些位置。

> 例如：
>
> ​	*Identifier:*
>
> ​		*IdentifierChars* but not a *Keyword* or *BooleanLiteral* or *NullLiteral*

最后，一些由罗马类型的叙述短语定义的非终结符，这种情况想要列出所有可选终结符是不切实际的。

> 例如：
>
> ​	*RawInputCharacter:*
>
> ​		any Unicode character



## 第三章 词法结构

本章详细说明了Java编程语言中的词法结构。

程序是以Unicode编写的（第3.1节），但是Java提供了词法翻译（3.2节）；因此程序可以仅使用ASCII字符，通过使用Unicode转义从而可以包含任何的Unicode字符。Java中定义了换行符（3.4节），可以使得在现有不同使用习惯的主机系统上的行号能够保持一致。

词法翻译产生的Unicode字符被简化成一系列输入元素（3.5节），即空白字符（3.6节）、注释（3.7节）和符号。其中符号是指句法中的标识符（3.8节）、关键字（3.9节）、字面常量（3.10节）、分隔符（3.11节）和操作符（3.12节）。

### 3.1 Unicode

程序使用Unicode字符集编写的。和该字符集相关的信息以及与之关联的字符编码可以在[http://www.unicode.org/]( http://www.unicode.org/[)找到。

Java SE平台会跟随Unicode标准的演进。Java的每个发行版本中所使用的Unicode的确切版本，都会在Character类的文档中进行了说明。

> JDK 1.1之前的版本使用的Unicode版本是1.1.5。后续的JDK版本很多都升级到了新的Unicode版本上，包括 JDK 1.1 (Unicode 2.0)、JDK 1.1.7(Unicode 2.1)、Java SE 1.4 (Unicode 3.0)、Java SE 5.0 (Unicode 4.0)、Java SE 7 (Unicode 6.0)，以及Java SE 8 (Unicode 6.2).

### 3.4 换行符

Java编译器接下里会通过识别*换行符*将输入的Unicode字符序列分成多行。

> *LineTerminator:*
>
> ​	the ASCII LF character, also known as "newline"
>
> ​	the ASCII CR character, also known as "return"
>
> ​	the ASCII CR character followed by the ASCII LF character
>
> *InputCharacter:*
>
> ​	*UnicodeInputCharacter* but not CR or LF

一行的结束由ASCII字符CR，或LF，或CR LF。两个CR后面紧跟一个LF被认为是一个换行符，而不是两个。

换行符还可以表示以 // 形式注释的结束（3.7节）

> 由换行符定义的行可以用来确定Java编译器产生的行号。

由换行符和输入字符构成的序列，是在标记处理过程中第3步中的终结符。

### 3.5 输入元素和符号

在转义处理（3.3节）以及之后的输入行识别（3.4节）中产生的输入字符和行终结符，可以被精简为*输入元素*。

> *Input:*
>
> ​	*{InputElement} [Sub]*
>
> *InputElement:*
>
> ​	*WhiteSpace*
>
> ​	*Comment*
>
> ​	*Token*
>
> *Token:*
>
> ​	*Identifier*
>
> ​	*Keyword*
>
> ​	*Literal*
>
> ​	*Separator*
>
> ​	*Operator*
>
> *Sub:*
>
> ​	the ASCII SUB character, also known as "control-Z"

那些不是空白字符或者注释的输入元素被称作为符号。在句法（2.3节）中符号又是终结符。

空白字符（3.6节）和注释（3.7节）可以用来分隔符号；如果连在一起，可能会以另一种方式标记。例如，输入中的ASCII字符 - 和 = 只有在它们之间没有任何空白字符或者注释的情况下，才可以形成操作符 -=（3.12节）。

为了兼容某些操作系统，Java做出了一些妥协；即ASCII SUB字符（\u001a，或control-Z）如果是转义后的输入流中的最后一个字符，将会被忽略掉。

假设在输入流中有两个符号x和y。如果x在y的前面，我们就会说x在y的左边，y在x的右边。

> 例如，在下面简短的代码片段中：
>
> class Empty { 
>
> }
>
> 我们说符号}在符号{的右边，即使从它们出现的位置上来看，符号}在{的下面，我们也可以这么说。使用“左边”和“右边”这种惯例可以方便我们表述；例如，二元操作符的右操作数，或者一个赋值语句的左边。

### 3.6 空白字符

空白字符包括ASCII空格符，水平制表符，换页符和行终止符（3.4节）。

> *WhiteSpace:*
>
> ​	the ASCII SP character, also known as "space"
>
> ​	the ASCII HT character, also known as "horizontal tab"
>
> ​	the ASCII FF character, also known as "form feed"
>
> ​	*LineTerminator*

### 3.7 注释

Java有两种注释：

* /* text */

  传统注释：从ASCII字符 /* 到ASCII字符 */ 之间的所有文本都将被忽略（就像C和C++一样）。

* // text

  单行注释：从ASCII字符 // 到结尾的所有文本都将被忽略（就像C++一样）

  > *Comment:*
  >
  > ​	*TraditionalComment*
  >
  > ​	*EndOfLineComment*
  >
  > *TraditionalComment:*
  >
  > ​	/ * *CommentTail*
  >
  > *CommentTail:*
  >
  > ​	\* *CommentTailStar*
  >
  > ​	*NotStar CommentTail*
  >
  > *CommentTailStar:*
  >
  > ​	/
  >
  > ​	\* *CommentTailStar*
  >
  > ​	*NotStarNotSlash CommentTail*
  >
  > *NotStar:*
  >
  > ​	*InputCharacter* but not *
  >
  > ​	*LineTerminator*
  >
  > *NotStarNotSlash:*
  >
  > ​	*InputCharacter* but not * or /
  >
  > ​	*LineTerminator*
  >
  > *EndOfLineComment:*
  >
  > ​	/ / *{InputCharacter}*

这些产生式包括了以下特性：

* 注释不能嵌套
* /* 和 */ 在以 // 开头的注释中没有特殊含义
* // 在以 /* 或者 /** 开头的注释中没有特殊含义

> 因此，下面的文本只是一条完整的注释：
>
> /* this comment /* // /** ends here: */

词法约定注释不能出现在字符字面常量（3.10.4）和字符串字面常量（3.10.5）中。

### 3.8 标识符

*标识符*是没有长度限制的由Java字母和数字构成的序列，其中首字符必须是Java字母。

> *Identifier:*
>
> ​	*IdentifierChars* but not a *Keyword* or *BooleanLiteral* or *NullLiteral*
>
> *IdentifierChars:*
>
> ​	*JavaLetter {JavaLetterOrDigit}*
>
> *JavaLetter:*
>
> ​	any Unicode character that is a "Java letter"
>
> *JavaLetterOrDigit:*
>
> ​	any Unicode character that is a "Java letter-or-digit"

"Java字母"指作为Character.isJavaIdentifierStart(int)方法的参数返回true的字符。

"Java 字母或数字"指作为Character.isJavaIdentifierPart(int)方法参数返回true的字符。

> "Java字母"包括大写和小写的ASCII拉丁字母A-Z（\u0041-\u005a），和a-z（\u0061-\u007a）, 并且处于里是原因，还包括ASCII下划线（_或\u005f）和美元符号（$, 或者\u0024）。$符号应该仅出现在机械生成的源代码中，或者用于另一种很少见的情况，即访问遗留系统中的已有的名字。
>
> "Java数字"包含AACII数字0-9（\u0030-\u0039）。

字母和数字可以从整个Unicode字符集中抽取出来，其中包含的字符集支持编写绝大多数在当今世界中使用的脚本，包括用来支持中文、日文和韩文的大型字符集。这就允许了程序员在程序中用他们自己的本地语言语言编写标识符。

标识符的拼写（Unicode字符序列）不能与关键字（3.9节）、布尔字符常量（3.10.3节）、空字符常量（3.10.7）或者编译时发生的错误的拼写相同。

两个标识符只有在拼写完全相同，即每个字母和数字都是相同的Unicode字符。具有相同外观的标识符仍然可能是不同的。

> 例如，由单个大写的拉丁字母A（A，\u0041）构成的字符，小写拉丁字母A（a，\u0061）构成的字符，希腊大写字母ALPHA（A，\u0391）构成的字符、西里尔小写字母A（a，\u0430）构成的字符和数学中加粗斜体小写字母A（a，\udc82）构成的字符，都是不同的字符。
>
> Unicode组合字符和与其等价的安顺序拆解开的多个字符的组合所表示的标识符是不一样的。例如，大写拉丁字母A带上一个上提号（Á, \u00c1）和一个拉丁大写字母A（A, \u0041）后紧跟一个无空格间隙的上提号（´, \u0301）组成的标识符是不一样的。可查看Unicode标准，3.11节[“Normalization Forms”](https://www.unicode.org/versions/Unicode14.0.0/)
>
> 下面是一些标识符的例子:
>
> * String
> * i3
> * αρετη
> * MAX_VALUE
> * isLetterOrDigit

### 3.9 关键字

Java中保留了50个字符序列，都由ASCII字母构成，用来作为关键字；这些保留字段不能作为标识符（3.8）。

> 关键字：
>
> ​	*(one of)*
>
> ​	abstract  continue for 			  new 		  switch
>
> ​	assert 	default    if 				 package    synchronized
>
> ​	boolean do 		  goto 			private 	 this
>
> ​	break 	double    implements  protected throw
>
> ​	byte 	  else 	    import 		 public 	  throws
>
> ​	case	   enum	  instanceof    return 	  transient
>
> ​	catch 	extends   int 				short 	   try
>
> ​	char 	  final 	   interface 	  static 	   void
>
> ​	class 	 finally      long 			strictfp 	 volatile
>
> ​	const 	float 	   native 		 super 		while
>
> 尽管const和goto关键字目前没有使用，但是仍然保留了。这样使得这些C++关键字在程序中使用不当时，编译器能够产生更有用的错误信息。
>
> 尽管true和false看起来应该是关键字，但是从技术层面来讲，它们仅仅是布尔字面常量（3.10.3节）。与此类似，null看起来也应该是关键字，但是从技术层面来讲，它仅仅是空字面常量（3.10.7）。

### 3.10 字面常量

所谓字面常量，也就是基础类型（4.2节）、String类型（4.3.3节）或者空类型（4.1）在源代码中的表示。

> *Literal:*
>
> ​	*IntegerLiteral*
>
> ​	*FloatingPointLiteral*
>
> ​	*BooleanLiteral*
>
> ​	*CharacterLiteral*
>
> ​	*StringLiteral*
>
> ​	*NullLiteral*

#### 3.10.1 整型

*整型*可以表示成10进制、16进制、8进制和二进制（底数分别是10、16、8和2）。

> *IntegerLiteral:*
>
> ​	*DecimalIntegerLiteral*
>
> ​	*HexIntegerLiteral*
>
> ​	*OctalIntegerLiteral*
>
> ​	*BinaryIntegerLiteral*
>
> *DecimalIntegerLiteral:*
>
> ​	*DecimalNumeral [IntegerTypeSuffix]*
>
> *HexIntegerLiteral:*
>
> ​	*HexNumeral [IntegerTypeSuffix]*
>
> *OctalIntegerLiteral:*
>
> ​	*OctalNumeral [IntegerTypeSuffix]*
>
> *BinaryIntegerLiteral:*
>
> ​	*BinaryNumeral [IntegerTypeSuffix]*
>
> *IntegerTypeSuffix:*
>
> ​	*(one of)*
>
> ​	l L

以ASCII字母L或者l结尾的整型是long类型，如1l；否则它的类型就是int（4.2.1节）。

> 推荐使用L后缀，因为字母l（如1l）很难和数字1（one）区分开来。

下划线可以用于整数各个数字之间的分隔符。

在十六进制或者二进制中，整型只能由从0x或0b之后的字符开始，到任何用来表示类型的后缀之前的这段数字构成。因此，下划线不能出现在0x或0b之后，也不能出现在最后一个数字之后。

在十进制或者八进制中，整型由用来表示类型的后缀之前的所有数字构成。因此，下划线不会出现在第一个数字之前或者最后一个数字之后。下划线可以出现在八进制数字中的首个0之后（由于0是可以表示成整数的一部分）和非0十进制首个非0数字之后。

十进制数要么是由单个ASCII数字0组成，代表整数0；要么是由ASCII数字1-9组成，可选地，后面跟着一个或多个0-9的ASCII数字，中间穿插着下划线，代表一个正整数。

> *DecimalNumeral:*
>
> ​	0
>
> ​	*NonZeroDigit [Digits]*
>
> ​	*NonZeroDigit Underscores Digits*
>
> *NonZeroDigit:*
>
> ​	*(one of)*
>
> ​	1 2 3 4 5 6 7 8 9
>
> *Digits:*
>
> ​	*Digit*
>
> ​	*Digit [DigitsAndUnderscores] Digit*
>
> *Digit:*
>
> ​	0
>
> ​	*NonZeroDigit*
>
> *DigitsAndUnderscores:*
>
> ​	*DigitOrUnderscore {DigitOrUnderscore}*
>
> *DigitOrUnderscore:*
>
> ​	*Digit*
>
> ​	_
>
> *Underscores:*
>
> ​	_ *{*_*}*

十六进制数字包含前导符0x或者0X，后面紧跟着一个或多个ASCII十六进制数字，数字中间穿插着下划线；可以表示正整数、0或者负整数。

在十六进制数字中，用ASCII字母a-f或者A-F表示10-15；每一个字母用来表示十六进制数字时，可以是大写，也可以是小写。

> *HexNumeral:*
>
> ​	0 x *HexDigits*
>
> ​	0 X *HexDigits*
>
> *HexDigits:*
>
> ​	*HexDigit*
>
> ​	*HexDigit [HexDigitsAndUnderscores] HexDigit*
>
> *HexDigit:*
>
> ​	*(one of)*
>
> ​	0 1 2 3 4 5 6 7 8 9 a b c d e f A B C D E F
>
> *HexDigitsAndUnderscores:*
>
> ​	*HexDigitOrUnderscore {HexDigitOrUnderscore}*
>
> *HexDigitOrUnderscore:*
>
> ​	*HexDigit*
>
> ​	_
>
> 上面的*HexDigit*产生式来源于3.3节。

八进制数字包含：ASCII数字0，后面跟着一个或者多个ASCII数字0-7，中间穿插着下划线；可以表示正整数、0和负整数。

> *OctalNumeral:*
>
> ​	0 *OctalDigits*
>
> ​	0 *Underscores OctalDigits*
>
> *OctalDigits:*
>
> ​	*OctalDigit*
>
> ​	*OctalDigit [OctalDigitsAndUnderscores] OctalDigit*
>
> *OctalDigit:*
>
> ​	*(one of)*
>
> ​	0 1 2 3 4 5 6 7
>
> *OctalDigitsAndUnderscores:*
>
> ​	*OctalDigitOrUnderscore {OctalDigitOrUnderscore}*
>
> *OctalDigitOrUnderscore:*
>
> ​	*OctalDigit*
>
> ​	_
>
> 注意：八进制数总是由2个或者更多的数字构成，单独的一个0永远被当做是十进制数看待；当然这在实际应用中并不会有什么问题，因为数字0、00和0x0都表示完全相同的整型值。

二进制数包含一个由ASCII字符0b构成的前导符，后面跟着一个或多个ASCII数字0或1，中间穿插着下划线；可以表示正整数、0和负整数。

> *BinaryNumeral:*
>
> ​	0 b *BinaryDigits*
>
> ​	0 B *BinaryDigits*
>
> *BinaryDigits:*
>
> ​	*BinaryDigit*
>
> ​	*BinaryDigit [BinaryDigitsAndUnderscores] BinaryDigit*
>
> *BinaryDigit:*
>
> ​	*(one of)*
>
> ​	0 1
>
> *BinaryDigitsAndUnderscores:*
>
> ​	*BinaryDigitOrUnderscore {BinaryDigitOrUnderscore}*
>
> *BinaryDigitOrUnderscore:*
>
> ​	*BinaryDigit*
>
> ​	_

整型表示的最大十进制数是 2147483648 (2<sup>31</sup>)。

0-2147483647表示的所有十进制字面常量，可以出现在任何用int类型表示的字面常量出现的地方。十进制2147483648只能作为一元减操作符-的操作数（15.15.4）。

如果2147483648不是作为一元减操作数-的操作数，或者int类型的十进制数大于2147483648 (2<sup>31</sup>)，将会产生编译错误。

int类型的十六进制、八进制和二进制所表示的最大正值都是 2147483647 (2<sup>31</sup>-1) ，分别为：

* 0x7fff_ffff,
* 0177_7777_7777,
* 0b0111_1111_1111_1111_1111_1111_1111_1111

int类型的十六进制、八进制和二进制所表示的最小负值都是-2147483648 (-2<sup>31</sup>)，分别是：

* 0x8000_0000,
* 0200_0000_0000,
* 0b1000_0000_0000_0000_0000_0000_0000_0000

下面的十六进制、八进制和二进制表示的值都是-1：

* 0xffff_ffff,
* 0377_7777_7777,
* 0b1111_1111_1111_1111_1111_1111_1111_1111

如果一个int类型的十六进制、八进制或者二进制不能用32位表示，将会产生编译错误。

long类型的最大十进制数是 9223372036854775808L (2<sup>63</sup>)。

0L-9223372036854775807L所表示的十进制数，可以出现在任何用long类型表示的字面常量出现的地方。十进制9223372036854775807L只能作为一元减操作符-的操作数（15.15.4）。

如果9223372036854775807L不是作为一元减操作符-的操作数，或者long类型的十进制数大于9223372036854775808L (2<sup>63</sup>)，将会产生编译错误。

long类型的十六进制、八进制和二进制表示的最大正值都是9223372036854775807L (2<sup>63</sup>-1)，分别为：

* 0x7fff_ffff_ffff_ffffL,
* 07_7777_7777_7777_7777_7777L,
* 0b0111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111L

long类型的十六进制、八进制和二进制表示的最小负值都是-9223372036854775808L (-2<sup>63</sup>)，分别是：

* 0x8000_0000_0000_0000L, and
* 010_0000_0000_0000_0000_0000L,
* 0b1000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000L

下面的十六进制、八进制和二进制表示的都是-1L：

* 0xffff_ffff_ffff_ffffL,
* 017_7777_7777_7777_7777_7777L,
* 0b1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111_1111L

如果一个long类型的十六进制、八进制和二进制不能用64位表示，将会产生编译错误。

> int类型表示的整数示例:
>
> 0 2 0372 0xDada_Cafe 1996 0x00_FF__00_FF
>
> long类型表示的整数示例:
>
> 0l 0777L 0x100000000L 2_147_483_648L 0xC0B0L

#### 3.10.2 浮点型

*浮点型字面常量*有以下几个部分：整数部分、十进制或者十六进制小数点（用ASCII字符 . 表示）、小数部分、指数部分，以及类型后缀。

一个浮点数可以表示成十进制或者十六进制。

对于十进制浮点数，至少有一个数字（要么是整数部分，要么是小数部分），以及一个小数点、指数或者浮点类型后缀。所有的其他部分都是可选的。指数（如果存在）由 ASCII 字母 e 或 E 后跟可选的带符号整数表示。

对于十六进制浮点数，至少包含一个数字（位于整数部分或者小数部分），指数是必须的，并且float类型的后缀是可选的。指数可以用ASCII字母p或者P表示，后面跟着可选的有符号整数。

下划线可以作为分隔符，用在整数部分的数字之间、小数部分的数字之间，以及指数部分的数字之间。

> *FloatingPointLiteral:*
>
> ​	*DecimalFloatingPointLiteral*
>
> ​	*HexadecimalFloatingPointLiteral*
>
> *DecimalFloatingPointLiteral:*
>
> ​	*Digits* . *[Digits] [ExponentPart] [FloatTypeSuffix]* 
>
> ​	. *Digits [ExponentPart] [FloatTypeSuffix]*
>
> ​	*Digits ExponentPart [FloatTypeSuffix]*
>
> ​	*Digits [ExponentPart] FloatTypeSuffix*
>
> *ExponentPart:*
>
> ​	*ExponentIndicator SignedInteger*
>
> *ExponentIndicator:*
>
> ​	*(one of)*
>
> ​	e E
>
> *SignedInteger:*
>
> ​	*[Sign] Digits*
>
> *Sign:*
>
> *(one of)*
>
> ​	\+ -
>
> *FloatTypeSuffix:*
>
> ​	*(one of)*
>
> ​	f F d D
>
> *HexadecimalFloatingPointLiteral:*
>
> ​	*HexSignificand BinaryExponent [FloatTypeSuffix]*
>
> *HexSignificand:*
>
> ​	*HexNumeral [*.*]* 
>
> ​	0 x *[HexDigits]* . *HexDigits*
>
> ​	0 X *[HexDigits]* . *HexDigits*
>
> *BinaryExponent:*
>
> ​	*BinaryExponentIndicator SignedInteger*
>
> *BinaryExponentIndicator:*
>
> ​	*(one of)*
>
> ​	p P

以ASCII字母F或者f结尾的浮点数是float类型的。否则，它的类型就是double，double类型的浮点数结尾的后缀D或者d是可选的（4.2.3节）。

那些可以用float和double表示的值，是可以分别使用IEEE 754的32位单精度和64为位双精度二进制浮点数格式表示。

> java.lang包中的Float和Double类中的valueOf方法中，描述了从Unicode字符串表示的浮点数到内部IEEE 754二进制浮点数的输入转换详细细节。

float表示的最大正数是 3.4028235e38f。

float表示的最小非0正数是1.40e-45f。

double表示的最大正数是 1.7976931348623157e308。

double表示的最小非0正数是4.9e-324。

如果一个非0的浮点数非常大，在将其四舍五入转换成其内部表示时，会变成IEEE 754无穷值；因此这种情况性爱会产生编译错误。

通过使用常量表达式，如 1f/0f 或者 -1d/0d，亦或者使用Float和Double类中预先定义好的常量POSITIVE_INFINITY和NEGATIVE_INFINITY，可以让程序在不产生编译错误的情况下表示无穷值。

如果一个非0浮点数非常小，在四舍五入转换成其内部表示时，会变成0；这将会产生编译错误。

如果一个非0浮点数的值比较小，在四舍五入转换成其内部表示时，变成了一个非归一化的非0值；这种情况下不会产生编译错误。

在Float和Double类中还定义好了用来表示非数字值的预定义常量Float.NaN和Double.NaN。

> float类型字面常量示例:
>
> 1e1f 	2.f 	.3f 	0f 	3.14f 	6.022137e+23f
>
> double类型字面常量示例:
>
> 1e1 	2. 	.3 	0.0 	3.14 	1e-9d 	1e137

#### 3.10.3 布尔常量

boolean类型有两个值，用*布尔字面常量*true和false表示，其中true和false都是由ASCII字母构成。

> *BooleanLiteral:*
>
> ​	*(one of)*
>
> ​	true false

布尔字面常量的类型总是boolean（4.2.5节）。

#### 3.10.4 字符常量

字符常量是用ASCII单引号括起来的耽搁字符或单个转义字符序列（3.10.6节）。单引号（或称为撇号）由\u0027表示。

> *CharacterLiteral:*
>
> ​	' *SingleCharacter* ' 
>
> ​	' *EscapeSequence* '
>
> *SingleCharacter:*
>
> ​	*InputCharacter* but not ' or \
>
> 转义字符序列的定义可查看（3.10.6）

字符常量只能表示UTF-16码元（3.1节），也就是说，它们的取值范围被限制在了\u0000-\uffff。Supplementary characters must be represented either as a surrogate pair within a char sequence, or as an integer, depending on the API they are used with.

字符常量的类型总是用char表示（4.2.1）。

如果在*SingleCharacter*或*EscapeSequence*后面跟着的字符不是 \' ，将会产生编译错误。

如果行终结符（3.4节）出现在开始的 \' 之后和结束的 \' 之前，将会产生编译错误。

> 正如3.4节中指出的，CR和LF字符从来不都不是*InputCharacter*，而是被识别为*LineTerminator*。
>
> 下面是char类型常量示例：
>
> * 'a'
> * '%'
> * '\t'
> * '\\'
> * '\''
> * '\u03a9'
> * '\uFFFF'
> * '\177'
> * '™'
>
> 因为Unicode转义字符在很早就被处理掉了，因此将换行（LF）的值写成'\u000a'是不正确的；Unicode转义符\u000a在程序翻译的第一步（3.3节）就被转换成了真正的换行，而在程序翻译的第二步（3.4节）换行就变成了*LineTerminator*，因此这个字符在第三步是无效的。取而代之的是，我们应该用转义符'\n'（3.10.6）。类似地，将回车（CR）的值写成'\u000d'也是不正确的，应该用'\r'代替。
>
> 在C和C++中，字符常量可以包含1个以上的字符，但是这样的字符常量的值是由具体实现定义的。在Java编程语言中，字符常量总是严格的表示一个字符。

#### 3.10.5 字符串常量

一个*字符常量*包含0个或多个用双引号括起来的字符，这些字符可以用转义序列（3.10.6）表示；其中在从U+0000-U+FFFF范围内的字符用一个转义字符序列表示，而在从U+010000-U+10FFFF范围内的UTF-16代理码元字符可以用两个转义字符序列表示。

> *StringLiteral:*
>
> ​	" *{StringCharacter}* "
>
> *StringCharacter:*
>
> ​	*InputCharacter* but not " or \
>
> ​	*EscapeSequence*
>
> 转义字符序列的定义可查看（3.10.6）

字符常量的类型总是String（4.3.3）。

如果行终结符出现在开始的 " 之后和结束的 " 之前，将会产生编译错误。

> 正如3.4节中指出的，CR和LF字符从来不都不是*InputCharacter*，而是被识别为*LineTerminator*。
>
> 一个长的字符常量总是可以被拆解为若干个短小的部分，并且可以用连接操作符 + 写成表达式（可能需要圆括号括起来）。
>
> 下面是字符类型常量示例：
>
> "" 								 // the empty string
>
> "\"" 							   // a string containing " alone
>
> "This is a string" 		 // a string containing 16 characters
>
> "This is a " +			    // actually a string-valued constant expression,
>
>  "two-line string" 		// formed from two string literals
>
> 因为Unicode转义字符在很早就被处理掉了，因此将换行（LF）的值写成"\u000a"是不正确的；Unicode转义符\u000a在程序翻译的第一步（3.3节）就被转换成了真正的换行，而在程序翻译的第二步（3.4节）换行就变成了*LineTerminator*，因此这个字符在第三步是无效的。取而代之的是，我们应该用转义符"\n"（3.10.6）。类似地，将回车（CR）的值写成"\u000d"也是不正确的，应该用"\r"代替。最后，用"\U0022"来表示包含单个双引号（"）的字符常量也是不可行的。

字符常量是对String类的实例引用（4.3.1，4.3.3节）。

而且，一个字符常量通常指向的都是String的同一个实例。这是因为字符常量-或者更通俗的来讲，那些用来表示常量表达式（15.28节）的字符串常量，被String.intern方法给限制住了，这样它们就能够共享唯一的实例。

> **例 3.10.5-1. 字符串常量**
>
> 一段程序包含以下编译单元（7.3）
>
> ​	package testPackage;
>
> ​	class Test { 
>
>  		public static void main(String[] args) { 
>	
>  			 String hello = "Hello", lo = "lo"; 
>	
>  			 System.out.print((hello == "Hello") + " "); 
>	
>   			System.out.print((Other.hello == hello) + " "); 
>	
>   			System.out.print((other.Other.hello == hello) + " "); 
>	
>   			System.out.print((hello == ("Hel"+"lo")) + " "); 
>	
>   			System.out.print((hello == ("Hel"+lo)) + " "); 
>	
>   			System.out.println(hello == ("Hel"+lo).intern()); 
>
> ​	 } 
>
> } 
>
> class Other { static String hello = "Hello"; }
>
> 以及下面的编译单元：
>
> ​	package other; 
>
> ​	public class Other { public static String hello = "Hello"; }
>
> 那么该程序将会产生：
>
> ​	true true true true false true
>
> 这个示例诠释了以下6点：
>
> * 同一个包（第7章（包））下的同一个类（第8章（类））中，相同内容的字符串常量指向同一个String对象的引用
> * 同包中下，不同类中，相同内容的字符串常量指向同一个String对象的引用
> * 不同包，不同类中，相同内容的字符串常量指向同一个String对象的引用
> * 通过字符串常量表达式计算出来的字符串，其是在编译时刻就已经被计算出来，并且在这之后会被当做字符串常量对待
> * 运行时刻计算得到的字符串是新创建的，因此会区别对待
> * 通过显示限定计算得到的字符串，其结果与任何已经存在的具有相同内容的字符串常量是相同的。

#### 3.10.6 字符和字符串常量的转义序列

字符和字符串的转义序列使得在字符常量和字符串常量中，可以不使用Unicode转义字符表示非图形字符，以及单引号、双引号、反斜杠字符。

> *EscapeSequence:*
>
> ​	\ b (backspace BS, Unicode \u0008)
>
> ​	\ t (horizontal tab HT, Unicode \u0009)
>
> ​	\ n (linefeed LF, Unicode \u000a)
>
> ​	\ f (form feed FF, Unicode \u000c)
>
> ​	\ r (carriage return CR, Unicode \u000d)
>
> ​	\ " (double quote ", Unicode \u0022)
>
> ​	\ ' (single quote ', Unicode \u0027)
>
> ​	\ \ (backslash \, Unicode \u005c)
>
> ​	*OctalEscape* (octal value, Unicode \u0000 to \u00ff)
>
> *OctalEscape:*
>
> ​	\ *OctalDigit*
>
> ​	\ *OctalDigit OctalDigit*
>
> ​	\ *ZeroToThree OctalDigit OctalDigit*
>
> *OctalDigit:*
>
> ​	*(one of)*
>
> ​	0 1 2 3 4 5 6 7
>
> *ZeroToThree:*
>
> ​	*(one of)*
>
> ​	0 1 2 3
>
> 上面的*OctalDigit*产生式出自3.10.1节

如果跟在转义序列中的反斜杠后面的字符不是ASCII  b、t、n、f、r、"、'、\、0、1、2、3、4、5、6或7，将会产生编译错误。Unicode字符\u在很早就被处理掉了（3.3）。

> 八进制转衣服是为了兼容C而设计的，但是其只能表示从\u0000-\u00FF这一段的Unicode值，因此经常会优先考虑Unicode转义符。

#### 3.10.7 空常量

空类型只有一个值，即空引用，用ASCII字符构成的空字符常量null表示。

> *NullLiteral:*
>
> ​	null

空常量的类型总是null（4.1节）。

### 3.11 分隔符

分隔符总共有12个，都是由ASCII字符构成。

> *Separator:*
>
> ​	*(one of)*
>
> ​	(	)	{	}	[	]	;	,	.	...	@	::

### 3.12 操作符

操作符总共有38个，都是由ASCII字符构成。

> *Operator:*
>
> ​	*(one of)*
>
> ​	= 	> 	< 	! 	~ 	  ? 	 : 	  ->
>
> ​	==  >=   <=  !=   &&    ||	 ++   --
>
> ​	\+ 	- 	 *	  /	&	    |	  ^     % 	<< 	>> 	>>>
>
> ​	+=  -=	*=   /=  &=     |=    ^=  %=   <<=  >>=   >>>=

## 第四章 类型、值和变量

Java编程语言是一种*静态类型*语言，这也就意味着：在编译阶段，每个变量和每个表达式的类型都能得到明确。

Java编程语言同时又是一种*强类型*语言，因为类型限制了变量（4.12节）可持有的值以及表达式可产生的值；限制了在这些变量上支持的操作；并决定了这些操作的含义。强静态类型有助于在编译阶段检查错误。

Java编程语言的类型被分成了两类：原始类型和引用类型。原始类型（4.2节）包括`boolean`(布尔)类型和数字类型。其中数字类型包括整型：`byte, short, int, long和char`，浮点型：`float和double`。引用类型（4.3节）包括类，接口和数组，还有一个特殊的null类型。一个对象（4.3.1节）是动态创建的一个类的实例；或者是一个动态创建的数组。引用类型的值是对象的引用。所有对象，包括数组，均支持`Object`（4.3.2节）类的方法。字符串使用`String`对象表示。

### 4.1 类型和值的分类

Java编程语言中有两种类型：原始类型（4.2节）和引用类型（4.3节）。与之相对应的有两种数据类型：原始数据类型（4.2节）和引用数据类型（4.3节）可以用作：变量中存储，参数传参，方法返回以及参数的操作。

> *Type:*
>
> *PrimitiveType*
>
> *ReferenceType*

还有一个特殊的*null*类型，没有名字；表达式`null`类型（3.10.7节，15.8.1节）。

因为null类型没有名字，因此不可能声明一个null类型的变量或者将变量转换为null类型。

null引用只可以作为null类型表达式的值。

始终可以将null引用赋值或者强制转换为任何引用类型（5.2节，5.3节，5.5节）。

> 在实际的开发中，编程人员可以忽略null类型，可以认为null类型仅仅是一个特殊字面值，可以用来表示任何引用类型。

### 4.2 原始类型和值

Java编程语言中预先定义了原始类型并通过相关关键字进行了命名（3.9节）。

> *PrimitiveType:*
>
> *{Annotation} NumericType*
>
> *{Annotation}* boolean
>
> *NumericType:*
>
> *IntegralType*
>
> *FloatingPointType*
>
> *IntegralType:*
>
> *(one of)*
>
> byte short int long char
>
> *FloatingPointType:*
>
> *(one of)*
>
> float double

原始值不与其他原始值共享状态。

*数字类型*可分为：整型和浮点型。

*整型*可分为：`byte、short、int`和`long`，其值分别为8位、16位、32位和64位有码二进制补码整数；以及`char`，其值为16位无符号整数，表示UTF-16的代码单元（3.1节）

浮点类型有:`float`，其值可表示32位IEEE 754浮点数；`double`，其值可表示64位IEEE 754浮点数。

`boolean`只有两个值：`true`和`false`。

#### 4.2.1 整型和值

整型值的取值范围如下：

* `byte`：[-128, 127]
* `short`：[-32768, 32767]
* `int`：[ -2147483648, 2147483647,]
* `long`：[-9223372036854775808, 9223372036854775807]
* `char`：['\u0000', '\uffff']，也就是[0, 65535]

#### 4.2.2 整型操作

Java编程语言为整型值提供了一系列操作符：

* 比较操作符，结果是`boolean`类型：

  -数字比较操作符 <、<=、> 以及>=（15.20.1节）

  -数字相等操作符 ==和!=（15.21.1节）

* 数字操作符，结果是`int`或者`long`：

  -一元加减操作符 +和-（15.53.3节，15.15.4节）

  -乘法操作符 *、/和%（15.17）

  -累加操作符 +和-（15.18）

  -自增操作符 ++，放在前面（15.15.1）和后面（15.14.2）

  -自减操作符 --，放在前面（15.15.2）和后面（15.14.3）

  -有符号和无符号操作符 <<、>> 和>>>（15.19）

  -按位补位操作符 ~（15.15.5）

  -按位操作符 &、^和|（15.22.1）

* 条件操作符 ？：（15.25）

* 强制转换运算符（第 15.16 节），可以将整数值转换为任何指定数字类型的值

* 字符串连接运算符 +（第 15.18.1 节），当给定一个字符串操作数和一个整数操作数时，会将整数操作数转换为一个以十进制形式表示其值的字符串，然后生成一个新创建的字符串表示连接后的符串的

其他一些有用的构造器，方法和常量在Byte、Short、Integer、Long和Character类中均有预先定义。

如果除移位运算符以外的整数运算符至少有一个 long 类型的操作数，则使用 64 位精度进行运算，并且数值运算符的结果为 long 类型。 如果另一个操作数不是long类型的，则首先通数字提升的方式（5.6）将其扩展（第 5.1.5 节）为 long类型。
否则，使用 32 位精度进行运算，数值运算符的结果为 int 类型。 如果任一操作数不是 int，则首先通过数字提升将其扩展为 int 类型。
任何整数类型的任何值都可以转换为任何数字类型或从任何数字类型转换。 整数类型和布尔类型之间不能强制转换。

> 有关将整数表达式转换为布尔值的习惯用法，请参见第 4.2.5 节。

整数运算符不以任何方式指示向上溢出或向下溢出。

由于以下原因，整数运算符可能会引发异常（第 11 节（*异常*））：

* null引用的拆箱转换（5.1.8），任何整型运算符都会抛出NullPointerException异常

* 整型除法运算符 /（15.17.2）和整型取余运算符 % （15.17.3）在右操作数为0时会抛出ArithmeticException异常

* 自增（15.14.2,15.15.1）和自减运算符（15.14.3,15.15.2）在装箱操作过程中没有足够的内存空间时会抛出OutOfMemoryError异常

  > 示例4.2.2-1. 整型操作
  >
  > class Test { 
  >
  > ​	public static void main(String[] args) { 
  >
  > ​		int i = 1000000; 
  >
  > ​		System.out.println(i * i); 
  >
  > ​		long l = i; 
  >
  > ​		System.out.println(l * l); 
  >
  > ​		System.out.println(20296 / (l - i)); 
  >
  > ​	} 
  >
  > }
  >
  > 上述程序输出：
  >
  > -727379968
  >
  > 1000000000000
  >
  > 紧接着在除法表达式1-i中会遇到ArithmeticException异常，因为l-i是0。第一次乘法运算以 32 位精度执行，而第二次乘法运算是long类型。  -727379968 是低 32 位数学运算结果的十进制表示，1000000000000表示对于 int 类型来说这个值太大了。

  #### 4.2.3 浮点型，格式和值

  

