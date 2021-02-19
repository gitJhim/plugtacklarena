package me.Jhim.TacklArena.Profile.KillTexts;

public class KillText {

    private String title;
    private String text;
    private String permission;
    private boolean is_Public;
    private int price;

    public KillText(String title, String text, String permission, boolean is_Public, int price) {
        this.title = title;
        this.text = text;
        this.permission = permission;
        this.is_Public = is_Public;
        this.price = price;
    }

    public String getTitle() { return title; }

    public String getText() { return text; }

    public String getPermission() { return permission; }

    public boolean isPublic() { return is_Public; }

    public int getPrice() { return price; }

}
