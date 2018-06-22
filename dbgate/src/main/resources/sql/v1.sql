CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(20) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT 'user password field',
  `emailAddress` varchar(10) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `lastUpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `avatarURL` varchar(1024) DEFAULT '' COMMENT 'url of user icon',
  `phoneNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `uuid_alloc` (
  `id` int(11) NOT NULL AUTO_INCREMENT comment "user id alloced",
  `osid` varchar(256) not null comment "os id of platform",
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `osid` (`osid`)
)ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


CREATE TABLE `feed` (
  `feedId` int(11) NOT NULL AUTO_INCREMENT,
  `authorId` int(11) NOT NULL comment 'author id of the sns',
  `text` VARCHAR(1024) NOT NULL DEFAULT "" comment 'text content of the sns',
  `mediaLink` TEXT not null comment 'media link such as image or video',
  `commentNum` int(11) not null default 0 comment 'comment number of the sns',
  `likeNum` int(11) not null default 0 comment 'like number of the sns',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`feedId`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `follows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromUid` int(11) NOT NULL COMMENT 'from user id',
  `toUid` int(11) NOT NULL COMMENT 'to user id',
  `direction` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'relation direction',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `from_to_direction` (`fromUid`,`toUid`,`direction`),
  KEY `from_to` (`fromUid`,`toUid`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;