package com.camadeusa.module.network.event;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.camadeusa.NetworkCore;
import com.camadeusa.module.game.GamemodeManager;
import com.camadeusa.player.ArchrPlayer;
import com.camadeusa.timing.TickQuarterSecondEvent;
import com.camadeusa.timing.TickSecondEvent;
import com.camadeusa.timing.TickThreeSecondEvent;
import com.camadeusa.utility.subservers.event.SubserversEvents;
import com.camadeusa.utility.subservers.packet.PacketGetServerConfigInfo;
import com.google.gdata.data.spreadsheet.ListEntry;

import net.ME1312.SubServers.Client.Bukkit.SubAPI;

public class NetworkServerInfoEvents implements Listener {
	public static Map<String, Map<String, Object>> serverInfoCache = new HashMap<>();

	@EventHandler
	public void onTickSecondEvent(TickThreeSecondEvent event) {
		GamemodeManager.currentplayers = ArchrPlayer.getOnlinePlayers().size();
	}

	@EventHandler
	public void onTickSecond(TickSecondEvent event) {
		ArchrPlayer.correctArchrPlayerList();

	}

	@EventHandler
	public void onTickQuarterSecond(TickQuarterSecondEvent event) {
		if (SubserversEvents.connected) {
			SubAPI.getInstance().getSubDataNetwork().sendPacket(
					new PacketGetServerConfigInfo(SubAPI.getInstance().getSubDataNetwork().getName(), json -> {
					}));
		}

	}

}
