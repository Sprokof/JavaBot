package com.javadiscord.javabot.commands.jam.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class JamSubmission {
	private long id;
	private LocalDateTime createdAt;
	private Jam jam;
	private String themeName;
	private long userId;
	private String sourceLink;
	private String description;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JamSubmission)) return false;
		JamSubmission that = (JamSubmission) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
