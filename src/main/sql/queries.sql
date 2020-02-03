
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

