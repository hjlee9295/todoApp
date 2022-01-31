INSERT INTO TODO(id, title, content) VALUES (1, 'aaaa', '1111');
INSERT INTO TODO(id, title, content) VALUES (2, 'bbbb', '2222');
INSERT INTO TODO(id, title, content) VALUES (3, 'cccc', '3333');

-- todo dummy data

INSERT INTO TODO(id, title, content) VALUES (4, 'java', 'master java');
INSERT INTO TODO(id, title, content) VALUES (5, 'python', 'master python');
INSERT INTO TODO(id, title, content) VALUES (6, 'javascript', 'master javascript');

-- comment dummy data

INSERT INTO comment(id, todo_id, nickname, body) VALUES (1, 4, 'hlee', 'good luck with Java');
INSERT INTO comment(id, todo_id, nickname, body) VALUES (2, 4, 'pkim', 'best luck with Java');
INSERT INTO comment(id, todo_id, nickname, body) VALUES (3, 4, 'jlee', 'wishing you a luck Java');

INSERT INTO comment(id, todo_id, nickname, body) VALUES (4, 5, 'hlee', 'good luck with Python');
INSERT INTO comment(id, todo_id, nickname, body) VALUES (5, 5, 'pkim', 'best luck with Python');
INSERT INTO comment(id, todo_id, nickname, body) VALUES (6, 5, 'jlee', 'wishing you a luck Python');

INSERT INTO comment(id, todo_id, nickname, body) VALUES (7, 6, 'hlee', 'good luck with Javascript');
INSERT INTO comment(id, todo_id, nickname, body) VALUES (8, 6, 'pkim', 'best luck with Javascript');
INSERT INTO comment(id, todo_id, nickname, body) VALUES (9, 6, 'jlee', 'wishing you a luck Javascript');