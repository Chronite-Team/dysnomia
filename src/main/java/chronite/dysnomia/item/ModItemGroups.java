package chronite.dysnomia.item;

import chronite.dysnomia.Dysnomia;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup DYSNOMIA_TOOLS_GROUP = PolymerItemGroupUtils.builder()
            .displayName(Text.translatable("itemgroup.dysnomia.tools"))
            .icon(() -> new ItemStack(ModItems.ARIADNE_THREAD))
            .entries((displayContext, entries) -> {
                entries.add(ModItems.ARIADNE_THREAD);
            }).build();

    public static void registerItemGroups() {
        Dysnomia.LOGGER.info("Registering Item Groups for " + Dysnomia.MOD_ID);
        PolymerItemGroupUtils.registerPolymerItemGroup(Identifier.of(Dysnomia.MOD_ID, "tools"),
                DYSNOMIA_TOOLS_GROUP);
    }
}
