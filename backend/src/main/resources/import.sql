-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');


-- init achievements table
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Debugger', 'Bugs fear ME!! Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Self Sustained', 'Improvise. Adapt. Overcome. Solve x Bugs created by yourself', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Peace Was No Decision', 'Solve x Bugs, while having more than two open quests ', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Lucky Luke', 'Faster than your own shadow, Solve x Bugs needing less time than your average', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'No Hurry', 'Slow and steady wins the race, Solve x Bugs needing more time than your average', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Firefighter','Throwing yourself in the fire to save the day. Solve x major or higher level Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Street', 'Now I have seen everything! Solve Bugs of each Level, x sets. ', 1);

insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'paul_anton_rainer', 'Paul Anton Rainer', 'prainer@intershop.com', 65);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'steven_heyder', 'Steven Heyder', 'sheyder@intershop.com',100);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'sebastian_glass', 'Sebastian Glass', 'sglass@intershop.com', 250);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'sven_dibowski', 'Sven Dibowski', 'sdibowski@intershop.com', 124);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'ronny_wilms', 'Ronny Wilms', 'rwilms@intershop.com', 225);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'can_arici', 'Can Arici', 'can.arici@xim.ag', 24);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'dilara_gueler', 'Dilara Güler', 'DGueler@intershop.com', 55);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'christian_linde', 'Christian Linde', 'clinde@intershop.com', 169);

insert into user_achievements (user_id , achievement_id) values (8, 1);
insert into user_achievements (user_id , achievement_id) values (9, 1);
insert into user_achievements (user_id , achievement_id) values (9, 2);
insert into user_achievements (user_id , achievement_id) values (9, 4);
insert into user_achievements (user_id , achievement_id) values (9, 6);
insert into user_achievements (user_id , achievement_id) values (10, 1);
insert into user_achievements (user_id , achievement_id) values (10, 2);
insert into user_achievements (user_id , achievement_id) values (10, 3);
insert into user_achievements (user_id , achievement_id) values (10, 4);
insert into user_achievements (user_id , achievement_id) values (10, 6);
insert into user_achievements (user_id , achievement_id) values (11, 1);
insert into user_achievements (user_id , achievement_id) values (12, 1);
insert into user_achievements (user_id , achievement_id) values (12, 3);
insert into user_achievements (user_id , achievement_id) values (12, 6);
insert into user_achievements (user_id , achievement_id) values (13, 1);
insert into user_achievements (user_id , achievement_id) values (13, 5);
insert into user_achievements (user_id , achievement_id) values (14, 1);
insert into user_achievements (user_id , achievement_id) values (15, 1);


