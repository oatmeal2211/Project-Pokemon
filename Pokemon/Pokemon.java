package Pokemon;

public abstract class Pokemon {
    private final String NAME;
    private int level;
    private int currentexp;
    private final String TYPE1;
    private final String TYPE2;
    private int attack;
    private int defense;
    private int currenthp;
    private int maxhp;
    private int sp_attack;
    private int sp_defense;
    private int speed;
    private int threshold;

    public Pokemon(String info){
        String[] stats = info.split(",");
        this.NAME = stats[0];
        this.level = Integer.parseInt(stats[1]);
        this.currentexp = Integer.parseInt(stats[2]);
        this.TYPE1 = stats[3];
        this.TYPE2 = stats[4];
        this.attack = Integer.parseInt(stats[5]);
        this.defense = Integer.parseInt(stats[6]);
        this.currenthp = Integer.parseInt(stats[7]);
        this.maxhp = Integer.parseInt(stats[8]);
    }

    public abstract void advantage();
    public abstract void disadvantage();

    public boolean checkLvlUp(){
        if(this.level <= 10){
            this.threshold = 100;
            if(currentexp >= this.threshold){
                currentexp -= this.threshold;
                return true;
            }
            return false;
        }
        else if(this.level <= 30){
            this.threshold = 200;
            if(currentexp >= this.threshold){
                currentexp -= this.threshold;
                return true;
            }
            return false;
        }
        else{
            this.threshold = 300;
            if(currentexp >= this.threshold){
                currentexp -= this.threshold;
                return true;
            }
            return false;
        }
    }

    
}
