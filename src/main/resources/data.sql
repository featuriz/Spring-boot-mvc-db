--
-- 				| 1				| 2				| 3
-- USERNAME: 	| sudhakar		| user1			| user2
-- PASSWORD: 	| sudhakar		| user1Pass		| user2Pass
-- ROLE: 		| ROLE_ADMIN	| ROLE_USER		| ROLE_USER
--

--
INSERT INTO `users` (`user_id`, `active`, `email`, `last_name`, `name`, `password`, `user_name`) VALUES 
('1', b'1', 'featuriz@gmail.com', 'Krishnan', 'Sudhakar', '$2a$10$rKBUg5phQmvK2Bp98znkje1nHbBGfGI2u90/dvvzGCyl9XZ3hUeT2', 'sudhakar'),
('2', b'1', 'user1@example.com', 'User1', 'User1', '$2a$10$bQz9FAobaLN/jztqSR8UCeJQgS3mN.Uot0vYqMJ/LE9PS6xgTitqi', 'user1'),
('3', b'1', 'user2@example.com', 'User2', 'User2', '$2a$10$5YO3nXaHYnj4mERl2cVlJei7Ip6NmO0eclttZ.94GM.w4eRMbPkne', 'user2');
--
INSERT INTO `roles` (`role_id`, `role`) VALUES ('1', 'ROLE_ADMIN'), ('2', 'ROLE_USER');
--
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1'), ('2', '2'), ('3', '2');

--
--