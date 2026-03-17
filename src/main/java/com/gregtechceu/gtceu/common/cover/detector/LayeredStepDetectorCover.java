package com.gregtechceu.gtceu.common.cover.detector;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.ICoverable;
import com.gregtechceu.gtceu.api.cover.CoverDefinition;
import com.gregtechceu.gtceu.common.machine.trait.LayeredRecipeLogic;

import net.minecraft.core.Direction;

public class LayeredStepDetectorCover extends DetectorCover {

    public LayeredStepDetectorCover(CoverDefinition definition, ICoverable coverHolder, Direction attachedSide) {
        super(definition, coverHolder, attachedSide);
    }

    @Override
    public boolean canAttach() {
        return super.canAttach() && GTCapabilityHelper.getRecipeLogic(coverHolder.getLevel(), coverHolder.getPos(),
                attachedSide) instanceof LayeredRecipeLogic;
    }

    @Override
    protected void update() {
        if (this.coverHolder.getOffsetTimer() % 20 != 0) {
            return;
        }

        var recipeLogic = GTCapabilityHelper.getRecipeLogic(coverHolder.getLevel(), coverHolder.getPos(), attachedSide);
        var layeredStep = recipeLogic instanceof LayeredRecipeLogic layeredRecipeLogic ?
                layeredRecipeLogic.getCoverRedstoneOutput() : 0;
        setRedstoneSignalOutput(layeredStep);
    }
}
