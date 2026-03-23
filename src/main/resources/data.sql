-- Transactions from LAST MONTH (Should NOT be included in "Total Spent This Month")
INSERT INTO TRANSACTION (id, description, amount, category, date)
VALUES (RANDOM_UUID(), 'Rent - February', 1200.00, 1, '2026-02-01 10:00:00');

INSERT INTO TRANSACTION (id, description, amount, category, date)
VALUES (RANDOM_UUID(), 'Old Grocery Trip', 55.50, 0, '2026-02-15 14:30:00');

-- Transactions from THIS MONTH (Should BE included)
INSERT INTO TRANSACTION (id, description, amount, category, date)
VALUES (RANDOM_UUID(), 'Gym Membership', 45.00, 2, '2026-03-05 08:00:00');

INSERT INTO TRANSACTION (id, description, amount, category, date)
VALUES (RANDOM_UUID(), 'Dinner Out', 80.25, 3, '2026-03-20 19:00:00');

INSERT INTO TRANSACTION (id, description, amount, category, date)
VALUES (RANDOM_UUID(), 'Gas/Fuel', 60.00, 5, '2026-03-22 12:00:00');