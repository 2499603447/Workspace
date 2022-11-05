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

浮点型包括`float`和`double`，概念上分别对应IEEE 754中的32位单精度和64位双精度格式的值和操作，在*IEEE Standard for Binary Floating-Point Arithmetic*, ANSI/IEEE Standard 754-1985 (IEEE, New York)中有所指定。

IEEE 754 标准不仅包括由符号和数值组成的正数和负数，还包括正负0、正无穷和负无穷以及特殊的 Not-a-Number 值（以下简称 NaN）。 NaN 值用于表示某些无效操作的结果，例如除法0除0。 NaN 常量既可以是`float`类型，又可以是`double`类型，被预定义为 `Float.NaN` 和 `Double.NaN`。

Java编程语言的每个实现都需要支持两组标准的浮点集，即单精度浮点集（*float value set*）和双精度浮点集（*double value set*）。此外，Java 编程语言的实现可以支持两个或者一个扩展指数浮点集，称为单精度浮点扩展指数集（*float-extented-exponent*）和双精度浮点扩展指数值集（*double-extended-exponent*）。 在某些情况下，这些扩展指数集可以用来代替标准值集来表示 `float` 或 `double` 类型的表达式的值（第 5.1.13 节，第 15.4 节）。

任何浮点值集的有限非0值都可以表示为*s · m · 2<sup>(e - N + 1)</sup>*，，其中*s* 是+1或-1，*m*是小于2<sup>*N*</sup>的正整数，*e*是位于*E<sub>min</sub>* = -(2<sup>*K* - 1</sup> - 2)和*E<sub>max</sub>* = 2<sup>(*K* - 1)</sup> - 1的闭区间的整数；其中参数*N*和*K*是取决于所用的值集。

#### 4.2.4 浮点数操作

#### 4.2.5 boolean类型和布尔值

### 4.3 引用类型和值

有四种引用类型：类类型（第8.1节）、接口类型（第9.1节）、类型变量（第4.4节）和数组类型（第10.1节）。

> *ReferenceType:*
>
> ​	*ClassOrInterfaceType*
>
> ​	*TypeVariable*
>
> ​	*ArrayType*
>
> *ClassOrInterfaceType:*
>
> ​	*ClassType*
>
> ​	*InterfaceType*
>
> *ClassType:*
>
> ​	*{Annotation} Identifier [TypeArguments]*
>
> ​	*ClassOrInterfaceType* . *{Annotation} Identifier [TypeArguments]*
>
> *InterfaceType:*
>
> ​	*ClassType*
>
> *TypeVariable:*
>
> ​	*{Annotation} Identifier*
>
> *ArrayType:*
>
> ​	*PrimitiveType Dims*
>
> ​	*ClassOrInterfaceType Dims*
>
> ​	*TypeVariable Dims*
>
> *Dims:*
>
> ​	*{Annotation}* [ ] *{{Annotation}* [ ]*}*
>
> 示例代码:
>
> ```java
> class Point { int[] metrics; } 
> 
> interface Move { void move(int deltax, int deltay); }
> ```
>
> 声明了一个类类型`Point`, 一个接口类型`Move`, 并且用一个数组类型`int[]` (整型数组) 声明了`Point`类的`metrics`域。

类或接口类型由一个标识符或标识符序列组成，其中每个标识符后面可选地跟着类型参数（第4.5.1节）。如果类型参数出现在类或者接口的任意位置，则它是参数化类型（第4.5节）。

类或接口类型中的每个标识符都被分类为包名或类型名（第 6.5.1 节）。 可以对分类为类型名称的标识符进行注释。 如果类或接口类型具有 *`T.id`* 形式（后面可选地跟着类型参数），则 *`id`* 必须是 *`T`* 的可访问成员类型的简单名称（第 6.6 节、第 8.5 节、第 9.5 节），否则编译时将会发生错误。 类或接口类型表示该成员类型。

#### 4.3.1 对象

一个*对象*一个是*类的实例*或者是一个数组。

引用值（通常直接称为*引用*）是指向这些对象的指针，并且有一个特殊的空引用，它不指向任何对象。

一个类的实例是通过类的实例创建表达式显示创建的（第15.9节）。

一个数组是通过数组创建表达式显示创建的（第15.10.1节）。

当字符串连接操作符+（第15.18.1节）被用于非常量表达式（第15.28节）中，会隐式地创建一个新的实例，从而产生一个`String`类型的新对象（第4.3.3节）。

计算数组初始化表达式（第 10.6 节）时，会隐式创建一个新数组对象； 这可能发生在类或接口初始化的时候（第 12.4 节）、创建类的新实例的时候（第 15.9 节）或执行局部变量声明语句的时候（第 14.4 节）。

在装箱（第5.1.7节）的过程中` Boolean, Byte, Short, Character, Integer, Long, Float,和Double `的新的对象可能会被隐式地创建。

**示例 4.3.1-1. 对象的创建**

```java
class Point { 
  int x, y;
  Point() { System.out.println("default"); } 
  Point(int x, int y) { this.x = x; this.y = y; } 
  /* A Point instance is explicitly created at class initialization time: */ 
  static Point origin = new Point(0,0); 
  /* A String can be implicitly created by a + operator: */ 
  public String toString() { return "(" + x + "," + y + ")"; } 
} 

class Test { 
   public static void main(String[] args) { 
     /* A Point is explicitly created using newInstance: */ 
     Point p = null; 
     try { 
       p = (Point)Class.forName("Point").newInstance(); 
     } catch (Exception e) { 
       System.out.println(e); 
     } 
     /* An array is implicitly created by an array constructor: */ 
     Point a[] = { new Point(0,0), new Point(1,1) }; 
     /* Strings are implicitly created by + operators: */ 
     System.out.println("p: " + p); 
     System.out.println("a: { " + a[0] + ", " + a[1] + " }"); 
     /* An array is explicitly created by an array creation expression: */ 
     String sa[] = new String[2]; 
     sa[0] = "he"; sa[1] = "llo"; 
     System.out.println(sa[0] + sa[1]); 
   } 
}
```

**程序执行结果：**

```
default
p: (0,0)
a: { (0,0), (1,1) }
hello
```

可作用于对象的引用的操作符有：

* 字段访问，使用限定名称（第6.6节）或字段访问表达式（第15.11节）
* 方法调用（第15.12节）
* 类型转换操作符（第5.5节，第15.16节）
* 字符串拼接操作符+（第15.18.1节），当给定一个`String`类型的操作数和一个引用时，该操作符会执行时，会调用引用对象的`toString`方法，将引用转换为`String`（如果该引用或`toString`的结果是空引用，则使用『null』），然后会将两个字符串拼接起来产生一个新的字符串。
* `instanceOf`操作符（第15.20.2节）
* 引用相等的判定操作符 == 和 != (第15.21.3节)
* 条件操作符 ? : （第15.25节）

同一个对象可能会有很多个引用。大多数的对象会有状态，这些状态存储在类的实例对象的域中；或者存储在数组对象的成员变量中。如果两个变量包含指向同一个对象的引用，那么当通过其中一个变量的对象引用修改该对象的状态时，可通过另一个变量中的引用观察到修改后的状态。

**示例4.3.1-2. 原始标识和引用标识**

```java
class Value { int val; } 
class Test { 
  public static void main(String[] args) { 
    int i1 = 3; 
    int i2 = i1; 
    i2 = 4; 
    System.out.print("i1==" + i1); 
    System.out.println(" but i2==" + i2); 
    Value v1 = new Value(); 
    v1.val = 5; 
    Value v2 = v1; 
    v2.val = 6; 
    System.out.print("v1.val==" + v1.val); 
    System.out.println(" and v2.val==" + v2.val); 
  } 
}
```

**程序输出结果：**

```
i1==3 but i2==4
v1.val==6 and v2.val==6
```

因为`v1.val`和`v2.val`引用的是同一个`Value`对象中的同一个实例变量（第4.12.3节），这个对象通过唯一的`new`表达式创建，而`i1`和`i2`是不同的变量。

每个对象都与一个监视器（第 17.1 节）相关联，`synchronized`方法（第 8.4.3 节）和`synchronized`语句（第 14.19 节）使用监视器来控制多个线程对状态的并发访问（第 17 节（线程和锁） ））。

#### 4.3.2 Object类

`Object`类是所有其他类的超类（第8.1.4节）。

所有的类和数组类型都继承（第8.4.8节）了`Object`类的方法，具体包括：

* `clone`方法，用来创建对象的副本。

* `equal`方法，定义了对象相等的概念，是基于值的比较，而不是引用的比较。

* `finalize`方法，在对象被销毁前运行（第12.6节）。

* `getClass`方法，返回表示该对象所属类的`Class`对象。

  每一个引用类型都存在`Class`对象。它有很多用处，例如用来发现一个类的全类名、它的成员、它的直接超类，以及它实现的所有接口。

  `getClass`方法调用的结果类型是`Class<? extends |T|>`，这里*`T`*是`getClass`（第15.12.1节）搜索到的类或接口， 其中|*T*| 表示*T*的类型擦除（第4.6节）。

  被声明为`synchronized`（第8.4.3.6）的类方法，会在与该类的Class对象相关联的监视器上进行同步。

* `hashCode`方法，和`equal`方法一起，在像`java.util.HashMap`这样的散列表中非常有用。

* `wait、notify和notifyAll`方法，在使用线程实现并发编程时会用到（第17.2节）。

* `toString`方法，返回对象的字符串表示形式。

#### 4.3.3 String 类

`String`类的实例表示的是Unicode代码点序列。

一个`String`对象具有一个常量值（不可修改）。

字符串字面常量（第3.10.5）是对`String`实例的引用。

当结果不是常量表达式（第15.28节）时，字符串拼接操作符+（第15.18.1节）隐式地创建了一个新的`String`对象。

#### 4.3.4 当引用类型相同时

如果两个引用类型具有相同的二进制名称（第 13.1 节）并且它们的类型参数（如果有）相同，则它们编译时类型相同。

当两个引用类型相同时，它们有时会被称为相同的类或相同的接口。

在运行时，具有相同二进制名称的多个引用类型可能由不同的类加载器同时加载。 这些类型可能代表也可能不代表相同的类型声明。 即使两个这样的类型确实表示相同的类型声明，它们也被认为是不同的。

如果满足下列条件，则两个引用类型就是同一个运行时类型：

* 它们都是类或接口类型，由相同的类加载器定义，并且有相同的二进制名称（第13.1节），此时，它们有时也会被称为*相同运行时类*或*相同运行时接口*。
* 它们都是数组类型，并且其成员的类型都是同一个运行时类型（第10章）。

### 4.4 类型变量

类型变量是在类、接口、方法和构造器中用作类型的非限定标识符。

类型变量由泛型类、接口、方法或构造函数的类型参数的声明引入（第 8.1.2 节、第 9.1.2 节、第 8.4.4 节、第 8.8.4 节）。

> *TypeParameter:*
>
> ​	*{TypeParameterModifier} Identifier [TypeBound]*
>
> *TypeParameterModifier:*
>
> ​	*Annotation*
>
> *TypeBound:*
>
> ​	extends *TypeVariable*
>
> ​	extends *ClassOrInterfaceType {AdditionalBound}*
>
> *AdditionalBound:*
>
> ​	& *InterfaceType*

当类型变量声明为类型参数时，其作用域在第6.3节中进行说明。

每一个类型变量声明为类型参数时，都有一个边界。如果对某个类型变量没有声明任何边界，则默认其边界为`Object`。如果声明了边界，其由下面两种情况构成：

* 单个类型变量*T*。
* 类或接口类型*T*，后面可能跟着接口类型 *I<sub>1</sub> & ... & I<sub>n</sub>*

如果*I<sub>1</sub> & ... & I<sub>n</sub>*中的任意一个是类类型或者类型变量，那么就会产生一个编译时错误。

The erasures (§4.6) of all constituent types of a bound must be pairwise different, or a compile-time error occurs.

类型变量不能同时是同一个泛型接口的不同参数化的两个接口类型的子类型，否则会发生编译时错误。

The order of types in a bound is only significant in that the erasure of a type variable is determined by the first type in its bound, and that a class type or type variable may only appear in the first position.

### 4.5 参数化类型

#### 4.5.1 参数化类型的类型参数

### 4.6 类型擦除

### 4.7

### 4.8

### 4.9

### 4.10

### 4.11 哪些地方使用到了类型

在大多数的声明和某些类型的表达式中，都是用到了类型。具体地讲，有16处类型上下文中使用到了类型：

* 在声明中：
  1. 类声明中的extends或implements子句中的类型(第8.1.4节、第8.1.5节、 第8.5节、第9.5节)。
  2. 在接口声明的extends子句中而类型（第9.1.3节、第8.5节、第9.5节）。
  3. 方法的返回类型（包括注解类型的元素类型）（第8.4.5节、第9.4节、第9.6.1节）。
  4. 方法或者构造器的throws子句中的类型（第8.4.6节、第8.8.5节、第9.4节）。
  5. 泛型类、接口、方法或者构造器的类型参数声明的extends子句中的类型(第8.1.2、9.1.2、8.4.4、8.8.4节)。
  6. 类或接口的成员变量的声明中的类型(第8.3、9.3、8.9.1节)。
  7. 方法、构造函数或 lambda 表达式的形式参数声明中的类型（第8.4.1、8.8.1、9.4、15.27.1节）。
  8. 方法的接收器参数的类型（第8.4.1节）。
  9. 局部变量声明中的类型（第14.4、14.14.1、14.14.2、14.20.3节）。
  10. 异常参数声明中的类型（第14.20）。
* 在表达式中：
  11. 传递给显示构造器的调用语句、类实例创建语句和方法调用表达式的显示类型参数列表（第8.8.7.1、15.9、15.12节）。
  12. 在非限定类实例创建表达式中，作为要实例化的类类型（第 15.9 节）或作为要实例化的匿名类的直接超类或直接超接口（第 15.9.5 节）
  13. 数组创建表达式中的元素类型（§15.10.1）
  14. 转换表达式的转换运算符中的类型（第 15.16 节）
  15. instanceof 关系运算符之后的类型（§15.20.2）
  16. 在方法引用表达式（第 15.13 节）中，作为引用类型来搜索成员方法或作为类类型或数组类型来构造。

同时，类型还用作：

* 在上述任何上下文中的数组类型中的元素类型。
* 在上述任何上下文中的参数化类型的非通配符类型参数或有界通配符类型参数。

最终，在Java编程语言中还有3种特殊的情况用来表示对类型的使用：

* 无界通配符（第4.5.1节）。
* 可变参数类型中的...（第8.4.1节），用来表示数组类型。
* 在构造器声明中简单的类型名称（第8.8节），用来表示被构造对象的类。

在各种类型上下文中的类型的意义在下列章节中给出：

* 第4.2节，阐述原始（基本）类型。
* 第4.4节，阐述类型参数。
* 第4.5节，阐述参数化的类和接口类型，或者作为参数化类型中的类型参数或作为参数化类型中通配符类型参数的边界出现的类型。
* 第4.8节，阐述原始的类和接口类型。
* 第4.9节，阐述在类型参数边界中的交集类型。
* 第6.5节，阐述在无关紧要的泛型上下文中的类和接口类型（第6.1节）。
*  第10.1节，阐述数组类型。

一些类型上下文对引用类型可以如何被参数化进行了限制：

* 下面的类型上下文要求：如果一个类型是参数化引用类型，那么它就不能有任何通配符类型参数：

  — 在类型的extends或implements子句中（第8.1.4、8.1.5节）

  — 在接口声明的extends子句中的类型（第9.1.3节）

  — 在一个非限定类的实例创建表达式中，作为被实例化的类类型（第15.9节），或者作为被实例化的匿名内部类的直接超类或超接口（第15.9.5节）

  — 在方法的引用表达式中（第15.13节），作为用来查找成员方法的引用类型，或者作为要构造的类类型或数组类型。

  除此之外，任何通配符类型参数都不允许出现在显示构造器调用语句、类实例创建表达式、方法调用表达式和方法引用表达式的显示类型参数列表中 (第8.8.7.1、15.9、15.12、15.13节)。

* 下面的类型上下文要求：如果一个类型是参数化引用类型，那么它只能有无界通配符类型参数（即，它是可具化类型）：

  — 作为数组创建表达式中的元素类型（§15.10.1）

  — 作为instanceof 关系运算符之后的类型（§15.20.2）

* 下面的类型上下文都不允许使用参数化引用类型，因为它们都涉及异常，并且异常的类型都是非泛化的（第6.1节）：

  — 作为方法或者构造器的throws子句中的类型（第8.4.6节、第8.8.5节、第9.4节）

  — 作为异常参数声明中的类型（第14.20）

  在任何使用类型的类型上下文中，... 

  to be translated



### 4.12 变量

一个变量就是一个存储位置，并且具有相关联的类型，有时我们称之为编译时类型，该类型要么是原始类型（第4.2节），要么是引用类型（第4.3节）。

一个变量的值可通过赋值或者前缀与后缀的++（递增）与--（递减）操作符 (第15.14.2, 15.14.3, 15.15.1, 15.15.2节)来改变。

变量的值与其类型的兼容性是由Java编程语言的设计方案所保证的，只要程序没有产生编译时期的非检查异常的警告（第4.12.2节）。类型的缺省值（第4.12.5节）都是与其类型兼容的，而所有的赋值操作都会检查其赋值兼容性（第5.2节），这种检查通常是在编译时期完成的，但是有一个例外的是，涉及数组的赋值兼容性是在运行时进行检查的（第10.5节）。

#### 4.12.1 原始类型变量

原始类型的变量始终保存该原始类型的原始值。

#### 4.12.2 引用类型变量

类类型*T*的变量可以持有空引用，或者是对*T*类以及*T*的任意子类的实例的引用。

接口类型的变量可以持有空引用，或者是对实现了该接口的任意类的实例的引用。

> 注意：
>
> 不能保证变量总是引用其声明类型的子类型，而只能引用声明类型的子类或子接口。 这是由于下面讨论的堆污染的可能性。

如果*T*是原始类型，那么『*T*的数组』类型的变量就可以持有空引用，或者是对任意『*T*的数组』类型的数组的引用。

如果*T*是引用类型，那么『*T*的数组』类型的变量就可以持有空引用，或者是对任意『*S*的数组』类型的数组的引用，其中*S*类型是*T*类型的子类或子接口。

`Object[]`类型的变量可以持有对任意引用类型数组的引用。

`Object`类型的数组可以持有空引用，或者是对任意对象的引用。不管引用的对象是一个类的实例或者数组。

参数化类型的变量可能会引用不是该参数化的对象，这种情况被称为*堆污染*。

堆污染只有可能发生在程序执行的操作中包含原生类型，而恰巧这些原生类型的操作会产生编译时的非受检警告(第4.8、5.1.9、5.5.2、8.4.1、8.4.8.3、8.4.8.4、9.4.1.2、15.12.4.2节)，或者如果程序通过原始或非泛型的超类型数组变量为不可具体化元素类型的数组变量起别名。

> 例如，下面这段代码：
>
> ```java
> List l = new ArrayList<Number>();
> List<String> ls = l; // Unchecked warning
> ```
>
> 这段代码会产生编译时的非受检检查警告，因为它无法确认变量l是否确实指向List<String>对象的引用，不管是在编译时（在编译时的类型检查规则限制范围内）还是运行时。
>
> 如果执行上面的代码，将会产生堆污染，因为变量`ls`声明成了`List<String>`，但是它指向了的对象却不是`List<String>`。
>
> 由于类型变量没有具体化，因此无法在运行时识别问题，因此实例在运行时不携带任何有关用于创建它们的类型参数的信息。
>
> 在上面给处的简单实例中，看起来好像在编译时期识别这种情况并给出错误提醒会显得更加直接。然而，在通常（典型）情况下，变量l可能是某个方法单独编译后的调用结果，或者它的值可能依赖某个任意的控制流。因此，上面的代码非常不典型，而且确实非常糟糕的风格。
>
> 更进一步讲，`Object[]`是所有数组类型的超类，也就意味着不安全地为一个变量器别名是很可能发生的，进而就产生了堆污染。例如，下面的代码可以编译，因为它是静态类型正确的：
>
> ```java
> static void m(List<String>... stringLists) {
>    Object[] array = stringLists;
>    List<Integer> tmpList = Arrays.asList(42);
>    array[0] = tmpList; // (1)
>    String s = stringLists[0].get(0); // (2)
> }
> ```
>
> 在代码（1）处会产生堆污染，因为`stringLists`数组中的元素应该指向`List<String>`，但是却指向了`List<Integer>`。没有任何方法可以检测到这个污染，不管是在通用的超类型（`Object[]`）还是不可具化的类型（形参声明的类型，`List<String>`）面前。在（1）处不产生非受检查警告是合理的；但是，在运行时，在（2）处会产生一个`ClassCastException`异常。
>
> 在对上述方法的任何调用都会产生编译时的非受检查警告，因为Java编程语言的静态类型系统会认为这种调用是要创建一个元素类型为不可具化（第15.12.4.2）的`List<String>`数组。当且仅当方法体对可变参数是类型安全的，编程人员才可以使用`SafeVarargs`注解来关闭调用处的告警（第9.6.4.7节）。由于像上面这样编写的方法体会产生堆污染，所以使用注解来禁用对调用者的警告是完全不恰当的。
>
> 最终，注意`stringLists`数组可以通过除`Object[]`类型之外的其他类型变量起别名，然而在此情况下，堆污染仍然会发生。例如，`array`变量的类型可以是`java.util.Collection[]`，即原生元素类型，而上面个的方法体可以编译，也不会产生告警或错误，但是仍旧会产生堆污染。而且，假设Java SE平台将`Sequence`定义为了`List<T>`的非泛化超类，那么即便使用`Sequence`用作`array`类型，也会产生堆污染。

变量总是会指向这样一个对象：表示参数化类型的类的实例。

> 上述实例中的`ls`值总是某个类的实例，这个类提供了`List`的一种表示。
>
> 对于将原生类型的表达式赋值给参数化类型这一做法，应该只有在将未使用参数化类型的遗留代码和使用了参数化类型的现有的新的代码相结合时，才考虑使用。
>
> 如果没有任何操作会触发编译时的非受检查警告，也没有对不可具化的数组变量做不安全的别名操作，那么堆污染就不会产生。请注意，这并不表示堆污染只有在确实产生了编译时的非受检查警告时才会发生。因为运行的程序可能是二进制程序，它们是用针对老版本的Java编程语言的编译器产生的，或者其源代码显示地使用注解关闭了非受检查的告警。这种习惯可以说是非常不好的。
>
> 相反地，无论执行的代码是否可能会产生编译时的非受检查警告，都有可能不会发生堆污染。实际上，良好的编程习惯要求编程人员确保无论是否存在非受检查异常，代码都应该是正确的，并且不会发生堆污染。

#### 4.12.3 变量的种类

变量分为8种：

1. 类变量：在类声明（第 8.3.1.1 节）中使用关键字`static` 声明的字段，或者在接口声明（第 9.3 节）中使用或不使用关键字`static`声明的字段。
   类变量在其类或接口准备好时创建（第 12.3.2 节）并初始化为默认值（第 4.12.5 节）。 当类或接口被卸载时，类变量实际上也不再存在（第 12.7 节）。

2. 实例变量：在类声明（第 8.3.1.1 节）中没有使用关键字`static`声明的字段。

   如果类*T*有个域`a`是实例变量，那么新的实例变量`a`就会被创建并初始化成默认值（第 4.12.5 节），作为每一个新创建的*T*类对象或*T*的任意子类（第8.1.4节）对象的一部分。当包含实例域的对象不再被引用时，在该对象的所有必须的终结操作（第12.6节）执行完成后，该实例变量即会被有效地终止生命周期。

3. 数组成员：它们是不具名变量，在新的数组对象被创建时（第10章（数组），第15.10.2节），它们会被创建并被初始化为缺省值。数组成员在数组不再被引用时，即会被有效地终止声明周期。

4. 方法参数（第8.4.1节）：传递给方法的有名参数

   对于在方法声明中声明的每一个参数，在方法每次被调用时（第15.12节）都会创建新的参数变量。新的变量被初始化为方法调用中相应的参数值。当方法体执行完成后，方法参数即被有效地终止声明周期。

5. 构造器参数（第8.8.1节）：传递给构造器的有名参数

   对于构造器声明中的每一个参数，在类的实例创建表达式（第15.9节）或显式构造器调用（第8.8.7节）中对构造器的每一次调用，都会闯创建新的参数变量。新的变量被初始化为创建表达式或构造器中相应的参数值。当构造器体执行完毕后，构造器参数即被有效地终止生命周期。

6. lamba参数（第15.27.1节）：传递给lambda表达式体（第15.27.2节）的有名参数值。

   对于lambda表达式中声明的每一个参数，当lambda体实现的方法被调用时（第15.12节），每次调用都会创建新的参数变量。新的参数变量被初始化为方法调用中相应的参数值。当lambda表达式体执行完毕后，lamabda参数即被有效地终止生命周期。

7. 异常参数：每当异常被`try`语句的`catch子句`（第14.20节）捕获时都会创建一个异常参数。

   新的变量会用与异常相关联的实际对象（第11.3，14.18节）进行初始化。异常参数在与`catch`子句相关联的块执行完成后，即被有效地终止生命周期。

8. 局部变量：由局部变量声明语句（第14.4节）声明。

   当控制流进入一个块（第14.2节）或`fror`语句（第14.14节）中时，就会立即为在这个块或`for`语句中包含的局部变量声明语句中所声明的每个局部变量创建新变量。

   局部变量声明语句可以包含初始化变量的表达式。但是，带有初始化表达式的局部变量只有在声明它的局部变量声明语句被执行时，才会被初始化。（明确赋值规则（第16章）可以防止局部变量在初始化之前被使用，只有在赋值后的局部变量才能使用。）当局部变量的块或for语句执行完成后，局部变量即被有效地终止生命周期。

   > 在局部变量声明语句被执行时，就可以认为局部变量是创建了的。当然存在一种例外情况，这种 例外情况涉及 `switch`语句（第 14.11 节），其中控制可以进入块但绕过局部变量声明语句的执行。 然而，由于明确赋值规则（第 16 节（明确赋值））所施加的限制，由这种绕过的局部变量声明语句声明的局部变量，在通过赋值表达式明确赋值之前不能使用（ §15.26）。

   示例 4.12.3.-1、不同种类的变量

   ```java
   class Point { 
       static int numPoints; // numPoints is a class variable 
       int x, y; // x and y are instance variables 
       int[] w = new int[10]; // w[0] is an array component 
       int setX(int x) { // x is a method parameter 
           int oldx = this.x; // oldx is a local variable 
           this.x = x; 
           return oldx; 
       } 
   }
   ```

#### 4.12.4 `final`变量

一个变量可以被声明为`final`，且`final`变量只能被赋值一次。如果对`final`变量进行赋值，除非在此之前该变量是明确未被赋值过的，否则会报编译错误（第16章（明确赋值））。

一旦`final`变量被赋值，那么它始终持有同一个值。如果一个`final`变量持有一个对象的引用，可以通过对象的操作改变其状态，但是这个变量始终指向的是这个对象。这条规则同样也适用于数组，因为数组也是对象。如果一个`final`变量持有的是指向数组的引用，那么数组的元素可以通过数组上的操作所修改，但是该变量始终指向这个数组。

空`final`指的是其声明中缺少初始化器的`final`变量。

*常量变量*是指用常量表达式初始化的基本数据类型或者`String`类型的`final`变量。无论一个变量是否是常量变量，都涉及相关的类初始化（第12.4.1节）、二进制兼容性（第13.1，13.4.9节），以及明确赋值（第16章）。

有三种变量被隐式地声明为了`final`：接口中的域（第9.3节）、`try-with-resources`语句中的resource，以及多重`catch`子句（第14.20节）中的异常参数。单`catch`子句中的异常参数永远不会被隐式地声明为`final`，但是它可以被认为效果等同于final。

示例4.12.4-1、fianl变量

将变量声明为`final`可以起到归档作用，因为它的值不会被修改，同时可以帮助避免编程错误。在下面而程序中：

```java
class Point { 
    int x, y; 
    int useCount; 
    Point(int x, int y) { this.x = x; this.y = y; } 
    static final Point origin = new Point(0, 0); 
}
```

`Point`类声明了一个`final`变量`origin`。`origin`变量持有的是对表示坐标原点（0，0）的`Point`类的实例的引用。变量`Point.origin`的值永远不能修改，所以它始终引用同一个`Point`对象，即有构造器创建的对象。但是，在这个`Point`对象上的操作可以改变其状态，例如，修改其`useCount`，甚至可以误导性地修改其`x,y`的坐标。

某些没有被声明为`final`的变量可以被认为效果等同于`final`：

* 局部变量的声明包含初始化器（第14.4.2节），如果满足以下所有条件，那么也被认为效果等同`final`：

  — 没有被声明为`final`

  — 永远不会出现在赋值语句左边（第15.26节）。（注意包含初始化器的局部变量的声明不是赋值语句）

  — 永远不会作为自增/自减操作符的操作数出现（第15.14，15.15节）

* 局部变量的声明缺少初始化器，如果满足以下所有条件，那么也被认为效果等同`final`:

  — 没有被声明为`final`

  — 无论何时它作为赋值操作符（第15.26节）的左操作数出现，都是明确未赋过值的，并且在本次赋值操作前未被明确赋值。即它是明确未赋过值的，并且在本次赋值操作的右操作数之后没有被明确赋值（第16章）

  — 永远不会作为自增/自减操作符的操作数出现。

* 方法、构造器、lambda表达式和异常参数（第8.4.1、8.8.1、9.4、15.27.1.14.20节）具有初始化器的将被视为局部变量，以用来决定是否等同`final`

如果变量在效果上等同`final`，那么对其声明添加`final`修饰符不会引入任何编译上的错误。相反地，在合法的程序中被声明为`final`的局部变量或参数，如果将`final`修饰符移出，那么它们会在效果上等同于`final`。

#### 4.12.5 变量的初始值

程序中的每个变量在使用前必须拥有值：

* 每个类变量、实例变量或数组元素在被创建时都需要用缺省值进行初始化（第15.9、15.10.2节）：

  — 对于`byte`类型，缺省值是0，即`(byte)0`

  — 对于`short`类型，缺省值是0，即`(short)0`

  — 对于`int`类型，缺省值是0，即0

  — 对于`long`类型，缺省值是0，，即0L

  — 对于`float`类型，缺省值是正0，即0.0f

  — 对于`double`类型，缺省值是正0，即0.0d

  — 对于`char`类型，缺省值是空字符串，即`\u0000`

  — 对于`boolean`类型，缺省值是`false`

  — 对于所有的引用类型（第4.3节），缺省值是`null`

* 每个方法参数（第8.4.1节）都被初始化为方法调用者（第15 .12节）提供的相应的参数值。

* 每个构造器参数（第8.8.1节）都被初始化为类实例创建表达式（第15.9节）或显示构造调用（第8.8.7节）中相应的参数值。

* 异常参数（第14.20节）被初始化为被抛出的表示异常的对象（第11.3、14.18节）

* 局部变量（14.4，14.14）必须在被使用前显示地通过初始化（14.4）或赋值操作（15.26）进行赋值，其赋值方式可以使用明确赋值规则进行校验（第16章（明确赋值））。

  示例4.12.5-1、变量的初始值

  ```java
  class Point { 
      static int npoints; 
      int x, y; 
      Point root; 
  } 
  class Test { 
      public static void main(String[] args) { 
          System.out.println("npoints=" + Point.npoints); 
          Point p = new Point(); 
          System.out.println("p.x=" + p.x + ", p.y=" + p.y); 
          System.out.println("p.root=" + p.root); 
      } 
  }
  ```

  程序输出：

  ```
  npoints=0
  p.x=0, p.y=0
  p.root=null
  ```

  上面的输出说明了`npoints`是通过缺省初始化进行赋值的，这一过程发生在`Point`类的准备阶段（第12.3.2节）；并且`x,y,root`也是通过缺省初始化进行赋值的，这一过程发生在新的`Point`被实例化时。第12章（执行）对类和接口的加载、链接和初始化的各个方面都进行了完整的描述，另外还有对创建新的类的实例的类的初始化的描述。

#### 4.12.6 类型、类和接口

在Java编程语言中，每个变量和表达式都有一个在编译时期就可以确定的类型。这个类型可以是基本类型，也可以是引用类型。引用类型包括类类型和接口类型。引用类型可通过*类型声明*中有引入，包括类的声明（8.1节）和接口声明（9.1节）。我们经常使用*类型*来引用类或接口。

在Java虚拟机中，每一个对象都属于某个特定的类：在对象创建表达式（15.9节）中出现的类，或者其`Class`对象被用来调用发射方法产生的对象，或者字符连接操作符+（第15.18.1节）隐式创建的`String`类。这个类被称作*该对象的类*。一个对象被当做它的类的实例，以及它的类的所有超类的实例。

每个数组也都有一个类。当调用数组对象上的`getClass`方法时，会返回一个表示*数组类*（10.8节）的（`Class`类的）类对象。

变量的编译时类型总是声明的类型，而表达式的编译时类型是可以在编译时进行推断的。编译时类型限制了变量和表达式在运行时可以持有的值。如果运行时的值是一个引用类型并且不是`null`，那么它将指向一个属于某个类的对象或数组，并且这个类必须与编译时类型兼容。

尽管一个变量或表达式的编译时类型可以是接口类型的，但是接口是没有任何实例的。类型是接口的变量或表达式可以引用任何实现了该接口的类（8.1.5）节。

有时变量或表达式被认为含有『运行时类型』。假设这个值不为`null`，该类型即运行时变量或表达式的值所引用的对象所属的类。

编译时类型和运行时类型之间的对应关系并不完全一致是由以下两个原因造成的：

1. 在运行时，Java虚拟机使用类加载器加载类或接口。每个类加载器定义了其自己的类和接口集。因此，对于不同的两个加载器加载的同一个类或接口定义，可能会产生运行时的两个不同的类或接口。所以，对于正确编译的代码，如果加载它的类加载器不一样，那么在链接时可能会失败。

   > 可参阅论文*Dynamic Class Loading in the Java Virtual Machine*, by Sheng Liang and Gilad Bracha, in *Proceedings of OOPSLA '98*, 发表于 *ACM SIGPLAN Notices*, Volume 33, Number 10, October 1998, pages 36-44, 以及 *The Java Virtual Machine Specification, Java SE 8 Edition* 以了解更多细节。

2. 类型变量（4.4节）和类型参数（4.5.1节）在运行时是没有具化的。因此，相同的类或接口在运行时表示的是多个编译时的参数化类型（4.5节）。具体来说，给定泛型类型（第 8.1.2 节、第 9.1.2 节）的所有编译时参数化共享一个运行时表示。

   > 在特定情况下，参数化类型的对象可能会引用不是该参数化类型的变量，这种情况就是堆污染（4.12.2节）。该变量始终将表示该参数化类型

   示例 4.12.6-1、变量类型和对象的类

   ```java
   interface Colorable { 
     	void setColor(byte r, byte g, byte b); 
   } 
   class Point { int x, y; } 
   class ColoredPoint extends Point implements Colorable { 
       byte r, g, b; 
       public void setColor(byte rv, byte gv, byte bv) { 
         	r = rv; g = gv; b = bv; 
       } 
   } 
   class Test { 
       public static void main(String[] args) { 
           Point p = new Point(); 
           ColoredPoint cp = new ColoredPoint(); 
           p = cp; 
         	Colorable c = cp; 
       } 
   }
   ```

   在这个示例中：

   * `Test`类中的`main`方法中的局部变量`p`的类型为`Point`，并且被初始化赋值为对一个`Point`类的新的实例引用。
   * 类似地，局部变量`cp`的类型为`ColoredPoint`，并且被初始化赋值为对一个`ColoredPoint`新的实例引用。
   * 将`cp`的值赋值给变量`p`，这就导致了`p`持有对一个`ColoredPoint`对象的引用。这是允许的，因为`ColoredPoint`是`Point`的一个子类，因此`ColoredPoint`类与`Point`类型是赋值兼容的（5.2节）。一个`ColoredPoint`对象包括对`Point`所有方法的支持；除了特殊的域`r,g和b`之外，它还拥有`Point`类的域，即`x,y`。
   * 局部变量`c`的类型是接口类型`Colorable`，所以它可以持有任意实现了`Colorable`类的对象的引用；具体来说，它可以持有`ColoredPoint`对象的引用。

   > 注意：
   >
   > 像`new Colorable()`这样的表达式是非法的，因为是能创建类的实例，而不能创建接口的实例。但是，表达式`new Colorable() { public void setColor...}`是合法的，因为它声明了一个实现了`Colorable`接口的匿名内部类（15.9.5节）。

## 第五章 类型转换与上下文

Java编程语言编写的每一个表达式要么不产生任何结果（15.1节），要么有一个可以在编译时期推断出来的类型（15.3节）。当一个表达式出现在大多数上下文中时，它必须和上下文中期望的类型*兼容*；这个类型被称为*目标类型*。为了方便起见，表达式与其周围的上下文相兼容，可以借助下面两种方式实现：

* 首先，对于一些被称为*合成表达式*（15.2节）的表达式，推断出来的类型可能会受到目标类型的影响。同样的表达式在不同的上下文中可以有不同的类型。
* 其次，在表达式的类型被推断出来之后，会执行从表达式的类型到目标类型的隐式转换。

如果这两种方式都不能产生恰当的类型，那么就会产生编译错误。

决定一个表达式是否是合成表达式，一起在合成表达式的情况下，其在特定的上下文中的类型与兼容性，取决于上下文的类型和表达式的形式。除了影响表达式的类型，目标类型在某些情况下会影响表达式的运行时行为，以便产生恰当类型的值。

类似地，决定目标类型是否允许隐式转换的这一规则，会依据上下文的种类、表达式的类型以及在特殊场景下常量表达式的值（15.28节）而变化。从`S`类型到`T`类型的转换，将允许`S`类型的表达式在编译时期被当做`T`类型来处理。在某些情况下，这样做要求在运行时期要有相应的动作去检查这种转换的合法性，或者将该表达式运行时的值转译为适合新类型`T`的形式。

**示例5.0-1 编译时和运行时的类型转换**

* 



## 第七章 包

程序被组织成了一组包，每个包都有自己的一组类型名称，这样有助于防止名称冲突。

仅当类型声明为`public` 时，才能在声明它的包之外访问顶级类型（6.6 节）。

包的命名结构是分层的（7.1 节）。 包的成员是类和接口类型（第 7.6 节），它们在包的编译单元中声明，而子包可能包含它们自己的编译单元和子包。

包可以在文件系统或者数据库中进行存储（7.2节）。存储在文件系统中的包可以对其编译单元的组织结构进行限制，以便可以轻松地找到类的实现。

包由大量的编译单元（7.3节）组成。一个编译单元自动具有对它所在包中声明的所有类型的访问权限，并且还自动导入了在预定义包`java.lang`中声明的所有类型。

对小型程序和非正式的开发来说，包可以不具名（7.4.2节）或者拥有简单的名字，但是如果代码广发发布，就应该使用限定名来选定唯一包名。这样可以防止两个开发组碰巧选择了相同的包名，从而导致这些包在同一个程序中产生冲突。

### 7.1 包成员

包的成员有子包，以及在其所有编译单元（7.3节）中声明的所有顶层类（7.6节、8章）和顶层接口（9章）。

> 例如，在Java SE平台API中：
>
> * `java`包中有子包：`awt、applet、io、lang、net`和`util`，但是没有任何编译单元
> * `java.awt`包有子包`image`，以及大量包含类和接口类型声明的编译单元。

如果一个包的完全限定名（6.7节）是`P`，`Q`是`P`的子包，那么`P.Q`就是该子包的完全限定名，表示一个包。

一个包不能包含两个同名的成员，否则就会产生编译错误。

> 下面有些示例：
>
> * 因为`java.awt`有子包`image`，所以它不能包含命名为`image`的类或接口。
> * 如果有一个包名称为`mouse`，并且在该包中有一个成员类型为`Button`（可以用`mouse.Button`指代），那么就不能有任何完全限定名为`mouse.Button`或者`mouse.Button.Click`的包。
> * 如果`com.nighthacks.java.jag`是一个类型的完全限定名，那么就不能有任何包具有完全限定名`com.nighthacks.java.jag`或者`com.nighthacks.java.jag.scrabble`。
>
> 然而，对于不同的包的成员，是可以具有相同的名称的。例如，可以声明一个包：
>
> ```java
> package vector;
> public class Vector {
>     Object[] vec;		
> }
> ```
>
> 它有一个成员，即命名为`Vector`的`public`类，尽管`java.util`包中页声明了一个名`Vector`的类。这两个类类型是不同的，这就反应了一个事实：它们具有不同的完全限定名（6.7节）。示例中`Vector`的完全限定名是`vector.Vector`，而`java.util.Vector`是包含在Java SE平台的`Vector`类的完全限定名。因为`vector`包包含名为`Vector`的类，因此它不能包含名为`Vector`的子包。

包的层级结构命名，旨在提供一种便捷的方式使得对相关包的组织更加方便。除了能够组织包中某个子包与在该包中声明的顶层类型具有相同名字这种情形发生之外，它本身没有其他特殊的意义。

> 例如，在名为`oliver`和`oliver.twist`的包之间，或者名为`evelyn.wood`和`evelyn.waugh`的包之间，不存在特殊的访问关系。也就是说，在名为`oliver.twist`包中的代码与其他任何包中的代码相比，对`oliver`包内部声明的类型并没有更多的访问权限。

### 7.2 主机对包的支持

每一个主机系统都决定了包和编译单元是如何被创建和存储的。

每一个主机系统还决定了哪些编译单元在特定的编译中是可观察的（7.3节）。编译单元的可观察性又决定了哪些包是可观察的，哪些包是在范围内的。

在Java SE平台的简单实现中，包和编译单元可以存储在本地文件系统中。其他的实现可以使用分布式系统或其他形式的数据库存储。

如果主机系统将包和编译单元存储在数据库中，那么在对于基于文件实现中允许的编译，数据库不得强加任何可能的限制（7.6）。

> 例如，使用数据库存储包的系统不能对每个编译单元中公共类和接口的数量的最大值进行限制。

但是，使用数据库存储的系统必须提供一个选项，可以将程序转换为遵守限制的形式，以便导出到基于文件的实现系统中。

> 作为将包存储在文件系统中极其简单的例子，项目中的包、源码和二进制文件可以都存储在单个目录及其子目录中。该目录的每个直接子目录可以表示顶层包，即完全限定名由单个简单名构成的包。每一个更深层次的子目录表示由包含它的目录所表示的包的子包，依次类推。
>
> 该目录可以包含如下直接子目录：
>
> `com, gls, jag, java, wnj`
>
> 目录`java`包含Java SE平台包；目录`jag`、`gls`和`wnj`可以包含本规范的三个作者创建的用于个人使用的包，并且在这个小组中彼此共享。`com`目录包含从其他公司获取的包，这些公司使用了6.1节中的惯用描述为他们的包产生唯一的名字。
>
> 继续看这个例子，`java`目录将包含下面的子目录：
>
> `applet, awt, io, lang, net, util`
>
> 对应的包为：`java.applet, java.awt, java.io, java.lang, java.net以及java.util`，它们被定义为ava SE平台API的一部分。
>
> 再继续看这个例子，如果我们深入到`util`目录中，我们可以看到下面的文件：
>
> `BitSet.java 	Observable.java`
>
> `BitSet.class 	Observable.class`
>
> `Date.java 		Observer.java`
>
> `Date.class 	Observer.class`
>
> `...`
>
> 上述的每个`.java`文件都包含一个编译单元的源码；该编译单元包含一个类或接口的定义，这些定义的二进制编译形式包含在对应的`.class`文件中。
>
> 在这个包的简单组织下，Java SE平台有一个实现可将包名转换为路径名；该实现是通过将不同的包名拼接起来，并在相邻的部分之间放置一个文件分隔符（路径指示符）。
>
> 例如，在一个操作系统上的文件分隔符为`/`,包名如下：
>
> `jag.scrable.board`
>
> 将会被转换为目录名：
>
> `jag/scrable/board`
>
> 包名的组成部分或者类名可能会包含不能在主机文件系统的普通目录名中正确显示的字符，例如在只允许在文件名中出现ASCII字符的系统上显示Unicode字符。作为一种惯用法，该字符可以用`@`字符后面紧跟着四个表示该字符的十六进制数字值来转义，就像在`\uxxxx`转义（3.3节）一样。
>
> 在这个惯用法下，下面的包名：
>
> `children.activities.crafts.papierM\u00e2ch\u00e9`
>
> 也可以全由Unicode编写：
>
> `children.activities.crafts.papierMâché`
>
> 它可以被映射成下面的目录名：
>
> `children/activities/crafts/papierM@00e2ch@00e9`
>
> 如果`@`字符在某些主机文件系统的文件名中不是有效字符时，那么可以用其他在标识符中无效的字符来代替。

### 7.3 编译单元

<span style='font-family:"Times"'>*CompilationUnit*</span>是Java程序的句法（2.3节）的目标符号（2.1节）。由下面的产生式定义：

​	<span style='font-family:"Times"'>*ComplilationUnit:*</span>

​		<span style='font-family:"Times"'>*[PackageDeclaration]{ImportDeclaration} {TypeDeclaration}*</span>

一个*编译单元*包含三部分，每个部分都是可选的：

* `package`声明（7.4节），给出该编译单元所属的包的完全限定名（6.7节）

  不包含`package`声明的编译单元是不具名包（7.4.2节）的一部分。

* `import`声明（7.5节），允许来自其他包的类型和类型为`static`的成员使用其简单名进行引用。

* 类和接口类型的顶层类型声明。

每一个编译单元隐式导入声明在预定义包`java.lang`中的所有`public`类型名，就好像我们在每个编译单元的开头紧跟`package`语句后编写了`import java.lang.*;`声明一样。因此，所有这些类型的名字在每个编译单元中都可以用简单名访问。

预定义包`java`中的所有编译单元和它的子包`lang`与`io`总是*可观察的*。

对于其他所有包，主机系统将决定哪些编译单元是可观察的。

<span style='font-size:14px'>编译单元的可观察性会影响其包的客观察性。</span>

不同编译单元中声明的类型可以彼此循环依赖。Java编译器必须妥善安排，以便可以同时编译所有这样的类型。

### 7.4 包声明

#### 7.4.1 具名包

编译单元中的*包声明*指定了编译单元所归属的包的名字（6.2节）。

​	<span style='font-family:"Times"'>*PackageDeclaration:*</span>

​		<span style='font-family:"Times"'>*{PackageModifier}* package *Identifier {*. *Identifier}* ;</span>

​	<span style='font-family:"Times"'>*PackageModifier:*</span>

​		<span style='font-family:"Times"'>*Annotation*</span>

在`package`声明中提及到的包名必须是该包的完全限定名（6.7节）。

包声明的作用域和遮蔽规则在第6.3节和第6.4节中进行了说明。

针对包声明上的注解修饰符的规则在第9.7.4节和9.7.5节进行了说明。

在给定的包上，最多只允许有一个被注解的`package`声明。

<span style='font-size:14px'>The manner in swhich this restriction is enforced must, of necessity, vary from implementation to 	implementation. The following scheme is strongly recommended for file-system-based implementations: The sole annotated package declaration, if it exists, is placed in a source file called package-info.java in the directory containing the source files for the package. This file does not contain the source for a class called package-info.java; indeed it would be illegal for it to do so, as package-info is not a legal identifier. Typically package-info.java contains only a package declaration, preceded immediately by the annotations on the package. While the file could technically contain the source code for one or more classes with package access, it would be very bad form.</span>

<span style='font-size:14px'>It is recommended that package-info.java, if it is present, take the place of package.html for javadoc and other similar documentation generation systems. If this file is present, the documentation generation tool should look for the package documentation comment immediately preceding the (possibly annotated) package declaration in package-info.java. In this way, package-info.java becomes the sole repository for package-level annotations and documentation. If, in future, it becomes desirable to add any other package-level information, this file should prove a convenient home for this information.</span>

#### 7.4.2 不具名包

没有任何`package`声明的编译单元是不具名包的一部分。

未命名包是Java SE平台提供的一种机制，主要是为了在开发小型或临时应用，或者在刚刚开始开发时提供便捷方式。

不具名包不能有子包，因为`package`声明的语法总是要包含对具名的顶层包的引用。

Java SE平台的实现必须支持至少有一个不具名包。当然它也可以支持多个不具名包，这不是强制要求的。在每个不具名包中包含哪些编译单元是由主机系统决定的。

<span style='font-size:12px'>例如，下面的编译单元：</span>

```java
class FirstCall { 
    public static void main(String[] args) { 
      System.out.println("Mr. Watson, come here. I want you."); 
    } 
}
```

定义了一个非常简单的编译单元，其作为不具名包的一部分。

Java SE平台实现中，使用了层级文件系统来存储包，一种典型的策略是将不具名包与每个目录相关联；在任意时刻；一次只能观察到一个不具名的包，即与“当前工作目录”相关联的包。 “当前工作目录”的确切含义取决于主机系统。

### 7.4.3 包的可观察性

包是*可观察的*，当且仅当：

* 包含包的声明的编译单元是可观察的（7.3节）。
* 包的子包是可观察的。

`java, java.lang`和`java.io`总是可观察的。

<span style='font-size:14px'>可以从上述的规则以及可观察编译单元的规则中得出下面的结论。预定义包`java.lang`声明了`Object`类，依次`Object`的编译单元总是可观察的（7.3节）。因此，`java.lang`包是可观察的（7.4.3节），而`java`包也是如此。更进一步，由于`Object`包是可观察的，`Object[]`数组类型隐式地也是如此，它的超接口`java.io.Serializable`（10.1节）也是如此，因此`java.io`包也是可观察的。</span>

### 7.5 Import声明

<span style='font-family:Times'>*Import声明*</span>使得剧具名类或者`static`成员可通过单个标识符构成的简单名（6.2）进行引用。

如果没有使用恰当的import声明，引用另外一个包中声明的类型或者另一个类型的`static`成员的唯一方法就是使用完全限定名（6.7节）。

​	<span style='font-family:Times'>*ImportDeclaration:*</span>

​		<span style='font-family:Times'>*SingleTypeImportDeclaration*</span>

​		<span style='font-family:Times'>*TypeImportOnDemandDeclaration*</span>

​		<span style='font-family:Times'>*SingleStaticImportDeclaration*</span>

​		<span style='font-family:Times'>*StaticImportOnDemandDeclaration*</span>

* 单类型导入声明（single-type-import）（7.5.1节）通过其规范名称（第 6.7 节）来导入单个具名类型。
* 按需类型导入声明（type-import-on-demand）（7.5.2节）通过类型或包的规范名称来导入具名类型或具名包中所有需要的可访问类型。
* 单静态导入申明（single-static-import）（7.5.3节）通过给定规范名来导入类型中给定的所有可访问的`static`成员。
* 按需静态导入声明（static-import-on-demand）（7、5、4节）通过提及类型的规范名称来导入具名类型中所有需要的可访问`static`成员

通过这些声明导入的类型或成员的作用域和遮蔽规则在6.3节和6.4节中进行了说明。

import声明使得在实际包含import声明的编译单元中只需要通过简单名就可以访问该类或成员。通过`import`声明引入的类型或成员的作用域并不包含在相同包中的其他编译单元、当前编译单元中的其他`import`声明，或者当前编译单元中的`package`声明（除了`package`声明的注解）。

#### 7.5.1 单类型导入声明

一个*单类型导入声明*通过其给定类型的规范名导入了单个类型，使其在该单类型导入声明所在的编译单元的类或接口声明中使用简单的名称即可获取它们。

<span style='font-family:Times'>*SingleTypeImportDeclaration:*</span>

​	<span style='font-family:Times'>import *TypeName* ;</span>

<span style='font-family:Times'>TypeName</span>必须是类类型、接口类型、枚举类型或注解类型的规范名（6.7节）。

该类型名必须是限定的（6.5.5.2节），否则会出现编译时错误。

如果具名类型不可访问（6.6节），那么这就是一个编译时错误。

如果在同一个编译单元中尝试去使用单类型导入声明导入两个具有相同的简单名的类型，那么将会产生编译时错误；除非这两个是同一个类型，在这种情况下，重复声明将被忽略。

如果由单类型导入声明导入的类型是包含该import声明的编译单元中声明的，那么该`import`声明将会被忽略。

如果单类型导入声明导入的类型的简单明是*`n`*，并且编译单元也声明了简单名为*`n`*的顶层类型（7.6），那么就会产生编译错误。

如果编译单元中包含使用单类型导入声明导入的简单名为*`n`*的类型，同时又包含使用单静态导入声明导入的简单名亦为*`n`*的类型，此时将会产生编译错误。

**示例7.5.1-1. 单类型导入**

​	`import java.util.Vector;`

上面的代码使得名为`Vector`在某个编译单元中的类和接口中是可用的。因此，在任何没有被具有相同名字的域、参数、局部变量声明，或嵌套类型声明所遮蔽（6.4.1节）或遮掩（6.4.2节）的地方，简单名`Vector`都将引用`java.util`包中的`Vector`的类型声明。

> 注意：
>
> `java.util.vector`被声明为泛型（8.1.2节）。一旦被导入，名称 `Vector` 可在参数化类型（如 `Vector<String>`）中使用，无需限定，或用作原始类型 `Vector`。 `import`声明的一个相关限制是可以导入在泛型类型声明中声明的嵌套类型，但它的外部类型总是被删除。

**示例7.5.1-2. 重复类型声明**

下面这段程序：

```java
import java.util.Vector;
class Vector { Object[] vec; }
```

将会产生编译时错误，因为`Vector`声明重复，下面的程序也是如此：

```java
import java.util.Vector;
import myVector.Vector;
```

其中`myVector`是包含在下面的编译单元的包：

```java
package myVector;
public class Vector { Object[] vec; }
```

**示例 7.5.1-3. 不导入任何子包**

> 注意：
>
> `import`语句不能导入子包，只能导入类型。

例如，试图导入`java.util`，然后使用`util.Random`这个名字来引用`java.util.Random`类型是不可行的：

```java
import java.util;
class Test { util.Random generator; }
 // incorrect: compile-time error
```

**示例7.5.1-4. 导入的类型名也是包名**

按照6.1节中描述的命名习惯中，包名和类型名通常是不同的。尽管如此，在下面这个刻意设计的代码示例中，有一个不按照习惯命名的包`Vector`，它声明了名为`Mosquito`的公共类：

```java
package Vector; 
public class Mosquito { int capacity; }
```

这样，对于下面的编译单元：

```java
package strange; 
import java.util.Vector; 
import Vector.Mosquito; 
class Test { 
 public static void main(String[] args) { 
 System.out.println(new Vector().getClass()); 
 System.out.println(new Mosquito().getClass()); 
 } 
}
```

使用单类型导入声明从`java.util`包中导入`Vector`类，并没有妨碍包`Vector`在随后的`import`声明中出现和被正确识别。这个示例可以正确编译，并产生下面的输出：

```
class java.util.Vector
class Vector.Mosquito
```

#### 7.5.2 按需类型导入声明

按需类型导入声明允许具名包或类型的所有可访问类型都可以按需导入。

<span style='font-family:Times'>*TypeImportOnDemandDeclaration:*</span>

​	<span style='font-family:Times'>import *PackageOrTypeName* . * ;</span>

*`PackageOrTypeName`*必须是包、类类型、接口类型、枚举类型或注解类型的规范名（6.7节）。

如果*`PackageOrTypeName`*表示的是类型（6.5.4节），那么该名字必须是限定名（6.5.5.2节），否则将会产生编译时错误。

如果具名的包或类型是不可访问的（6.6节），将会产生编译时错误。

如果在按需类型导入声明中导入的是当前编译单元的包名或者`java.lang`，那么不会产生编译错误，在这种情况下，按需类型导入声明会被忽略。

在同一个编译单元中两个或多个按需类型导入声明可以有相同的类型或包，但是除了其中的一个声明外，其他的声明都被认为是冗余的，其效果就像是该类型只被导入了一次。

在一个编译单元中，同时包含了相同类型的按需类型导入声明和按需静态导入类型声明（7.5.4节），其效果就像是该类型的`static`成员（8.5节，9.5节）只被导入了一次。

**示例 7.5.2-1. 按需类型导入**

​	`import java.util.*;`

上面的代码在`java.util`包中声明的所有`public`类型的简单名在该编译单元的类和接口声明中都是可用的。因此，简单名`Vector`在该编译单元中引用的应该是`java.util`包中的`Vector`类型，只要该类型声明没有被遮蔽（6.4.1节）或遮掩（6.4.2节）。

该声明可能会被简单名是`Vector`的类型的单类型导入声明所遮蔽，或者被在该编译单元所属的包中声明为`Vector`的类型所遮蔽，或者被任何嵌套类或接口所遮蔽。

该声明可能被命名为`Vector`的域、参数或局部变量所遮掩

（上述的情况都属于不寻常情况。）

#### 7.5.3 单静态导入声明

*单静态导入声明*从某个类型中导入了具有给定简单名的所有可访问的`static`成员。这使得在编译单元中类或接口的声明中，使用简单名即可访问通过单静态导入声明的静态成员。

<span style='font-family:Times'>*SingleStaticImportDeclaration:*</span>

​	<span style='font-family:Times'>import static *TypeName* . *Identifier* ;</span>

*`TypeName`*必须是类类型、接口类型、枚举类型或注解类型的规范名（6.7节）。

该名字必须是限定名（6.5.5.2节），否则将会产生编译时错误。

如果具名类型不可访问（6.6节），那么将会产生编译错误。

*`Identifier`*必是须是具名类型中的一个`static`成员（至少一个）。如果没有任何具有该名称的`static`成员，将会产生编译错误，或者所有的具名成员都都是不可访问的。

允许一个单静态导入声明导入具有相同名称的字段或者类型，或者多个具有相同名称和签名的方法。

如果一个单静态导入声明导入的简单名是*`n`*，而该编译单元也命名了简单名为*`n`*的顶层类（7.6节），此时会发生编译错误。

如果一个编译单元既包含通过静态导入声明导入的类型，其简单名是*`n`*，又包含一个通过静态导入声明（7.5.1节）导入的类型，其简单名也是*`n`*， 此时会发生编译错误。

#### 7.5.4 按需静态导入声明

*按需静态导入声明*允许具名类型的所有可访问的`static`成员都可以按需导入。

<span style='font-family:Times'>*StaticImportOnDemandDeclaration:*</span>

​	<span style='font-family:Times'>import static *TypeName* . * ;</span>

<span style='font-family:Times'>*TypeName*</span>必须是类类型、接口类型、枚举类型或者注解类型的规范名（6.7节）。

该名称必须是限定名（6.5.5.2节），否则将会发生编译错误。

如果类型是不可访问的（6.6节），将会发生编译错误。

同一个编译单元中可以有两个或者更多的按需静态导入声明可以导入具有相同名称的类型，其效果就像是只有一个这种声明。

同一个编译单元中可以有两个或者更多的按需静态导入声明可以导入具有相同名称的成员，其效果就像是该成员值被导入了一次。

一个按需静态导入声明导入具有相同名字的多个域或类型，或者具有相同名字和签名的多个方法是允许的。

如果一个编译单元中同时包含两个名称相同类型的按需静态导入声明和按需类型导入声明（7.5.2节），其效果就像是该类型的`static`成员类型只被导入了一次。

### 7.6 顶层类声明

*顶层类型声明*所声明的是顶层类类型（第8章）和顶层接口类型（第9章）。

<span style='font-family:Times'>*TypeDeclaration:*</span>

​	<span style='font-family:Times'>*ClassDeclaration*</span>

​	<span style='font-family:Times'>*InterfaceDeclaration*</span>

​	;

<span style='font-size:14px'>在编译单元的顶层类型声明中出现的额外的";"符号对编译单元的含义没有不会产生任何影响。Java编程语言中允许出现单独的分号，这可以被看作是对习惯了在类声明后面添加";"的C++程序员的一种妥协。在新的Java代码中不应该使用它们。</span>

在没有任何访问修饰符时，顶层类型将具有包访问权限：它只能被在声明它的包的编译单元内被访问（6.6.1节）。一个类型可以声明为`public`，这样就授予了其他包中的代码可以访问该类型的权限。

如果顶层类型声明中包含下列访问修饰符：`protected`、`private`或`static`，此时将会产生编译时错误。

如果同一个包中的任何其他顶层类或接口类型的名字和当前的顶层类的名字相同，此时将会产生编译时错误。

顶层类的作用域和遮蔽规则在第6.3节和第6.4节中进行了说明。

顶层类的完全限定名在第6.7节中进行了说明。

**示例7.6-1. 顶层类型声明的冲突**

```java
package test; 
import java.util.Vector; 
class Point { 
 int x, y; 
} 
interface Point { // compile-time error #1 
 int getR(); 
 int getTheta(); 
} 
class Vector { Point[] pts; } // compile-time error #2
```

其中，第一处编译异常是由于在同一个包中`Point`名字在一个类和一个接口中进行了重复声明而引起的。第二个编译错误是由于尝试去通过类类型声明和单类型导入声明来声明`Vector`名字而引起的。

但是需要注意的是，类名也可能是和在编译单元中按需类型导入声明（7.5.2节）中的的类名相同，这并不是个错误。因此，在下面的程序中：

```java
package test; 
import java.util.*; 
class Vector {} // not a compile-time error
```

`Vector`类的声明是允许的，即使还有一个`java.utl.Vector`类。在该编译单元中，简单名`Vector`引用的是`test.Vector`类，而不是`java.util.Vector`（它仍旧可以被编译单元中的代码所引用，但是只能通过完全限定名引用）。

**示例7.6-2. 顶层类的作用域**

```java
package points; 
class Point { 
 int x, y; // coordinates 
 PointColor color; // color of this point 
 Point next; // next point with this color 
 static int nPoints; 
} 
class PointColor { 
 Point first; // first point with this color 
 PointColor(int color) { this.color = color; } 
 private int color; // color components 
}
```

这个程序中定义了两个类，它们在其类成员中彼此使用对方。因为类类型`Point`和`PointColor`包含包`points`中所有的类型声明，包括当前编译单元中的所有类型声明，作为其作用域，所以这个程序可以被正确的编译。即，向前引用不是问题。

**示例7.6-3. 完全限定名**

```java
class Point { int x, y; }
```

在这段代码中，`Point`类是在没有任何`package`语句的编译单元中声明的；因此`Point`就是其完全限定名。但是，在下面的代码中：

```java
package vista;
class Point { int x, y; }
```

`Point`类的完全限定名是`vista.Point`。（包名`vista`适合于局部或个人使用。如果想让这个包被广泛的部署使用，最好还是起一个唯一的包名（6.1节）。）

Java SE平台的实现必须用二进制名（13.1节）跟踪包内的类型。一个类型的多重命名方式必须可以扩展成二进制名，以确保这些名字被理解的类型和引用的类型是相同的。

例如，如果有下面的这个包含单类型导入声明（7.5.1节）的编译单元：

```java
import java.util.Vector;
```

那么在这个编译单元内，简单名`Vector`和完全限定名`java.util.Vector`引用的是相同的名字。

当且仅当包存储在文件系统中（7.2节）时，主机系统才可以选择强行限制：在下列两个条件之一时，如果在文件名由类型名加上扩展名（例如，`.java`或`.jav`）构成的文件中找不到该类型，那就会产生一个编译错误：

* 该类型被声明该类型的包中的其他编译单元中的代码所引用。
* 该类型被声明为`public`（并且此可以被在其他包中的代码所访问）。

这个限制表明每个编译单元必须至少有一个这种类型。这条限制使得Java编译器可以很容易地找到包中的具名类。实际上，许多开发者选择将每个类或者接口类型都置于其自己的编译单元中，无论它是`public`的，还是会被其他编译单元中的代码所引用。

例如，`public`类型`wet.sprocket,Toad`的源码可以在目录`wet/sprocket`中的`Toad.java`文件中找到，而相应的对象代码可以在同一个目录下的`Toad,class`文件中找到。

## 第8章 类



## 第9章 接口



## 第10章 数组

在Java编程语言中，*数组*是动态创建的对象（4.3.1节），可以被赋值给`Object`类型（4.3.2节）的变量。`Object`类的所有方法都可以在数组上调用。

数组对象包含大量的变量。变量的数量也可以是0，这种情况下我们称该数组为*empty*空。数组中包含的变量没有名字，可通过数组表达式引用，这种表达式使用非负整数进行索引。这些变量被称作数组的元素。如果一个数组有*`n`*个元素，我们可以说数组的长度是*`n`*；数组中的元素可使用0-*n-1*（包含）的整型进行索引。

数组中的所有元素都有着相同的类型，称作为数组的*元素类型*（*conponent type*）。如果数组的元素类型是*T*，那么数组的类型可被写作*T[]*。

`float`类型的数组元素的值总是float值集（第4.2.3）中的元素；类似地，`double`类型的数组元素的值总是double值集中的元素。不允许`float`类型的数组中的元素值是仅存在于float扩展指数值集，而不存在于float值集，同样地，`double`类型的的数组中的元素也不允许出现那种仅存在于double扩展指数集，而不存在与double值集中的值。

数组中的元素类型也可以是数组类型。这种数组中的元素可以包含子数组的引用。对于任意数组类型，我们看其元素类型，如果它仍旧是数组类型，那么我们就继续去看该类型的元素类型，以此类推，最终必须会有一个元素类型不是数组类型，它被称为源数组的成员类型（*element type*），并且在这个数据结构级别上的元素被称为源数组的成员。















## 第11章 异常



## 第12章 执行

## 第13章 二进制兼容性

## 第14章 块和语句

## 第15章 表达式

## 第16章 明确赋值



## 第17章 线程与锁

## 第18章 类型推断

## 第19章语法

## 索引















