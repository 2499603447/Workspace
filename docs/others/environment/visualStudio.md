# 插件工具

## 设置tab为4空格

## 设置代码列限制线Editor Guidelines

https://marketplace.visualstudio.com/items?itemName=PaulHarrington.EditorGuidelines

## 安装代码规范检查工具Sonar

https://marketplace.visualstudio.com/items?itemName=SonarSource.SonarLintforVisualStudio2019

# 自动添加注释

Visual Studio+VAssistX

在VAssistX中为C++提供了比较规范注释模板，用这个注释模板为编写的C++代码添加注释是很便捷的选择，操作起来也很简单，具体方法和步骤如下：

## 1.增加函数头注释

![](env.assets/20161026112749628)

之后注释模板中编辑自己需要的注释内容：

![](env.assets/20161026112916035)

编辑好注释模板之后将输入光标定位到需要添加注释的C++函数，然后右键进行添加

![](env.assets/20161026113223782)

右击函数名，然后依次点击“Refacto”–>“Document Method”，这个时候函数头注释就会蹦出来，不过这个注释的格式是默认的，想修改注释格式，可以通过以下方法。

点击 “VAssistX”–>“Visual VAssistX Options”然后选择Suggestions,再点击“Edit VA Snippets”。
在打开的窗口中选择Refactor Document Method，在这就可以更改你的显示样式了。可以参照默认的注释格式来定制自己的注释：

```c++
/*!
*@brief  
*@author %USERNAME%
*@date   $YEAR$年$MONTH$月$DAY$日
*@param[out] 
*@param[in]  $MethodArg$  
*@return     $SymbolType$  
*/
```

## 2.增加文件头注释 

要想在文件头添加注释，需要
把鼠标光标定位到VS编辑器的第一行，点击 “VAssistX”–>“Insert VA Snippet…”—>“File Header Detail”，即可增加文件头注释。默认的注释格式如下，可以通过点击“VAssistX”–>“Visual VAssistX Options”—>“Advanced”—>“Suggestions”—>“Edit VA Snippets”，选择你相应语言的“File Header Detail”修改。

```c++
/********************************************************************
/*!
 * \file client.cpp
 *
 * \author TerryTian
 * \mail tianhaohust@gmail.com
 * \date 九月 2016
 *
 * 
 */
*********************************************************************/
```

