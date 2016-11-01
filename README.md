# 简介

这是我封装的一个修改JFrame外观的类，它的用途是介绍JAVA调用图片、音频、字体这三类静态资源的方法，当然也可以直接拿来用。

下载后用Eclipes打开运行即可。

效果图(自行脑补BGM)：
![这里写图片描述](http://img.blog.csdn.net/20161101220030475)

# 注意

在音频调用中，可能会报错：“Access restriction: The type AudioPlayer is not accessible due to restriction on required library C:\JDK1.5\jre\lib\rt.jar”。

这是Eclipse的原因，修改一下配置即可：

修改window->preference->java->complier->errors/warning->deprecated and restricted API把 Forbidden reference 的Error改成warning 即可
