-- Add default user (username: user, password: password)
insert into users (username, password, enabled)
values('user', '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e', 1);

insert into authorities (username, authority)
values('user', 'ROLE_USER');

insert into authorities (username, authority)
values('user', 'ROLE_ADMIN');

-- Add todo entries
insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10001,'user', 'Get AWS Certified', date('now'), 0);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10002,'user', 'Get Azure Certified', date('now'), 0);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10003,'user', 'Get GCP Certified', date('now'), 0);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
values(10004,'user', 'Learn DevOps', date('now'), 0);