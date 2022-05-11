package rDarkTablets.Tasks;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import rDarkTablets.Constants;
import rDarkTablets.Task;
import rDarkTablets.Utils;

public class CraftTablets extends Task {
    public CraftTablets(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Utils.getMainLecternWidget().visible();
    }

    @Override
    public void execute() {
        // Make sure the correct tablet is selected
        if (Utils.getTabletWidgetTexture() != Constants.SELECTED_LECTERN_WIDGET_OPTION_TEXTURE_ID) {
            if (Condition.wait(() -> Utils.getTabletWidget().click(), 150, 10)) {
                Condition.wait(() -> Utils.getTabletWidgetTexture() == Constants.SELECTED_LECTERN_WIDGET_OPTION_TEXTURE_ID,
                        100, 10);
            }
        }

        // Make sure "All" is selected
        if (
                Utils.getLecternQuantityAllWidgetTexture() != Constants.SELECTED_LECTERN_WIDGET_OPTION_TEXTURE_ID) {

            if (Condition.wait(() -> Utils.getLecternQuantityAllWidget().click(), 150, 10)) {
                Condition.wait(() -> Utils.getLecternQuantityAllWidgetTexture() == Constants.SELECTED_LECTERN_WIDGET_OPTION_TEXTURE_ID, 100, 10);
            }
        }

        // Make sure create is pressed
        if (Utils.getLecternCreateTabletWidget().visible()
                && Utils.getLecternCreateTabletWidgetText().equals("Create")) {
            Utils.getLecternCreateTabletWidget().click();
            Condition.wait(() -> !Utils.getTabletWidget().visible(), 100, 10);
        }

        Condition.wait(() -> Players.local().animation() == -1
                && Inventory.stream().name(Constants.DARK_ESSENCE_BLOCK).isEmpty(), 500, 60);
    }
}
