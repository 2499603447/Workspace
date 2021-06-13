# 编写易理解的文档

**要点：**编写适合残疾人士使用的文档。

我们在编写开发文档时会考虑到文档的可访问性。本页面并不是非常详尽的参考资料，但是描述了一些通用的准则和示例用于说明遵循的最佳的做法。[世界卫生组织](https://www.who.int/en/news-room/fact-sheets/detail/disability-and-health)![open_in_new_black_24dp](../../../assets/icons/open_in_new_black_24dp.svg)估计15%的世界人口（超过1亿人口)有无障碍的需求。当文档在编写时就考虑到了可访问性，那么它将会改善所有读者的整体体验。

## 一般注意事项

* 不要使用带有身体歧视的语言（ableist）。避免在讨论残疾和无障碍时使用具有偏见和伤害的词。更多信息，请参阅[编写包容性文档](..\General_principles\Inclusive_language.md)。

* 确保读者只需要使用键盘就可以访问文档的所有部分（包括选项卡，表单提交按钮和交互元素），而无需使用鼠标或者轨迹板。

* 使用屏幕阅读器测试文档。这种测试会帮助您发现文档内容中的无障碍方面的问题，且是一个自我编辑文档内容的好方法。要适用屏幕阅读器，请参阅[屏幕阅读器列表](https://en.wikipedia.org/wiki/List_of_screen_readers)![open_in_new_black_24dp](../../../assets/icons/open_in_new_black_24dp.svg)。

* 在HTML中，使用[语义标签](..\HTML_and_CSS\HTML_and_semantic_tagging.md)。例如，使用`<em>`元素仅表示强调，而不是表示斜体。

* 在HTML中，首选[原生样式](https://developer.mozilla.org/en-US/docs/Web/HTML/Element)![open_in_new_black_24dp](../../../assets/icons/open_in_new_black_24dp.svg)而不是自定义样式。

* 去除非必要的字体格式。（屏幕阅读器会明确描述文本的修改。）

* 如果您的文档描述一个产品，其中包含特殊的无障碍特性，那么您需要在文档中明确这些特性。例如，`gcloud`命令行工具包含可切换的无障碍特性，如百分比进度条和ASCII渲染框。

* 在句子和段落中不要强制换行。换行符在调整窗口大小或者放大的文本中可能无法正常工作。

* 尽可能避免[驼峰大小写](https://en.wikipedia.org/wiki/Camel_case)![open_in_new_black_24dp](../../../assets/icons/open_in_new_black_24dp.svg)以及[全大写](https://en.wikipedia.org/wiki/All_caps)![open_in_new_black_24dp](../../../assets/icons/open_in_new_black_24dp.svg)的方式。有些屏幕阅读器会单独朗读大写字母，有些语言的字母只有[单个表示方式](https://en.wikipedia.org/wiki/Unicase)![open_in_new_black_24dp](../../../assets/icons/open_in_new_black_24dp.svg)，没有大小写之分。遵循[大写](..\Language_and_grammar\Capitalization.md)准则。

* 根据屏幕阅读器（或个人设置）的不同，并非所有标点符号都

  读。确保在没有标点符号的同时能够准确的将文档的内容传递给阅读者。因此，尽可能避免使用感叹号、问号和分号。

* 在标题，文本，导航或目录中，请勿使用*＆*代替*and*。但是，在引用使用_&_的UI元素时，或者在空间限制要求缩写的表标题和图表标签中，可以使用_&_。当然，处于技术目的，在代码中使用_&_也是可以的。

## 轻松阅读

* 打破文字墙，以提高浏览性。例如，创建单独的段落，章节标题和使用列表。

* 使用较短的句子。尝试每个句子使用少于26个词。

* 如果某些词需要频繁地使用，则在第一次使用时定义首字母缩写词和缩写词。

* 对相同的事情是同并行书写结构。例如，每个列表以同样的格式开始。

* 将段落的重要信息放在第一句中以便于浏览。

* 使用清晰、直接的语言。避免使用双重否定和例外的例外。

  ![image-20210530140440770](../assets/thumb_up_black_24dp.svg) **推荐：** You can continue without a path。

  ![image-20210530140610116](../assets/thumb_down_black_24dp.svg) **不推荐：** A missing path won't prevent you from continuing.

## 章节标题和大标题

使用描述性的章节标题和大标题，因为这样可以帮助用户在不同页面之间进行切换。如果章节标题和大标题具有唯一性，那么在不通过页面之间的跳转将会变得很容易。

* 使用章节标题层次结构。
* 不要跳过标题层次结构。例如，只将`<h3>`放在`<h2>`下面。
* 要更改标题的视觉样式，请使用CSS，而不要使用与层次结构无关的标题级别。
* 不要有空标题或者标题没有与之关联内容
* 使用标题元素标记标题。在HTML中：`<h1>`、`<h2>`等。Markdown中：`#`、`##`等等。
* 使用一级标题作为页面标题或主要内容标题。

更多信息和示例，请参阅[章节标题和大标题](..\Formatting_and_organization\Headings_and_titles.md)。

## 链接

* 使用[有意义的链接文本](..\Linking\Link_text.md)。在断章取义时，链接应该是有意义的。
* 不要使用*单击此处*或*阅读本文档*。一些使用屏幕阅读器的人会从一个链接跳到另一个链接扫视页面，且需要了解链接中包含的内容。
* 使用[外部链接图标](..\Linking\Cross-references.md)用来表明该链接会跳转至一个新域。
* 不要强制链接在新的选项卡或者窗口中打开。让读者决定如何打开链接。如果您认为某个链接需要在新选项卡或窗口中打开，请让读者知道该链接的打开方式与预期不同。例如，如果以下链接在新选项卡中打开，则链接文本应告知他们：[链接到其他站点（在新选项卡中打开）](..\Linking\Links_to_other_sites.md)。
* 如果你使用了一个页面内链接，使用一个标准短语来提示读者，这样他们就知道链接将把他们带到哪里。有关详细信息，请参阅[指向同一页上各节的链接](..\Linking\Cross-references.md)。
* 尽可能地避免相邻链接。而是在两者之间用一个字符分隔它们。
* 如果一个链接用于下载文件，链接文本需要表明该动作以及文件类型。

## 图片

* 对于每张图片，请提供[alt文本](..\Formatting_and_organization\Figures_and_other_images.md)以充分概括每张图片的意图。
* 不要在图像中呈现新信息；始终提供与图像相同的文本说明。
* 除非绝对必要，否则不要重复图像。
* 不要使用文本、代码示例或终端输出的图像。请使用实际文本。
* 如果可以，请使用SVG而不是PNG。SVG在放大图像时能够保持清晰。

更多信息，请参阅[图片相关的文本](..\Formatting_and_organization\Figures_and_other_images.md)

## 视频，录音和GIF动画

* 提供音频和视频内容的字幕、文字记录或描述。例如，可以使用YouTube的[音频捕捉](https://support.google.com/youtube/answer/6373554)功能
* 确保字幕可以翻译成主要语言。
* 不要使用腐蚀或灰化元素。它们可以引起运动捕捉方面的弊端。

## 按钮和图标

* 对于表单提交按钮，请使用原生的HTML`<button>`元素。
* 图标是表示对象或函数的符号或图像。有关图标的使用信息，请参阅UI元素和交互页面的[按钮和图标](..\Computer_interfaces\UI_elements_and_interaction.md)部分。

## UI导航

当使用尖括号（>）记录菜单路径时，请添加[`arial-label`标签属性](https://www.w3.org/TR/WCAG20-TECHS/ARIA14.html)。因为有些屏幕阅读器会把此符号解释为大于号或者键盘的向右箭头。有关更多的信息和示例，请参阅[菜单栏](..\Computer_interfaces\UI_elements_and_interaction.md)。

## 表格

## 公式

## 自定义CSS和JavaScript

## 文本渲染

## 更多资源

