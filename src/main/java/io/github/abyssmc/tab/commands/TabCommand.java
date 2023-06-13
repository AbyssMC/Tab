package io.github.abyssmc.tab.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.abyssmc.tab.start.StartManager;

public class TabCommand implements CommandExecutor {

    private final StartManager manager;

    public TabCommand(StartManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1 || !args[0].equals("reload")) {
            sender.sendMessage(getFormat());
            return true;
        }
        manager.reload();
        sender.sendMessage("Please rejoin to see the changes");
        return true;
    }

    private String getFormat() {
        return
            "\n " +
            "\n §9QTab §7- by iChocoMilk" +
            "\n " +
            "\n §b/qtab ->" +
            "\n   §freload §7- Reload configuration" +
            "\n ";
    }
}
