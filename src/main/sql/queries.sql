
-- Donne toutes les salles avec le nom d'étage de batiment et du campus
SELECT
campus.nom_campus ,
batiment.nom_batiment,
etage.nom_etage,
salle.nom_salle


FROM campus
INNER JOIN batiment ON (campus.id = batiment.campus_id)
INNER JOIN etage ON (batiment.id = etage.batiment_id)
INNER JOIN salle ON (etage.id = salle.etage_id)
---------------------------------------------------------------------------------------------------------------------

-- Retourne les salles avec toutes les infos en fonction d'une string
-- Un exemple de "fuzzy matching"

SELECT
campus.nom_campus ,
batiment.nom_batiment,
etage.nom_etage,
salle.nom_salle


FROM campus
INNER JOIN batiment ON (campus.id = batiment.campus_id)
INNER JOIN etage ON (batiment.id = etage.batiment_id)
INNER JOIN salle ON (etage.id = salle.etage_id)
WHERE campus.nom_campus LIKE '%S%'
OR batiment.nom_batiment LIKE '%S%'
OR etage.nom_etage LIKE '%S%'
OR salle.nom_salle LIKE '%S%'

---------------------------------------------------------------------------------------------------------------------
-- Retourne les salles d'un Campus particulier en fonction d'une string
-- Un exemple de "fuzzy matching"
--     private Integer id;
--     private String salle_name;
--     private Integer etage_id;
--     private String etage_name;
--     private Integer batiment_id;
--     private String batiment_name;
--     private Integer campus_id;
SELECT
salle.id as id_salle,
salle.nom_salle as nom_salle,
etage.id as id_etage,
etage.nom_etage as nom_etage,
batiment.id as id_batiment,
batiment.nom_batiment as nom_batiment,
campus.id as id_campus


FROM campus
INNER JOIN batiment ON (campus.id = batiment.campus_id)
INNER JOIN etage ON (batiment.id = etage.batiment_id)
INNER JOIN salle ON (etage.id = salle.etage_id)
WHERE campus.id = 1
AND (
    batiment.nom_batiment LIKE '%S%'
    OR etage.nom_etage LIKE '%S%'
    OR salle.nom_salle LIKE '%S%'
    )
---------------------------------------------------------------------------------------------------------------------
--  Get list of all campus
SELECT
    campus.id as id_campus,
    campus.nom_campus as nom_campus,
    null as liste_salles

FROM campus
GROUP BY campus.id


---------------------------------------------------------------------------------------------------------------------


SELECT
salle.id as id_salle,
salle.nom_salle as nom_salle,
etage.id as id_etage,
etage.nom_etage as nom_etage,
batiment.id as id_batiment,
batiment.nom_batiment as nom_batiment,
campus.id as id_campus


FROM campus
INNER JOIN batiment ON (campus.id = batiment.campus_id)
INNER JOIN etage ON (batiment.id = etage.batiment_id)
INNER JOIN salle ON (etage.id = salle.etage_id)
WHERE campus.id = 4
AND (
 batiment.nom_batiment LIKE '%Toul%'
 OR etage.nom_etage LIKE '%Toul%'
 OR salle.nom_salle LIKE '%Toul%'
 )
 AND(
 batiment.nom_batiment LIKE '%T035%'
 OR etage.nom_etage LIKE '%T035%'
 OR salle.nom_salle LIKE '%T035%'
    )



-- SELECT toutes les salles ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle
-- Utile pour la liste déroulante dans les alertes

SELECT
    salle.id as id_salle,
    salle.nom_salle as nom_salle,
    etage.id as id_etage,
    etage.nom_etage as nom_etage,
    batiment.id as id_batiment,
    batiment.nom_batiment as nom_batiment,
    campus.id as id_campus,
    campus.nom_campus as nom_campus


FROM campus
INNER JOIN batiment ON (campus.id = batiment.campus_id)
INNER JOIN etage ON (batiment.id = etage.batiment_id)
INNER JOIN salle ON (etage.id = salle.etage_id)

ORDER BY nom_campus, nom_batiment, nom_etage, nom_salle

-- Get Campus by ID

SELECT
    campus.id,
    campus.nom_campus

FROM campus

WHERE campus.id = ?

-- Get all alertes from user id
select
	alerte.id as id_alerte,
    alerte.date as date_alerte,
    alerte.message as message,
    alerte.personne_id as id_personne,
    alerte.salle_id as id_salle

from alerte

where alerte.personne_id = 21

---
--- Requête SQL pour avoir les favoris d'une personne
---
SELECT
	uhf.id as id_favori,
    uhf.personne_id as personne_id,
    uhf.salle_id as salle_id,
    CASE
		WHEN 1 THEN 'Principale'
        ELSE 'Secondaire'
    END as principale_or_secondaire

FROM user_has_favoris uhf
INNER JOIN personne ON (personne.id = uhf.personne_id)
INNER JOIN salle ON (salle.id = uhf.personne_id)

Where uhf.personne_id = 3

-- Requête pour avoir toutes les salles qui ne sont pas dans les favoris d'une personne

SELECT

	s1.id as id_salle,
    s1.nom_salle as nom_salle,
    e1.id as id_etage,
    e1.nom_etage as nom_etage,
    b1.id as id_batiment,
    b1.nom_batiment as nom_batiment,
    c1.id as id_campus,
    c1.nom_campus as nom_campus
FROM salle as s1
INNER JOIN etage AS e1 ON (s1.etage_id = e1.id)
INNER JOIN batiment AS b1 ON (e1.batiment_id = b1.id)
INNER JOIN campus as c1 ON (b1.campus_id = c1.id)

WHERE c1.id = 1
AND NOT EXISTS(
	SELECT

		s2.id as id_salle

	FROM user_has_favoris as uhf
	INNER JOIN personne AS p2 ON (uhf.personne_id = p2.id)
	INNER JOIN salle AS s2 ON (uhf.salle_id = s2.id)
	INNER JOIN etage AS e2 ON (s2.etage_id = e2.id)
	INNER JOIN batiment AS b2 ON (e2.batiment_id = b2.id)
	INNER JOIN campus AS c2 ON (b2.campus_id = c2.id)
	WHERE p2.id = 3
	AND c2.id = 1
    AND s1.id = s2.id
)




SELECT
    *
FROM sensors s
INNER JOIN capteur c ON (s.id_rasberry = c.id)
INNER JOIN capteur_salle cs ON (c.nom_capteur = cs.nom_capteur AND cs.date_debut < dateheurFIN AND (isnull(cs.date_fin) OR cs.date_fin > dateheureDEBUT) )

WHERE cs.salle_id = id_de_notre_salle



select
	p.email,
    e.age,
    e.commune,
    e.date_creation,
    e.diplome,
    e.domaine,
    e.parent,
    e.sexe,
    e.situation,
    e.mot1, e.mot2, e.mot3,
    info.actions_publiques_ameliorer_qualite_air,
    info.aeration_logement,
    info.effet_pollution_air_sante,
    info.eviter_trafic_velo,
    info.frequence_aeration_logement,
    info.impact_air_pollue_organe,
    info.impact_sante,
    info.moyens_air_sain_exter,
    info.moyens_air_sain_inter,
    info.pics_pollution,
    info.pollution,
    info.precautions_pic_pollution,
    info.prev_quotidien_q_air_ext,
    info.q_air_general,
    info.q_air_quartier,
    info.recommandation_proteger_pollution_quotidienne,
    info.remarques,
    info.saison_pollue,
    info.sources_pollution_air_exter,
    info.sources_pollution_air_inter,
    info.sport,
    info.sport_route_trafic,
    ss.air_agreable,
    ss.air_sensation,
    ss.capteur_temp,
    ss.climat_salle,
    ss.dist_fenetre,
    ss.dist_ventilo,
    ss.odeur,
    ss.poussiere,
    ss.q_air_hei,
    ss.q_air_salle,
    ss.symptomes,
    ss.temp_sensation

from personne p
inner join enquete e ON (p.id = e.id_user)
inner join info_sensibilisation info ON (p.id = info.id_user AND e.date_creation = info.date_creation)
inner join sensation_salles ss ON (p.id = ss.id_user AND info.date_creation = ss.date_creation)


