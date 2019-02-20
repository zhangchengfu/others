CREATE TABLE `oauth2_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(100) DEFAULT NULL,
  `client_id` varchar(100) DEFAULT NULL,
  `client_secret` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_oauth2_client_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `oauth2_client` VALUES (1, 'oauth2-client', 'f3ed0d90-3fc8-4eba-b83c-a0cfe54c174d', '78e238b4-c199-49ad-b619-69b6a78959d8');

CREATE TABLE `oauth2_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_oauth2_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `oauth2_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e');