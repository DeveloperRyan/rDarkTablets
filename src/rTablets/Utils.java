package rTablets;

import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Widgets;

import java.util.HashMap;

public class Utils {
    public static Component getTabletWidget() {
        final Component arceuusTabWidget = Widgets.component(403, 14);
        final Component draynorTabWidget = Widgets.component(403, 15);
        final Component battlefrontTabWidget = Widgets.widget(403).component(16);
        final Component mindAltarTabWidget = Widgets.widget(403).component(16);
        final Component salveTabWidget = Widgets.widget(403).component(17);
        final Component fenkenstrainTabWidget = Widgets.widget(403).component(18);
        final Component ardougneTabWidget = Widgets.widget(403).component(19);
        final Component harmonyTabWidget = Widgets.widget(403).component(20);
        final Component cemeteryTabWidget = Widgets.widget(403).component(21);
        final Component barrowsTabWidget = Widgets.widget(403).component(22);
        final Component apeTabWidget = Widgets.widget(403).component(23);



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
}
