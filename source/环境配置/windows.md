# Windows

## 删除linnux开机启动项

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