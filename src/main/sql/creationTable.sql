CREATE TABLE `utilisateurs`(
	`user_id` int(20) NOT NULL AUTO_INCREMENT,
	`user_prenom` varchar(30) NOT NULL,
	`user_nom` varchar(30) NOT NULL,
	`user_pseudo` varchar(30) NOT NULL,
	`user_password` varchar(20) NOT NULL,
	`user_mail` varchar(30) NOT NULL,
	`user_date_birth` date NOT NULL,
	`user_sex` varchar(30) NOT NULL,
	`user_liquidites` double NOT NULL,
	`user_valeur` double NOT NULL,
	PRIMARY KEY (`user_id`)
);

CREATE TABLE `administrateurs`(
	`admin_id` int(20) NOT NULL AUTO_INCREMENT,
	`admin_nom` varchar(30) NOT NULL,
	PRIMARY KEY (`admin_id`)
);

CREATE TABLE `transactions`(
	`transac_id` int(20) NOT NULL AUTO_INCREMENT,
	`transac_user_pseudo` VARCHAR(30) NOT NULL ,
	`transac_volume` DOUBLE NOT NULL ,
	`transac_cotation_categorie` VARCHAR(30) NOT NULL ,
	`transac_cotation_id`int(20)NOT NULL ,
	`transac_cotation_prix` DOUBLE NOT NULL ,
	`transac_cotation_nom` VARCHAR(30) NOT NULL ,
	`transac_sens` BOOLEAN,
	PRIMARY KEY (`transac_id`)
);
CREATE TABLE `historiques`(
	`histo_id` int(20) NOT NULL AUTO_INCREMENT,
	`histo_user_pseudo` VARCHAR(30) NOT NULL ,
	`histo_volume` DOUBLE NOT NULL ,
	`histo_cotation_categorie` VARCHAR(30) NOT NULL ,
	`histo_cotation_id`int(20)NOT NULL ,
	`histo_cotation_prix` DOUBLE NOT NULL ,
	`histo_cotation_nom` VARCHAR(30) NOT NULL ,
	`histo_sens` BOOLEAN,
	PRIMARY KEY (`histo_id`)
);

CREATE TABLE `cotations`(
	`cotation_id`int(20) NOT NULL AUTO_INCREMENT,
	`cotation_categorie`  VARCHAR(30) NOT NULL ,
	`cotation_nom`  VARCHAR(30) NOT NULL ,
	`cotation_prix` DOUBLE NOT NULL ,
	`cotation_haut`DOUBLE NOT NULL ,
	`cotation_bas` DOUBLE NOT NULL ,
	`cotation_varjour` DOUBLE NOT NULL ,
	`cotation_veille` DOUBLE NOT NULL ,
	`cotation_ouverture` DOUBLE NOT NULL ,
	`cotation_volume` int(20) NOT NULL,
	PRIMARY KEY (`cotation_id`)
);


INSERT INTO `utilisateurs` (`user_id`,`user_prenom`,`user_nom`,`user_pseudo`,`user_password`,`user_mail`,`user_date_birth`,`user_sex`,`user_liquidites`,`user_valeur`)
	VALUE (1,'Tom','Test','TomCat','root' ,'tom@hei.yncrea.fr',20181212,'M',55001,815243228);

INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'Alten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'Blten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'Clten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'Dlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'AAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'FAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'HAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'JAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'LAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'NAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'PAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'RAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'TAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'VAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)
	VALUE (null, 'MP', 'XAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246);
	

