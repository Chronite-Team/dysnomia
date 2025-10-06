package chronite.dysnomia.item.custom;

import chronite.dysnomia.Dysnomia;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ModelAndTexture;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.Objects;
import java.util.Set;

public class AriadneThreadItem extends SimplePolymerItem {
    public AriadneThreadItem(Settings settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return Items.BOW;
    }



    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        if (PolymerResourcePackUtils.hasResources()) {
            return Identifier.of(Dysnomia.MOD_ID, "ariadne_thread");
        } else {
            Dysnomia.LOGGER.info(context.getPlayer().getStringifiedName() + " doesn't have the Resource Pack.");
            return Identifier.of("minecraft","string");

        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return ActionResult.PASS;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack hand = user.getEquippedStack(EquipmentSlot.MAINHAND);
        ItemStack offhand = user.getEquippedStack(EquipmentSlot.OFFHAND);
        if (hand.isOf(this)) {
            hand.setCount(hand.getCount() - 1);
        } else if (offhand.isOf(this)) {
            offhand.setCount(offhand.getCount() - 1);
        }
        try  {
            ServerWorld overworld = Objects.requireNonNull(world.getServer()).getOverworld();
            BlockPos spawn = user.getWorldSpawnPos(overworld, overworld.getSpawnPoint().getPos());
            boolean success = user.teleport(overworld,
                    spawn.getX(), spawn.getY(), spawn.getZ(),
                    Set.of(), 0f, 0f, false
                    );
            if (success) {
                return stack;
            }
        }
        catch (NullPointerException exception) {
            String warning = String.format("%s failed to return to world spawn because server threw %s",
                    user.getStringifiedName(),
                    exception.getLocalizedMessage()
                    );
            Dysnomia.LOGGER.warn(warning);
        }
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 100;
    }

}
