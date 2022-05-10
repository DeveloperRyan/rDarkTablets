package rDarkTablets;

import org.powbot.api.Area;
import org.powbot.api.Tile;

public class Constants {
    // Areas ------------------------------
    public static final Area lecternArea = new Area(
            new Tile(1672, 3770, 0),
            new Tile(1672, 3764, 0),
            new Tile(1679, 3764, 0),
            new Tile(1679, 3761, 0),
            new Tile(1686, 3761, 0),
            new Tile(1684, 3769, 0)
    );
    public static final Area teleportArea = new Area(new Tile(1639, 3831, 0), new Tile(1626, 3844, 0));
    public static final Area essenceMineArea = new Area(
            new Tile(1757, 3850, 0),
            new Tile(1758, 3850, 0),
            new Tile(1758, 3857, 0),
            new Tile(1768, 3857, 0),
            new Tile(1768, 3845, 0),
            new Tile(1757, 3845, 0)
    );
    public static final Area darkAltarArea = new Area(new Tile(1714, 3884, 0), new Tile(1712, 3878, 0));

    // Configuration -----------------------
    public static String tabletName = "";
    public static boolean enableTeleport = false;
}
