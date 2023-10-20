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

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow public int count;

	@Shadow private int damage;

	@Shadow public int id;

	@Shadow public NbtCompound nbt;

	@Inject(at = @At("RETURN"), method = "<init>(III)V")
	private void init(int j, int k, int par3, CallbackInfo ci) {
		this.id = Item.BREAD.id;
	}
	@Inject(at = @At("RETURN"), method = "getItem", cancellable = true)
	private void getItem(CallbackInfoReturnable<Item> cir) {
		cir.setReturnValue(Item.ITEMS[Item.BREAD.id]);
	}
	@Inject(at = @At("HEAD"), method = "toNbt", cancellable = true)
	private void readNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
		nbt.putShort("id", (short)Item.BREAD.id);
		nbt.putByte("Count", (byte)this.count);
		nbt.putShort("Damage", (short)Item.BREAD.getMaxDamage());
		if (this.nbt != null) {
			nbt.put("tag", this.nbt);
		}
		cir.setReturnValue(nbt);
	}
	@Inject(at = @At("HEAD"), method = "writeNbt", cancellable = true)
	private void writeNbt(NbtCompound nbt, CallbackInfo ci) {
		this.id = Item.BREAD.id;
		this.count = nbt.getByte("Count");
		this.damage = Item.BREAD.getMaxDamage();
		if (nbt.contains("tag")) {
			this.nbt = nbt.getCompound("tag");
		}
		ci.cancel();
	}
}