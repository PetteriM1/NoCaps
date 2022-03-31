package nocaps;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener {

    private double maxCaps;
    private String noCapsMessage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        maxCaps = getConfig().getDouble("maxCaps");
        noCapsMessage = getConfig().getString("noCapsMessage");
        if (maxCaps < 0 || maxCaps > 1) {
            getLogger().warning("maxCaps value must be between 0 and 1");
        }
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(PlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("nocaps.ignore")) {
            String msg = e.getMessage();
            int value, capsCount = 0;
            int length = msg.length();
            for (int x = 0; x < length; x++) {
                value = msg.charAt(x);
                if (value >= 65 && value <= 90) capsCount++;
            }
            if ((double) capsCount / length > maxCaps && length > 3) {
                e.setMessage(msg.toLowerCase());
                if (!noCapsMessage.isEmpty()) {
                    e.getPlayer().sendMessage(noCapsMessage);
                }
            }
        }
    }
}
