package web.app.api.objects;

public class Playlist {
    int id;
    String description;
    boolean isPublic;
    String name;
    int userId;

    public Playlist(String description, boolean isPublic, String name, int userId) {
        this.description = description;
        this.isPublic = isPublic;
        this.name = name;
        this.userId = userId;
    }

    public Playlist(int id, String description, boolean isPublic, String name, int userId) {
        this.id = id;
        this.description = description;
        this.isPublic = isPublic;
        this.name = name;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
