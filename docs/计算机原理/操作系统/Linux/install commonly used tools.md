# **1.**  **Install jdk1.7/1.8**

**Firstly, you need to add PPA:**

$>sudo add-apt-repository ppa:webupd8team/java

$>sudo apt-get update

**Secondly, install oracle-jave-installer**

Jdk7

$>sudo apt-get install oracle-java7-installer

jdk8

$>sudo apt-get install oracle-java8-installer

 

Installer will notice you to agree some terms of services, SELECT ok, and the SELECT yes.

(Chooseable)

/**

But if you are too lazy to lift you fingers, you can add some cmds like this:

Jdk7

$>echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

Jdk8

$>echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections

**/

Now ,you just need to wait it download from the internet. If your net’s speed is very slow, you can download the compressed file, such as xxxx.tar.gz, and copy the xxx.tar.gz to:

​    /var/cache/oracle-jdk7-installer           (jdk7) 

​    /var/cache/oracle-jdk8-installer           (jdk8) 

Then, reinstall the installer.

**Thirdly, set jdk environment**

JDk7

$>sudo update-java-alternatives -s java-7-oracle

JDK8

$>sudo update-java-alternatives -s java-8-oracle

If you have installed both jdk7 and jdk8, and you need to switch form 1.7 to 1.8 or 1.8 to 1.7, you can do like this:

jdk8 to jdk7

$>sudo update-java-alternatives -s java-7-oracle

jdk7 to jdk8

$>sudo update-java-alternatives -s java-8-oracle

**Now, test whether jdk work well:**

$>java -version

$>javac -version

# **2.**  **Install Eclipse**

**Firstly, Install**

Download eclipse-java-neon-2-linux-gtk-x86_64.tar.gz from eclipse’s official website

Then uncompress it to your workhome

**Secondly, Create fast launch menu:** 

**Locate in \*/usr/share/applications\***，create ***eclipse.desktop\***:

$>sudo vim eclipse.desktop, and then input codes like this:

```xml
 [Desktop Entry]

Type=Application

Name=Eclipse


Comment=Eclipse Integrated Development Environment

Icon=your path/icon.xpm

Exec=your path/eclipse

Terminal=false

Categories=Development;IDE;Java;
```

Now, click ***Dash and type “eclipse”\***，you will see a eclipse icon？Click it to launch，Eclipse will launch now，at the same thime, Eclipse icon will show in the left taskbar。

We can lock it in the taskbar，you just to click right button your mouse and click lock to Launcher。

Note：we can move **eclipse.desktop** to ***/home/dezhou/SoftWares/eclipse (Your eclipse root path)\***，and create a soft link to ***/usr/share/applications\***，in this case we don’t need to recreate eclipse.desktop file when we reinstall our system。

$>cd /usr/share/applications

$>sudo ln -s /home/dezhou/SoftWares/eclipse /eclipse.desktop

# **3.**   Install Mkdocs

## 安装

> pip3 install mkdocs

使用系统默认的镜像安装会出现很多报错问题，如下：

> Retrying (Retry(total=4, connect=None, read=None, redirect=None, status=None)) after connection broken by 'ProxyError('Cannot connect to proxy.', OSError(0, 'Error'))': /simple/lunr/  

由于mkdocs安装过程中又会依赖其他的包，所以一个个手动下载不太现实。因此需要替换pip的安装镜像。

找到pip3的congfig文件，pip.conf；我的文件位于~/.config/pip目录中

编辑该文件，修改为如下

> [global]
> index-url = http://mirrors.aliyun.com/pypi/simple/
> [install]
> trusted-host=mirrors.aliyun.com

这样便可以将pip的安装镜像替换为阿里镜像

再执行#pip3 install mkdocs，便可安装成功

## 问题

安装完成后，发现直接再终端输入mkdocs显示：

> bash: mkdocs：未找到命令

这是因为mkdocs没有添加至P系统环境变量导致

添加mkdocs至习系统环境变量

在~/.bashrc文件末尾添加export PATH=/home/dezhou/.local/bin:$PATH，保存退出

终端执行souce ~/.bashrc

再次执行mkdocs，显示如下表明成功

> Usage: mkdocs [OPTIONS] COMMAND [ARGS]...
>
>   MkDocs - Project documentation with Markdown.
>
> Options:
>   -V, --version  Show the version and exit.
>   -q, --quiet    Silence warnings
>   -v, --verbose  Enable verbose output
>   -h, --help     Show this message and exit.
>
> Commands:
>   build      Build the MkDocs documentation
>   gh-deploy  Deploy your documentation to GitHub Pages
>   new        Create a new MkDocs project
>   serve      Run the builtin development server