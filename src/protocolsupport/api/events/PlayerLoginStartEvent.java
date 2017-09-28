package protocolsupport.api.events;

import java.net.InetSocketAddress;
import java.util.UUID;

import org.bukkit.event.HandlerList;

import protocolsupport.api.Connection;
import protocolsupport.api.ProtocolSupportAPI;

/**
 * This event is fired after player login start (after login start packet which contains client username)
 */
public class PlayerLoginStartEvent extends PlayerAbstractLoginEvent {

	private final String hostname;
	private boolean onlinemode;
	private boolean useonlinemodeuuid;
	private UUID uuid;

	public PlayerLoginStartEvent(Connection connection, String username, boolean onlinemode, boolean useonlinemodeuuid, String hostname) {
		super(connection, username);
		this.onlinemode = onlinemode;
		this.useonlinemodeuuid = useonlinemodeuuid;
		this.hostname = hostname;
	}

	@Deprecated
	public PlayerLoginStartEvent(InetSocketAddress address, String username, boolean onlinemode, boolean useonlinemodeuuid, String hostname) {
		this(ProtocolSupportAPI.getConnection(address), username, onlinemode, useonlinemodeuuid, hostname);
	}

	/**
	 * Returns hostname which player used when connecting to server
	 * @return hostname which player used when connecting to server
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * Returns true if online-mode checks will be used to auth player <br>
	 * By default returns same value as server online-mode setting
	 * @return true if online-mode checks will be used to auth player
	 */
	public boolean isOnlineMode() {
		return onlinemode;
	}

	/**
	 * Sets if online-mode checks will be used to auth player
	 * @param onlinemode if online-mode checks will be used to auth player
	 */
	public void setOnlineMode(boolean onlinemode) {
		this.onlinemode = onlinemode;
	}

	/**
	 * Returns true if online-mode uuid will be assigned to player <by>
	 * Only used if player authed using online-mode checks <br>
	 * By default returns same value as server online-mode setting
	 * @return true if online-mode uuid will be assigned to player
	 */
	public boolean useOnlineModeUUID() {
		return useonlinemodeuuid;
	}

	/**
	 * Sets if online-mode uuid will be assigned to player <br>
	 * Only used if player authed using online-mode checks
	 * @param useonlinemodeuuid if online-mode uuid will be assigned to player 
	 */
	public void setUseOnlineModeUUID(boolean useonlinemodeuuid) {
		this.useonlinemodeuuid = useonlinemodeuuid;
	}

	/**
	 * Returns true if has forced uuid
	 * @return true if has forced uuid
	 */
	public boolean hasForcedUUID() {
		return uuid != null;
	}

	/**
	 * Sets forced uuid <br>
	 * If set to null, server-selected uuid will be used <br>
	 * This option overrides any other uuid options (like {@link #useOnlineModeUUID()})
	 * @param uuid forced uuid
	 */
	public void setForcedUUID(UUID uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gets currently set forced uuid or null if not set <br>
	 * By default returns null
	 * @return currently set forced uuid
	 */
	public UUID getForcedUUID() {
		return uuid;
	}


	private static final HandlerList list = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return list;
	}

	public static HandlerList getHandlerList() {
		return list;
	}

}
