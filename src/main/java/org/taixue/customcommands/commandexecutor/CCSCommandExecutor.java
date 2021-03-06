package org.taixue.customcommands.commandexecutor;

import org.taixue.customcommands.Plugin;
import org.taixue.customcommands.language.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CCSCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            return false;
        }
        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("version")) {
                Messages.setNewVariable("permission", "ccs.version");
                if (commandSender.hasPermission("ccs.version")) {
                    Messages.sendMessageString(commandSender, "Plugin name: " + Plugin.NAME);
                    Messages.sendMessageString(commandSender, "Version: " + Plugin.VERSION);
                    Messages.sendMessageString(commandSender, "Author: " + Plugin.AUTHOR);
                    Messages.sendMessageString(commandSender, "Organization: " + Plugin.ORGANIZATION);
                    Messages.sendMessageString(commandSender, "Github: " + Plugin.GITHUB);
                    Messages.sendMessageString(commandSender, "Mcbbs: " + Plugin.MCBBS);
                    Messages.sendMessageString(commandSender, "QQ group: " + Plugin.QQ_GROUP);

                    Messages.sendMessageString(commandSender, "Remember to give me a star :)");
                    return true;
                }
                else {
                    Messages.sendMessage(commandSender, "lackPermission");
                }
            }
            if (strings[0].equalsIgnoreCase("reload")) {
                Messages.setNewVariable("permission", "ccs.config.reload");
                if (commandSender.hasPermission("ccs.config.reload")) {
                    Plugin.reload();
                    Messages.infoString(commandSender.getName() + " reloaded CustomCommands.");
                    Messages.sendMessageString(commandSender, "CustomCommands reload completely!");
                } else {
                    Messages.sendMessage(commandSender, "lackPermission");
                }
                return true;
            }
            if (strings[0].equalsIgnoreCase("debug")) {
                Messages.setNewVariable("permission", "ccs.config.debug");
                if (commandSender.hasPermission("ccs.config.debug")) {
                    if (Plugin.debug) {
                        Messages.sendMessageString(commandSender, "Debug: off");
                    } else {
                        Messages.sendMessageString(commandSender, "Debug: on");
                    }
                    Plugin.debug = !Plugin.debug;
                    Plugin.pluginConfig.set("debug", Plugin.debug);
                    Plugin.saveConfigFile();
                } else {
                    Messages.sendMessageString(commandSender, "lackPermission");
                }
                return true;
            }
            else {
                return false;
            }
        }
        if (strings[0].equalsIgnoreCase("config")) {
            return Plugin.CCSC_COMMAND_EXECUTOR.onCommand(
                    commandSender,
                    command,
                    s,
                    Arrays.asList(strings).subList(1, strings.length).toArray(strings));
        }
        if (strings[0].equalsIgnoreCase("run")) {
            return Plugin.CCSR_COMMAND_EXECUTOR.onCommand(
                    commandSender,
                    command,
                    s,
                    Arrays.asList(strings).subList(1, strings.length).toArray(strings));
        }
        if (strings[0].equalsIgnoreCase("env")) {
            return Plugin.CCSE_COMMAND_EXECUTOR.onCommand(
                    commandSender,
                    command,
                    s,
                    Arrays.asList(strings).subList(1, strings.length).toArray(strings));
        }
        return false;
    }
}
