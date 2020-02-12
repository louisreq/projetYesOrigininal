package hei.devweb.traderz.entities;

import java.sql.Timestamp;

public class Capteur {
    private Integer id_capteur;
    private Integer id_rasberry;
    private Timestamp date_collected;
    private Float CO;
    private Float CO2;
    private Float LMG;
    private Float dust;
    private Float formol;
    private Float humid;
    private Integer light;
    private Integer sound;
    private Float temperature;
    private Float tolu;

    public Capteur(Integer id_capteur, Integer id_rasberry, Timestamp date_collected, Float CO, Float CO2, Float LMG, Float dust, Float formol, Float humid, Integer light, Integer sound, Float temperature, Float tolu) {
        this.id_capteur = id_capteur;
        this.id_rasberry = id_rasberry;
        this.date_collected = date_collected;
        this.CO = CO;
        this.CO2 = CO2;
        this.LMG = LMG;
        this.dust = dust;
        this.formol = formol;
        this.humid = humid;
        this.light = light;
        this.sound = sound;
        this.temperature = temperature;
        this.tolu = tolu;
    }

    public Integer getId_capteur() {
        return id_capteur;
    }

    public void setId_capteur(Integer id_capteur) {
        this.id_capteur = id_capteur;
    }

    public Integer getId_rasberry() {
        return id_rasberry;
    }

    public void setId_rasberry(Integer id_rasberry) {
        this.id_rasberry = id_rasberry;
    }

    public Timestamp getDate_collected() {
        return date_collected;
    }

    public void setDate_collected(Timestamp date_collected) {
        this.date_collected = date_collected;
    }

    public Float getCO() {
        return CO;
    }

    public void setCO(Float CO) {
        this.CO = CO;
    }

    public Float getCO2() {
        return CO2;
    }

    public void setCO2(Float CO2) {
        this.CO2 = CO2;
    }

    public Float getLMG() {
        return LMG;
    }

    public void setLMG(Float LMG) {
        this.LMG = LMG;
    }

    public Float getDust() {
        return dust;
    }

    public void setDust(Float dust) {
        this.dust = dust;
    }

    public Float getFormol() {
        return formol;
    }

    public void setFormol(Float formol) {
        this.formol = formol;
    }

    public Float getHumid() {
        return humid;
    }

    public void setHumid(Float humid) {
        this.humid = humid;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getTolu() {
        return tolu;
    }

    public void setTolu(Float tolu) {
        this.tolu = tolu;
    }
}
