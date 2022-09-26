package net.javadiscord.javabot.systems.help.commands;

import java.util.concurrent.ScheduledExecutorService;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.javadiscord.javabot.data.config.BotConfig;
import net.javadiscord.javabot.data.h2db.DbActions;
import net.javadiscord.javabot.systems.help.HelpExperienceService;
import net.javadiscord.javabot.systems.help.dao.HelpAccountRepository;
import net.javadiscord.javabot.systems.help.dao.HelpTransactionRepository;

/**
 * A simple command that can be used inside reserved help channels to immediately unreserve them,
 * instead of waiting for a timeout.
 * An alias to /unreserve
 */
public class CloseCommand extends UnreserveCommand {

	/**
	 * The constructor of this class, which sets the corresponding
	 * {@link net.dv8tion.jda.api.interactions.commands.build.SlashCommandData}.
	 * @param asyncPool The thread pool for asynchronous operations
	 * @param botConfig The main configuration of the bot
	 * @param dbActions A utility object providing various operations on the main database
	 * @param helpExperienceService Service object that handles Help Experience Transactions.
	 * @param helpTransactionRepository Dao object that represents the HELP_TRANSACTION SQL Table.
	 * @param helpAccountRepository Dao object that represents the HELP_ACCOUNT SQL Table.
	 */
	public CloseCommand(BotConfig botConfig, ScheduledExecutorService asyncPool, DbActions dbActions, HelpExperienceService helpExperienceService, HelpTransactionRepository helpTransactionRepository, HelpAccountRepository helpAccountRepository) {
		super(botConfig, asyncPool, dbActions, helpExperienceService, helpTransactionRepository, helpAccountRepository);
		setSlashCommandData(
				Commands.slash("close", "Unreserves this help channel so that others can use it.")
						.setGuildOnly(true).addOption(OptionType.STRING, "reason",
								"The reason why you're unreserving this channel", false));
	}
}
