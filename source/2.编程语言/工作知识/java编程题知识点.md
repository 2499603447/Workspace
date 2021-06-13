# 基本数据结构

## 线性表（数组、动态数组）

 

## 队列

(思考与广度优先遍历算法的紧密联系）

队列是一种先进先出的数据结构，即第一个进入队列元素将会第一个出来。

### 问题

典型入门级问题：

用两个栈实现一个队列

```java
/**
* 用两个栈实现一个队列
* 总结为：换->放->换
* 缺点：需要进行两次的数据交换
* 特点：push过程稍复杂（需要操纵两个栈），pop过程简单（只需要操纵一个栈即可）
*/

public class QueueByStack {
    private Stack stack1;
    private Stack stack2;
    
    /**
    * 入队列，放入队列尾部
    * @param value 待存入队列中的数据
    */
    public void push(T value) {
        // 先将stack1的数据放入stack2
        moveTo(stack1, stack2);
        // 将数据放入stack1
        stack1.push(value);
        // 在将stack2的数据放入stack1
        moveTo(stack2, stack1);
    }
    
    /*
    * 将srcStack中的所有数据转移至dstStack中
    * @param srcStack 原栈
    * @param dstStack 目的栈
    */
    private void moveTo(Stack srcStack, Stack dstStack) {
        while(!srcStack.isEmpty()) {
            dstStack.push(srcStack.pop());
        }
    }
    
    /**
    * 出队列，队列队首元素，删除数据
    * 始终从stack1中删除
    * @param T 删除的数据
    */
    public T pop() {
        while(!stack1.isEmpty()) {
            return stack1.pop();
        }
        
        return null;
    }
    
    /**
    * 返回队列的队首元素，不删除数据
    * @param T 删除的数据
    */
    public T peek() {
        T value;
        if(!stack1.isEmpty()) {
            value = stack1.peek();
        }
        
        return value;
    }
}

/**
* 用两个栈实现一个队列
* 缺点：
* 特点：pop过程稍复杂（需要操纵两个栈），push过程简单（只需要操纵一个栈即可）
*/

public class QueueByStack {
    private Stack stack1;
    private Stack stack2;
    
    /**
    * 入队列，放入队列尾部
    * 始终将数据放入stack1中
    */
    public void push(T value) {
        stack1.push(value);
    }
    
    /**
    * 出队列，队列队首元素，删除数据
    */
    public T pop() {
        if (stack2.isEmpty()) {
            moveTo(stack1, stack2);
        }
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        
        return null;
    }
    
    /*
    * 将srcStack中的所有数据转移至dstStack中
    * @param srcStack 原栈
    * @param dstStack 目的栈
    */
    private void moveTo(Stack srcStack, Stack dstStack) {
        while (!srcStack.isEmpty()){
            dstStack.push(srcStack.pop());
        }
    }
    
    /**
    * 返回队列的队首元素，不删除数据
    */
    public T peek() {
        if (stack2.isEmpty()) {
            moveTo(stack1, stack2);
        }
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        
        return null;
    }
}
```



## 栈

（思考与递归的紧密联系体现）

栈是一种后进先出的数据结构，即最后一个进来的元素将会第一个出来。

### 问题

用两个队列实现一个栈

```java
/*
* 用两个队列实现一个栈
* 通过交换对象的引用，从而减少的移动数据的次数
* 整个过程总结为：“移”-“放”-“移”
* 缺点：会移动两次的数据
*/
public class StackByQueue {
    private Queue queue1;
    private Queue queue2;
    
    /*
    * 将数据放入栈中
    * @param value 待放入的数据
    */
    public void push(T value) {
        moveTo(queue1, queue2);
        queue1.push(value);
        moveTo(queue2, queue1);
    }
    
    /*
    * 将srcQueue中的所有数据转移至dstQueue中
    * @param srcQueue 原队列
    * @param dstQueue 目的队列
    */
    private moveTo(Queue srcQueue, Queue dstQueue) {
        while (!srcQueue.isEmpty()) {
            dstQueue.push(srQueue.pop());
        }
    }
    
    /*
    * 弹出最后一个放入的数据
    * @param 返回的栈顶数据
    */
    public T pop() {
        while (!queue1.isEmpty()) {
            return queue1.pop();
        }
        
        return null;
    }
    
    /*
    *
    */
    public T peek() {
        
    }
}

/*
* 用两个队列实现一个栈
* 通过交换对象的引用，从而减少的移动数据的次数
* 整个过程总结为：先“放”后“移”
*/
public class StackByQueue {
    private Queue queue1;
    private Queue queue2;
    
    /*
    * 将数据放入栈中
    * @param value 待放入的数据
    */
    public void push(T value) {
        queue1.push(value);
        moveTo(queue2, queue1);
        
        // 交换queue1和queue2的引用
        Queue temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
    
    /*
    * 将srcQueue中的所有数据转移至dstQueue中
    * @param srcQueue 原队列
    * @param dstQueue 目的队列
    */
    private moveTo(Queue srcQueue, Queue dstQueue) {
        while (!srcQueue.isEmpty()) {
            dstQueue.push(srQueue.pop());
        }
    }
    
    /*
    * 弹出最后一个放入的数据
    * @param 返回的栈顶数据
    */
    public T pop() {
        while (!queue2.isEmpty()) {
            return queue2.pop();
        }
        
        return null;
    }
    
    /*
    *
    */
    public T peek() {
        
    }
}
```



## 链表

## 哈希表

哈希表和二叉排序树查找考察的重点树对应的数据结构，而不是算法。哈希表的主要优点是利用它能够在O（1）的时间内查找到某一元素，是效率最高的查找方式；但是其缺点是需要额外的存储空间来实现哈希表。

# 高级数据结构

## 二叉树、平衡二叉树（工作）

树的前、中、后序遍历算法的递归好循环6中实现。按照根节点被访问的顺序，树的遍历可分为前序遍历（根-左-右）、中序遍历（左-根-右）、后序遍历（左-右-根）

### 问题

根据给定的遍历结果，对原有的树进行还原。

如：输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入放入前序宾利和中序遍历的结果都不含重复的数字。输出二叉树的根节点。

二叉树的节点为：

```java
/*
* 二叉树节点java描述
*/
class BinaryTreeNode {
	int value;
	BinaryTreeNode left;
	BinaryTreeNode right;
}
```

```c++
/*
* 二叉树节点c/c++描述
*/
struct BinaryTreeNode {
    int value;
    BinaryTreeNode * left;
    BinaryTreeNode * right;
}
```

示例1：

```
输入：
前序遍历{1,2,4,7,3,5,6,8}
后序遍历{4,7,2,1,5,3,8,6}

输出：
root
```

## 二叉排序树

与二叉排序树查找算法对应的数据结构是二叉搜索树。

二叉搜索树的后序遍历：

二叉搜索树与双向链表

## 堆、二叉堆（专业）

# 常用算法思想

## 排序算法

面试官经常会要求应聘者比较插入排序、冒泡排序、归并排序、快速排序等不同排序算法的优劣；因此应聘者需要对这些排序算法的特性烂熟于胸，能顾从额外空间消耗、平均时间复杂度和最差时间复杂度等方面去比较他们它们的优缺点。

## 迭代、递归的思想

自下而上的递归思想

递归的问题：

性能开销：递归中有很多计算都是重复的，因此会对性能早成一定的影响。

调用栈溢出：每一次的函数调用度需要在内存中分配空间，然而每个进程的栈的容量是有限的；当递归调用的层级太多时，就会超出栈的容量，从而导致栈溢出，在java里面就是表现为我们常见的Error：StackOverFlowError。

以斐波那契数列为例，当输入的参数较小，如10，那么循环和递归都能返回正确的结果，而当我们输入参数很大时，如四位数甚至更多的情况下，使用递归的方法就会出错。

## 分治的思想（二分查找、归并排序等）

<font color=red>对于二分查找算法，不管只用循环还是递归，都要能够信手捏来写出完整的代码</font>

## 搜索算法（DFS, BFS）