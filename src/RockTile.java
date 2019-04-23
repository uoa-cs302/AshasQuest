public class RockTile extends Tile {



    public RockTile(int id) {

        //The id passed in will be used to identify what kind of tile it is.
        super(Assets.stone, id);

    }



    @Override

    //Below means that it can be collided with.
    public boolean isSolid(){

        return true;

    }



}