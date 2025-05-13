package site.backrer.decisiveBattle.Entity;

public class Scene {
    private int id;
    private int code;
    private int maxPlayerSize;
    private int minPlayerSize;
    private String sceneName;
    private double pos1_x;
    private double pos1_y;
    private double pos1_z;
    private double pos2_x;
    private double pos2_y;
    private double pos2_z;
    private String worldName;

    // 构造函数
    public Scene(int id, int code, int maxPlayerSize, int minPlayerSize, String sceneName,
                 double pos1_x, double pos1_y, double pos1_z,
                 double pos2_x, double pos2_y, double pos2_z, String worldName) {
        this.id = id;
        this.code = code;
        this.maxPlayerSize = maxPlayerSize;
        this.minPlayerSize = minPlayerSize;
        this.sceneName = sceneName;
        this.pos1_x = pos1_x;
        this.pos1_y = pos1_y;
        this.pos1_z = pos1_z;
        this.pos2_x = pos2_x;
        this.pos2_y = pos2_y;
        this.pos2_z = pos2_z;
        this.worldName = worldName;
    }

    // 不带ID的构造函数（用于插入时）
    public Scene(int code, int maxPlayerSize, int minPlayerSize, String sceneName,
                 double pos1_x, double pos1_y, double pos1_z,
                 double pos2_x, double pos2_y, double pos2_z, String worldName) {
        this.code = code;
        this.maxPlayerSize = maxPlayerSize;
        this.minPlayerSize = minPlayerSize;
        this.sceneName = sceneName;
        this.pos1_x = pos1_x;
        this.pos1_y = pos1_y;
        this.pos1_z = pos1_z;
        this.pos2_x = pos2_x;
        this.pos2_y = pos2_y;
        this.pos2_z = pos2_z;
        this.worldName = worldName;
    }

    // Getter方法
    public int getCode() { return code; }
    public int getMaxPlayerSize() { return maxPlayerSize; }
    public int getMinPlayerSize() { return minPlayerSize; }
    public String getSceneName() { return sceneName; }
    public double getPos1_x() { return pos1_x; }
    public double getPos1_y() { return pos1_y; }
    public double getPos1_z() { return pos1_z; }
    public double getPos2_x() { return pos2_x; }
    public double getPos2_y() { return pos2_y; }
    public double getPos2_z() { return pos2_z; }
    public String getWorldName() { return worldName; }
    public int getId() { return id; }

    public void setId(int id) {this.id = id;}
    public void setCode(int code) {this.code = code;}
    public void setMaxPlayerSize(int maxPlayerSize) {this.maxPlayerSize = maxPlayerSize;}
    public void setMinPlayerSize(int minPlayerSize) {this.minPlayerSize = minPlayerSize;}
    public void setSceneName(String sceneName) {this.sceneName = sceneName;}
    public void setPos1_x(double pos1_x) {this.pos1_x = pos1_x;}
    public void setPos1_y(double pos1_y) {this.pos1_y = pos1_y;}
    public void setPos1_z(double pos1_z) {this.pos1_z = pos1_z;}
    public void setPos2_x(double pos2_x) {this.pos2_x = pos2_x;}
    public void setPos2_y(double pos2_y) {this.pos2_y = pos2_y;}
    public void setPos2_z(double pos2_z) {this.pos2_z = pos2_z;}
    public void setWorldName(String worldName) {this.worldName = worldName;}

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", code=" + code +
                ", maxPlayerSize=" + maxPlayerSize +
                ", minPlayerSize=" + minPlayerSize +
                ", sceneName='" + sceneName + '\'' +
                ", pos1_x=" + pos1_x +
                ", pos1_y=" + pos1_y +
                ", pos1_z=" + pos1_z +
                ", pos2_x=" + pos2_x +
                ", pos2_y=" + pos2_y +
                ", pos2_z=" + pos2_z +
                ", worldName='" + worldName + '\'' +
                '}';
    }
}
