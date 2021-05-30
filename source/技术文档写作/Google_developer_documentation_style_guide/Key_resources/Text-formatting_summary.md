# 文本格式摘要

该页总结了样式指南中其他地方介绍的许多常规文本格式约定，并提供了快速参考。有关详细信息，请参见[视觉格式](..\HTML_and_CSS\HTML_and_semantic_tagging.md)

**粗体**

对[UI 元素](..\Computer_interfaces\UI_elements_and_interaction.md)和[注意](..\Formatting_and_organization\Notes_and_other_notices.md)开头使用粗体格式， `<b>` 或者 `**`, 

尽管在Markdown中，双下划线， `__`， 也可以表示粗体格式；但是在文本编辑器中难以区分。最好使用双*号表示粗体。

**斜体**

在提请注意特定字词或词组时，请使用斜体格式， `<i>` or `_`；例如在[定义术语](..\Formatting_and_organization\Key_terms.md)或者用[单词指代自身](..\Formatting_and_organization\Words_as__words.md)时。

将书名，电影，网络连续剧和其他全长作品的标题斜体化，除非它们是链接的一部分。有关更多信息，请参见[交叉引用](..\Linking\Cross-references.md)。

将参数名称斜体化。例如，当您引用向`doSomthing(Uri data, int count)`之类的方法，将_data_和_count_斜体化。

将数学变量和版本变量斜体化。例如， *x* + *y* = 3, version 1.4.*x*。

要在HTML中表示强调，请使用`<em>`元素，该元素在大多数情况下会以斜体显示。要在Markdown中表示强调，请使用下划线（`_`）或者单引号（`*`）；您无法在Markdown中进行语义标记。

**下划线**

不要使用下划线。

**代码字体**

在HTML中使用`<code>`或者在Markdown中使用`` `将等宽字体和其他样式应用于[文本代码](..\Computer_interfaces\Code_in_text.md)，内联代码和用户输入。

将代码快`<pre>`或者```` `作用于[代码示例](..\Computer_interfaces\Code_Samples.md)或者其他代码块。

不要重写或者修改内联字体样式。

使用代码字体标记代码，如类名，方法名，HTTP状态码，控制台输出和占位符变量。

**句子大小写**

[一般大写](..\Language_and_grammar\Capitalization.md)采用美式英语风格。在所有的[章节标题](..\Language_and_grammar\Capitalization.md)（headings），[大标题](..\Language_and_grammar\Capitalization.md)（titles），和[导航栏](..\Language_and_grammar\Capitalization.md)。

**引号**

一般来说，[标点符号](..\Punctuation\Quotation_marks.md)使用美式英语风格。

对于较短作品的大标题，如网络系列中的文章或剧集，请将大标题用引号括起来，除非它们是链接的一部分。

**字体类型，大小和颜色**

请不要覆盖[字体类型，大小，或颜色](..\HTML_and_CSS\Font_and_font_size.md)的全局样式。

使用[语义HTML](..\HTML_and_CSS\HTML_and_semantic_tagging.md)控制页面上的文本样式。例如，使用code标签，`<code>`或`` `，而不要使用等宽字体手动设置文本样式。

**其他标点符号约定**

不要使用[与标识符（&）](..\Key_resources\Word_list.md)作为连接词或者_and_的缩写。使用_and_替代。其中包括章节标题和导航栏。**例外：**需要引用UI元素或使用_&_的菜单名的情况下，可以使用_&_。

将引号和结束标点放在[链接文本之外](..\Linking\Link_text.md)。

