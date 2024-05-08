public class Hospede extends Thread {
    public int groupId;
    private Integer id;

    public Hospede(Integer id) {
        this.id = id;
        this.groupId = groupId;
//        System.out.println("Hospede " + id + " criado!");
    }

    @Override
    public long getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }
}