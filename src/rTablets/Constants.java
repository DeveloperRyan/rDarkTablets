package rTablets;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Widgets;

public class Constants {
    // Areas ------------------------------
    public static final Area lecternArea = new Area(
        new Tile(1675, 3767, 0),
        new Tile(1685, 3767, 0),
        new Tile(1685, 3761, 0),
        new Tile(1679, 3761, 0),
        new Tile(1679, 3764, 0),
        new Tile(1675, 3764, 0)
);
    public static final Area essenceMineArea = new Area(new Tile(1766, 3855, 0), new Tile(1759, 3846, 0));
    public static final Area darkAltarArea = new Area(new Tile(1714, 3884, 0), new Tile(1712, 3878, 0));

    // Configuration -----------------------
    public static String tabletName = "";
    public static boolean enableTeleport = false;
}
