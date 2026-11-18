package org.example.model;

public class PadelPlayer {
    private final Integer id;
    private final String name;
    private final Integer age;
    private final Double level;
    private final String club;
    private final Integer padelBatId;

    public PadelPlayer(Integer id, String name, Integer age, Double level, String club, Integer padelBatId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.level = level;
        this.club = club;
        this.padelBatId = padelBatId;
    }

    public PadelPlayer(String name, Integer age, Double level, String club, Integer padelBatId) {
        this.id = null;
        this.name = name;
        this.age = age;
        this.level = level;
        this.club = club;
        this.padelBatId = padelBatId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getLevel() {
        return level;
    }

    public String getClub() {
        return club;
    }

    public Integer getPadelBatId() {
        return padelBatId;
    }

    @Override
    public String toString() {
        return "PadelPlayer[id=" + id + ", name=" + name + ", age=" + age + ", level=" + level + ", club=" + club + ", padelBatId=" + padelBatId + "]";
    }
}
