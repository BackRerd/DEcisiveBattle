package site.backrer.decisiveBattle.Entity;

public class Scene {
    private int id;
    private String code;
    private int maxPlayerSize;
    private int minPlayerSize;
    private String sceneName;
    private double pos1;
    private double pos2;
    private String worldName;

    public Scene(int id, String code, int maxPlayerSize, int minPlayerSize, String sceneName, double pos1, double pos2, String worldName) {
        this.id = id;
        this.code = code;
        this.maxPlayerSize = maxPlayerSize;
        this.minPlayerSize = minPlayerSize;
        this.sceneName = sceneName;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.worldName = worldName;
    }

    public String getCode() { return code; }
    public int getMaxPlayerSize() { return maxPlayerSize; }
    public int getMinPlayerSize() { return minPlayerSize; }
    public String getSceneName() { return sceneName; }
    public double getPos1() { return pos1; }
    public double getPos2() { return pos2; }
    public String getWorldName() { return worldName; }
    public int getId() { return id; }
}

