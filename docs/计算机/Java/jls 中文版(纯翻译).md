**The Java Language Specification**

Java SE 8 Edition

 

James Gosling

Bill Joy

Guy Steele

Gilad Bracha

Alex Buckley

 

译者:张德舟

2019.06.19

# 前言

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









 

# 第一章：介绍

Java®编程语言是一种通用的，并发的，基于类的，面向对象的语言。它的设计非常简单，许多程序员都可以熟练掌握。Java编程语言与C和C ++相关，但组织方式有所不同，省略了C和C ++的许多方面，同时又包含了其他语言的一些想法。它旨在成为一种生产语言，而不是一种研究语言，因此，正如C. A. R. Hoare在其关于语言设计的经典论文中所建议的那样，该设计避免包括新的和未经测试的特征。

The Java programming language is strongly and statically typed。 此规范明确区分了编译时可以和必须检测的编译时错误，以及运行时发生的错误。 编译时通常包括将程序转换为与机器无关的字节码。 运行时活动包括加载和链接执行程序所需的类、生成可选的机器代码、程序的动态优化，以及实际的程序执行。

Java编程语言是一种相对高级的语言，你无法在其中找到任何代表机器详细信息的影子。它包括内存的自动管理，通常使用垃圾回收器来避免显式地释放所带来的的安全性问题（如C中的free，c++中的delete）。<font color=red>**High-performance garbage-collected implementations can have bounded pauses to support systems programming and real-time applications.**</font>该语言不包含任何不安全的设计，例如数组没有进行索引检查就进行访问，因为这种不安全的设计将会导致程序不按照指定的方式运行。

通常将Java编程语言编译为Java虚拟机规范Java SE 8 Edition中定义的字节码指令集和二进制格式。

## 1.1 Java语言规范组织结构

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

## 1.2 示例程序

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

## 1.3 表示法

本规范中通篇引用的都是来自于Java SE平台的API中的类和接口。凡是使用单个字符*N*来表示的类或者接口（除非在实例中特别说明），都是表示在java.lang包中名为*N*的类或接口。对于那些在java.lang之外的其他包中的类或接口，将使用规范名称（第6.7节）。

用来说明本规范的非规范性信息将用楷体表示。

<font face="楷体">这是非规范性信息,其描述了直观知识、基本原理、建议、样例等</font>

Java编程语言的类型系统偶尔也会依赖替换的概念，例如，[F<sub>1</sub>:=T<sub>1</sub>,...,F<sub>n</sub>:=T<sub>n</sub>]表示对所有的*i*, 1 <= i <= n, F<sub>i</sub>会被T<sub>i</sub>替换。

## 1.4 与预定义的类和接口之间的关系

 如上所述，这篇规范经常会引用Java SE平台中的API中的类。尤其是，一些类和Java编程语言之间存在着特殊的关系，如Object、Class、ClassLoader、String以及Thread，和java.lang.reflect包中的类和接口，以及一些其他接口。本规范对这些类和接口的行为进行了限制，但是没有提供完整的规格说明。读者可以参考Java SE平台的API文档。

因此，本规范并没有提及任何反射的细节。Java语言的许多语言结构在核心反射API（java.lang.reflect）和语言模型API（javax.lang.model）中都有类似结构，但是本规范一般不做讨论。例如，在列举对象的创建方式时，本规范并不会将可以完成这一任务的核心反射API列入其中。读者应该了解Java语言中的这些额外的机制，尽管在本规范中并没有谈及它们。

## 1.5 反馈

读者可以将有关*Java语言规范*中技术上的错误、有歧义的地方反馈到jls-jvms-spec-comments@openjdk.java.net.

有关javac（Java编程语言参考的编译器）的行为相关的问题，特别是与本规范一致的问题，可以发送至compiler-dev@openjdk.java.net.

## 1.6 引用参考

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



# 第二章 文法

本章节介绍了上下文无关的语法，用于本规范中来定义程序的词法和句法结构。

## 2.1 上下文无关的文法

上下文无关的语法由大量的产生式构成。每个产生式的左部都是由一个被称为“非终结符”的抽象符号，其右部是一个符号序列，由一个或多个非终结符和终结符构成。对于每一种语法，终结符都来自于特定的字母表。

Starting from a sentence consisting of a single distinguished nonterminal, called the goal symbol, a given context-free grammar specifies a language, namely, the set of possible sequences of terminal symbols that can result from repeatedly replacing any nonterminal in the sequence with a right-hand side of a production for which the nonterminal is the left-hand side.(待翻译)

## 2.2 词法

第3章给出了Java编程语言的词法。该词法以Unicode字符集中的字符作为其终结符。它定义了一组产生式，从目标符号Input开始（第3.5节），Unicode字符应该如何被转义成输入元素序列（第3.5节）。

这些输入元素在将空白字符（第3.6节）和注释（第3.7节）剔除后，就构成了Java编程语言的句法中的终结符，即符号（token）（第3.5节）。这些符号包括Java编程语言的标识符（第3.8节）、关键字（第3.9节）、字面常量（literal）（第3.10节）、分隔符（第3.11节）和操作符（第3.12节）。

## 2.3 句法

Java编程语言中的句法将在第4、6-10、14和15章中介绍。该句法将由词法定义的符号作为其终结符。其定义了一系列产生式，描述了从目标符号CompilcationUnit(第7.3节)开始，符号序列可以如何构成句法上正确的程序。
为了方便起见，句法相关的所有内容在第19章中集中体现。

## 2.4 文法表示法





