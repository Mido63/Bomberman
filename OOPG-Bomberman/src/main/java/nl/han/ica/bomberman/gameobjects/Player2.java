package nl.han.ica.bomberman.gameobjects;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.bomberman.Bomberman;
import nl.han.ica.bomberman.gameobjects.tiles.BoardsTile;
import processing.core.PVector;

import java.util.List;

public class Player2 extends AnimatedSpriteObject implements ICollidableWithTiles {

    final int size=25;
    private final Bomberman world;

    /**
     * Constructor
     * @param world Referentie naar de wereld
     */
    public Player2(Bomberman world) {
        super(new Sprite("src/main/java/nl/han/ica/bomberman/media/Images/player2.JPG"), 1);
        this.world=world;
        setCurrentFrameIndex(0);
        setFriction(0.05f);
    }

    @Override
    public void update() {
        if (getX()<=0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY()<=0) {
            setySpeed(0);
            setY(0);
        }
        if (getX()>=world.getWidth()-size) {
            setxSpeed(0);
            setX(world.getWidth() - size);
        }
        if (getY()>=world.getHeight()-size) {
            setySpeed(0);
            setY(world.getHeight() - size);
        }
        

    }
    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (key == 'a') {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }
        if (key == 'w') {
            setDirectionSpeed(0, speed);
        }
        if (key == 'd') {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(0);
        }
        if (key == 's') {
            setDirectionSpeed(180, speed);
        }
        if (key == '0') {
            System.out.println("Spatie!");
            //world.createBom();
        }
    }

    //TODO: hier voegen we de bots locaties toe.
    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles)  {
        PVector vector;

        for (CollidedTile ct : collidedTiles) {
            if (ct.theTile instanceof BoardsTile) {
                if (ct.collisionSide == ct.TOP) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setY(vector.y - getHeight());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if(ct.collisionSide == ct.BOTTOM) {
                	try {
                		vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                		setY(vector.y + getHeight());
                	} catch(TileNotFoundException e) {
                		e.printStackTrace();
                	}
                }
                if (ct.collisionSide == ct.RIGHT) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setX(vector.x + getWidth());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (ct.collisionSide == ct.LEFT) {
                    try {
                        vector = world.getTileMap().getTilePixelLocation(ct.theTile);
                        setX(vector.x - getWidth());
                    } catch (TileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                
            }
        }
    }
}
