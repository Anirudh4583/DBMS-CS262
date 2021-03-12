CREATE TABLE `facebook`.`user` (
  `201951024` INT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `join_date` DATE NOT NULL,
  `city` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

ALTER TABLE `facebook`.`user` 
ADD CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `facebook`.`friends` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `facebook`.`friends` (
  `201951024` INT NULL,
  `friend_id` VARCHAR(45) NOT NULL,
  `since` DATE NOT NULL,
  `message_out_count` INT NOT NULL,
  `messageint_count` INT NOT NULL,
  PRIMARY KEY (`friend_id`),
  UNIQUE INDEX `friend_id_UNIQUE` (`friend_id` ASC) VISIBLE);

CREATE TABLE `facebook`.`post` (
  `201951024` INT NULL DEFAULT NULL,
  `post_id` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `time_of_post` TIME NOT NULL,
  `post_text` VARCHAR(45) NULL,
  PRIMARY KEY (`post_id`),
  UNIQUE INDEX `post_id_UNIQUE` (`post_id` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);

ALTER TABLE `facebook`.`post` 
ADD CONSTRAINT `comment_post_id`
  FOREIGN KEY (`post_id`)
  REFERENCES `facebook`.`post` (`post_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `comment_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `facebook`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `facebook`.`comments` (
  `comment_id` VARCHAR(45) NOT NULL,
  `text` VARCHAR(45) NULL,
  `post_id` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE INDEX `comment_id_UNIQUE` (`comment_id` ASC) VISIBLE,
  UNIQUE INDEX `post_id_UNIQUE` (`post_id` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);


CREATE TABLE `facebook`.`comment_replies` (
  `reply_id` VARCHAR(45) NOT NULL,
  `text` VARCHAR(45) NULL,
  `time_of_reply` TIME NOT NULL,
  `comment_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`reply_id`),
  UNIQUE INDEX `reply_id_UNIQUE` (`reply_id` ASC) VISIBLE,
  UNIQUE INDEX `comment_id_UNIQUE` (`comment_id` ASC) VISIBLE);

ALTER TABLE `facebook`.`comments` 
ADD CONSTRAINT `reply_comment_id`
  FOREIGN KEY (`comment_id`)
  REFERENCES `facebook`.`comments` (`comment_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `reply_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `facebook`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


CREATE TABLE `facebook`.`comment_likes` (
  `user_id` INT NOT NULL,
  `comment_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `comment_id_UNIQUE` (`comment_id` ASC) VISIBLE,
  CONSTRAINT `lke_comment_id`
    FOREIGN KEY (`comment_id`)
    REFERENCES `facebook`.`comments` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `facebook`.`attachments` (
  `attach_id` VARCHAR(45) NOT NULL,
  `text` VARCHAR(45) NULL,
  `post_id` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `attach_post_id_UNIQUE` (`post_id` ASC),
  CONSTRAINT `attach_post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `facebook`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `facebook`.`attach_type` (
  `data` VARCHAR(45) NOT NULL,
  `attach_id` VARCHAR(45) NOT NULL,
  `jpeg` VARCHAR(45) NULL,
  `mp3` VARCHAR(45) NULL,
  `mp4` VARCHAR(45) NULL,
  PRIMARY KEY (`data`),
  UNIQUE INDEX `attach_id_UNIQUE` (`attach_id` ASC) VISIBLE);

ALTER TABLE `facebook`.`attachments` 
ADD PRIMARY KEY (`attach_id`);
;


ALTER TABLE `facebook`.`attach_type` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`attach_id`);
;


ALTER TABLE `facebook`.`attach_type` 
ADD CONSTRAINT `attach_type_id`
  FOREIGN KEY (`attach_id`)
  REFERENCES `facebook`.`attachments` (`attach_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;




INSERT INTO `facebook`.`USER` (`user_id`, `name`, `email`, `join_date`, `city`) VALUES ('ani4583', 'ANIRUDH', 'ani@gmail.com', '2019-05-12', 'indore');
INSERT INTO `facebook`.`USER` (`user_id`, `name`, `email`, `join_date`, `city`) VALUES ('100max', 'max', 'max@orkut.com', '2017-10-30', 'LA');
INSERT INTO `facebook`.`USER` (`user_id`, `name`, `email`, `join_date`, `city`) VALUES ('lion1998', 'lion', '', '2020-09-14', 'NYC');
INSERT INTO `facebook`.`USER` (`user_id`, `name`, `email`, `join_date`) VALUES ('ggs3', 'garet', 'mm@gg.com', '2009-05-05');
INSERT INTO `facebook`.`USER` (`user_id`, `name`, `city`) VALUES ('cameron89', 'gtxpreet', 'delhi');
INSERT INTO `facebook`.`USER` (`user_id`, `name`, `email`, `join_date`) VALUES ('india30', 'ramesh', 'rrin@fiber.co', '1998-02-02');
