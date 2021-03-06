# TreeSet

TreeSet是SortedSet接口的一个重要实现，其使用Tree结构来存储数据。不管是否显示指定比较器，元素的顺序都可以使用其自然序进行排列。也可以在创建的时候指定比较器，这取决于使用何种构造函数。TreeSet实现NavigableSet接口，同时继承AbstractSet抽象类

TreeSet的一些重要的特性:

1. TreeSet实现SortedSet接口，所以不允许重复的value
2. TreeSet中对象以升序的方式进行存储
3. 元素并不是保持插入时的顺序，而是按照key值进行排序
4. TreeSet中不允许插入Heterogeneous objects（异构对象）
5. 由于其快速的访问以及检索速度，TreeSet用来存储大量有序信息是一个不错的选择
6. TreeSet是基于自平衡搜索二叉树实现的，类似于红黑树。因此像add，remove，search操作或非O（Logn）的时间。像打印n个元素之类的操作耗费O（n）的时间

## TreeSet构造函数

1. **TreeSet t = new TreeSet();**
   This will create empty TreeSet object in which elements will get stored in default natural sorting order.
2. **TreeSet t = new TreeSet(Comparator comp);**
   This constructor is used when external specification of sorting order of elements is needed.
3. **TreeSet t = new TreeSet(Collection col);**
   This constructor is used when any conversion is needed from any Collection object to TreeSet object.
4. **TreeSet t = new TreeSet(SortedSet s)**;
   This constructor is used to convert SortedSet object to TreeSet Object.

## TreeSet的同步

TreeSet的实现不是线程同步的，即当多个线程同时访问treeset，并且至少有一个线程需要对set进行修改，那么必须在外部对set的访问进行同步操作。需要使用对set进行封装的且支持同步的set对象。如果没有这样的对象，那么需要使用Collection.synchronizedSortedSet方法将set包裹起来。最好在set创建的时候就进行此操作，以避免一些异常的不同步的操作;

```java
TreeSet ts = new TreeSet();
Set syncSet = Collections.synchronziedSet(ts); 
```

下面的代码片段展示了TreeSet的基本操作：

```java
// Java program to demonstrate insertions in TreeSet 
import java.util.*; 
  
class TreeSetDemo { 
    public static void main(String[] args) 
    { 
        TreeSet<String> ts1 = new TreeSet<String>(); 
  
        // Elements are added using add() method 
        ts1.add("A"); 
        ts1.add("B"); 
        ts1.add("C"); 
  
        // Duplicates will not get insert 
        ts1.add("C"); 
  
        // Elements get stored in default natural 
        // Sorting Order(Ascending) 
        System.out.println(ts1); 
    } 
} 
```

输出：

```
[A, B, C]
```

在对TreeSet进行插入和删除元素操作时，逆序牢记两点：

* 第一：插入null到TreeSet会抛出NullPointerException，因为当插入null时，会和TreeSet中已经存在的其他元素进行compare，但是null并不能和任何对象进行比较。
* 第二：如果我们使用默认的自然排序规则，则强制对象必须是同质的并且具有可比性，否则将会抛出**RuntimeException:***ClassCastException

```java
// Java code to illustrate StringBuffer 
// class does not implements 
// Comparable interface. 
import java.util.*; 
class TreeSetDemo { 
  
    public static void main(String[] args) 
    { 
        TreeSet<StringBuffer> ts = new TreeSet<StringBuffer>(); 
  
        // Elements are added using add() method 
        ts.add(new StringBuffer("A")); 
        ts.add(new StringBuffer("Z")); 
        ts.add(new StringBuffer("L")); 
        ts.add(new StringBuffer("B")); 
        ts.add(new StringBuffer("O")); 
  
        // We will get RunTimeException :ClassCastException 
        // As StringBuffer does not implements Comparable interface 
        System.out.println(ts); 
    } 
} 
```

![image-20200406172413948](Set.assets/image-20200406172413948.png)

## 注意

1. 当且仅当相应的类实现Comparable接口时，该对象才被认为是可比较的。
2. String类以及所有的包装类都实现了Comparable接口但是StringBuffer并没有实现Comparable接口。因此在上面的代码中我们遇到了ClassCastException
3. 对于一个空的tree-set，当试图将null作为第一值插入时，从JDK 7之后会得到一个NPE。从JDK 1.7开始，TreeSet完全不接受null作为值。然而自JDK6之前，null是允许作为第一个值的。但是后面再试图插入更多的值时，我们便会遇到NPE

因此，这被视为一个bug并在JDK7中被修复了。

## TreeSet中的方法

TreeSet实现了SortedSet接口，所以其Collection，Set和SortedSet中的所有方法的能力。下面是TreeSet中的方法。

1. 1. [void add(Object o):](https://www.geeksforgeeks.org/treeset-add-method-in-java/) This method will add specified element according to some sorting order in TreeSet. Duplicate entires will not get added.
   2. [boolean addAll(Collection c):](https://www.geeksforgeeks.org/treeset-addall-method-in-java/) This method will add all elements of specified Collection to the set. Elements in Collection should be homogeneous otherwise ClassCastException will be thrown. Duplicate Entries of Collection will not be added to TreeSet.
   3. [void clear():](https://www.geeksforgeeks.org/treeset-clear-method-in-java/) This method will remove all the elements.
   4. [boolean contains(Object o):](https://www.geeksforgeeks.org/treeset-contains-method-in-java/) This method will return true if given element is present in TreeSet else it will return false.
   5. [Object first():](https://www.geeksforgeeks.org/treeset-first-method-in-java/) This method will return first element in TreeSet if TreeSet is not null else it will throw NoSuchElementException.
   6. [Object last():](https://www.geeksforgeeks.org/treeset-last-method-in-java/) This method will return last element in TreeSet if TreeSet is not null else it will throw NoSuchElementException.
   7. [SortedSet headSet(Object toElement):](https://www.geeksforgeeks.org/treeset-headset-method-in-java/) This method will return elements of TreeSet which are less than the specified element.
   8. [SortedSet tailSet(Object fromElement):](https://www.geeksforgeeks.org/treeset-tailset-method-in-java/) This method will return elements of TreeSet which are greater than or equal to the specified element.
   9. [SortedSet subSet(Object fromElement, Object toElement):](https://www.geeksforgeeks.org/treeset-subset-method-in-java/) This method will return elements ranging from fromElement to toElement. fromElement is inclusive and toElement is exclusive.
   10. [boolean isEmpty():](https://www.geeksforgeeks.org/treeset-isempty-method-in-java/) This method is used to return true if this set contains no elements or is empty and false for the opposite case.
   11. [Object clone():](https://www.geeksforgeeks.org/treeset-clone-method-in-java/) The method is used to return a shallow copy of the set, which is just a simple copied set.
   12. [int size():](https://www.geeksforgeeks.org/treeset-size-method-in-java/) This method is used to return the size of the set or the number of elements present in the set.
   13. [boolean remove(Object o):](https://www.geeksforgeeks.org/treeset-remove-method-in-java/) This method is used to return a specific element from the set.
   14. [Iterator iterator():](https://www.geeksforgeeks.org/treeset-iterator-method-in-java/) Returns an iterator for iterating over the elements of the set.
   15. [Comparator comparator()](https://www.geeksforgeeks.org/treeset-comparator-method-in-java/): This method will return Comparator used to sort elements in TreeSet or it will return null if default natural sorting order is used.
   16. **[ceiling(E e):](https://www.geeksforgeeks.org/treeset-ceiling-method-in-java-with-examples/)** This method returns the least element in this set greater than or equal to the given element, or null if there is no such element.
   17. **descendingIterator():** This method returns an iterator over the elements in this set in descending order.
   18. **descendingSet():** This method returns a reverse order view of the elements contained in this set.
   19. **floor(E e):** This method returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
   20. **higher(E e):** This method returns the least element in this set strictly greater than the given element, or null if there is no such element.
   21. **lower(E e):** This method returns the greatest element in this set strictly less than the given element, or null if there is no such element.
   22. **pollFirst():** This method retrieves and removes the first (lowest) element, or returns null if this set is empty.
   23. **pollLast():** This method retrieves and removes the last (highest) element, or returns null if this set is empty.
   24. **spliterator():** This method creates a late-binding and fail-fast Spliterator over the elements in this set.

2. ### TreeSet源码分析

3. 见Java源码分析-Set-TreeSet