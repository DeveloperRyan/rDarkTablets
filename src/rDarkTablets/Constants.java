package rDarkTablets;

import org.powbot.api.Area;
import org.powbot.api.Tile;

public class Constants {
    // Areas ------------------------------
    public static final Area LECTERN_AREA = new Area(
            new Tile(1672, 3770, 0),
            new Tile(1672, 3764, 0),
            new Tile(1679, 3764, 0),
            new Tile(1679, 3761, 0),
            new Tile(1686, 3761, 0),
            new Tile(1684, 3769, 0)
    );
    public static final Area TELEPORT_AREA = new Area(new Tile(1639, 3831, 0), new Tile(1626, 3844, 0));
    public static final Area ESSENCE_MINE_AREA = new Area(
            new Tile(1757, 3850, 0),
            new Tile(1758, 3850, 0),
            new Tile(1758, 3857, 0),
            new Tile(1768, 3857, 0),
            new Tile(1768, 3845, 0),
            new Tile(1757, 3845, 0)
    );
    public static final Area ESSENCE_MINE_WALKABLE = new Area(
            new Tile(1766, 3850, 0),
            new Tile(1765, 3850, 0),
            new Tile(1764, 3850, 0),
            new Tile(1763, 3850, 0),
            new Tile(1762, 3850, 0),
            new Tile(1761, 3850, 0),
            new Tile(1766, 3849, 0),
            new Tile(1765, 3849, 0),
            new Tile(1764, 3849, 0),
            new Tile(1763, 3849, 0),
            new Tile(1762, 3849, 0),
            new Tile(1761, 3849, 0)
    );
    public static final Area DARK_ALTAR_AREA = new Area(new Tile(1711, 3886, 0), new Tile(1720, 3877, 0));

    // Configuration -----------------------
    public static String tabletName = "";
    public static boolean enableTeleport = false;

    // Item IDs / Names --------------------
    public static String DARK_ESSENCE_BLOCK = "Dark essence block";
    public static String DENSE_ESSENCE_BLOCK = "Dense essence block";
    public static int ARCEUUS_LIBRARY_TELEPORT = 19613;

    // Object IDs / Names ------------------
    public static String DENSE_RUNESTONE = "Dense runestone";
    public static String DARK_ALTAR = "Dark Altar";
    public static int LECTERN_ID = 28802;


    // Widget/Component IDs
    public static final int SELECTED_LECTERN_WIDGET_OPTION_TEXTURE_ID = 897;
    public static final int LECTERN_WIDGET_ID = 403;
    public static final int LECTERN_WIDGET_COMPONENT_ID = 0;
    public static final int LECTERN_WIDGET_TABLET_SUB_COMPONENT_ID = 0;
    public static final int LECTERN_WIDGET_QUANTITY_ALL_COMPONENT_ID = 8;
    public static final int LECTERN_WIDGET_QUANTITY_ALL_SUB_COMPONENT_ID = 0;
    public static final int LECTERN_WIDGET_CREATE_COMPONENT_ID = 13;
    public static final int LECTERN_WIDGET_CREATE_SUB_COMPONENT_TEXT_ID = 9;
    public static final int ARCEUUS_TAB_WIDGET_ID = 14;
    public static final int DRAYNOR_TAB_WIDGET_ID = 15;
    public static final int BATTLEFRONT_TAB_WIDGET_ID = 16;
    public static final int MIND_ALTAR_TAB_WIDGET_ID = 17;
    public static final int SALVE_TAB_WIDGET_ID = 18;
    public static final int FENKENSTRAIN_TAB_WIDGET_ID = 19;
    public static final int ARDOUGNE_TAB_WIDGET_ID = 20;
    public static final int HARMONY_TAB_WIDGET_ID = 21;
    public static final int CEMETERY_TAB_WIDGET_ID = 22;
    public static final int BARROWS_TAB_WIDGET_ID = 23;
    public static final int APE_TAB_WIDGET_ID = 24;
}