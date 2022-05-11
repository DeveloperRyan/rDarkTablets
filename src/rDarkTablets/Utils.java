package rDarkTablets;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Widgets;
import org.powbot.api.rt4.walking.local.Flag;

import java.util.HashMap;

public class Utils {
    public static Component getTabletWidget() {
        final Component arceuusTabWidget = Widgets.component(Constants.LECTERN_WIDGET_ID, Constants.ARCEUUS_TAB_WIDGET_ID);
        final Component draynorTabWidget = Widgets.component(Constants.LECTERN_WIDGET_ID, Constants.DRAYNOR_TAB_WIDGET_ID);
        final Component battlefrontTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.BATTLEFRONT_TAB_WIDGET_ID);
        final Component mindAltarTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.MIND_ALTAR_TAB_WIDGET_ID);
        final Component salveTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.SALVE_TAB_WIDGET_ID);
        final Component fenkenstrainTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.FENKENSTRAIN_TAB_WIDGET_ID);
        final Component ardougneTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.ARDOUGNE_TAB_WIDGET_ID);
        final Component harmonyTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.HARMONY_TAB_WIDGET_ID);
        final Component cemeteryTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.CEMETERY_TAB_WIDGET_ID);
        final Component barrowsTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.BARROWS_TAB_WIDGET_ID);
        final Component apeTabWidget = Widgets.widget(Constants.LECTERN_WIDGET_ID).component(Constants.APE_TAB_WIDGET_ID);


        HashMap<String, Component> widgetMappings = new HashMap<>();
        widgetMappings.put("Arceuus library teleport", arceuusTabWidget);
        widgetMappings.put("Draynor manor teleport", draynorTabWidget);
        widgetMappings.put("Battlefront teleport", battlefrontTabWidget);
        widgetMappings.put("Mind altar teleport", mindAltarTabWidget);
        widgetMappings.put("Salve graveyard teleport", salveTabWidget);
        widgetMappings.put("Fenkenstrain's castle teleport", fenkenstrainTabWidget);
        widgetMappings.put("West ardougne teleport", ardougneTabWidget);
        widgetMappings.put("Harmony island teleport", harmonyTabWidget);
        widgetMappings.put("Cemetery teleport", cemeteryTabWidget);
        widgetMappings.put("Barrows teleport", barrowsTabWidget);
        widgetMappings.put("Ape atoll teleport", apeTabWidget);

        return widgetMappings.get(Constants.tabletName);
    }

    public static Component getMainLecternWidget() {
        return Widgets.component(Constants.LECTERN_WIDGET_ID, Constants.LECTERN_WIDGET_COMPONENT_ID);
    }

    public static Component getLecternCreateTabletWidget() {
        return Widgets.component(Constants.LECTERN_WIDGET_ID, Constants.LECTERN_WIDGET_CREATE_COMPONENT_ID);
    }

    public static Component getLecternQuantityAllWidget() {
        return Widgets.component(Constants.LECTERN_WIDGET_ID, Constants.LECTERN_WIDGET_QUANTITY_ALL_COMPONENT_ID);
    }

    public static int getLecternQuantityAllWidgetTexture() {
        return getLecternQuantityAllWidget().component(Constants.LECTERN_WIDGET_QUANTITY_ALL_SUB_COMPONENT_ID).textureId();
    }

    public static int getTabletWidgetTexture() {
        return getTabletWidget().component(Constants.LECTERN_WIDGET_TABLET_SUB_COMPONENT_ID).textureId();
    }

    public static String getLecternCreateTabletWidgetText() {
        return Widgets.component(Constants.LECTERN_WIDGET_ID,
                Constants.LECTERN_WIDGET_CREATE_COMPONENT_ID,
                Constants.LECTERN_WIDGET_CREATE_SUB_COMPONENT_TEXT_ID).text();
    }
}
