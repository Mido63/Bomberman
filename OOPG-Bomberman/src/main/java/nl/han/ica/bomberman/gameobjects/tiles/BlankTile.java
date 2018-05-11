package nl.han.ica.bomberman.gameobjects.tiles;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

public class BlankTile extends Tile {
    /**
     * @param sprite The image which will be drawn whenever the draw method of the Tile is called.
     */
    public BlankTile(Sprite sprite) {
        super(sprite);
    }
}