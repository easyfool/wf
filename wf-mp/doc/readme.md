# MybatisPlus

## 代码生成工具

### 官方插件

mybatisX

### 推荐插件

mybatisplus

## 常见注解
- @TableName: 用来指定表名称
- @TableId: 用来指定表的主键字段信息
- @TableField: 用来指定表中的普通字段信息

IdType 枚举
- Auto: 数据库自增长
- INPUT: 通过set方法自行输入
- ASSIGN_ID

使用@TableField的场景

- 成员变量与数据库字段名称不一致
- 成员变量以is开头，且是布尔值
- 成员变量与数据库字段冲突(使用了数据库关键字)
- 成员变量不是数据库字段，@TableField(exists=false)


