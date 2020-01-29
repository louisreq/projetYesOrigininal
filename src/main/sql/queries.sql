
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

