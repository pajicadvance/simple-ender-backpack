package me.pajic.simpleenderbackpack.item;

import me.pajic.simpleenderbackpack.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
//? if <= 1.21.1 {
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
//?}
//? if > 1.21.1
/*import net.minecraft.world.InteractionResult;*/

public class EnderBackpackItem extends Item {
    public EnderBackpackItem(Properties properties) {
        super(properties);
    }

    @Override
    //? if <= 1.21.1
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
    //? if > 1.21.1
    /*public @NotNull InteractionResult use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {*/
        if (level.isClientSide()) {
            player.playSound(SoundEvents.ENDER_CHEST_OPEN);
        }
        if (!level.isClientSide()) {
            Util.openEnderBackpack(player);
        }
        //? if <= 1.21.1
        return InteractionResultHolder.success(player.getItemInHand(usedHand));
        //? if > 1.21.1
        /*return InteractionResult.SUCCESS;*/
    }
}
