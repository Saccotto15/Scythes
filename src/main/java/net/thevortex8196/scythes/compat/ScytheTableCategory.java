package net.thevortex8196.scythes.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;
import net.thevortex8196.scythes.block.ModBlocks;

import java.util.LinkedList;
import java.util.List;

public class ScytheTableCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE = Identifier.of(Scythes.MOD_ID, "textures/gui/scythe_table_rei.png");
    public static final CategoryIdentifier<ScytheTableDisplay> SCYTHE_TABLE = CategoryIdentifier.of(Scythes.MOD_ID, "scythe_table");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return SCYTHE_TABLE;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("block.scythes.scythe_table");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.SCYTHE_TABLE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();

        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 39, startPoint.y + 34))
                .entries(display.getInputEntries().get(0)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 68, startPoint.y + 52))
                .entries(display.getInputEntries().get(1)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 68, startPoint.y + 34))
                .entries(display.getInputEntries().get(2)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 68, startPoint.y + 16))
                .entries(display.getInputEntries().get(3)).markInput());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 120, startPoint.y + 34))
                .entries(display.getOutputEntries().get(0)).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
