INSERT INTO users (user_id, username, email, password, enabled) VALUES (1001, 'Joe', 'joe@example.com', '456', true);
INSERT INTO users (user_id, username, email, password, enabled) VALUES (1002, 'Maggie', 'maggie@example.com', '123', true);
INSERT INTO users (user_id, username, email, password, enabled) VALUES (1003, 'Kirk', 'kirk@example.com', '456', false);
INSERT INTO users (user_id, username, email, password, enabled) VALUES (1004, 'Fiona', 'fiona@example.com', '456', false);
INSERT INTO users (user_id, username, email, password, enabled) VALUES (1005, 'Mike', 'mike@example.com', '456', true);

INSERT INTO user_roles (user_user_id, role) VALUES (1001, 'ADMIN');
INSERT INTO user_roles (user_user_id, role) VALUES (1001, 'MODERATOR');
INSERT INTO user_roles (user_user_id, role) VALUES (1002, 'USER');
INSERT INTO user_roles (user_user_id, role) VALUES (1003, 'USER');
INSERT INTO user_roles (user_user_id, role) VALUES (1004, 'USER');
INSERT INTO user_roles (user_user_id, role) VALUES (1005, 'USER');
