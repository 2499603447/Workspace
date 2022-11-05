# Map

## HashMap

早在JDK1.2，HashMap就作为Collection的一部分存在。它提供了对Map接口的基本实现。其通过key-value键值对的形式存储。为了访问数据，必须先知道其key值。HashMap之所以称为HashMap是因为它使用了一项称为哈希的技术。哈希散列是将一个大字符串转换为代表相同字符串的小字符串的技术。更小的值更加有利于索引和更快的查找。HashSet的内部也是用了HashMap。其内部使用了一个链表来存储键值对。

### HashMap中的一些重要特性

* HashMap存在于java.util包中
* HashMap继承自抽象类AbstractMap（其提供了Map的不完全实现）
* 同时其还实现了Cloneable接口和Serializable接口。上面定义的K和V分别表示Key和Value
* HashMap不允许存在重复的key值，但是允许存在重读的value。这就意味着一个Key不能有多个value与之对应，但是多个key可以对应同一个value值。
* HashMap允许null作为key，但是由于其key值不允许重复，所以只能存在一个null; 可以有多个value为null
* HashMap不保证元素的顺序；尤其是，其不保证其顺序是一直不变的。它与HashTable大致相似，但不是线程安全的。

### HashMap内部结构

HashMap内部包含一个Node节点的数组，且node节点是有下面四个字段组成的一个Node类：

```java
final int hash;
final K key;
V value;
Node<K,V> next;
```

节点数组的定义如下:

```java
Node<K, V>[] table;
```

可以看到，节点包含对其自身对象的引用，所以其是一个链表。

HashMap结构：

<img src="../../../%25E8%25AE%25A1%25E7%25AE%2597%25E6%259C%25BA/Java/java%25E5%25B7%25A5%25E4%25BD%259C%25E7%25BA%25A7%25E6%258A%2580%25E8%2583%25BD.assets/array.png" alt="array" align="left" />

Node结构：

<img src="../../../%25E8%25AE%25A1%25E7%25AE%2597%25E6%259C%25BA/Java/java%25E5%25B7%25A5%25E4%25BD%259C%25E7%25BA%25A7%25E6%258A%2580%25E8%2583%25BD.assets/node_hash_map.png" alt="node_hash_map" align="left"/>

### HashMap性能

HashMap性能取决于两个参数：

1. Initial Capacity
2. Load Factor

如前所属，Capacity是存储区的容量，而Initial Capacity是创建HashMap实例时的容量。Load Factor是进行rehashing时的一个措施。Rehashing增加存储容量的一个过程。在HashMap中，扩容时容量会被乘以2。负载因子也是衡量在重新散列之前允许填充HashMap的哪一部分的度量(即从新散列到哪一部分)。当HashMap中的元素增加时，当前容量和负载因子的乘积会增加，即完成了一次rehash。如果初始容量设置的很大，那么rehash将永远不会被执行。但是如果容量一直保持在很高的位置，那么会增加迭代的复杂度。因此为了挺高性能，在初始值设定必须慎重考虑。设置初始值时应该考虑预估的可能大小。最通常的，优先选用0.75作为负载因子的大小，这是在时间和空间上的一个很好的解决方案。负载因子的值介于0和1之间。

### 同步的HashMap

前面所讲HashMap是非同步的，如多个线程可以同时对一个HashMap进行访问。如果多个线程同时访问HshMap并且至少一个线程对其进行了结构上的操作，则必须在外部进行同步操作。可通过对封装的map某些对象来完成同步。如果不存在这样的对象，则可以将其用Collections.synchronizedMap()包裹以使HashMap得到同步，同时还需要避免一些意外的非同步访问，如下所示：

```java
Map m = Collections.synchronizedMap(new HashMap(...));
```

这样，map就是同步的了。

如果在创建迭代器之后进行了任何的修改操作（除了通过迭代器的remove方法进行的任何形式的修改），便会很快碰到错误，且抛出ConcurrentModificationException异常。

### HashMap构造器

HashMap提供了四个构造器，且每个构造方法的访问权限控制符都是public

1. HashMap():默认的构造函数，初始化HashMap的大小为16，以及负载因子为0.75

   ```java
   /**
    * Constructs an empty <tt>HashMap</tt> with the default initial capacity
    * (16) and the default load factor (0.75).
    */
   public HashMap() {
       this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
   }
   ```

   

2. HashMap(int initialCapcity):指定初始化创建的map容量，使用默认的负载因子0.75

   ```java
    /**
        * Constructs an empty <tt>HashMap</tt> with the specified initial
        * capacity and the default load factor (0.75).
        *
        * @param  initialCapacity the initial capacity.
        * @throws IllegalArgumentException if the initial capacity is negative.
        */
   public HashMap(int initialCapacity) {
       this(initialCapacity, DEFAULT_LOAD_FACTOR);
   }
   ```

3. HashMap(int intialCapcity, float loadFactor):使用指定的初始大小和负载因子创建一个空的HashMap

   ```java
   /**
        * Constructs an empty <tt>HashMap</tt> with the specified initial
        * capacity and load factor.
        *
        * @param  initialCapacity the initial capacity
        * @param  loadFactor      the load factor
        * @throws IllegalArgumentException if the initial capacity is negative
        *         or the load factor is nonpositive
        */
   public HashMap(int initialCapacity, float loadFactor) {
       if (initialCapacity < 0)
           throw new IllegalArgumentException("Illegal initial capacity: " +
                                              initialCapacity);
       if (initialCapacity > MAXIMUM_CAPACITY)
           initialCapacity = MAXIMUM_CAPACITY;
       if (loadFactor <= 0 || Float.isNaN(loadFactor))
           throw new IllegalArgumentException("Illegal load factor: " +
                                              loadFactor);
       this.loadFactor = loadFactor;
       this.threshold = tableSizeFor(initialCapacity);
   }
   ```

   

4. HashMap(Map map): 使用同样的map创建一个HashMap实例

   ```java
   /**
        * Constructs a new <tt>HashMap</tt> with the same mappings as the
        * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
        * default load factor (0.75) and an initial capacity sufficient to
        * hold the mappings in the specified <tt>Map</tt>.
        *
        * @param   m the map whose mappings are to be placed in this map
        * @throws  NullPointerException if the specified map is null
        */
   public HashMap(Map<? extends K, ? extends V> m) {
       this.loadFactor = DEFAULT_LOAD_FACTOR;
       putMapEntries(m, false);
   }
   ```

示例：

```java
// Java program to illustrate 
// Java.util.HashMap 
  
import java.util.HashMap; 
import java.util.Map; 
  
public class GFG { 
    public static void main(String[] args) 
    { 
  
        HashMap<String, Integer> map 
            = new HashMap<>(); 
  
        print(map); 
        map.put("vishal", 10); 
        map.put("sachin", 30); 
        map.put("vaibhav", 20); 
  
        System.out.println("Size of map is:- "
                           + map.size()); 
  
        print(map); 
        if (map.containsKey("vishal")) { 
            Integer a = map.get("vishal"); 
            System.out.println("value for key"
                               + " \"vishal\" is:- "
                               + a); 
        } 
  
        map.clear(); 
        print(map); 
    } 
  
    public static void print(Map<String, Integer> map) 
    { 
        if (map.isEmpty()) { 
            System.out.println("map is empty"); 
        } 
  
        else { 
            System.out.println(map); 
        } 
    } 
} 
```

输出：

```
map is empty
Size of map is:- 3
{vaibhav=20, vishal=10, sachin=30}
value for key "vishal" is:- 10
map is empty
```

### HashMap复杂度

对于一些基本的操作其提供常量的时间复杂度，例如get和put方法；只要hash函数能够得到正确的编写并且将其正确散列到bucket中。对HashMap的迭代取决于HashMap的容量以及键值对的数量。基本上，其和capacity+size成正比。Capacity是HashMap中bucket的数量，所以一开始把capacity设的过高并不是一个好主意。

### HashMap方法

1. **[void clear():](https://www.geeksforgeeks.org/hashmap-clear-method-in-java/)** Used to remove all mappings from a map.
2. **[boolean containsKey(Object key):](https://www.geeksforgeeks.org/hashmap-containskey-method-in-java/)** Used to return True if for a specified key, mapping is present in the map.
3. **[boolean containsValue(Object value):](https://www.geeksforgeeks.org/hashmap-containsvalue-method-in-java/)** Used to return true if one or more key is mapped to a specified value.
4. **[Object clone():](https://www.geeksforgeeks.org/hashmap-clone-method-in-java/)** It is used to return a shallow copy of the mentioned hash map.
5. **[boolean isEmpty():](https://www.geeksforgeeks.org/hashmap-isempty-method-in-java/)** Used to check whether the map is empty or not. Returns true if the map is empty.
6. **[Set entrySet():](https://www.geeksforgeeks.org/hashmap-entryset-method-in-java/)** It is used to return a set view of the hash map.
7. **[Object get(Object key):](https://www.geeksforgeeks.org/hashmap-get-method-in-java/)** It is used to retrieve or fetch the value mapped by a particular key.
8. **[Set keySet():](https://www.geeksforgeeks.org/hashmap-keyset-method-in-java/)** It is used to return a set view of the keys.
9. **[int size():](https://www.geeksforgeeks.org/hashmap-size-method-in-java/)** It is used to return the size of a map.
10. **[Object put(Object key, Object value):](https://www.geeksforgeeks.org/hashmap-put-method-in-java/)** It is used to insert a particular mapping of key-value pair into a map.
11. **[putAll(Map M):](https://www.geeksforgeeks.org/hashmap-putall-method-in-java/)** It is used to copy all of the elements from one map into another.
12. **[Object remove(Object key):](https://www.geeksforgeeks.org/hashmap-remove-method-in-java/)** It is used to remove the values for any particular key in the Map.
13. **[Collection values():](https://www.geeksforgeeks.org/hashmap-values-method-in-java/)** It is used to return a Collection view of the values in the HashMap.
14. **[compute(K key, BiFunction remappingFunction)](https://www.geeksforgeeks.org/hashmap-compute-method-in-java-with-examples/):** This method Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).
15. **[computeIfAbsent(K key, Function mappingFunction)](https://www.geeksforgeeks.org/hashmap-computeifabsent-method-in-java-with-examples/):** This method If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.
16. **computeIfPresent(K key, BiFunction remappingFunction):** This method If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
17. **forEach(BiConsumer action):** This method Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.
18. **getOrDefault(Object key, V defaultValue):** This method returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
19. **merge(K key, V value, BiFunction remappingFunction):** This method If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.
20. **putIfAbsent(K key, V value):** This method If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
21. **replace(K key, V value):** This method replaces the entry for the specified key only if it is currently mapped to some value.
22. **replace(K key, V oldValue, V newValue):** This method replaces the entry for the specified key only if currently mapped to the specified value.
23. **replaceAll(BiFunction function):** This method replaces each entry’s value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.

### HashMap源码分析

见Java源码分析-Map-HashMap

## WeakHashMap

Hash table based implementation of the Map interface, with weak keys. An entry in a WeakHashMap will automatically be removed when its key is no longer in ordinary use. More precisely, the presence of a mapping for a given key will not prevent the key from being discarded by the garbage collector, that is, made finalizable, finalized, and then reclaimed. When a key has been discarded its entry is effectively removed from the map, so this class behaves somewhat differently from other Map implementations.

### WeakHashMap的一些重要特性

* value和key都可以为null
* 不是线程安全的
* <font color=red>This class is intended primarily for use with key objects whose equals methods test for object identity using the == operator.</font>

### WeakHashMap构造函数

1. **WeakHashMap():** 使用默认的 initial capacity-(16) and load factor (0.75)创建一个空的WeakHashMap
2. **WeakHashMap(int initialCapacity):** 使用给定的initial capacity和默认的load factor (0.75)创建一个空的WeakHashMap.
3. **WeakHashMap(int initialCapacity, float loadFactor):** 使用给定的 initial capacity和给定的 load factor创建一个空的WeakHashMap.
4. **WeakHashMap(Map m):** This constructor is used to create a new WeakHashMap with the same mappings as the specified map.

### WeakHashMap中的方法

1. [void clear():](https://www.geeksforgeeks.org/weakhashmap-clear-method-in-java/) 

   The method removes all of the mappings from this map. The map will be empty after this call returns.

   ```
   Syntax: public void clear().
   Returns: NA.
   Exception: NA.
   ```

2. [boolean containsValue(Object value):](https://www.geeksforgeeks.org/weakhashmap-containsvalue-method-in-java/) 

   this method returns true if this map maps one or more keys to the specified value.

   ```
   Syntax: public boolean containsValue(Object value).
   Returns: true if this map maps one or more keys to the specified value.
   Exception: NA
   ```

3. [boolean containsKey(Object key):](https://www.geeksforgeeks.org/weakhashmap-containskey-method-in-java/) 

   This method returns true if this map contains a mapping for the specified key.

   ```
   Syntax: public boolean containsKey(Object key).
   Returns: true if there is a mapping for key; false otherwise.
   Exception: NA
   ```

4. [put(K key, V value):](https://www.geeksforgeeks.org/weakhashmap-put-method-in-java/) 

   Associates the specified value with the specified key in this map. If the map previously contained a mapping for this key, the old value is replaced.

   ```
   Syntax: public put(K key, V value).
   Returns: the previous value associated with key, 
   or null if there was no mapping for key. 
   (A null return can also indicate that the 
   map previously associated null with key.)
   Exception: NA
   ```

5. [boolean isEmpty():](https://www.geeksforgeeks.org/weakhashmap-isempty-method-in-java-with-examples/) 

   Returns true if this map contains no key-value mappings. This result is a snapshot, and may not reflect unprocessed entries that will be removed before next attempted access because they are no longer referenced.

   ```
   Syntax: public boolean isEmpty()
   Returns: true if this map contains no key-value mappings.
   Exceptions: NA
   ```

   ```java
   // Java code illustrating clear(), containsValue() 
   // containsKey() and isEmpty() method 
     
   import java.util.Map; 
   import java.util.WeakHashMap; 
     
   class WeakHashMapdemo 
   { 
       public static void main(String[] arg) 
       { 
           Map<Number, String> weak = new WeakHashMap<Number, String>(); 
           weak.put(1, "geeks"); 
           weak.put(2, "for"); 
           weak.put(3, "geeks"); 
             
           // Checking weak map 
           System.out.println("our weak map: " + weak); 
             
           // Checking if "for" exist 
           if(weak.containsValue("for")) 
               System.out.println("for exist"); 
             
           // Checking if 1 exist as a key in map 
           if(weak.containsKey(1)) 
               System.out.println("1 exist"); 
             
           // Removing all data 
           weak.clear(); 
             
           // Checking whether map is empty or not 
           if(weak.isEmpty()) 
               System.out.println("empty map: " + weak); 
       } 
   } 
   ```

   **Output:**

   ```
   our weak map: {3=geeks, 2=for, 1=geeks}
   for exist
   1 exist
   empty map: {}
   ```

6. [Set entrySet():](https://www.geeksforgeeks.org/weakhashmap-entryset-method-in-java/) 

   Returns a Set view of the mappings contained in this map. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation, or through the setValue operation on a map entry returned by the iterator) the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.

   ```
   Syntax: public Set entrySet()
   Returns: a set view of the mappings contained in this map.
   Exception: NA
   ```

7. [Set keySet():](https://www.geeksforgeeks.org/weakhashmap-keyset-method-in-java/) 

   Returns a Set view of the keys contained in this map. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation), the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll, and clear operations. It does not support the add or addAll operations.

   ```
   Syntax: public Set keySet().
   Returns: a set view of the keys contained in this map
   Exception: NA
   ```

8. [Collection values():](https://www.geeksforgeeks.org/weakhashmap-values-method-in-java/) 

   Returns a Collection view of the values contained in this map. The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. If the map is modified while an iteration over the collection is in progress (except through the iterator's own remove operation), the results of the iteration are undefined. The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.

   ```
   Syntax: public Collection values().
   Returns: a collection view of the values contained in this map
   Exception: NA
   ```

   ```java
   // Java code illustrating entrySet(), keySet() and Values() 
     
   import java.util.Collection; 
   import java.util.Map; 
   import java.util.Set; 
   import java.util.WeakHashMap; 
     
   class WeakHashMapdemo 
   { 
       public static void main(String[] arg) 
       { 
           Map<Number, String> weak = new WeakHashMap<Number, String>(); 
           weak.put(1, "geeks"); 
           weak.put(2, "for"); 
           weak.put(3, "geeks"); 
             
           Set set1 = weak.entrySet(); 
             
           // Checking set 
           System.out.println(set1); 
             
           // Creating set for key 
           Set keySet = weak.keySet(); 
             
           // Checking keySet 
           System.out.println("key set: " + keySet ); 
             
           Collection value = weak.values(); 
             
           // Checking values of map 
           System.out.println("values: " + value); 
       } 
   } 
   ```

   **Output:**

   ```
   [3=geeks, 2=for, 1=geeks]
   key set: [3, 2, 1]
   values: [geeks, for, geeks]
   ```

9. [void putAll(Map m):](https://www.geeksforgeeks.org/weakhashmap-putall-method-in-java/) 

   Copies all of the mappings from the specified map to this map. These mappings will replace any mappings that this map had for any of the keys currently in the specified map.

   

   ```
   Syntax: public void putAll(Map m)
   Returns: NA
   Exception: 
   NullPointerException - if the specified map is null.
   ```

10. [V get(Object key):](https://www.geeksforgeeks.org/weakhashmap-get-method-in-java/) 

    Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.

    ```
    Syntax: public V get(Object key)
    Returns: the value to which the specified 
    key is mapped, or null if this map contains no mapping for the key
    Exception: NA
    ```

11. [V remove(Object key):](https://www.geeksforgeeks.org/weakhashmap-remove-method-in-java/) 

    Removes the mapping for a key from this weak hash map if it is present. More formally, if this map contains a mapping from key k to value v such that (key==null ? k==null : key.equals(k)), that mapping is removed.

    ```
    Syntax: public V remove(Object key)
    Returns: the previous value associated with key, or 
    null if there was no mapping for key
    Exception: NA.
    ```

12. [int size():](https://www.geeksforgeeks.org/weakhashmap-size-method-in-java/) 

    Returns the number of key-value mappings in this map. This result is a snapshot, and may not reflect unprocessed entries that will be removed before next attempted access because they are no longer referenced.

    ```
    Syntax: public int size()
    Returns: the number of key-value mappings in this map.
    Exception: NA
    ```

    ```java
    // Java code remove(), putAll() 
    // get() and size() method 
      
    import java.util.Collection; 
    import java.util.Map; 
    import java.util.Set; 
    import java.util.WeakHashMap; 
      
    class WeakHashMapdemo 
    { 
        public static void main(String[] arg) 
        { 
            Map<Number, String> weak = new WeakHashMap<Number, String>(); 
            weak.put(1, "geeks"); 
            weak.put(2, "for"); 
            weak.put(3, "geeks"); 
              
            Map<Number, String> weak1 = new WeakHashMap<Number, String>(); 
            weak1.putAll(weak); 
              
            // getting value of key 2 
            System.out.println(weak1.get(2)); 
              
            // size of map 
            System.out.println("Size of map is: " + weak1.size()); 
              
            // removing 2nd element 
            weak1.remove(2); 
              
            // size after removing key and value pair 
            System.out.println("Size after removing: " + weak1.size()); 
        } 
    } 
    ```

    **Output:**

    ```
    for
    Size of map is: 3
    Size after removing: 2
    ```

### WeakHashMap源码剖析

见Java源码分析-Map-WeakHashMap

## 