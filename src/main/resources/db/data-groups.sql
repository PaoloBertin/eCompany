-- Create the Groups
INSERT INTO groups(group_name) values ('Users');
INSERT INTO groups(group_name) values ('Administrators');

-- Map the Groups to Roles
INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' FROM groups WHERE group_name='Users';
-- Administrators are both a ROLE_USER and ROLE_ADMIN
INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_USER' FROM groups WHERE group_name='Administrators';
INSERT INTO group_authorities(group_id, authority) SELECT id,'ROLE_ADMIN' FROM groups WHERE group_name='Administrators';

-- Map the users to Groups
INSERT INTO group_members(group_id, username) SELECT id,'paolo.bertin' FROM groups WHERE group_name='Administrators';
INSERT INTO group_members(group_id, username) SELECT id,'mario.rossi' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.verdi' FROM groups WHERE group_name='Users';
INSERT INTO group_members(group_id, username) SELECT id,'giuseppe.garibaldi' FROM groups WHERE group_name='Users';

