package mpoo.bsi.ufrpe.organictrade.recommendationsystem;

public class UserId  {
    private String content;

    public UserId(String s) {
        content = s;
    }

    public int hashCode() { return content.hashCode();}

    public String toString() { return content; }
}
