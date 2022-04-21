GRANT USAGE ON *.* TO `Client`@`localhost`
GRANT SELECT ON `yummymap`.`storeinfo` TO `Client`@`localhost`
GRANT USAGE ON *.* TO `Manager`@`localhost` IDENTIFIED BY PASSWORD '*66769EF1B56407DE561E7E09B995B32D0561BC7D'
GRANT SELECT, INSERT, UPDATE, DELETE, LOCK TABLES ON `yummymap`.* TO `Manager`@`localhost`
GRANT USAGE ON *.* TO `Store`@`localhost` IDENTIFIED BY PASSWORD '*B9DBF1AA32F9AC65EA08E5D9CE8798CFEE53772E'
GRANT SELECT, INSERT, UPDATE ON `yummymap`.`storeaccount` TO `Store`@`localhost`
GRANT SELECT, INSERT, UPDATE, DELETE ON `yummymap`.`storeinfo` TO `Store`@`localhost`
GRANT USAGE ON *.* TO `pma`@`localhost`
GRANT SELECT, INSERT, UPDATE, DELETE ON `phpmyadmin`.* TO `pma`@`localhost`
