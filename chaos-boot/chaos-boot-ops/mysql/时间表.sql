CREATE TABLE num (i INT);

INSERT INTO num (i)
VALUES
	(0),
	(1),
	(2),
	(3),
	(4),
	(5),
	(6),
	(7),
	(8),
	(9);

CREATE TABLE
IF NOT EXISTS calendar (datelist date);

INSERT INTO calendar (datelist) SELECT
	adddate(
		(
			-- 这里的起始日期，你可以换成当前日期
			DATE_FORMAT("2016-1-1", '%Y-%m-%d')
		),
		numlist.id
	) AS `date`
FROM
	(
		SELECT
			n1.i + n10.i * 10 + n100.i * 100 + n1000.i * 1000 + n10000.i * 10000 AS id
		FROM
			num n1
		CROSS JOIN num AS n10
		CROSS JOIN num AS n100
		CROSS JOIN num AS n1000
		CROSS JOIN num AS n10000
	) AS numlist;

