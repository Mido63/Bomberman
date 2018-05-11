package nl.han.ica.bomberman;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.bomberman.gameobjects.tiles.BoardsTile;
import nl.han.ica.bomberman.gameobjects.tiles.Kist;
import nl.han.ica.bomberman.gameobjects.Bomb;
import nl.han.ica.bomberman.gameobjects.Player;
import nl.han.ica.bomberman.gameobjects.Player2;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class BombermanWorld extends GameEngine {

    private Bomb bomb;
    private Player player;
    private Player2 player2;
    private boolean bombCreated = false;
    Alarm timer;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.bomberman.BombermanWorld"});
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {

        int worldWidth=749;
        int worldHeight=749;

        //initializeSound();
        createDashboard(worldWidth, 100);
        initializeTileMap();

        createObjects();

        createViewWithoutViewport(worldWidth, worldHeight);

    }

    /**
     * Creeërt de view zonder viewport
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/bomberman/media/Images/background-Custom.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }
    /**
     * Initialiseert geluid
     */
//    private void initializeSound() {
//        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/bomberman/media/Music/background-music.mp3");
//        backgroundSound.loop(-1);
//        bombExplotion = new Sound(this, "src/main/java/nl/han/ica/bomberman/media/Music/explotion.mp3");
//    }


    /**
     * Maakt de spelobjecten aan
     */
    private void createObjects() {
    	player = new Player(this);
        addGameObject(player, 100, 100);
        player2 = new Player2(this);
        addGameObject(player2, 400, 400);
    }
    
    public void createBom() {
    	bombCreated = true;
    	if(bombCreated) {
    		bomb = new Bomb(bomb);
        	addGameObject(bomb, 100, 100);
        	update();
        	bombCreated = false;	
    	}	
    }

    /**
     * Maakt het dashboard aan
     * @param dashboardWidth Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        addDashboard(dashboard);
    }
    /** 
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/bomberman/media/Images/block.jpg");
        Sprite kistSprite = new Sprite("src/main/java/nl/han/ica/bomberman/media/Images/crate.jpg");
        TileType<BoardsTile> muren = new TileType<>(BoardsTile.class, boardsSprite);
        TileType<Kist> kisten = new TileType<>(Kist.class, kistSprite);
        ArrayList<Integer> n = new ArrayList<>();
        final int WALL = 1;
        final int KIST = -1;
        Random random = new Random(); 
        for(int i=0;i<133;i++) {
        	n.add(random.nextInt(0 + 1 +1) + -1);
        }
        System.out.println(n);
       
		TileType[] tileTypes = {kisten, muren };
        int tileSize=50;
        int tilesMap[][]={
        		  { WALL, WALL	    , WALL	    ,  WALL	    , WALL	    , WALL      , WALL	    , WALL      , WALL	    , WALL      , WALL      , WALL      , WALL      , WALL      , WALL},
                  { WALL, -1  	    , -1	    , n.get(0)  , n.get(1)  , n.get(2)  , n.get(3)  , n.get(4)  , n.get(5)  , n.get(6)  , n.get(7)  , n.get(8)  , n.get(9)  , n.get(10) , WALL},
                  { WALL, -1  	    , -1	    , n.get(11) , WALL	    , n.get(12) , WALL      , n.get(13) , WALL	    , n.get(14) , WALL		, n.get(15) , WALL      , n.get(16) , WALL},
                  { WALL, n.get(17) , n.get(18) , n.get(19) , n.get(20) , n.get(21) , n.get(22) , n.get(23) , n.get(24) , n.get(25) , n.get(26) , n.get(27) , n.get(28) , n.get(29) , WALL},
                  { WALL, n.get(30) , WALL	    , n.get(31) , WALL      , n.get(32) , WALL	    , n.get(33) , WALL	    , n.get(34) , WALL		, n.get(35) , WALL      , n.get(36) , WALL},
                  { WALL, n.get(37) , n.get(38) , n.get(39) , n.get(40) , n.get(41) , n.get(42) , n.get(43) , n.get(44) , n.get(45) , n.get(46)	, n.get(47) , n.get(48) , n.get(49) , WALL},
                  { WALL, n.get(50) , WALL	    , n.get(51) , WALL	    , n.get(52) , WALL	    , n.get(53) , WALL      , n.get(54) , WALL		, n.get(55) , WALL      , n.get(56) , WALL},
                  { WALL, n.get(57) , n.get(58) , n.get(59) , n.get(60) , n.get(61) , n.get(62) , n.get(63) , n.get(64) , n.get(65) , n.get(66)	, n.get(67) , n.get(68) , n.get(69) , WALL},
                  { WALL, n.get(70) , WALL	    , n.get(71) , WALL	    , n.get(72) , WALL      , n.get(73) , WALL	    , n.get(74) , WALL		, n.get(75) , WALL      , n.get(76) , WALL},
                  { WALL, n.get(77) , n.get(78) , n.get(79) , n.get(80) , n.get(81) , n.get(82) , n.get(83) , n.get(84) , n.get(85) , n.get(86)	, n.get(87) , n.get(88) , n.get(89) , WALL},
                  { WALL, n.get(90) , WALL	    , n.get(91) , WALL	    , n.get(92) , WALL      , n.get(93) , WALL	    , n.get(94) , WALL		, n.get(95) , WALL      , n.get(96) , WALL},
                  { WALL, n.get(97) , n.get(98) , n.get(99) , n.get(100), n.get(101), n.get(102), n.get(103), n.get(104), n.get(105), n.get(106), n.get(107), n.get(108), n.get(109), WALL},
                  { WALL, n.get(110), WALL	    , n.get(111), WALL	    , n.get(112), WALL 	    , n.get(113), WALL	    , n.get(114), WALL		, n.get(115), WALL      , -1        , WALL},
                  { WALL, n.get(116), n.get(117), n.get(118), n.get(119), n.get(120), n.get(121), n.get(122), n.get(123), n.get(124), n.get(125), n.get(126), -1        , -1        , WALL},
                  { WALL, WALL	    , WALL	    , WALL	    , WALL	    , WALL      , WALL	    , WALL	    , WALL	    , WALL		, WALL		, WALL	    , WALL      , WALL      , WALL}
                  
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
    }
}