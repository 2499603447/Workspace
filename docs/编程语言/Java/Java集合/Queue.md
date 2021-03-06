# Queue

Queue接口位于java.util包下，并继承自Collection接口。Queue集合同于存储待处理数据并提供各种操作，如插入，删除等等。其是一系列有序的对象列表，插入元素只限于在列表的尾部，删除元素只限于在列表的开头。它遵循FIFO（First-In-First-Out）原理。Java中最常见的Queue接口的具体声明实体类为PriorityQueue以及LinkedList。需要注意的是这两个都不是线程安全的。*PriorityBlockingQueue*是线程安全的。队列的一些重要特性如下：

* 队列通常是在队列的尾部插入数据，在队列的头部进行删除，遵循FIFO
* Java中的Queue支持所有的Collection接口中的所有操作，包括插入，删除等等
* LinkedList，ArrayBlockingQueue以及PriorityQueue是最常用的几个实现类
* 对BlockingQueue进行任何空指针的相关操作，将会抛出NPE
* BlockingQueue是线程安全的
* java.util中的可用队列是“无界”队列
* java.util.concurrent包中可用的队列是有界队列
* 除Deque之外，所有的队列支持在队列尾删除元素，在队列头部插入元素；Deque两端都支持插入和删除

## Queue中方的方法

1. **add()-** T此方法用于在队列尾部添加元素。 更具体地说，如果使用链表，则在链表的最后，或者在实施优先队列的情况下，根据优先级。
2. **peek()-** This method is used to view the head of queue without removing it. It returns Null if the queue is empty.
3. **element()-** This method is similar to peek(). It throws *NoSuchElementException* when the queue is empty.
4. **remove()-** This method removes and returns the head of the queue. It throws *NoSuchElementException* when the queue is empty.
5. **poll()-** This method removes and returns the head of the queue. It returns null if the queue is empty.
6. **size()-** This method return the no. of elements in the queue.

![img](Queue.assets/Selection_031.png)

由于其是Collection的子类，所以其继承了Collection的所有方法，size(), isEmpty(), contains()等

下面是简单的示例用于展示这些方法;

```java
// Java orogram to demonstrate working of Queue 
// interface in Java 
import java.util.LinkedList; 
import java.util.Queue; 
  
public class QueueExample 
{ 
  public static void main(String[] args) 
  { 
    Queue<Integer> q = new LinkedList<>(); 
  
    // Adds elements {0, 1, 2, 3, 4} to queue 
    for (int i=0; i<5; i++) 
     q.add(i); 
  
    // Display contents of the queue. 
    System.out.println("Elements of queue-"+q); 
  
    // To remove the head of queue. 
    int removedele = q.remove(); 
    System.out.println("removed element-" + removedele); 
  
    System.out.println(q); 
  
    // To view the head of queue 
    int head = q.peek(); 
    System.out.println("head of queue-" + head); 
  
    // Rest all methods of collection interface, 
    // Like size and contains can be used with this 
    // implementation. 
    int size = q.size(); 
    System.out.println("Size of queue-" + size); 
  } 
} 
```

输出：

```
Elements of queue-[0, 1, 2, 3, 4]
removed element-0
[1, 2, 3, 4]
head of queue-1
Size of queue-4
```

更多的队列相关知识请看Java基础篇-常见的集合-Queue