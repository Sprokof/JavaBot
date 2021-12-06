package com.javadiscord.javabot.data.properties.config.guild;

import com.javadiscord.javabot.data.properties.config.GuildConfigItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.dv8tion.jda.api.entities.NewsChannel;
import net.dv8tion.jda.api.entities.TextChannel;

@Data
@EqualsAndHashCode(callSuper = true)
public class QOTWConfig extends GuildConfigItem {
	private boolean dmEnabled;
	private long submissionChannelId;
	private long questionChannelId;

	public TextChannel getSubmissionChannel() {
		return this.getGuild().getTextChannelById(this.submissionChannelId);
	}

	public NewsChannel getQuestionChannel() {
		return this.getGuild().getNewsChannelById(this.questionChannelId);
	}
}
