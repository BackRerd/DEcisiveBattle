package site.backrer.decisiveBattle.Entity;

public class ItemPackage {
    private int id;
    private String code;
    private String name;
    private String base64;
    private String probability;

    // 全参构造


    public ItemPackage(String code, String name, String base64, String probability) {
        this.code = code;
        this.name = name;
        this.base64 = base64;
        this.probability = probability;
    }

    public ItemPackage(int id, String code, String name, String base64, String probability) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.base64 = base64;
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getBase64() {
        return base64;
    }

    public String getProbability() {
        return probability;
    }
}

