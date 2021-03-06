# 集合体系

https://www.geeksforgeeks.org/collections-in-java-2/?ref=lbp（contributed by  **Dharmesh Singh**）

集合将一组单个对象表示为一个单个单元，Java提供的Collection框架定义了一系列类和接口，这些类和接口可以将一组对象表示成一个单元。

Collection(java.util.Colection)和Map(java.util.Map)接口是java集合的两个最顶层接口

## 引入Collection框架的原因

在Collection Framework出现之前（JDK1.2之前），java对象分组的标准方法是通过Array，Vectors或者Vectors，亦或者HashTables,这个集合没有公共的接口。访问这些数据结构是非常令编程人员苦恼的，因为每种都有各自不同访问该成员的方法（和语法）

```java
// Java program to show why collection framework was needed 
import java.io.*; 
import java.util.*; 
  
class Test 
{ 
    public static void main (String[] args) 
    { 
        // Creating instances of array, vector and hashtable 
        int arr[] = new int[] {1, 2, 3, 4}; 
        Vector<Integer> v = new Vector(); 
        Hashtable<Integer, String> h = new Hashtable(); 
        v.addElement(1); 
        v.addElement(2); 
        h.put(1,"geeks"); 
        h.put(2,"4geeks"); 
  
        // Array instance creation requires [], while Vector 
        // and hastable require () 
        // Vector element insertion requires addElement(), but 
        // hashtable element insertion requires put() 
  
        // Accessing first element of array, vector and hashtable 
        System.out.println(arr[0]); 
        System.out.println(v.elementAt(0)); 
        System.out.println(h.get(1)); 
  
        // Array elements are accessed using [], vector elements 
        // using elementAt() and hashtable elements using get() 
    } 
} 
```

Output:

```
1
1
geeks
```

正如我们看到的一样，这些集合（Array, Vector ,hashTable）都没有实现一个标准的数据访问接口。这对于编程人员编写能够在多种数据集合下都能工作良好的算法是非常困难的。另外一个缺点是‘Vector’的大多数方法都是final的，这就意味着我们不能通过extends继承Vector去实现类似Vector的集合。

Java开发者决定去实现一个公共的接口去解决上面的问题，因此Collection Framework框架出现在了JDK1.2中。

同时对遗留的Vector和Hashtables做出了修改，以让其能够兼容Collection Framework。

## Collection Framework的优势

1. 不变的API：API具有一组基本的接口，像Collection，Set，List或Map.实现这些接口的所有类（ArrayList，LinkedList，Vector等）都有一些通用的方法集。
2. 减少编程的工作量：编程人员不必再考虑Collection的设计，并可以在程序中专注于Collection的最佳使用。
3. 提高程序的速度和质量：通过提供的有用的数据结构和高性能的算法实现，从而提高程序的性能表现。

## Collection Framework层级结构图

```xml
             Collection                Map
         /     /    \      \            |
        /      /      \     \           |
     Set    List    Queue  Dequeue   SortedMap
     /
    /
 SortedSet 
            Core Interfaces in Collections

Note that this diagram only shows core interfaces.  
```

Collection和Map下的具体实现类

<img src="../../../%25E8%25AE%25A1%25E7%25AE%2597%25E6%259C%25BA/Java/Java%25E5%259F%25BA%25E7%25A1%2580%25E7%25AF%2587.assets/7113407-b03ac1fd1d1e007e.webp" alt="img" style="zoom:80%;" align="left"/>

## Enumeration，Iterator， ListIteratoor

Iterator存在于Collection框架中，用来遍历其中的元素。有三种类型的iterator

### Enumertion

用于获取旧集合（Vector，Hashtable）元素的接口。枚举是JDK 1.0中出现的第一个迭代器，其余的在JDK 1.2中被包含进来，同时伴随着更多的功能。枚举还用于指定SequenceInputStream的输入流。我们可在任何vector对象上调用其elements()方法得到Enumerationd对象。

```java
// Here "v" is an Vector class object. e is of
// type Enumeration interface and refers to "v"
Enumeration e = v.elements();
```

Enumeration接口有两个方法

```java
public interface Enumeration<E> {
    /**
     * Tests if this enumeration contains more elements.
     *
     * @return  <code>true</code> if and only if this enumeration object
     *           contains at least one more element to provide;
     *          <code>false</code> otherwise.
     */
    boolean hasMoreElements();

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return     the next element of this enumeration.
     * @exception  NoSuchElementException  if no more elements exist.
     */
    E nextElement();
}
```

下面的代码片段展示如何使用Enumeration接口遍历vector的元素。

```java
// Java program to demonstrate Enumeration 
import java.util.Enumeration; 
import java.util.Vector; 
  
public class Test 
{ 
    public static void main(String[] args) 
    { 
        // Create a vector and print its contents 
        Vector v = new Vector(); 
        for (int i = 0; i < 10; i++) 
            v.addElement(i); 
        System.out.println(v); 
  
        // At beginning e(cursor) will point to 
        // index just before the first element in v 
        Enumeration e = v.elements(); 
  
        // Checking the next element availability 
        while (e.hasMoreElements()) 
        { 
            // moving cursor to next element 
            int i = (Integer)e.nextElement(); 
  
            System.out.print(i + " "); 
        } 
    } 
} 
```

输出：

```
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
0 1 2 3 4 5 6 7 8 9 
```

Enumeration的限制：

* Enumeration只用于老的类（Vector， HashTable），因此其不具有通用性
* 使用Enumeration没有remove操作
* 只能进行正向迭代

### Iterator

iterator是一个通用的迭代器，使用iterator可以操作任意的Collection对象。通过iterator我们可以进行读取和移除操作。在Enumeration的基础上增加了额外的移除元素的能力。

每当我们想要访问实现Collection框架接口的集合（Set,List, Queue, Deque, 以及实现了Map接口的集合）中的元素，我们必须使用Iterator。Iterator是collection框架中唯一可获得的cursor.

通过调用集合框架中的iterator()接口获取Iterator对象：

```java
// Here "c" is any Collection object. itr is of
// type Iterator interface and refers to "c"
Iterator itr = c.iterator();
```

Iterator的接口定义如下:

```java
public interface Iterator<E> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @implSpec
     * The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this iterator
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception.  Actions are
     * performed in the order of iteration, if that order is specified.
     * Exceptions thrown by the action are relayed to the caller.
     *
     * @implSpec
     * <p>The default implementation behaves as if:
     * <pre>{@code
     *     while (hasNext())
     *         action.accept(next());
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @since 1.8
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

remove() 方法会抛出两个种类的异常：

- *UnsupportedOperationException :* 该iterator不支持remove操作，通常是实现类中没有实现此方法。
- *IllegalStateException :* If the next method has not yet been called, or the remove method has already been called after the last call to the next method

```java
// Java program to demonstrate Iterator 
import java.util.ArrayList; 
import java.util.Iterator; 
  
public class Test 
{ 
    public static void main(String[] args) 
    { 
        ArrayList al = new ArrayList(); 
  
        for (int i = 0; i < 10; i++) 
            al.add(i); 
  
        System.out.println(al); 
  
        // at beginning itr(cursor) will point to 
        // index just before the first element in al 
        Iterator itr = al.iterator(); 
  
        // checking the next element availabilty 
        while (itr.hasNext()) 
        { 
            //  moving cursor to next element 
            int i = (Integer)itr.next(); 
  
            // getting even elements one by one 
            System.out.print(i + " "); 
  
            // Removing odd elements 
            if (i % 2 != 0) 
               itr.remove();  
        } 
        System.out.println();  
        System.out.println(al); 
    } 
} 
```

输出：

```
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
0 1 2 3 4 5 6 7 8 9 
[0, 2, 4, 6, 8]
```

Iterator的限制：

* 只能正向迭代
* 不支持通过iterator替换和增加元素

### ListIterator

只适用于实现了List接口的集合，如arraylist， linkedlist等。其可以进行双向迭代。

ListIterator必须使用在当我们想要枚举list元素的时候。其相比iterator有着更多的功能。

通过调用List接口中的listIterator() 方法获取ListIterator对象。

```java
// Here "l" is any List object, ltr is of type
// ListIterator interface and refers to "l"
ListIterator ltr = l.listIterator();
```

ListIterator继承自Iterator接口。所有Iterator接口中的三个方法同样适用于Listiterator；除此之外其还有额外的6个方法。

```java
// Forward direction

// Returns true if the iteration has more elements
public boolean hasNext();

// same as next() method of Iterator
public Object next();

// Returns the next element index 
// or list size if the list iterator
// is at the end of the list
public int nextIndex();

// Backward direction

// Returns true if the iteration has more elements
// while traversing backward
public boolean hasPrevious();

// Returns the previous element in the iteration
// and can throws NoSuchElementException
// if no more element present
public Object previous();

// Returns the previous element index 
//  or -1 if the list iterator is at the 
// beginning of the list
public int previousIndex();

// Other Methods
 
// same as remove() method of Iterator
public void remove();

// Replaces the last element returned by 
// next() or previous() with the specified element 
public void set(Object obj);

// Inserts the specified element into the list at
// position before the element that would be returned 
// by next(),
public void add(Object obj);
```

可以清晰的看到从Iterator继承过来的三个方法和Iterator做着同样的事情。hasPrevious() 方法，pervious操作和hasNext()以及next()有着类似的功能。

set()方法可以抛出四个异常：

- *UnsupportedOperationException* – if the set operation is not supported by this list iterator
- *ClassCastException :* If the class of the specified element prevents it from being added to this list
- *IllegalArgumentException :* If some aspect of the specified element prevents it from being added to this list
- *IllegalStateException :* If neither next nor previous have been called, or remove or add have been called after the last call to next or previous

add()方法会抛出三个异常

- *UnsupportedOperationException :* If the add method is not supported by this list iterator
- *ClassCastException :* If the class of the specified element prevents it from being added to this list
- *IllegalArgumentException :* If some aspect of this element prevents it from being added to this list

```java
// Java program to demonstrate ListIterator 
import java.util.ArrayList; 
import java.util.ListIterator; 
  
public class Test 
{ 
    public static void main(String[] args) 
    { 
        ArrayList al = new ArrayList(); 
        for (int i = 0; i < 10; i++) 
            al.add(i); 
  
        System.out.println(al); 
  
        // at beginning ltr(cursor) will point to 
        // index just before the first element in al 
        ListIterator ltr = al.listIterator(); 
  
        // checking the next element availabilty 
        while (ltr.hasNext()) 
        { 
            //  moving cursor to next element 
            int i = (Integer)ltr.next(); 
  
            // getting even elements one by one 
            System.out.print(i + " "); 
  
            // Changing even numbers to odd and 
            // adding modified number again in  
            // iterator 
            if (i%2==0) 
            { 
                i++;  // Change to odd 
                ltr.set(i);  // set method to change value 
                ltr.add(i);  // to add 
            } 
        } 
        System.out.println(); 
        System.out.println(al); 
    } 
}
```

输出：

```
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
0 1 2 3 4 5 6 7 8 9 
[1, 1, 1, 3, 3, 3, 5, 5, 5, 7, 7, 7, 9, 9, 9]
```

ListIterator的限制：

这是一个强大的迭代器，但是只适用于List接口的实现类，所以其并不是通用的迭代器。

### 重要的相同点

1：在最开始的时候，iterator的引用都将指向集合中第一个元素索引之前

2：我们不能创建Enumeration， Iterator，ListIterator对象，因为它们都是接口，不能够实例化。我们使用elements(), iterator(), listIterator()得到对应的对象。这些方法有着类似的内部类分别继承对应的接口。通过下面的代码片段可以得到验证：

```java
// Java program to demonstrate iterators references 
import java.util.Enumeration; 
import java.util.Iterator; 
import java.util.ListIterator; 
import java.util.Vector; 
  
public class Test 
{ 
    public static void main(String[] args) 
    { 
        Vector v = new Vector(); 
  
        // Create three iterators 
        Enumeration e = v.elements(); 
        Iterator  itr = v.iterator(); 
        ListIterator ltr = v.listIterator(); 
  
        // Print class names of iterators 
        System.out.println(e.getClass().getName()); 
        System.out.println(itr.getClass().getName()); 
        System.out.println(ltr.getClass().getName()); 
    } 
} 
```

输出：

```
java.util.Vector$1
java.util.Vector$Itr
java.util.Vector$ListItr
```

引用类名称中的$符号证明使用了内部类的概念并创建了这些类对象。

# 集合视图

## 概念

Java集合中，我们可以通过视图（View）获得其他实现的了Collection和Map接口的对象。例如，我们在使用Map类的keySet方法获得一个Set集合对象。初看起来，这个方法创建了一个新的set集合，然后将所有的键都填进去，然后返回这个集合。但是，情况并非如此，keySet返回一个实现Set接口的类对象，这个类的方法对原来的Map对象进行操作。这个集合就称为视图（view）。（详细的原理见Java源码分析-Map-HashMap-keyset）

## 轻量级集合包装器

（1）. ArraylList.asList方法

在Arrays类中有一个静态方法--asList方法，这个方法作用是：将普通的Java数组包装成一个List集合。例如：

```java
String []strings = new String[10];
strings[0] = "geeks";
strings[1] = "for";
strings[2] = "geeks";
List<String> stringList = Arrays.asList(strings);
```

返回的对象不是一个ArrayList对象。它就是一个视图对象，这个对象带有底层数组的get和set方法。
那这个视图对象与普通的List或者ArrayList对象有什么区别吗？
在这里，视图对象不能操作所有改变数组大小的方法(比如说，add方法和remove方法)，在调用这些方法的时候，程序会抛出一个UnsupportedOperationException异常；但是普通的List对象能够正常的调用改变数组大小的方法。

（2）. Collections.nCopies方法

与Arrays.asList方法类似的另一个方法那就是在Collection中的nCopies方法。例如：

```java
List<String> stringList = Collections.nCopies(100, "geeks");
```

上面的代码将创建一个包含100个"geeks"字符串的List集合对象。这样的操作优势在于存储代价很小，因为这个对象不能修改大小。这就是视图技术的一种巧妙应用。

## 子范围

在Java中，我们还可以给 很多的集合建立**子范围视图**。例如，假设有一个集合对象list，我们想要从中取出第10个~第19个元素。可以使用subList方法来获得一个List集合对象的子范围视图。例如：

```java
List<String> list = new ArrayList<>();
for (int i = 0; i < 20; i++) {
    list.add("" + i);
}
System.out.println(list);
//获取第10个~第19个
List<String> list2 = list.subList(9, 20);

System.out.println(list2);
System.out.println(list);
//清空自子范围视图之后，原来的List集合对象相应位置的数据也会被自动清空的
list2.clear();
System.out.println(list2);
System.out.println(list);
```

## 只读视图

Collections还有几个方法，用于产生集合的不可修改视图。这些视图对现有的集合增加了一个运行时的检查。如果发现对集合进行修改的话(这里不仅仅是改变数组的大小，并且包括set之类的方法)，就会抛出一个异常，同时这个集合将保持未修改的状态。
可以使用如下8种方法来获得不可修改的视图：

1. Collections.unmodifiableCollection

2. Collections.unmodifiableList

3. Collections.unmodifiableSet
4. Collections.unmodifiableSortedSet

5. Collections.unmodifiableNavigableSet

6. Collections.unmodifiableMap

7. Collections.unmodifiableSortedMap

8. Collections.unmodifiableNavigableMap

每个方法都定义于一个接口。例如，Collections.unmodifiableList方法定义于List接口，与ArrayList、LinkedList或者任何实现了List接口的其他类一起协同工作。
例如，假设想要查看某个集合的内容，但是又能避免这个集合会被修改的情况，就可以进行下列的操作：

```java
LinkedList<String> list = new LinkedList<>();
list.add("geeks");
list.add("for geeks");
List<String> list2 = Collections.unmodifiableList(list);
//是不能被修改的
//list2.set(0, "geek");
```

Collections.unmodifiableList方法将返回一个List集合的对象(视图对象)，我们可以从这个视图对象中取得内容，但是不能修改，因为在个视图对象中，所有修改类型的方法已经被重新定义为一个抛出UnsupportOperationException的异常，而不是将方法的调用传递给底层集合对象(这里底层集合对象指的就是当前List集合对象的实际类型对象，这种调用方式是由于Java的多态性导致的)。
但是我们这里需要注意的是，不可更改的视图对象并不是指集合本身不可修改，我们仍然可以通过集合原来的引用来(犹如上面例子中的list)对集合进行修改。同时，如果原来的引用修改了集合，那么视图对象的内容也是跟着变化的。

## 同步视图

如果多个线程访问集合，就确保集合不会被意外的破坏。例如，如果一个线程视图将元素添加到Hash表中，同时另一个线程正在对Hash表进行再散列，这种操作的结果是灾难性的。
但是我们使用视图机制来确保常规集合的线程安全，而不是实现线程安全的集合类。例如，Collections类的静态方法synchronizedMap方法可以将任何一个映射表转换成为具有同步访问方法的Map：

```java
Map<String, String> map = Collections.synchrizedMap(new HashMap<String, String>());
```

现在，就可以自由的使用多线程来访问map对象了。像get和put这类方法都是同步操作的，即在另一个线程中调用另一个方法之前，刚才的方法调用必须彻底执行完毕。

## 受查视图

受查视图时用来对泛型类型发生问题时，提供调试的支持

```java
List<String> list = new ArrayList<>();
List list2 = list;
//程序运行到这里是不会报错的，但是如果后面访问这里元素，
//并且试图强制转换为String类型的变量才会抛出一个ClassCastException的异常
list2.add(10);
//这里会抛出ClassCastException异常
//String string = (String) list2.get(0);
```

例如，上面的例子，先创建了一个List<String>类型的对象，再将它的泛型类型擦除，变成了List类型，由于泛型类型被擦除，原来的泛型类型就会被Object代替，所以我们在list2中添加一个整数(Integer)类型是不会有问题的，同时程序运行到add方法那里也不会报错的。也就是说，程序的编译时和运行时的错误，我们都成功的越过去了。但是在将添加进去的那个元素强制转成为String类型，就会抛出ClassCastException的异常。如果我们使用受查视图的话，例如：

```java
List<String> list3 = Collections.checkedList(list, String.class);
List list4 = list3;
//程序运行到这里就会抛出一个ClassCastException的异常
list4.add(10);
```

虽然在编译时，程序是没有报错的，但是程序一旦运行到add方法那里直接会抛出一个ClassCastException的异常。也就是说，通过受查视图，可以逃避编译时的检查，但是躲不过运行时的检查！