public class PokemonTeam {

    private Integer id;
    private String gameProgressId;
    private String name;
    private Integer level;
    private Integer experiencePoints;


    public PokemonTeam() {
    }

    public PokemonTeam(String gameProgressId, String name, Integer level, Integer experiencePoints) {
        this.gameProgressId = gameProgressId;
        this.name = name;
        this.level = level;
        this.experiencePoints = experiencePoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGameProgressId() {
        return gameProgressId;
    }

    public void setGameProgressId(String gameProgressId) {
        this.gameProgressId = gameProgressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

}
