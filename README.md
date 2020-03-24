# myConmmunity
this is my community demo copy from codedrinker

## 工具

## 资料
[github OAuth ](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[bootstrap](https://v3.bootcss.com/css/)  
[thymeleaf](https://www.thymeleaf.org/)  
[JQuery](jquert.com)  
[SpringMVC](https://docs.spring.io/spring/docs/5.2.4.RELEASE/spring-framework-reference/web.html#spring-web)  


## 快捷键记录
ctrl+alt+v ： 自动抽取变量  
ctrl+shift+F12： 最大化代码编辑界面  
tab: html 页面自动补全标签

## 脚本  
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

```

##当前进度
p31 完成问题详情页面 08:49