/*
 * This file is part of LiteLoader.
 * Copyright (C) 2012-16 Adam Mummery-Smith
 * All Rights Reserved.
 */
package com.mumfrey.liteloader.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mumfrey.liteloader.client.overlays.IMinecraft;
import com.mumfrey.liteloader.common.GameEngine;
import com.mumfrey.liteloader.common.Resources;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.integrated.IntegratedServer;

/**
 *
 * @author Adam Mummery-Smith
 */
public class GameEngineClient implements GameEngine<Minecraft, IntegratedServer>
{
    private final Minecraft engine = Minecraft.getMinecraft();

    private final Resources<?, ?> resources = new ResourcesClient();

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#getProfiler()
     */
    @Override
    public Profiler getProfiler()
    {
        return this.engine.mcProfiler;
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#isClient()
     */
    @Override
    public boolean isClient()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#isServer()
     */
    @Override
    public boolean isServer()
    {
        return this.isSinglePlayer();
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#isInGame()
     */
    @Override
    public boolean isInGame()
    {
        return this.engine.player != null && this.engine.world != null && this.engine.world.isRemote;
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#isRunning()
     */
    @Override
    public boolean isRunning()
    {
        return ((IMinecraft)this.engine).isRunning();
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#isSingleplayer()
     */
    @Override
    public boolean isSinglePlayer()
    {
        return this.engine.isSingleplayer();
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#getClient()
     */
    @Override
    public Minecraft getClient()
    {
        return this.engine;
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#getServer()
     */
    @Override
    public IntegratedServer getServer()
    {
        return this.engine.getIntegratedServer();
    }

    @Override
    public Resources<?, ?> getResources()
    {
        return this.resources;
    }

    public GameSettings getGameSettings()
    {
        return this.engine.gameSettings;
    }

    public ScaledResolution getScaledResolution()
    {
        return new ScaledResolution(this.engine);
    }

    public GuiNewChat getChatGUI()
    {
        return this.engine.ingameGUI.getChatGUI();
    }

    public GuiScreen getCurrentScreen()
    {
        return this.engine.currentScreen;
    }

    public boolean hideGUI()
    {
        return this.engine.gameSettings.hideGUI;
    }

    public SoundHandler getSoundHandler()
    {
        return this.engine.getSoundHandler();
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine#getKeyBindings()
     */
    @Override
    public List<KeyBinding> getKeyBindings()
    {
        ArrayList<KeyBinding> keyBindings = new ArrayList<KeyBinding>();
        keyBindings.addAll(Arrays.asList(this.engine.gameSettings.keyBindings));
        return keyBindings;
    }

    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.common.GameEngine
     *      #setKeyBindings(java.util.List)
     */
    @Override
    public void setKeyBindings(List<KeyBinding> keyBindings)
    {
        this.engine.gameSettings.keyBindings = keyBindings.toArray(new KeyBinding[0]);
    }
}
