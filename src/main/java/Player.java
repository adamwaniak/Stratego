public class Player {
    private int score;
    private Color color;

    public enum Color{
        RED, BLUE
    }

    public Player(Color color){
        this.color = color;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }

    public void addToScore(int addingScore){
        score += addingScore;
    }
}
