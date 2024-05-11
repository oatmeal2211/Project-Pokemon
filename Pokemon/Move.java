package Pokemon;

public class Move {
    private final String NAME;
    private final String TYPE;
    private final String CATEGORY;
    private int base_powerpoints;
    private int current_powerpoints;
    private int power;
    private int accuracy;

    public Move(String info){
        String[] stats = info.split(",");
        this.NAME = stats[1];
        this.TYPE = stats[2];
        this.CATEGORY = stats[3];
        this.base_powerpoints = Integer.parseInt(stats[4]);
        this.power = Integer.parseInt(stats[5]);
        this.accuracy = Integer.parseInt(stats[6]);
    }
}
