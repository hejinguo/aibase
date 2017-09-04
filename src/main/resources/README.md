com.ai.base 前缀的包为核心基础功能,后期会生成独立的jar包,因此不允许随意修改和增加代码.
------------------------------------------------------------------------------------
项目开发约定:
com.ai.oam 前缀的包为本项目功能代码,包名次的最后一个单词代表其功能分组目录.
com.ai.oam.controller 		SpringMVC控制层
com.ai.oam.service    		业务逻辑层
com.ai.oam.mapper     		Mybatis持久层
com.ai.oam.model	   		Bean与数据库表的映射对象
com.ai.oam.item	       	 枚举对象的存放位置