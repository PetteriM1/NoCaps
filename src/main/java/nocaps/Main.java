package nocaps;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("nocaps.ignore")) {
            e.setMessage(e.getMessage().toLowerCase());
        }
    }
}