create database if not exists Connectville;
use Connectville;
/*
USER: user_id, username, password, email, role
POST: post_id, title, topic (similar to FB hashtag), image, link, text, create_user, create_timestamp
NEWSFEED: post_id, pinned 
GROUP: group_id, title, description
GROUP_POSTS: id, group_id, post_id
USER_FOLLOW: follow_id, user_id, group_id
USER_LIKE: like_id, user_id, post_id
USER_COMMENT: comment_id, user_id, post_id, text
*/

create table if not exists users(
	user_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL,
    PRIMARY  KEY(user_id));
    
create table if not exists posts(
	post_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    topic VARCHAR(50) NOT NULL,
    image VARCHAR(200),
    link VARCHAR(200),
    text VARCHAR(280),
    create_user INT NOT NULL,
    create_timestamp TIMESTAMP,
    pinned BOOL DEFAULT FALSE,
    PRIMARY KEY(post_id),
    FOREIGN KEY(create_user) REFERENCES users(user_id));
    
    

create table if not exists groups_(
	group_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    description varchar(200),
    PRIMARY KEY(group_id));
    
create table if not exists group_posts(
	id INT NOT NULL AUTO_INCREMENT,
    group_id INT NOT NULL,
    post_id  INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(group_id) REFERENCES groups_(group_id),
    FOREIGN KEY(post_id) REFERENCES posts(post_id));
    
create table if not exists user_follow(
	follow_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    group_id INT NOT NULL,
    PRIMARY KEY(follow_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(group_id) REFERENCES groups_(group_id));
    
create table if not exists user_like(
	like_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    PRIMARY KEY(like_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(post_id) REFERENCES posts(post_id));

create table if not exists user_comment(
	comment_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    text VARCHAR(280),
    PRIMARY KEY(comment_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(post_id) REFERENCES posts(post_id));

    
    