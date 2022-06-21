package net.javadiscord.javabot.systems.qotw.commands.questions_queue;

import io.sentry.Sentry;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.restaction.interactions.InteractionCallbackAction;
import net.javadiscord.javabot.Bot;
import net.javadiscord.javabot.util.ExceptionLogger;
import net.javadiscord.javabot.util.Responses;
import net.javadiscord.javabot.systems.qotw.dao.QuestionQueueRepository;
import net.javadiscord.javabot.systems.qotw.model.QOTWQuestion;
import net.javadiscord.javabot.systems.qotw.commands.QOTWSubcommand;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Subcommand that allows staff-members to remove single questions from the QOTW Queue.
 */
public class RemoveQuestionSubcommand extends QOTWSubcommand {
	@Override
	protected InteractionCallbackAction<?> handleCommand(SlashCommandInteractionEvent event, Connection con, long guildId) throws SQLException {
		OptionMapping idOption = event.getOption("id");
		if (idOption == null) {
			return Responses.warning(event, "Missing required arguments.");
		}
		long id = idOption.getAsLong();
		boolean removed = new QuestionQueueRepository(con).removeQuestion(guildId, id);
		if (removed) {
			return Responses.success(event, "Question Removed", "The question with id `" + id + "` has been removed.");
		} else {
			return Responses.warning(event, "Could not remove question with id `" + id + "`. Are you sure it exists?");
		}
	}

	/**
	 * Replies with all Question of the Week Questions.
	 *
	 * @param event The {@link CommandAutoCompleteInteractionEvent} that was fired.
	 * @return A {@link List} with all Option Choices.
	 */
	public static List<Command.Choice> replyQuestions(CommandAutoCompleteInteractionEvent event) {
		List<Command.Choice> choices = new ArrayList<>(25);
		try (Connection con = Bot.dataSource.getConnection()) {
			QuestionQueueRepository repo = new QuestionQueueRepository(con);
			List<QOTWQuestion> questions = repo.getQuestions(event.getGuild().getIdLong(), 0, 25);
			questions.forEach(question -> choices.add(new Command.Choice(String.format("(Priority: %s) %s", question.getPriority(), question.getText()), question.getId())));
		} catch (SQLException e) {
			ExceptionLogger.capture(e, getClass().getSimpleName());
		}
		return choices;
	}
}
