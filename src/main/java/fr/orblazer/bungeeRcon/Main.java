package fr.orblazer.bungeeRcon;

import fr.orblazer.bungeeRcon.server.RconServer;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class of plugin
 *
 * @author orblazer
 */
public class Main extends Plugin {
    private static Main instance;
    private static final Logger logger = ProxyServer.getInstance().getLogger();

    private Config config;
    private RconServer rconServer;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Loading config
        config = new Config(this);

        // Start server
        SocketAddress address = new InetSocketAddress(config.getPort());
        rconServer = new RconServer(this.getProxy(), config.getPassword());

        logger.log(Level.INFO, "Binding rcon to address: {0}...", address);

        Channel channel = rconServer.bind(address).awaitUninterruptibly().channel();
        if (!channel.isActive()) {
            logger.warning("Failed to bind rcon port. Address already in use?");
        }
    }

    @Override
    public void onDisable() {
        logger.info("Trying to stop RCON listener");
        rconServer.shutdown();
    }

    /**
     * Get the configuration
     *
     * @return The instance of configuraion
     */
    public Config getConfig() {
        return config;
    }
}
