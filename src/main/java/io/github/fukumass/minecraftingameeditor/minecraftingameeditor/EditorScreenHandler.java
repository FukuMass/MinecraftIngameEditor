package io.github.fukumass.minecraftingameeditor.minecraftingameeditor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class EditorScreenHandler extends ScreenHandler {
  protected EditorScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
    super(type, syncId);
  }

  @Override
  public ItemStack transferSlot(PlayerEntity player, int index) {
    return null;
  }

  @Override
  public boolean canUse(PlayerEntity player) {
    return false;
  }
}