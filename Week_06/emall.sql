-- 用户表
CREATE TABLE user (
    id BIGINT(20) UNSIGNED NOT NULL  COMMENT '用户id',
    nick_name VARCHAR(32) NOT NULL COMMENT '用户名称',
    password VARCHAR(128) NOT NULL COMMENT '密码，加密保存',
    register_time DATETIME NOT NULL COMMENT '注册时间',
    status TINYINT(2) UNSIGNED NOT NULL COMMENT '状态: 1-生效，2-失效',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `nick_name_unique` (`nick_name`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '用户表';

-- 用户绑定账户表
CREATE TABLE user_bind_account (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '主键id',
    user_id BIGINT(20) UNSIGNED NOT NULL COMMENT '用户id',
    type TINYINT(2) UNSIGNED NOT NULL COMMENT '账户类型: 1-手机，2-邮箱，3-微信',
    value VARCHAR(128) NOT NULL COMMENT '账户信息，手机号需加密保存',
    status TINYINT(2) UNSIGNED NOT NULL COMMENT '状态: 1-生效，2-解绑',
    bind_time DATETIME NOT NULL COMMENT '绑定时间',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '绑定账户表，用于登录，找回密码等';
-- 创建索引用于查询
CREATE INDEX user_id_index ON user_bind_account (user_id);
-- 创建索引用于查询
CREATE INDEX account_index ON user_bind_account (value);

-- 用户收获地址
CREATE TABLE user_address (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '主键id',
    user_id BIGINT(20) UNSIGNED NOT NULL COMMENT '用户id',
    receiver_name varchar(128) NOT NULL COMMENT '收货姓名',
    receiver_phone varchar(128) NOT NULL COMMENT '收货固定电话，需加密',
    receiver_mobile varchar(128) NOT NULL COMMENT '收货手机号码，需加密',
    country varchar(32) NOT NULL COMMENT '国家',
    province varchar(32) NOT NULL COMMENT '省，州',
    city varchar(32) NOT NULL COMMENT '城市',
    district varchar(32) NOT NULL COMMENT '区，县',
    zip_code varchar(32) NOT NULL COMMENT '邮编',
    detail varchar(128) NOT NULL COMMENT '详细地址',
    status TINYINT(2) UNSIGNED NOT NULL COMMENT '状态: 1-生效，2-失效',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '用户收获地址表';
CREATE INDEX user_id_index ON user_address (user_id);--创建索引用于查询

-- 商品分类表
CREATE TABLE category (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '分类id',
    parent_id BIGINT(20) UNSIGNED NOT NULL COMMENT '父类',
    name varchar(128) NOT NULL COMMENT '名称',
    status TINYINT(2) UNSIGNED NOT NULL COMMENT '状态: 1-生效，2-失效',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '商品分类表';

-- 商品表
CREATE TABLE product (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '分类id',
    category_id BIGINT(20) UNSIGNED NOT NULL COMMENT '分类',
    brand_name varchar(128) NOT NULL COMMENT '品牌名称',
    name varchar(128) NOT NULL COMMENT '商品名称',
    price BIGINT(20)  NOT NULL COMMENT '商品价格，以分为单位存储',
    status TINYINT(2) UNSIGNED NOT NULL COMMENT '状态: 1-生效，2-失效',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '商品表';

-- 订单表
CREATE TABLE order (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '订单id',
    user_id BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    total_price BIGINT(20)  NOT NULL COMMENT '订单总价',
    status TINYINT(2) UNSIGNED NOT NULL COMMENT '状态: 0-已取消，10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
    pay_time DATETIME NOT NULL COMMENT '支付时间',
    send_time DATETIME NOT NULL COMMENT '发货时间',
    end_time DATETIME NOT NULL COMMENT '交易完成时间',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '订单表';
CREATE INDEX user_id_index ON order (user_id); --创建索引用于查询

-- 订单详情表
CREATE TABLE order_item (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '详情id',
    user_id BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    order_id BIGINT(20) UNSIGNED NOT NULL COMMENT '订单ID',
    product_id BIGINT(20) UNSIGNED NOT NULL COMMENT '商品ID',
    total_price BIGINT(20)  NOT NULL COMMENT '总价',
    quantity INT(11)  NOT NULL COMMENT '商品数量',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '订单详情表';
CREATE INDEX user_id_index ON order_item (user_id); --创建索引用于查询
--创建索引用于查询
CREATE INDEX order_id_index ON order_item (order_id);

-- 支付表
CREATE TABLE payment (
    id BIGINT(20) UNSIGNED NOT NULL COMMENT '分类id',
    user_id BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    order_id BIGINT(20) UNSIGNED NOT NULL COMMENT '订单ID',
    amount BIGINT(20)  NOT NULL COMMENT '支付金额',
    pay_platform int(10) DEFAULT NULL COMMENT '支付平台: 1-支付宝, 2-微信, 3-网银',
    platform_number varchar(200) DEFAULT NULL COMMENT '支付流水号',
    status varchar(20) DEFAULT NULL COMMENT '支付状态',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT '支付表';