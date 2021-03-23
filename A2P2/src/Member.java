
public class Member {

    private String name;
    private int id;
    private String type;
    static int currMemberIndex;

    public Member() {
    }

    public Member(String name, int id, String type) {
        this.name = name;
        this.id = id;
        this.type = type;
        currMemberIndex++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static int getCurrMemberIndex() {
        return currMemberIndex;
    }

}
