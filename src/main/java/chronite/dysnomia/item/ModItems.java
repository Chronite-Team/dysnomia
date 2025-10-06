package chronite.dysnomia.item;

import chronite.dysnomia.Dysnomia;
import chronite.dysnomia.item.custom.AriadneThreadItem;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item ARIADNE_THREAD = registerItem("ariadne_thread", AriadneThreadItem::new);

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(Dysnomia.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM,
                        Identifier.of(Dysnomia.MOD_ID, name)))));
    }

    public static void registerModItems() {
        Dysnomia.LOGGER.info("Registering Mod Items for " + Dysnomia.MOD_ID);

        PolymerItemGroupUtils.builder();
        //PolymerItemGroupUtils.registerPolymerItemGroup();

    }
}
