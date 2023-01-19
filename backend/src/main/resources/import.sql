-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');


-- init achievements table
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Debugger', 'Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Self Sustained', 'Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Peace Was No Decision', 'Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Lucky Luke', 'Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'No Hurry', 'Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Firefighter', 'Solve x Bugs', 1);
insert into achievement (id, title, description, tier) values (nextval('hibernate_sequence'), 'Street', 'Solve x Bugs', 1);

insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'paul_anton_rainer', 'Paul Anton Rainer', 'prainer@intershop.com', 0);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'steven_heyder', 'Steven Heyder', 'sheyder@intershop.com', 0);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'sebastian_glass', 'Sebastian Glass', 'sglass@intershop.com', 200);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'sven_dibowski', 'Sven Dibowski', 'sdibowski@intershop.com', 0);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'ronny_wilms', 'Ronny Wilms', 'rwilms@intershop.com', 0);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'can_arici', 'Can Arici', 'can.arici@experts.com', 0);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'dilara_gueler', 'Dilara Güler', 'DGueler@intershop.com', 0);
insert into questUser (	id, username, display_name, email, experience_points) values (nextval('hibernate_sequence'), 'christian_linde', 'Christian Linde', 'clinde@intershop.com', 0);

insert into user_achievements (user_id , achievement_id) values (10, 2);
