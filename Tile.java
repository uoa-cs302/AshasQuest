import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;

public class Tile {
    private final int x;
    private final int y;
    private BufferedImage sprite = null;

    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    private static boolean maps_initialised = false;

    public enum HometownTerrain {
        // format is row, col, range, 
        // where the range is the grid rows between col + range - 1
        GRASS               (1, 1, 8, "hometown"), 
        DIRT                (2, 1, 5, "hometown"), 
        LEFT_EDGE           (2, 18, 1, "hometown"),
        RIGHT_EDGE          (2, 16, 1, "hometown"),
        TOP_EDGE            (3, 17, 1, "hometown"),
        BOTTOM_EDGE         (1, 17, 1, "hometown"),
        TOP_LEFT_CORNER     (1, 19, 1, "hometown"),
        TOP_RIGHT_CORNER    (1, 20, 1, "hometown"),
        BOTTOM_LEFT_CORNER  (2, 19, 1, "hometown"),
        BOTTOM_RIGHT_CORNER (2, 20, 1, "hometown"),
        TOP_LEFT_INSIDE     (3, 18, 1, "hometown"),
        TOP_RIGHT_INSIDE    (3, 16, 1, "hometown"),
        BOTTOM_LEFT_INSIDE  (1, 18, 1, "hometown"),
        BOTTOM_RIGHT_INSIDE (1, 16, 1, "hometown");

        private final int row;
        private final int col;
        private final int range;
        private final String filename;
        private final int tile_size;

        private HometownTerrain(int row, int col, int range, String filename) {
            this.row = row;
            this.col = col;
            this.range = range;
            this.filename = filename;
            if (filename.equals("boss")) {
                this.tile_size = 32;
            }
            else {
                this.tile_size = 16;
            }
        }
    }

    public enum AshlandsTerrain {
        // format is row, col, range, 
        // where the range is the grid rows between col + range - 1
        ASH                 (1, 2, 7, "castle"), 
        DIRT                (2, 1, 6, "castle"), 
        LEFT_EDGE           (8, 4, 1, "castle"),
        RIGHT_EDGE          (8, 6, 1, "castle"),
        TOP_EDGE            (7, 5, 1, "castle"),
        BOTTOM_EDGE         (9, 5, 1, "castle"),
        TOP_LEFT_CORNER     (7, 4, 1, "castle"),
        TOP_RIGHT_CORNER    (7, 6, 1, "castle"),
        BOTTOM_LEFT_CORNER  (9, 4, 1, "castle"),
        BOTTOM_RIGHT_CORNER (9, 6, 1, "castle"),
        TOP_LEFT_INSIDE     (7, 7, 1, "castle"),
        TOP_RIGHT_INSIDE    (7, 8, 1, "castle"),
        BOTTOM_LEFT_INSIDE  (8, 7, 1, "castle"),
        BOTTOM_RIGHT_INSIDE (8, 8, 1, "castle");

        private final int row;
        private final int col;
        private final int range;
        private final String filename;
        private final int tile_size;

        private AshlandsTerrain(int row, int col, int range, String filename) {
            this.row = row;
            this.col = col;
            this.range = range;
            this.filename = filename;
            if (filename.equals("boss")) {
                this.tile_size = 32;
            }
            else {
                this.tile_size = 16;
            }
        }
    }

    public enum CastleTerrain {
        // format is row, col, range, 
        // where the range is the grid rows between col + range - 1
        // NAME             (row, col, range, spritesheet)
        TOP_LEFT_CORNER         (1, 10, 1, "castle"),
        TOP_EDGE                (1, 11, 1, "castle"),
        TOP_RIGHT_CORNER        (1, 12, 1, "castle"),
        LEFT_EDGE               (2, 10, 1, "castle"),
        FLOOR                   (2, 11, 1, "castle"), 
        RIGHT_EDGE              (2, 12, 1, "castle"),
        BOTTOM_LEFT_CORNER      (3, 10, 1, "castle"),
        BOTTOM_EDGE             (3, 11, 1, "castle"),
        BOTTOM_RIGHT_CORNER     (3, 12, 1, "castle"),

        TOP_LEFT_INSIDE         (2, 15, 1, "castle"),
        TOP_RIGHT_INSIDE        (2, 14, 1, "castle"),
        BOTTOM_LEFT_INSIDE      (1, 15, 1, "castle"),
        BOTTOM_RIGHT_INSIDE     (1, 14, 1, "castle"),

        WALL                    (4, 10, 1, "castle");

        private final int row;
        private final int col;
        private final int range;
        private final String filename;
        private final int tile_size;

        private CastleTerrain(int row, int col, int range, String filename) {
            this.row = row;
            this.col = col;
            this.range = range;
            this.filename = filename;
            if (filename.equals("boss")) {
                this.tile_size = 32;
            }
            else {
                this.tile_size = 16;
            }
        }
    }
    
    public enum BossTerrain {
        // format is row, col, range, 
        // where the range is the grid rows between col + range - 1
        // NAME             (row, col, range, spritesheet)
        MAT_TOP_LEFT_CORNER     (2, 3, 1, "boss"), 
        MAT_TOP_EDGE            (2, 4, 1, "boss"), 
        MAT_TOP_RIGHT_CORNER    (2, 5, 1, "boss"), 
        MAT_LEFT_EDGE           (3, 3, 1, "boss"), 
        MAT                     (3, 4, 1, "boss"), 
        MAT_RIGHT_EDGE          (3, 5, 1, "boss"), 
        MAT_BOTTOM_LEFT_CORNER  (4, 3, 1, "boss"), 
        MAT_BOTTOM_EDGE         (4, 4, 1, "boss"), 
        MAT_BOTTOM_RIGHT_CORNER (4, 5, 1, "boss"), 

        TOP_LEFT_CORNER         (0, 0, 1, "boss"),
        TOP_EDGE                (0, 1, 1, "boss"),
        TOP_RIGHT_CORNER        (0, 2, 1, "boss"),
        LEFT_EDGE               (1, 0, 1, "boss"),
        FLOOR                   (1, 1, 1, "boss"), 
        RIGHT_EDGE              (1, 2, 1, "boss"),
        BOTTOM_LEFT_CORNER      (2, 0, 1, "boss"),
        BOTTOM_EDGE             (2, 1, 1, "boss"),
        BOTTOM_RIGHT_CORNER     (2, 2, 1, "boss"),

        TOP_LEFT_INSIDE         (1, 4, 1, "boss"),
        TOP_RIGHT_INSIDE        (1, 3, 1, "boss"),
        BOTTOM_LEFT_INSIDE      (0, 4, 1, "boss"),
        BOTTOM_RIGHT_INSIDE     (0, 3, 1, "boss"),

        BLACK                   (3, 6, 1, "boss");

        private final int row;
        private final int col;
        private final int range;
        private final String filename;
        private final int tile_size;

        private BossTerrain(int row, int col, int range, String filename) {
            this.row = row;
            this.col = col;
            this.range = range;
            this.filename = filename;
            if (filename.equals("boss")) {
                this.tile_size = 32;
            }
            else {
                this.tile_size = 16;
            }
        }
    }

    private static final HashMap<String, BossTerrain> BOSS_HASH_MAP = new HashMap<>();
    private static final HashMap<String, AshlandsTerrain> ASHLANDS_HASH_MAP = new HashMap<>();
    private static final HashMap<String, CastleTerrain> CASTLE_HASH_MAP = new HashMap<>();
    private static final HashMap<String, HometownTerrain> HOMETOWN_HASH_MAP = new HashMap<>();

    private static void initialise_maps() {
        maps_initialised = true;

        HOMETOWN_HASH_MAP.put("0", HometownTerrain.GRASS);
        HOMETOWN_HASH_MAP.put("1", HometownTerrain.TOP_LEFT_CORNER);
        HOMETOWN_HASH_MAP.put("2", HometownTerrain.TOP_EDGE);
        HOMETOWN_HASH_MAP.put("3", HometownTerrain.TOP_RIGHT_CORNER);
        HOMETOWN_HASH_MAP.put("4", HometownTerrain.LEFT_EDGE);
        HOMETOWN_HASH_MAP.put("5", HometownTerrain.RIGHT_EDGE);
        HOMETOWN_HASH_MAP.put("6", HometownTerrain.BOTTOM_LEFT_CORNER);
        HOMETOWN_HASH_MAP.put("7", HometownTerrain.BOTTOM_EDGE);
        HOMETOWN_HASH_MAP.put("8", HometownTerrain.BOTTOM_RIGHT_CORNER);
        HOMETOWN_HASH_MAP.put("9", HometownTerrain.DIRT);
        HOMETOWN_HASH_MAP.put("A", HometownTerrain.TOP_RIGHT_INSIDE);
        HOMETOWN_HASH_MAP.put("B", HometownTerrain.TOP_LEFT_INSIDE);
        HOMETOWN_HASH_MAP.put("C", HometownTerrain.BOTTOM_RIGHT_INSIDE);
        HOMETOWN_HASH_MAP.put("D", HometownTerrain.BOTTOM_LEFT_INSIDE);

        ASHLANDS_HASH_MAP.put("0", AshlandsTerrain.ASH );
        ASHLANDS_HASH_MAP.put("1", AshlandsTerrain.TOP_LEFT_CORNER);
        ASHLANDS_HASH_MAP.put("2", AshlandsTerrain.TOP_EDGE);
        ASHLANDS_HASH_MAP.put("3", AshlandsTerrain.TOP_RIGHT_CORNER);
        ASHLANDS_HASH_MAP.put("4", AshlandsTerrain.LEFT_EDGE);
        ASHLANDS_HASH_MAP.put("5", AshlandsTerrain.RIGHT_EDGE);
        ASHLANDS_HASH_MAP.put("6", AshlandsTerrain.BOTTOM_LEFT_CORNER);
        ASHLANDS_HASH_MAP.put("7", AshlandsTerrain.BOTTOM_EDGE);
        ASHLANDS_HASH_MAP.put("8", AshlandsTerrain.BOTTOM_RIGHT_CORNER);
        ASHLANDS_HASH_MAP.put("9", AshlandsTerrain.DIRT);
        ASHLANDS_HASH_MAP.put("A", AshlandsTerrain.TOP_RIGHT_INSIDE);
        ASHLANDS_HASH_MAP.put("B", AshlandsTerrain.TOP_LEFT_INSIDE);
        ASHLANDS_HASH_MAP.put("C", AshlandsTerrain.BOTTOM_RIGHT_INSIDE);
        ASHLANDS_HASH_MAP.put("D", AshlandsTerrain.BOTTOM_LEFT_INSIDE);

        CASTLE_HASH_MAP.put("0", CastleTerrain.FLOOR );
        CASTLE_HASH_MAP.put("1", CastleTerrain.TOP_LEFT_CORNER);
        CASTLE_HASH_MAP.put("2", CastleTerrain.TOP_EDGE);
        CASTLE_HASH_MAP.put("3", CastleTerrain.TOP_RIGHT_CORNER);
        CASTLE_HASH_MAP.put("4", CastleTerrain.LEFT_EDGE);
        CASTLE_HASH_MAP.put("5", CastleTerrain.RIGHT_EDGE);
        CASTLE_HASH_MAP.put("6", CastleTerrain.BOTTOM_LEFT_CORNER);
        CASTLE_HASH_MAP.put("7", CastleTerrain.BOTTOM_EDGE);
        CASTLE_HASH_MAP.put("8", CastleTerrain.BOTTOM_RIGHT_CORNER);
        CASTLE_HASH_MAP.put("9", CastleTerrain.WALL);
        CASTLE_HASH_MAP.put("A", CastleTerrain.TOP_RIGHT_INSIDE);
        CASTLE_HASH_MAP.put("B", CastleTerrain.TOP_LEFT_INSIDE);
        CASTLE_HASH_MAP.put("C", CastleTerrain.BOTTOM_RIGHT_INSIDE);
        CASTLE_HASH_MAP.put("D", CastleTerrain.BOTTOM_LEFT_INSIDE);

        BOSS_HASH_MAP.put("0", BossTerrain.FLOOR);
        BOSS_HASH_MAP.put("1", BossTerrain.TOP_LEFT_CORNER);
        BOSS_HASH_MAP.put("2", BossTerrain.TOP_EDGE);
        BOSS_HASH_MAP.put("3", BossTerrain.TOP_RIGHT_CORNER);
        BOSS_HASH_MAP.put("4", BossTerrain.LEFT_EDGE);
        BOSS_HASH_MAP.put("5", BossTerrain.RIGHT_EDGE);
        BOSS_HASH_MAP.put("6", BossTerrain.BOTTOM_LEFT_CORNER);
        BOSS_HASH_MAP.put("7", BossTerrain.BOTTOM_EDGE);
        BOSS_HASH_MAP.put("8", BossTerrain.BOTTOM_RIGHT_CORNER);
        BOSS_HASH_MAP.put("9", BossTerrain.BLACK);
        BOSS_HASH_MAP.put("A", BossTerrain.TOP_RIGHT_INSIDE);
        BOSS_HASH_MAP.put("B", BossTerrain.TOP_LEFT_INSIDE);
        BOSS_HASH_MAP.put("C", BossTerrain.BOTTOM_RIGHT_INSIDE);
        BOSS_HASH_MAP.put("D", BossTerrain.BOTTOM_LEFT_INSIDE);
        BOSS_HASH_MAP.put("E", BossTerrain.MAT_TOP_LEFT_CORNER);
        BOSS_HASH_MAP.put("F", BossTerrain.MAT_TOP_EDGE);
        BOSS_HASH_MAP.put("G", BossTerrain.MAT_TOP_RIGHT_CORNER);
        BOSS_HASH_MAP.put("H", BossTerrain.MAT_LEFT_EDGE);
        BOSS_HASH_MAP.put("I", BossTerrain.MAT);
        BOSS_HASH_MAP.put("J", BossTerrain.MAT_RIGHT_EDGE);
        BOSS_HASH_MAP.put("K", BossTerrain.MAT_BOTTOM_LEFT_CORNER);
        BOSS_HASH_MAP.put("L", BossTerrain.MAT_BOTTOM_EDGE);
        BOSS_HASH_MAP.put("M", BossTerrain.MAT_BOTTOM_RIGHT_CORNER);
    }

    public Tile(int x, int y, int room, String tile) {
        if (!maps_initialised) { initialise_maps(); }

        this.x = x;
        this.y = y;
        
        switch (room) {
            // chooses terrain based on room number
            case 1: {
                HometownTerrain terrain = HOMETOWN_HASH_MAP.get(tile);
                int n = ThreadLocalRandom.current().nextInt(terrain.range);
                sprite = Sprite.getSprite(terrain.row, terrain.col + n, terrain.filename, terrain.tile_size);
                break;
            }
            case 2: {
                AshlandsTerrain terrain = ASHLANDS_HASH_MAP.get(tile);
                int n = ThreadLocalRandom.current().nextInt(terrain.range);
                sprite = Sprite.getSprite(terrain.row, terrain.col + n, terrain.filename, terrain.tile_size);
                break;
            }
            case 0:
            case 3:
            case 4:
            case 6:
            case 7: 
            case 9:
            case 10: 
            case 11: {
                BossTerrain terrain = BOSS_HASH_MAP.get(tile);
                int n = ThreadLocalRandom.current().nextInt(terrain.range);
                sprite = Sprite.getSprite(terrain.row, terrain.col + n, terrain.filename, terrain.tile_size);
                break;
            }
            case 5:
            case 8: {
                CastleTerrain terrain = CASTLE_HASH_MAP.get(tile);
                int n = ThreadLocalRandom.current().nextInt(terrain.range);
                sprite = Sprite.getSprite(terrain.row, terrain.col + n, terrain.filename, terrain.tile_size);
                break;
            }
        }
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH), x, y, null); // can use SCALE_FAST
    }
}