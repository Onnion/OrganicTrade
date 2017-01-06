package mpoo.bsi.ufrpe.organictrade.recomendationSystem;
public class ItemId {
    private String content;

    public ItemId(String s) {
        content = s;
    }

    public int hashCode() { return content.hashCode();}

    public String toString() { return content; }
}