package rDarkTablets;

import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackInventoryOption;
import org.powbot.api.script.paint.TrackSkillOption;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;
import rDarkTablets.Tasks.*;

import java.util.ArrayList;

import org.powbot.api.Random;

@ScriptManifest(name = "rDarkTablets", description = "Creates tablets in Arceuus.",
        version = "0.2.0", category = ScriptCategory.MoneyMaking)

@ScriptConfiguration.List(
        {
                @ScriptConfiguration(name = "Select Tablet",
                        description = "What tablet would you like to create?",
                        defaultValue = "Arceuus library teleport",
                        allowedValues = {"Arceuus library teleport", "Draynor manor teleport", "Battlefront teleport",
                                "Mind altar teleport", "Salve graveyard teleport", "Fenkenstrain's castle teleport", "West ardougne teleport",
                                "Harmony island teleport", "Cemetery teleport", "Barrows teleport", "Ape atoll teleport"},
                        optionType = OptionType.STRING
                ),
                @ScriptConfiguration(name = "Library Teleport",
                        description = "Enable library teleport, must have library teleport tablets in inventory.",
                        optionType = OptionType.BOOLEAN)
        }
)

public class rDarkTablets extends AbstractScript {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private Task currentTask = null;

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("rDarkTablets", "", "127.0.0.1:5675", true, false);
    }


    @Override
    public void onStart() {
        // Two required items for this script: Pickaxe, chisel
        Item inventoryPickaxe = Inventory.stream().filter(i -> i.name().toLowerCase().contains("pickaxe")).first();
        Item equipmentPickaxe = Equipment.stream().filter(i -> i.name().toLowerCase().contains("pickaxe")).first();
        Item chisel = Inventory.stream().name("Chisel").first();

        Constants.tabletName = getOption("Select Tablet");
        Constants.enableTeleport = getOption("Library Teleport");

        // Make sure we have pickaxe + chisel
        if (inventoryPickaxe.valid() || equipmentPickaxe.valid() && chisel.valid()) {
            taskList.add(new WalkToMine("Walking to Mine"));
            taskList.add(new Mine("Mine Essence"));
            taskList.add(new WalkToAltar("Walking to Altar"));
            taskList.add(new Convert("Convert Essence"));
            taskList.add(new Teleport("Teleport"));
            taskList.add(new WalkToLectern("Walking to Lectern"));
            taskList.add(new OpenLectern("Open Lectern"));
            taskList.add(new CraftTablets("Create Tablets"));
        } else {
            ScriptManager.INSTANCE.stop();
        }

        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .addString("Status: ", () -> currentTask.name)
                .trackInventoryItem(Inventory.stream().filter(i -> i.name().equals(Constants.tabletName)).first().id(), "Tablets", TrackInventoryOption.Price, TrackInventoryOption.QuantityChange)
                .trackSkill(Skill.Magic, TrackSkillOption.Exp)
                .trackSkill(Skill.Mining, TrackSkillOption.Exp)
                .trackSkill(Skill.Crafting, TrackSkillOption.Exp)
                .trackSkill(Skill.Runecrafting, TrackSkillOption.Exp)
                .build();
        addPaint(paint);

        System.out.println("[DEBUG] CONFIGURATION | Tablet: " + Constants.tabletName + " | Teleport: " + Constants.enableTeleport);
    }

    @Override
    public void poll() {
        System.out.println("[DEBUG] New Poll Begun");
        for (Task task : taskList) {
            if (task.activate()) {
                currentTask = task;
                System.out.println("[DEBUG] Current task: " + currentTask.name);
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    break;
                }
            }
        }

        if (Random.nextInt(0, 500) == 1) {
            if (Random.nextBoolean()) {
                Camera.angle(Random.nextInt(0, 359));
            }
        }
//        System.out.println("[DEBUG] Poll complete");
    }
}
