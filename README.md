# hong-framework
Spring Boot Starter快速开发集

1. Maven中<dependencies>节点和<dependencyManagement>节点的区别
https://www.cnblogs.com/mr-wuxiansheng/p/6189438.html
https://zhidao.baidu.com/question/328259809156818925.html

2.基于maven使用IDEA创建多模块项目
http://www.itdaan.com/blog/2017/01/12/ecef5f0820622a6e8d966ec30e7ae334.html

3.项目目录结构说明
hong-framework:所有项目的父项目,主要用来管理所有项目使用的jar包及其版本
hong-common:公共的工具类项目，继承父项目hong-framework，它会被打成jar包供其它项目使用
hong-starter-parent:各种自定义的starter的父项目,继承父项目hong-framework
