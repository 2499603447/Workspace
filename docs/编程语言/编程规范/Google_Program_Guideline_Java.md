[TOC]

------

# 1. 引言

本文档是Google在Java<sup>TM</sup>编程语言中源代码编码标准的完整定义。当且仅当遵循以下规则的Java源文件才被描述为遵循Google 编码风格的。

和其他编码风格指南一样，所涉及的问题不仅包含格式的美观问题，还涉及其他类型的约定或编码标准。然而，本文档主要关注我们普遍遵循的必须遵守的规则（**hard-and-fast rules**），并避免给出一些没有明确强制执行的建议（不管是人类或者工具给出的）

## 1.1 术语注释

在本文档中，除非明确说明：

1. class是指“普通”类、枚举类、接口或注释类型（@interface）。
2. member（of a class）是指嵌套类、字段、方法或构造函数；也就是说，类的所有顶层内容，初始值设定项和注释除外。
3. comment通常是指实现注释。我们不使用“文档注释”一词，而是使用通用术语“Javadoc”。

其他的“术语注释”将会贯穿整个文档出现。

## 1.2 指南注释

本文当中的示例代码并不是标准的。也就是说，Google Style的代码并不是表示这段代码的唯一风格。示例中所提供的几种可选风格并不是规则中所强制的。

# 2. 源文件

## 2.1 文件名

源文件名由顶层类名（大小写敏感，只有一个）加上.java后缀组成。

如：ClassName.java

## 2.2 文件编码：UTF-8

源文件编码方式为UTF-8

## 2.3 特殊字符

### 2.3.1 空格字符

除了行结束符序列之外，ASCII水平空格字符（0x20）是源文件中出现的唯一空白字符，这意味着：

1. 字符串和字符文本中的其他空白字符都将被转义
2. tab制表符不用于缩进

### 2.3.2 特殊转义字符

对于具有特殊转义序列（\b、\t、\n、\f、\r、\”、\'和\）的任何字符，都使用该序列，而不是相应的八进制（例如.\012）或Unicode（例如.\u000a）转义。

### 2.3.3 非ASCII 字符

对于其余的非ASCII字符，使用实际的Unicode字符（例如∞）或等效的Unicode转义（例如.\u21e）。尽管在字符串文本和注释之外使用Unicode转义是非常不可取的，但最终的选择还是取决于哪个使得代码更易于阅读和理解。

>提示：在Unicode转义的情况下，有时甚至在使用实际的Unicode字符时，解释性的注释可能非常有用。

例子：

| Example                                                  | Discussion                                                   |
| :------------------------------------------------------- | :----------------------------------------------------------- |
| `String unitAbbrev = "μs";`                              | Best: perfectly clear even without a comment.                |
| `String unitAbbrev = "\u03bcs"; // "μs"`                 | Allowed, but there's no reason to do this.                   |
| `String unitAbbrev = "\u03bcs"; // Greek letter mu, "s"` | Allowed, but awkward and prone to mistakes.                  |
| `String unitAbbrev = "\u03bcs";`                         | Poor: the reader has no idea what this is.                   |
| `return '\ufeff' + content; // byte order mark`          | Good: use escapes for non-printable characters, and comment if necessary. |

> **提示**：不要仅仅因为担心某些程序可能无法正确处理非ASCII字符而降低代码的可读性。如果发生这种情况，这些程序就会**中断**，必须**修复**。

# 3. 源文件结构

一个源文件中**按顺序**包含以下几个部分

1. License/Copyright信息，如果有的话
2. 包声明语句
3. 导包语句
4. 一个顶层类

用**一个空行**分隔每一个存在的部分

## 3.1 License/Copyright信息，如果存在的话

如果许可证和版权信息属于一个文件，则在此添加。

## 3.2 包结构

package语句没有换行，列限制（4.4节，行限制：100）不适用于包语句。

## 3.3 import 语句

### 3.3.1 不使用通配符导入

不使用通配符导入，使用静态或非静态导包。

### 3.3.2 不使用换行

导入语句不用换行。列限制（第4.4节，列限制：100）不适用于导入语句。

### 3.3.3 顺序和间距

导包的顺序如下：

1. 所有的静态导包在一个块中
2. 所有的非静态导包在一个块中

如果同时存在静态和非静态导入，用一个空行将静态导包和非静态导包分开。在其他的导包语句不要存在任何的空行。

在每一个导包区块中，导入的名称以ASCII排序顺序显示。（**注意**：这与按ASCII排序顺序的import语句不同，因为“.”在“；”之前排序。）

### 3.3.4 对类不使用静态导包

嵌套类不使用静态导入，使用普通导包方式导入。

## 3.4 类声明

### 3.4.1 一个顶层类声明

每一个顶层类都存在于一个单独的源文件中。

### 3.4.2 类的内容顺序

类的成员变量和初始化构造器的顺序选择在最终的易学性（可读性等）方面有很大影响。然而并没有唯一的准则教我们如何去做；不同类中的内容可能有着不同的排列顺序。

重要的是每个类使用一些合理的排序，如果被问到那么维护者可以说出为什么即可。例如，新方法不只是习惯性地添加到类的末尾，因为这样会产生“按日期添加的时间顺序”排序，这并不是合理的排序。

#### 3.4.2.1 重载：永远不要分开

当一个类有多个构造函数，或者有多个同名的方法时，这些方法会按顺序出现，其间没有其他代码（甚至没有私有成员）。

# 4. 格式

> 术语注释：类的块状结构是指类的主体部分，包括方法或者构造函数。注意，4.8.3.1节中的数组初始化，任何数组初始化可以选择性地将其视为块状结构。

## 4.1 花括号

### 4.1.1 合适的地方使用花括号

括号通常和if，else，for，do 以及while语句，哪怕这些语句的主体部分语句只有一条。

### 4.1.2 非空块：K&R 风格

非空块和块状结构的花括号遵循Kernighan and Ritchie风格（"[Egyptian brackets](http://www.codinghorror.com/blog/2012/07/new-programming-jargon.html)"）：

* 在左大括号之前没有换行符。
* 左大括号后换行。
* 右大括号前换行。
* 仅当右大括号终止语句或终止方法、构造函数或命名类的主体时，才在右大括号后换行。例如，如果大括号后面跟着else或逗号，则大括号后面没有换行符。

示例：

```java
return () -> {
  while (condition()) {
    method();
  }
};

return new MyClass() {
  @Override public void method() {
    if (condition()) {
      try {
        something();
      } catch (ProblemException e) {
        recover();
      }
    } else if (otherCondition()) {
      somethingElse();
    } else {
      lastThing();
    }
  }
};
```

第4.8.1节“枚举类”给出了枚举类的一些异常。

### 4.1.3 空代码块：可能很简洁

空块或者块状结构可以采用K&R风格（4.1.2中所述）。或者，除非它是多块语句（直接包含多个块的语句：if/else或try/catch/finally）的一部分，否则它可以在打开后立即关闭，在（{}）中间不带字符或换行符。

示例：

```java
 // This is acceptable
  void doNothing() {}

  // This is equally acceptable
  void doNothingElse() {
  }
  
  // This is not acceptable: No concise empty blocks in a multi-block statement
  try {
    doSomething();
  } catch (Exception e) {}
```

## 4.2 块缩进：两个空格

每次打开新的块或类似块的构造时，缩进都会增加两个空格。当块结束时，缩进返回到上一个缩进级别。缩进级别适用于整个块中的代码和注释。（参见第4.1.2节“非空块：K&R样式”中的示例。）

## 4.3 一条语句一行

每条语句结束都会有一个换行符。

## 4.4 列限制：100

Java代码的列限制为100个字符。“字符”表示任何Unicode代码点。除以下所述外，任何超出此限制的行都必须换行，如第4.5节“换行”中所述。

> 每个Unicode代码点都算作一个字符，不管其显示宽度大于或小于一个字符。例如，如果使用全角字符，则可以选择早于此规则严格要求的位置进行换行。

例外情况：

1. 不可能遵守列限制的行（例如，Javadoc中的长URL或长JSNI方法引用）。
2. package和import语句（见第3.2节package语句和第3.3节import语句）。
3. 注释中可以剪切并粘贴到shell的命令行。

## 4.5 换行

> 术语说明：当可能占用一行的代码被分成多行时，此活动被称为换行。

没有一个全面的、确定的公式确切地说明如何在每种情况下进行换行。通常有几种有效的方法来对同一段代码进行换行。

> 注意：换行的根本的原因是为了避免超出列限制，即使实际上符合列限制的代码也可以由作者自行决定是否换行。

> 提示：提取方法或局部变量可以解决超出列限制的问题，而无需换行。

### 4.5.1 什么时候进行换行

换行往往倾向于更高德语义层次，即：

1. 在非赋值运算符处换行时，换行符在符号之前。

   （注意，这与谷歌风格的其他语言，如C++和JavaScript不一样。）

   * 这同样适用于以下“类运算符”的符号
     * 点分隔符（.）
     * 方法引用（::）的两个冒号
     * 类型绑定中的与号（<T extends Foo&Bar>）
     * catch块中的管道（catch（FooException | BarException e））。

2. 当在赋值运算符处换行时，换行符通常在符号之后，但这两种方式都是可以接受的。

   * 这也适用于增强for（“foreach”）语句中的“类赋值运算符”冒号。

3. 方法或构造函数名称紧跟其后面的左括号（。

4. 逗号（，）仍紧跟其前面的标记。

5. 在lambda中，除非当lambda的主体由一个没有空格的表达式组成时，可能会在箭头之后立即出现一个换行符外，否则在箭头旁边永远不会出现换行符。示例：

   ```java
   MyLambda<String, Long, Object> lambda =
       (String label, Long value, Object obj) -> {
           ...
       };
   
   Predicate<String> predicate = str ->
       longExpressionInvolving(str);
   ```

   > 注：换行的最初目的是为了使得代码能够清楚简洁，而不一定是行数最小的。

### 4.5.2 连续行的缩进至少4个空格

换行时，第一行（每个续行）之后的每一行至少从原始开始行缩进4个空格。

如果有多个连续行，缩进可能会根据需要变化超过+4。通常，只有当两个连续行以语义上等平级的元素开头时，它们才使用相同的缩进级别。

关于水平对齐的第4.6.3节说明了不鼓励使用可变数量的空格将某些标记与先前的行对齐的做法。

## 4.6 空格

### 4.6.1 垂直空格

始终显示一个空行：

1. 在类的连续成员或初始值设定项之间：字段、构造函数、方法、嵌套类、静态初始值设定项和实例初始值设定项。
   * 例外：两个连续字段之间的空行（其之间没有其他代码）是可选的。这些空行根据需要用于对字段进行合理的分组。
   * 例外：枚举常量之间的空行，在4.8.1中介绍
2. 根据本文当其他章节的要求（如第3节，源文件结构和第3.3节，导入语句）。

一个空行也可以出现在任何提高可读性的地方，例如在语句之间将代码组织成逻辑子部分。不鼓励在类的第一个成员或初始值设定项之前或最后一个成员或初始值设定项之后增加空行。

允许多个连续的空行，但从不要求（或鼓励）。

### 4.6.2 水平空格

除了语言或其他样式规则所要求的地方之外，除了文字、注释和Javadoc之外，单个ASCII空间也**只**出现在以下位置。

1. 将任何保留字（如if、for或catch）与该行上紧跟其后的左括号“（”用空格进行分隔

2. 将任何保留字（如else或catch）与该行前的右大括号（}）用空格分隔开

3. 在任何左大括号（{）之前，有两个例外：

   * @SomeAnnotation（{a，b}）（不使用空格）

   * String [] [] x={{“foo”}}；（{{之间不需要空格,以下第8项有所提及）

4. 在任何二元或三元运算符的两边。这也适用于以下“类似于运算符”的符号：

   * 连接类型绑定中的与号：<T extends Foo&Bar>

   * 处理多个异常的catch块的管道：catch（FooException | BarException e）

   * 增强for（“foreach”）语句中的冒号（：）

   * lambda表达式中的箭头：（String str）->str.length（）

   但不是

   * 方法引用的两个冒号（::），其编写方式类似于Object::toString

   * 点分隔符（.），写得像object.toString（）

5. 在转换的，：；或右括号（)）之后

6. 在开始行尾注释的双斜杠（//）两侧。这里允许有多个空格，但不是必需的。

7. 在声明的类型和变量之间：List<String> list

8. 仅在数组初始值设定项的两个大括号内可选

   * new int[] {5，6}和new int[] {5，6}都是有效的

9. 在类型批注和[]或...

这条规则并不是说要求或禁止在一行的开始或结束处增加额外的空格；它只适用于一行的内部。

### 4.6.3 水平对齐：从来不需要

> 术语说明：水平对齐是在代码中添加可变数量的额外空格，目的是使某些标记直接出现在前几行的某些其他标记的下面。

这种做法是允许的，但从来不是谷歌风格所要求的。没有必要增加若干空格来使变量的赋值等号与上一行的对应位置的等号对齐。

下面是一个没有对齐的示例，然后使用对齐：

```java
private int x; // this is fine
private Color color; // this too

private int   x;      // permitted, but future edits
private Color color;  // may leave it unaligned
```

> **Tips:**
>
> 对齐有助于提高可读性，但会给将来的维护带来问题。考虑一下未来的变化，它只需要涉及一行。此更改可能会使以前令人满意的格式混乱，这是**允许**的。更常见的情况是，它会提示编码程序（可能是您）调整附近行上的空白，可能会触发一系列连续的重新格式化。这一行的更改现在有一个“爆炸半径”，最坏的情况下可能会导致无意义的复杂工作，但最好的情况下其仍然会破坏版本历史信息，减慢审阅者的速度，并加剧合并冲突。

## 4.7 分组括号：推荐

只有当作者和审阅者都认为没有括号，代码就不可能被曲解，也不可能使代码更易于阅读时，才省略可选的分组括号。假设每个读者脑海中都存储了整个Java运算符优先表是不合理的。

## 4.8 特殊结构

### 4.8.1 枚举类

在枚举常量后面的每个逗号之后，换行符是可选的。也允许添加空行（通常只有一行）。这是一种可能性：

```java
private enum Answer {
  YES {
    @Override public String toString() {
      return "yes";
    }
  },

  NO,
  MAYBE
}
```

如果枚举类没有方法，也没有关于其常量的说明，则可以选择将其格式化为数组初始值设定项（请参阅关于数组初始值设定项的第4.8.3.1节）。

```java
private enum Suit { CLUBS, HEARTS, SPADES, DIAMONDS }
```

因为枚举类也是类，所以所有其他格式化类的规则都适用。

### 4.8.2 变量声明

#### 4.8.2.1 每个变量单独声明

每次只声明一个变量（字段或局部）：不使用int a、b；等声明。

例外：for循环的头中可以接受多个变量声明。

#### 4.8.2.2 需要时声明

局部变量不应该习惯性地将其声明在其所在的代码块或块状结构的开始位置。相反，局部变量被声明在它们第一次被使用的点附近（在合理范围内），以最小化它们的作用域。局部变量声明通常具有初始值设定项，或者在声明后立即初始化。

### 4.8.3 数组

#### 4.8.3.1 数组初始化：可以是块状结构的

任何数组初始值设定项都可以选择性地格式化，就像它是一个“类似块的构造”一样。例如，以下都是有效的（不是详尽的列表）：

```java
new int[] {           new int[] {
  0, 1, 2, 3            0,
}                       1,
                        2,
new int[] {             3,
  0, 1,               }
  2, 3
}                     new int[]
                          {0, 1, 2, 3}
```

#### 4.8.3.2 不要使用C语言风格的数组声明方式

方括号是类型的一部分，而不是变量：用String[] args，而不是String args[]。

### 4.8.4 switch语句

> 术语说明：在switch块的大括号内是一个或多个语句组。每个语句组由一个或多个switch标签（case FOO:或default:）组成，后跟一个或多个语句（或者，对于最后一个语句组，为零个或多个语句）。

#### 4.8.4.1 缩进

与任何其他块一样，switch块的内容缩进+2。

在一个switch标签之后，有一个换行符，缩进级别增加+2，就像一个被打开的块一样。警接着的switch标签返回到上一个缩进级别，就好像块已关闭一样。

#### 4.8.4.2 Fall-through：注释

在switch块中，每个语句组要么突然终止（出现break、continue、return或抛出异常），要么用注释标记，以指示执行将或可能继续进入下一个语句组。任何传达fall-through概念的注释都是需要的（通常是 // fall through）。switch块的最后一个语句组中不需要此特殊注释。例子：

```java
switch (input) {
  case 1:
  case 2:
    prepareOneOrTwo();
    // fall through
  case 3:
    handleOneTwoOrThree();
    break;
  default:
    handleLargeNumber(input);
}
```

> 注意: 在case 1:之后不需要注释，只需要在语句组的末尾。

#### 4.8.4.3 default

每个switch语句都包含一个defalut语句组，即使它不包含代码。

例外：枚举类型的switch语句可以省略default语句组，前提是它显示地包含覆该类型所有的可能值。这使得IDEs或其他静态分析工具能够在遗漏任何情况时发出警告。

### 4.8.5 注解

注解应用于类、方法或者构造函数，其紧跟在documentation block之后，每个注解单独占用一行。这些注解不是连续的换行，所以每个注解不需要单独缩进。

```java
@Override
@Nullable
public String getNameIfPresent() { ... }
```

例外：单个无参数注解可以与第一行一起出现，例如：

```java
@Override public int hashCode() { ... }
```

应用于字段的注解也是紧跟在在文档块之后，但在这种情况下，可能会在同一行列出多个注解（可能是参数化的）；例如：

```java
@Partial @Mock DataLoader loader;
```

对参数、局部变量或类型的注解格式化没有特定的规则。

### 4.8.6 注释

这部分解释了注释的概念。Javadoc在第7节中单独说明。

任何换行符之前都可以是任意空格，后面紧跟注释。这样的注释使该行非空。

#### 4.8.6.1 块注释样式（翻译不是很好，待完善）

块注释在与周围代码相同的级别缩进。他们可能在/* ... **/样式或// ...风格。对于多行/* * ... * /注释，后续行必须以*开头，与前一行上的*对齐。

```java
/*
 * This is          // And so           /* Or you can
 * okay.            // is this.          * even do this. */
 */
```

注释不包含在用星号或其他字符绘制的框中。

> 提示：在编写多行注释时，如果需要在必要时进行重新格式化（段落样式），注释可以使用使用/* ... */样式。大多数代码格式化程序不会对//...进行重新格式化。

### 4.8.7 修饰符

类和成员修饰符（如果存在）按Java语言规范建议的顺序显示：

```java
public protected private abstract default static final transient volatile synchronized native strictfp
```

### 4.8.8 数字字面值

long 数值整型数值使用大写L后缀，不要用小写（容易和数字1混淆）。例如, 3000000000L 而不是3000000000l。

# 5. 命名

## 5.1 适用于所有标识符的通用规则

标识符只使用ASCII字母和数字，在少数情况下使用下划线。因此，每个有效的标识符名称都由正则表达式\w+匹配。

在Google风格中，不使用特殊的前缀或后缀。例如，这些名称不是Google风格的：name_、mName、s_name和kName。

## 5.2 标识符类型规则

### 5.2.1 包名

包名称都是小写的，连续的单词简单地连在一起（没有下划线）。例如，com.example.deep space，而不是com.example.deepspace或com.example.deepspace。

### 5.2.2 类名

类名的命名遵循大驼峰原则。

类名通常是名词或名词短语。例如，Character或ImmutableList。接口名称也可以是名词或名词短语（例如，List），但有时可以是形容词或形容词短语（例如，Readable）。

对于注释类型的命名，没有特定的规则，甚至没有公认的约定。

测试类的命名以它们正在测试的类的名称开始，以Test结束。例如，HashTest或HashIntegrationTest。

### 5.2.3 方法名

方法名的命名遵循小驼峰原则。

方法名通常是动词或动词短语。例如，sendMessage或stop。

JUnit测试方法名称中可能会出现下划线，以分隔名称的逻辑组件，每个组件的命名也都遵循小驼峰原则。一个典型的模式是<methodUnderTest>_<state>，例如pop_emptyStack。没有一种正确的方法来命名测试方法。

### 5.2.4 常量名

常量名称使用CONSTANT_CASE：所有字母大写，每个单词之间用一个下划线隔开。但究竟什么是常数？

常量是static final 域，其内容是不可变的，其方法没有可检测到的副作用。这包括原语、字符串、不可变类型和不可变类型的不可变集合。如果实例的任何可观察状态可以更改，则它不是常数。仅仅不改变这个对象是不够的。示例：

```java
// Constants
static final int NUMBER = 5;
static final ImmutableList<String> NAMES = ImmutableList.of("Ed", "Ann");
static final ImmutableMap<String, Integer> AGES = ImmutableMap.of("Ed", 35, "Ann", 32);
static final Joiner COMMA_JOINER = Joiner.on(','); // because Joiner is immutable
static final SomeMutableType[] EMPTY_ARRAY = {};
enum SomeEnum { ENUM_CONSTANT }

// Not constants
static String nonFinal = "non-final";
final String nonStatic = "non-static";
static final Set<String> mutableCollection = new HashSet<String>();
static final ImmutableSet<SomeMutableType> mutableElements = ImmutableSet.of(mutable);
static final ImmutableMap<String, SomeMutableType> mutableValues =
    ImmutableMap.of("Ed", mutableInstance, "Ann", mutableInstance2);
static final Logger logger = Logger.getLogger(MyClass.getName());
static final String[] nonEmptyArray = {"these", "can", "change"};
```

这些名称通常是名词或者名词短语

### 5.2.5 非常量域命

非常量字段名（静态或其他）用小驼峰命名规则。

这些名字通常是名词或名词短语。例如，computedValues或index。

### 5.2.6 参数名

参数名用遵循小驼峰规则。

公共方法中应避免使用一个字符的参数名

### 5.2.7 局部变量名

局部变量名遵循小驼峰。

即使在final和immutable时，局部变量也不被视为常量，也不应被设置为常量的样式。

### 5.2.8 类型变量名

类型变量以下面两种样式之一进行命名：

* 一个大写字母，可以选择后跟一个数字（例如E、T、X、T2）

* 用于类的格式名称（见第5.2.2节，类名），后跟大写字母T（例如：RequestT、FooBarT）。

## 5.3 驼峰案例

有时，有不止一种合理的方法可以将英语短语转换成驼峰大小写，例如出现缩略词或“IPv6”或“iOS”等不寻常的结构时。为了提高可预测性，Google Style指定了以下（几乎）确定性方案。

从组成名字的单词形式开始：

* 将短语转换为纯ASCII并删除任何撇号。例如， "Müller's algorithm" 可能变成"Muellers algorithm"。

* 将此结果分为单词、空格和任何剩余的标点符号（通常是连字符）。

  推荐：如果任何一个单词已经有了一个常见的驼峰式的外观，将其分成几个部分（例如，“AdWords”变成“ad words”）。注意，像“iOS”这样的词本身并不是驼峰式的；它违背了任何惯例，因此本建议不适用。

* 现在将所有内容（包括首字母缩略词）小写，然后只将以下内容的第一个字符大写：
  * ... 每一个字，以产生上大驼峰，或
  * ... 除第一个字外，以产生小驼峰

* 最后，将所有单词连接到一个标识符中。

注意，原文的大小写几乎被完全忽略。示例：

|                         |                                      |                     |
| :---------------------- | :----------------------------------- | :------------------ |
| Prose form              | Correct                              | Incorrect           |
| "XML HTTP request"      | `XmlHttpRequest`                     | `XMLHTTPRequest`    |
| "new customer ID"       | `newCustomerId`                      | `newCustomerID`     |
| "inner stopwatch"       | `innerStopwatch`                     | `innerStopWatch`    |
| "supports IPv6 on iOS?" | `supportsIpv6OnIos`                  | `supportsIPv6OnIOS` |
| "YouTube importer"      | `YouTubeImporter` `YoutubeImporter`* |                     |

*可接受，但不推荐。

> 注意：有些单词在英语中有模糊的连字符：例如“nonempty”和“non-empty”都是正确的，所以方法名checkNonempty和checkNonEmpty也都是正确的。

# 6 编程练习

## 6.1 @override：总是使用

无论何时，使用@Override注释对方法进行标记总是合法的。这包括重写超类方法的类方法、实现接口方法的类方法和重新指定超接口方法的接口方法。

例外：当父方法被@Deprecated标注时，可以省略@Override。

## 6.2 捕获异常：不要忽视

除以下所述外，对捕获到的异常不做任何响应是不正确的方法。（典型的响应是将其记录下来，或者如果它被认为是“不可能的”，则将其作为AssertionError重新抛出。）

当在catch块中确实不应该采取任何操作时，需要在注释中对不做任何操作进行解释说明。

```java
try {
  int i = Integer.parseInt(response);
  return handleNumericResponse(i);
} catch (NumberFormatException ok) {
  // it's not numeric; that's fine, just continue
}
return handleTextResponse(response);
```

例外：在测试中，如果捕获的异常的名称是或者以expected的开头，则可以忽略它而不加注释。下面是一个非常常见的习惯用法，用于确保被测代码确实抛出了预期类型的异常，因此这里不需要注释。

```java
try {
  emptyStack.pop();
  fail();
} catch (NoSuchElementException expected) {
}
```

## 6.3 静态成员：使用class进行限定

当对静态类成员的引用必须使用限定符时，则使用该类的类名称，而不是用该类类型的引用或表达式限定的。

```java
Foo aFoo = ...;
Foo.aStaticMethod(); // good
aFoo.aStaticMethod(); // bad
somethingThatYieldsAFoo().aStaticMethod(); // very bad
```

## 6.4 Finalizer：不要使用

尽量不要重写Object.finalize方法

> Tip：不要这样做。如果必须这样做，首先要非常仔细地阅读和理解Effective Java 条目7 "Avoid Finalizers"。

# 7. Javadoc

## 7.1 格式

### 7.1.1 通用格式

基本的Javadoc块格式如下:

```java
/**
 * Multiple lines of Javadoc text are written here,
 * wrapped normally...
 */
public int method(String p1) { ... }
```

... 或者一行，如下：

```java
/** An especially short bit of Javadoc. */
```

基本形式总是可以接受的。当整个Javadoc块（包括注释标记）可以放在一行时，可以替换单行形式。请注意，这仅适用于没有@return等块标记的情况。

### 7.1.2 段落

一个空行，即仅包含对齐的前导星号（*）的行出现在段落之间和块标记组（如果存在）之前。除第一个段落外，每个段落的第一个单词前面都有<p>，后面没有空格。

### 7.1.3 块标签

使用的任何标准“块标记”都以@param、@return、@throws、@deprecated的顺序出现，并且这四种类型永远不会以空描述出现。当块标记后面的内容一行放不下时，后面的连续换行从@位置缩进四（或更多）个空格。

## 7.2 摘要片段

每个Javadoc块都以一个简短的概要开始。此概要非常重要：它是在引用的地方（如类和方法索引）的Tips中出现的唯一部分。

这是一个片段-一个名词短语或动词短语，而不是一个完整的句子。它不是以A {@code Foo} is a...开头的，或This method returns…，也不是像Save the record.这样形成一个完整的命令语句。然而，这个片段是大写的，并像一个完整的句子一样加上标点符号。

> ```java
> Tip:一个常见的错误是以/** @return the customer ID*/的形式编写简单的Javadoc。这不正确，应改为/** Returns the customer ID. */
> ```

## 7.3 什么时候使用Javadoc

至少，Javadoc存在于每个public修饰的类中，以及此类中每个public或者protected修饰的成员，下面提到的一些例外情况除外。

额外的Javadoc内容也可以出现在Javadoc中，如第7.3.4节“非必需的Javadoc”所述。

### 7.3.1 例外： 顾名思义的方法

Javadoc对于像getFoo这样的“简单、明显”的方法是可选的，在这种情况下，除了“Return the foo”之外没有什么真正值得说的了。

>重要提示：用此例外情况来说明省略读者可能需要知道的相关信息是不合适的。例如，对于一个名为getCanonicalName的方法，不要省略它的文档（其基本原理是只返回/** Returns the canonical name */),因为一个读者可能不知道“canonical name”是什么意思！

### 7.3.2 例外： overrides

在重写超类型方法的时候，并不总是要求写Javadoc。

### 7.3.4 非必须的Javadoc注释

其他类和成员根据需要和意愿使用Javadoc。

无论何时实现注释将用于定义类或成员的总体目的或行为，该注释都将被写成Javadoc（使用/**）。

非必需的Javadoc并不是严格要求遵循第7.1.2、7.1.3和7.2节的格式化规则，尽管这样做是推荐的。

