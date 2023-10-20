/*
    Loafy
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/loafy
    License: GNU LGPLv3
*/

package com.mclegoman.loafy.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
	@Inject(at = @At("RETURN"), method = "getTranslationKey(Lnet/minecraft/item/ItemStack;)Ljava/lang/String;", cancellable = true)
	private void getTranslationKey(ItemStack par1, CallbackInfoReturnable<String> cir) {
		cir.setReturnValue("item.bread");
	}
}