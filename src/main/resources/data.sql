insert into users values(1,'Daniel', 'daniel', 'daniel@mail.com', 'user');
insert into users values(2,'Bianca', 'bianca', 'bianca@mail.com', 'user');
insert into users values(3,'Stefan', 'stefan', 'stefan@mail.com', 'user');
insert into users values(4,'Vlad', 'vlad', 'vlad@mail.com', 'user');
insert into users values(5,'Dragos', 'dragos', 'dragos@mail.com', 'user');
insert into users values(6, 'Admin', 'admin', 'admin@mail.com', 'admin');

insert into groups_ values(1, 'HR', 'HR Group');
insert into groups_ values(2, 'Tech', 'Tech Group');
insert into groups_ values(3, 'Legal', 'Legal Group');
insert into groups_ values(4, 'Newsfeed', 'Newsfeed');


insert into posts values(1, 'New Java Version', '#java', '1.jpg', null, 'New Java version just dropped', 3, SYSDATE(), true);
insert into posts values(2, 'Elon Musk Drama', '#crypto', '2.jpg', null, 'Elon Musk did thing and crypto price did something', 5, SYSDATE(), false);
insert into posts values(3, 'Company Now Hiring', '#job', '3.jpg', null, 'Company hires new people and gives them a lot of money', 2, SYSDATE(), false);
insert into posts values(4, 'New Taxation Law for IT', '#laws','4.jpg', null, 'Government just imposed the 10% tax to programmers too', 1, SYSDATE(), true);
insert into posts values(5, 'This is a post #1 in newsfeed', '#news', '5.jpg', null, 'This is the text from a post #1 in newsfeed', 6, SYSDATE(), true);
insert into posts values(6, 'This is a post #2 in newsfeed', '#news', '6.jpg', null, 'This is the text from a post #2 in newsfeed', 6, SYSDATE(), true);
insert into posts values(7, 'This is a post #3 in newsfeed', '#news', '7.jpg', null, 'This is the text from a post #3 in newsfeed', 6, SYSDATE(), true);
insert into posts values(8, 'This is a post #4 in newsfeed', '#news', '8.jpg', null, 'This is the text from a post #4 in newsfeed', 6, SYSDATE(), true);



insert into group_posts values(1, 2, 1);
insert into group_posts values(2, 2, 2);
insert into group_posts values(3, 1, 3);
insert into group_posts values(4, 3, 4);
insert into group_posts values(5, 4, 5);
insert into group_posts values(6, 4, 6);
insert into group_posts values(7, 4, 7);
insert into group_posts values(8, 4, 8);


insert into user_follow values(1, 1, 1);
insert into user_follow values(2, 1, 3);
insert into user_follow values(3, 2, 2);
insert into user_follow values(4, 2, 3);
insert into user_follow values(5, 3, 2);
insert into user_follow values(6, 4, 1);
insert into user_follow values(7, 4, 2);
insert into user_follow values(8, 4, 3);
insert into user_follow values(9, 5, 1);

insert into user_comment values(1, 1, 'so cool! can\'t wait for it');
insert into user_comment values(2, 2, 1, 'finally!');
insert into user_comment values(3, 5, 1, 'i\'ll still work in java 8 tho');
insert into user_comment values(4, 3, 3, 'i am going to apply for this');
insert into user_comment values(5, 4, 3, 'the life work balance is great here');
insert into user_comment values(6, 5, 4, 'but i love money :(');
insert into user_comment values(7, 2, 2, 'elon back at it');

insert into user_like values(1,1,1);
insert into user_like values(2,1,2);
insert into user_like values(3,2,2);
insert into user_like values(4,2,1);
insert into user_like values(5,2,3);
insert into user_like values(6,4,4);
insert into user_like values(7,5,1);
insert into user_like values(8,5,4);



