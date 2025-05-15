package site.backrer.decisiveBattle.Entity;

public class SceneItemBinding {
    private int id;
    private int sceneId;
    private String itemPackageCode;

    public SceneItemBinding(int id, int sceneId, String itemPackageCode) {
        this.id = id;
        this.sceneId = sceneId;
        this.itemPackageCode = itemPackageCode;
    }

    // Getters & Setters ...

    public int getId() {
        return id;
    }

    public int getSceneId() {
        return sceneId;
    }

    public String getItemPackageCode() {
        return itemPackageCode;
    }
}

