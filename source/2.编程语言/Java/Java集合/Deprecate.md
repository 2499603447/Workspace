# Deprecate集合

Java集合框架的更新迭代至今，其中Vector，Stack，HashTable已经废弃，不建议使用

## Vector

Vector 是**矢量队列**，它是JDK1.0版本添加的类。继承于AbstractList，实现了List, RandomAccess, Cloneable这些接口。
Vector 继承了AbstractList，实现了List；所以，**它是一个队列，支持相关的添加、删除、修改、遍历等功能**。
Vector 实现了RandmoAccess接口，即**提供了随机访问功能**。RandmoAccess是java中用来被List实现，为List提供快速访问功能的。在Vector中，我们即可以通过元素的序号快速获取元素对象；这就是快速随机访问。
Vector 实现了Cloneable接口，即实现clone()函数。它能被克隆。

和ArrayList不同，**Vector中的操作是线程安全的**；

其实ArrayListhe和Vector在用法上完全相同.但由于Vector是一个古老的集合.(从jdk1.0就有了),那时候java还没有提供系统的集合框架,所以在Vector里提供了一些方法名很长的方法.例如:addElement(Object obj),实际上这个方法和add(Object obj)没什么区别.

**从jdk1.2以后,Java提供了系统的集合框架,就将Vector改为实现List接口,作为List的实现之一**,从而导致Vector里有一些重复的方法.

Vector里有一些功能重复的方法,这些方法中方法名更短的是属于后来新增的方法.更长的是原先vector的方法.而后来ArrayList是作为List的主要实现类.看过的Java思想编程中也提到了Vector有很多缺点.尽量少用Vector实现类.

## Stack

public class Stack<E>**extends Vector**

由于Vector是通过数组实现的，这就意味着，Stack也是通过数组实现的，而非链表。

Stack类表示后进先出（LIFO）的对象堆栈。它通过五个操作对类Vector进行了扩展 ，允许将向量视为堆栈。它提供了通常的push和pop操作，以及取堆栈顶点的peek方法、测试堆栈是否为空的empty方法、在堆栈中查找项并确定到堆栈顶距离的search方法。

首次创建堆栈时，它不包含项。

**Deque** **接口及其实现提供了 LIFO 堆栈操作的更完整和更一致的 set，应该优先使用此 set，而非此类**。例如：

Deque<Integer> stack = new ArrayDeque<Integer>();

## 结论

**这两个都是jdk1.0的过时API,应该避免使用.因此不再对其源码进行解析学习.**

**jdk1.5新增了很多多线程情况下使用的集合类.位于java.util.concurrent.**

**如果你说,Vector是同步的,你要在多线程使用.那你应该使用java.util.concurrent.CopyOnWriteArrayList等而不是Vector.**

**如果你要使用Stack做类似的业务.那么非线程的你可以选择linkedList,多线程情况你可以选择java.util.concurrent.ConcurrentLinkedDeque 或者java.util.concurrent.ConcurrentLinkedQueue**

**多线程情况下,应尽量使用java.util.concurrent包下的类.**