# Window中删除linnux开机启动项

在Win10删除linux时直接删除分区后，对于启动项(EFI)的处理
 一、
 1、用win+R打开diskpart.exe
 2、输入list disk，查看磁盘状态
 3、根据容量选择win10系统盘：select disk 0
 4、输入list partition，查看分区情况
 5、选择类型为“系统”的分区：select partition 1
 6、输入assign letter=p，将该分区命名为“p”，出现在“我的电脑”中（可以到“我的电脑”中查看，但是即使以管理员权限也不能进入）

二、
 1、以管理员权限打开cmd
 2、此时可以用dos命令操作p盘：cd进入目录，dir查看目录，cd删除文件，rd删除文件夹（空），rd /s /q删除文件夹及文件
 3、下面为执行的命令
 （1）p:
 （2）dir
 （3）cd EFI
 （4）dir（此时能看到一个名为Ubuntu或者UOS(deepin)的文件夹）

 （5）rd /s /q ubuntu（注意空格）
 （6）dir（可以看到Ubuntu文件夹已经删除）
 （7）remove letter=p（在diskpart中运行;不再为p盘命名）

# Ubuntu切换阿里源

请注意：Ubuntu 20.04 已于4月23日发布正式版本。
请注意：发现有些软件比如ckermit，仓库里还没有，升级及换源自行斟酌。

## 1 备份原来的源

```
cp -ra /etc/apt/sources.list /etc/apt/sources.list.bak
```

## 2 搞清楚ubuntu的代号

比如：
4.10 Warty Warthog(长疣的疣猪)
5.04 Hoary Hedgehog(灰白的刺猬)
5.10 Breezy Badger(活泼的獾)

6.06(LTS) Dapper Drake(整洁的公鸭)
6.10 Edgy Eft(急躁的水蜥)
7.04 Feisty Fawn(坏脾气的小鹿)
7.10 Gutsy Gibbon(勇敢的长臂猿)

8.04(LTS) Hardy Heron(耐寒的苍鹭)
8.10 Intrepid Ibex (勇敢的野山羊)
9.04 Jaunty Jackalope(得意洋洋的怀俄明野兔)
9.10 Karmic Koala(幸运的考拉)

10.04(LTS) Lucid Lynx(清醒的猞猁)
10.10 Oneiric Ocelot(梦幻的豹猫)
11.04 Natty Narwhal(敏捷的独角鲸)
11.10 Oneiric Ocelot（有梦的虎猫）

12.04(LTS) Precise Pangolin(精准的穿山甲)
12.10 Quantal Quetzal(量子的绿咬鹃)
13.04 Raring Ringtail(铆足了劲的猫熊)
13.10 Saucy Salamander(活泼的蝾螈)

14.04(LTS) Trusty Tahr (可靠的塔尔羊)(LTS)
14.10 Utopic Unicorn(乌托邦独角兽)
15.04 Vivid Vervet (活泼的小猴)
15.10 Wily Werewolf (狡猾的狼人)

16.04(LTS) Xenial Xerus (好客的非洲地松鼠)
16.10 Yakkety Yak（牦牛）
17.04 Zesty Zapus(开心的跳鼠)
17.10 Artful Aardvark(机灵的土豚)

18.04(LTS) Bionic Beaver（仿生海狸）
18.10 Cosmic Cuttlefish（宇宙墨鱼）
19.04 Disco Dingo（舞动的灵犬）
19.10 Eoan Ermine（白貂）

20.04(LTS) Focal Fossa（专注的马达加斯加长尾狸猫）
…
要知道当前系统的代号，可以用以下命令：

```
lsb_release -a
```

## 3 检查阿里源看下这个源存在不存在

http://archive.ubuntu.com/ubuntu/dists/

可以看到，现在已经有了ubuntu 20.04(代号focal)的源。

## 4 然后以下面的为模板进行更换

把’TODO’的地方换成上面的Codename

```
deb http://mirrors.aliyun.com/ubuntu/ TODO main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ TODO main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ TODO-security main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ TODO-security main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ TODO-updates main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ TODO-updates main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ TODO-proposed main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ TODO-proposed main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ TODO-backports main restricted universe multiverse

deb-src http://mirrors.aliyun.com/ubuntu/ TODO-backports main restricted universe multiverse
```

换好20.04的源以后是这样：

```
deb http://mirrors.aliyun.com/ubuntu/ focal main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-security main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-security main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-updates main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-updates main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-proposed main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-proposed main restricted universe multiverse

deb http://mirrors.aliyun.com/ubuntu/ focal-backports main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ focal-backports main restricted universe multiverse
```


然后粘贴到刚才的/etc/apt/sources.list里。

## 5 更新缓存和升级

sudo apt-get update
sudo apt-get upgrade

# Nodejs

## 安装nodejs

### Repository安装

* 安装npm

  > sudo apt-get install npm

  系统默认的repository下载下来的版本可能比较老，出现和nodejs版本不匹配的情况，如下：

  > npm WARN npm npm does not support Node.js v10.21.0
  > npm WARN npm You should probably upgrade to a newer version of node as we
  > npm WARN npm can't make any promises that npm will work with this version.
  > npm WARN npm Supported releases of Node.js are the latest release of 4, 6, 7, 8, 9.
  > npm WARN npm You can find the latest version at https://nodejs.org/

  这时候我们需要更新npm为最新版本

* 安装nodejs管理器

  > sudo npm install -g n
  >
  > https://www.jianshu.com/p/a927bcecdbc0

* 安装nodejs

  > 查看稳定版本
  >
  > ~$: n --stable
  >
  > 14.16.0
  >
  > 安装14.16.0
  >
  > ~$: n 14.16.0

  等待安装完成

* 设置环境变量

  找到node的安装位置，我刚刚安装的位置是/usr/local/n/versions/node/14.16.0/

  在~/.bashrc文件的最后一行添加如下一行，保存退出，重新打开终端，或者source ~/.bashrc

  > export PATH=/usr/local/n/versions/node/14.16.0/bin:$PATH

* 查看版本号

  > ~$ node --version
  > v14.16.0
  > ~$ npm --version
  > 6.14.11

### 手动下载安装

## 安装package.json中的依赖

### 安装所有模块

npm install 

### 安装dependencies中的模块

npm install --dependencies

### 安装devDependencies中模块

npm install --devDependencies