# ArrayList

ArrayList是集合框架的一部分，处于java.util包下。它给我们提供了动态数组。尽管它可能比标准的数组慢，但是在程序需要对数组进行许多操作时确很有用。

* ArrayList继承自AbstractList类并实现了List接口
* ArrayList一开始会初始化一个大小，然而这个大小会随着集合元素的增加或减少而变化
* ArrayList允许我们随机访问list
* ArrayList不能用于存储原始数据类型，像int，char等等。针对这些情况，我们需要一个包装类
* Java中的ArrayList可以看做和c++中的vector类似

![java-arraylist](List.assets/java-collection-1586135575496.jpg)

最基本的ArrayList可以有构造器和方法组成。下面列出了一系列构造器和方法，同时还列出了一些使用方法

## ArrayList构造器

1. ArrayList(): 用于构造一个空的list
2. ArrayList(Collection c): 从集合c中构造一个list
3. ArrayList(int capacity): 指定初始容量的list

下面是创建一个带泛型的ArrayList

```java
// Creating generic integer ArrayList
ArrayList<Integer> arrli = new ArrayList<Integer>();
```

```java
// Java program to demonstrate working of ArrayList in Java 
import java.io.*; 
import java.util.*; 
  
class arrayli 
{ 
    public static void main(String[] args) 
                       throws IOException 
    { 
        // size of ArrayList 
        int n = 5; 
  
        //declaring ArrayList with initial size n 
        ArrayList<Integer> arrli = new ArrayList<Integer>(n); 
  
        // Appending the new element at the end of the list 
        for (int i=1; i<=n; i++) 
            arrli.add(i); 
  
        // Printing elements 
        System.out.println(arrli); 
  
        // Remove element at index 3 
        arrli.remove(3); 
  
        // Displaying ArrayList after deletion 
        System.out.println(arrli); 
  
        // Printing elements one by one 
        for (int i=0; i<arrli.size(); i++) 
            System.out.print(arrli.get(i)+" "); 
    } 
} 
```

输出：

```
[1, 2, 3, 4, 5]
[1, 2, 3, 5]
1 2 3 5 
```

## ArrayList中的方法

1. [forEach(Consumer<? super E> action):](https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/) Performs the given action for each element of the Iterable until all elements have been processed or the action throws an exception.
2. [retainAll(Collection<?> c):](https://www.geeksforgeeks.org/arraylist-retainall-method-in-java/) Retains only the elements in this list that are contained in the specified collection.
3. [removeIf(Predicate<? super E> filter):](https://www.geeksforgeeks.org/arraylist-removeif-method-in-java/) Removes all of the elements of this collection that satisfy the given predicate.
4. [contains(Object o):](https://www.geeksforgeeks.org/arraylist-contains-java/) Returns true if this list contains the specified element.
5. [remove(int index):](https://www.geeksforgeeks.org/arraylist-linkedlist-remove-methods-java-examples/) Removes the element at the specified position in this list.
6. [remove(Object o):](https://www.geeksforgeeks.org/arraylist-linkedlist-remove-methods-java-examples/) Removes the first occurrence of the specified element from this list, if it is present.
7. [get(int index):](https://www.geeksforgeeks.org/arraylist-get-method-java-examples/) Returns the element at the specified position in this list.
8. [subList(int fromIndex, int toIndex):](https://www.geeksforgeeks.org/arraylist-sublist-method-in-java-with-examples/) Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
9. [spliterator():](https://www.geeksforgeeks.org/arraylist-spliterator-method-in-java/) Creates a late-binding and fail-fast Spliterator over the elements in this list.
10. [set(int index, E element):](https://www.geeksforgeeks.org/arraylist-set-method-in-java-with-examples/) Replaces the element at the specified position in this list with the specified element.
11. [size():](https://www.geeksforgeeks.org/arraylist-size-method-in-java-with-examples/) Returns the number of elements in this list.
12. [removeAll(Collection<?> c):](https://www.geeksforgeeks.org/arraylist-removeall-method-in-java-with-examples/) Removes from this list all of its elements that are contained in the specified collection.
13. [ensureCapacity(int minCapacity):](https://www.geeksforgeeks.org/arraylist-ensurecapacity-method-in-java-with-examples/) Increases the capacity of this ArrayList instance, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument.
14. [listIterator():](https://www.geeksforgeeks.org/arraylist-listiterator-method-in-java-with-examples/) Returns a list iterator over the elements in this list (in proper sequence).
15. [listIterator(int index):](https://www.geeksforgeeks.org/arraylist-listiterator-method-in-java-with-examples/) Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.
16. [isEmpty():](https://www.geeksforgeeks.org/arraylist-isempty-java-example/) Returns true if this list contains no elements.
17. [removeRange(int fromIndex, int toIndex):](https://www.geeksforgeeks.org/arraylist-removerange-java-examples/) Removes from this list all of the elements whose index is between fromIndex, inclusive, and toIndex, exclusive.
18. [void clear():](https://www.geeksforgeeks.org/arraylist-clear-java-examples/) This method is used to remove all the elements from any list.
19. [void add(int index, Object element):](https://www.geeksforgeeks.org/java-util-arraylist-add-method-java/) This method is used to insert a specific element at a specific position index in a list.
20. [void trimToSize():](https://www.geeksforgeeks.org/arraylist-trimtosize-java-example/) This method is used to trim the capacity of the instance of the ArrayLis to the list’s current size.
21. [int indexOf(Object O):](https://www.geeksforgeeks.org/java-util-arraylist-indexof-java/) The
    index the first occurrence of a specific element is either returned, or -1 in case the element is not in the list.
22. [int lastIndexOf(Object O):](https://www.geeksforgeeks.org/arraylist-lastindexof-java-example/) The index the last occurrence of a specific element is either returned, or -1 in case the element is not in the list.
23. [Object clone():](https://www.geeksforgeeks.org/clone-method-in-java-2/) This method is used to return a shallow copy of an ArrayList.
24. [Object[\] toArray():](https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/) This method is used to return an array containing all of the elements in the list in correct order.
25. [Object[\] toArray(Object[] O)](https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/): It is also used to return an array containing all of the elements in this list in the correct order same as the previous method.
26. [boolean addAll(Collection C):](https://www.geeksforgeeks.org/java-util-arraylist-addall-method-java/) This method is used to append all the elements from a specific collection to the end of the mentioned list, in such a order that the values are returned by the specified collection’s iterator.
27. [boolean add(Object o):](https://www.geeksforgeeks.org/java-util-arraylist-add-method-java/) This method is used to append a specificd element to the end of a list.
28. [boolean addAll(int index, Collection C):](https://www.geeksforgeeks.org/java-util-arraylist-addall-method-java/) Used to insert all of the elements starting at the specified position from a specific collection into the mentioned list.

## ArrayList源码分析

见Java源码分析-List-ArrayList

# LinkedList

链表是一个线性的数据结构，其中元素并不是按物理地址连续存储的；每个元素节点中的数据域和地址域分开存储。元素之间使用指针和地址进行链接。每个元素称为一个节点Node。由于其动态性以及插入和删除的简便性，在某些方面要优于array。当然，它也有一些缺点，如无法直接访问节点，当我们需要访问某个节点时，我们需要从头开始，然后挨个链接到下一个节点以找到我们想要访问的那个节点。

为了将元素存储在链表中，我们使用双端链表；其拥有线性数据结构，同时继承自一个抽象类以及实现List和Deque接口。

Java中，LinkedList类实现了List接口。LinkedList类同时包含各种构造函数以及方法。

## LinekdList构造函数

1. LinkedList()：用于创建一个空的链表
2. LinkedList(Collection c)：用于创建一个包含指定集合中所有元素的有序list

```java
// Java code for Linked List implementation 
  
import java.util.*; 
  
public class Test 
{ 
    public static void main(String args[]) 
    { 
        // Creating object of class linked list 
        LinkedList<String> object = new LinkedList<String>(); 
  
        // Adding elements to the linked list 
        object.add("A"); 
        object.add("B"); 
        object.addLast("C"); 
        object.addFirst("D"); 
        object.add(2, "E"); 
        object.add("F"); 
        object.add("G"); 
        System.out.println("Linked list : " + object); 
  
        // Removing elements from the linked list 
        object.remove("B"); 
        object.remove(3); 
        object.removeFirst(); 
        object.removeLast(); 
        System.out.println("Linked list after deletion: " + object); 
  
        // Finding elements in the linked list 
        boolean status = object.contains("E"); 
  
        if(status) 
            System.out.println("List contains the element 'E' "); 
        else
            System.out.println("List doesn't contain the element 'E'"); 
  
        // Number of elements in the linked list 
        int size = object.size(); 
        System.out.println("Size of linked list = " + size); 
  
        // Get and set elements from linked list 
        Object element = object.get(2); 
        System.out.println("Element returned by get() : " + element); 
        object.set(2, "Y"); 
        System.out.println("Linked list after change : " + object); 
    } 
} 
```

输出：

```
Linked list : [D, A, E, B, C, F, G]
Linked list after deletion: [A, E, F]
List contains the element 'E' 
Size of linked list = 3
Element returned by get() : F
Linked list after change : [A, E, Y]
```

## LinkedList() 方法

1. **[add(int index, E element):](https://www.geeksforgeeks.org/java-util-linkedlist-add-method-in-java/)** 在list中的指定位置插入指定的元素。
2. **[add(E e):](https://www.geeksforgeeks.org/java-util-linkedlist-add-method-in-java/)** 在list末尾插入指定元素。
3. **[addAll(int index, Collection c):](https://www.geeksforgeeks.org/java-util-linkedlist-addall-method-in-java/)** 将指定集合中的所以元素插入到list中的指定位置。
4. **[addAll(Collection c):](https://www.geeksforgeeks.org/java-util-linkedlist-addall-method-in-java/)** 将指定集合中的所有元素以其iterator返回的顺序追加到list中的末尾
5. **[addFirst(E e):](https://www.geeksforgeeks.org/linkedlist-addfirst-method-in-java/)** 将指定元素插入到list的开始处。
6. **[addLast(E e):](https://www.geeksforgeeks.org/linkedlist-addlast-method-in-java/)** 在list末尾插入指定元素。
7. **[clear():](https://www.geeksforgeeks.org/linkedlist-clear-method-in-java/)** 移除list中的所有元素.
8. **[clone():](https://www.geeksforgeeks.org/linkedlist-clone-method-in-java/)** 返回当前list的一份浅拷贝的数据.
9. **[contains(Object o):](https://www.geeksforgeeks.org/linkedlist-contains-method-in-java/)** This method returns true if this list contains the specified element.
10. **descendingIterator():** This method returns an iterator over the elements in this deque in reverse sequential order.
11. **[element():](https://www.geeksforgeeks.org/linkedlist-element-method-in-java-with- examples/)** This method retrieves, but does not remove, the head (first element) of this list.
12. **[get(int index)](https://www.geeksforgeeks.org/linkedlist-get-method-in-java/):** This method returns the element at the specified position in this list.
13. **[getFirst():](https://www.geeksforgeeks.org/java-util-linkedlist-get-getfirst-getlast-java/)** This method returns the first element in this list.
14. **[getLast():](https://www.geeksforgeeks.org/linkedlist-getlast-method-in-java/)** This method returns the last element in this list.
15. **[indexOf(Object o):](https://www.geeksforgeeks.org/linkedlist-indexof-method-in-java/)** This method returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
16. **[lastIndexOf(Object o):](https://www.geeksforgeeks.org/linkedlist-lastindexof-method-in-java/)** This method returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
17. **[listIterator(int index):](https://www.geeksforgeeks.org/linkedlist-listiterator-method-in-java/)** This method returns a list-iterator of the elements in this list (in proper sequence), starting at the specified position in the list.
18. **[offer(E e):](https://www.geeksforgeeks.org/java-util-linkedlist-offer-offerfirst-offerlast-java/)** This method Adds the specified element as the tail (last element) of this list.
19. **[offerFirst(E e):](https://www.geeksforgeeks.org/java-util-linkedlist-offer-offerfirst-offerlast-java/)** This method Inserts the specified element at the front of this list.
20. **[offerLast(E e):](https://www.geeksforgeeks.org/java-util-linkedlist-offer-offerfirst-offerlast-java/)** This method Inserts the specified element at the end of this list.
21. **peek():** This method retrieves, but does not remove, the head (first element) of this list.
22. **peekFirst():** This method retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
23. **peekLast():** This method retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
24. **[poll():](https://www.geeksforgeeks.org/java-util-linkedlist-poll-pollfirst-polllast- examples-java/)** This method retrieves and removes the head (first element) of this list.
25. **[pollFirst():](https://www.geeksforgeeks.org/java-util-linkedlist-poll-pollfirst-polllast- examples-java/)** This method retrieves and removes the first element of this list, or returns null if this list is empty.
26. **[pollLast():](https://www.geeksforgeeks.org/java-util-linkedlist-poll-pollfirst-polllast- examples-java/)** This method retrieves and removes the last element of this list, or returns null if this list is empty.
27. **[pop():](https://www.geeksforgeeks.org/linkedlist-pop-method-in-java/)** This method Pops an element from the stack represented by this list.
28. **[push(E e):](https://www.geeksforgeeks.org/linkedlist-push-method-in-java/)** This method Pushes an element onto the stack represented by this list.
29. **[remove():](https://www.geeksforgeeks.org/linkedlist-remove-method-in-java/)** This method retrieves and removes the head (first element) of this list.
30. **[remove(int index):](https://www.geeksforgeeks.org/linkedlist-remove-method-in-java/)** This method removes the element at the specified position in this list.
31. **remove(Object o):** This method removes the first occurrence of the specified element from this list, if it is present.
32. **[removeFirst():](https://www.geeksforgeeks.org/linkedlist-removefirst-method-in-java/)** This method removes and returns the first element from this list.
33. **[removeFirstOccurrence(Object o):](https://www.geeksforgeeks.org/linkedlist-removefirstoccurrence-method-in- java/)** This method removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
34. **removeLast():** This method removes and returns the last element from this list.
35. **removeLastOccurrence(Object o):** This method removes the last occurrence of the specified element in this list (when traversing the list from head to tail).
36. **[set(int index, E element)](https://www.geeksforgeeks.org/linkedlist-set-method-in-java/):** This method replaces the element at the specified position in this list with the specified element.
37. **[size():](https://www.geeksforgeeks.org/linkedlist-size-method-in-java/)** This method returns the number of elements in this list.
38. **[spliterator():](https://www.geeksforgeeks.org/linkedlist-spliterator-method-in-java/)** This method Creates a late-binding and fail-fast Spliterator over the elements in this list.
39. **toArray():** This method returns an array containing all of the elements in this list in proper sequence (from first to last element).
40. **toArray(T[] a):** This method returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of the returned array is that of the specified array.

## LinkedList源码分析

见Java源码分析-List-LinkedList

# Questions

## Arraylist与LinkedList默认空间是多少；

## Arraylist与LinkedList区别与各自的优势List 和 Map 区别；

## Arraylist与LinkedList在频繁的get操作上的耗时主要在什么地方（结合源码分析）；

通过大量的数据测试可发现其执行时间会相差千倍左右。

## 不同的场景下的选择是什么？即什么时候用数组，什么时候用ArrayList，什么时候用LinkedList？