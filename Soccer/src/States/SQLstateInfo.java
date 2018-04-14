package States;

/**
 * Holds current selected values
 */
public class SQLstateInfo {
    private String tournament = null;
    private String game = null;
    private String player = null;
    private String team = null;

    public SQLstateInfo deepCopy(){
        SQLstateInfo copy = new SQLstateInfo();
        copy.tournament = this.tournament;
        copy.game = this.game;
        copy.player = this.player;
        copy.team = this.team;
        return copy;
    }

    public String getTournament() {
        assert (tournament != null);
        return tournament;
    }

    public String getGame() {
        assert (game != null);
        return game;
    }

    public String getPlayer() {
        assert (player != null);
        return player;
    }

    public String getTeam() {
        assert (team != null);
        return team;
    }

    public void setTournament(String tournament){
        this.tournament = tournament;
    }

    public void setGame(String game){
        this.game = game;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public void setTeam(String team){
        this.team = team;
    }
}
