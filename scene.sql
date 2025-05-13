CREATE TABLE scene (
                       id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                       code VARCHAR(50) NOT NULL COMMENT '场景代号',
                       max_player_size INT NOT NULL COMMENT '最大玩家数量',
                       min_player_size INT NOT NULL COMMENT '最小玩家数量（开始游戏）',
                       scene_name VARCHAR(100) NOT NULL COMMENT '场景名称',
                       pos1_x DOUBLE NOT NULL COMMENT '范围1 - X坐标',
                       pos1_y DOUBLE NOT NULL COMMENT '范围1 - Y坐标',
                       pos1_z DOUBLE NOT NULL COMMENT '范围1 - Z坐标',
                       pos2_x DOUBLE NOT NULL COMMENT '范围2 - X坐标',
                       pos2_y DOUBLE NOT NULL COMMENT '范围2 - Y坐标',
                       pos2_z DOUBLE NOT NULL COMMENT '范围2 - Z坐标',
                       world_name VARCHAR(100) NOT NULL COMMENT '世界名称',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

CREATE TABLE spawn (
                       id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                       scene_id INT NOT NULL COMMENT '场景ID (外键关联场景表)',
                       x DOUBLE NOT NULL COMMENT 'X坐标',
                       y DOUBLE NOT NULL COMMENT 'Y坐标',
                       z DOUBLE NOT NULL COMMENT 'Z坐标',
                       world_name VARCHAR(100) NOT NULL COMMENT '世界名称',  -- 新增的字段
                       FOREIGN KEY (scene_id) REFERENCES scene(id) ON DELETE CASCADE ON UPDATE CASCADE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);
CREATE TABLE `leave` (
                         id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                         scene_id INT NOT NULL COMMENT '场景ID (外键关联场景表)',
                         x DOUBLE NOT NULL COMMENT 'X坐标',
                         y DOUBLE NOT NULL COMMENT 'Y坐标',
                         z DOUBLE NOT NULL COMMENT 'Z坐标',
                         world_name VARCHAR(100) NOT NULL COMMENT '世界名称',
                         FOREIGN KEY (scene_id) REFERENCES scene(id) ON DELETE CASCADE ON UPDATE CASCADE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

CREATE TABLE box (
                     id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
                     scene_id INT NOT NULL COMMENT '场景ID (外键关联场景表)',
                     x DOUBLE NOT NULL COMMENT 'X坐标',
                     y DOUBLE NOT NULL COMMENT 'Y坐标',
                     z DOUBLE NOT NULL COMMENT 'Z坐标',
                     world_name VARCHAR(100) NOT NULL COMMENT '世界名称',  -- 新增的字段
                     FOREIGN KEY (scene_id) REFERENCES scene(id) ON DELETE CASCADE ON UPDATE CASCADE,
                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);
