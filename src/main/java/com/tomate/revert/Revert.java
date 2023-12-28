package com.tomate.revert;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Revert.MODID)
public class Revert
{
    public static final String MODID = "revert";

    public Revert()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
