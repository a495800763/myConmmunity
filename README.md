# myConmmunity
this is my community demo copy from codedrinker

## 工具

## 资料
[SpringMVC](https://docs.spring.io/spring/docs/5.2.4.RELEASE/spring-framework-reference/web.html#spring-web)  
[Spring Boot](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/)  
[thymeleaf](https://www.thymeleaf.org/)  
[github OAuth ](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[bootstrap](https://v3.bootcss.com/css/)  
[JQuery](jquert.com)  
[MyBatis Generator](http://mybatis.org/generator/)  


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

```sql

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `commentator` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `like_count` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

```bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

##当前进度
p31 完成问题详情页面 08:49